package entity;

import java.util.Optional;
import javafx.util.Pair;

public class Drone extends SimpleEnemy {

    public static int BASE_LIFE = 8;
    private static int DRONE_BULLET_WIDTH = 8;
    private static int DRONE_BULLET_HEIGHT = 8;
    private static int RIGHT = 1;
    private static int LEFT = 0;
    private static int DRONE_SCORE = 10;
    private int direction = RIGHT;
    
    public Drone(int x, int y, int enemyLvl, int life) {
        super(x, y, enemyLvl);
        this.life = life;
    }
    
    public Drone(Pair<Integer, Integer> pos, int enemyLvl, int life) {
    	super(pos.getKey(), pos.getValue(), enemyLvl);
    	this.life = life;
    }
    
    public void update() {
        move();
        tryAndShoot();
    }

    protected void move() {
        if(direction == RIGHT)
        {
            this.body.move(this.body.getCollisionBox().getX() + 1, this.body.getCollisionBox().getY());
            if(this.body.getMap().equals(Optional.empty())) {
        		return;
        	}
            else if(this.body.getCollisionBox().getX() + this.length >= this.body.getMap().get().getWidth() - this.length / 2)
            {
                this.direction = LEFT;
            }
        }
        else
        {
            this.body.move(this.body.getCollisionBox().getX() - 1, this.body.getCollisionBox().getY());
            if(this.body.getCollisionBox().getX() - this.length <= this.length / 2)
            {
            this.direction = RIGHT;
            }
        }
    }
        
    public String getType() {
        return "Drone";
    }

    @Override
    protected int getBulletWidth() {
        return DRONE_BULLET_WIDTH;
    }

    @Override
    protected int getBulletHeight() {
        return DRONE_BULLET_HEIGHT;
    }

    @Override
    protected int getAngle() {
        return playerAngle();
    }

	@Override
	public int getScoreValue() {
		return DRONE_SCORE + level;
	}
}