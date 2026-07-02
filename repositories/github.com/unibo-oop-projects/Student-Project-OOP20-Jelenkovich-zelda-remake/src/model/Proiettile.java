package model;

public class Proiettile {
	
	private static final int HEIGHT = 8;
	private static final int WIDTH = 8;
	private static final String IMG_PATH = "res/img/proiettile.png";
	
	private double posX;
	private double posY;
	private double posFinalx;
	private double posFinaly;
	private double posInitx;
	private double posInity;
	private String imgPath;
	
	public Proiettile (double x, double y, double posFinalx, double posFinaly){
		this.posInitx = x;
		this.posInity = y;
		this.posX = x;
		this.posY = y;
		this.posFinalx = posFinalx;
		this.posFinaly = posFinaly;
		this.imgPath = IMG_PATH;
	}

	public void setX(int x) {
		this.posX = x;
	}
	
	public void setY(int y) {
		this.posY = y;
	}
	
	public int getHeight() {
		return HEIGHT;
	}

	public int getWidth() {
		return WIDTH;
	}

	public double getX() {
		return this.posX;
	}
	
	public double getY() {
		return this.posY;
	}

	public double getPosFinaly() {
		return this.posFinaly;
	}

	public double getPosFinalx() {
		return this.posFinalx;
	}

	public String getImgPath() {
		return imgPath;
	}
	
	public double getInitx() {
		return this.posInitx;
	}
	
	public double getInity() {
		return this.posInity;
	}
	
}
