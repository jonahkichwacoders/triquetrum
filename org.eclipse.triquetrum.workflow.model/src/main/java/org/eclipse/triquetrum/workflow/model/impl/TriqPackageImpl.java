/**
 * Copyright (c) 2016 iSencia Belgium NV.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Erwin De Ley - initial API and implementation and/or initial documentation
 */
package org.eclipse.triquetrum.workflow.model.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.triquetrum.workflow.model.Actor;
import org.eclipse.triquetrum.workflow.model.Annotation;
import org.eclipse.triquetrum.workflow.model.Attribute;
import org.eclipse.triquetrum.workflow.model.CompositeActor;
import org.eclipse.triquetrum.workflow.model.CompositeEntity;
import org.eclipse.triquetrum.workflow.model.Director;
import org.eclipse.triquetrum.workflow.model.Entity;
import org.eclipse.triquetrum.workflow.model.Location;
import org.eclipse.triquetrum.workflow.model.NamedObj;
import org.eclipse.triquetrum.workflow.model.Parameter;
import org.eclipse.triquetrum.workflow.model.Port;
import org.eclipse.triquetrum.workflow.model.Relation;
import org.eclipse.triquetrum.workflow.model.TriqFactory;
import org.eclipse.triquetrum.workflow.model.TriqPackage;
import org.eclipse.triquetrum.workflow.model.Vertex;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class TriqPackageImpl extends EPackageImpl implements TriqPackage {
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass namedObjEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass attributeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass annotationEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass parameterEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass directorEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass entityEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass compositeEntityEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass actorEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass compositeActorEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass portEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass relationEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass locationEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass vertexEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EDataType ptolemyNamedObjEDataType = null;

  /**
   * Creates an instance of the model <b>Package</b>, registered with
   * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
   * package URI value.
   * <p>Note: the correct way to create the package is via the static
   * factory method {@link #init init()}, which also performs
   * initialization of the package, or returns the registered package,
   * if one already exists.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.ecore.EPackage.Registry
   * @see org.eclipse.triquetrum.workflow.model.TriqPackage#eNS_URI
   * @see #init()
   * @generated
   */
  private TriqPackageImpl() {
    super(eNS_URI, TriqFactory.eINSTANCE);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private static boolean isInited = false;

  /**
   * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
   * 
   * <p>This method is used to initialize {@link TriqPackage#eINSTANCE} when that field is accessed.
   * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #eNS_URI
   * @see #createPackageContents()
   * @see #initializePackageContents()
   * @generated
   */
  public static TriqPackage init() {
    if (isInited) return (TriqPackage)EPackage.Registry.INSTANCE.getEPackage(TriqPackage.eNS_URI);

    // Obtain or create and register package
    TriqPackageImpl theTriqPackage = (TriqPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof TriqPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new TriqPackageImpl());

    isInited = true;

    // Create package meta-data objects
    theTriqPackage.createPackageContents();

    // Initialize created meta-data
    theTriqPackage.initializePackageContents();

    // Mark meta-data to indicate it can't be changed
    theTriqPackage.freeze();

  
    // Update the registry and return the package
    EPackage.Registry.INSTANCE.put(TriqPackage.eNS_URI, theTriqPackage);
    return theTriqPackage;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getNamedObj() {
    return namedObjEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getNamedObj_Name() {
    return (EAttribute)namedObjEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getNamedObj_Attributes() {
    return (EReference)namedObjEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getNamedObj_WrappedType() {
    return (EAttribute)namedObjEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getNamedObj_WrappedObject() {
    return (EAttribute)namedObjEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getNamedObj_DeepComplete() {
    return (EAttribute)namedObjEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getNamedObj_IconId() {
    return (EAttribute)namedObjEClass.getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EOperation getNamedObj__GetContainer() {
    return namedObjEClass.getEOperations().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EOperation getNamedObj__TopLevel() {
    return namedObjEClass.getEOperations().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EOperation getNamedObj__SetProperty__String_String_String() {
    return namedObjEClass.getEOperations().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EOperation getNamedObj__BuildWrappedObject() {
    return namedObjEClass.getEOperations().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EOperation getNamedObj__InitializeFrom__NamedObj() {
    return namedObjEClass.getEOperations().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EOperation getNamedObj__GetFullName() {
    return namedObjEClass.getEOperations().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAttribute() {
    return attributeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAnnotation() {
    return annotationEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getAnnotation_Text() {
    return (EAttribute)annotationEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getAnnotation_FontFamily() {
    return (EAttribute)annotationEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getAnnotation_TextSize() {
    return (EAttribute)annotationEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getAnnotation_Bold() {
    return (EAttribute)annotationEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getAnnotation_Italic() {
    return (EAttribute)annotationEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getAnnotation_Color() {
    return (EAttribute)annotationEClass.getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getParameter() {
    return parameterEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getParameter_Expression() {
    return (EAttribute)parameterEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getDirector() {
    return directorEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EOperation getDirector__GetParameters() {
    return directorEClass.getEOperations().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getEntity() {
    return entityEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getEntity_InputPorts() {
    return (EReference)entityEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getEntity_OutputPorts() {
    return (EReference)entityEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EOperation getEntity__GetParameters() {
    return entityEClass.getEOperations().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getCompositeEntity() {
    return compositeEntityEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getCompositeEntity_Entities() {
    return (EReference)compositeEntityEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getCompositeEntity_Relations() {
    return (EReference)compositeEntityEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getActor() {
    return actorEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getCompositeActor() {
    return compositeActorEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getCompositeActor_Director() {
    return (EReference)compositeActorEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getPort() {
    return portEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getPort_Input() {
    return (EAttribute)portEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getPort_Output() {
    return (EAttribute)portEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getPort_LinkedRelations() {
    return (EReference)portEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getPort_MultiPort() {
    return (EAttribute)portEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EOperation getPort__CanAcceptNewConnection() {
    return portEClass.getEOperations().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getRelation() {
    return relationEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getRelation_LinkedPorts() {
    return (EReference)relationEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getRelation_LinkedRelations() {
    return (EReference)relationEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getRelation_LinkingRelations() {
    return (EReference)relationEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EOperation getRelation__Link__NamedObj() {
    return relationEClass.getEOperations().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EOperation getRelation__Unlink__NamedObj() {
    return relationEClass.getEOperations().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EOperation getRelation__IsConnected() {
    return relationEClass.getEOperations().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EOperation getRelation__GetVertex() {
    return relationEClass.getEOperations().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getLocation() {
    return locationEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getLocation_Expression() {
    return (EAttribute)locationEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EOperation getLocation__GetLocation() {
    return locationEClass.getEOperations().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getVertex() {
    return vertexEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EDataType getPtolemyNamedObj() {
    return ptolemyNamedObjEDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public TriqFactory getTriqFactory() {
    return (TriqFactory)getEFactoryInstance();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private boolean isCreated = false;

  /**
   * Creates the meta-model objects for the package.  This method is
   * guarded to have no affect on any invocation but its first.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void createPackageContents() {
    if (isCreated) return;
    isCreated = true;

    // Create classes and their features
    namedObjEClass = createEClass(NAMED_OBJ);
    createEAttribute(namedObjEClass, NAMED_OBJ__NAME);
    createEReference(namedObjEClass, NAMED_OBJ__ATTRIBUTES);
    createEAttribute(namedObjEClass, NAMED_OBJ__WRAPPED_TYPE);
    createEAttribute(namedObjEClass, NAMED_OBJ__WRAPPED_OBJECT);
    createEAttribute(namedObjEClass, NAMED_OBJ__DEEP_COMPLETE);
    createEAttribute(namedObjEClass, NAMED_OBJ__ICON_ID);
    createEOperation(namedObjEClass, NAMED_OBJ___GET_CONTAINER);
    createEOperation(namedObjEClass, NAMED_OBJ___TOP_LEVEL);
    createEOperation(namedObjEClass, NAMED_OBJ___SET_PROPERTY__STRING_STRING_STRING);
    createEOperation(namedObjEClass, NAMED_OBJ___BUILD_WRAPPED_OBJECT);
    createEOperation(namedObjEClass, NAMED_OBJ___INITIALIZE_FROM__NAMEDOBJ);
    createEOperation(namedObjEClass, NAMED_OBJ___GET_FULL_NAME);

    attributeEClass = createEClass(ATTRIBUTE);

    annotationEClass = createEClass(ANNOTATION);
    createEAttribute(annotationEClass, ANNOTATION__TEXT);
    createEAttribute(annotationEClass, ANNOTATION__FONT_FAMILY);
    createEAttribute(annotationEClass, ANNOTATION__TEXT_SIZE);
    createEAttribute(annotationEClass, ANNOTATION__BOLD);
    createEAttribute(annotationEClass, ANNOTATION__ITALIC);
    createEAttribute(annotationEClass, ANNOTATION__COLOR);

    parameterEClass = createEClass(PARAMETER);
    createEAttribute(parameterEClass, PARAMETER__EXPRESSION);

    directorEClass = createEClass(DIRECTOR);
    createEOperation(directorEClass, DIRECTOR___GET_PARAMETERS);

    entityEClass = createEClass(ENTITY);
    createEReference(entityEClass, ENTITY__INPUT_PORTS);
    createEReference(entityEClass, ENTITY__OUTPUT_PORTS);
    createEOperation(entityEClass, ENTITY___GET_PARAMETERS);

    compositeEntityEClass = createEClass(COMPOSITE_ENTITY);
    createEReference(compositeEntityEClass, COMPOSITE_ENTITY__ENTITIES);
    createEReference(compositeEntityEClass, COMPOSITE_ENTITY__RELATIONS);

    actorEClass = createEClass(ACTOR);

    compositeActorEClass = createEClass(COMPOSITE_ACTOR);
    createEReference(compositeActorEClass, COMPOSITE_ACTOR__DIRECTOR);

    portEClass = createEClass(PORT);
    createEAttribute(portEClass, PORT__INPUT);
    createEAttribute(portEClass, PORT__OUTPUT);
    createEReference(portEClass, PORT__LINKED_RELATIONS);
    createEAttribute(portEClass, PORT__MULTI_PORT);
    createEOperation(portEClass, PORT___CAN_ACCEPT_NEW_CONNECTION);

    relationEClass = createEClass(RELATION);
    createEReference(relationEClass, RELATION__LINKED_PORTS);
    createEReference(relationEClass, RELATION__LINKED_RELATIONS);
    createEReference(relationEClass, RELATION__LINKING_RELATIONS);
    createEOperation(relationEClass, RELATION___LINK__NAMEDOBJ);
    createEOperation(relationEClass, RELATION___UNLINK__NAMEDOBJ);
    createEOperation(relationEClass, RELATION___IS_CONNECTED);
    createEOperation(relationEClass, RELATION___GET_VERTEX);

    locationEClass = createEClass(LOCATION);
    createEAttribute(locationEClass, LOCATION__EXPRESSION);
    createEOperation(locationEClass, LOCATION___GET_LOCATION);

    vertexEClass = createEClass(VERTEX);

    // Create data types
    ptolemyNamedObjEDataType = createEDataType(PTOLEMY_NAMED_OBJ);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private boolean isInitialized = false;

  /**
   * Complete the initialization of the package and its meta-model.  This
   * method is guarded to have no affect on any invocation but its first.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void initializePackageContents() {
    if (isInitialized) return;
    isInitialized = true;

    // Initialize package
    setName(eNAME);
    setNsPrefix(eNS_PREFIX);
    setNsURI(eNS_URI);

    // Create type parameters

    // Set bounds for type parameters

    // Add supertypes to classes
    attributeEClass.getESuperTypes().add(this.getNamedObj());
    annotationEClass.getESuperTypes().add(this.getAttribute());
    parameterEClass.getESuperTypes().add(this.getAttribute());
    directorEClass.getESuperTypes().add(this.getAttribute());
    entityEClass.getESuperTypes().add(this.getNamedObj());
    compositeEntityEClass.getESuperTypes().add(this.getEntity());
    actorEClass.getESuperTypes().add(this.getEntity());
    compositeActorEClass.getESuperTypes().add(this.getCompositeEntity());
    portEClass.getESuperTypes().add(this.getNamedObj());
    relationEClass.getESuperTypes().add(this.getNamedObj());
    locationEClass.getESuperTypes().add(this.getAttribute());
    vertexEClass.getESuperTypes().add(this.getLocation());

    // Initialize classes, features, and operations; add parameters
    initEClass(namedObjEClass, NamedObj.class, "NamedObj", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getNamedObj_Name(), ecorePackage.getEString(), "name", "new", 1, 1, NamedObj.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getNamedObj_Attributes(), this.getAttribute(), null, "attributes", null, 0, -1, NamedObj.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getNamedObj_WrappedType(), ecorePackage.getEString(), "wrappedType", null, 0, 1, NamedObj.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getNamedObj_WrappedObject(), this.getPtolemyNamedObj(), "wrappedObject", null, 0, 1, NamedObj.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getNamedObj_DeepComplete(), ecorePackage.getEBoolean(), "deepComplete", "false", 0, 1, NamedObj.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getNamedObj_IconId(), ecorePackage.getEString(), "iconId", null, 0, 1, NamedObj.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEOperation(getNamedObj__GetContainer(), this.getNamedObj(), "getContainer", 0, 1, IS_UNIQUE, IS_ORDERED);

    initEOperation(getNamedObj__TopLevel(), this.getNamedObj(), "topLevel", 0, 1, IS_UNIQUE, IS_ORDERED);

    EOperation op = initEOperation(getNamedObj__SetProperty__String_String_String(), null, "setProperty", 0, 1, IS_UNIQUE, IS_ORDERED);
    addEParameter(op, ecorePackage.getEString(), "name", 1, 1, IS_UNIQUE, IS_ORDERED);
    addEParameter(op, ecorePackage.getEString(), "value", 0, 1, IS_UNIQUE, IS_ORDERED);
    addEParameter(op, ecorePackage.getEString(), "className", 0, 1, IS_UNIQUE, IS_ORDERED);

    initEOperation(getNamedObj__BuildWrappedObject(), null, "buildWrappedObject", 0, 1, IS_UNIQUE, IS_ORDERED);

    op = initEOperation(getNamedObj__InitializeFrom__NamedObj(), null, "initializeFrom", 0, 1, IS_UNIQUE, IS_ORDERED);
    addEParameter(op, this.getPtolemyNamedObj(), "ptObject", 0, 1, IS_UNIQUE, IS_ORDERED);

    initEOperation(getNamedObj__GetFullName(), ecorePackage.getEString(), "getFullName", 0, 1, IS_UNIQUE, IS_ORDERED);

    initEClass(attributeEClass, Attribute.class, "Attribute", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(annotationEClass, Annotation.class, "Annotation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getAnnotation_Text(), ecorePackage.getEString(), "text", "Configure to edit text.", 0, 1, Annotation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getAnnotation_FontFamily(), ecorePackage.getEString(), "fontFamily", "Arial", 0, 1, Annotation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getAnnotation_TextSize(), ecorePackage.getEInt(), "textSize", "8", 0, 1, Annotation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getAnnotation_Bold(), ecorePackage.getEBoolean(), "bold", "false", 0, 1, Annotation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getAnnotation_Italic(), ecorePackage.getEBoolean(), "italic", "false", 0, 1, Annotation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getAnnotation_Color(), ecorePackage.getEString(), "color", "0,0,0,255", 1, 1, Annotation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(parameterEClass, Parameter.class, "Parameter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getParameter_Expression(), ecorePackage.getEString(), "expression", null, 0, 1, Parameter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(directorEClass, Director.class, "Director", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEOperation(getDirector__GetParameters(), this.getParameter(), "getParameters", 0, -1, IS_UNIQUE, IS_ORDERED);

    initEClass(entityEClass, Entity.class, "Entity", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getEntity_InputPorts(), this.getPort(), null, "inputPorts", null, 0, -1, Entity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getEntity_OutputPorts(), this.getPort(), null, "outputPorts", null, 0, -1, Entity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEOperation(getEntity__GetParameters(), this.getParameter(), "getParameters", 0, -1, IS_UNIQUE, IS_ORDERED);

    initEClass(compositeEntityEClass, CompositeEntity.class, "CompositeEntity", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getCompositeEntity_Entities(), this.getEntity(), null, "entities", null, 0, -1, CompositeEntity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getCompositeEntity_Relations(), this.getRelation(), null, "relations", null, 0, -1, CompositeEntity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(actorEClass, Actor.class, "Actor", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(compositeActorEClass, CompositeActor.class, "CompositeActor", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getCompositeActor_Director(), this.getDirector(), null, "director", null, 0, 1, CompositeActor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(portEClass, Port.class, "Port", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getPort_Input(), ecorePackage.getEBoolean(), "input", null, 0, 1, Port.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getPort_Output(), ecorePackage.getEBoolean(), "output", null, 0, 1, Port.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getPort_LinkedRelations(), this.getRelation(), this.getRelation_LinkedPorts(), "linkedRelations", null, 0, -1, Port.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getPort_MultiPort(), ecorePackage.getEBoolean(), "multiPort", "false", 0, 1, Port.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEOperation(getPort__CanAcceptNewConnection(), ecorePackage.getEBoolean(), "canAcceptNewConnection", 0, 1, IS_UNIQUE, IS_ORDERED);

    initEClass(relationEClass, Relation.class, "Relation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getRelation_LinkedPorts(), this.getPort(), this.getPort_LinkedRelations(), "linkedPorts", null, 0, -1, Relation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getRelation_LinkedRelations(), this.getRelation(), this.getRelation_LinkingRelations(), "linkedRelations", null, 0, -1, Relation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getRelation_LinkingRelations(), this.getRelation(), this.getRelation_LinkedRelations(), "linkingRelations", null, 0, -1, Relation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    op = initEOperation(getRelation__Link__NamedObj(), null, "link", 0, 1, IS_UNIQUE, IS_ORDERED);
    addEParameter(op, this.getNamedObj(), "linkedThing", 0, 1, IS_UNIQUE, IS_ORDERED);

    op = initEOperation(getRelation__Unlink__NamedObj(), null, "unlink", 0, 1, IS_UNIQUE, IS_ORDERED);
    addEParameter(op, this.getNamedObj(), "linkedThing", 0, 1, IS_UNIQUE, IS_ORDERED);

    initEOperation(getRelation__IsConnected(), ecorePackage.getEBoolean(), "isConnected", 0, 1, IS_UNIQUE, IS_ORDERED);

    initEOperation(getRelation__GetVertex(), this.getVertex(), "getVertex", 0, 1, IS_UNIQUE, IS_ORDERED);

    initEClass(locationEClass, Location.class, "Location", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getLocation_Expression(), ecorePackage.getEString(), "expression", null, 0, 1, Location.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEOperation(getLocation__GetLocation(), ecorePackage.getEDouble(), "getLocation", 0, -1, IS_UNIQUE, IS_ORDERED);

    initEClass(vertexEClass, Vertex.class, "Vertex", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    // Initialize data types
    initEDataType(ptolemyNamedObjEDataType, ptolemy.kernel.util.NamedObj.class, "PtolemyNamedObj", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);

    // Create resource
    createResource(eNS_URI);
  }

} //TriqPackageImpl
