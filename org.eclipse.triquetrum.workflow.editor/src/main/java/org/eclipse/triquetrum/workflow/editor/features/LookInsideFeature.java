/*******************************************************************************
 * Copyright (c) 2015-2016 iSencia Belgium NV.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Erwin De Ley - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.triquetrum.workflow.editor.features;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.IResizeShapeFeature;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.context.impl.ResizeShapeContext;
import org.eclipse.graphiti.features.custom.AbstractCustomFeature;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.platform.IPlatformImageConstants;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.triquetrum.workflow.model.CompositeActor;

public class LookInsideFeature extends AbstractCustomFeature {

  public LookInsideFeature(IFeatureProvider fp) {
    super(fp);
  }

  @Override
  public void execute(ICustomContext context) {
    PictogramElement[] pes = context.getPictogramElements();
    if (pes != null && pes.length == 1) {
      Object bo = getBusinessObjectForPictogramElement(pes[0]);
      if (bo instanceof CompositeActor) {
        lookInsideShape(pes[0]);
      }
    }
  }

  private void lookInsideShape(PictogramElement pe) {
    ContainerShape cs = (ContainerShape) pe;
    int width = pe.getGraphicsAlgorithm().getWidth();
    int height = pe.getGraphicsAlgorithm().getHeight();

    int changeWidth = width*5;
    int changeHeight = height*3;

    boolean visible = false;
    if ("true".equals(Graphiti.getPeService().getPropertyValue(pe, "isExpanded"))) {
      changeWidth = Integer.parseInt(Graphiti.getPeService().getPropertyValue(pe, "initial_width"));
      changeHeight = Integer.parseInt(Graphiti.getPeService().getPropertyValue(pe, "initial_height"));
      Graphiti.getPeService().setPropertyValue(pe, "isExpanded", "false");
      visible = false;
    } else {
      Graphiti.getPeService().setPropertyValue(pe, "initial_width", String.valueOf(width));
      Graphiti.getPeService().setPropertyValue(pe, "initial_height", String.valueOf(height));
      visible = true;
    }

    ResizeShapeContext context1 = new ResizeShapeContext(cs);
    context1.setSize(changeWidth, changeHeight);
    context1.setLocation(cs.getGraphicsAlgorithm().getX(), cs.getGraphicsAlgorithm().getY());
    IResizeShapeFeature rsf = getFeatureProvider().getResizeShapeFeature(context1);
    if (rsf.canExecute(context1)) {
      rsf.execute(context1);
    }

    if (visible) {
      Graphiti.getPeService().setPropertyValue(pe, "isExpanded", "true");
    }
    // visible/invisible all the children
    makeChildrenInvisible(cs, visible);
  }

  @Override
  public boolean canExecute(ICustomContext context) {
    boolean ret = false;
    PictogramElement[] pes = context.getPictogramElements();
    if (pes != null && pes.length == 1) {
      Object bo = getBusinessObjectForPictogramElement(pes[0]);
      if (bo instanceof CompositeActor) {
        ret = true;
      }
    }
    return ret;
  }

  @Override
  public String getName() {
    return "Look Inside";
  }

  @Override
  public String getDescription() {
    return "Look inside a Composite Actor";
  }

  @Override
  public String getImageId() {
    return IPlatformImageConstants.IMG_EDIT_COLLAPSE;
  }

  /**
   * Sets visibility for the children of a model element's shape.
   *
   * @param cs
   * @param visible
   */
  public void makeChildrenInvisible(ContainerShape cs, boolean visible) {
    if (cs.getChildren().isEmpty()) {
      return;
    } else {
      for(Shape childShape : cs.getChildren()) {
        String boCategory = Graphiti.getPeService().getPropertyValue(childShape, "__BO_CATEGORY");
        if (childShape instanceof ContainerShape) {
          makeChildrenInvisible((ContainerShape) childShape, visible);
          childShape.setVisible(visible);
        } else if ("PARAMETER".equals(boCategory)) {
          childShape.setVisible(visible);
        } else if ("PORT".equals(boCategory)) {
          childShape.setVisible(visible);
        } else if ("INPUT".equals(boCategory)) {

        } else if ("OUTPUT".equals(boCategory)) {

        }
      }
    }
  }
}
