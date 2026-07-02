package iuniversity.model.exams;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import iuniversity.model.didactics.Course;
import iuniversity.model.user.Student;

/**
 * 
 * ExamCall interface. Students can be registered or withdrawn
 *
 */
public interface ExamCall {

    /**
     * 
     * Status of a {@link ExamCall}.
     *
     */
    enum CallStatus {

        /*
         * Exam call is created but not visible to students.
         */
        // CREATED,
        /*
         * Exam call is created but and visible to students. Students can't register
         * yet.
         */
        // PUBLISHED,
        /**
         * Exam call is published and students can register to the call.
         */
        OPEN,
        /**
         * Exam call is published but students can't register no more.
         */
        CLOSED;
    }

    enum ExamType {
        ORAL("Orale"), WRITTEN("Scritto"), PRACTICAL("Pratico");

        private final String label;

        ExamType(final String label) {
            this.label = label;
        }

        @Override
        public String toString() {
            return this.label;
        }

    }

    /**
     * 
     * @return the ExamCall course
     */
    Course getCourse();

    /**
     * 
     * @return the set of student's that registered to the call
     */
    Set<Student> getRegisteredStudents();

    /**
     * 
     * @return the list of student based on the registration strategy
     */
    List<Student> getRegistrationList();

    /**
     * 
     * @return the start Date and time of the call
     */
    LocalDate getStart();

    /**
     * 
     * @return the {@link ExamType} of the call
     */
    ExamType getExamType();

    /**
     * 
     * @return the status of the call
     */
    CallStatus getStatus();

    /**
     * 
     * @return an optional with is filled with the maximum number of student that
     *         register to the call
     */
    int maxStudents();

    /**
     * Add student to the Exam call list.
     * 
     * @param student the student to add to the exam call list
     * @return true if the student was registered
     */
    boolean registerStudent(Student student);

    /**
     * 
     * @param student the student to remove from the exam call list
     * @return true if the student was already in list
     */
    boolean withdrawStudent(Student student);

    /**
     * 
     * @return true if the exam call is open which means that student can register or withdraw
     */
    boolean isOpen();

    /**
     * 
     * @return true if the maximum number of students is reached
     */
    boolean isFull();

}
