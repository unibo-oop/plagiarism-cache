package it.bomberman.collisions;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Rectangle extends Shape {
	// quale struttura dati utilizzare?
	// sono sicuro che saranno sempre 4 punti.
	// � imortante l'ordine dei punti
	protected final List<Vector2> vertices;
	protected int width; // final ?
	protected int height; // final ?

	public Rectangle(int x, int y, int width, int height) {
		super(x, y);
		this.vertices = new ArrayList<Vector2>(4);
		init(x, y, width, height);
	}

	// se reso pubblico stiamo implicitamente accettando rettangoli con rotazione
	// inoltre si rendono necessari ulteriori controlli
	private Rectangle(int x, int y, Vector2 p1, Vector2 p2, Vector2 p3, Vector2 p4) {
		super(x, y);
		this.vertices = new ArrayList<Vector2>(4);
	}

	private final void init(int x, int y, int width, int height) {
		initPoints(new Vector2(x, y), new Vector2(x + width, y), new Vector2(x + width, y + height),
				new Vector2(x, y + height));
		this.width = width;
		this.height = height;
	}

//	private void init(Vector2 p1, Vector2 p2, Vector2 p3, Vector2 p4) {
//		initPoints(p1, p2, p3, p4);
//		// giusto?
//		this.width = this.getBottomRight().getY() - this.getBottomRight().getY();
//		this.height = this.getBottomRight().getX() - this.getBottomRight().getX();
//	}

	private void initPoints(Vector2 p1, Vector2 p2, Vector2 p3, Vector2 p4) {
		this.vertices.add(p1);
		this.vertices.add(p2);
		this.vertices.add(p3);
		this.vertices.add(p4);
	}

	public List<Vector2> getVertices() {
		return Collections.unmodifiableList(this.vertices);
	}

	@Override
	public boolean intersects(Shape shape) {
		if(shape instanceof Rectangle) {
			return this.intersects((Rectangle)shape);
		}
		if(shape instanceof Circle) {
			return shape.intersects(this);
		}
		return false;
	}

	public boolean intersects(Circle c) {
		return c.intersects(this);
	}

	public boolean intersects(Rectangle other) {
		if (this.getBottomRight().getX() <= other.getTopLeft().getX()
				|| this.getTopLeft().getX() >= other.getBottomRight().getX()) {
			return false;
		}
		if (this.getBottomRight().getY() <= other.getTopLeft().getY()
				|| this.getTopLeft().getY() >= other.getBottomRight().getY()) {
			return false;
		}
		return true;
	}

	// getTopLeft dovrebber cambiare se i punti del triangolo fossero dati in ordine
	// sparso
	public Vector2 getTopLeft() {
		// rendere template method?
		return getVertices().get(0);
	}

	public Vector2 getBottomRight() {
		return getVertices().get(2);
	}

	public boolean pointInsideRectangle(Vector2 point) {
		if (getTopLeft().getX() < point.getX() && getTopLeft().getY() < point.getY()
				&& getBottomRight().getX() > point.getX() && getBottomRight().getY() > point.getY()) {
			return true;
		}
		return false;
		
		/*
		 * 0 <= AP�AB <= AB�AB and 0 <= AP�AD <= AD�AD double ap =
		 * this.vertices.get(0).distance(point); double ab =
		 * this.vertices.get(0).distance(this.vertices.get(1)); double ad =
		 * this.vertices.get(0).distance(this.vertices.get(3));
		 */
		// if(0 <= AP*AB)
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	@Override
	public void render(Graphics g, Color color) {
		// DEBUG ONLY
		g.drawRect(this.position.getX(),this.position.getY(),this.width,this.height);  
		g.setColor(color);  
	    g.fillRect(this.position.getX(),this.position.getY(),this.width,this.height);  
	}
	
	public void move(int x, int y) {
		this.setPosition(x,y);
		this.vertices.get(0).setX(x);
		this.vertices.get(0).setY(y);
		this.vertices.get(1).setX(x + this.width);
		this.vertices.get(1).setY(y);
		this.vertices.get(2).setX(x + this.width);
		this.vertices.get(2).setY(y + this.height);
		this.vertices.get(3).setX(x);
		this.vertices.get(3).setY(y + this.height);
	}
}
