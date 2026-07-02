package towerDefense.entities.api;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.LinkedList;
import towerDefense.Constants;

public class RangedEntity extends MovingEntity{

    private Rectangle rangeBox;
    private long lastTime = System.currentTimeMillis();
    private long currentTime;
    private String projectileType;
    private LinkedList<Projectile> projectiles = new LinkedList<Projectile>();
    private boolean started = false;

    /**
     * Builds a new moving entity by setting its parameters and by uploading its sprite
     * 
     * @param startPoint
     *          top-left corner of its hitbox
     * @param speed
     *          speed at which the entity will move
     * @param hp
     *          total number of HP of the unit
     * @param damage   
     *          damage per tick that it will deal
     * @param nameEntity
     *          name of that entity
     * @param cost
     *          money cost to summon the entity
     */
    public RangedEntity(Point startPoint, int speed, int hp, int damage, String nameEntity, int cost) {
        super(startPoint, speed, hp, damage, nameEntity, cost);
        this.rangeBox = new Rectangle(startPoint);
        this.rangeBox.setSize(400, 80);
        this.currentTime = lastTime + 6000;
        if(nameEntity == Constants.archer){
            this.projectileType = Constants.arrow;
        }else if(nameEntity == Constants.turret){
            this.projectileType = Constants.boulder;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void draw(Graphics g){
        g.drawImage(super.getSprite(), this.getPosition().x, this.getPosition().y, null);
        for(Projectile arrow : this. projectiles){
            arrow.draw(g);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void attack(Entity target){
        currentTime = System.currentTimeMillis();
        if(this.projectiles.size() < 5 ){
            Point projPos;
            if(!started){
                if(this.projectileType == Constants.boulder){
                    projPos = new Point(this.getPosition().x , this.getPosition().y + 20);
                }else{
                    projPos = new Point(this.getPosition().x +30 , this.getPosition().y + 60);
                }
                Projectile projectile = new Projectile(this.getDamage(), projPos, this.projectileType, target);
                this.projectiles.add(projectile);
                lastTime = System.currentTimeMillis();
                this.started = true;
            }else{
                if((currentTime - lastTime) > 1000){
                    if(this.projectileType == Constants.boulder){
                        projPos = new Point(this.getPosition().x , this.getPosition().y + 20);
                    }else{
                        projPos = new Point(this.getPosition().x +30 , this.getPosition().y + 60);
                    }
                    Projectile projectile = new Projectile(this.getDamage(), projPos, this.projectileType, target);
                    this.projectiles.add(projectile);
                    lastTime = System.currentTimeMillis();
                } 
            }
        }
    }

    /**
     * Updates the position of the unit's range of fire
     */
    public void updateRangeBox() {
        this.rangeBox.setLocation((int)this.rangeBox.getX() + super.getSpeed(), (int)this.rangeBox.getY());
        if(lastTime+250<System.currentTimeMillis()){
            lastTime=System.currentTimeMillis();
        }
    }

    /**
     * 
     * @return the range at which the unit can fire, expressed by a rectangle
     */
    public Rectangle getRangeBox(){
        return this.rangeBox;
    }

    /**
     * @return all the currently active projectiles 
     */
    public LinkedList<Projectile> getProjectiles(){
        return this.projectiles;
    }

    /**
     * Resizes the range at which the unit can target enemies, expressed by 2 coordinates
     */
    public void resizeRangebox(int x, int y){
        this.rangeBox.setSize(x, y);
    }

    
}
