package controllers.riepilogo;

import java.net.URL;
import java.time.LocalDate;
import java.util.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.riepilogo.*;
import model.utili.AzioneUtente;
import model.utili.Periodo;
import model.utili.Utente;
import view.adminuser.LoaderAdminUserSelection;
import view.eccezioni.AlertEccezione;
import view.piantina.LoaderTableView;

public final class ControllerRiepilogo implements Initializable {

	@FXML private Label spazioTitolo;
	@FXML private Label spazioNome;
	@FXML private Label spazioCognome;
	@FXML private Label spazioEmail;
	@FXML private Label spazioTelefono;
	@FXML private Label spazioData;
	@FXML private Label spazioPeriodo;
	@FXML private Label spazioPosti;
	@FXML private Label spazioTavolo;
	@FXML private Label spazioCodPrenotazione;
	private ModelRiepilogo modello;
	private Utente utente;
	private AzioneUtente azione;
	private Periodo periodo;
	private LocalDate data;
	private String idTavolo;
	
	public ControllerRiepilogo(Utente utente, AzioneUtente azione, Periodo periodoPrenotato, LocalDate dataPrenotata, String idTavoloPrenotato) {
		this.utente = utente;
		this.azione = azione;
		this.periodo = periodoPrenotato;
		this.data = dataPrenotata;
		this.idTavolo = idTavoloPrenotato;
		this.modello = new ModelRiepilogoImpl(periodoPrenotato, dataPrenotata, idTavoloPrenotato);
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if(this.azione.equals(AzioneUtente.CREAZIONE)) {
			this.spazioTitolo.setText("creata");
		} else {
			this.spazioTitolo.setText("modificata");
		}
		this.riempiCampi();
	}
	
	private void riempiCampi() {
		this.spazioNome.setText(this.modello.getNome());
		this.spazioCognome.setText(this.modello.getCognome());
		this.spazioEmail.setText(this.modello.getEmail());
		this.spazioTelefono.setText(this.modello.getTelefono());
		this.spazioData.setText(this.data.toString());
		this.spazioPeriodo.setText(this.periodo.toString().toLowerCase());;
		this.spazioPosti.setText(this.modello.getPosti());;
		this.spazioTavolo.setText(this.idTavolo);;
		this.spazioCodPrenotazione.setText(this.modello.getCodice());
	}
	
	public void handlerFine() {
		try {
			if(this.utente.equals(Utente.ADMIN)) {  //se ADMIN riporta alla mappa tavoli
				final LoaderTableView piantinaTavoli = new LoaderTableView();
				LoaderTableView.loaderTableView(this.utente);
				piantinaTavoli.start(new Stage());
			} else {  //se USER riporta alla selezione admin/utente
				final LoaderAdminUserSelection viewSelectionUser = new LoaderAdminUserSelection();
				viewSelectionUser.start(new Stage());
			}
			this.spazioTitolo.getScene().getWindow().hide();
		} catch (Exception e) {
			final AlertEccezione avviso = new AlertEccezione();
	    	avviso.err();
		}
	}
	
}
