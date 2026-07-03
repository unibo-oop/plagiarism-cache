package model.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Dimension;
import java.util.Optional;

import org.junit.Test;

import model.entities.AbstractBarrel;
import model.entities.BarrelFactory;
import model.entities.BarrelFactoryImpl;
import model.entities.Mario;
import model.entities.MarioImpl;
import model.entities.Movement;

public class BarrelTest {

    @Test
    public void testBarrel() {
        final BarrelFactory bf = new BarrelFactoryImpl();
        final AbstractBarrel simpleBarrel = bf.createStandardBarrel(10.0, 20.0, new Dimension(100,100));
        final Mario tester = new MarioImpl(9.0, 20.0, new Dimension(100,100));
        tester.move(Optional.of(Movement.RIGHT));
        assertTrue("The barrel was supposed to hit Mario", simpleBarrel.isColliding(tester));
        /*TODO tester.move(Optional.of(Movement.LEFT));
        assertFalse("The barrel was not supposed to hit Mario", simpleBarrel.isColliding(tester));*/
        for(int i=0; i < 100; i++) {
            simpleBarrel.move(Optional.of(Movement.RIGHT));
            tester.move(Optional.of(Movement.RIGHT));
        }
        assertEquals("The barrel was supposed to be in the right position",Double.valueOf(110.0), Double.valueOf(simpleBarrel.getX()));
        assertTrue("The barrel was supposed to hit Mario", simpleBarrel.isColliding(tester));
    }

}
