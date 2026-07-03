package com.geoquiz.model.xmlunmarshal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Class implementing the concept of typical dish. It is used to unmarshal the TypicalDishes XML file
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "dish",
    "country"
})
@XmlRootElement(name = "TypicalDish")
public class TypicalDish {

    @XmlElement(name = "Dish", required = true)
    private String dish;
    @XmlElement(name = "Country", required = true)
    private String country;

    /**
     * Recupera il valore della proprietà dish.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getDish() {
        return dish;
    }

    /**
     * Imposta il valore della proprietà dish.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setDish(final String value) {
        this.dish = value;
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
