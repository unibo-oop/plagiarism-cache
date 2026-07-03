package it.unibo.roguekong.model.value.impl;

import it.unibo.roguekong.model.value.Position;

public class PositionImpl implements Position{
    private double x=0;
    private double y=0;

    /**
     * void constructor with default values (x=0, y=0)
     */
    public PositionImpl(){
        this(0, 0);
    }

    /**
     * constructor with x and y params
     * @param x positions of x
     * @param y position of y
     */
    public PositionImpl(double x, double y){
        setX(x);
        setY(y);
    }

    /**
     * constructor with another PositionImpl param
     * @param position entire position class
     */
    public PositionImpl(Position position){
        setX(position.getX());
        setY(position.getY());
    }

    public void setX(double x){
        this.x = x;
    }

    public void setY(double y){
        this.y = y;
    }

    public double getX(){
        return this.x;
    }
    public double getY() {
        return this.y;
    }

    /**
     * method to check if another object is equals to this one
     * @param other the reference object with which to compare.
     * @return boolean value, true if this PositionImpl is equal to another, false if is not
     */
    @Override
    public boolean equals(Object other) {
        if(this == other) return true;

        if(!(other instanceof Position otherPosition)) return false;

        return Double.compare(this.x, otherPosition.getX()) == 0 && Double.compare(this.y, otherPosition.getY()) == 0;
    }
}
