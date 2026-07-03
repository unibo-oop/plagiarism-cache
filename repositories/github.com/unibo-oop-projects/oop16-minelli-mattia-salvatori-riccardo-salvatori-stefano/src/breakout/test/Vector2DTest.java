
package breakout.test;



import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import breakout.model.physics.Vector2D;

/**
 * Vector2D test.
 *
 */
public class Vector2DTest {

    private static final double EPS = 0.00000001; // Epsilon for double testing precision

    /**
     * Test.
     */
    @Test
    public void test() {
        final Vector2D zero = Vector2D.ZERO;
        final Vector2D v1 = Vector2D.valueOfCartesian(1, 1);
        final Vector2D v2 = Vector2D.valueOfPolar(Math.sqrt(2), 45);

        assertSame(zero, Vector2D.valueOfCartesian(0, 0));
        assertSame(zero, Vector2D.valueOfPolar(0, 0));
        assertEquals(v1.getMagnitude(), v2.getMagnitude(), EPS);
        v2.setAngle(90);
        assertEquals(v2.getY(), v2.getMagnitude(), EPS);
        v2.setAngle(0);
        assertEquals(v2.getX(), v2.getMagnitude(), EPS);
        v1.setMagnitude(0);
        assertEquals(v1.getX(), 0, EPS);
        assertEquals(v1.getY(), 0, EPS);

        final Vector2D v3 = Vector2D.valueOfPolar(Math.sqrt(2), 45);
        v3.setAngle(-v2.getAngle());
        System.out.println(v2);
        System.out.println(v3);
        assertThat(v2.getX(), CoreMatchers.is(v3.getX()));
        assertThat(v2.getY(), CoreMatchers.is(-v3.getY()));
    }

}
