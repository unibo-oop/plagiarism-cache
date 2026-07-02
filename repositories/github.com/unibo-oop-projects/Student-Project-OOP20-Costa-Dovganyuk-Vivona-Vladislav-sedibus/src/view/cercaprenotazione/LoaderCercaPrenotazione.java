package view.cercaprenotazione;

import java.awt.Dimension;
import java.awt.Toolkit;

import controllers.cercaprenotazione.ControllerCercaPrenotazione;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.utili.AzioneUtente;

public final class LoaderCercaPrenotazione extends Application  {

	private static final String PERC_SCENA = "/layouts/CercaPrenotazione.fxml";
	private static AzioneUtente azione;
	
	public LoaderCercaPrenotazione() {}
	
	public static void loadCercaPrenotazione(AzioneUtente azioneUtente) {
		LoaderCercaPrenotazione.azione = azioneUtente;
	}

	@Override
	public void start(Stage scenaPrimaria) throws Exception {
		final FXMLLoader caricatore = new FXMLLoader(getClass().getResource(PERC_SCENA));
		final ControllerCercaPrenotazione cercaPrenotazione = new ControllerCercaPrenotazione(LoaderCercaPrenotazione.azione);
		caricatore.setController(cercaPrenotazione);
		final Parent radice = caricatore.load();
		final Dimension dimSchermo = Toolkit.getDefaultToolkit().getScreenSize();
		final double larghezza = dimSchermo.getWidth() / scenaPrimaria.getWidth();
		final double altezza = dimSchermo.getHeight() / scenaPrimaria.getHeight();
		final Scene miaScena = new Scene(radice, larghezza, altezza);
		scenaPrimaria.setScene(miaScena);
		scenaPrimaria.setResizable(false);
		scenaPrimaria.show();
	}

}
