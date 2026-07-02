package controllers.adminuser;

import javafx.fxml.FXML;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import view.adminuser.LoaderAdminLogin;
import view.adminuser.LoaderUserAction;
import view.eccezioni.AlertEccezione;
import javafx.scene.control.Button;

public final class ControllerAdminUserSelection  {
	
	@FXML
	private Button Admin;
	
	@FXML
	public void loadAdminLogin (ActionEvent event) {
		final LoaderAdminLogin view = new LoaderAdminLogin();
    	final Stage currentStage = (Stage) this.Admin.getScene().getWindow();
    	try { 
    		view.start(new Stage());
    		currentStage.close();
	    } catch (Exception e) {
	    	final AlertEccezione avviso = new AlertEccezione();
			avviso.err();
	    }
	}
	
	@FXML
	public void loadUserDecision (ActionEvent event) {
		final LoaderUserAction view = new LoaderUserAction();
    	final Stage currentStage = (Stage) this.Admin.getScene().getWindow();
		try {
			view.start(new Stage());
			currentStage.close();
	    } catch (Exception e) {
	    	final AlertEccezione avviso = new AlertEccezione();
			avviso.err();
	    }
	}
	
	@FXML
	public void applicationStop (ActionEvent event) {
		
		Platform.exit();
	}
}
	
	
	