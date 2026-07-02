package view;


import java.io.IOException;
import java.util.List;

import javafx.application.Application;
import javafx.application.Platform;
import model.Entity;
import view.Road;

public class ViewImpl implements View{
	
	public static MainWindow main = new MainWindow();
	public  Road road;
	public SceneManager sceneManager;
	
	
	public ViewImpl(SceneManager scene) {
		this.sceneManager = scene;
	}
	
	public SceneManager getSceneManager() {
		return this.sceneManager;
	}
	
	 public void startView(){
	        Application.launch(MainWindow.class);
	  }
	 


	public void setScene(Road r) {
		this.road = r;		
	}
	 
	public void gameOver() {
		Platform.runLater(() -> {
			try {
				this.sceneManager.gameOver();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}
		

	public void initializeRoad() {
		Platform.runLater(() -> this.road.initializeRoad());	
	}
	
	public void updateText(final double health, final int shield, final int score) {
		Platform.runLater(() -> this.road.updateText(health, shield, score));
	}
	
	public void drawEntities(final List<Entity> listEntities) {
		Platform.runLater(() -> this.road.drawEntity(listEntities));
	}
	
	


}
