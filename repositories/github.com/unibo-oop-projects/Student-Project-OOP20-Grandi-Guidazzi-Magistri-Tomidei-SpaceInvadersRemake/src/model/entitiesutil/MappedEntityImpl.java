package model.entitiesutil;

import model.entities.SpecificEntityType;
import util.Pair;

public class MappedEntityImpl implements MappedEntity {

	private final SpecificEntityType type;
	private final Pair<Double, Double> position;
	private final int width;
	private final int height;

	public MappedEntityImpl(SpecificEntityType type, double x, double y, int width,int height) {
		this.position = new Pair<>((double)x, (double)y);
		this.width = width;
		this.height = height;
		this.type = type;
	}

	@Override
	public Pair<Double, Double> getPos() {
		return this.position;
	}

	@Override
	public double getX() {
		return this.position.getX();
	}

	@Override
	public double getY() {
		return this.position.getY();
	}

	@Override
	public int getWidth() {
		return this.width;
	}

	@Override
	public int getHeight() {
		return this.height;
	}

	@Override
	public SpecificEntityType getEntityType() {
		return this.type;
	}

	
}
