package iuniversity.controller.home;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import iuniversity.controller.AbstractController;
import iuniversity.model.exams.ExamCall;
import iuniversity.model.exams.ExamReport;
import iuniversity.model.exams.ExamResult;
import iuniversity.model.exams.ExamResult.ExamResultType;
import iuniversity.model.user.Student;
import iuniversity.view.home.StudentHomeView;

/**
 * The controller of the student home view. Displays informations and statistics.
 */
public class StudentHomeControllerImpl extends AbstractController implements StudentHomeController {

    /**
     * {@inheritDoc}
     */
    @Override
    public void displayStudentInfo() {
        checkStudent();
        final StudentHomeView view = (StudentHomeView) this.getView();
        view.setFirstName(getLoggedStudent().getName());
        view.setLastName(getLoggedStudent().getLastName());
        view.setRegistrationNumber(getLoggedStudent().getRegistrationNumber());
        view.setDegreeProgramme(getLoggedStudent().getDegreeProgramme().toString());
    }

    private Stream<ExamReport> getStudentExamReportStream(final Student student) {
        return this.getModel().getExamManager().getExamReports().stream().filter(e -> e.getStudent().equals(student));
    }

    private Stream<ExamResult> getStudentExamResultStream(final Student student) {
        return getStudentExamReportStream(student).map(r -> r.getResult());
    }

    private Stream<ExamResult> getStudentNumericExamResultStream(final Student student) {
        return getStudentExamResultStream(student).filter(r -> hasNumericResult(r));
    }

    private boolean hasNumericResult(final ExamResult result) {
        return result.getResult().isPresent();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void displayStudentStatistics() {
        checkStudent();
        final StudentHomeView view = (StudentHomeView) this.getView();
        view.setAcquiredCredits(getStudentExamReportStream(getLoggedStudent())
                .filter(e -> e.getResult().getResultType() == ExamResultType.SUCCEDED)
                .mapToInt(e -> e.getCourse().getCFU()).sum());
        view.setHighestExamResult(getStudentNumericExamResultStream(getLoggedStudent())
                .mapToInt(e -> e.getResult().get()).max().orElse(0));
        view.setLowestExamResult(getStudentNumericExamResultStream(getLoggedStudent())
                .mapToInt(e -> e.getResult().get()).min().orElse(0));
        view.setMeanExamResult(getStudentNumericExamResultStream(getLoggedStudent())
                .mapToDouble(e -> e.getResult().get()).average().orElse(0));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void withdrawStudent(final ExamCall examCall) {
        checkStudent();
        this.getModel().getExamManager().withdrawStudent(examCall, getLoggedStudent());
        this.saveExamCalls();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void displayOpenBookedExamCalls() {
        checkStudent();
        ((StudentHomeView) this.getView()).setOpenBookedExamCalls(this.getModel().getExamManager().getExamCalls()
                .stream().filter(e -> e.getRegisteredStudents().contains(getLoggedStudent()))
                .filter(e -> e.isOpen())
                .collect(Collectors.toSet()));
    }

}
