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
package org.eclipse.triquetrum.workflow.model.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.triquetrum.workflow.model.Attribute;
import org.eclipse.triquetrum.workflow.model.TriqPackage;
import org.eclipse.triquetrum.workflow.model.util.PtolemyUtil;

import ptolemy.kernel.util.IllegalActionException;
import ptolemy.kernel.util.NameDuplicationException;
import ptolemy.kernel.util.NamedObj;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Attribute</b></em>'.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public class AttributeImpl extends NamedObjImpl implements Attribute {
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  protected AttributeImpl() {
    super();
    // this is the default type from Ptolemy that we'll be using
    setWrappedType("ptolemy.kernel.util.Attribute");
  }

  // This is where we can hook in a ptolemy object construction, including its container
  @Override
  protected void eBasicSetContainer(InternalEObject newContainer) {
    NamedObj oldPtContainer = (NamedObj) (getContainer() != null ? getContainer().getWrappedObject() : null);
    super.eBasicSetContainer(newContainer);
    NamedObj newPtContainer = (NamedObj) (getContainer() != null ? getContainer().getWrappedObject() : null);
    if(oldPtContainer!=newPtContainer) {
      try {
        getWrappedObject().setContainer(newPtContainer);
      } catch (IllegalActionException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } catch (NameDuplicationException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
  }

  @Override
  public void buildWrappedObject() {
    try {
      NamedObj container = (NamedObj) (getContainer() != null ? getContainer().getWrappedObject() : null);
      wrappedObject = PtolemyUtil._createAttribute(container, getWrappedType(), getName());
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  @Override
  public ptolemy.kernel.util.Attribute getWrappedObject() {
    return (ptolemy.kernel.util.Attribute) wrappedObject;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass() {
    return TriqPackage.Literals.ATTRIBUTE;
  }

} //AttributeImpl
