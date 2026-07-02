package model.direction;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * 
 * Simple tests for directions methods.
 *
 */
public class TestDirection {

    /**
     * DirectionDecriptor test.
     */
    @Test
    public void testDirectionDecriptorImpl() {
        int i = 0;
        assertEquals(DirectionDecryptor.getDirection(i--), DirectionEnum.EAST);
        assertEquals(DirectionDecryptor.getDirection(i), DirectionEnum.SOUTHEAST);
        i = i  - DirectionEnum.SIZE;
        assertEquals(DirectionDecryptor.getDirection(i++), DirectionEnum.SOUTHEAST);
        assertEquals(DirectionDecryptor.getDirection(i), DirectionEnum.EAST);
        i = i + DirectionEnum.SIZE;
        assertEquals(DirectionDecryptor.getDirection(i++), DirectionEnum.EAST);
        assertEquals(DirectionDecryptor.getDirection(i++), DirectionEnum.NORTHEAST);
        assertEquals(DirectionDecryptor.getDirection(i++), DirectionEnum.NORTH);
        assertEquals(DirectionDecryptor.getDirection(i++), DirectionEnum.NORTHWEST);
        assertEquals(DirectionDecryptor.getDirection(i++), DirectionEnum.WEST);
        assertEquals(DirectionDecryptor.getDirection(i++), DirectionEnum.SOUTHWEST);
        assertEquals(DirectionDecryptor.getDirection(i++), DirectionEnum.SOUTH);
        assertEquals(DirectionDecryptor.getDirection(i++), DirectionEnum.SOUTHEAST);
        assertEquals(DirectionDecryptor.getDirection(i), DirectionEnum.EAST);

    }
}
