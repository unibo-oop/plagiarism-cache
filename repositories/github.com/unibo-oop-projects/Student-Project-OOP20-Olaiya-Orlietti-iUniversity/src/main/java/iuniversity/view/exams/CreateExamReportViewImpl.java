package iuniversity.view.exams;

import java.util.Objects;
import java.util.Set;

import iuniversity.controller.exams.CreateExamReportController;
import iuniversity.model.exams.ExamCall;
import iuniversity.model.user.Student;
import iuniversity.view.AbstractView;
import iuniversity.view.PageSwitcher;
import iuniversity.view.Pages;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.VBox;
import iuniversity.model.exams.ExamResult.ExamResultType;

/**
 * The view for the creation of an exam report.
 */
public class CreateExamReportViewImpl extends AbstractView implements CreateExamReportView {

    private static final int MAX_RESULT = 30;
    private static final String REQUIRED_INPUT_MESSAGE = "Controllare che tutti i campi siano consistenti";
    private static final String INVALID_EXAM_REPORT_ARGUMENT = "Controllare che la valutazione sia costruita correttamente";
    private static final String NO_HELD_EXAM_CALL_MESSAGE = "Non ci sono appelli d'esame conclusi";

    @FXML
    private ChoiceBox<ExamCall> examCallChoice;

    @FXML
    private ChoiceBox<Student> studentChoice;

    @FXML
    private ChoiceBox<ExamResultType> resultTypeChoice;

    @FXML
    private Spinner<Integer> resultSpin;

    @FXML
    private CheckBox laudeCB;

    @FXML
    private Button cancelBtn;

    @FXML
    private Button publishBtn;

    @FXML
    private VBox resultTypeVB;

    @FXML
    private VBox resultVB;

    private CreateExamReportController controller;

    /**
     * {@inheritDoc}
     */
    @FXML
    public void initialize() {
        this.resultSpin.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, MAX_RESULT));
        cancelBtn.setOnAction(e -> {
            PageSwitcher.goToPage(getStage(), Pages.TEACHER_HOME, getController().getModel());
        });
        examCallChoice.setOnAction(e -> {
            final ExamCall examCall = this.examCallChoice.getValue();
            if (!Objects.isNull(examCall)) {
                this.controller.displayStudentChoices(examCall);
                System.out.println(examCall);
            }
        });
        publishBtn.setOnAction(e -> {
            if (!checkInput()) {
                this.showErrorMessage(REQUIRED_INPUT_MESSAGE);
                return;
            }
            try {
                this.controller.createExamReport(examCallChoice.getValue().getCourse(), studentChoice.getValue(),
                        resultTypeChoice.getValue(), resultSpin.getValue(), laudeCB.isSelected());
                PageSwitcher.goToPage(getStage(), Pages.TEACHER_HOME, getController().getModel());
            } catch (IllegalArgumentException ex) {
                this.showErrorMessage(INVALID_EXAM_REPORT_ARGUMENT);
                ex.printStackTrace();
            }
        });
        laudeCB.setOnAction(e -> {
            resultTypeVB.setVisible(!laudeCB.isSelected());
            resultVB.setVisible(!laudeCB.isSelected());
        });
    }

    private boolean checkInput() {
        return Objects.nonNull(this.examCallChoice.getValue()) && Objects.nonNull(studentChoice.getValue());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start() {
        this.controller = (CreateExamReportController) this.getController();
        controller.displayExamCallChoices();
        controller.displayResultTypeChoices();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setExamCallChoices(final Set<ExamCall> examCalls) {
        if (examCalls.isEmpty()) {
            this.showInfoMessage(NO_HELD_EXAM_CALL_MESSAGE);
        }
        this.examCallChoice.getItems().clear();
        this.examCallChoice.getItems().addAll(examCalls);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setStudentChoices(final Set<Student> students) {
        this.studentChoice.getItems().clear();
        this.studentChoice.getItems().addAll(students);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setExamResultTypeChoices(final Set<ExamResultType> examResultType) {
        this.resultTypeChoice.getItems().clear();
        this.resultTypeChoice.getItems().addAll(examResultType);
    }

}
