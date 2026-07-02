package gameEnemiesManager;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import util.Resource;

public class EnemiesManager {
	
	private BufferedImage cactus1;	//image of a type of cactus
	private BufferedImage cactus2;	//image of an another type of cactus
	private Random rand;
	private List<Enemy> enemies;	//list with a enemies
	private MainCharacter mainCharacter;
	
	public EnemiesManager() {
		rand = new Random();
		cactus1 = Resource.getResouceImage("res/cactus1.png");	//upload image of cactus
		cactus2 = Resource.getResouceImage("res/cactus2.png");	//upload image of cactus
		enemies = new ArrayList<Enemy>();
		this.mainCharacter = mainCharacter;
		enemies.add(createEnemy());								//upload an enemies
	}
	
	/* Create an Enemy */
	private Enemy createEnemy() {
		int type = rand.nextInt(2);		//two different type of image of cactus-enemy
		if(type == 0) {
			return new Cactus(mainCharacter, 800, cactus1.getWidth() - 10, cactus1.getHeight() - 10, cactus1);
		} else {
			return new Cactus(mainCharacter, 800, cactus2.getWidth() - 10, cactus2.getHeight() - 10, cactus2);
		}
	}
	
	/* Manage Collision with Enemies */
	public boolean isCollision() {
		for(Enemy e : enemies) {										//control collision with all enemies
			if (mainCharacter.getBound().intersects(e.getBound())) {
				return true;
			}
		}
		return false;
	}
	
	public void reset() {
		enemies.clear();			//reset all enemies
		enemies.add(createEnemy());	//create new enemies
	}
	
	/* Update Enemies */
	public void update() {
		for(Enemy e : enemies) {
			e.update();
		}
		Enemy enemy = enemies.get(0);
		if(enemy.isOutOfScreen()) {
			mainCharacter.upScore();
			enemies.clear();
			enemies.add(createEnemy());
		}
	}
	
	/* Print Enemies */
	public void drawEnvironment(Graphics env) {
		for(Enemy e : enemies) {
			e.drawEnvironment(env);
		}
	}
}
