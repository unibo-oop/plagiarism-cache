package controller;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;


public class PatrickManager extends Bonus implements Runnable{

    public PatrickManager(AnchorPane base){
        this.root=base;
        this.image = new ImageView(new Image("Images/patrickstella.png"));
    }

   

    @Override
    public void action() {
        System.out.println("Ciao");
    }


    @Override
    public void run() {

        spawn(90,79);
        move();
        this.image.setOnMouseClicked((event)->action());


    }
}
