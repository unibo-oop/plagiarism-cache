package it.unibo.javacrush.model;

import it.unibo.javacrush.model.api.GravityEngine;
import it.unibo.javacrush.model.impl.gravity.DownwardGravity;

/**
 * Test class for {@link DownwardGravityTest} implementation.
 */
public class DownwardGravityTest extends AbstractGravityTest {

    /**
     * {@inheritDoc}
     */
    @Override
    protected GravityEngine createGravity() {
        return new DownwardGravity();
    }
}
