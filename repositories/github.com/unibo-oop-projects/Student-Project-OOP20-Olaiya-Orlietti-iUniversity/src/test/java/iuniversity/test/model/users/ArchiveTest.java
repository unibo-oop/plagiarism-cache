package iuniversity.test.model.users;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;

import iuniversity.model.didactics.Course;
import iuniversity.model.didactics.DegreeProgramme;
import iuniversity.model.user.Archive;
import iuniversity.model.user.ArchiveImpl;
import iuniversity.model.user.Student;
import iuniversity.model.user.StudentImpl;
import iuniversity.model.user.Teacher;
import iuniversity.model.user.TeacherImpl;
import iuniversity.model.user.User.Gender;
import iuniversity.test.SampleTestData;

public class ArchiveTest {

    private Archive archive = new ArchiveImpl();
    private SampleTestData sampleData = new SampleTestData();
    
    @Test
    public void testGetStudents() {
        Student marioRossi = new StudentImpl.StudentBuilder("Mario","Rossi",0,0)
                                            .username("stu.mario.rossi")
                                            .dateOfBirth(LocalDate.of(1990, 8, 8))
                                            .address("Via Don Minzoni")
                                            .gender(Gender.MALE)
                                            .degreeProgramme(sampleData.getIngegneria())
                                            .build();
        assertEquals("Mario", marioRossi.getName());
        assertEquals("Rossi", marioRossi.getLastName());
        assertEquals(0, marioRossi.getId());
        assertEquals(0, marioRossi.getRegistrationNumber());
        assertEquals("stu.mario.rossi", marioRossi.getUsername());
        assertEquals(sampleData.getIngegneria(), marioRossi.getDegreeProgramme());
    }
    
    @Test
    public void testGetTeachers() {
        Set<Course> courses = new HashSet<>();
        Course algebra = sampleData.getAlgebra();
        Course analisiMat = sampleData.getAnalisiMatematica();
        courses.add(algebra);
        courses.add(analisiMat);
        Teacher claudioBravo = new TeacherImpl.TeacherBuilder("Claudio","Bravo",0,0)
                                            .username("doc.claudio.bravo")
                                            .dateOfBirth(LocalDate.of(1990, 8, 8))
                                            .address("Via Madonna della Rosa")
                                            .gender(Gender.MALE)
                                            .courses(courses)
                                            .build();
        assertEquals("Claudio", claudioBravo.getName());
        assertEquals("Bravo", claudioBravo.getLastName());
        assertEquals(0, claudioBravo.getId());
        assertEquals(0, claudioBravo.getRegistrationNumber());
        assertEquals("doc.claudio.bravo", claudioBravo.getUsername());
        assertEquals(courses, claudioBravo.getCourses());
    }
    
    @Test
    public void testGetNewStudentRegistrationNumber() {
        Student marioRossi = new StudentImpl.StudentBuilder("Mario","Rossi",0,archive.getNewStudentRegistrationNumber())
                .username("stu.mario.rossi")
                .dateOfBirth(LocalDate.of(1990, 8, 8))
                .address("Via Don Minzoni")
                .gender(Gender.MALE)
                .degreeProgramme(sampleData.getIngegneria())
                .build();
        archive.addStudent(marioRossi);
        Student marioGrossi = new StudentImpl.StudentBuilder("Mario","Grossi",1,archive.getNewStudentRegistrationNumber())
                .username("stu.mario.grossi")
                .dateOfBirth(LocalDate.of(1990, 8, 8))
                .address("Via Madonna della Rosa")
                .gender(Gender.MALE)
                .degreeProgramme(sampleData.getIngegneria())
                .build();
        archive.addStudent(marioGrossi);
        assertEquals(1,marioRossi.getRegistrationNumber());
        assertEquals(2,marioGrossi.getRegistrationNumber());
    }
    
    @Test
    public void testGetNewTeacherRegistrationNumber() {
        Set<Course> courses = new HashSet<>();
        Course algebra = sampleData.getAlgebra();
        Course analisiMat = sampleData.getAnalisiMatematica();
        courses.add(algebra);
        courses.add(analisiMat);
        Teacher claudioBravo = new TeacherImpl.TeacherBuilder("Claudio","Bravo",0,archive.getNewTeacherRegistrationNumber())
                .username("doc.claudio.bravo")
                .dateOfBirth(LocalDate.of(1990, 8, 8))
                .address("Via Madonna della Rosa")
                .gender(Gender.MALE)
                .courses(courses)
                .build();
        archive.addTeacher(claudioBravo);
        Teacher francescoFrancoletti = new TeacherImpl.TeacherBuilder("Francesco","Francoletti",0,archive.getNewTeacherRegistrationNumber())
                .username("doc.claudio.bravo")
                .dateOfBirth(LocalDate.of(1990, 8, 8))
                .address("Via Madonna della Rosa")
                .gender(Gender.MALE)
                .courses(courses)
                .build();
        archive.addTeacher(francescoFrancoletti);
        assertEquals(1,claudioBravo.getRegistrationNumber());
        assertEquals(2,francescoFrancoletti.getRegistrationNumber());
    }
    
    @Test
    public void testGetStudentByRegistrationNumber() {
        Set<Course> courses = new HashSet<>();
        Course algebra = sampleData.getAlgebra();
        Course analisiMat = sampleData.getAnalisiMatematica();
        courses.add(algebra);
        courses.add(analisiMat);
        Student marioRossi = new StudentImpl.StudentBuilder("Mario","Rossi",0,archive.getNewStudentRegistrationNumber())
                .username("stu.mario.rossi")
                .dateOfBirth(LocalDate.of(1990, 8, 8))
                .address("Via Don Minzoni")
                .gender(Gender.MALE)
                .degreeProgramme(sampleData.getIngegneria())
                .build();
        archive.addStudent(marioRossi);
        Student marioGrossi = new StudentImpl.StudentBuilder("Mario","Grossi",1,archive.getNewStudentRegistrationNumber())
                .username("stu.mario.grossi")
                .dateOfBirth(LocalDate.of(1990, 8, 8))
                .address("Via Madonna della Rosa")
                .gender(Gender.MALE)
                .degreeProgramme(sampleData.getIngegneria())
                .build();
        archive.addStudent(marioGrossi);
        assertEquals(Optional.of(marioRossi),archive.getStudentByRegistrationNumber(marioRossi.getRegistrationNumber()));
        assertEquals(Optional.of(marioGrossi),archive.getStudentByRegistrationNumber(marioGrossi.getRegistrationNumber()));
    }
    
    @Test
    public void testGetTeacherByRegistrationNumber() {
        Set<Course> courses = new HashSet<>();
        Course algebra = sampleData.getAlgebra();
        Course analisiMat = sampleData.getAnalisiMatematica();
        courses.add(algebra);
        courses.add(analisiMat);
        Teacher claudioBravo = new TeacherImpl.TeacherBuilder("Claudio","Bravo",0,archive.getNewTeacherRegistrationNumber())
                .username("doc.claudio.bravo")
                .dateOfBirth(LocalDate.of(1990, 8, 8))
                .address("Via Madonna della Rosa")
                .gender(Gender.MALE)
                .courses(courses)
                .build();
        archive.addTeacher(claudioBravo);
        Teacher francescoFrancoletti = new TeacherImpl.TeacherBuilder("Francesco","Francoletti",0,archive.getNewTeacherRegistrationNumber())
                .username("doc.claudio.bravo")
                .dateOfBirth(LocalDate.of(1990, 8, 8))
                .address("Via Madonna della Rosa")
                .gender(Gender.MALE)
                .courses(courses)
                .build();
        archive.addTeacher(francescoFrancoletti);
        assertEquals(Optional.of(claudioBravo),archive.getTeacherByRegistrationNumber(claudioBravo.getRegistrationNumber()));
        assertEquals(Optional.of(francescoFrancoletti),archive.getTeacherByRegistrationNumber(francescoFrancoletti.getRegistrationNumber()));
    }
}
