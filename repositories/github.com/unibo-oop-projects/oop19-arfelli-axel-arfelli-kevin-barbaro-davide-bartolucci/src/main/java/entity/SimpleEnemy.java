package entity;

import java.util.List;
import java.util.Optional;
import java.util.Random;

public abstract class SimpleEnemy extends SimpleShip {

    private static int MAX_RANGE = 20;
    private static int RANGE_MULTIPL = 50;
    protected static int ANGLE_DOWN = 270;
    private Random rand = new Random();
    private int count;
    protected List<Actor> player;
    protected int level;
    protected Long lastShoot = System.currentTimeMillis();
    
    public SimpleEnemy(int x, int y, int enemyLvl) {
        super(x, y);
        this.level = enemyLvl;
    }
    
    public void update() {
        move();
        tryAndShoot();
    }
    
    protected abstract void move();

    public abstract String getType();
    
    public Faction getFaction() {
        return Faction.ENEMY;
    }
    
    protected int playerAngle() {
    	if(this.body.getMap().equals(Optional.empty())) {
    		return ANGLE_DOWN;
    	}
    	else {
    		Optional<Actor> optPlayer = this.body.getMap().get().getActors(Faction.ALLY).keySet().stream().findAny();
    		if(optPlayer.isPresent()) 
    		{
    			double playerX = optPlayer.get().getBody().getCollisionBox().getX();
    	        double playerY = optPlayer.get().getBody().getCollisionBox().getY();
    	        double ipotenusa = Math.sqrt(Math.pow(playerX - this.body.getCollisionBox().getX(), 2) + Math.pow(playerY - this.body.getCollisionBox().getY(), 2));
    	        double angle = Math.toDegrees(Math.acos(Math.abs(playerX - this.body.getCollisionBox().getX()) / ipotenusa));
    	        if(this.body.getCollisionBox().getX() >= playerX && this.body.getCollisionBox().getY() >= playerY) 
    	        {
    	            return (int) (360 - angle);
    	        }
    	        else if(this.body.getCollisionBox().getY() >= playerY)
    	        {
    	            return (int) (180 + angle);
    	        }
    	        else if(this.body.getCollisionBox().getX() >= playerX && this.body.getCollisionBox().getY() < playerY)
    	        {
    	            return (int) angle;
    	        }
    	        else
    	        {
    	            return (int) (180 - angle);
    	        }
    		}
    		else 
    		{
    			return ANGLE_DOWN;
    		}
    	}
    }

    protected abstract int getAngle();
    
    protected void tryAndShoot() {
        
    	int testValue = rand.nextInt(MAX_RANGE);
        if(testValue >= RANGE_MULTIPL - this.level - this.count)
        {
            this.count = 0;
            shoot(getAngle());
        }
        else 
        {
            this.count++;
        }
    }
}