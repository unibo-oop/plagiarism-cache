package iuniversity.model.didactics;

import java.io.Serializable;

public class CourseImpl implements Course, Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String name;
    private int CFU;

    public CourseImpl(String name, int CFU) {
        super();
        this.name = name;
        this.CFU = CFU;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getCFU() {
        return this.CFU;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + CFU;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CourseImpl other = (CourseImpl) obj;
        if (CFU != other.CFU)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return name + " (" + CFU + " cfu)";
    }

}
