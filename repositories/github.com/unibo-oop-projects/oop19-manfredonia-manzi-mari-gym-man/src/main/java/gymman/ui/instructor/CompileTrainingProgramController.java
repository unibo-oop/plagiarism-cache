package gymman.ui.instructor;

import gymman.auth.AuthService;
import gymman.common.DuplicateEntityException;
import gymman.fitness.DateUtils;
import gymman.fitness.TrainingProgram;
import gymman.fitness.TrainingProgramRepository;
import gymman.ui.navigation.NavigationService;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

/**
 * Class used to create a new TrainingProgram. The new training
 * program should contain at least one exercise.
 *
 */
public class CompileTrainingProgramController extends AbstractModifyProgramController {
    /**
     * Constructor of CompileTrainingProgramController.
     * @param nav : the navigation service, used to controllers communication
     * @param tProgramRepo : all training programs repository
     * @param auth : authentication service to control the user permissions
     */
    public CompileTrainingProgramController(
            final NavigationService nav,
            final TrainingProgramRepository tProgramRepo,
            final AuthService auth
    ) {
        super(nav, tProgramRepo);
    }

    @Override
    public void onDisplay() {

        updateView();
    }


    /**
     * Method to save to the TrainingProgramRepository the new
     * TrainingProgram. The training program will not be saved if
     * there isn't at least one Exercise. After saving the new program,
     * it will turn back to the instructor interface.
     *
     * @throws DuplicateEntityException : if the training program already exist
     */
    @FXML
    public void saveTrainingProgram() throws DuplicateEntityException {

        /*
         * Control if all the necessary information have been filled.
         * If not don't save the training program, otherwise continue the
         * to steps to save it.
         */
        if (!areInfoFieldsSelected()) {
            // Information fields not correct
        } else {
            /*
             * Necessary information have been filled. Control if there is at least
             * one exercise.
             */
            if (getAllExercises().size() == 0) {
                final Alert alert = new Alert(AlertType.WARNING, "Scheda dell'allenamento deve contenere almeno un esercizio", ButtonType.OK);
                alert.showAndWait();
            } else {
                    final Alert alert = new Alert(AlertType.CONFIRMATION,  "Confermi questa scheda d'allenamento ?", ButtonType.YES, ButtonType.NO);
                    alert.showAndWait();
                    if (alert.getResult().equals(ButtonType.YES)) {
                        final TrainingProgram trainingPrg = new TrainingProgram.TrainingProgramBuilder()
                                    .customer(getCustomer())
                                    .goal(getGoal(this.goalCombo.getValue().toString()))
                                    .periodValidationFrom(DateUtils.convertToDateViaInstant(this.fromDateP.getValue()))
                                    .periodValidationTo(DateUtils.convertToDateViaInstant(this.toDateP.getValue()))
                                    .buildTrainingProgramPlan();
                        getAllExercises().forEach((exercise) -> trainingPrg.addNewExercise(exercise));
                        getAllExercises().removeAll(getAllExercises());
                    this.getTrainingProgramRepo().add(trainingPrg);
                } else if (alert.getResult().equals(ButtonType.NO)) {
                       resetExerciseFields();
                      this.getNav().navigate("page_instructor");
                   }
            }
        }

        resetExerciseFields();
        this.getNav().navigate("page_instructor");
    }

}
