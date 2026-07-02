package controllers.adminuser;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.utili.Utente;
import view.adminuser.LoaderAdminUserSelection;
import view.eccezioni.AlertEccezione;
import view.piantina.LoaderTableView;


public final class ControllerAdminLogin  {
	
	@FXML
	private TextField user;
	@FXML 
	private PasswordField pass;
	@FXML
	private Label failedLabel;
	private static Utente utente;

	/**
	 * loadTableViewAdmin compares the username and password entered by the user with those present in the logindata.json file.
	 * In case of success, the view of the tables is loaded passing the User Enum set on admin as the argument
	 */
	 @FXML
	 public void loadTableViewAdmin(ActionEvent event) {
		 
	  if (pass.getText().equals(model.utili.Admin.getPassword()) && user.getText().equals(model.utili.Admin.getUser()))//confronto i dati inseriti dall'utente con quelli sul file logindata.json usando i metodi della model admin
	  {	
		utente=Utente.ADMIN;//imposto il tipo di utente su amministratore
		
		final LoaderTableView view = new LoaderTableView();
    	final Stage currentStage = (Stage) this.user.getScene().getWindow();
    	LoaderTableView.loaderTableView(utente);
		try {
			view.start(new Stage());
			currentStage.close();
	     } catch (Exception e) {
	    	 final AlertEccezione avviso = new AlertEccezione();
	    	 avviso.err();
	     }
	
	  }
	  
	  else 
		  failedLabel.setText("Nome Utente o Password Errati");
		  
	 }
     
	 
	  
	 @FXML
	 public void goBack (ActionEvent event){
		 
		    final LoaderAdminUserSelection view= new LoaderAdminUserSelection();
	        final Stage currentStage = (Stage) this.user.getScene().getWindow();
	  
			try {
				view.start(new Stage());
				currentStage.close();
		     } catch (Exception e) {
		    	 final AlertEccezione avviso = new AlertEccezione();
		    	 avviso.err();
		     }
	 }

}	
    
	
