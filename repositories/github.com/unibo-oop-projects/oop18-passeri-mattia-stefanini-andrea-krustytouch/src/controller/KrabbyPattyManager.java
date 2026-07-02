package controller;



import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;


public class KrabbyPattyManager extends Bonus implements Runnable {

    public KrabbyPattyManager(AnchorPane base){
        this.root=base;
        this.image = new ImageView(new Image("Images/KrabbyPatty.png"));
    }

   
    @Override
    public void action() {
        System.out.println("Ciao");
    }

    @Override
    public void run() {
        spawn(61,61);
        move();
        this.image.setOnMouseClicked((event)->action());

    }
}
