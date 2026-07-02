package iuniversity.model.didactics;

import java.util.Collection;
import java.util.Set;

public interface DidacticsManager {
    
    /**
     * 
     * @return the set of degree programmes
     */
    Set<DegreeProgramme> getDegreeProgrammes();

    /**
     * 
     * @return the set of courses
     */
    Set<Course> getCourse();
    /**
     * @return the current academic year
     */
    AcademicYear currentAcademicYear();
    /**
     * Add the course
     * @param course    The course to add
     */
    void addCourse(Course course);
    /**
     * Add the degree programme
     * @param degreeProgramme   The degree programme to add
     */
    void addDegreeProgramme(DegreeProgramme degreeProgramme);
    /**
     * Remove the course
     * @param course    The course to remove
     */
    void removeCourse(Course course);
    /**
     * Remove the degree programme
     * @param degreeProgramme   The degree programme to remove
     */
    void removeDegreeProgramme(DegreeProgramme degreeProgramme);
    /**
     * The set of courses becomes the one given in input
     * @param courses   The new set of courses  
     */
    void setCourses(Collection<Course> courses);
    /**
     * The set of degree programmes becomes the one given in input
     * @param degreeProgramms   The new set of degree programmes
     */
    void setDegreeProgrammes(Collection<DegreeProgramme> degreeProgrammes);
}
