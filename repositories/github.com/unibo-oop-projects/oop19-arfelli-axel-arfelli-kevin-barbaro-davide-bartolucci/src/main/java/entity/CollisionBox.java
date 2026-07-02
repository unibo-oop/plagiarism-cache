package entity;

import javafx.geometry.Bounds;

public interface CollisionBox<O> {

    O getWidth();

    O getHeight();

    O getX();

    O getY();

    boolean checkCollision(CollisionBox<O> box);

    Bounds getBounds();
}
