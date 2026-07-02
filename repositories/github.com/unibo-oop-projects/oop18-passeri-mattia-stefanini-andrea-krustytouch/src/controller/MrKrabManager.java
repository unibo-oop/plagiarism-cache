package controller;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;


public class MrKrabManager extends Bonus implements Runnable {

    public MrKrabManager(AnchorPane base){
        this.root=base;
        this.image = new ImageView(new Image("Images/mrkrab_finale.png"));
    }

   

    @Override
    //function that rappresent the effect of the bonus
    public void action(){
        SpawnerPlanktonManager.planktonCollector.forEach(this::RemoveChild);
        Platform.runLater(()->root.getChildren().remove(this.image));
    }

	private boolean RemoveChild(PlanktonManager elem) {
		return this.root.getChildren().remove(elem.Plankton);
	}

    @Override
    //inherited function to move MrKrab
    public void run() {
        spawn(74,62.5);
        move();
        this.image.setOnMouseClicked((event)->action());


    }

}
