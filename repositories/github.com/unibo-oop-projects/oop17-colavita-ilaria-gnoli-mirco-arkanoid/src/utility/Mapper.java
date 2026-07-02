package utility;

import java.util.Optional;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import model.entities.Ball;
import model.entities.Brick;
import model.entities.IEntity;
import model.entities.PowerUp;
import model.entities.Bar;

/**
 * Classe utile a trasformare gli {@link IEntity} del model in oggetti rappresentabili su schermo.
 * 
 * @author Gnoli Mirco
 *
 */
//da uniformare bar e brick. Getwidth c'è in entrambi?
//colorare la pallina //con un'immagine? //colorare barra //colorare mattoni
//adapter? Valutare.
public final class Mapper {

    //Creato solo per silenziare il checkstyle
    private Mapper() { }

    /**
     * 
     * @param ent - {@link IEntity} da rappresentare con un oggetto utilizzabile dalla view
     * @return Optional(Shape)
     */
    public static Optional<? extends Shape> entityToView(final IEntity ent) {
        if (ent instanceof Ball) {
            return Optional.of(entityToView((Ball) ent));
        }
        if (ent instanceof Brick) {
            return Optional.of(entityToView((Brick) ent)); 
        }
        if (ent instanceof Bar) {
            return Optional.of(entityToView((Bar) ent)); 
        }
        if (ent instanceof PowerUp) {
            return Optional.of(entityToView((PowerUp) ent));
        }

        return Optional.empty();
    }

    /**
     * Metodo per rappresentare una {@link Ball}.
     * 
     * @param ball
     * @return {@link Circle}
     */
    private static Circle entityToView(final Ball ball) {
        switch (ball.getType()) {
        case FIRE_BALL:
            return new Circle(ball.getPosition().getKey(), ball.getPosition().getValue(), ball.getRadius(), Color.RED);
            //new ImagePattern(ImageViewObject.FIREBALL.getImage())
        case MAGNET_BALL:
            //boh
        default:
            return new Circle(ball.getPosition().getKey(), ball.getPosition().getValue(), ball.getRadius(), Color.BLACK);
        }
    }

    /**
     * Metodo per rappresentare un {@link Brick}.
     * 
     * @param brick
     * @return {@link Rectangle}
     */
    private static Rectangle entityToView(final Brick brick) {
        Rectangle r = new Rectangle(brick.getWidth(), brick.getHeight());
        r.setTranslateX(brick.getMinX());
        r.setTranslateY(brick.getMinY());
        //r.setFill(Random_Paint());

        return r;
    }

    /**
     * Metodo per rappresentare una {@link Bar}.
     * 
     * @param bar
     * @return {@link Rectangle}
     */
    private static Rectangle entityToView(final Bar bar) {
        Rectangle rect = new Rectangle();
        rect.setX(bar.getMinX());
        rect.setY(bar.getMinY());
        rect.setWidth(bar.getMaxX() - bar.getMinX());
        rect.setHeight(bar.getMaxY() - bar.getMinY());
        rect.setFill(Color.MEDIUMORCHID);       ///qui valutare come fare il colore

        return rect;
    }

    /**
     * Metodo per rappresentare un {@link PowerUp}.
     * 
     * @param powerUp
     * @return {@link Rectangle} //o ellisse boh
     */
    private static Rectangle entityToView(final PowerUp ent) {
        Rectangle rect = new Rectangle();
        rect.setX(ent.getMinX());
        rect.setY(ent.getMinY());
        rect.setWidth(ent.getMaxX() - ent.getMinX());
        rect.setHeight(ent.getMaxY() - ent.getMinY());
        rect.setFill(Color.BLUEVIOLET);
        return rect;
    }
}
