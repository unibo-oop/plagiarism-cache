package iuniversity.view.home;

import java.util.Objects;
import java.util.Set;

import iuniversity.controller.home.TeacherHomeController;
import iuniversity.model.exams.ExamCall;
import iuniversity.view.AbstractView;
import iuniversity.view.PageSwitcher;
import iuniversity.view.Pages;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

/**
 * The teacher home view.
 */
public class TeacherHomeViewImpl extends AbstractView implements TeacherHomeView {

    @FXML
    private Label firstNameLbl;

    @FXML
    private Label lastNameLbl;

    @FXML
    private Label registrationNumberLbl;

    @FXML
    private Label promotedStudentsLbl;

    @FXML
    private Label failedStudentsLbl;

    @FXML
    private Label withdrawnStudentsLbl;

    @FXML
    private Label cumLaudeStudentsLbl;

    @FXML
    private Label meanResultsLbl;

    @FXML
    private ListView<ExamCall> openExamCallList;

    @FXML
    private Button addExamResultBtn;

    @FXML
    private Button newExamCallBtn;

    @FXML
    private Button removeExamCallBtn;

    @FXML
    private Button logoutBtn;

    private TeacherHomeController controller;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setFirstName(final String firstName) {
        firstNameLbl.setText(firstName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLastName(final String lastName) {
        lastNameLbl.setText(lastName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setRegistrationNumber(final int registrationNumber) {
        registrationNumberLbl.setText(String.valueOf(registrationNumber));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPromotedStudents(final int promotedStudents) {
        promotedStudentsLbl.setText(String.valueOf(promotedStudents));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setFailedStudents(final int failedStudents) {
        failedStudentsLbl.setText(String.valueOf(failedStudents));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setWithdrawnStudents(final int withdrawnStudents) {
        withdrawnStudentsLbl.setText(String.valueOf(withdrawnStudents));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCumLaudeStudents(final int cumLaudeStudents) {
        cumLaudeStudentsLbl.setText(String.valueOf(cumLaudeStudents));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setMeanResults(final double meanResults) {
        meanResultsLbl.setText(String.valueOf(meanResults));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setOpenExamCalls(final Set<ExamCall> examCalls) {
        this.openExamCallList.getItems().clear();
        this.openExamCallList.getItems().addAll(examCalls);
    }

    /**
     * 
     */
    @FXML
    public void initialize() {
        logoutBtn.setOnAction(e -> {
            getController().logout();
            PageSwitcher.goToPage(getStage(), Pages.LOGIN, getController().getModel());
        });

        newExamCallBtn.setOnAction(e -> {
            PageSwitcher.goToPage(getStage(), Pages.CREATE_EXAM_CALL, getController().getModel());
        });

        removeExamCallBtn.setOnAction(e -> {
            final ExamCall examCall = openExamCallList.getSelectionModel().getSelectedItem();
            if (!Objects.isNull(examCall)) {
                this.controller.removeExamCall(examCall);
                this.controller.displayOpenExamCalls();
            }
        });
        addExamResultBtn.setOnAction(e -> {
            PageSwitcher.goToPage(getStage(), Pages.CREATE_EXAM_REPORT, getController().getModel());
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start() {
        this.controller = (TeacherHomeController) this.getController();
        this.controller.displayTeacherInfo();
        this.controller.displayTeacherStatistics();
        this.controller.displayOpenExamCalls();
    }

}
