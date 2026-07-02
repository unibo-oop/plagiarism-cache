package zombieversity.view.entities;

import javafx.geometry.Point2D;
import zombieversity.view.imagefactory.Sprite;

public interface AttackView {

    void setRotation(Point2D pivot, Point2D second);

    void setDirection(double angle);

    Sprite getSprite();

}
