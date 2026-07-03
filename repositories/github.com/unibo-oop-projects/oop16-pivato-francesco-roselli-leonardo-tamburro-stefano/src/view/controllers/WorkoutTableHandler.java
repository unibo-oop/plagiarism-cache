package view.controllers;

import static view.ViewHandler.getObserver;

import java.util.List;
import java.util.Map;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableColumn.CellDataFeatures;
import javafx.scene.control.TreeTableView;
import javafx.scene.input.MouseEvent;
import model.ExerciseImpl;
import model.enumerations.ExerciseType;
import model.interfaces.Exercise;
import model.interfaces.User;
import view.Vista;
import view.WorkoutTable;

public class WorkoutTableHandler extends Vista implements WorkoutTable {
	
	private User currentUser;
	
	@FXML private Button backBtn;
	@FXML private TreeTableView<Exercise> table;
	@FXML private TreeTableColumn<Exercise, String> muscolarGroupCol;
	@FXML private TreeTableColumn<Exercise, String> restCol;
	@FXML private TreeTableColumn<Exercise, String> exerciseCol;
	@FXML private TreeTableColumn<Exercise, String> setCol;
	@FXML private TreeTableColumn<Exercise, String> repsCol;
	@FXML private TreeTableColumn<Exercise, String> weightCol;
	@FXML private Button addBtn;
	@FXML private Button removeBtn;
	@FXML private TextField exerciseField;
	@FXML private TextField repsField;
	@FXML private TextField weightField;
	@FXML private TextField restField;
	@FXML private TextField setField;
	@FXML private TextField dayField;
	@FXML private ChoiceBox<ExerciseType> muscolarGroupChoiceBox;

	private final TreeItem<Exercise> dummyRoot = new TreeItem<>();

	public WorkoutTableHandler() {}

	@FXML
	private void initialize() {
		this.muscolarGroupChoiceBox.setItems(FXCollections.observableArrayList(ExerciseType.values()));
		this.exerciseCol.setCellValueFactory(
				(CellDataFeatures<Exercise, String> p) -> new ReadOnlyStringWrapper(p.getValue().getValue().getName()));
		//this.muscolarGroupCol.setCellValueFactory(
		//		(CellDataFeatures<Exercise, String> p) -> new ReadOnlyStringWrapper(p.getValue().getValue().getType().toString()));
		this.setCol.setCellValueFactory(
				(CellDataFeatures<Exercise, String> p) -> new ReadOnlyStringWrapper(p.getValue().getValue().getSet()));
		this.repsCol.setCellValueFactory(
				(CellDataFeatures<Exercise, String> p) -> new ReadOnlyStringWrapper(p.getValue().getValue().getReps()));
		this.weightCol.setCellValueFactory(
				(CellDataFeatures<Exercise, String> p) -> new ReadOnlyStringWrapper(p.getValue().getValue().getWeight()));
		this.restCol.setCellValueFactory(
				(CellDataFeatures<Exercise, String> p) -> new ReadOnlyStringWrapper(p.getValue().getValue().getRest()));
		table.setRoot(dummyRoot);
		table.setShowRoot(false);
	}

	@FXML
	private void goBack() {
		super.goBack(this.backBtn.getScene());
	}

	@FXML
	private void addExercise() {
		Exercise ex = new ExerciseImpl.ExerciseBuilder()
				.name(exerciseField.getText())
				.reps(repsField.getText())
				.rest(restField.getText())
				.set(setField.getText())
				.weight(weightField.getText())
				.type(muscolarGroupChoiceBox.getSelectionModel().getSelectedItem())
				.build();
		getObserver().addExercise(this.currentUser, ex, this.dayField.getText());
		this.table.getRoot().getChildren().clear();
		getObserver().populateWorkoutTable(this.currentUser);
	}

	@FXML
	private void removeExercise() {
		getObserver().removeExercise(this.currentUser, this.table.getSelectionModel().getSelectedItem().getValue(),
				this.table.getSelectionModel().getSelectedItem().getParent().getValue().getName());
		this.table.getRoot().getChildren().clear();
		getObserver().populateWorkoutTable(this.currentUser);
	}

	@FXML
	private void copyExercise(MouseEvent event) {
		if (event.getClickCount() == 2) {
			Exercise selectedExercise = this.table.getSelectionModel().getSelectedItem().getValue();
			if (selectedExercise != null) {
				this.dayField.setText(this.table.getSelectionModel().getSelectedItem().getParent().getValue().getName());
				this.exerciseField.setText(selectedExercise.getName());
				this.muscolarGroupChoiceBox.setValue(selectedExercise.getType());
				this.setField.setText(selectedExercise.getSet());
				this.repsField.setText(selectedExercise.getReps());
				this.weightField.setText(selectedExercise.getWeight());
				this.restField.setText(selectedExercise.getRest());
			}
		}
	}
	
	@Override
	public void setExerciseList(User user, Map<Exercise, List<Exercise>> exercisesMap) {
		this.currentUser = user;
		exercisesMap.keySet().stream().forEach(day -> {
			TreeItem<Exercise> d = new TreeItem<Exercise>(day);
			exercisesMap.get(day).stream().forEach(e -> d.getChildren().add(new TreeItem<>(e)));
			dummyRoot.getChildren().add(d);
		});
	}

}
