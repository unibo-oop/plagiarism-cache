package iuniversity.model.exams;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import iuniversity.model.didactics.Course;
import iuniversity.model.user.Student;

/**
 * 
 * Implementation of ExamCall interface.
 *
 */
public class ExamCallImpl implements ExamCall, Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private static final int DAYS_BEFORE_CALL = 1;

    private int maxStudents;
    private List<Student> registeredStudents = new ArrayList<>();
    private LocalDate callStart;
    private LocalDate registrationStart;
    private LocalDate registrationEnd;
    private ExamType examType;
    private Course course;
    private StudentRegistrationStrategy registrationStrategy;

    /**
     * Should not be called.
     */
    public ExamCallImpl() {
        /**
         * It is functional for serialization.
         */
    }

    private ExamCallImpl(final Course course, final LocalDate callStart, final ExamType examType, final int maxStudents,
            final StudentRegistrationStrategy registrationStrategy) {
        this.course = course;
        this.callStart = callStart;
        this.examType = examType;
        this.maxStudents = maxStudents;
        this.registeredStudents = new ArrayList<>();
        this.registrationStart = LocalDate.now();
        this.registrationEnd = callStart.minusDays(1);
        this.registrationStrategy = registrationStrategy;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Student> getRegisteredStudents() {
        return Set.copyOf(this.registeredStudents);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Student> getRegistrationList() {
        return Collections.unmodifiableList(this.registeredStudents);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LocalDate getStart() {
        return this.callStart;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ExamType getExamType() {
        return this.examType;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CallStatus getStatus() {
        final LocalDate now = LocalDate.now();
        return (now.isAfter(registrationStart) || now.isEqual(registrationStart))
                && (now.isBefore(registrationEnd) || now.isEqual(registrationEnd)) ? CallStatus.OPEN
                        : CallStatus.CLOSED;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Course getCourse() {
        return this.course;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int maxStudents() {
        return this.maxStudents;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isFull() {
        return registeredStudents.size() == this.maxStudents;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isOpen() {
        return this.getStatus() == CallStatus.OPEN;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean registerStudent(final Student student) {
        if (isFull() || !isOpen() || this.registeredStudents.contains(student)) {
            return false;
        }
        registrationStrategy.register(this.registeredStudents, student);
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean withdrawStudent(final Student student) {
        if (!isOpen()) {
            return false;
        }
        return this.registeredStudents.remove(student);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((callStart == null) ? 0 : callStart.hashCode());
        result = prime * result + ((course == null) ? 0 : course.hashCode());
        result = prime * result + ((examType == null) ? 0 : examType.hashCode());
        result = prime * result + maxStudents;
        result = prime * result + ((registeredStudents == null) ? 0 : registeredStudents.hashCode());
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ExamCallImpl other = (ExamCallImpl) obj;
        if (callStart == null) {
            if (other.callStart != null) {
                return false;
            }
        } else if (!callStart.equals(other.callStart)) {
            return false;
        }
        if (course == null) {
            if (other.course != null) {
                return false;
            }
        } else if (!course.equals(other.course)) {
            return false;
        }
        if (examType != other.examType) {
            return false;
        }
        if (maxStudents != other.maxStudents) {
            return false;
        }
        if (registeredStudents == null) {
            if (other.registeredStudents != null) {
                return false;
            }
        } else if (!registeredStudents.equals(other.registeredStudents)) {
            return false;
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return callStart.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + " | " + course + ", " + examType
                + ", aperto fino al " + registrationEnd.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    /**
     * This class is a builder for ExamCalls.
     *
     */
    public static class Builder implements ExamCallBuilder {

        private int maximumStudents;
        private LocalDate start;
        private ExamType type;
        private Course course;
        private StudentRegistrationStrategy registrationStrategy;
        private boolean built;

        public Builder() {
            registrationStrategy = new StudentRegistrationStrategyFactoryImpl().atTheEndOfList();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public ExamCallBuilder callStart(final LocalDate callStart) {
            this.start = callStart;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public ExamCallBuilder examType(final ExamType examType) {
            this.type = examType;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public ExamCallBuilder course(final Course course) {
            this.course = course;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public ExamCallBuilder maximumStudents(final int maximumStudents) {
            this.maximumStudents = maximumStudents;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public ExamCallBuilder registrationStrategy(final StudentRegistrationStrategy strategy) {
            this.registrationStrategy = strategy;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public ExamCall build() {
            if (built) {
                throw new IllegalStateException("Builder can be consumed once");
            } else if (Objects.isNull(course) || Objects.isNull(start) || Objects.isNull(type)) {
                throw new IllegalStateException("Can't build an exam call, arguments missing");
            } else if (start.isBefore(LocalDate.now().plusDays(DAYS_BEFORE_CALL))) {
                throw new IllegalStateException("ExamCall must be at least " + DAYS_BEFORE_CALL + " days after today");
            }
            built = true;
            return new ExamCallImpl(course, start, type, maximumStudents, registrationStrategy);
        }

    }

}
