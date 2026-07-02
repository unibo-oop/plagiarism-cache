package model.people;

public class Hero implements Entity {
	
	private static final int LIFE = 3;
    private static final int HERO_WIDTH = 40;
    private static final int HERO_HEIGHT = 50;
    private static final String IMG_PATH = "res/img/hero.png";
	
	private int posX;
	private int posY;
	private int life;
	private String imgPath;
	
	public Hero (int initx, int inity){
		this.life = LIFE;
		this.posX = initx;
		this.posY = inity;
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

	public int getHeroWidth() {
		return HERO_WIDTH;
	}

	public int getHeroHeight() {
		return HERO_HEIGHT;
	}
	
	@Override
	public String getImgPath() {
		return this.imgPath;
	}

	public void downLife() {
		this.life--;
	}

	public int getLife() {
		return life;
	}

	
}