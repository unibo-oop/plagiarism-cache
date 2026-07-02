package model.physics;

import java.io.Serializable;

import model.entities.GameBoardImpl;
import model.entities.GameObject;

public class ComponentPhysicsEmpty implements ComponentPhysics, Serializable {

    private static final long serialVersionUID = -4098502184492343004L;

    /**
    *
    * {@inheritDoc}
    */
    @Override
    public void update(final int timeElapsed, final GameObject gameObject, final GameBoardImpl board) {
        //this physical component does nothing.
    }

}
