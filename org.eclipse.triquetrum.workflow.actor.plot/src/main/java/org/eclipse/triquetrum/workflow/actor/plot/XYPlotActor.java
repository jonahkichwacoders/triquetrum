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
package org.eclipse.triquetrum.workflow.actor.plot;

import org.eclipse.triquetrum.workflow.actor.plot.ui.XYPlot;

import ptolemy.actor.TypedAtomicActor;
import ptolemy.actor.TypedIOPort;
import ptolemy.data.DoubleToken;
import ptolemy.data.expr.StringParameter;
import ptolemy.data.type.BaseType;
import ptolemy.kernel.CompositeEntity;
import ptolemy.kernel.util.IllegalActionException;
import ptolemy.kernel.util.NameDuplicationException;

/**
 * This actor implements a an XY plot on Nebula's XYGraph visualization widget.
 * <p>
 * It is a simplified reimplementation of ptolemy.actor.lib.gui.XYPlotter :
 * <ul>
 * <li>Supports only one dataset, i.e. inputX and inputY are single-channel ports</li>
 * <li>Creates an own plot widget, i.e. no shared plot across several plot actors</li>
 * </ul>
 * If the inputX is not connected, an iteration counter is passed as X value, i.e. the XYPlotActor behaves more-or-less like a sequence plotter.
 * </p>
 */
public class XYPlotActor extends TypedAtomicActor {

  /**
   * The title to put on top. Note that the value of the title overrides the value of the name of the actor or the display name of the actor.
   */
  public StringParameter title;
  /**
   * The name to be assigned to the X axis
   */
  public StringParameter xName;
  /**
   * The name to be assigned to the Y axis
   */
  public StringParameter yName;

  /**
   * The input receiving the X double values
   */
  public TypedIOPort inputX;
  /**
   * The input receiving the Y double values, paired to the X values received in the same iterations
   */
  public TypedIOPort inputY;

  /**
   * An x counter that is used to generate x values i.c.o. an unconnected x port.
   */
  private double xCounter;

  /**
   * The plot widget wrapper used by the actor instance.
   */
  private XYPlot plot;

  /**
   * Construct an actor with the given container and name.
   *
   * @param container
   * @param name
   * @throws IllegalActionException
   * @throws NameDuplicationException
   */
  public XYPlotActor(CompositeEntity container, String name) throws IllegalActionException, NameDuplicationException {
    super(container, name);
    inputX = new TypedIOPort(this, "inputX", true, false);
    inputX.setTypeEquals(BaseType.DOUBLE);

    inputY = new TypedIOPort(this, "inputY", true, false);
    inputY.setTypeEquals(BaseType.DOUBLE);

    title = new StringParameter(this, "title");
    xName = new StringParameter(this, "X name");
    xName.setExpression("X");
    yName = new StringParameter(this, "Y name");
    yName.setExpression("Y");
  }

  @Override
  public void initialize() throws IllegalActionException {
    super.initialize();
    xCounter = 0d;
    plot = new XYPlot();
    try {
      plot.init(this);
      plot.openWindow();
    } catch (Exception e) {
      throw new IllegalActionException(this, e, "error initialising XYPlot");
    }
  }

  @Override
  public boolean postfire() throws IllegalActionException {
    boolean hasX = false;
    boolean hasY = false;
    double xValue = 0.0;
    double yValue = 0.0;
    if (inputX.getWidth() > 0) {
      if (inputX.hasToken(0)) {
        xValue = ((DoubleToken) inputX.get(0)).doubleValue();
        hasX = true;
      }
    } else {
      xValue = xCounter++;
      hasX = true;
    }
    if (inputY.hasToken(0)) {
      yValue = ((DoubleToken) inputY.get(0)).doubleValue();
      hasY = true;
    }
    if (hasX && hasY) {
      plot.addPoint(xValue, yValue);
    }
    return super.postfire();
  }
}
