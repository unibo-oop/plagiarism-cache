package magazzino.entratamerci.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import magazzino.entratamerci.models.fornitore;
import magazzino.entratamerci.service.FornitoriService;

import java.io.IOException;
import java.util.ArrayList;

public class InserisciAggiornaFornitoreController {

    @FXML
    private Button btnSalva;

    @FXML
    private TextField codiceFornitore;

    @FXML
    private TextField descrizioneFornitore;
    @FXML
    private TextField nomeFornitore;

    @FXML
    private TextField cognomeFornitore;
    @FXML
    private CheckBox isObsoleto;

    private fornitore Fornitore;

    private Alert alertFornitore = new Alert(Alert.AlertType.WARNING, "Fornitore con codice già presente in anagrafica");
    private Alert alertDatiObbligatori = new Alert(Alert.AlertType.WARNING, "Il codice e la descrizione sono campi obbligatori");

    @FXML
    void salvaFornitore(ActionEvent event) throws IOException {
        FornitoriService service = new FornitoriService();
        ArrayList<fornitore> fornitori = service.getFornitori();

        if(codiceFornitore.getText().isEmpty() || descrizioneFornitore.getText().isEmpty()){
            alertDatiObbligatori.show();
            return;
        }

        if(Fornitore == null){
            if(service.isFornitoreEsistente(fornitori, codiceFornitore.getText().trim())){
                alertFornitore.show();
                return;
            }

            fornitori.add(new fornitore(codiceFornitore.getText().trim(), descrizioneFornitore.getText().trim(),codiceFornitore.getText().trim(),codiceFornitore.getText().trim()));
        }else {
            fornitori.get(fornitori.indexOf(Fornitore)).setDescrizione(descrizioneFornitore.getText().trim());
            fornitori.get(fornitori.indexOf(Fornitore)).setNome(nomeFornitore.getText());
            fornitori.get(fornitori.indexOf(Fornitore)).setCognome(cognomeFornitore.getText());
        }

        service.setFornitori(fornitori);
        Stage stage = (Stage) btnSalva.getScene().getWindow();
        stage.close();
    }

    public void initialize(){

    }

    public void initData(fornitore Fornitore){
        this.Fornitore = Fornitore;

        codiceFornitore.setText(Fornitore.getCodice());
        codiceFornitore.setEditable(false);
        descrizioneFornitore.setText(Fornitore.getDescrizione());
        nomeFornitore.setText(Fornitore.getNome());
        cognomeFornitore.setText(Fornitore.getCognome());    }
}
