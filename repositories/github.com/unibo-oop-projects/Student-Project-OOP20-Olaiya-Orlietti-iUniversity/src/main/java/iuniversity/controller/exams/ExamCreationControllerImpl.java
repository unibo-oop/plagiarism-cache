package iuniversity.controller.exams;

import java.time.LocalDate;
import java.util.Set;

import iuniversity.controller.AbstractController;
import iuniversity.model.didactics.Course;
import iuniversity.model.exams.ExamCall.ExamType;
import iuniversity.view.exams.ExamCreationView;

/**
 * Manages the creation of an exam call.
 */
public class ExamCreationControllerImpl extends AbstractController implements ExamCreationController {

    private static final String EXAM_CALL_CREATION_ERROR = "Errore nella creazione dell'appello d'esame. Riprovare";

    /**
     * {@inheritDoc}
     */
    @Override
    public void initializeChoices() {
        this.initializeExamTypeChoices();
        this.initilizeCourseChoices();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initializeExamTypeChoices() {
        ((ExamCreationView) this.getView()).setExamTypeChoices(Set.of(ExamType.values()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initilizeCourseChoices() {
        checkTeacher();
        ((ExamCreationView) this.getView()).setCourseChoices(getLoggedTeacher().getCourses());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void publishExamCall(final LocalDate callStart, final Course course, final ExamType examType,
            final int maximumStudents) {
        try {
            this.getModel().getExamManager().addExamCall(callStart, course, examType, maximumStudents);
            this.saveExamCalls();
        } catch (IllegalStateException e) {
            this.getView().showErrorMessage(EXAM_CALL_CREATION_ERROR);
        }
    }
}
