package iuniversity.controller.home;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import iuniversity.controller.AbstractController;
import iuniversity.model.exams.ExamCall;
import iuniversity.model.exams.ExamReport;
import iuniversity.model.exams.ExamResult.ExamResultType;
import iuniversity.model.user.Teacher;
import iuniversity.view.home.TeacherHomeView;

/**
 * The controller of the teacher home view. Displays informations and statistics.
 */
public class TeacherHomeControllerImpl extends AbstractController implements TeacherHomeController {

    /**
     * {@inheritDoc}
     */
    @Override
    public void displayTeacherInfo() {
        checkTeacher();
        final Teacher teacher = getLoggedTeacher();
        final TeacherHomeView view = (TeacherHomeView) this.getView();
        view.setFirstName(teacher.getName());
        view.setLastName(teacher.getLastName());
        view.setRegistrationNumber(teacher.getRegistrationNumber());
    }

    private Stream<ExamReport> getTeacherExamReportStream() {
        return this.getModel().getExamManager().getExamReports().stream()
                .filter(e -> getLoggedTeacher().getCourses().contains(e.getCourse()));
    }

    private Stream<ExamReport> getTeacherExamReportStreamByResultType(final ExamResultType examResultType) {
        return getTeacherExamReportStream().filter(e -> e.getResult().getResultType() == examResultType);
    }

    private Stream<ExamCall> getTeacherExamCallsStream() {
        return this.getModel().getExamManager().getExamCalls().stream()
                .filter(e -> getLoggedTeacher().getCourses().contains(e.getCourse()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void displayTeacherStatistics() {
        checkTeacher();
        final TeacherHomeView view = (TeacherHomeView) this.getView();
        view.setPromotedStudents((int) getTeacherExamReportStreamByResultType(ExamResultType.SUCCEDED).count());
        view.setFailedStudents((int) getTeacherExamReportStreamByResultType(ExamResultType.FAILED).count());
        view.setWithdrawnStudents((int) getTeacherExamReportStreamByResultType(ExamResultType.WITHDRAWN).count());
        view.setCumLaudeStudents((int) getTeacherExamReportStreamByResultType(ExamResultType.SUCCEDED)
                .filter(e -> e.getResult().cumLaude()).count());
        view.setMeanResults(getTeacherExamReportStream().map(e -> e.getResult().getResult()).filter(r -> r.isPresent())
                .mapToDouble(r -> r.get()).average().orElse(0));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeExamCall(final ExamCall examCall) {
        this.getModel().getExamManager().removeExamCall(examCall);
        this.saveExamCalls();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void displayOpenExamCalls() {
        checkTeacher();
        ((TeacherHomeView) this.getView())
                .setOpenExamCalls(getTeacherExamCallsStream().filter(e -> e.isOpen()).collect(Collectors.toSet()));
    }

}
