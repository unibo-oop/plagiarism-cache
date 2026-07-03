package maingame.input.mouseinput;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import maingame.input.ModelInput;
import util.Vector2Impl;

/**
 * Implementazione interfaccia MouseInput.
 */
public class MouseInputImpl extends MouseAdapter {

    private final ModelInput model;

    /**
     * @param modelInput
     *            model input del game a quale fa riferimento.
     */
    public MouseInputImpl(final ModelInput modelInput) {
        this.model = modelInput;
    }

    @Override
    public void mouseDragged(final MouseEvent e) {
        model.setMouseCoordinate(new Vector2Impl<>(e.getX(), e.getY()));
    }

    @Override
    public void mouseMoved(final MouseEvent e) {
        model.setMouseCoordinate(new Vector2Impl<>(e.getX(), e.getY()));
    }

    @Override
    public void mousePressed(final MouseEvent e) {
        model.setMouseButton(e.getButton());
    }

    @Override
    public void mouseReleased(final MouseEvent e) {
        model.setMouseButton(-1);
    }

}