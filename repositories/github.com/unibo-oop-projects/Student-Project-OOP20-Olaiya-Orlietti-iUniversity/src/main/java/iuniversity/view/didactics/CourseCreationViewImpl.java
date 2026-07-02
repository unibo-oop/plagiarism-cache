package iuniversity.view.didactics;

import iuniversity.controller.didactics.CourseCreationController;
import iuniversity.view.AbstractView;
import iuniversity.view.PageSwitcher;
import iuniversity.view.Pages;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class CourseCreationViewImpl extends AbstractView implements CourseCreationView {

    @FXML
    private TextField courseNameTF;

    @FXML
    private TextField cfuTF;

    @FXML
    private Button addBtn;

    @FXML
    private Button cancelBtn;

    @FXML
    public void initialize() {
        this.addBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                createCourse();
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
    }

    @Override
    public void createCourse() {
        ((CourseCreationController) this.getController()).createCourse(courseNameTF.getText(), 
                Integer.parseInt(cfuTF.getText()));
        PageSwitcher.goToPage(this.getStage(), Pages.ADMIN_HOME, this.getController().getModel());
    }

}
