package it.bomberman.collisions;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Set;

public abstract class Shape {

	// indica l'angolo in alto a sinistra
	protected Vector2 position; 
	// (0,0)------->x
	// |
	// |
	// v
	// y

	public Shape(int x, int y) {
		this.position = new Vector2(x, y);
	}

	public Vector2 getPosition() {
		return this.position;
	}

	public void setPosition(int x, int y) {
		this.position.setX(x);
		this.position.setY(y);
	}

	public abstract boolean intersects(Shape shape);

	public boolean intersectsAny(Set<Shape> otherBoundingShapes) {
		return otherBoundingShapes.stream()
				.filter(s -> this.intersects(s)) // filtra tutte le shapes che intersecano this
				.count() > 0; // se ne trova più di una allora restituisce true
	}

	/**
	 * DEBUG ONLY
	 * @param g
	 */
	public abstract void render(Graphics g, Color color);

	public abstract void move(int x, int y);
}
