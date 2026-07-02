package view.piantina;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.time.LocalDate;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.utili.Periodo;
import model.utili.Utente;

public final class LoaderTavoloVerdeLibero extends Application{

	private static final String TITLE = "Tavolo LIBERO";
	private static final String PATH_LAYOUT = "/layouts/TavoloVerdeLibero.fxml";
	private static final double OPACITY = 0.95;
	private double PROPORTION_WIDTH;
	private double PROPORTION_HEIGHT;
	
	private Text testoSuperiore;
	private Text testoPeriodo;
	private Text testoData;
	private Text testoUtente;
	private Button buttonConferma;
	
	private String TESTO_SUPERIORE;
	private Periodo periodo;
	private LocalDate data;
	private Stage previousStage;
	private Utente utente;
	
	public LoaderTavoloVerdeLibero(Utente utente,String idTavolo, Periodo periodo, LocalDate data,Stage previousStage) {
		this.utente = utente;
		TESTO_SUPERIORE = "Tavolo " + idTavolo + " - LIBERO";
		this.periodo = periodo;
		this.data = data;
		this.previousStage = previousStage;
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
        
        
        this.testoSuperiore = (Text) loader.getNamespace().get("testoSuperiore");
        this.testoPeriodo = (Text) loader.getNamespace().get("testoPeriodo");
        this.testoData = (Text) loader.getNamespace().get("testoData");
        this.testoUtente = (Text) loader.getNamespace().get("testoUtente");
        
        this.testoSuperiore.setText(TESTO_SUPERIORE);
        this.testoPeriodo.setText(this.periodo.toString());
        this.testoData.setText(this.data.toString());
        this.testoUtente.setText(this.utente.toString());
        
        this.buttonConferma = (Button) loader.getNamespace().get("buttonConferma");
        //chiudo lo stage della visione tavolo, che verra riaperta dal Controller Prenotazione
        this.buttonConferma.setOnMouseReleased(e->{
        	this.previousStage.close();
        });
        
        primaryStage.setResizable(false);
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.setOpacity(OPACITY);
        primaryStage.setTitle(TITLE);
        primaryStage.setScene(scene);
        primaryStage.show();

	}

}
