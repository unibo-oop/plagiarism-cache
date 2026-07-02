package model.entities.vehicle;

import constraints.DirOfMovement;
import model.environment.Point;
import constraints.VehicleConstraints.Status;

public interface Vehicle {


/**
 * @return the vehicle velocity. 
 */
int getVelocity();

/**
 * 
 * @return the sight of the driver
 */
int getAreaOfControl();

/**
 * 
 * @param pos new position of the vehicle
 */
void setPosition(Point pos);

Point getPosition();

/**
 * 
 * @return the start position of the vehicle
 */
Point getDeparture();

/**
 * 
 * @return the ending position of the vehicle
 */
Point getDestination();

/**
 * 
 * @param velocity increase the vehicle velocity.
 */
void setVelocity(int velocity);

/**
 * 
 * @return vehicle sense
 */
DirOfMovement getSense();

/**
 * 
 * @return vehicle status
 * 
 */
Status getStatus();

/**
 * 
 * @param status of the vehicle 
 */
void setStatus(Status status);


String toString();

/**
 * 
 * @return vehicle maxVel.
 */
int getMaxVel();


/**
 * 
 * @param maxVel max velocity that a vehicle can reach
 */
void setMaxVel(int maxVel);
}
