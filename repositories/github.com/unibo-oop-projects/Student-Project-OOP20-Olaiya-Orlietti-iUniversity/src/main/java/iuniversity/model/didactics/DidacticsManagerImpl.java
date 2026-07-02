package iuniversity.model.didactics;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class DidacticsManagerImpl implements DidacticsManager {

    private Set<DegreeProgramme> degreeProgrammes = new HashSet<>();
    private Set<Course> courses = new HashSet<>();
    private AcademicYear academicYear;

    @Override
    public Set<DegreeProgramme> getDegreeProgrammes() {
        return Collections.unmodifiableSet(degreeProgrammes);
    }

    @Override
    public Set<Course> getCourse() {
        return Collections.unmodifiableSet(courses);
    }
    
    @Override
    public AcademicYear currentAcademicYear() {
        return this.academicYear;
    }

    @Override
    public void addCourse(Course course) {
        this.courses.add(course);
    }

    @Override
    public void addDegreeProgramme(DegreeProgramme degreeProgramme) {
        this.degreeProgrammes.add(degreeProgramme);
    }

    @Override
    public void removeCourse(Course course) {
        this.courses.remove(course);
    }

    @Override
    public void removeDegreeProgramme(DegreeProgramme degreeProgramme) {
        this.degreeProgrammes.remove(degreeProgramme);
    }

    @Override
    public void setCourses(Collection<Course> courses) {
        this.courses = new HashSet<>(courses);
    }

    @Override
    public void setDegreeProgrammes(Collection<DegreeProgramme> degreeProgrammes) {
        this.degreeProgrammes = new HashSet<>(degreeProgrammes);
    }

}
