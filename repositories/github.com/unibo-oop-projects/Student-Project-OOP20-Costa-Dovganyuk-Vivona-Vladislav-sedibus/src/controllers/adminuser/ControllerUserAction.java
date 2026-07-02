package controllers.adminuser;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.utili.Utente;
import view.adminuser.LoaderAdminUserSelection;
import view.cercaprenotazione.LoaderCercaPrenotazione;
import view.eccezioni.AlertEccezione;
import view.piantina.LoaderTableView;
import model.utili.AzioneUtente;

public final class ControllerUserAction {
	
	private static Utente utente;
	private static AzioneUtente azioneUtente;
	
	@FXML
	private Button prenota;
	
	@FXML
	 public void loadTableViewUser(ActionEvent event) {

		utente=Utente.USER;
		
		final LoaderTableView view= new LoaderTableView();
   	    final Stage currentStage = (Stage) this.prenota.getScene().getWindow();
   	    LoaderTableView.loaderTableView(utente);
		try { 
			 view.start(new Stage());
			   currentStage.close();
	    } catch (Exception e) {
	    	this.errore();
	    }
     }
	
	@FXML
	public void loadModificaPrenotazione (ActionEvent event){
		
		azioneUtente=AzioneUtente.MODIFICA;
		
		final Stage currentStage = (Stage) this.prenota.getScene().getWindow();
		final LoaderCercaPrenotazione view = new LoaderCercaPrenotazione();
		LoaderCercaPrenotazione.loadCercaPrenotazione(azioneUtente);
		try {
			view.start(new Stage());
			currentStage.close();
		} catch (Exception e) {
			this.errore();
		}
	}
	
	@FXML
	public void loadCancellaPrenotazione (ActionEvent event){
		
		azioneUtente=AzioneUtente.CANCELLA;
		
		final Stage currentStage = (Stage) this.prenota.getScene().getWindow();
		final LoaderCercaPrenotazione view = new LoaderCercaPrenotazione();
		LoaderCercaPrenotazione.loadCercaPrenotazione(azioneUtente);
		try {
			view.start(new Stage());
			currentStage.close();
		} catch (Exception e) {
			this.errore();
		}
	}
	
	@FXML
	public void goBack (ActionEvent Event) {
		
		final Stage currentStage = (Stage) this.prenota.getScene().getWindow();
		final LoaderAdminUserSelection view= new LoaderAdminUserSelection();
		
		try {
			view.start(new Stage());
			currentStage.close();
		} catch (Exception e) {
			this.errore();
		}
	}
	
	private void errore() {
		final AlertEccezione avviso = new AlertEccezione();
		avviso.err();
	}
	
}