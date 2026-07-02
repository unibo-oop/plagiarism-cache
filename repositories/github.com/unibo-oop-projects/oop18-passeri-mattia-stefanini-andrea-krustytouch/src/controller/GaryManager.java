package controller;


import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.awt.event.ActionListener;

import javax.swing.Timer;

public class GaryManager extends Bonus implements  Runnable {


    public GaryManager(AnchorPane base){
        this.root=base;
        this.image = new ImageView(new Image("Images/gary_finale.png"));
    }

 
    @Override
    public void action() {

       //SpawnerPlanktonManager.time=10000;
        Timer timer = new Timer(200000,TimeSlower());

 

        timer.setRepeats(false);
        timer.start();
        Platform.runLater(()->root.getChildren().remove(this.image));
    }

	private ActionListener TimeSlower() {
		return (event)->SpawnerPlanktonManager.time=1000;
	}



    @Override
    public void run() {
        this.spawn(98.7,83.3);
        this.move();
        this.image.setOnMouseClicked((event)->action());
    }
}
