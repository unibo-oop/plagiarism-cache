package iuniversity.model.exams;

import java.io.Serializable;

import com.google.common.base.Optional;

public interface ExamResult extends Serializable {

    /**
     * The result type of an {@link ExamResult}.
     *
     */
    enum ExamResultType {
        /**
         * The exam is passed.
         */
        SUCCEDED("Superato"),
        /**
         * The student did not complete the exam.
         */
        WITHDRAWN("Ritirato"),
        /**
         * The student failed in succeeding the exam.
         */
        FAILED("Respinto"),
        /**
         * The student passed the exam but refused the Evaluation given.
         */
        DECLINED("Rifiutato");

        private final String label;

        ExamResultType(final String label) {
            this.label = label;
        }

        @Override
        public String toString() {
            return this.label;
        }
    }

    /**
     * 
     * @return The type of evaluation given to the student
     */
    ExamResultType getResultType();

    /**
     * 
     * @return Whether the student passed the exam with honors
     */
    boolean cumLaude();

    /**
     * 
     * @return if provided, the numeric result of the exam
     */
    Optional<Integer> getResult();
}
