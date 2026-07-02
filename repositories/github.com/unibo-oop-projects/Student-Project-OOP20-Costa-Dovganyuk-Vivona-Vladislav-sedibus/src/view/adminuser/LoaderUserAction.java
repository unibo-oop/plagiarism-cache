package view.adminuser;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public final class LoaderUserAction extends Application{
	
	GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	
	private static final String PER_USERDECISION= "/layouts/UserAction.fxml";
	private double ResizeWidth;
	private double ResizeHeight;
	private int width = gd.getDisplayMode().getWidth();
	private int height = gd.getDisplayMode().getHeight();
	
	public void start(Stage stage) throws Exception {
		final Parent root = FXMLLoader.load(getClass().getResource(PER_USERDECISION));
		
		ResizeWidth=width/stage.getWidth();
		ResizeHeight=height/stage.getHeight();
	
		final Scene scene = new Scene(root, width/ResizeWidth, height/ResizeHeight);


		stage.setTitle("Sedibus");
		stage.setScene(scene);
		stage.show();
	}

}
