package towerDefense.entities.api;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;
import javax.imageio.ImageIO;
import towerDefense.Constants;
import towerDefense.game.impl.Sfx;

public abstract class MovingEntity implements Entity{

    private Point position; //upper left position of the hitbox
    private Rectangle hitbox;
    private int speed;
    private int hp;
    private int damage;
    private Entity target;
    private BufferedImage sprite;
    private int currentSpriteWalk = 0;
    private int currentSpriteAttack = 0;
    private long lastTime = System.currentTimeMillis();
    private long lastTimeAttack=lastTime;
    private String nameEntity;

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
    public MovingEntity(Point startPoint, int speed, int hp, int damage, String nameEntity, int cost){
        this.position = new Point(startPoint);
        this.hitbox = new Rectangle(startPoint);
        this.hitbox.setSize(80, 80);
        this.speed = speed;
        this.hp = hp;
        this.damage = damage;
        this.nameEntity=nameEntity;
        this.updateSprite(Constants.walk);
    }

    /**
     * Updates the position of the entities, of its hitbox and its sprite every tick
     */
    public void updatePosition() {
        this.position.setLocation(this.position.getX() + this.speed, this.position.getY());
        this.hitbox.setLocation((int)this.hitbox.getX() + this.speed, (int)this.hitbox.getY());
        this.updateSprite(Constants.walk);
        if(lastTime+250<System.currentTimeMillis()){
            lastTime=System.currentTimeMillis();
        }
    }

    /**
     * Updates the current sprite of the entity
     * 
     * @param activity  
     *          determins which activity is the entity doing in that moment
     */
    public void updateSprite(String activity) {
        int currentSprite=0;

        if(lastTime+125<System.currentTimeMillis()){
            lastTime=System.currentTimeMillis();
            
            try {
                if(activity==Constants.walk){
                    if((this.nameEntity==Constants.barbarian&&currentSpriteWalk==8)||
                            (this.nameEntity==Constants.knight&&currentSpriteWalk==8)||
                                (this.nameEntity==Constants.goblin&&currentSpriteWalk==6)||
                                    (this.nameEntity==Constants.archer&&currentSpriteWalk==8)||
                                        (this.nameEntity==Constants.turret&&currentSpriteWalk==1)||
                                            (this.nameEntity==Constants.wizard&&currentSpriteWalk==6)){
                        currentSpriteWalk=0;
                    }
                    currentSpriteWalk++;
                    currentSprite=this.currentSpriteWalk;
                }else if(activity==Constants.attack){
                    if((this.nameEntity==Constants.barbarian && currentSpriteAttack==30)||
                            (this.nameEntity==Constants.knight&&currentSpriteAttack==9)||
                                    (this.nameEntity==Constants.goblin&&currentSpriteAttack==7)||
                                        (this.nameEntity==Constants.archer&&currentSpriteAttack==17)||
                                            (this.nameEntity == Constants.turret && this.currentSpriteAttack==6)||
                                                (this.nameEntity == Constants.wizard && this.currentSpriteAttack==10)){
                        currentSpriteAttack=0;
                    }
                    currentSpriteAttack++;
                    currentSprite=this.currentSpriteAttack;
                }
                this.sprite = ImageIO.read(getClass().getResource("/Assets/"+nameEntity+"/"+activity+"/"+currentSprite+"/"+currentSprite+".png"));  
            } catch (IOException e) {
                System.out.println("error loading image " + e.getMessage());
            }
        } 
    }

    /**
     * Triggers an attack between 2 entities, one being the attacker and the other one being the target 
     * 
     * @param target
     *          entity that is going to be attacked
     */
    public void attack(Entity target) {
        this.target = target;
        this.target.incomeDamage(this.damage);

        if(lastTimeAttack+1500<System.currentTimeMillis()){
            lastTimeAttack=System.currentTimeMillis();

            Sfx SFX=new Sfx();
            if(nameEntity==Constants.barbarian || nameEntity==Constants.knight){
                SFX.startSFX("HitSword");
            }else{
                SFX.startSFX("Hit");
            }
        }
    }

    /**
     * Determines a target by looking at the first available enemy
     * 
     * @param enemies
     *          list of all the current enemies 
     * @return
     *          the first enemy of the list
     */
    public MovingEntity getTarget(LinkedList<MovingEntity> enemies){
            return enemies.getFirst();
    }

    /**
     * Draws the entity that is calling this method
     * 
     * @param g
     *      graphic object used to draw all the components
     */
    public void draw(Graphics g){
        g.drawImage(this.sprite, this.getPosition().x, this.getPosition().y, null);
    }

    /**
     * {@inheritDoc}
     */
    public Rectangle getHitbox(){
        return this.hitbox;
    }

    /**
     * @return the current sprite of the entity
     */
    public BufferedImage getSprite(){
        return this.sprite;
    }

    /**
     * 
     * @return the currentspritewalk index
     */
    public int getCurrentSpriteWalk(){
        return this.currentSpriteWalk;
    }

    /**
     * 
     * @return the currentspritewalk index
     */
    public int getCurrentSpriteAttack(){
        return this.currentSpriteAttack;
    }

    /**
     * @return the name of the entity
     */
    public String getNameEntity(){
        return this.nameEntity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Point getPosition() {
        return this.position;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getSpeed() {
        return this.speed;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getHp() {
       return this.hp;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getDamage() {
        return this.damage;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void incomeDamage(int value) {
        this.hp -= value;
    }  
 
    /**
     * Resize the hitbox of the entity
     * 
     * @param x
     * @param y
     */
    public void resizeHitbox(int x, int y){
        this.hitbox.setSize(x, y);
    }
}
