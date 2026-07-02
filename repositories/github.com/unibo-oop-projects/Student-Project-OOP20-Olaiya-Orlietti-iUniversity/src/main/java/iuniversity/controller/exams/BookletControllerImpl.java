package iuniversity.controller.exams;

import java.util.stream.Collectors;

import iuniversity.controller.AbstractController;
import iuniversity.model.exams.ExamReport;
import iuniversity.model.user.Student;
import iuniversity.view.exams.BookletView;

/**
 * Manages the visualization of the student booklet.
 */
public class BookletControllerImpl extends AbstractController implements BookletController {

    private boolean reportBelongsToStudent(final ExamReport examReport, final Student student) {
        return examReport.getStudent().equals(student);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void displayReports() {
        checkStudent();
        ((BookletView) this.getView()).setReports(this.getModel().getExamManager().getExamReports().stream()
                .filter(r -> reportBelongsToStudent(r, getLoggedStudent())).collect(Collectors.toSet()));
    }

}
