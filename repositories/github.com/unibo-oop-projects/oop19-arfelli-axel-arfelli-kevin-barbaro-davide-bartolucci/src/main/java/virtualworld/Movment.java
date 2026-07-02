package virtualworld;

import entity.CollisionBox;

public interface Movment {

    VirtualMap getMap();

    boolean moveTo(int x, int y);

    boolean isInside(int x, int y);

    CollisionBox<Integer> getCollisionBox();

}
