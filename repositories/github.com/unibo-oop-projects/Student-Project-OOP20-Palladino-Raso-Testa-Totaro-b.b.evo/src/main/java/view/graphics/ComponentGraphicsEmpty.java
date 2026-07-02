package view.graphics;

import java.io.Serializable;

import model.entities.GameObject;

public class ComponentGraphicsEmpty implements ComponentGraphics, Serializable {

    private static final long serialVersionUID = 520121868558537070L;

    /**
    *
    * {@inheritDoc}
    */
    @Override
    public void update(final GameObject obj, final AdapterGraphics w) {
        //this method do nothing
    }
}
