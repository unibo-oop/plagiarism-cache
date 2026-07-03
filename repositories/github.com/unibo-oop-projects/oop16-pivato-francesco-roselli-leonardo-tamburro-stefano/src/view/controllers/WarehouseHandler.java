package view.controllers;

import static view.ViewHandler.getObserver;
import static view.ViewUtils.setVista;

import java.util.List;

import javafx.beans.property.ReadOnlyDoubleWrapper;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import model.interfaces.Product;
import view.Page;
import view.Vista;
import view.Warehouse;

public class WarehouseHandler extends Vista implements Warehouse {
	
	@FXML private Button backBtn;
	@FXML private Button addProductBtn;
	//@FXML private Button serachBtn;
    //@FXML private TextField searchField;
    //@FXML private MenuButton filter1;
    //@FXML private MenuButton filter2;
    @FXML private TableView<Product> table;
    @FXML private TableColumn<Product, String> productIdCol;
    @FXML private TableColumn<Product, String> productNameCol;
    @FXML private TableColumn<Product, String> categoryCol;
    @FXML private TableColumn<Product, Number> quantityCol;
    @FXML private TableColumn<Product, Number> priceCol;
    @FXML private TableColumn<Product, String> descriptionCol;
    
    public WarehouseHandler() {}
    
	@FXML
	private void initialize() {
		this.productIdCol.setCellValueFactory(c -> new ReadOnlyStringWrapper(c.getValue().getProductCode()));
		this.productNameCol.setCellValueFactory(c -> new ReadOnlyStringWrapper(c.getValue().getName()));
		this.categoryCol.setCellValueFactory(c -> new ReadOnlyStringWrapper(c.getValue().getCategory()));
		this.quantityCol.setCellValueFactory(c -> new ReadOnlyIntegerWrapper(c.getValue().getQuantity()));
		this.priceCol.setCellValueFactory(c -> new ReadOnlyDoubleWrapper(c.getValue().getPrice()));
		this.descriptionCol.setCellValueFactory(c -> new ReadOnlyStringWrapper(c.getValue().getDescription()));
		
		// finita l'inizializzazione popolo la tabella
		getObserver().populateProductTable();
	}
    
    @FXML
    private void back() {
    	super.goBack(this.backBtn.getScene());
    }
    
    @FXML
	private void copyRow(MouseEvent event) {
		if (event.getClickCount() == 2) {
			Product product = table.getSelectionModel().getSelectedItem();
			if(product != null) {
				setVista(this.table.getScene(), Page.PRODUCT_DETAILS, true);
				getObserver().modifyProductData(product);
			}
		}
	}
    
    @FXML
    private void addProduct() {
    	setVista(this.addProductBtn.getScene(), Page.PRODUCT_DETAILS, true);
    }
    
    /*@FXML
    private void search() {
    	List<String> filters = new ArrayList<>();
    	System.out.println(this.searchField.getText());
    	if (this.searchField.getText().equals("")) {
    		ViewUtils.showAlertMessage("INVALID FIELD", "Inserirsci una stringa per la ricerca");
    	}
    	else {
    		filters.add(this.searchField.getText());
    		getObserver().populateProductTable();
    	}
    }

	@Override
	public String getSearchedString() {
		return this.searchField.getText();
	}*/

	@Override
	public void populateTable(List<Product> productList) {
		this.table.setItems(FXCollections.observableArrayList(productList));
	}
    

}
