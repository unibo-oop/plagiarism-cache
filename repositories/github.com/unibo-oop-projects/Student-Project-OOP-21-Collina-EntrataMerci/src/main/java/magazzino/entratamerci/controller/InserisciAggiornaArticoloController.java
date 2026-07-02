package magazzino.entratamerci.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import magazzino.entratamerci.models.articolo;
import magazzino.entratamerci.service.ArticoliService;

import java.io.IOException;
import java.util.ArrayList;

public class InserisciAggiornaArticoloController {

    @FXML
    private Button btnSalva;

    @FXML
    private TextField codiceArticolo;

    @FXML
    private TextField descrizioneArticolo;

    @FXML
    private CheckBox isObsoleto;

    private articolo articolo;

    private Alert alertArticolo = new Alert(Alert.AlertType.WARNING, "Articolo con codice già presente in anagrafica");
    private Alert alertDatiObbligatori = new Alert(Alert.AlertType.WARNING, "Il codice e la descrizione sono campi obbligatori");

    @FXML
    void salvaArticolo(ActionEvent event) throws IOException {
        ArticoliService service = new ArticoliService();
        ArrayList<articolo> articoli = service.getArticoli();

        if(codiceArticolo.getText().isEmpty() || descrizioneArticolo.getText().isEmpty()){
            alertDatiObbligatori.show();
            return;
        }

        if(articolo == null){
            if(service.isArticoloEsistente(articoli, codiceArticolo.getText().trim())){
                alertArticolo.show();
                return;
            }

            articoli.add(new articolo(codiceArticolo.getText().trim(), descrizioneArticolo.getText().trim(), isObsoleto.isSelected()));
        }else {
            articoli.get(articoli.indexOf(articolo)).setDescrizione(descrizioneArticolo.getText().trim());
            articoli.get(articoli.indexOf(articolo)).setObsoleto(isObsoleto.isSelected());
        }

        service.setArticoli(articoli);
        Stage stage = (Stage) btnSalva.getScene().getWindow();
        stage.close();
    }

    public void initialize(){

    }

    public void initData(articolo articolo){
        this.articolo = articolo;

        codiceArticolo.setText(articolo.getCodice());
        codiceArticolo.setEditable(false);
        isObsoleto.setSelected(articolo.isObsoleto());
        descrizioneArticolo.setText(articolo.getDescrizione());
    }
}
