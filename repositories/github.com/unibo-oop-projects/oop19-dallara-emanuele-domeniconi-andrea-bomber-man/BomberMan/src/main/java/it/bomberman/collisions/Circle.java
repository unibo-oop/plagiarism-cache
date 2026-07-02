package it.bomberman.collisions;

import java.awt.Color;
import java.awt.Graphics;

public class Circle extends Shape {
	private int r;

	public Circle(int x, int y, int r) {
		super(x, y);
		this.r = r;
	}
	
	@Override
	public boolean intersects(Shape shape) {
		if(shape instanceof Rectangle) {
			return this.intersects((Rectangle) shape);
		}
		if(shape instanceof Circle) {
			return this.intersects((Circle) shape);
		}
		return false;
	}
	
	public boolean intersects(Circle circle) {
		double distance = this.position.distance(circle.getPosition());
		double radius = (double) r;

		if (distance <= radius) {
			return true;
		}
		return false;
	}

	public boolean intersects(Rectangle rectangle) {
		// variabili temporanee per testare i lati
		double cx = this.position.getX();
		double cy = this.position.getY();
		double rx = rectangle.getPosition().getX();
		double ry = rectangle.getPosition().getY();
		double rw = rectangle.getWidth();
		double rh = rectangle.getHeight();
		double radius = this.r;
		
		double testX = cx;
		double testY = cy;

		// serie di if per capire quale lato e' il piu vicino
		if (cx < rx)
			testX = rx; // prova lato sinistro
		else if (cx > rx + rw)
			testX = rx + rw; // lato destro
		if (cy < ry)
			testY = ry; // lato piu' alto
		else if (cy > ry + rh)
			testY = ry + rh; // lato piu' basso

		// calcola distanza dal lato piu vicino
		double distX = cx - testX;
		double distY = cy - testY;
		double distance = Math.sqrt((distX * distX) + (distY * distY));

		// se la distanza e' minore del raggio abbiamo una collisione
		if (distance <= radius) {
			return true;
		}
		return false;
	}

	@Override
	public void render(Graphics g, Color color) {
		// DEBUG ONLY
	}

	@Override
	public void move(int x, int y) {
		this.position.setX(x);
		this.position.setY(y);
	}



}
