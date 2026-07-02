package it.unibo.javacrush.model;

import it.unibo.javacrush.model.api.GravityEngine;
import it.unibo.javacrush.model.impl.gravity.LeftwardGravity;

/**
 * Test class for {@link LeftwardGravityTest} implementation.
 */
public class LeftwardGravityTest extends AbstractGravityTest {

    /**
     * {@inheritDoc}
     */
    @Override
    protected GravityEngine createGravity() {
        return new LeftwardGravity();
    }
}
