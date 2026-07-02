package iuniversity.controller.didactics;

import java.util.Set;

import iuniversity.model.didactics.Course;
import iuniversity.model.didactics.DegreeProgramme.DegreeType;

public interface DegreeProgrammeCreationController {
    
    /**
     * Create a new degree programme
     * @param name      The name for the degree programme to create
     * @param type      The type of degree programme
     * @param course    The set of courses of the new degree course
     */
    void createDegreeProgramme(String name, DegreeType type, Set<Course> course);
    /**
     * Initializes the view with degree programmes
     */
    void initializeChoices();
}
