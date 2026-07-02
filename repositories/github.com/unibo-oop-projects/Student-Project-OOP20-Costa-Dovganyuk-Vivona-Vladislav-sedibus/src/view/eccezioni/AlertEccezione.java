package view.eccezioni;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public final class AlertEccezione {

	private static final String TITOLO_ERRORE = "ERRORE";
	private static final String MEX_ERRORE = "Attenzione si e\' verificato un errore";
	private Alert alert;
	
	/**
	 * shows the exception to the user
	 */
	public void err() {
		this.alert = new Alert(AlertType.ERROR);
		this.alert.setTitle(TITOLO_ERRORE);
		this.alert.setResizable(false);
		this.alert.setHeaderText(MEX_ERRORE);
		this.alert.showAndWait();
	}
	
}
