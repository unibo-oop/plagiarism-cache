package model.entities;

import java.util.HashSet;


import java.util.Random;
import java.util.Set;

import model.Model;
import model.entitiesutil.EntityConstants;
import model.physics.EntityCollision.EdgeCollision;
import util.Pair;

/**
 * The class that create and manage the alien group
 *
 */
public class AlienGroup{

	private Random random = new Random();
	private Model model;
	private final int MAX_ALIEN_SHOOTING = 4;
	private final int MIN_ALIEN_SHOOTING = 1;
	private final int MAX_ALIEN_PER_COLUMN = 10;
	private EdgeCollision expectedCollision;
	
	/**
	 * the constructor of the alien group,
	 * it takes the number of the alien in the group
	 * @param numAlien
	 */
	public AlienGroup(Model model) {
		this.model = model;
	}
	
	/**The method to create the group of aliens.
	 * 
	 * @param numAlien
	 * @return a set with the aliens 
	 */
	public Set<Alien> createAlienGroup(int numAlien){
		this.expectedCollision = EdgeCollision.LEFT;
		Set<Alien> alienGroup = new HashSet<>();
		int alienInserted = 0;
		int rows = numAlien / this.MAX_ALIEN_PER_COLUMN;
		int spacingX = EntityConstants.Alien.INITIAL_WIDTH * 2;
		int spacingY = EntityConstants.Alien.INITIAL_HEIGHT * 2;
		Pair<Integer, Integer> lastPos;
		rows += numAlien % this.MAX_ALIEN_PER_COLUMN == 0 ? 0 : 1;
		for(int i = 0; i < rows; i++) {
			lastPos=new Pair<>((int)model.getMaxWorldWidth() - EntityConstants.Alien.INITIAL_WIDTH, (i+1)*spacingY);
			for(int j = 0; j < this.MAX_ALIEN_PER_COLUMN; j++) {
				if(alienInserted >= numAlien) {
					break;
				}
				if(i == 0) {
					alienGroup.add(new Alien(lastPos.getX(), lastPos.getY(), this, model, SpecificEntityType.ALIEN_1));
				} else if(i == 1) {
					alienGroup.add(new Alien(lastPos.getX(), lastPos.getY(), this, model, SpecificEntityType.ALIEN_2));
				} else {
					alienGroup.add(new Alien(lastPos.getX(), lastPos.getY(), this, model, SpecificEntityType.ALIEN_3));
				}
				lastPos = new Pair<>(lastPos.getX() - spacingX, lastPos.getY());
				alienInserted++;
			}
		}
		return alienGroup;
	}
	
	/**
	 * The method that moves down all the aliens
	 */
	public void alienGroupDown(EdgeCollision collision) {
		if(this.expectedCollision.equals(collision)) {
			this.model.getAlienList().stream().forEach(i->i.changeDirection());
			if(this.expectedCollision.equals(EdgeCollision.LEFT)) {
				this.expectedCollision = EdgeCollision.RIGHT;
			} else {
				this.expectedCollision = EdgeCollision.LEFT;
			}
		}
	}

	/**
	 * The method that lets the aliens shoot
	 */
	public void shoot(int cycle) {
		Set<Alien> alienShootingAtTheSameTime = new HashSet<>();
		if(this.isTimeToShoot(cycle)) {
			while(alienShootingAtTheSameTime.size() < random.nextInt(this.MAX_ALIEN_SHOOTING) + this.MIN_ALIEN_SHOOTING) {
				alienShootingAtTheSameTime.add((Alien) this.model.getAlienList().get(random.nextInt(this.model.getAlienList().size())));
			}
			alienShootingAtTheSameTime.stream().forEach(i->i.shoot());
		}
	}

	/**
	 * A method that return true if at least one of the alien can shoot
	 * @param cycles
	 * @return true if at least one of the aliens can shoot, false otherwise 
	 */
	private boolean isTimeToShoot(int cycles) {
		for(var e : this.model.getAlienList()) {
			return e.canShoot(cycles);
		}
		return false;
	}
}
