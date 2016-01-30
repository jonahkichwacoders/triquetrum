/*******************************************************************************
 * Copyright (c) 2015 iSencia Belgium NV.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Erwin De Ley - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.triquetrum.workflow.editor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.ICreateFeature;
import org.eclipse.graphiti.features.context.IDoubleClickContext;
import org.eclipse.graphiti.features.context.IPictogramElementContext;
import org.eclipse.graphiti.features.context.impl.CustomContext;
import org.eclipse.graphiti.features.custom.ICustomFeature;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.palette.IObjectCreationToolEntry;
import org.eclipse.graphiti.palette.IPaletteCompartmentEntry;
import org.eclipse.graphiti.palette.IToolEntry;
import org.eclipse.graphiti.palette.impl.PaletteCompartmentEntry;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.tb.ContextEntryHelper;
import org.eclipse.graphiti.tb.DefaultToolBehaviorProvider;
import org.eclipse.graphiti.tb.IContextButtonEntry;
import org.eclipse.graphiti.tb.IContextButtonPadData;
import org.eclipse.triquetrum.workflow.editor.features.ActorConfigureFeature;
import org.eclipse.triquetrum.workflow.editor.features.LookInsideFeature;
import org.eclipse.triquetrum.workflow.editor.features.ModelElementCreateFeature;
import org.eclipse.triquetrum.workflow.editor.features.RunFeature;

public class TriqToolBehaviorProvider extends DefaultToolBehaviorProvider {

  public TriqToolBehaviorProvider(IDiagramTypeProvider diagramTypeProvider) {
    super(diagramTypeProvider);
  }

  @Override
  public ICustomFeature getDoubleClickFeature(IDoubleClickContext context) {
    ICustomFeature customFeature = new ActorConfigureFeature((TriqFeatureProvider) getFeatureProvider());
    // canExecute() tests especially if the context contains an actor or director or so
    if (customFeature.canExecute(context)) {
      return customFeature;
    } else {
      return super.getDoubleClickFeature(context);
    }
  }

  @Override
  public ICustomFeature getCommandFeature(CustomContext context, String hint) {
    if (RunFeature.HINT.equals(hint)) {
      return new RunFeature(getFeatureProvider());
    }
    return super.getCommandFeature(context, hint);
  }

  @Override
  public IContextButtonPadData getContextButtonPad(IPictogramElementContext context) {
    IContextButtonPadData data = super.getContextButtonPad(context);
    PictogramElement pe = context.getPictogramElement();

    // 1. set the generic context buttons
    setGenericContextButtons(data, pe, CONTEXT_BUTTON_DELETE | CONTEXT_BUTTON_UPDATE);

    // 2. set the look inside button
    CustomContext cc = new CustomContext(new PictogramElement[] { pe });
    ICustomFeature[] cf = getFeatureProvider().getCustomFeatures(cc);
    for (int i = 0; i < cf.length; i++) {
      ICustomFeature iCustomFeature = cf[i];
      if (iCustomFeature instanceof LookInsideFeature) {
        boolean isExpanded = ("true".equals(Graphiti.getPeService().getPropertyValue(pe, "isExpanded")));
        IContextButtonEntry collapseButton = ContextEntryHelper.createCollapseContextButton(isExpanded, iCustomFeature, cc);
        data.setCollapseContextButton(collapseButton);
        break;
      }
    }
    return data;
  }

  @Override
  public IPaletteCompartmentEntry[] getPalette() {
    final Map<String, IPaletteCompartmentEntry> paletteCompartments = new TreeMap<>();

    TriqFeatureProvider tbp = (TriqFeatureProvider) getDiagramTypeProvider().getFeatureProvider();
    IPaletteCompartmentEntry[] entries = super.getPalette();
    for (IPaletteCompartmentEntry pcEntry : entries) {
      List<IToolEntry> toolEntries = pcEntry.getToolEntries();
      for (IToolEntry tlEntry : toolEntries) {
        if (tlEntry instanceof IObjectCreationToolEntry) {
          IObjectCreationToolEntry octEntry = (IObjectCreationToolEntry) tlEntry;
          ICreateFeature createFeature = octEntry.getCreateFeature();
          IPaletteCompartmentEntry compartment = paletteCompartments.get(pcEntry.getLabel());
          if (createFeature instanceof ModelElementCreateFeature) {
            ModelElementCreateFeature mecFt = (ModelElementCreateFeature) createFeature;
            if (mecFt.getGroup() != null) {
              IConfigurationElement rootGrpElement = tbp.getRootgroupsByName().get(mecFt.getGroup());
              if (rootGrpElement != null) {
                compartment = paletteCompartments.get(mecFt.getGroup());
                if (compartment == null) {
                  String iconResource = rootGrpElement.getAttribute("icon");
                  if (iconResource != null) {
                    ((TriqDiagramTypeProvider) getDiagramTypeProvider()).getImageProvider().myAddImageFilePath(
                        rootGrpElement.getContributor().getName(), iconResource, iconResource);
                  }
                  compartment = new PaletteCompartmentEntry(mecFt.getGroup(), iconResource);
                  paletteCompartments.put(compartment.getLabel(), compartment);
                }
              }
            }
          }
          if (compartment == null) {
            compartment = new PaletteCompartmentEntry(pcEntry.getLabel(), pcEntry.getIconId());
            paletteCompartments.put(compartment.getLabel(), compartment);
          }
          compartment.getToolEntries().add(tlEntry);
        }
      }
    }

    return new ArrayList<>(paletteCompartments.values()).toArray(new IPaletteCompartmentEntry[0]);
  }

  @Override
  public boolean isShowFlyoutPalette() {
    return true;
  }
}
