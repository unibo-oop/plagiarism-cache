package entity;
import java.util.Optional;

import org.apache.commons.lang3.time.StopWatch;

import gameengine.GameLogger;
import gameengine.GameLoggerAdaptor;
import gameengine.GameLogger.OutputLevel;
import virtualworld.VirtualMap;

public abstract class SimpleShip extends UUIDActor {
    
    protected int life;
    protected boolean isAlive = true;
    protected VirtualBody body;
    protected int length = 64;
    protected GameLogger logger;
    
    public SimpleShip(int x, int y) {
        this.body = new BodyImpl(new CollisionBoxInt(x, y, length, length));
    }

    @Override
    public abstract void update();

    @Override
    public boolean isAlive() {
        return this.isAlive;
    }

    @Override
    public abstract String getType();

    @Override
    public int getLife() {
        return this.life;
    }

    @Override
    public void addToLife(int amount) {
        this.life = this.life + amount;
        if(this.life <= 0)
        {
            this.isAlive = false;
        }
    }
    
    public VirtualBody getBody() {
        return this.body;
    }

    @Override
    public abstract Faction getFaction();
    
    protected abstract int getBulletWidth();
    
    protected abstract int getBulletHeight();
    
    protected void shoot(int shootingAngle) {
    	if(this.body.getMap().equals(Optional.empty())) {
    		return;
    	}

        try {
			this.body.getMap().get().addProjectile(new GenericProjectile(new CollisionBoxInt(body.getCollisionBox().getX() + (this.length / 2), body.getCollisionBox().getY() + (this.length / 2), getBulletWidth(), 
			        getBulletHeight()), shootingAngle, this), body.getCollisionBox().getX() + (this.length / 2), body.getCollisionBox().getY() + (this.length / 2));
		} catch (Exception e) {
			this.logger.logLine("Failed to spawn projectile", OutputLevel.ERROR);
		}

    }
    
    public void setMap(VirtualMap map) {
    	//((SimpleShip) this.body).setMap(map);
    	((BodyImpl) this.body).setMap(map);
    }
}
