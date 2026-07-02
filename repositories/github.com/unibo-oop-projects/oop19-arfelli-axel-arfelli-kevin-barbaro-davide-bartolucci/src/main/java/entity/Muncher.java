package entity;

import java.util.Optional;
import java.util.Random;
import javafx.util.Pair;

public class Muncher extends SimpleEnemy {

    public static int BASE_LIFE = 15;
    private static int MUNCHER_BULLET_WIDTH = 8;
    private static int MUNCHER_BULLET_HEIGHT = 8;
    private static int CHARGE_RANGE = 30;
    private static int CHARGE_TRESHOLD_START = 30;
    private static int MUNCHER_SCORE = 15;
    private static int CHARGE_COOLDOWN = 20;
    private final int chargeTreshold;
    private int initialHeigth;
    private int chargeCount;
    private boolean charge = false;
    private Random randomCharge = new Random();
    private boolean down;
    
    public Muncher(int x, int y, int enemyLvl, int life) {
        super(x, y, enemyLvl);
        this.charge = false;
        this.chargeTreshold = CHARGE_TRESHOLD_START - enemyLvl;
        this.life = life;
        this.initialHeigth = y;
    }
    
    public Muncher(Pair<Integer, Integer> pos, int enemyLvl, int life) {
    	super(pos.getKey(), pos.getValue(), enemyLvl);
    	this.charge = false;
        this.chargeTreshold = CHARGE_TRESHOLD_START - enemyLvl;
        this.life = life;
        this.initialHeigth = pos.getValue();
    }
    
    public void update() {
        move();
        tryAndShoot();
    }

    protected void move() {
        if(!charge)
        {
        	Optional<Actor> optPlayer = this.body.getMap().get().getActors(Faction.ALLY).keySet().stream().findAny();
    		if(optPlayer.isPresent()) 
    		{
	            if(this.body.getCollisionBox().getX() <= optPlayer.get().getBody().getCollisionBox().getX())
	            {
	                this.body.move(body.getCollisionBox().getX()+1, body.getCollisionBox().getY());
	            }
	            else
	            {
	                this.body.move(body.getCollisionBox().getX()-1, body.getCollisionBox().getY());
	            }
	            if(this.randomCharge.nextInt(CHARGE_RANGE) >= chargeTreshold) {
	            	if(chargeCount == CHARGE_COOLDOWN) 
	            	{
	            		chargeCount = 0;
		                this.charge = true;
		                this.down = true;
	            	}
	            	else 
	            	{
	            		chargeCount++;
	            	}
	            }
    		}
        }
        else
        {
            if(this.down)
            {
                this.body.move(body.getCollisionBox().getX(), body.getCollisionBox().getY()-3);
                if(this.body.getMap().equals(Optional.empty())) {
            		return;
            	}
                else if(body.getCollisionBox().getY() - this.length <= 0)
                {
                    this.down = false;
                }
            }
            else
            {
                this.body.move(body.getCollisionBox().getX(), body.getCollisionBox().getY()+2);
                if(body.getCollisionBox().getY() >= this.initialHeigth)
                {
                    this.charge = false;
                }
            }
        }
        
    }
    
    protected int getAngle() {
        return ANGLE_DOWN;
    }
        
    public String getType() {
        return "Muncher";
    }

    @Override
    protected int getBulletWidth() {
        return MUNCHER_BULLET_WIDTH;
    }

    @Override
    protected int getBulletHeight() {
        return MUNCHER_BULLET_HEIGHT;
    }

	@Override
	public int getScoreValue() {
		return MUNCHER_SCORE + level;
	}
}