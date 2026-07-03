package view.controllers;

import static view.ViewHandler.getObserver;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import model.enumerations.Status;
import model.interfaces.Product;
import view.ProductDetails;
import view.ViewUtils;
import view.Vista;

public class ProductDetailsHandler extends Vista implements ProductDetails {
	
	private Product selectedProduct;
	
	@FXML private Button minus;
    @FXML private TextField quantity;
    @FXML private TextField price;
    @FXML private TextField name;
    @FXML private Button save;
    @FXML private TextArea description;
    @FXML private Button back;
    @FXML private TextField id;
    @FXML private TextField category;
    @FXML private Button delete;
    @FXML private Button plus;
    
    @FXML
    private void initialize() {
    	this.quantity.setText("0");
    }

    @FXML
    void add() {
    	this.quantity.setText( String.valueOf(Integer.parseInt(this.quantity.getText()) + 1) );
    }

    @FXML
    void remove() {
    	this.quantity.setText( String.valueOf(Integer.parseInt(this.quantity.getText()) - 1) );
    }

    @FXML
    void saveItem() {
    	Status status = getObserver().saveProductData();
        if(status != Status.ALL_RIGHT){
        	ViewUtils.showAlertMessage(status.name(), status.getText());
        }else {
        	super.goBack(this.back.getScene());
        }
    }

    @FXML
    void deleteItem() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setContentText("Sei sicuro di voler eliminare permanentemente questo utente e tutti i relativi dati");
		alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.CANCEL);
		alert.showAndWait()
	     	.filter(response -> response == ButtonType.YES)
	     	.ifPresent(response -> {
	     		if (this.selectedProduct != null) {
	     			getObserver().deleteProductData(this.selectedProduct);
	        		super.goBack(this.back.getScene());
	     		}else {
	     			ViewUtils.showAlertMessage("Warning", "No product has been selected");
	     		}
	     	});
    	
    }

    @FXML
    void goBack() {
    	super.goBack(this.back.getScene());
    }

	@Override
	public String getId() {
		return this.id.getText();
	}
	
	@Override
	public String getName() {
		return this.name.getText();
	}

	@Override
	public String getCategory() {
		return this.category.getText();
	}

	@Override
	public int getQuantity() {
		return Integer.parseInt(this.quantity.getText());
	}

	@Override
	public double getPrice() {
		return Double.parseDouble(this.price.getText());
	}

	@Override
	public String getDescription() {
		return this.description.getText();
	}

	@Override
	public void setData(Product product) {
		this.selectedProduct = product;
		this.id.setText(product.getProductCode());
		this.name.setText(product.getName());
		this.category.setText(product.getCategory());
		this.quantity.setText(String.valueOf(product.getQuantity()));
		this.price.setText(String.valueOf(product.getPrice()));
		this.description.setText(product.getDescription());
		this.id.setDisable(true);
		this.name.setDisable(true);
		this.category.setDisable(true);
	}

}
