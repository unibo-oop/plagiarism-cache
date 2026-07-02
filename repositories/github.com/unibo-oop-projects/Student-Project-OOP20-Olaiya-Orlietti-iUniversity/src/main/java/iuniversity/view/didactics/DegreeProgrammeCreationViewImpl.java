package iuniversity.view.didactics;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import iuniversity.controller.didactics.DegreeProgrammeCreationController;
import iuniversity.model.didactics.Course;
import iuniversity.model.didactics.DegreeProgramme.DegreeType;
import iuniversity.view.AbstractView;
import iuniversity.view.PageSwitcher;
import iuniversity.view.Pages;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class DegreeProgrammeCreationViewImpl extends AbstractView implements DegreeProgrammeCreationView {

    @FXML
    private TextField nameTF;
    
    @FXML
    private ChoiceBox<DegreeType> typeChoice;

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

    private Set<Course> selectedCourses = new HashSet<>();

    @FXML
    public void initialize() {
        addCourseBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Course course = courseChoice.getValue();
                selectedCourses.add(course);
                addedCourseList.getItems().add(course);
            }
        });

        removeCourseBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Course course = addedCourseList.getSelectionModel().getSelectedItem();
                selectedCourses.remove(course);
                addedCourseList.getItems().remove(course);
            }
        });

        addBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                createDegreeProgramme();
                PageSwitcher.goToPage(getStage(), Pages.ADMIN_HOME, getController().getModel());
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
        ((DegreeProgrammeCreationController) this.getController()).initializeChoices();
    }

    @Override
    public void setDegreeTypeChoices(List<DegreeType> degreeTypes) {
        this.typeChoice.getItems().addAll(degreeTypes);
    }

    @Override
    public void setCourseChoices(List<Course> course) {
        this.courseChoice.getItems().addAll(course);
    }

    @Override
    public void createDegreeProgramme() {
        ((DegreeProgrammeCreationController) this.getController()).createDegreeProgramme(nameTF.getText(), 
                typeChoice.getValue(), selectedCourses);
    }

}
