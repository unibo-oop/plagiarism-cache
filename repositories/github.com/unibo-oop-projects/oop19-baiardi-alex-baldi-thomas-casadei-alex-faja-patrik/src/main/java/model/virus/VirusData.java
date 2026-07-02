package model.virus;


import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlAccessType;

/**
 *
 */
@XmlRootElement (name = "virus")
@XmlAccessorType (XmlAccessType.FIELD)
public class VirusData {

    @XmlAttribute
    private String name;
    @XmlElement
    private int minIncubationPeriod;
    @XmlElement
    private int maxIncubationPeriod;
    @XmlElement
    private double infectivity;
    @XmlElement
    private double mortality;
    @XmlElement
    private int minRecoveryPeriod;
    @XmlElement
    private int maxRecoveryPeriod;

    /**
     * Gets the virus' name.
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the virus' minimum incubation period.
     * @return the minIncubationPeriod
     */
    public int getMinIncubationPeriod() {
        return minIncubationPeriod;
    }

    /**
     * Gets the virus' maximum incubation period.
     * @return the maxIncubationPeriod
     */
    public int getMaxIncubationPeriod() {
        return maxIncubationPeriod;
    }

    /**
     * Gets the virus' infectivity rate.
     * @return the infectivity rate
     */
    public double getInfectivity() {
        return infectivity;
    }

    /**
     * Gets the virus' mortality rate.
     * @return the mortality rate
     */
    public double getMortality() {
        return mortality;
    }

    /**
     * Gets the virus' minimum recovery period.
     * @return the minRecoveryPeriod
     */
    public int getMinRecoveryPeriod() {
        return minRecoveryPeriod;
    }

    /**
     * Gets the virus' maximum incubation period.
     * @return the maxRecoveryPeriod
     */
    public int getMaxRecoveryPeriod() {
        return maxRecoveryPeriod;
    }

    /**
     * sets the virus' name.
     * @param name the name to set
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Sets the virus' minimum incubation period.
     * @param minIncubationPeriod the minIncubationPeriod to set
     */
    public void setMinIncubationPeriod(final int minIncubationPeriod) {
        this.minIncubationPeriod = minIncubationPeriod;
    }

    /**
     * Sets the virus' maximum incubation period.
     * @param maxIncubationPeriod the maxIncubationPeriod to set
     */
    public void setMaxIncubationPeriod(final int maxIncubationPeriod) {
        this.maxIncubationPeriod = maxIncubationPeriod;
    }

    /**
     * Sets the virus' infectivity rate.
     * @param infectivity the infectivity to set
     */
    public void setInfectivity(final double infectivity) {
        this.infectivity = infectivity;
    }

    /**
     * Sets the virus' mortality rate.
     * @param mortality the mortality to set
     */
    public void setMortality(final double mortality) {
        this.mortality = mortality;
    }

    /**
     * Sets the virus' minimum recovery period.
     * @param minRecoveryPeriod the minRecoveryPeriod to set
     */
    public void setMinRecoveryPeriod(final int minRecoveryPeriod) {
        this.minRecoveryPeriod = minRecoveryPeriod;
    }

    /**
     * Sets the virus' maximum recovery period.
     * @param maxRecoveryPeriod the maxRecoveryPeriod to set
     */
    public void setMaxRecoveryPeriod(final int maxRecoveryPeriod) {
        this.maxRecoveryPeriod = maxRecoveryPeriod;
    }

}
