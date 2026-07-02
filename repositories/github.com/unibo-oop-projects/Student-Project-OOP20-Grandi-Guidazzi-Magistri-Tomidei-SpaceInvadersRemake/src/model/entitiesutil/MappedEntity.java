package model.entitiesutil;

import model.entities.SpecificEntityType;
import util.Pair;

public interface MappedEntity {

	Pair<Double, Double> getPos();

	double getX();

	double getY();

	int getWidth();

	int getHeight();

	SpecificEntityType getEntityType();

}