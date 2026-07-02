package model.staticobj;

public class StaticObj implements IStaticObj{
	
	private static final int HEIGHT = 50;
	private static final int WIDTH = 50;
	
	
	private int posX;
	private int posY;
	private String imgPath;
	
	public StaticObj(int posX, int posY, String imgPath) {
		this.setX(posX);
		this.setY(posY);
		this.setImgPath(imgPath);
	}

	@Override
	public int getX() {
		return posX;
	}

	@Override
	public void setX(int posX) {
		this.posX = posX;
	}

	@Override
	public int getY() {
		return posY;
	}
	
	@Override
	public void setY(int posY) {
		this.posY = posY;
	}

	@Override
	public String getImgPath() {
		return imgPath;
	}

	@Override
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	@Override
	public int getWidth() {
		return WIDTH;
	}

	@Override
	public int getHeight() {
		return HEIGHT;
	}

}
