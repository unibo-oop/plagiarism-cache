package view.creaprenotazione;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.time.LocalDate;
import controllers.creaprenotazione.ControllerPrenotazione;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.utili.AzioneUtente;
import model.utili.Periodo;
import model.utili.Utente;

public final class LoaderPrenotazione extends Application {

	private static final String PERC_SCENA = "/layouts/ScenePrenotazione.fxml";
	private AzioneUtente azione;
	private Utente utente;
	private DatePicker testoData;
	private LocalDate data;
	private ChoiceBox<Periodo> testoPeriodo;
	private Periodo periodo;
	private String idTavolo;
	private String vecchioNome;
	private TextField testoNome;
	private String vecchioCognome;
	private TextField testoCognome;
	private String vecchiaEmail;
	private TextField testoEmail;
	private String vecchioTelefono;
	private TextField testoTelefono;
	private String vecchiPosti;
	private Label testoPosti;
	private String codicePrenotazione;
	
	public LoaderPrenotazione(Utente utente, AzioneUtente azione, Periodo periodo, LocalDate data, String idTavolo) {
		this.utente = utente;
		this.azione = azione;
		this.data = data;
		this.periodo = periodo;
		this.idTavolo = idTavolo;
	}
	
	public LoaderPrenotazione(Utente utente, AzioneUtente azione, String nome, String cognome, 
			String email, String tel, LocalDate data, Periodo periodo, String nPosti, String idTavolo, String codPrenot) {
		this(utente, azione, periodo, data, idTavolo);
		this.vecchioNome = nome;
		this.vecchioCognome = cognome;
		this.vecchiaEmail = email;
		this.vecchioTelefono = tel;
		this.vecchiPosti = nPosti;
		this.codicePrenotazione = codPrenot;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void start(Stage scenaPrimaria) throws Exception {
		final FXMLLoader caricatore = new FXMLLoader(getClass().getResource(PERC_SCENA));
		final ControllerPrenotazione crea = new ControllerPrenotazione(this.utente, this.azione, 
			this.idTavolo, this.codicePrenotazione, this.data, this.periodo, this.vecchiPosti);
		caricatore.setController(crea);
		final Parent radice = caricatore.load();
		final Dimension dimSchermo = Toolkit.getDefaultToolkit().getScreenSize();
		final double larghezza = dimSchermo.getWidth() / scenaPrimaria.getWidth();
		final double altezza = dimSchermo.getHeight() / scenaPrimaria.getHeight();
		final Scene miaScena = new Scene(radice, larghezza, altezza);
		scenaPrimaria.setScene(miaScena);
		scenaPrimaria.setResizable(false);
		
		this.testoData = (DatePicker) caricatore.getNamespace().get("testoData");
		this.testoData.setValue(this.data);
		this.testoData.getEditor().setDisable(true);
		
		this.testoPeriodo = (ChoiceBox<Periodo>) caricatore.getNamespace().get("testoPeriodo");
		this.testoPeriodo.setValue(this.periodo);
		this.testoPeriodo.getItems().addAll(Periodo.PRANZO, Periodo.CENA);
		
		if(this.azione.equals(AzioneUtente.MODIFICA)) {
			this.testoNome = (TextField) caricatore.getNamespace().get("testoNome");
			this.testoNome.setText(this.vecchioNome);
			this.testoCognome = (TextField) caricatore.getNamespace().get("testoCognome");
			this.testoCognome.setText(this.vecchioCognome);
			this.testoEmail = (TextField) caricatore.getNamespace().get("testoEmail");
			this.testoEmail.setText(this.vecchiaEmail);
			this.testoTelefono = (TextField) caricatore.getNamespace().get("testoTelefono");
			this.testoTelefono.setText(this.vecchioTelefono);
			this.testoPosti = (Label) caricatore.getNamespace().get("testoPosti");
			this.testoPosti.setText(this.vecchiPosti);
		} else {
			this.testoPeriodo.setDisable(true);
			this.testoData.setDisable(true);
		}
		
		scenaPrimaria.show();
	}

}
