package iuniversity.view.users;

import java.util.List;

import iuniversity.controller.users.StudentCreationController;
import iuniversity.model.didactics.DegreeProgramme;
import iuniversity.model.user.User.Gender;
import iuniversity.view.AbstractView;
import iuniversity.view.PageSwitcher;
import iuniversity.view.Pages;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class StudentCreationViewImpl extends AbstractView implements StudentCreationView {

    @FXML
    private TextField firstNameTF;

    @FXML
    private TextField lastNameTF;

    @FXML
    private TextField addressTF;

    @FXML
    private DatePicker dateOfBirthPicker;

    @FXML
    private ChoiceBox<Gender> genderChoice;

    @FXML
    private ChoiceBox<DegreeProgramme> degreeChoice;

    @FXML
    private Button addBtn;

    @FXML
    private Button cancelBtn;

    @FXML
    public void initialize() {
        this.addBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                createStudent();
            }
        });

        this.cancelBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                PageSwitcher.goToPage(getStage(), Pages.ADMIN_HOME, getController().getModel());
            }
        });
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void start() {
        ((StudentCreationController) this.getController()).initializeChoices();
    }

    @Override
    public void createStudent() {
        ((StudentCreationController) this.getController()).createStudent(firstNameTF.getText(), lastNameTF.getText(), 
                dateOfBirthPicker.getValue(), genderChoice.getValue(), 
                addressTF.getText(), degreeChoice.getValue());
    }

    @Override
    public void showStudentCredentials(String username, String password) {
        new Alert(Alert.AlertType.INFORMATION, "Nuove credenziali:\nUsername: " + username + ", password: " + password)
        .showAndWait();
        PageSwitcher.goToPage(getStage(), Pages.ADMIN_HOME, getController().getModel());
    }
    @Override
    public void setGenderChoices(List<Gender> genders) {
        this.genderChoice.getItems().addAll(genders);
    }
    @Override
    public void setDegreeProgrammChoices(List<DegreeProgramme> degreeProgrammes) {
        this.degreeChoice.getItems().addAll(degreeProgrammes);
    }

}
