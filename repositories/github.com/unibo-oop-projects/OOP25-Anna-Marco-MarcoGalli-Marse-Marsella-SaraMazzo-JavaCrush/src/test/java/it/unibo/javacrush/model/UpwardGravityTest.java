package it.unibo.javacrush.model;

import it.unibo.javacrush.model.api.GravityEngine;
import it.unibo.javacrush.model.impl.gravity.UpwardGravity;

/**
 * Test class for {@link UpwardGravityTest} implementation.
 */
public class UpwardGravityTest extends AbstractGravityTest {

    /**
     * {@inheritDoc}
     */
    @Override
    protected GravityEngine createGravity() {
        return new UpwardGravity();
    }
}
