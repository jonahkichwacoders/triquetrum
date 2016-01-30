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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.Platform;
import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.IAddFeature;
import org.eclipse.graphiti.features.ICreateConnectionFeature;
import org.eclipse.graphiti.features.ICreateFeature;
import org.eclipse.graphiti.features.IDirectEditingFeature;
import org.eclipse.graphiti.features.IFeature;
import org.eclipse.graphiti.features.IUpdateFeature;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.context.IDirectEditingContext;
import org.eclipse.graphiti.features.context.IPictogramElementContext;
import org.eclipse.graphiti.features.context.IUpdateContext;
import org.eclipse.graphiti.features.custom.ICustomFeature;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.ui.features.DefaultFeatureProvider;
import org.eclipse.triquetrum.workflow.editor.features.ActorAddFeature;
import org.eclipse.triquetrum.workflow.editor.features.ActorUpdateFeature;
import org.eclipse.triquetrum.workflow.editor.features.ConnectionAddFeature;
import org.eclipse.triquetrum.workflow.editor.features.ConnectionCreateFeature;
import org.eclipse.triquetrum.workflow.editor.features.DirectorAddFeature;
import org.eclipse.triquetrum.workflow.editor.features.DirectorUpdateFeature;
import org.eclipse.triquetrum.workflow.editor.features.LookInsideFeature;
import org.eclipse.triquetrum.workflow.editor.features.ModelElementCreateFeature;
import org.eclipse.triquetrum.workflow.editor.features.ModelElementNameDirectEditFeature;
import org.eclipse.triquetrum.workflow.editor.features.ParameterUpdateFeature;
import org.eclipse.triquetrum.workflow.editor.features.PortAddFeature;
import org.eclipse.triquetrum.workflow.model.Actor;
import org.eclipse.triquetrum.workflow.model.CompositeActor;
import org.eclipse.triquetrum.workflow.model.Director;
import org.eclipse.triquetrum.workflow.model.NamedObj;
import org.eclipse.triquetrum.workflow.model.Parameter;
import org.eclipse.triquetrum.workflow.model.Port;
import org.eclipse.triquetrum.workflow.model.Relation;

/**
 * Creates all Triq editor features, including ICreateFeatures for actors as defined in palette extension contributions.
 *
 */
public class TriqFeatureProvider extends DefaultFeatureProvider {
  public static final String PALETTE_CONTRIBUTION_EXTENSION_ID = "org.eclipse.triquetrum.workflow.editor.paletteContribution";

  /**
   * This map maintains the registered palette contributions for groups.
   * For the moment we only support 1 level, i.e. no tree/hierarchy yet.
   * This map is consulted during the execution of org.eclipse.triquetrum.workflow.editor.TriqToolBehaviorProvider.getPalette()
   */
  private Map<String, IConfigurationElement> rootgroupsByName = new HashMap<>();

  /**
   * @param dtp
   */
  public TriqFeatureProvider(IDiagramTypeProvider dtp) {
    super(dtp);
  }

  public Map<String, IConfigurationElement> getRootgroupsByName() {
    return rootgroupsByName;
  }

  @Override
  public ICustomFeature[] getCustomFeatures(ICustomContext context) {
    return new ICustomFeature[] { new LookInsideFeature(this) };
  }

  @Override
  public IFeature[] getDragAndDropFeatures(IPictogramElementContext context) {
    // simply return all create connection features
    return getCreateConnectionFeatures();
  }

  @Override
  public ICreateConnectionFeature[] getCreateConnectionFeatures() {
    return new ICreateConnectionFeature[] { new ConnectionCreateFeature(this) };
  }

  @Override
  public ICreateFeature[] getCreateFeatures() {
    List<ICreateFeature> results = new ArrayList<>();
    IExtensionPoint extPoint = Platform.getExtensionRegistry().getExtensionPoint(PALETTE_CONTRIBUTION_EXTENSION_ID);
    for (IExtension ext : extPoint.getExtensions()) {
      for (IConfigurationElement cfgElem : ext.getConfigurationElements()) {
        handlePaletteEntry(results, null, cfgElem);
      }
    }
    return results.toArray(new ICreateFeature[0]);
  }

  @Override
  public IAddFeature getAddFeature(IAddContext context) {
    if (context.getNewObject() instanceof Director) {
      return new DirectorAddFeature(this);
    } else if ((context.getNewObject() instanceof Actor)||(context.getNewObject() instanceof CompositeActor)) {
      return new ActorAddFeature(this);
    } else if (context.getNewObject() instanceof Relation) {
      return new ConnectionAddFeature(this);
    } else if (context.getNewObject() instanceof Port) {
      return new PortAddFeature(this);
    }
    return super.getAddFeature(context);
  }

  @Override
  public IUpdateFeature getUpdateFeature(IUpdateContext context) {
    PictogramElement pictogramElement = context.getPictogramElement();
    Object bo = getBusinessObjectForPictogramElement(pictogramElement);
    if (bo instanceof Parameter) {
      return new ParameterUpdateFeature(this);
    } else if (bo instanceof Director) {
      return new DirectorUpdateFeature(this);
    } else if (bo instanceof Actor) {
      return new ActorUpdateFeature(this);
    }
    return super.getUpdateFeature(context);
  }

  @Override
  public IDirectEditingFeature getDirectEditingFeature(IDirectEditingContext context) {
    PictogramElement pe = context.getPictogramElement();
    Object bo = getBusinessObjectForPictogramElement(pe);
    if (bo instanceof NamedObj) {
      return new ModelElementNameDirectEditFeature(this);
    }
    return super.getDirectEditingFeature(context);
  }

  public void handlePaletteEntry(List<ICreateFeature> results, IConfigurationElement parentGroupElem, IConfigurationElement cfgElem) {
    switch (cfgElem.getName()) {
    case "entry": {
      String group = parentGroupElem != null ? parentGroupElem.getAttribute("displayName") : null;
      String label = cfgElem.getAttribute("displayName");
      String clazz = cfgElem.getAttribute("class");
      String iconResource = cfgElem.getAttribute("icon");
      String eClassName = cfgElem.getAttribute("type");
      // look for (optional) attributes
      Map<String, String> properties = new HashMap<>();
      for(IConfigurationElement child : cfgElem.getChildren()) {
        if("property".equals(child.getName())) {
          String name = child.getAttribute("name");
          String value = child.getAttribute("value");
          properties.put(name, value);
        }
      }
      ModelElementCreateFeature mecf = new ModelElementCreateFeature(this, group, eClassName, label, clazz, iconResource, properties);
      if (iconResource != null) {
        // option 1 to register extra images from palette extensions
        // not an ideal hack, as we need to replicate Graphiti's ad-hoc internal image key construction
        //(cfr. org.eclipse.graphiti.ui.internal.services.impl.ImageService.createImageDescriptorForId(String, String))
//        ImageDescriptor imageDescriptor = TriqEditorPlugin.imageDescriptorFromPlugin(cfgElem.getContributor().getName(), iconResource);
//        GraphitiUIPlugin.getDefault().getImageRegistry().put(makeKey(TriqDiagramTypeProvider.ID,iconResource), imageDescriptor);

        // option 2 : cfr suggestion in https://bugs.eclipse.org/bugs/show_bug.cgi?id=366452#c8
        ((TriqDiagramTypeProvider) getDiagramTypeProvider()).getImageProvider().myAddImageFilePath(cfgElem.getContributor().getName(), iconResource, iconResource);
      }
      results.add(mecf);
      break;
    }
    case "group": {
      String label = cfgElem.getAttribute("displayName");
      // this should enforce a single level of groups, i.e. children of subgroups are traversed and added,
      // but they all get linked to their "top-level" parent group.
      IConfigurationElement groupElement = parentGroupElem;
      if(parentGroupElem == null) {
        groupElement = cfgElem;
        // no parent, so store this group as a root group
        rootgroupsByName.put(label, cfgElem);
      }
      for (IConfigurationElement child : cfgElem.getChildren()) {
        handlePaletteEntry(results, groupElement, child);
      }
    }
    }
  }

  // this is needed for option 1 to register extra images from palette extensions
//  private String makeKey(String dtp, String imageId) {
//    return dtp + "||" + imageId;
//  }

}
