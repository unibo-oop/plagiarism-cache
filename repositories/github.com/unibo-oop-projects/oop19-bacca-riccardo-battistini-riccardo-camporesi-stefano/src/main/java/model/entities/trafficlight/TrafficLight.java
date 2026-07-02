package model.entities.trafficlight;

import constraints.DirOfMovement;
import model.environment.Point;

public interface TrafficLight extends TrafficLightObserver {

    /**
     * 
     */
    enum Phases {
        GREEN, RED, YELLOW
    }

    /**
     * @return the status of the traffic light
     */
    Phases getCurrentPhase();

    /**
     * 
     * @param position where the stop line is located.
     */
    void setPosition(Point position);

    /**
     * 
     * @return position of the traffichLight
     */
    Point getPosition();

    /**
     * 
     * @return sense of the lane that it's responsible
     */
    DirOfMovement getSense();

    /**
     * 
     * @param sense of the lane that it will be responsible
     */
    void setSense(DirOfMovement sense);

}
