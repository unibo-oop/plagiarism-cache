//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.8-b130911.1802 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2017.03.19 alle 03:22:23 PM CET 
//

package model.domain;

import javax.xml.bind.annotation.XmlRegistry;

/**
 * This object contains factory methods for each Java content interface and Java
 * element interface generated in the model.domain package.
 * <p>
 * An ObjectFactory allows you to programatically construct new instances of the
 * Java representation for XML content. The Java representation of XML content
 * can consist of schema derived interfaces and classes representing the binding
 * of schema type definitions, element declarations and model groups. Factory
 * methods for each of these are provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

	/**
	 * Create a new ObjectFactory that can be used to create new instances of
	 * schema derived classes for package: model.domain
	 * 
	 */
	public ObjectFactory() {
	}

	/**
	 * Create an instance of {@link Reservations }
	 * 
	 */
	public Reservations createReservations() {
		return new Reservations();
	}

	/**
	 * Create an instance of {@link Reservation }
	 * 
	 */
	public Reservation createReservation() {
		return new Reservation();
	}

}
