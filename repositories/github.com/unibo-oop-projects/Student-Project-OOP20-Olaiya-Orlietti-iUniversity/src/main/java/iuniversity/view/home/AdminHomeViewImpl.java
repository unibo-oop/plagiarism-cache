package iuniversity.view.home;

import iuniversity.view.AbstractView;
import iuniversity.view.PageSwitcher;
import iuniversity.view.Pages;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class AdminHomeViewImpl extends AbstractView {

    @FXML
    private Button addCourseBtn;

    @FXML
    private Button addDegreeProgramme;

    @FXML
    private Button addStudentBtn;

    @FXML
    private Button addTeacherBtn;

    @FXML
    private Button logoutBtn;


    /**
     * Choose whether to add the course, the degree course, the student, the teacher or log out.
     */
    @Override
    public void start() {
        addCourseBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                PageSwitcher.goToPage(getStage(), Pages.ADD_COURSE, getController().getModel());
            }
        });

        addDegreeProgramme.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                PageSwitcher.goToPage(getStage(), Pages.ADD_DEGREE_PROGRAMME, getController().getModel());
            }
        });

        addStudentBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                PageSwitcher.goToPage(getStage(), Pages.ADD_STUDENT, getController().getModel());
            }
        });

        addTeacherBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                PageSwitcher.goToPage(getStage(), Pages.ADD_TEACHER, getController().getModel());
            }
        });

        logoutBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                getController().logout();
                PageSwitcher.goToPage(getStage(), Pages.LOGIN, getController().getModel());
            }
        });
    }

    @FXML
    public void initialize() {
   
    }
}
