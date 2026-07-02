//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.8-b130911.1802 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2017.03.19 alle 03:22:23 PM CET 
//


package model.domain;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java per Reservation complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="Reservation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" />
 *       &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="email" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="phone" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="table" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" />
 *       &lt;attribute name="numberOfPersons" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" />
 *       &lt;attribute name="date" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="menu" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Reservation")
public class Reservation {

    @XmlAttribute(name = "id")
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger id;
    @XmlAttribute(name = "name")
    protected String name;
    @XmlAttribute(name = "email")
    protected String email;
    @XmlAttribute(name = "phone")
    protected String phone;
    @XmlAttribute(name = "table")
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger table;
    @XmlAttribute(name = "numberOfPersons")
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger numberOfPersons;
    @XmlAttribute(name = "date")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar date;
    @XmlAttribute(name = "menu")
    protected String menu;

    /**
     * Recupera il valore della proprietà id.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getId() {
        return id;
    }

    /**
     * Imposta il valore della proprietà id.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setId(BigInteger value) {
        this.id = value;
    }

    /**
     * Recupera il valore della proprietà name.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Imposta il valore della proprietà name.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Recupera il valore della proprietà email.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail() {
        return email;
    }

    /**
     * Imposta il valore della proprietà email.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail(String value) {
        this.email = value;
    }

    /**
     * Recupera il valore della proprietà phone.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Imposta il valore della proprietà phone.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPhone(String value) {
        this.phone = value;
    }

    /**
     * Recupera il valore della proprietà table.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getTable() {
        return table;
    }

    /**
     * Imposta il valore della proprietà table.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setTable(BigInteger value) {
        this.table = value;
    }

    /**
     * Recupera il valore della proprietà numberOfPersons.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getNumberOfPersons() {
        return numberOfPersons;
    }

    /**
     * Imposta il valore della proprietà numberOfPersons.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setNumberOfPersons(BigInteger value) {
        this.numberOfPersons = value;
    }

    /**
     * Recupera il valore della proprietà date.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDate() {
        return date;
    }

    /**
     * Imposta il valore della proprietà date.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDate(XMLGregorianCalendar value) {
        this.date = value;
    }

    /**
     * Recupera il valore della proprietà menu.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMenu() {
        return menu;
    }

    /**
     * Imposta il valore della proprietà menu.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMenu(String value) {
        this.menu = value;
	}

}
