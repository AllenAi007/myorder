
package org.ai.order.model.xml;

import org.ai.order.model.xml.PermissionType;
import org.ai.order.model.xml.ResourceType;
import org.ai.order.model.xml.ResourcesType;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.ai.order.model package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Permission_QNAME = new QName("", "permission");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.ai.order.model
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link PermissionType }
     * 
     */
    public PermissionType createPermissionType() {
        return new PermissionType();
    }

    /**
     * Create an instance of {@link ResourcesType }
     * 
     */
    public ResourcesType createResourcesType() {
        return new ResourcesType();
    }

    /**
     * Create an instance of {@link ResourceType }
     * 
     */
    public ResourceType createResourceType() {
        return new ResourceType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PermissionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "permission")
    public JAXBElement<PermissionType> createPermission(PermissionType value) {
        return new JAXBElement<PermissionType>(_Permission_QNAME, PermissionType.class, null, value);
    }

}
