package model.people;

public class Enemy implements Entity{

	private static final int ENEMY_WIDTH = 40;
	private static final int ENEMY_HEIGHT = 50;
	private static final String IMG_PATH = "res/img/enemy.png";
		
	private int posX;
	private int posY;
	private int prevx;
	private int prevy;
	private String imgPath;
		
	public Enemy (int x, int y){
		this.posX = x;
		this.posY = y;
		this.prevx = x;
		this.prevy = y;
		this.imgPath = IMG_PATH;
	}

	@Override
	public int getY() {
		return this.posY;
	}

	@Override
	public int getX() {
		return this.posX;
	}
		
	@Override
	public void setX(int x) {
		this.posX = x;
	}

	@Override
	public void setY(int y) {
		this.posY = y;
	}
	
	public void setPosition(int x, int y) {
		this.setX(x);
		this.setY(y);
	}

	public int getEnemyWidth() {
		return ENEMY_WIDTH;
	}

	public int getEnemyHeight() {
		return ENEMY_HEIGHT;
	}

	@Override
	public String getImgPath() {
		return this.imgPath;
	}

	/**
	 * salvataggio coordinate della posizione precedente
	 * @param x
	 * @param y
	 */
	public void setPrevPos(double x, double y) {
		this.prevx = (int)x;
		this.prevy = (int)y;
	}
	
	/**
	 * 
	 * @return coordinata x precedente al movimento
	 */
	public int getPrevx() {
		return this.prevx;
	}
	
	/**
	 * 
	 * @return coordinata y precedente al movimento
	 */
	public int getPrevy() {
		return this.prevy;
	}
	
}