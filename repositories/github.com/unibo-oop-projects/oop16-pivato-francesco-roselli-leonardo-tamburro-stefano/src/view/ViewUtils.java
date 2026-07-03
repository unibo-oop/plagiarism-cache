package view;

import java.io.IOException;
import java.util.LinkedList;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * 
 * This class contain all the methods to manage the view
 * @author Ste
 *
 */
public class ViewUtils {
	
	private static final double MIN_HEIGHT_SIZE = 600;
	private static final double MIN_WIDTH_SIZE = 1024;
	private static final String ICON = "/view/resources/images/newclubsportman-logo.jpg";
	private static FXMLLoader loader;
	private static final LinkedList<Page> vistaQueue = new LinkedList<>();
	
	/**
	 * @return the handler relative to the current vista
	 */
	public static <T> T getHandler(){
		return loader == null ? null : loader.getController();
	}
	
	/**
	 * @return the last screen navigated, used for goBack functions
	 */
	public static Page getLastVista(){
	    vistaQueue.pop();
	    return vistaQueue.getFirst();
	}
	
	/**
	 * Change the current vista
	 * @param scene to change
	 * @param page to load
	 * @param isForward true for regular function, false if used to implement back buttons
	 */
	public static void setVista(Scene scene, Page page, boolean isForward) {
		BorderPane root;
		try {
			loader = new FXMLLoader(ViewUtils.class.getResource(page.url()));
			root = (BorderPane) loader.load();
			Stage primaryStage = (Stage) scene.getWindow();
			scene.setRoot(root);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(isForward){
		    vistaQueue.push(page);
		}
		//System.out.println(screenQueue);
	}
	
	/**
	 * Open the main window
	 */
	public static void openWindow(){
		BorderPane root = new BorderPane();
		Scene scene = new Scene(root);
		Stage stage = new Stage();
		scene.getStylesheets().add(Style.BASIC.url());
		stage.setScene(scene);
		stage.getIcons().add(new Image(ICON));
		stage.setTitle("New Club Sportman");
		stage.setMinWidth(MIN_WIDTH_SIZE);
		stage.setMinHeight(MIN_HEIGHT_SIZE);
		setVista(scene, Page.LOGIN, true);
		stage.show();
	}
	
	
	/**
	 * Set the style of the application
	 * @param scene
	 * @param styleSheet
	 */
	public static void setStyle(Scene scene, String styleSheet){
		scene.getStylesheets().clear();
		scene.getStylesheets().add(styleSheet);
	}
	
	/**
	 * @param message to show in the dialog box
	 */
	public static void showAlertMessage(String title, String message){
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle(title);
		alert.setContentText(message);
		alert.showAndWait();
	}

}
