package view.piantina;

import java.awt.Dimension;
import java.awt.Toolkit;

import javafx.application.Application;
import javafx.stage.Stage;
import model.utili.Utente;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.fxml.FXMLLoader;


public final class LoaderTableView extends Application {
	
	private Text testoUtente;
	private static Utente utente;

	private static final String PATH_LAYOUT = "/layouts/MappaTavoli.fxml";
	private double PROPORTION_WIDTH;
	private double PROPORTION_HEIGHT;

	
	public static void loaderTableView(Utente utente) {
		LoaderTableView.utente = utente;
	}
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		final FXMLLoader loader = new FXMLLoader(getClass().getResource(PATH_LAYOUT));
        final Parent root = loader.load();
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        
        
        PROPORTION_WIDTH = sw/primaryStage.getWidth();
    	PROPORTION_HEIGHT = sh/primaryStage.getHeight();
        
        final Scene scene = new Scene(root,sw/PROPORTION_WIDTH,sh/PROPORTION_HEIGHT);
        
        this.testoUtente = (Text) loader.getNamespace().get("testoUtente");
        this.testoUtente.setText(LoaderTableView.utente.toString());
        
        primaryStage.setResizable(false);
        primaryStage.setTitle("Visione Tavoli - " + LoaderTableView.utente);
        primaryStage.centerOnScreen();
        
        primaryStage.setScene(scene);
        primaryStage.show();

	}
	
}
