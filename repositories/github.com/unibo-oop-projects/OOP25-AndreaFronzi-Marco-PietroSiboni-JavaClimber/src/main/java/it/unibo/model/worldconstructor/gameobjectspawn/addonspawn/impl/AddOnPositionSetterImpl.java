package it.unibo.model.worldconstructor.gameobjectspawn.addonspawn.impl;

import it.unibo.model.gameobj.api.GameObject;
import it.unibo.model.physics.impl.Vector2dImpl;
import it.unibo.model.worldconstructor.gameobjectspawn.addonspawn.api.AddOnPositionSetter;

/**
 * Implementation of AddOnPositionGenerator.
 * Calculates position for add-ons relative to their host platform.
 */
public class AddOnPositionSetterImpl implements AddOnPositionSetter {

    /**
     * {@inheritDoc}
     */
    @Override
    public <X extends GameObject> X generatePosition(final X addOn, final double platformWidth) {
        final double posX = this.generatePosX(addOn, platformWidth);
        final double posY = this.generatePosY(addOn, addOn.getPosY());
        addOn.setPosition(new Vector2dImpl(posX, posY));
        return addOn;
    }

    private <X extends GameObject> double generatePosX(final X addOn, final double platformWidth) {
        return addOn.getPosX() + (platformWidth / 2) - (addOn.getWidth() / 2);
    }

    private <X extends GameObject> double generatePosY(final X addOn, final double platformPosY) {
        return platformPosY - addOn.getHeight(); // posizione iniziale della piattaforma + altezza dell'add-on
    }

}
