package com.geoquiz.model.xmlunmarshal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * This class implements the concept of info on a monument. It is used to unmarshal the Monuments{Easy, Medium, Difficult} XML file
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "monument",
    "country"
})
@XmlRootElement(name = "MonumentInfo")
public class MonumentInfo {

    @XmlElement(name = "Monument", required = true)
    private String monument;
    @XmlElement(name = "Country", required = true)
    private String country;

    /**
     * Recupera il valore della proprietà monument.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getMonument() {
        return monument;
    }

    /**
     * Imposta il valore della proprietà monument.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setMonument(final String value) {
        this.monument = value;
    }

    /**
     * Recupera il valore della proprietà country.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getCountry() {
        return country;
    }

    /**
     * Imposta il valore della proprietà country.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setCountry(final String value) {
        this.country = value;
    }

}
