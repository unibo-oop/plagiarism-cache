package model.entities;

import javafx.util.Pair;

public class Bar implements IBar {

	static final int MOVE_FOR_PRESSION = 5;
	static final int MODIFY = 40; //Pixel aggiunti o sottratti alla barra per reduceBar() o extendBar()  
	
	private int x;
	private int y;
	private int width;
	private int height;
	private double arcWidth;
	private double arcHeight;
	
	Bar(final int x, final int y, final int width, final int height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	Bar(final int x, final int y, final int width, final int height, final double arcWidth, final double arcHeight){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.arcHeight = arcHeight;
		this.arcWidth = arcWidth;
	}

    @Override
    public int getMinX() {
        return this.x;
    }

	@Override
    public int getMaxX() {
        return this.x + this.width;
    }

    @Override
    public int getMinY() {
        return this.y;
    }

	@Override
    public int getMaxY() {
        return this.y + height;
    }

	@Override
    public void setPosition(int newX, int newY) {
    	this.x = newX;
    	this.y = newY;
    }

	@Override
    public Pair<Integer, Integer> getPosition() {
    	return new Pair<Integer, Integer>(this.x, this.y);
    }

    public int getLenght() {
        return this.width;
    }

    //Interf. IBar

	@Override
	public void moveRight() {
		System.out.println("Mi muovo a destra");
		this.x = this.x + MOVE_FOR_PRESSION;
	}

	@Override
	public void moveLeft() {
		System.out.println("Mi muovo a sinistra");
		this.x = this.x - MOVE_FOR_PRESSION;
	}

	@Override
	public void extendBar() {
		this.width = this.width + MODIFY;
		this.x = this.x - MODIFY / 2;
	}

	@Override
	public void reduceBar() {
		this.width = this.width - MODIFY;
		this.x = this.x + MODIFY / 2;
	}

	@Override
	public void shootingBar() {
		System.out.println("Ciao sono la barra shooting");
	}
	
	public double getArcWidth() {
		return this.arcWidth;
	}
	public double getArcHeight() {
		return this.arcHeight;
	}
}
