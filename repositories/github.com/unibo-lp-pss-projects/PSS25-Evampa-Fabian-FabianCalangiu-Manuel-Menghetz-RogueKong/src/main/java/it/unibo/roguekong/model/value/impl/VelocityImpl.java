package it.unibo.roguekong.model.value.impl;

import it.unibo.roguekong.model.value.Velocity;
public class VelocityImpl implements Velocity {
    private double velocityX;
    private double velocityY;

    /**
     * constructor with x and y params
     * @param x velocity in x
     * @param y velocity in y
     */
    public VelocityImpl(double x, double y){
        setVelocityX(x);
        setVelocityY(y);
    }

    /**
     * void constructor with default values (x=1, y=1)
     */
    public VelocityImpl(){
        setVelocityX(1);
        setVelocityY(1);
    }

    /**
     * constructor with another VelocityImpl param
     * @param velocity entire velocity class
     */
    public VelocityImpl(Velocity velocity){
        setVelocityX(velocity.getVelocityX());
        setVelocityY(velocity.getVelocityY());
    }

    public void setVelocityX(double velocityX){
        this.velocityX = velocityX;
    }
    public void setVelocityY(double velocityY){
        this.velocityY = velocityY;
    }

    @Override
    public double getVelocityX(){
        return velocityX;
    }

    @Override
    public double getVelocityY(){
        return velocityY;
    }

    /**
     * resets the standard velocity
     */
    public void resetVelocity(){
        this.velocityX = 1;
        this.velocityY = 1;
    }
}
