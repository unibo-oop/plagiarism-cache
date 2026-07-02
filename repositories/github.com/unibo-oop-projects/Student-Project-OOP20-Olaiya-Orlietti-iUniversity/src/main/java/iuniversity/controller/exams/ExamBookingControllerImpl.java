package iuniversity.controller.exams;

import java.util.stream.Collectors;

import iuniversity.controller.AbstractController;
import iuniversity.model.didactics.Course;
import iuniversity.model.exams.ExamCall;
import iuniversity.model.exams.ExamResult.ExamResultType;
import iuniversity.model.user.Student;
import iuniversity.view.exams.ExamBookingView;

/**
 * Manages the booking of an exam call.
 */
public class ExamBookingControllerImpl extends AbstractController implements ExamBookingController {

    private static final String BOOKING_ERROR_MESSAGE = "Impossibile iscriversi all'appello d'esame";

    private boolean alreadySucceded(final Student student, final Course course) {
        return this.getModel().getExamManager().getExamReports().stream()
                .filter(r -> r.getResult().getResultType() == ExamResultType.SUCCEDED)
                .filter(r -> r.getCourse().equals(course)).anyMatch(r -> r.getStudent().equals(student));
    }

    private boolean isFollowedByStudent(final Course course, final Student student) {
        return student.getDegreeProgramme().getCourses().contains(course);
    }

    private boolean alreadyRegistered(final ExamCall examCall, final Student student) {
        return examCall.getRegisteredStudents().contains(student);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void displayAvailableExamCalls() {
        checkStudent();
        ((ExamBookingView) this.getView()).setAvailableExamCalls(this.getModel().getExamManager().getExamCalls()
                .stream().filter(e -> !alreadyRegistered(e, getLoggedStudent()))
                .filter(e -> e.isOpen())
                .filter(e -> isFollowedByStudent(e.getCourse(), getLoggedStudent()))
                .filter(e -> !alreadySucceded(getLoggedStudent(), e.getCourse())).collect(Collectors.toSet()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void bookExamCall(final ExamCall examCall) {
        checkStudent();
        if (!this.getModel().getExamManager().registerStudent(examCall, getLoggedStudent())) {
            this.getView().showErrorMessage(BOOKING_ERROR_MESSAGE);
        } else {
            this.saveExamCalls();
        }
    }

}
