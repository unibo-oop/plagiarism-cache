package magazzino.entratamerci.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import magazzino.entratamerci.dto.ArticoloModel;
import magazzino.entratamerci.models.articolo;
import magazzino.entratamerci.service.ArticoliService;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ArticoliController {

	@FXML
	private Button btAdd;

	@FXML
	private Button btnSearch;

	@FXML
	private CheckBox includiObsoleti;

	@FXML
	private TableView<ArticoloModel> tbData;
	@FXML
	private TableColumn<ArticoloModel, String> codice;
	@FXML
	private TableColumn<ArticoloModel, String> desc;
	@FXML
	private TableColumn<ArticoloModel, String> actions;
	@FXML
	private TableColumn<ArticoloModel, Boolean> isObsolete;
	@FXML
	private TableColumn<articolo, String> note;

	@FXML
	private TextField txtSearch;
	ObservableList<ArticoloModel> list;

	public void initialize() throws IOException {
		ArrayList<ArticoloModel> articoli = ModelWrapper.readArticoli();
		codice.setCellValueFactory(new PropertyValueFactory<>("codice"));
		desc.setCellValueFactory(new PropertyValueFactory<>("desc"));
		note.setCellValueFactory(new PropertyValueFactory<>("note"));
		isObsolete.setCellValueFactory(new PropertyValueFactory<>("isObsolete"));
		includiObsoleti.setSelected(true);
		list = FXCollections.observableArrayList(articoli);

		tbData.setRowFactory(tv -> {
			TableRow<ArticoloModel> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
					ArticoloModel rowData = row.getItem();

					try {
						FXMLLoader loader = new FXMLLoader(new File(StorageController.getInserisciAggiornaArticolo()).toURI().toURL());
						Stage stage = new Stage();
						stage.setScene(new Scene(loader.load()));
						stage.setTitle("Aggiorna articolo");
						stage.initModality(Modality.WINDOW_MODAL);

						InserisciAggiornaArticoloController controller = loader.getController();

						controller.initData(new ArticoliService().getArticoloByCodice(rowData.getCodice()));
						stage.show();
					} catch (IOException e) {
						throw new RuntimeException(e);
					}
				}
			});
			return row ;
		});

		tbData.setItems(list);
	}

	@FXML
	void addArticoli(ActionEvent event) throws IOException {

		FXMLLoader loader = new FXMLLoader(new File(StorageController.getInserisciAggiornaArticolo()).toURI().toURL());

		Stage stage = new Stage();
		stage.setScene(new Scene(loader.load()));
		stage.setTitle("Inserisci articolo");
		stage.initModality(Modality.WINDOW_MODAL);

		stage.show();
	}

	@FXML
	void searchArticoli(ActionEvent event) throws IOException {
		list = FXCollections.observableArrayList(ModelWrapper.readArticoli());
		String filtro = txtSearch.getText().toLowerCase();
		Stream<ArticoloModel> articoliStream = list.stream().filter(x-> x.getCodice().toLowerCase().contains(filtro) || x.getDesc().toLowerCase().contains(filtro));

		if(!includiObsoleti.isSelected()){
			articoliStream= articoliStream.filter(x-> x.getIsObsolete() == false);
		}
		ArrayList<ArticoloModel> articoliFiltered = articoliStream.collect(Collectors.toCollection(ArrayList:: new));
		tbData.setItems(FXCollections.observableArrayList(articoliFiltered));
	}

}