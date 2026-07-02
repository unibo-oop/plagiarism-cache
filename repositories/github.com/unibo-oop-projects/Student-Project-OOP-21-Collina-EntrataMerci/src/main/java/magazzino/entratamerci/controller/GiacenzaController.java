package magazzino.entratamerci.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import magazzino.entratamerci.dto.ArticoloModel;
import magazzino.entratamerci.dto.GiacenzaModel;
import magazzino.entratamerci.models.area;
import magazzino.entratamerci.models.locazione;
import magazzino.entratamerci.service.AreeService;
import magazzino.entratamerci.service.LocazioniService;
import magazzino.entratamerci.utils.ComboBoxUtility;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GiacenzaController {

    @FXML
    private TableColumn<GiacenzaModel, String> articoloColumn;

    @FXML
    private ComboBox<String> cmbArea;

    @FXML
    private ComboBox<String> cmbLocazione;

    @FXML
    private TableColumn<GiacenzaModel, String> posizioneColumn;

    @FXML
    private TableColumn<GiacenzaModel, Integer> qtaColumn;

    @FXML
    private TableView<GiacenzaModel> tableGiacenza;

    @FXML
    private TextField txtArticolo;
    
    private ObservableList<String> obsLocaz = FXCollections.observableArrayList();
    private ObservableList<String> obsArea = FXCollections.observableArrayList();
    
    @FXML
    void areaChanged(ActionEvent event) throws IOException {
        obsLocaz.clear();
        obsLocaz.add("");

        if(cmbArea.getValue() == null || cmbArea.getValue().isEmpty()){
            return;
        }
    	String areaCodice =cmbArea.getValue().split("-")[0].trim();
        area area = new AreeService().getAreaByCodice(areaCodice);
    	ArrayList<locazione> locazioniFiltered= new LocazioniService().getLocazioniByArea(area);
        ArrayList<String> locazioni = locazioniFiltered.stream().map(x-> String.format("%s - %s", x.getCodice(), x.getDescrizione())).collect(Collectors.toCollection(ArrayList<String>::new));
        obsLocaz.addAll(locazioni);
        cmbLocazione.setItems(obsLocaz);
    }

    @FXML
    void filterGiacenza(ActionEvent event) {
    	list = FXCollections.observableArrayList(ModelWrapper.readGiacenza());

		String articoloFilter = txtArticolo.getText().toLowerCase();
		String areaFilter = cmbArea.getValue() != null && !cmbArea.getValue().isEmpty() ? cmbArea.getValue().split("-")[0].trim() : "";
		String locazioneFilter = cmbLocazione.getValue() != null && !cmbLocazione.getValue().isEmpty() ? cmbLocazione.getValue().split("-")[0].trim() : "";

		ObservableList<ArticoloModel> filteredList = FXCollections.observableArrayList();
		Stream<GiacenzaModel> giacenzaStream = list.stream();

        if(!articoloFilter.isEmpty()){
            giacenzaStream =  giacenzaStream.filter(x-> x.getArticolo().toLowerCase().contains(articoloFilter));
        }
        if(!areaFilter.isEmpty()){
            giacenzaStream =  giacenzaStream.filter(x-> x.getPosizione().contains(areaFilter));
        }
        if(!locazioneFilter.isEmpty()){
            giacenzaStream =  giacenzaStream.filter(x-> x.getPosizione().contains(locazioneFilter));
        }

		ArrayList<GiacenzaModel> giacenzaFiltered = giacenzaStream.collect(Collectors.toCollection(ArrayList:: new));
		tableGiacenza.setItems(FXCollections.observableArrayList(giacenzaFiltered));
    }

    private ArrayList<GiacenzaModel> giacenza;
    private ObservableList<GiacenzaModel> list;

    public void initialize() throws IOException {

        giacenza = ModelWrapper.readGiacenza();

        articoloColumn.setCellValueFactory(new PropertyValueFactory<>("Articolo"));
        posizioneColumn.setCellValueFactory(new PropertyValueFactory<>("Posizione"));
        qtaColumn.setCellValueFactory(new PropertyValueFactory<>("Quantita"));

        list = FXCollections.observableArrayList(giacenza);
        tableGiacenza.setItems(list);

        obsArea.add("");
        ArrayList<String> aree = new AreeService().getAree().stream().map(x-> ComboBoxUtility.DisplayCodiceDescrizione(x.getCodice(),x.getDescrizione())).collect(Collectors.toCollection(ArrayList::new));
        obsArea.addAll(aree);
        cmbArea.setItems(obsArea);
        
    }

}