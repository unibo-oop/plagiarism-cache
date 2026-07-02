package iuniversity.view.users;

import java.util.HashSet;
import java.util.Set;

import iuniversity.controller.users.TeacherCreationController;
import iuniversity.model.didactics.Course;
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
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class TeacherCreationViewImpl extends AbstractView implements TeacherCreationView {

    @FXML
    private TextField firstNameTF;

    @FXML
    private TextField lastNameTF;

    @FXML
    private DatePicker dateOfBirthPicker;

    @FXML
    private ChoiceBox<Gender> genderChoice;

    @FXML
    private TextField addressTF;
    
    @FXML
    private ChoiceBox<Course> courseChoice;

    @FXML
    private ListView<Course> addedCourseList;

    @FXML
    private Button addCourseBtn;

    @FXML
    private Button removeCourseBtn;

    @FXML
    private Button addBtn;

    @FXML
    private Button cancelBtn;

    private Set<Course> addedCourses = new HashSet<>();

    @FXML
    public void initialize() {
        addCourseBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Course course = courseChoice.getValue();
                addedCourseList.getItems().add(course);
                addedCourses.add(course);
            }
        });

        removeCourseBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Course course = addedCourseList.getSelectionModel().getSelectedItem();
                addedCourseList.getItems().remove(course);
                addedCourses.remove(course);
            }
        });

        addBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                createTeacher();
            }
        });

        cancelBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                PageSwitcher.goToPage(getStage(), Pages.ADMIN_HOME, getController().getModel());
            }
        });
    }

    @Override
    public void start() {
        ((TeacherCreationController) this.getController()).initializeChoices();
    }

    @Override
    public void createTeacher() {
        ((TeacherCreationController) this.getController()).createTeacher(firstNameTF.getText(), lastNameTF.getText(), 
                dateOfBirthPicker.getValue(), genderChoice.getValue(), addressTF.getText(), addedCourses);
    }

    @Override
    public void showTeacherCredentials(String username, String password) {
        new Alert(Alert.AlertType.INFORMATION, "Nuove credenziali:\nUsername: " + username + ", password: " + password)
        .showAndWait();
        PageSwitcher.goToPage(getStage(), Pages.ADMIN_HOME, getController().getModel());
    }

    @Override
    public void setGenderChoices(Set<Gender> genders) {
        genderChoice.getItems().addAll(genders);
    }

    @Override
    public void setCourseChoices(Set<Course> course) {
        courseChoice.getItems().addAll(course);
    }

}
