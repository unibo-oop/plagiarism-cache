package magazzino.entratamerci.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import magazzino.entratamerci.dto.InserisciOrdineModel;
import magazzino.entratamerci.models.*;
import magazzino.entratamerci.service.AreeService;
import magazzino.entratamerci.service.ArticoliService;
import magazzino.entratamerci.service.FornitoriService;
import magazzino.entratamerci.service.LocazioniService;
import magazzino.entratamerci.service.OrdiniService;
import magazzino.entratamerci.utils.ComboBoxUtility;
import magazzino.entratamerci.utils.StringFormatter;

public class InserisciOrdineController {

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnSalva;

    @FXML
    private ComboBox<String> cmbAree;

    @FXML
    private ComboBox<String> cmbArticolo;

    @FXML
    private ComboBox<String> cmbFornitore;

    @FXML
    private ComboBox<String> cmbLocazione;

    @FXML
    private TableColumn<InserisciOrdineModel,String> codiceColumn;

    @FXML
    private TableColumn<InserisciOrdineModel, String> descColumn;

    @FXML
    private TableColumn<InserisciOrdineModel, Integer> qtaColumn;

    @FXML
    private TableView<InserisciOrdineModel> gridOrdine;

    @FXML
    private Label progressivo;

    @FXML
    private Label progressivo1;

    @FXML
    private Label progressivo11;

    @FXML
    private Label progressivo12;

    @FXML
    private Label progressivo121;

    @FXML
    private TextField txtQta;

    private ObservableList<String> obsArt = FXCollections.observableArrayList();
    private ObservableList<String> obsForn = FXCollections.observableArrayList();
    private ObservableList<String> obsLocaz = FXCollections.observableArrayList();
    private ObservableList<String> obsArea = FXCollections.observableArrayList();
    ObservableList<InserisciOrdineModel> listTable;
    Alert alert = new Alert(AlertType.WARNING, "Seleziona almeno un articolo con qta maggiore di 0");
    Alert alertDuplicate = new Alert(AlertType.WARNING, "Articolo gia' presente ");
    Alert alertNoArticoli = new Alert(AlertType.WARNING, "Aggiungi degli articoli prima di salvare.");
    Alert alertObbligatori = new Alert(AlertType.WARNING, "Area,Locazione e Fornitore sono parameri obbligatori");
    Alert alertNaN = new Alert(AlertType.WARNING, "Quantita non formattata correttamente");
    Alert alertNumeroNegativo = new Alert(AlertType.WARNING, "Quantita <= 0");



    @FXML
    void addRowOrdine(ActionEvent event) {

        if(cmbArticolo.getValue() == null || cmbArticolo.getValue().isEmpty() || txtQta.getText().isEmpty()) {
            alert.show();
        }else {
            String[] codiceDescrizione = cmbArticolo.getValue().split("-");
            String codice = codiceDescrizione[0].trim();
            String descrizione = codiceDescrizione[1].trim();

            if(listTable.stream().filter(x-> x.getCodice().get().equals(codice)).findAny().isPresent()){
                alertDuplicate.show();
                return;
            }

            int qta = 0;

            try{
                qta = Integer.parseInt(txtQta.getText());
            }catch (NumberFormatException ex){
                alertNaN.show();
                return;
            }

            if(qta <= 0){
                alertNumeroNegativo.show();
                return;
            }

            listTable.add(new InserisciOrdineModel(codice, descrizione, qta));
        }
    }

    @FXML
    void saveOrdine(ActionEvent event) throws IOException {
        if(listTable.stream().count()==0) {
            alertNoArticoli.show();
            return;
        }
        if(cmbArticolo.getValue() == null || cmbFornitore.getValue() == null || cmbLocazione.getValue() == null || cmbAree.getValue() == null ) {
            alertObbligatori.show();
            return;
        }

        String fornitore= ComboBoxUtility.GetKey(cmbFornitore.getValue());
        String area= ComboBoxUtility.GetKey(cmbAree.getValue());
        String locazione = ComboBoxUtility.GetKey(cmbLocazione.getValue());

        int progressivo= new OrdiniService().getProgressivoNextOrdine();

        area objarea = new AreeService().getAreaByCodice(area);
        locazione objlocazione = new LocazioniService().getLocazioneByCodice(locazione);
        fornitore objfornitore = new FornitoriService().getFornitoreByCodice(fornitore);
        carrello c = new carrello();

        ArrayList<articolo> articoli = new ArticoliService().getArticoli();

        for (InserisciOrdineModel inserisciOrdineModel : listTable) {
            articolo art = new ArticoliService().getArticoloByCodice(articoli, inserisciOrdineModel.getCodice().get());
            c.addItem(art,inserisciOrdineModel.getQta().get());
        }

        c.changePosition(objarea, objlocazione);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        ordine ordine = new ordine( LocalDateTime.now().getYear(), progressivo, dateFormat.format(new Date()), objfornitore, c);
        new OrdiniService().addOrdine(ordine);

        Stage stage = (Stage) btnSalva.getScene().getWindow();
        stage.close();

    }

    public void initialize() {
        ArrayList<String> articoli = new ArticoliService().getArticoliNonObsoleti().stream().map(x-> ComboBoxUtility.DisplayCodiceDescrizione(x.getCodice(),x.getDescrizione())).collect(Collectors.toCollection(ArrayList::new));
        obsArt.addAll(articoli);
        cmbArticolo.setItems(obsArt);

        ArrayList<String> aree = new AreeService().getAree().stream().map(x-> ComboBoxUtility.DisplayCodiceDescrizione(x.getCodice(),x.getDescrizione())).collect(Collectors.toCollection(ArrayList::new));
        obsArea.addAll(aree);
        cmbAree.setItems(obsArea);

        ArrayList<String> fornitori = new FornitoriService().getFornitori().stream().map(x-> ComboBoxUtility.DisplayCodiceDescrizione(x.getCodice(),x.getDescrizione())).collect(Collectors.toCollection(ArrayList::new));
        obsForn.addAll(fornitori);
        cmbFornitore.setItems(obsForn);

        codiceColumn.setCellValueFactory(new PropertyValueFactory<>("codice"));
        descColumn.setCellValueFactory(new PropertyValueFactory<>("descr"));
        qtaColumn.setCellValueFactory(new PropertyValueFactory<>("qta"));

        listTable = FXCollections.observableArrayList(new ArrayList<>());
        gridOrdine.setItems(listTable);

        gridOrdine.setRowFactory(tv -> {
            TableRow<InserisciOrdineModel> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    InserisciOrdineModel rowData = row.getItem();
                    listTable.remove(rowData);
                }
            });
            return row ;
        });

    }

    @FXML
    void area_OnChange(ActionEvent event) throws IOException {
        String currarea=ComboBoxUtility.GetKey(cmbAree.getValue());
        obsLocaz.clear();
        area area = new AreeService().getAreaByCodice(currarea);
        ArrayList<locazione> locazioni =  new LocazioniService().getLocazioniByArea(area);

        ArrayList<String > locazioniCmb = locazioni.stream().map(x-> ComboBoxUtility.DisplayCodiceDescrizione(x.getCodice(),x.getDescrizione())).collect(Collectors.toCollection(ArrayList::new));
        obsLocaz.addAll(locazioniCmb);
        cmbLocazione.setItems(obsLocaz);
    }

}
