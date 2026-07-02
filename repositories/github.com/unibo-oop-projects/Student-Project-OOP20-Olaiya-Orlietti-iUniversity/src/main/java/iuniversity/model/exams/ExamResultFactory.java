package iuniversity.model.exams;

public interface ExamResultFactory {

    /**
     * @return an ExamResult with maximum result and honors
     */
    ExamResult succeededCumLaude();
    /**
     * @param result the result of the exam
     * @return a succeeded Exam Result with the specified result
     * @throws IllegalArgumentException if result is below sufficiency 
     */
    ExamResult succeded(int result);
    /**
     * @param result the result of the exam
     * @return a failed Exam Result with the specified result
     * @throws IllegalArgumentException if result is greater than sufficiency 
     */
    ExamResult failed(int result);
    /**
     * @return a withdrawn Exam Result 
     */
    ExamResult withdrawn();
    /**
     * @param result the result of the exam
     * @return a declined Exam Result
     * @throws IllegalArgumentException if result is greater than sufficiency
     */
    ExamResult declined(int result);

}
