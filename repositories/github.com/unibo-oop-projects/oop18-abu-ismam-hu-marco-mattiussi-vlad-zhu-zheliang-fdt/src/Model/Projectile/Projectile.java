package model.projectile;

import utilityclasses.Pair;
import model.enemy.Enemy;
import model.entity.Entity;

public class Projectile implements Entity  {
	Pair<Integer,Integer> position;
	
	private Enemy enemy;
	Pair<Integer, Integer> enemyPosition;
	private int damage, tick;
	private boolean alive;
	
	public Projectile(Pair <Integer,Integer> position, Enemy enemy, int damage){
		this.position = position;
		this.damage = damage;
		this.enemy = enemy;
		this.alive = true;
		this.enemyPosition = enemy.getLocation();
	}
	
	@Override
	public void update() {
		if (alive){
				tick++;
			if (tick == 1){
				enemy.setDamage(damage);
				tick = 0;
				alive = false;
			}
		}	
	}

	@Override
	public Pair<Integer, Integer> getLocation() {
		return position;
	}

	@Override
	public boolean shouldBeRemoved() {
		return !alive;
	}
}
