package utility;

import javafx.event.EventHandler;
import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class Handler {

    public static EventHandler<MouseEvent> imgMouseOver = mouseEvent -> {
        ImageView img = (ImageView)mouseEvent.getSource();
        Glow glow = new Glow(0.2);
        img.setEffect(glow);
    };

    public static EventHandler<MouseEvent> imgMouseExit = mouseEvent -> {
        ImageView img = (ImageView)mouseEvent.getSource();
        Glow glow = new Glow(0.0);
        img.setEffect(glow);
    };

}
