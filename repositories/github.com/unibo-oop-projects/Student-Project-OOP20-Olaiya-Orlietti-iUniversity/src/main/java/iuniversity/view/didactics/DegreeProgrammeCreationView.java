package iuniversity.view.didactics;

import java.util.List;

import iuniversity.model.didactics.Course;
import iuniversity.model.didactics.DegreeProgramme.DegreeType;

public interface DegreeProgrammeCreationView {

    /**
     * Choose the type of degree programme
     * @param degreeTypes       
     */
    void setDegreeTypeChoices(List<DegreeType> degreeTypes);
    /**
     * Set the courses that can be chosen based on the type of degree type
     * @param course    
     */
    void setCourseChoices(List<Course> course);
    /**
     * Create the degree programme
     */
    void createDegreeProgramme();
}
