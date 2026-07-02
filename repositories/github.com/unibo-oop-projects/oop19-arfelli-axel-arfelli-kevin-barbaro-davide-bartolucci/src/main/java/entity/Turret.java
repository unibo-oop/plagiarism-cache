package entity;

import javafx.util.Pair;

public class Turret extends SimpleEnemy {

    public static int BASE_LIFE = 12;
    private static int TURRET_BULLET_WIDTH = 8;
    private static int TURRET_BULLET_HEIGHT = 8;
    private static int TURRET_SCORE = 12;

    public Turret(int x, int y, int enemyLvl, int life) {
        super(x, y, enemyLvl);
        this.life = life;
    }
    
    public Turret(Pair<Integer, Integer> pos, int enemyLvl, int life) {
    	super(pos.getKey(), pos.getValue(), enemyLvl);
    	this.life = life;
    }
    
    public void update() {
        move();
        tryAndShoot();
    }

    protected void move() {
    	//La Turret resta immobile dove viene inizialmente spawnata
    	return;
    }
    
    public String getType() {
        return "Turret";
    }
    
    protected int getBulletWidth() {
        return TURRET_BULLET_WIDTH;
    }
    
    protected int getBulletHeight() {
        return TURRET_BULLET_HEIGHT;
    }

    @Override
    protected int getAngle() {
        return playerAngle();
    }

	@Override
	public int getScoreValue() {
		return TURRET_SCORE + level;
	}
}