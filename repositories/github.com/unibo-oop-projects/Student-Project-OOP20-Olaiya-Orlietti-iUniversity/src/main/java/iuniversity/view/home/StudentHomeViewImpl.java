package iuniversity.view.home;

import java.util.Objects;
import java.util.Set;

import iuniversity.controller.home.StudentHomeController;
import iuniversity.model.exams.ExamCall;
import iuniversity.view.AbstractView;
import iuniversity.view.PageSwitcher;
import iuniversity.view.Pages;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

/**
 * The student home view.
 */
public class StudentHomeViewImpl extends AbstractView implements StudentHomeView {

    @FXML
    private Label firstNameLbl;

    @FXML
    private Label lastNameLbl;

    @FXML
    private Label registrationNumberLbl;

    @FXML
    private Label degreeProgrammeLbl;

    @FXML
    private Label acquiredCreditsLbl;

    @FXML
    private Label meanExamResultLbl;

    @FXML
    private Label highestResultLbl;

    @FXML
    private Label lowestResultLbl;

    @FXML
    private Button showBookletBtn;

    @FXML
    private Button logoutBtn;

    @FXML
    private Button bookExamBtn;

    @FXML
    private Button withdrawBtn;

    @FXML
    private ListView<ExamCall> bookedExamCallList;

    private StudentHomeController controller;

    /**
     * {@inheritDoc}
     */
    @FXML
    public void initialize() {
        logoutBtn.setOnAction(e -> {
            getController().logout();
            PageSwitcher.goToPage(getStage(), Pages.LOGIN, getController().getModel());
        });
        withdrawBtn.setOnAction(e -> {
            final ExamCall examCall = this.bookedExamCallList.getSelectionModel().getSelectedItem();
            if (!Objects.isNull(examCall)) {
                this.controller.withdrawStudent(examCall);
                this.controller.displayOpenBookedExamCalls();
            }
        });
        bookExamBtn.setOnAction(e -> {
            PageSwitcher.goToPage(getStage(), Pages.BOOK_EXAM_CALL, getController().getModel());
        });
        showBookletBtn.setOnAction(e -> {
            PageSwitcher.openPage(Pages.BOOKLET, getController().getModel());
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start() {
        this.controller = (StudentHomeController) this.getController();
        this.controller.displayStudentInfo();
        this.controller.displayStudentStatistics();
        this.controller.displayOpenBookedExamCalls();
    }

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
    public void setDegreeProgramme(final String degreeProgramme) {
        degreeProgrammeLbl.setText(degreeProgramme);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setAcquiredCredits(final int acquiredCredits) {
        acquiredCreditsLbl.setText(String.valueOf(acquiredCredits));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setMeanExamResult(final double meanExamResult) {
        meanExamResultLbl.setText(String.valueOf(meanExamResult));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setHighestExamResult(final int highestExamResult) {
        highestResultLbl.setText(String.valueOf(highestExamResult));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLowestExamResult(final int lowestExamResult) {
        lowestResultLbl.setText(String.valueOf(lowestExamResult));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setOpenBookedExamCalls(final Set<ExamCall> examCalls) {
        bookedExamCallList.getItems().clear();
        bookedExamCallList.getItems().addAll(examCalls);
    }

}
