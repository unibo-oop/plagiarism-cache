package iuniversity.model.user;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class ArchiveImpl implements Archive {

    private Set<Student> students = new HashSet<>();
    private Set<Teacher> teachers = new HashSet<>();
    private Admin admin = new AdminImpl();
    

    @Override
    public Set<Student> getStudents() {
        return Collections.unmodifiableSet(students);
    }

    @Override
    public Set<Teacher> getTeachers() {
        return Collections.unmodifiableSet(teachers);
    }

    @Override
    public Admin getAdmin() {
        return this.admin;
    }

    @Override
    public void addStudent(Student student) {
        this.students.add(student);
    }

    @Override
    public void addTeacher(Teacher teacher) {
        this.teachers.add(teacher);
    }
    
    @Override
    public void setStudents(Collection<Student> students) {
        this.students = new HashSet<>(students);
    }
    
    @Override
    public void setTeachers(Collection<Teacher> teachers) {
        this.teachers = new HashSet<>(teachers);
    }

    @Override
    public void removeStudent(Student student) {
        this.students.remove(student);
    }

    @Override
    public void removeTeacher(Teacher teacher) {
        this.teachers.remove(teacher);
    }

    @Override
    public int getNewStudentRegistrationNumber() {
        int registrationNumber = 0;
        for (Student student : this.students) {
            if (registrationNumber < student.getRegistrationNumber()) {
                registrationNumber = student.getRegistrationNumber();
            }
        }
        return ++registrationNumber;
    }

    @Override
    public int getNewTeacherRegistrationNumber() {
        int registrationNumber = 0;
        for (Teacher teacher : this.teachers) {
            if (registrationNumber < teacher.getRegistrationNumber()) {
                registrationNumber = teacher.getRegistrationNumber();
            }
        }
        return ++registrationNumber;
    }

    @Override
    public int getNewUserId() {
        int id = 0;
        Set<User> users = new HashSet<User>();
        users.addAll(students);
        users.addAll(teachers);
        for (User user: users) {
            if (id < user.getId()) {
                id = user.getId();
            }
        }
        return ++id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Teacher> getTeacherByRegistrationNumber(final int registrationNumber) {
        return teachers.stream().filter(t -> t.getRegistrationNumber() == registrationNumber).findFirst();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Student> getStudentByRegistrationNumber(final int registrationNumber) {
        return students.stream().filter(s -> s.getRegistrationNumber() == registrationNumber).findFirst();
    }
}
