package model.common;

public interface Position {

    double getX();

    double getY();

    void update(Movement movement);

    Position getSumPosition(double x, double y);

}
