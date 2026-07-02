package boxhead.model.entities;

import boxhead.model.entities.utils.Direction;
import javafx.geometry.Point2D;
import javafx.geometry.BoundingBox;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;
import boxhead.model.entities.utils.*;
import boxhead.controller.entities.AmmoController;
import boxhead.model.entities.gun.*;
import boxhead.model.entities.gun.Gun.GunType;

public class Player extends AbstractHealthEntity {
	
	private static final double PLAYER_WIDTH = 10;
	private static final double PLAYER_HEIGHT = 10;
	
	private static final int MAX_HEALTH=100;
	private static final long CHANGE_WEAPON_INTERVAL = 300;
	
	private LinkedList<Gun> guns;
	private Set<BoundingBox> walls;
	private final List<Boolean> iscolliding;
	private Gun currentGun;
	private int gunIndex;
	private Gun selectedGun=null;
	private long lastChange;
	private AmmoController ammoController;
	
	public Player() {
		super(Point2D.ZERO, Direction.EAST, new Point2D(150, 150), EntityType.PLAYER, MAX_HEALTH);
		this.guns = new LinkedList<>();
		this.currentGun=new GunFactory().getGun(this.getPosition(),GunType.PISTOL);
		this.guns.add(this.currentGun);
		this.iscolliding=new ArrayList<>();
		this.gunIndex=0;
		this.lastChange = 0;
		super.setBoundingBox(PLAYER_WIDTH, PLAYER_HEIGHT);
	}
	
	/**
	 * Method to set the walls.
	 * @param walls
	 */
	public final void setWalls(final Set<BoundingBox> walls) {
		this.walls = walls;
	}
	
	/**
	 * Method to set the ammos.
	 * @param ammos
	 */
	public final void setAmmoController(final AmmoController ammoController) {
		this.ammoController = ammoController;
	}
	
	 /**
     * Return current gun
     * @return Gun currentGun 
     */
	public final Gun getCurrentGun() {
		return this.currentGun;
	
	}
	
	public final Gun getSelectedGun(GunType gunType) {
		
		this.guns.forEach(g -> {
		      if(g.getGunType()==gunType)
		        this.selectedGun = g;
		});
		return this.selectedGun;
	}
	
	/**
     * Set current gun
     * @param Gun gun 
     */
	public final void setCurrentGun(Gun gun) {
		this.currentGun=gun;
	}
	
	/**
     * Check collision in the next position
     * @param Point2D nextPosition
     */
	public void checkCollision(final Direction direction) {
		if (!direction.equals(Direction.NULL)) {
			this.setDirection(direction);
	        final BoundingBox playerBB = new BoundingBox(getPosition().getX() + direction.traduce().getX()*4,
	                getPosition().getY() + direction.traduce().getY()*4, getWidth(), getHeight());
	
	        this.walls.forEach(BB -> {
	            if (Collision.isColliding(playerBB, BB)) {
	                this.iscolliding.add(true);
	            }
	        });
	        
	        this.ammoController.getAmmos().forEach(BB -> {
	        	if (Collision.isColliding(playerBB, BB)) {
	        		this.ammoController.removeAmmo(BB);
	        		this.currentGun.rechargeAmmo(this.currentGun.getMagazineSize());
	        		if (this.health <= 75) {
	        			this.health += 25;
	        		} else {
	        			this.health = 100;
	        		}
	        	}
	        });
	
	        if (!this.iscolliding.isEmpty()) {
	            setSpeed(Point2D.ZERO);
	        } else {
	            setSpeed(direction.traduce().multiply(2.5));
	        }
		}
		else {
			this.setSpeed(direction.traduce().multiply(2.5));
		}
        this.iscolliding.clear();
	}	
	
	/**
     * Unlock a new gun
     * @param Gun gun that has been unlocked
     */
	public void unlockGun(Gun gun) {
		this.guns.add(gun);
	}

	/**
     * Set currentGun to the next gun in the inventory
     */
	public void nextGun() {
		if(System.currentTimeMillis() - this.lastChange > CHANGE_WEAPON_INTERVAL) {
			if(this.gunIndex+1<this.guns.size()) {
				this.currentGun=this.guns.get(++gunIndex);
			} else {
				this.gunIndex=0;
				this.currentGun=this.guns.get(gunIndex);
			}
			this.lastChange = System.currentTimeMillis();
		}
	}
	/**
     * Set currentGun to the previous gun in the inventory
     */
	public void previousGun() {
		if(System.currentTimeMillis() - this.lastChange > CHANGE_WEAPON_INTERVAL) {
			if(this.gunIndex>0) {
				this.currentGun=this.guns.get(--gunIndex);
			} else {
				this.gunIndex=this.guns.size()-1;
				this.currentGun=this.guns.get(gunIndex);
			}
			this.lastChange = System.currentTimeMillis();
		}
	}
	
	public void update() {
		this.setPosition(this.getPosition().add(this.speed));
		this.ammoController.update();
	}
}