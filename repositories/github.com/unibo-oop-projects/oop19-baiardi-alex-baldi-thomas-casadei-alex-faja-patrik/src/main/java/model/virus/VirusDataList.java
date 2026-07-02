package model.virus;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@XmlRootElement(name = "virusList")
@XmlAccessorType (XmlAccessType.FIELD)
public class VirusDataList {

    @XmlElement(name = "virus")
    private List<VirusData> virus = new ArrayList<>();

    /**
     * @return List<VirusData> lists
     */
    public List<VirusData> getVirus() {
        return this.virus;
    }

    /**
     * @param virus VirusData list
     */

    public void setVirus(final List<VirusData> virus) {
        this.virus = virus;
    }

}
