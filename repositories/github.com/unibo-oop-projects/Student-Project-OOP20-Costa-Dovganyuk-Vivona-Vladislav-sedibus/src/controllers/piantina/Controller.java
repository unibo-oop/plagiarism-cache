package controllers.piantina;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.piantina.ImplMainTableModel;
import model.piantina.MainTableModel;
import model.utili.Periodo;
import model.utili.Utente;
import view.adminuser.LoaderAdminUserSelection;
import view.eccezioni.AlertEccezione;
import view.piantina.LoaderTavoloRossoOccupato;
import view.piantina.LoaderTavoloVerdeLibero;

public final class Controller implements Initializable {

	private static final String TAVOLO_VERDE_STYLE_PATH = "layouts/tavoloLibero.css";
	private static final String TAVOLO_ROSSO_STYLE_PATH = "layouts/tavoloOccupato.css";
	private static final String ORARIO_PRANZO = "12.00 - 13.30";
	private static final String ORARIO_CENA = "18.00 - 19.30";
	
	@FXML private AnchorPane panePrincipale;
	@FXML private DatePicker datePicker;
	@FXML private ChoiceBox<Periodo> periodBox;
	@FXML private AnchorPane paneTavoli;
	@FXML private AnchorPane paneData;
	@FXML private Text testoUtente;
	@FXML private Text testoOrario;
	
	private LocalDate localDate = LocalDate.now() ;
	private final List<Button> listButton = new ArrayList<>();
	private final List<Button> listRedButton = new ArrayList<>();
	private MainTableModel model;
	private Periodo periodo;
	private boolean primaChiamata = true;
	//boolean per la visualizzazione dei tavoli ROSSI
	
	
	public Controller() {}
	
	
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	setPeriodBox();
    	this.datePicker.setValue(localDate);
    	getIDTavoliPrenotati(this.localDate, this.periodo);
    	handlerTavolo();
    	
    }
    
    
    private boolean isAdmin() {
    	if(Utente.valueOf(this.testoUtente.getText()).equals(Utente.ADMIN)) {
    		return true;
    	}else {
    		return false;
    	}
    }
    
    private Utente getUtente() {
    	return isAdmin() == true ? Utente.ADMIN : Utente.USER;
    }
    
    private void coloraTavoli(List<Integer> l) {
    	
    	this.listRedButton.clear();
    	this.paneTavoli.getChildren().forEach(e -> {
        	final Button b = (Button)e;
        	
        	b.getStylesheets().clear();
        	
        	
        	if(l.contains(Integer.parseInt(b.getId()))) {
        		b.getStylesheets().add(TAVOLO_ROSSO_STYLE_PATH);
        		
        		this.listRedButton.add(b);
        		
        		
        	}else {
        		b.setDisable(false);
        		b.getStylesheets().add(TAVOLO_VERDE_STYLE_PATH);
        	}
        	
        	this.listButton.add(b);
        });
    }
    
    private void handlerTavolo() {
    	
    	this.listButton.forEach(b -> {

    		b.setOnAction(e -> {
    			final var currentStage = (Stage) this.datePicker.getScene().getWindow();
    			if(this.listRedButton.contains(b) && isAdmin()) {
    				//apro la view dell'admin
    				final int idTavolo = Integer.parseInt(b.getId());
    				final LoaderTavoloRossoOccupato tavoloRossoView = new LoaderTavoloRossoOccupato(b.getId(),
    						this.model.getCodicePrenotazione(idTavolo), 
    						this.model.getCognomeNomeCliente(idTavolo),
    						this.model.getPostiPrenotati(idTavolo),
    						this.model.getNumTelefonoCliente(idTavolo),
    						this.model.getEmailCliente(idTavolo), this.periodo, this.localDate,currentStage);
    				try {
						tavoloRossoView.start(new Stage());
					} catch (Exception exception) {
						this.mostraErrore();
					}
    			}else{
    				if(this.listRedButton.contains(b)) {
    					
    				}else {
    					
    					final LoaderTavoloVerdeLibero tavoloVerdeView = new LoaderTavoloVerdeLibero(getUtente(),b.getId(),this.periodo,this.localDate,currentStage);
        				try {
    						tavoloVerdeView.start(new Stage());
    					} catch (Exception exception) {
    						this.mostraErrore();
    					}
    				}
    				
    			}
    			
    		});
    	});
    	
    }
    
    public void topMenuHandler() {
    	if(this.primaChiamata) {
    		this.primaChiamata = false;
    	}else {
    		
    		this.periodo = this.periodBox.getValue();
	    	this.localDate = this.datePicker.getValue() == null ? LocalDate.now() : this.datePicker.getValue();
	    	getIDTavoliPrenotati(localDate, periodo);
	    	setOrario(this.periodo.equals(Periodo.PRANZO)? ORARIO_PRANZO : ORARIO_CENA);
    	}
    	
    }
    
    private void setPeriodBox() {
    	this.periodBox.getItems().addAll(Periodo.PRANZO, Periodo.CENA);
    	this.periodBox.setValue(Periodo.PRANZO);
    	this.periodo = this.periodBox.getValue();
    	setOrario(this.periodo.equals(Periodo.PRANZO)? ORARIO_PRANZO : ORARIO_CENA);
    }
    
    
    
    private void getIDTavoliPrenotati(LocalDate data, Periodo p) {
    	model = new ImplMainTableModel(p,data);   		
    	coloraTavoli(this.model.tavoliPrenotati(localDate, periodo));	

    }
    
    
    public void tornaIndietroHandler() {
    	final LoaderAdminUserSelection view = new LoaderAdminUserSelection();
    	final Stage currentStage = (Stage) this.datePicker.getScene().getWindow();
    	try {
			view.start(new Stage());
			currentStage.close();
		} catch (Exception e) {
			this.mostraErrore();
		}
    }
    
    private void setOrario(String testo) {
    	this.testoOrario.setText(testo);
    }
    
    private void mostraErrore() {
    	final AlertEccezione avviso = new AlertEccezione();
    	avviso.err();
    }
    
}