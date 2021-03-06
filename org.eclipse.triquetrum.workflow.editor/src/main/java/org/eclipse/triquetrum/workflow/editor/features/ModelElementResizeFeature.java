/*******************************************************************************
 * Copyright (c) 2016 iSencia Belgium NV.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Erwin De Ley - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.triquetrum.workflow.editor.features;

import org.eclipse.graphiti.features.DefaultResizeConfiguration;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.IResizeConfiguration;
import org.eclipse.graphiti.features.context.IResizeShapeContext;
import org.eclipse.graphiti.features.impl.DefaultResizeShapeFeature;

public class ModelElementResizeFeature extends DefaultResizeShapeFeature {

  public ModelElementResizeFeature(IFeatureProvider fp) {
    super(fp);
  }

  @Override
  public IResizeConfiguration getResizeConfiguration(IResizeShapeContext context) {
    return new NoResizeConfiguration();
  }

  private static class NoResizeConfiguration extends DefaultResizeConfiguration {
    @Override
    public boolean isHorizontalResizeAllowed() {
      return false;
    }

    @Override
    public boolean isVerticalResizeAllowed() {
      return false;
    }
  }
}
