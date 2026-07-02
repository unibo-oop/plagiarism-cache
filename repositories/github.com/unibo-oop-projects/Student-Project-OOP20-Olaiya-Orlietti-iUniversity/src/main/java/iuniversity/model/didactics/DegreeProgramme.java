package iuniversity.model.didactics;

import java.util.Set;

public interface DegreeProgramme {

    /**
     * @return a string with the name of the degree programme
     */
    String getName();
    /**
     * @return the degree type 
     */
    DegreeType getType();
    
    Set<Course> getCourses();

    public enum DegreeType {
        /*
         * The duration is three years
         */
        BACHELOR, 
        /**
         * The duration is five years
         */
        MASTER
    }
}
