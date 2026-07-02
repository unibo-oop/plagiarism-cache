package model.tower;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import model.enemy.Enemy;
import model.entity.Entity;
import model.observer.ObservableEntity;
import model.projectile.Projectile;
import utilityclasses.Pair;

public class BasicTower extends ObservableEntity implements Tower {
	private static final int gridSize = 20;
	Pair<Integer, Integer> position; 
	private int damage, range;
	private float shootTime;
	private Enemy target;
	private TowerType type;
	private boolean isShooting;
	private ArrayList<Pair<Integer, Integer>> shootingZone;
	private List<Entity> enemies; 
	private Projectile projectile;
	
	@Override
	public Projectile getProjectile() {
		return projectile;
	}


	public BasicTower(Pair<Integer, Integer> position, TowerType type) {
		this.position = position;
		this.damage = type.getDamage();
		this.range = type.getRange();
		this.target = null;
		this.shootTime = 20;
		this.type = type;
		this.isShooting = false;
		this.shootingZone = new ArrayList<Pair<Integer, Integer>>();
		setRange();
		this.enemies = new ArrayList<>();
	}
	
	private void findTarget() {
		for (Entity e: enemies) {
			for (int i = 0; i < shootingZone.size(); i++) {
				if (e.getLocation().equals(shootingZone.get(i))) {
					if (e instanceof Enemy) {
						this.target = (Enemy) e;
						System.out.println("target acquired");
						return;
					} else {
						throw new IllegalArgumentException();
					}
				}
			}
		}
		this.target = null;
	}
	
	private void setRange() {
		
		for (int i = position.getX() - range; i <= position.getX() + range; i++) {
			for (int j = position.getY() - range; j <= position.getY() + range; j++) {				
				if(position.getX() < gridSize && position.getY() < gridSize) {
					shootingZone.add(new Pair<>(i, j));
				}
			}
		}
	}
	
	@Override
	public void setEnemies(ArrayList<Entity> entities) {
		this.enemies = entities.stream()
				.filter(e -> e instanceof Enemy)
				.collect(Collectors.toList());
	}

	@Override
	public Pair<Integer, Integer> getLocation() {
		return position;
	}

	@Override
	public void update() {
		findTarget();
		if (isTargetSet()) {
			this.projectile = shoot();
			this.notifyObservers();
		}
	}


	@Override

	public TowerType getType() {
		return type;
	}
	
	@Override
	public void setType(final TowerType type) {
		this.type = type;
	}
	

	@Override
	public float getShootTime() {
		return shootTime;
	}

	

	private Projectile shoot() {
		return new Projectile(position, target, damage);
	}

	@Override
	public boolean isShooting() {
		return isShooting;
	}

	@Override
	public boolean isTargetSet() {
		if (target == null) {
			return false;
			}
			return true;	
	}

	@Override
	public Enemy getTarget() {
		return target;
	}


	@Override
	public boolean shouldBeRemoved() {
		return false;
	}
	
}
