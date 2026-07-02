package iuniversity.test.model.didactics;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;

import iuniversity.model.didactics.Course;
import iuniversity.model.didactics.CourseImpl;
import iuniversity.model.didactics.DegreeProgramme;
import iuniversity.model.didactics.DegreeProgramme.DegreeType;
import iuniversity.test.SampleTestData;
import iuniversity.model.didactics.DegreeProgrammeImpl;
import iuniversity.model.didactics.DidacticsManager;
import iuniversity.model.didactics.DidacticsManagerImpl;

public class DidacticsManagerTest {

    private DidacticsManager didacticsManager = new DidacticsManagerImpl();
    private SampleTestData sampleData = new SampleTestData();

    @Test
    public void testGetDegreeProgrammes() {
        Set<Course> courses = new HashSet<>();
        Course algebra = sampleData.getAlgebra();
        Course analisiMat = sampleData.getAnalisiMatematica();
        courses.add(algebra);
        courses.add(analisiMat);
        DegreeProgramme ingCivile = new DegreeProgrammeImpl("Ingegneria Civile", DegreeType.BACHELOR,courses);
        assertEquals("Ingegneria Civile", ingCivile.getName());
        assertEquals(DegreeType.BACHELOR, ingCivile.getType());
        assertEquals(courses, ingCivile.getCourses());
    }
    
    @Test
    public void testGetCourseAndSetCourses() {
        Set<Course> courses = new HashSet<>();
        Course algebra = sampleData.getAlgebra();
        Course analisiMat = sampleData.getAnalisiMatematica();
        courses.add(algebra);
        courses.add(analisiMat);
        didacticsManager.setCourses(courses);
        assertEquals(courses, didacticsManager.getCourse());
    }
    
    @Test
    public void testSetDegreeProgrammes() {
        Set<Course> courses = new HashSet<>();
        Course algebra = sampleData.getAlgebra();
        Course analisiMat = sampleData.getAnalisiMatematica();
        courses.add(algebra);
        courses.add(analisiMat);
        DegreeProgramme ingCivile = new DegreeProgrammeImpl("Ingegneria Civile", DegreeType.BACHELOR,courses);
        DegreeProgramme ingInformatica = new DegreeProgrammeImpl("Ingegneria Informatica", DegreeType.MASTER,courses);
        Set<DegreeProgramme> degreeProgrammes = new HashSet<>();
        didacticsManager.setDegreeProgrammes(degreeProgrammes);
        assertEquals(degreeProgrammes,didacticsManager.getDegreeProgrammes());
    }
}
