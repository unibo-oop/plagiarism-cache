package magazzino.entratamerci.controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import magazzino.entratamerci.dto.ArticoloModel;
import magazzino.entratamerci.dto.FornitoriModel;
import magazzino.entratamerci.service.FornitoriService;

public class FornitoriController {

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnSearch;

    @FXML
    private TableColumn<FornitoriModel, String> codColumn;

    @FXML
    private TableColumn<FornitoriModel, String> nomeColumn;
    
    @FXML
    private TableColumn<FornitoriModel, String> cognColumn;

    @FXML
    private TableColumn<FornitoriModel, String> descColumn;

    @FXML
    private TableView<FornitoriModel> gridFornitori;


    @FXML
    private TextField txtSearch;
    
    private ArrayList<FornitoriModel> arrModel;
	ObservableList<FornitoriModel> list;
    
	public void initialize() {
		arrModel = ModelWrapper.readFornitori();
		list = FXCollections.observableArrayList(arrModel);
		gridFornitori.setItems(list);
		codColumn.setCellValueFactory(new PropertyValueFactory<>("codice"));
		descColumn.setCellValueFactory(new PropertyValueFactory<>("desc"));
		nomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
		cognColumn.setCellValueFactory(new PropertyValueFactory<>("cognome"));

		
		gridFornitori.setRowFactory(tv -> {
			TableRow<FornitoriModel> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
					FornitoriModel rowData = row.getItem();

					try {
						FXMLLoader loader = new FXMLLoader(new File(StorageController.getInserisciAggiornaFornitore()).toURI().toURL());
						Stage stage = new Stage();
						stage.setScene(new Scene(loader.load()));
						stage.setTitle("Aggiorna Fornitore");
						stage.initModality(Modality.WINDOW_MODAL);

						InserisciAggiornaFornitoreController controller = loader.getController();

						controller.initData(new FornitoriService().getFornitoreByCodice(rowData.getCodice().get()));
						stage.show();
					} catch (IOException e) {
						throw new RuntimeException(e);
					}
				}
			});
			return row ;
		});
		
	}
	
	
    @FXML
    void addFornitori(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(new File(StorageController.getInserisciAggiornaFornitore()).toURI().toURL());

		Stage stage = new Stage();
		stage.setScene(new Scene(loader.load()));
		stage.setTitle("Inserisci Fornitore");
		stage.initModality(Modality.WINDOW_MODAL);

		stage.show();
    }
    
    

    @FXML
    void searchFornitori(ActionEvent event) {
    	String keyword = txtSearch.getText().toLowerCase();

		arrModel = ModelWrapper.readFornitori();
		list = FXCollections.observableArrayList(arrModel);
		gridFornitori.setItems(list);

		Stream<FornitoriModel> fornitori = list.stream().filter(x-> x.getCodice().get().toLowerCase().contains(keyword) ||
																	x.getDesc().get().toLowerCase().contains(keyword) ||
																	x.getNome().get().toLowerCase().contains(keyword) ||
																	x.getCognome().get().toLowerCase().contains(keyword));

		ArrayList<FornitoriModel> fornitoriFiltered = fornitori.collect(Collectors.toCollection(ArrayList::new));
		gridFornitori.setItems(FXCollections.observableArrayList(fornitoriFiltered));
    }

}

