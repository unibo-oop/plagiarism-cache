package iuniversity.model.user;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

public interface Archive {
    
    /**
     * @return the set of students 
     */
    Set<Student> getStudents();
    /**
     * @return the set of teachers
     */
    Set<Teacher> getTeachers();
    /**
     * @return the admin
     */
    Admin getAdmin();
    /**
     * Add the student
     * @param student   The student to add
     */
    void addStudent(Student student);
    /**
     * Add the teacher
     * @param teacher   The teacher to add
     */
    void addTeacher(Teacher teacher);
    /**
     * The set of students becomes the one given in input
     * @param students   The new set of students  
     */
    void setStudents(Collection<Student> students);
    /**
     * The set of teachers becomes the one given in input
     * @param teachers  The new set of teachers
     */
    void setTeachers(Collection<Teacher> teachers);
    /**
     * Remove the student
     * @param student   The student to remove
     */
    void removeStudent(Student student);
    /**
     * Remove the teacher
     * @param teacher   The teacher to remove
     */
    void removeTeacher(Teacher teacher);
    /**
     * @return an int with the new student registration number
     */
    int getNewStudentRegistrationNumber();
    /**
     * @return an int with the new teacher registration number
     */
    int getNewTeacherRegistrationNumber();
    /**
     * @return an int with the new user id
     */
    int getNewUserId();
    /**
     * @param registrationNumber
     * @return the teacher with the specified registration number
     */
    Optional<Teacher> getTeacherByRegistrationNumber(int registrationNumber);
    /**
     * @param registrationNumber
     * @return the student with the specified registration number
     */
    Optional<Student> getStudentByRegistrationNumber(int registrationNumber);
}
