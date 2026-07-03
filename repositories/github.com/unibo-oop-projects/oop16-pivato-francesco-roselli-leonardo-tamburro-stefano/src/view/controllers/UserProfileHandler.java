package view.controllers;

import static view.ViewHandler.getObserver;
import static view.ViewUtils.setVista;

import java.time.LocalDate;
import java.util.Arrays;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import model.enumerations.Status;
import model.enumerations.SubscriptionType;
import model.interfaces.User;
import view.Page;
import view.UserProfile;
import view.ViewUtils;
import view.Vista;


public class UserProfileHandler extends Vista implements UserProfile {
	
	private User currentUser;
	
	@FXML private BorderPane mainPane;
	@FXML private Label nickName;
	@FXML private Button loadImageBtn;
	@FXML private Button deleteImageBtn;
    @FXML private Button showTableBtn;
    @FXML private Button usersListBtn;
    @FXML private Button saveBtn;
    @FXML private Button deleteBtn;
    @FXML private Button backBtn;
    @FXML private TextField nameField;
    @FXML private DatePicker birthdateField;
    @FXML private TextField emailField;
    @FXML private DatePicker paymentDateField;
    @FXML private ImageView profileImage;
    @FXML private TextField phoneField;
    @FXML private TextField surnameField;
    @FXML private TextField cfField;
    @FXML private DatePicker expirationDateField;
    @FXML private DatePicker signinDateField;
    @FXML private ChoiceBox<SubscriptionType> subscriptionType;
    
    public UserProfileHandler() {}
    
    @Override
    public void setFields(User user) {
    	this.currentUser = user;
    	this.nameField.setText(user.getPerson().getFirstName());
    	this.surnameField.setText(user.getPerson().getLastName());
    	this.birthdateField.setValue(user.getPerson().getBirthday());;
    	this.phoneField.setText(user.getPerson().getTelephonNumber());
    	this.emailField.setText(user.getPerson().getEmail());
    	this.cfField.setText(user.getPerson().getTaxCode());
    	this.subscriptionType.setValue(user.getSubscription().getType());
    	this.signinDateField.setValue(user.getSubscription().getSigningDate());
    	this.expirationDateField.setValue(user.getSubscription().getExpirationDate());
    	this.paymentDateField.setValue(user.getSubscription().getPaymentDate());
    	this.profileImage.setImage(user.getPerson().getImageProfile());
    }

	@Override
	public String getName() {
		return this.nameField.getText();
	}

	@Override
	public String getSurname() {
		return this.surnameField.getText();
	}

	@Override
	public LocalDate getBirthdate() {
		return this.birthdateField.getValue();
	}

	@Override
	public String getPhoneNumber() {
		return this.phoneField.getText();
	}

	@Override
	public String getEmail() {
		return this.emailField.getText();
	}

	@Override
	public String getTaxCode() {
		return this.cfField.getText();
	}
	
	@Override
	public Image getProfileImage() {
		return this.profileImage.getImage();
	}

	@Override
	public SubscriptionType getSubscriptionType() {
		return this.subscriptionType.getValue();
	}

	@Override
	public LocalDate getValidFrom() {
		return this.signinDateField.getValue();
	}

	@Override
	public LocalDate getValidTo() {
		return this.expirationDateField.getValue();
	}

	@Override
	public LocalDate getPaymentDate() {
		return this.paymentDateField.getValue();
	}
	
	@FXML
	private void initialize(){		
		ObservableList<SubscriptionType> list = FXCollections.observableArrayList();
    	Arrays.stream(SubscriptionType.values()).forEach(v -> list.add(v));
    	this.subscriptionType.getItems().addAll(list);
    	this.subscriptionType.setValue(SubscriptionType.SELECT_SUBSCRIPTION);
	}

	@FXML
	private void loadImage() {
		Image image = getObserver().getProfileImage(this.profileImage.getScene());
		this.profileImage.setImage(image);
    }

    @FXML
    private void deleteImage() {
    	Image image = getObserver().deleteProfileImage();
    	this.profileImage.setImage(image);
    }

    @FXML
    private void viewWorkoutTable() {
    	if (this.currentUser != null) {
    		setVista(this.showTableBtn.getScene(), Page.WORKOUT_TABLE, true);
    		getObserver().populateWorkoutTable(this.currentUser);
    	}else {
    		ViewUtils.showAlertMessage("Warning", "The current user must be created and saved first");
    	}
    }
    
    @FXML
    private void showUsersList() {
    	setVista(this.usersListBtn.getScene(), Page.USERS_TABLE, true);
    }

    @FXML
    private void save() {
    	Status status = getObserver().saveUserData();
    	if(status != Status.ALL_RIGHT){
    		ViewUtils.showAlertMessage(status.name(), status.getText());
    	}else {
    		super.goBack(this.backBtn.getScene());
    	}
    }


    @FXML
    private void delete() {
    	Alert alert = new Alert(AlertType.WARNING);
		alert.setContentText("Sei sicuro di voler eliminare permanentemente questo utente e tutti i relativi dati");
		alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.CANCEL);
		alert.showAndWait()
	     	.filter(response -> response == ButtonType.YES)
	     	.ifPresent(response -> {
	     		if (this.currentUser != null) {
	     			getObserver().deleteUserData(currentUser);
		     		super.goBack(this.backBtn.getScene());
	     		}
	     	});
    }

    @FXML
    private void goBack() {
    	super.goBack(this.backBtn.getScene());
    }
    
}
