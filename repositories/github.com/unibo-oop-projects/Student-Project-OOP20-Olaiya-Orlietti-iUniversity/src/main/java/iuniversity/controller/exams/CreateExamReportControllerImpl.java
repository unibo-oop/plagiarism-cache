package iuniversity.controller.exams;

import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;

import iuniversity.controller.AbstractController;
import iuniversity.model.didactics.Course;
import iuniversity.model.exams.ExamCall;
import iuniversity.model.exams.ExamReport;
import iuniversity.model.exams.ExamReportImpl;
import iuniversity.model.exams.ExamResult.ExamResultType;
import iuniversity.model.user.Student;
import iuniversity.model.user.Teacher;
import iuniversity.view.exams.CreateExamReportView;

/**
 * Manages the creation of an exam report.
 */
public class CreateExamReportControllerImpl extends AbstractController implements CreateExamReportController {

    private boolean isTeachedByTeacher(final Course course, final Teacher teacher) {
        return teacher.getCourses().contains(course);
    }

    private boolean hasRegisteredStudents(final ExamCall examCall) {
        return !examCall.getRegisteredStudents().isEmpty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void displayExamCallChoices() {
        checkTeacher();
        ((CreateExamReportView) this.getView()).setExamCallChoices(this.getModel().getExamManager().getExamCalls()
                .stream().filter(e -> isTeachedByTeacher(e.getCourse(), getLoggedTeacher()))
                .filter(e -> hasRegisteredStudents(e))
                .filter(e -> !e.getRegisteredStudents().stream()
                        .allMatch(s -> this.getModel().getExamManager().alreadyReportedSuccess(s, e.getCourse())))
                .filter(e -> this.getModel().getExamManager().alreadyHeld(e)).collect(Collectors.toSet()));

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void displayResultTypeChoices() {
        ((CreateExamReportView) this.getView())
                .setExamResultTypeChoices(new HashSet<>(Arrays.asList(ExamResultType.values())));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void displayStudentChoices(final ExamCall examCall) {
        ((CreateExamReportView) this.getView()).setStudentChoices(examCall.getRegisteredStudents().stream()
                .filter(s -> !this.getModel().getExamManager().alreadyReportedSuccess(s, examCall.getCourse()))
                .collect(Collectors.toSet()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createExamReport(final Course course, final Student student, final ExamResultType resultType,
            final int result, final boolean cumLaude) {
        final ExamReport examReport = new ExamReportImpl.Builder().course(course).student(student)
                .resultType(resultType).result(result).laude(cumLaude).build();
        this.getModel().getExamManager().addExamReport(examReport);
        this.saveExamReports();
    }

}
