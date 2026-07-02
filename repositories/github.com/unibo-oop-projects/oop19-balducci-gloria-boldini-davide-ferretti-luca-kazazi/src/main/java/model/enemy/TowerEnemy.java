package model.enemy;

import java.awt.image.BufferedImage;
import controllers.movement.Movement;
import model.Direction;
import model.ID;

public class TowerEnemy extends Enemy {

    private long lastTime;
    private static final long DELAY = 5000;

    /**
     * Constructor for TowerEnemy
     * 
     * @param id
     * @param posX
     * @param posY
     * @param velX
     * @param velY
     * @param image
     * @param rayImage
     */
    public TowerEnemy(final ID id, final int posX, final int posY, final double velX, final double velY,
            final BufferedImage image, final BufferedImage rayImage) {
        super(id, posX, posY, velX, velY, image, rayImage);
        this.setEnemyTower();
        super.getRay().setRayX(-45);
        super.getRay().setRayY(-50);
    }

    @Override
    public void setPositionAfterCollision(final Direction direction) {
    }

    @Override
    public int getSpeed() {
        return 0;
    }

    @Override
    public Movement getMovement() {
        return null;
    }

    @Override
    public void tick() {
        super.tick();
        final long now = System.currentTimeMillis();
        if (now - lastTime > DELAY) {
            this.rayTowerEffect();
            lastTime = System.currentTimeMillis();
        }
    }

    private void rayTowerEffect() {
        if (super.getRay().isVisible()) {
            super.getRay().setVisible(false);
            super.getRay().setRay(false);
        } else {
            super.getRay().setVisible(true);
            super.getRay().setRay(true);
        }

    }

}
