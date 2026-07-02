package it.unibo.shoot.GameObjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import it.unibo.shoot.loader.SpriteSheet;
import it.unibo.shoot.model.Handler;
import it.unibo.shoot.model.ID;
import it.unibo.shoot.util.Constants;
public class Bullet extends GameObject {

    private static final int BULLET_SIZE = 8;
    private static final float SPEED = 10f;
    private final Handler handler;
    private int damage = 50;

    public Bullet(int x, int y, ID id, Handler handler, int mx, int my, SpriteSheet ss, int damage) {
        super(x, y, id, ss);
        this.handler = handler;
        this.damage = damage;

        float diffX = mx - x;
        float diffY = my - y;
        float distance = (float) Math.sqrt(diffX * diffX + diffY * diffY);                                  //normalizza la velocita del proiettile

        if (distance != 0){                                                                                 //crash se si cliccava la coordinata 0
            this.velX = (diffX / distance) * SPEED;
            this.velY = (diffY / distance) * SPEED;
        } else {
            this.velX = 0;
            this.velY = -SPEED;
        }
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;

        if (x < 0 || x > Constants.WORLD_WIDTH || y < 0 || y > Constants.WORLD_HEIGHT) {
            handler.removeObject(this);
            return;
        }

        handler.getObjects().stream()
            .filter(obj -> obj.getId() == ID.Block && getBounds().intersects(obj.getBounds()))
            .findFirst()
            .ifPresent(obj -> handler.removeObject(this));
    }

    public int getDamage() {
        return damage;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.blue);
        g.fillOval((int)x, (int)y, BULLET_SIZE, BULLET_SIZE);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, BULLET_SIZE, BULLET_SIZE);
    }
}