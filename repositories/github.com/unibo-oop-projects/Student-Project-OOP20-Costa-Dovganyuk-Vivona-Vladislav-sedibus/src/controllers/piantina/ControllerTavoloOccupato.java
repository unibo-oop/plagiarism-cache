package controllers.piantina;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.piantina.ImplMainTableModel;
import model.piantina.ImplRistorante;
import model.piantina.MainTableModel;
import model.piantina.Prenotazione;
import model.piantina.Ristorante;
import model.utili.AzioneUtente;
import model.utili.Cliente;
import model.utili.Periodo;
import model.utili.Utente;
import view.creaprenotazione.LoaderPrenotazione;
import view.eccezioni.AlertEccezione;
import view.eliminaprenotazione.ViewAlert;
import view.piantina.LoaderTableView;


public final class ControllerTavoloOccupato implements Initializable  {

	@FXML private Text textCodice;
	@FXML private Text textData;
	@FXML private Text textPeriodo;
	private Stage stageMappaTavoli;
	private MainTableModel model = null;
	private final Ristorante ristorante = new ImplRistorante();
	private final ViewAlert alert = new ViewAlert();
	
	private Cliente cliente;
	private Prenotazione prenotazione;
	private String codicePrenotazione;
	private Periodo periodo;
	private LocalDate data;
	private int idTavolo;
	private Utente utente;
	private AzioneUtente azione;
	
	public ControllerTavoloOccupato(Stage stageMappaTavoli) {
		this.stageMappaTavoli = stageMappaTavoli;
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rsrc) {
		this.utente = Utente.ADMIN;
	}

	
	public void handlerModifica() {
		
		this.azione = AzioneUtente.MODIFICA;
		if(this.model == null) {
			setModel();
		}
		
		final LoaderPrenotazione viewPrenotazione = new LoaderPrenotazione(this.utente, this.azione, 
				this.cliente.getNome(), this.cliente.getCognome(), 
				this.cliente.getEmail(), this.cliente.getTelefono(),
				data, periodo, String.valueOf(prenotazione.getPostiPrenotati()) , String.valueOf(this.idTavolo) , this.codicePrenotazione);
		
		try {
			viewPrenotazione.start(new Stage());
		} catch (Exception e) {
			this.mostraErrore();
		}
		closeCurrentStage();
		this.stageMappaTavoli.close();
		
	}
	
	public void handlerElimina() {
		
		if(this.model == null) {
			setModel();
		}
		
		if(alert.alertEliminazionePrenotazione(prenotazione, data, periodo).equals(ButtonType.YES)) {
			if(ristorante.eliminaPrenotazione(periodo, codicePrenotazione, this.cliente.getCognome())) {
				//chiudo lo stage corrente
				closeCurrentStage();
				//messaggio di conferma eliminazione
				alert.alertConfermaEliminazione();
				//riapertura nuovo stage mappa Tavoli
				openCloseMappaTavoli();
			}
			
		}
		
	}
	
	public void handlerAnnulla() {
		closeCurrentStage();
	}
	
	//chiude la vecchia mappa tavoli e apre quella nuova aggiornata
	private void openCloseMappaTavoli() {
		final LoaderTableView mappaTavoli = new LoaderTableView();
		LoaderTableView.loaderTableView(Utente.ADMIN);
		try {
			this.stageMappaTavoli.close();
			mappaTavoli.start(new Stage());
		} catch (Exception e) {
			this.mostraErrore();
		}
	}
	
	private void closeCurrentStage() {
		final var stage = (Stage) this.textCodice.getScene().getWindow();
		stage.close();
	}
	
	
	private void setModel() {
		this.periodo = Periodo.valueOf(this.textPeriodo.getText());
		this.data = LocalDate.parse(this.textData.getText());
		
		this.model = new ImplMainTableModel(this.periodo,this.data);
		this.codicePrenotazione = this.textCodice.getText();
		this.idTavolo = this.model.getIdTavolo(this.codicePrenotazione);	
		this.prenotazione = this.model.getPrenotazione(this.codicePrenotazione);
		this.cliente = this.prenotazione.getCliente();          
	}
	
	private void mostraErrore() {
		final AlertEccezione avviso = new AlertEccezione();
    	avviso.err();
	}
	
}
