package model.entities;

public interface Bullet extends GameEntity {

    
    Power getPower();

    enum Power {
        NORMAL, ARMOR_PIERCING
    }

    Tank getAttachedTank();

}
