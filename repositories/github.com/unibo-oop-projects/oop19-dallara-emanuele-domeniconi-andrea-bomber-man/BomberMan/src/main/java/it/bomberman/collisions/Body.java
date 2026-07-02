package it.bomberman.collisions;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Body {
	public List<Shape> boundingShapes;

	public Body() {
		this.boundingShapes = new ArrayList<Shape>();
	}
	
	public Body(Shape s) {
		this();
		this.add(s);
	}

	public void add(Shape s) {
		this.boundingShapes.add(s);
	}

	public boolean hasBoundingShapes() {
		return this.boundingShapes.isEmpty();
	}

	public boolean checkCollision(Body other) {
		if(this.equals(other))
			return false;
		return this.checkCollision(other.getBoundingShapes());
	}

	public boolean checkCollision(List<Shape> otherBoundingShapes) {
		if (this.boundingShapes.isEmpty() || otherBoundingShapes.isEmpty()) {
			return false;
			// oppure throw new Exception?
		}

//		prova con lambda functions
//		return this.boundingShapes.stream()
//				.filter(s -> s.intersectsAny(otherBoundingShapes))
//				.count() > 0;

		boolean out = false;
		// filtrare ogni shape per sapere se almeno una collide con almeno una delle shape dell'otherBody 
		for(var s : this.boundingShapes) {
			for(var otherS : otherBoundingShapes) {
				if(s.intersects(otherS)) {
					out = true;
				}
			}
		}
		return out;
	}

	public List<Shape> getBoundingShapes() {
		// restituisco una versione protetta di bounding shape per mantenere
		// l'incapsulamento
		return Collections.unmodifiableList(this.boundingShapes);
	}
	
	public void move(int x, int y) {
		for(Shape s : this.boundingShapes) {
			s.move(x, y);			
		}
	}
	
	public void render(Graphics g, Color color) {
		// debug only
		for(Shape s : this.boundingShapes) {
			s.render(g, color);
		}
	}
}
