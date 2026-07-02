package controller.input;

import java.io.Serializable;

import model.entities.GameObject;

public class ComponentInputEmpty implements ComponentInput, Serializable {

    private static final long serialVersionUID = 912897323541408067L;

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void update(final GameObject obj, final ControllerInput controlInput) {
      //this component is an empty entities, so it does nothing.
    }
}
