package com.geoquiz.model.xmlunmarshal;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * This class implements the concept of info on a country. It is used to unmarshal the CountriesInfo XML file.
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "country",
    "capitalCity",
    "currency",
    "flagRef",
    "difficulty"
})
@XmlRootElement(name = "CountryInfo")
public class CountryInfo {

    @XmlElement(name = "Country", required = true)
    private String country;
    @XmlElement(name = "CapitalCity", required = true)
    private String capitalCity;
    @XmlElement(name = "Currency", required = true)
    private String currency;
    @XmlElement(name = "FlagRef", required = true)
    private String flagRef;
    @XmlElement(name = "Difficulty", required = true)
    private String difficulty;

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

    /**
     * Recupera il valore della proprietà capitalCity.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getCapitalCity() {
        return capitalCity;
    }

    /**
     * Imposta il valore della proprietà capitalCity.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setCapitalCity(final String value) {
        this.capitalCity = value;
    }

    /**
     * Recupera il valore della proprietà currency.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * Imposta il valore della proprietà currency.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setCurrency(final String value) {
        this.currency = value;
    }

    /**
     * Recupera il valore della proprietà flagRef.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getFlagRef() {
        return flagRef;
    }

    /**
     * Imposta il valore della proprietà flagRef.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setFlagRef(final String value) {
        this.flagRef = value;
    }

    /**
     * Recupera il valore della proprietà difficulty.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getDifficulty() {
        return difficulty;
    }

    /**
     * Imposta il valore della proprietà difficulty.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setDifficulty(final String value) {
        this.difficulty = value;
    }

}
