package iuniversity.model.didactics;

import java.io.Serializable;
import java.util.Collections;
import java.util.Set;

public class DegreeProgrammeImpl implements DegreeProgramme, Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String name;
    private DegreeType type;
    private Set<Course> courses;
    
    public DegreeProgrammeImpl(String name, DegreeType type, Set<Course> courses) {
        this.name = name;
        this.type = type;
        this.courses = courses;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public DegreeType getType() {
        return this.type;
    }

    @Override
    public String toString() {
        return name + "[" + type + "]";
    }

    @Override
    public Set<Course> getCourses() {
        return Collections.unmodifiableSet(courses);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((courses == null) ? 0 : courses.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DegreeProgrammeImpl other = (DegreeProgrammeImpl) obj;
        if (courses == null) {
            if (other.courses != null) {
                return false;
            }
        } else if (!courses.equals(other.courses)) {
            return false;
        }
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        if (type != other.type) {
            return false;
        }
        return true;
    }
    
    
}
