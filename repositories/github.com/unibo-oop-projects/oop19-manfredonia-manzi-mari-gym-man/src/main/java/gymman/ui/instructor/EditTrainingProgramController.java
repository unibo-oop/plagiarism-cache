package gymman.ui.instructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import gymman.auth.AuthService;
import gymman.auth.NotLoggedInException;
import gymman.auth.Permission;
import gymman.auth.PermissionImpl;
import gymman.common.DuplicateEntityException;
import gymman.fitness.DateUtils;
import gymman.fitness.Exercise;
import gymman.fitness.TrainingProgram;
import gymman.fitness.TrainingProgramRepository;
import gymman.ui.ButtonsCellFactory;
import gymman.ui.navigation.NavigationService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.Getter;
import lombok.Setter;

/**
 * Class used to edit anTrainingProgram.
 */
public class EditTrainingProgramController extends AbstractModifyProgramController {

    private final AuthService authService;

    @Getter @Setter private TrainingProgram  trainingProgram;
    @Getter private Set<Exercise> deletedEx = new HashSet<>();

    /** Exercise denomination column. */
    @FXML private  TableColumn<Exercise, String> nameCol = new TableColumn<>("Denominazine");
    /** Exercise category column. */
    @FXML private  TableColumn<Exercise, String> categoryCol = new TableColumn<>("Categoria");
    /** Exercise type column. */
    //@FXML private  TableColumn<Exercise, String> typeCol = new TableColumn<>("Tipo");
    /** Exercise execution metric column. */
    @FXML private  TableColumn<Exercise, String> metricCol = new TableColumn<>("Metrica");
    /** Delete button column. */
    @FXML private  TableColumn<Exercise, Void> deleteCol = new TableColumn<>("");


    private Permission editProgram = new PermissionImpl("edit_program", "Modifica la scheda dell'allenamento");

    /**
     * Constructor of the edit page controller.
     * @param nav : navigation service to make possible
     *              the controllers communication
     * @param trainingProgramRepo : all training programs repository
     * @param auth : authentication service to controller the permissions
     */
    public EditTrainingProgramController(
            final NavigationService nav,
            final TrainingProgramRepository trainingProgramRepo,
            final AuthService auth
    ) {
        super(nav, trainingProgramRepo);
        this.authService = auth;
        this.authService.registerPermission(editProgram);
    }

    @FXML
    private TableView<Exercise> exercisesTable;

    @FXML private Button saveButton;


    /**
     * Initialize the view of editing page.
     * @throws NotLoggedInException if the user isn't logged in
     */
    @FXML
    public void initialize() throws NotLoggedInException {

        saveButton.setVisible(false);

        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        categoryCol.setCellValueFactory(new PropertyValueFactory<>("category"));
        metricCol.setCellValueFactory(new PropertyValueFactory<>("executionMetric"));

        ButtonsCellFactory<Exercise> buttonsFactory = new ButtonsCellFactory<>(0, 0);

        buttonsFactory.addButton("Delete", ex -> {
            saveButton.setVisible(true);
            deletedEx.add(ex);
            this.exSizeLabel.setText(String.valueOf(getAllExercises().size() - getDeletedEx().size()));
            exercisesTable.getItems().remove(ex);
        });

        this.deleteCol.setCellFactory(buttonsFactory.getFactory());

    }

    /**
     * Method to update the table of the exercises with the new exercise.
     */
    @FXML
    public void addExerciseToTable() {
        addExercise();
        this.exercisesTable.setItems(FXCollections.observableArrayList(getAllExercises()));
    }

    private ObservableList<Exercise> getExercises(final List<Exercise> exList) {
           return FXCollections.observableArrayList(exList);
    }



    @Override
    public final void onDisplay() {
        this.deletedEx = new HashSet<>();
        updateView();
        if (this.getCustomer() != null) {
            this.setTrainingProgram(this.getTrainingProgramRepo().getByUsername(getCustomer().getUsername()).get());
        } else {
            this.getNav().navigate("page_instructor");
        }

        fromDateP.setValue(DateUtils.convertToLocalDateViaInstant(trainingProgram.getValidFrom()));
        toDateP.setValue(DateUtils.convertToLocalDateViaInstant(trainingProgram.getValidTo()));
        durationLabel.setText(String.valueOf(DateUtils.daysBetweenDates(trainingProgram.getValidFrom(), trainingProgram.getValidTo())));
        goalCombo.setValue(trainingProgram.getGoal().toString());

        if (this.getAllExercises().size() == 0) {
            addExerciesToList(trainingProgram.getExercises());
        }
        this.exercisesTable.setItems(getExercises(getAllExercises()));
        this.exSizeLabel.setText(String.valueOf(getAllExercises().size()));



    }

    /**
     * Method to save the edited training program.
     * @throws DuplicateEntityException if trying to add a new training program that already
     * existed.
     */
    public void saveEditedTrainingProgram() throws DuplicateEntityException {
        this.getTrainingProgramRepo().remove(trainingProgram);

        removeExercises(deletedEx);
        saveButton.setVisible(true);

        if (!areInfoFieldsSelected()) {
            // Information fields not completed correctly.
        } else {
            Alert alert = new Alert(AlertType.CONFIRMATION, "Confermi questa scheda d'allenamento ?", ButtonType.YES, ButtonType.NO);
            alert.showAndWait();
            if (alert.getResult().equals(ButtonType.YES)) {

                final TrainingProgram tp = new TrainingProgram.TrainingProgramBuilder()
                        .customer(getCustomer())
                        .goal(this.getGoal(this.goalCombo.getValue().toString()))
                        .periodValidationFrom(DateUtils.convertToDateViaInstant(this.fromDateP.getValue()))
                        .periodValidationTo(DateUtils.convertToDateViaInstant(this.toDateP.getValue()))
                        .buildTrainingProgramPlan();
                getAllExercises().forEach((exercise) -> tp.addNewExercise(exercise));
                getAllExercises().removeAll(getAllExercises());
                this.getTrainingProgramRepo().add(tp);
            } else if (alert.getResult().equals(ButtonType.NO)) {
                resetExerciseFields();
                this.getNav().navigate("page_instructor");
            }
        }

        resetExerciseFields();
        this.getNav().navigate("page_instructor");
    }

}
