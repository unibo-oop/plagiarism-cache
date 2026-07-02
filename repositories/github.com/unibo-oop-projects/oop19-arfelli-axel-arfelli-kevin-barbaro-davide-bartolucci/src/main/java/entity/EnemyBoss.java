package entity;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import gameengine.GameLogger.OutputLevel;
import javafx.util.Pair;

public class EnemyBoss extends SimpleEnemy {
    
    public static int BASE_LIFE = 200;
    private static int RIGHT = 1;
    private static int LEFT = 0;
    private static int BULLET_WIDTH = 8;
    private static int BULLET_HEIGTH = 8;
    private static int MAX_RANGE = 8;
    private static int RANGE_MULTIPL = 10;
    private static int ATKS_COUNT = 3;
    private static int DIRECTION_DOWN = 270;
    private static int ATK_LENGTH = 12;
    private static int BOSS_SIZE = 128;
    private static int BOSS_SCORE = 1000;
    private int direction = RIGHT;
    private int atkCount = 0;
    private int atkCooldown = 0;
    private Random rand = new Random();
    private int count = 0;
    private boolean shooting = false;
    private Attacks currentAtk = Attacks.barrage;
    protected int length = BOSS_SIZE;
    
    private static Map<Attacks, Integer> ATK_COOLDOWN = new HashMap<Attacks, Integer>();
    static {
        ATK_COOLDOWN.put(Attacks.barrage, 2);
        ATK_COOLDOWN.put(Attacks.bulletHell, 5);
        ATK_COOLDOWN.put(Attacks.mitra, 6);
    }
    
    public EnemyBoss(int x, int y, int enemyLvl, int life) {
        super(x, y, enemyLvl);
        this.life = life;
    }
    
    public EnemyBoss(Pair<Integer, Integer> pos, int enemyLvl, int life) {
    	super(pos.getKey(), pos.getValue(), enemyLvl);
    	this.life = life;
    }

    @Override
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

    protected void tryAndShoot() {
        if(!shooting)
        {
            int testValue = rand.nextInt(MAX_RANGE);
            if(testValue >= RANGE_MULTIPL - this.level - this.count)
            {
                this.count = 0;
                this.shooting = true;
                int atkType = rand.nextInt(ATKS_COUNT);
                switch(atkType)
                {
                case 1: 
                    this.currentAtk = Attacks.barrage;
                    break;
                case 2:
                    this.currentAtk = Attacks.bulletHell;
                    break;
                case 3:
                    this.currentAtk = Attacks.mitra;
                    break;
                }
                this.shooting = true;
            }
            else 
            {
                this.count++;
            }
        }
        else
        {
            if(atkCooldown >= ATK_COOLDOWN.get(currentAtk))
            {
                atkCooldown = 0;
                atkCount++;
                shoot(getAngle());
                if(atkCount >= ATK_LENGTH)
                {
                    this.shooting = false;
                    atkCount = 0;
                }
            }
            atkCooldown++;
        }
    }
    
    protected void shoot(int shootingAngle) {
    	if(this.body.getMap().equals(Optional.empty())) {
    		return;
    	}
    	else if(this.currentAtk == Attacks.barrage)
        {
            try {
				this.body.getMap().get().addProjectile(new GenericProjectile(new CollisionBoxInt(body.getCollisionBox().getX() + (this.length / 2), body.getCollisionBox().getY() + (this.length / 2), getBulletWidth(), 
				        getBulletHeight()), shootingAngle, this), body.getCollisionBox().getX() + (this.length / 2), body.getCollisionBox().getY() + (this.length / 2));
			} catch (Exception e) {
				this.logger.logLine("Failed to spawn projectile", OutputLevel.ERROR);
			}
        }
        else if(this.currentAtk == Attacks.bulletHell)
        {
        	try {
				this.body.getMap().get().addProjectile(new GenericProjectile(new CollisionBoxInt(body.getCollisionBox().getX() + (this.length / 2), body.getCollisionBox().getY() + (this.length / 2), getBulletWidth(), 
				        getBulletHeight()), shootingAngle + 10, this), body.getCollisionBox().getX() + (this.length / 2), body.getCollisionBox().getY() + (this.length / 2));
        	this.body.getMap().get().addProjectile(new GenericProjectile(new CollisionBoxInt(body.getCollisionBox().getX() + (this.length / 2), body.getCollisionBox().getY() + (this.length / 2), getBulletWidth(), 
                    getBulletHeight()), shootingAngle + 20, this), body.getCollisionBox().getX() + (this.length / 2), body.getCollisionBox().getY() + (this.length / 2));
        	this.body.getMap().get().addProjectile(new GenericProjectile(new CollisionBoxInt(body.getCollisionBox().getX() + (this.length / 2), body.getCollisionBox().getY() + (this.length / 2), getBulletWidth(), 
                    getBulletHeight()), shootingAngle - 10, this), body.getCollisionBox().getX() + (this.length / 2), body.getCollisionBox().getY() + (this.length / 2));
        	this.body.getMap().get().addProjectile(new GenericProjectile(new CollisionBoxInt(body.getCollisionBox().getX() + (this.length / 2), body.getCollisionBox().getY() + (this.length / 2), getBulletWidth(), 
                    getBulletHeight()), shootingAngle - 20, this), body.getCollisionBox().getX() + (this.length / 2), body.getCollisionBox().getY() + (this.length / 2));
        	this.body.getMap().get().addProjectile(new GenericProjectile(new CollisionBoxInt(body.getCollisionBox().getX() + (this.length / 2), body.getCollisionBox().getY() + (this.length / 2), getBulletWidth(), 
                    getBulletHeight()), shootingAngle, this), body.getCollisionBox().getX() + (this.length / 2), body.getCollisionBox().getY() + (this.length / 2));
        	} catch (Exception e) {
    			this.logger.logLine("Failed to spawn projectile", OutputLevel.ERROR);
			}
        }
        else 
        {
        	try {
        	this.body.getMap().get().addProjectile(new GenericProjectile(new CollisionBoxInt(body.getCollisionBox().getX() + 60  + (this.length / 2), body.getCollisionBox().getY() + (this.length / 2), getBulletWidth(), 
                    getBulletHeight()), shootingAngle, this), body.getCollisionBox().getX() + 60  + (this.length / 2), body.getCollisionBox().getY() + (this.length / 2));
				this.body.getMap().get().addProjectile(new GenericProjectile(new CollisionBoxInt(body.getCollisionBox().getX()  + (this.length / 2), body.getCollisionBox().getY() + (this.length / 2), getBulletWidth(), 
				        getBulletHeight()), shootingAngle, this), body.getCollisionBox().getX() + (this.length / 2), body.getCollisionBox().getY() + (this.length / 2));
        	this.body.getMap().get().addProjectile(new GenericProjectile(new CollisionBoxInt(body.getCollisionBox().getX() - 60 + (this.length / 2), body.getCollisionBox().getY() + (this.length / 2), getBulletWidth(), 
                    getBulletHeight()), shootingAngle, this), body.getCollisionBox().getX() - 60  + (this.length / 2), body.getCollisionBox().getY() + (this.length / 2));
        	} catch (Exception e) {
    			this.logger.logLine("Failed to spawn projectile", OutputLevel.ERROR);
			}
        }
    }
    
    @Override
    public String getType() {
        return "Boss";
    }

    @Override
    protected int getAngle() {
        if(currentAtk == Attacks.barrage || currentAtk == Attacks.bulletHell) 
        {
            return playerAngle();
        }
        else 
        {
            return DIRECTION_DOWN;
        }
    }
    
    @Override
    protected int getBulletWidth() {
    	return BULLET_WIDTH;
    }

    @Override
    protected int getBulletHeight() {
        return BULLET_HEIGTH;
    }
    
    private enum Attacks {
        barrage,
        bulletHell,
        mitra;
    }

	@Override
	public int getScoreValue() {
		return BOSS_SCORE;
	}

}
