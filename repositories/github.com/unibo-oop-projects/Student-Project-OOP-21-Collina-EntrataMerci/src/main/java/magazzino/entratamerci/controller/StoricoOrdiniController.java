package magazzino.entratamerci.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import magazzino.entratamerci.dto.OrdineModel;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class StoricoOrdiniController {
    @FXML
    private TableColumn<OrdineModel, String> codiceColumn;

    @FXML
    private TableColumn<OrdineModel, String> dataColumn;

    @FXML
    private TableColumn<OrdineModel, String> nomeColumn;

    @FXML
    private TableColumn<OrdineModel, Integer> numeroPezziColumn;

    @FXML
    private TableColumn<OrdineModel, String> fornitoreColumn;

    @FXML
    private Button searchBtn;

    @FXML
    private TextField filtroArticolo;

    @FXML
    private TableView<OrdineModel> tableStorico;

    ArrayList<OrdineModel> ordini;


    @FXML
    void filterOrder(ActionEvent event) {
        String filter = filtroArticolo.getText().trim().toLowerCase();

        ArrayList<OrdineModel> ordiniFiltered = ordini.stream().filter(x-> x.getCodice().toLowerCase().contains(filter) ||
                x.getFornitore().toLowerCase().contains(filter) ||
                x.getNome().toLowerCase().contains(filter)).collect(Collectors.toCollection(ArrayList :: new));

        tableStorico.setItems(FXCollections.observableArrayList(ordiniFiltered));
    }

    public void initialize() {
        ordini = ModelWrapper.readOrdini();

        codiceColumn.setCellValueFactory(new PropertyValueFactory<>("Codice"));
        fornitoreColumn.setCellValueFactory(new PropertyValueFactory<>("Fornitore"));
        nomeColumn.setCellValueFactory(new PropertyValueFactory<>("Nome"));
        dataColumn.setCellValueFactory(new PropertyValueFactory<>("Data"));
        numeroPezziColumn.setCellValueFactory(new PropertyValueFactory<>("NumeroPezzi"));

        ObservableList<OrdineModel> list = FXCollections.observableArrayList(ordini);
        tableStorico.setItems(list);
    }

}
