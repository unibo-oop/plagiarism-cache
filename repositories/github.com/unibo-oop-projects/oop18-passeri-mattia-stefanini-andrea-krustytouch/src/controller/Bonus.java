package controller;

import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;


public abstract class Bonus {
    
    protected AnchorPane root;
    protected ImageView image;

    
    /*public Bonus(AnchorPane root) {
        this.root = root;
    }*/


    protected double randomPosition(){
        double MaxBound=(this.root.getWidth()*75)/100;
        double MinBound=(this.root.getWidth()*15)/100;
        double location =(Math.random()*(MaxBound-MinBound))+MinBound;
        return location;
    }

    protected void  move(){
        TranslateTransition movement= new TranslateTransition();
        movement.setNode(this.image);
        movement.setDuration(Duration.millis(3000));
        movement.setFromY(0);
        movement.setToY((this.root.getHeight()*55)/100);
        movement.setOnFinished((event)-> this.root.getChildren().remove(this.image));
        movement.play();
    }

    protected  void spawn(double Width, double Height){
    	 this.image.setFitWidth(Width);
         this.image.setFitHeight(Height);
         this.image.setLayoutY(0);
         this.image.setLayoutX(randomPosition());
         this.image.setVisible(true);
         Platform.runLater(()->root.getChildren().add(this.image));
    }

    protected abstract void action();

}
