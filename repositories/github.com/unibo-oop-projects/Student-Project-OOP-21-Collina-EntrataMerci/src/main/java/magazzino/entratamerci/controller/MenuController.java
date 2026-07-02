package magazzino.entratamerci.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import javafx.event.ActionEvent;




public class MenuController {
	
	@FXML public void showAree(ActionEvent event) {
	}
	
	@FXML public void showInserisciArrivo(ActionEvent event) {
		Stage stage = new Stage();
		try {
			Parent root = FXMLLoader.load(new File(StorageController.getAggOrdineFxmlPath()).toURI().toURL());
		    stage.setScene(new Scene(root));
		    stage.setTitle("Inserisci Arrivo");
		    stage.initModality(Modality.WINDOW_MODAL);
		    stage.initOwner(
		        ((Node)event.getSource()).getScene().getWindow() );
		    stage.show();
		} catch (Exception e) {
			System.out.println("ERR: "+e.getMessage());
		}
		
	}

	@FXML public void showStorico(ActionEvent event) {
		Stage stage = new Stage();
		try {
			Parent root = FXMLLoader.load(new File(StorageController.getOrdineFxmlPath()).toURI().toURL());
		    stage.setScene(new Scene(root));
		    stage.setTitle("Inserisci Arrivo");
		    stage.initModality(Modality.WINDOW_MODAL);
		    stage.initOwner(
		        ((Node)event.getSource()).getScene().getWindow() );
		    stage.show();
		} catch (Exception e) {
			System.out.println("ERR: "+e.getMessage());
		}
	}

	@FXML public void showFornitori(ActionEvent event) {
		Stage stage = new Stage();
		try {
			Parent root = FXMLLoader.load(new File(StorageController.getFornitoriFxmlPath()).toURI().toURL());
		    stage.setScene(new Scene(root));
		    stage.setTitle("Anagrafica Fornitori");
		    stage.initModality(Modality.WINDOW_MODAL);
		    stage.initOwner(
		        ((Node)event.getSource()).getScene().getWindow() );
		    stage.show();
		} catch (Exception e) {
			System.out.println("ERR: "+e.getMessage());
		}
	}

	@FXML public void showArticoli(ActionEvent event) throws MalformedURLException, IOException {
		Stage stage = new Stage();
		try {
			Parent root = FXMLLoader.load(new File(StorageController.getArticoliFxmlPath()).toURI().toURL());
		    stage.setScene(new Scene(root));
		    stage.setTitle("Anagrafica Articoli");
		    stage.initModality(Modality.WINDOW_MODAL);
		    stage.initOwner(
		        ((Node)event.getSource()).getScene().getWindow() );
		    stage.show();
		} catch (Exception e) {
			System.out.println("ERR: "+e.getMessage());
		}
		
	}

	@FXML public void showGiacenza(ActionEvent event) {
		Stage stage = new Stage();
		try {
			Parent root = FXMLLoader.load(new File(StorageController.getGiacenzaFxmlPath()).toURI().toURL());
			stage.setScene(new Scene(root));
			stage.setTitle("Giacenza");
			stage.initModality(Modality.WINDOW_MODAL);
			stage.initOwner(
					((Node)event.getSource()).getScene().getWindow() );
			stage.show();
		} catch (Exception e) {
			System.out.println("ERR: "+e.getMessage());
		}
	}

}
