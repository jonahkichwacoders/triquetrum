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

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.triquetrum.workflow.model.Attribute;
import org.eclipse.triquetrum.workflow.model.CompositeEntity;
import org.eclipse.triquetrum.workflow.model.Entity;
import org.eclipse.triquetrum.workflow.model.NamedObj;
import org.eclipse.triquetrum.workflow.model.Parameter;
import org.eclipse.triquetrum.workflow.model.Port;
import org.eclipse.triquetrum.workflow.model.Relation;
import org.eclipse.triquetrum.workflow.model.TriqFactory;
import org.eclipse.triquetrum.workflow.model.TriqPackage;
import org.eclipse.triquetrum.workflow.model.util.PtolemyUtil;

import ptolemy.actor.IOPort;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Entity</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.triquetrum.workflow.model.impl.EntityImpl#getInputPorts <em>Input Ports</em>}</li>
 *   <li>{@link org.eclipse.triquetrum.workflow.model.impl.EntityImpl#getOutputPorts <em>Output Ports</em>}</li>
 * </ul>
 *
 * @generated
 */
public class EntityImpl extends NamedObjImpl implements Entity {
  /**
   * The cached value of the '{@link #getInputPorts() <em>Input Ports</em>}' containment reference list.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @see #getInputPorts()
   * @generated
   * @ordered
   */
  protected EList<Port> inputPorts;
  /**
   * The cached value of the '{@link #getOutputPorts() <em>Output Ports</em>}' containment reference list.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @see #getOutputPorts()
   * @generated
   * @ordered
   */
  protected EList<Port> outputPorts;

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  protected EntityImpl() {
    super();
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass() {
    return TriqPackage.Literals.ENTITY;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  public EList<Port> getInputPorts() {
    if (inputPorts == null) {
      inputPorts = new EObjectContainmentEList<Port>(Port.class, this, TriqPackage.ENTITY__INPUT_PORTS);
    }
    return inputPorts;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  public EList<Port> getOutputPorts() {
    if (outputPorts == null) {
      outputPorts = new EObjectContainmentEList<Port>(Port.class, this, TriqPackage.ENTITY__OUTPUT_PORTS);
    }
    return outputPorts;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @generated NOT
   */
  public EList<Parameter> getParameters() {
    // TODO: improve this with a jdk8 collection filter thing
    EList<Parameter> result = new BasicEList<>();
    for (Attribute a : getAttributes()) {
      if (Parameter.class.isInstance(a)) {
        result.add((Parameter) a);
      }
    }

    return result;
  }

  @Override
  public NamedObj getChild(String name) {
    NamedObj child = super.getChild(name);
    if (child == null) {
      for (Port port : getInputPorts()) {
        if (name.equals(port.getName())) {
          child = port;
          break;
        }
      }
    }
    if (child == null) {
      for (Port port : getOutputPorts()) {
        if (name.equals(port.getName())) {
          child = port;
          break;
        }
      }
    }
    return child;
  }

  @Override
  public CompositeEntity getContainer() {
    return (CompositeEntity) eContainer();
  }

  @Override
  public void buildWrappedObject() {
    try {
      ptolemy.kernel.CompositeEntity container = (ptolemy.kernel.CompositeEntity) (getContainer() != null ? getContainer().getWrappedObject() : null);
      wrappedObject = PtolemyUtil._createEntity(container, getWrappedType(), null, getName());
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  @Override
  public void initializeFrom(ptolemy.kernel.util.NamedObj ptObject) {
    if (!isDeepComplete()) {
      if (!(ptObject instanceof ptolemy.kernel.Entity<?>)) {
        throw new IllegalArgumentException(ptObject + " should be an Entity");
      }
      super.initializeFrom(ptObject);
      ptolemy.kernel.Entity<?> e = (ptolemy.kernel.Entity<?>) ptObject;
      for (ptolemy.kernel.Port port : e.portList()) {
        if (port instanceof IOPort) {
          Port newPort = TriqFactory.eINSTANCE.createPort();
          newPort.setName(port.getName());
          newPort.setWrappedType(port.getClass().getName());
          newPort.setInput(((IOPort) port).isInput());
          newPort.setOutput(((IOPort) port).isOutput());
          newPort.setMultiPort(((IOPort) port).isMultiport());
          if (((IOPort) port).isInput()) {
            getInputPorts().add(newPort);
          }
          if (((IOPort) port).isOutput()) {
            getOutputPorts().add(newPort);
          }

          for(ptolemy.kernel.Relation ptRelation : (List<ptolemy.kernel.Relation>)port.linkedRelationList()) {
            for(Relation r : getContainer().getRelations()) {
              if(r.getName().equals(ptRelation.getName())) {
                r.link(newPort);
                break;
              }
            }
          }
        }
      }
      setDeepComplete(true);
    }
  }

  @Override
  public ptolemy.kernel.Entity<?> getWrappedObject() {
    return (ptolemy.kernel.Entity<?>) wrappedObject;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
    switch (featureID) {
      case TriqPackage.ENTITY__INPUT_PORTS:
        return ((InternalEList<?>)getInputPorts()).basicRemove(otherEnd, msgs);
      case TriqPackage.ENTITY__OUTPUT_PORTS:
        return ((InternalEList<?>)getOutputPorts()).basicRemove(otherEnd, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType) {
    switch (featureID) {
      case TriqPackage.ENTITY__INPUT_PORTS:
        return getInputPorts();
      case TriqPackage.ENTITY__OUTPUT_PORTS:
        return getOutputPorts();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public void eSet(int featureID, Object newValue) {
    switch (featureID) {
      case TriqPackage.ENTITY__INPUT_PORTS:
        getInputPorts().clear();
        getInputPorts().addAll((Collection<? extends Port>)newValue);
        return;
      case TriqPackage.ENTITY__OUTPUT_PORTS:
        getOutputPorts().clear();
        getOutputPorts().addAll((Collection<? extends Port>)newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eUnset(int featureID) {
    switch (featureID) {
      case TriqPackage.ENTITY__INPUT_PORTS:
        getInputPorts().clear();
        return;
      case TriqPackage.ENTITY__OUTPUT_PORTS:
        getOutputPorts().clear();
        return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID) {
    switch (featureID) {
      case TriqPackage.ENTITY__INPUT_PORTS:
        return inputPorts != null && !inputPorts.isEmpty();
      case TriqPackage.ENTITY__OUTPUT_PORTS:
        return outputPorts != null && !outputPorts.isEmpty();
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eInvoke(int operationID, EList<?> arguments) throws InvocationTargetException {
    switch (operationID) {
      case TriqPackage.ENTITY___GET_PARAMETERS:
        return getParameters();
    }
    return super.eInvoke(operationID, arguments);
  }

} // EntityImpl
