package towerDefense.entities.api;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import towerDefense.Constants;

public class Projectile {
    private Rectangle hitbox;
    private Point position;
    private int damage;
    private int speed;
    private BufferedImage currentSprite;
    private Point initialPos;
    
    /**
     * Builds a new projectile defining its type and parameters
     * 
     * @param damage
     *          damage that the projectile will deal
     * @param position
     *          current position of the projectile
     * @param type
     *          type of the projectile
     * @param target
     *          target at which it was shot
     */
    public Projectile(int damage, Point position, String type, Entity target){
        this.damage = damage;
        this.position = position;
        this.initialPos = new Point(position);
        if(type == Constants.arrow){
            this.speed = 4;
            this.hitbox = new Rectangle(this.position);
            this.hitbox.setSize(10, 5);
            try{
                this.currentSprite = ImageIO.read(getClass().getResource("/Assets/Projectile/Arrow.png"));
            }catch(Exception e){
                e.printStackTrace();
            }
        }else if(type == Constants.boulder){
            this.speed = 4;
            this.hitbox = new Rectangle(this.position);
            this.hitbox.setSize(15, 15);
            try{
                this.currentSprite = ImageIO.read(getClass().getResource("/Assets/Projectile/Boulder.png"));
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    /**
     * Calculates the damage upon hitting a target
     * @param target
     *          target of the projectile
     */
    public void hit(Entity target){
        target.incomeDamage(this.damage);
    }

    /**
     * Moves the projectile towards the current target
     * 
     * @param target
     *         current target of the projectile
     */
    public void move(Entity target) {
        this.position.x += this.speed;
        this.position.y += (int)this.getTrajectory(target);
        this.hitbox.setLocation(this.position);
    }

    /**
     * Checks if the projectile is colliding with an enemy
     * 
     * @param enemy
     *         current enemy targeted by the projectile
     */
    public boolean checkCollide(Entity enemy) {
        return this.hitbox.intersects(enemy.getHitbox());
    }
    
    /**
     * Checks if the arrow has travelled more than 200 units of distance
     */
    public boolean checkDistance(){
        if(this.getPosition().getX() - this.initialPos.getX() > 200){
            return true;
        }
        return false;
    }

    /**
     * Draws the entity that is calling this method
     * 
     * @param g
     *      graphic object used to draw all the components
     */
    public void draw(Graphics g) {
        g.drawImage(this.currentSprite, this.position.x, this.position.y, null);
    }

    /**
    * @return the hitbox of the projectile, expressed by a rectangle
    */
    public Rectangle getHitbox() {
        return hitbox;
    }

    /**
     * @return the current position of the projectile, expressed by 2 coordinates
     */
    public Point getPosition() {
        return this.position;
    }

    /**
     * @return the initial position of the projectile, expressed by 2 coordinates
     */
    public Point getInitialPos() {
        return this.initialPos;
    }

    /**
     * @return  the damage that the projectile deals
     */
    public int getDamage() {
        return damage;
    }

    /**
     * @return the speed of the projectile
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * @return the current sprite of the projectile
     */
    public BufferedImage getCurrentSprite() {
        return this.currentSprite;
    }

    /**
     * @param target
     *         current target of the projectile
     * @return
     *          the trajectory needed to land the projectile onto the target
     */
    public double getTrajectory(Entity target){
        double Dx = target.getPosition().getX() - this.position.x;
        double Dy = 40 + target.getPosition().getY() - this.position.y ;
        return ((double)speed * Dy)/Dx;
    }
}
