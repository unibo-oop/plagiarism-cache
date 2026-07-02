package entity;

import virtualworld.VirtualMap;

public class PlayerShip extends SimpleShip {
    
    private static int PRIMARY_BULLET_WIDTH = 8;
    private static int PRIMARY_BULLET_HEIGHT = 8;
    private static int UP_ANGLE = 90;
    private int tempx=0;
    private int tempy=0;
    
    public PlayerShip(int x, int y) {
        super(x, y);
        super.life=Integer.MAX_VALUE;
    }
    
    @Override
    public void update() {
    	synchronized (this) {
    		this.getBody().move(tempx, tempy);
		}
    }

    @Override
    public String getType() {
        return "Player";
    }

    @Override
    public Faction getFaction() {
        return Faction.ALLY;
    }

    @Override
    protected int getBulletWidth() {
    	return PRIMARY_BULLET_WIDTH;
    }

    @Override
    protected int getBulletHeight() {
    	return PRIMARY_BULLET_HEIGHT;
    }
    
    public void fire() {
            shoot(UP_ANGLE);
    }

    @Override
    public void setMap(VirtualMap map) {
    	return;
    }

	@Override
	public int getScoreValue() {
		return 0;
	}
	
	public void move(int x, int y){
		synchronized (this) {
			this.tempx=x;
			this.tempy=y;
		}		
	}
}
