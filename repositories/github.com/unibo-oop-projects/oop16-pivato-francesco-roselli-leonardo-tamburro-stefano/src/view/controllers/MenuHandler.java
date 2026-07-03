package view.controllers;

import static view.ViewUtils.setVista;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import view.Menu;
import view.Page;

public class MenuHandler implements Menu {
	
	@FXML private BorderPane mainPane;
	@FXML private Button warehouseBtn;
    @FXML private Button usersListBtn;
    @FXML private Button addUserBtn;
    @FXML private Button administrationBtn;
    
	public MenuHandler() {}
	
	@FXML
	private void initialize(){}
    
    @FXML
    void showUsersTable() {
    	setVista(this.usersListBtn.getScene(), Page.USERS_TABLE, true);
    }

    @FXML
    void showUserProfile() {
    	setVista(this.addUserBtn.getScene(), Page.USER_PROFILE, true);
    }

    @FXML
    void showWarehouse() {
    	setVista(this.warehouseBtn.getScene(), Page.WAREHOUSE, true);
    }
    
    @FXML
    void showAdminPage() {
    	setVista(this.administrationBtn.getScene(), Page.ADMINISTRATION, true);
    }

}
