package view.controllers;

import static view.ViewHandler.getObserver;
import static view.ViewUtils.setVista;

import java.util.List;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import model.interfaces.User;
import view.Page;
import view.UsersTable;
import view.Vista;

public class UsersTableHandler extends Vista implements UsersTable {

    @FXML private TableView<User> usersTable = new TableView<>();   
    @FXML private TableColumn<User, String> firstName;
    @FXML private TableColumn<User, String> lastName;
    @FXML private TableColumn<User, String> birthDate;
    @FXML private TableColumn<User, String> taxCode;
    @FXML private TableColumn<User, String> endValidity;
    @FXML private TableColumn<User, String> startValidity;
    @FXML private TableColumn<User, String> phoneNumber;
    @FXML private TableColumn<User, String> subscriptionType;
    @FXML private TableColumn<User, String> paymentDate;
    @FXML private TableColumn<User, String> email;
    @FXML private Button backBtn;
  
    
    public UsersTableHandler() {}
    
    @Override
    public void setData(final List<User> data) {
    	this.usersTable.setItems(FXCollections.observableArrayList(data));
    }
    
    @FXML
    private void goBack(){
    	super.goBack(this.backBtn.getScene());
    }
    
	@FXML
	private void copyRow(MouseEvent event) {
		if (event.getClickCount() == 2) {
			User selectedUser = usersTable.getSelectionModel().getSelectedItem();
			if(selectedUser != null) {
				setVista(this.usersTable.getScene(), Page.USER_PROFILE, true);
				getObserver().modifyUserData(selectedUser);
			}
		}
	}

	@FXML
	private void initialize() {
		this.firstName.setCellValueFactory(c -> new ReadOnlyStringWrapper(c.getValue().getPerson().getFirstName()));
		this.lastName.setCellValueFactory(c -> new ReadOnlyStringWrapper(c.getValue().getPerson().getLastName()));
		this.birthDate.setCellValueFactory(c -> new ReadOnlyStringWrapper(c.getValue().getPerson().getBirthday().toString()));
		this.taxCode.setCellValueFactory(c -> new ReadOnlyStringWrapper(c.getValue().getPerson().getTaxCode()));
		this.endValidity.setCellValueFactory(c -> new ReadOnlyStringWrapper(c.getValue().getSubscription().getSigningDate().toString()));
		this.startValidity.setCellValueFactory(c -> new ReadOnlyStringWrapper(c.getValue().getSubscription().getExpirationDate().toString()));
		this.phoneNumber.setCellValueFactory(c -> new ReadOnlyStringWrapper(c.getValue().getPerson().getTelephonNumber()));
		this.subscriptionType.setCellValueFactory(c -> new ReadOnlyStringWrapper(c.getValue().getSubscription().getType().toString()));
		this.paymentDate.setCellValueFactory(c -> new ReadOnlyStringWrapper(c.getValue().getSubscription().getPaymentDate().toString()));
		this.email.setCellValueFactory(c -> new ReadOnlyStringWrapper(c.getValue().getPerson().getEmail()));
		
		getObserver().populateUsersTable();
	}

}