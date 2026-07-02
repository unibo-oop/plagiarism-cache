package it.unibo.javacrush.model;

import it.unibo.javacrush.model.api.GravityEngine;
import it.unibo.javacrush.model.impl.gravity.RightwardGravity;

/**
 * Test class for {@link RightwardGravityTest} implementation.
 */
public class RightwardGravityTest extends AbstractGravityTest {

    /**
     * {@inheritDoc}
     */
    @Override
    protected GravityEngine createGravity() {
        return new RightwardGravity();
    }
}
