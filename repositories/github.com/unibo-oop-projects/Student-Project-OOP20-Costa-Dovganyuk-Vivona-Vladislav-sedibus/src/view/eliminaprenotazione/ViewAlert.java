package view.eliminaprenotazione;

import java.time.LocalDate;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import model.piantina.Prenotazione;
import model.utili.Periodo;

public final class ViewAlert {

	private final String TITOLO_ELIMINAZIONE = "Conferma Eliminazione Prenotazione";
	private final String HEADER_ELIMINAZIONE = "Confermi voler eliminare la prenotazione";
	private final String TITOLO_ELIMINAZIONE_CONFERMATA = "Eliminazione Confermata";
	private final String HEADER_ELIMINAZIONE_CONFERMATA = "Eliminazione effettuata con Successo";
	private Alert alert; 
	
	public ButtonType alertEliminazionePrenotazione(Prenotazione p, LocalDate data, Periodo periodo) {
		alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle(TITOLO_ELIMINAZIONE);
		alert.setHeaderText(HEADER_ELIMINAZIONE);
		alert.setContentText(getContenentText(p, data, periodo));
		alert.getButtonTypes().clear();
		
		alert.getButtonTypes().addAll(ButtonType.YES, ButtonType.CANCEL);
		alert.showAndWait();
		
		return alert.getResult();
	}
	
	
	private String getContenentText(Prenotazione p, LocalDate data, Periodo periodo) {
		return "Codice Prenotazione: " + p.getCodicePrenotazione() + 
				"\ndel: " + data.toString() + " per " + periodo.toString() +
				"\na nome di " + p.getCliente().getNome() + " " + p.getCliente().getCognome();
	}
	
	/**
	 * "window" called to confirm the deletion
	 */
	public void alertConfermaEliminazione() {
		alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(TITOLO_ELIMINAZIONE_CONFERMATA);
		alert.setHeaderText(HEADER_ELIMINAZIONE_CONFERMATA);
		alert.showAndWait();
		
	}
	
	/**
	 * "window" to be called in case of error
	 * @param messaggio the message to be displayed
	 */
	public void alertErrore(String messaggio) {
		alert = new Alert(AlertType.ERROR);
		alert.setHeaderText(messaggio);
		alert.showAndWait();
	}
	
}
