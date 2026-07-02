package iuniversity.view.exams;

import java.util.Objects;
import java.util.Set;

import iuniversity.controller.exams.ExamBookingController;
import iuniversity.model.exams.ExamCall;
import iuniversity.view.AbstractView;
import iuniversity.view.PageSwitcher;
import iuniversity.view.Pages;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

/**
 * The view for the exam booking.
 *
 */
public class ExamBookingViewImpl extends AbstractView implements ExamBookingView {

    @FXML
    private ListView<ExamCall> examCallList;

    @FXML
    private Button bookBtn;

    @FXML
    private Button cancelBtn;

    private ExamBookingController controller;

    /**
     * {@inheritDoc}
     */
    @FXML
    public void initialize() {
        cancelBtn.setOnAction(e -> {
            goToHome();
        });
        bookBtn.setOnAction(e -> {
            final ExamCall examCall = examCallList.getSelectionModel().getSelectedItem();
            if (!Objects.isNull(examCall)) {
                this.controller.bookExamCall(examCall);
                goToHome();
            }
        });
    }

    private void goToHome() {
        PageSwitcher.goToPage(getStage(), Pages.STUDENT_HOME, getController().getModel());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start() {
        this.controller = (ExamBookingController) this.getController();
        this.controller.displayAvailableExamCalls();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setAvailableExamCalls(final Set<ExamCall> examCalls) {
        examCallList.getItems().clear();
        examCallList.getItems().addAll(examCalls);
    }

}
