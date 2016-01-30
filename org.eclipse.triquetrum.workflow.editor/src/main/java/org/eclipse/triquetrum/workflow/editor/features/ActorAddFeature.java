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
package org.eclipse.triquetrum.workflow.editor.features;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.graphiti.features.IDirectEditingInfo;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.impl.AbstractAddShapeFeature;
import org.eclipse.graphiti.mm.algorithms.Polygon;
import org.eclipse.graphiti.mm.algorithms.Polyline;
import org.eclipse.graphiti.mm.algorithms.Rectangle;
import org.eclipse.graphiti.mm.algorithms.RoundedRectangle;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.algorithms.styles.Orientation;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.FixPointAnchor;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.ICreateService;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.services.IPeCreateService;
import org.eclipse.graphiti.util.ColorConstant;
import org.eclipse.graphiti.util.IColorConstant;
import org.eclipse.triquetrum.workflow.model.Actor;
import org.eclipse.triquetrum.workflow.model.CompositeActor;
import org.eclipse.triquetrum.workflow.model.Entity;
import org.eclipse.triquetrum.workflow.model.NamedObj;
import org.eclipse.triquetrum.workflow.model.Parameter;
import org.eclipse.triquetrum.workflow.model.Port;

public class ActorAddFeature extends AbstractAddShapeFeature {

  private static final int SHAPE_X_OFFSET = 8;
  private static final IColorConstant ACTOR_NAME_FOREGROUND = IColorConstant.BLACK;
  private static final IColorConstant PARAM_FOREGROUND = IColorConstant.DARK_GRAY;

  private static final IColorConstant ACTOR_FOREGROUND = new ColorConstant(98, 131, 167);
  private static final IColorConstant ACTOR_BACKGROUND = new ColorConstant(187, 218, 247);
  private static final IColorConstant PORT_FOREGROUND = IColorConstant.BLACK;
  private static final IColorConstant PORT_BACKGROUND = IColorConstant.WHITE;

  private static final Map<Class<? extends Port>, IColorConstant> portColours;

  static {
    portColours = new HashMap<Class<? extends Port>, IColorConstant>();
    // portColours.put(ErrorPort.class, ERRORPORT_BACKGROUND);
    // portColours.put(ControlPort.class, CONTROLPORT_BACKGROUND);
  }

  public ActorAddFeature(IFeatureProvider fp) {
    super(fp);
  }

  protected void link(PictogramElement pe, Object businessObject, String category) {
    super.link(pe, businessObject);
    // add property on the graphical model element, identifying the associated triq model element
    // so we can easily distinguish and identify them later on for updates etc
    if (businessObject instanceof NamedObj) {
      Graphiti.getPeService().setPropertyValue(pe, "__BO_NAME", ((NamedObj) businessObject).getName());
    }
    Graphiti.getPeService().setPropertyValue(pe, "__BO_CATEGORY", category);
    Graphiti.getPeService().setPropertyValue(pe, "__BO_CLASS", businessObject.getClass().getName());
  }

  public boolean canAdd(IAddContext context) {
    // check if user wants to add an actor
    if ((context.getNewObject() instanceof Actor)||(context.getNewObject() instanceof CompositeActor)) {
      // check if user wants to add to a diagram
      if (context.getTargetContainer() instanceof Diagram) {
        return true;
      }
    }
    return false;
  }

  public PictogramElement add(IAddContext context) {
    Entity addedActor = (Entity) context.getNewObject();
    ContainerShape targetContainer = context.getTargetContainer();

    Object topLevelForDiagram = getBusinessObjectForPictogramElement(getDiagram());
    if (topLevelForDiagram == null) {
      link(getDiagram(), addedActor.getContainer());
    }

    int xLocation = context.getX();
    int yLocation = context.getY();

    // CONTAINER SHAPE WITH ROUNDED RECTANGLE
    IPeCreateService peCreateService = Graphiti.getPeCreateService();
    ICreateService createService = Graphiti.getCreateService();
    IGaService gaService = Graphiti.getGaService();
    ContainerShape containerShape = peCreateService.createContainerShape(targetContainer, true);

    // define a default size for the shape
    int width = 100;
    int height = 60;

    Rectangle invisibleRectangle; // need to access it later
    {
      invisibleRectangle = gaService.createInvisibleRectangle(containerShape);
      gaService.setLocationAndSize(invisibleRectangle, xLocation, yLocation, width + 15, height);

      // create and set graphics algorithm
      RoundedRectangle actorRectangle = gaService.createRoundedRectangle(invisibleRectangle, 5, 5);
      actorRectangle.setForeground(manageColor(ACTOR_FOREGROUND));
      actorRectangle.setBackground(manageColor(ACTOR_BACKGROUND));
      actorRectangle.setLineWidth(2);
      gaService.setLocationAndSize(actorRectangle, SHAPE_X_OFFSET, 0, width, height);

      // if added Class has no resource we add it to the resource
      // of the diagram
      // in a real scenario the business model would have its own resource
      // if (addedActor.eResource() == null) {
      // getDiagram().eResource().getContents().add(addedActor);
      // }
      // create link and wire it
      link(containerShape, addedActor, "ACTOR");
    }

    // SHAPE WITH LINE
    {
      // create shape for line
      Shape shape = peCreateService.createShape(containerShape, false);

      // create and set graphics algorithm
      Polyline polyline = gaService.createPolyline(shape, new int[] { SHAPE_X_OFFSET, 20, SHAPE_X_OFFSET + width, 20 });
      polyline.setForeground(manageColor(ACTOR_FOREGROUND));
      polyline.setLineWidth(2);
      link(shape, addedActor, "ACTOR");
    }

    // SHAPE WITH actor name as TEXT
    {
      // create shape for text
      Shape shape = peCreateService.createShape(containerShape, false);

      // create and set text graphics algorithm
      Text text = gaService.createText(shape, addedActor.getName());
      text.setForeground(manageColor(ACTOR_NAME_FOREGROUND));
      text.setHorizontalAlignment(Orientation.ALIGNMENT_CENTER);
      // vertical alignment has as default value "center"
      text.setFont(gaService.manageDefaultFont(getDiagram(), false, true));
      gaService.setLocationAndSize(text, SHAPE_X_OFFSET, 0, width, 20);

      // create link and wire it
      link(shape, addedActor, "ACTOR");

      // provide information to support direct-editing directly
      // after object creation (must be activated additionally)
      IDirectEditingInfo directEditingInfo = getFeatureProvider().getDirectEditingInfo();
      // set container shape for direct editing after object creation
      directEditingInfo.setMainPictogramElement(containerShape);
      // set shape and graphics algorithm where the editor for
      // direct editing shall be opened after object creation
      directEditingInfo.setPictogramElement(shape);
      directEditingInfo.setGraphicsAlgorithm(text);
    }

    // SHAPES for basic configurable parameters (other parameters will be in the
    // Configure dialogue, but not shown by default in the actor shape)
    {
      int pIndex = 0;
      for (Parameter param : addedActor.getParameters()) {
        // create shape for text
        Shape shape = peCreateService.createShape(containerShape, false);

        // create and set text graphics algorithm
        String pName = param.getName();
        String pVal = param.getExpression();
        pName = (pName.length() > 12) ? pName.substring(0, 12) : pName;
        pVal = (pVal.length() > 12) ? pVal.substring(0, 12) : pVal;

        Text text = gaService.createText(shape, pName + " : " + pVal);
        // IUiLayoutService uil = GraphitiUi.getUiLayoutService();
        // IDimension dim = uil.calculateTextSize(text.getValue(),
        // text.getFont());

        text.setForeground(manageColor(PARAM_FOREGROUND));
        text.setHorizontalAlignment(Orientation.ALIGNMENT_LEFT);
        // vertical alignment has as default value "center"
        text.setFont(gaService.manageFont(getDiagram(), IGaService.DEFAULT_FONT, 6));
        gaService.setLocationAndSize(text, SHAPE_X_OFFSET + 5, 22 + 20 * pIndex++, width, 20);

        // create link and wire it
        link(shape, param, "PARAMETER");
        Graphiti.getPeService().setPropertyValue(shape, "__BO_VALUE", param.getExpression());
      }
    }

    // SHAPES FOR PORTS
    {
      // add output port anchor
      int pIndex = 0;
      for (Port p : (List<Port>) addedActor.getOutputPorts()) {
        FixPointAnchor anchor = peCreateService.createFixPointAnchor(containerShape);
        anchor.setLocation(createService.createPoint(15 + width, 10 + (pIndex++) * 12));
        anchor.setReferencedGraphicsAlgorithm(invisibleRectangle);
        link(anchor, p, "OUTPUT");
        // assign a rectangle graphics algorithm for the box relative anchor
        // note, that the rectangle is inside the border of the rectangle shape
        final Polygon portShape = gaService.createPlainPolygon(anchor, new int[] { 0, 0, 12, 6, 0, 12 });
        portShape.setForeground(manageColor(PORT_FOREGROUND));
        IColorConstant portColour = portColours.get(p.getClass());
        portColour = portColour != null ? portColour : PORT_BACKGROUND;
        portShape.setBackground(manageColor(portColour));
        portShape.setLineWidth(1);
        gaService.setLocationAndSize(portShape, -12, -6, 12, 12);
      }
      pIndex = 0;
      for (Port p : (List<Port>) addedActor.getInputPorts()) {
        FixPointAnchor anchor = peCreateService.createFixPointAnchor(containerShape);
        anchor.setLocation(createService.createPoint(0, 10 + (pIndex++) * 12));
        anchor.setUseAnchorLocationAsConnectionEndpoint(true);
        anchor.setReferencedGraphicsAlgorithm(invisibleRectangle);
        link(anchor, p, "INPUT");
        // assign a rectangle graphics algorithm for the box relative anchor
        // note, that the rectangle is inside the border of the rectangle shape
        final Polygon portShape = gaService.createPlainPolygon(anchor, new int[] { 0, 0, 12, 6, 0, 12 });
        portShape.setForeground(manageColor(PORT_FOREGROUND));
        IColorConstant portColour = portColours.get(p.getClass());
        portColour = portColour != null ? portColour : PORT_BACKGROUND;
        portShape.setBackground(manageColor(portColour));
        portShape.setLineWidth(1);
        gaService.setLocationAndSize(portShape, 0, -6, 12, 12);
      }

      layoutPictogramElement(containerShape);
    }

    return containerShape;
  }
}