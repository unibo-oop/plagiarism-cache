package Model;

import java.io.Serializable;

/**
 * This class implements the courses, composed of name and type
 * 
 * @author Francesco Ceroni
 * 
 */

public class CoursesImpl implements Courses, Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String name;
    private Type type;

    public CoursesImpl(final String name, Type typology) {
        this.name = name;
        this.type = typology;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

}
