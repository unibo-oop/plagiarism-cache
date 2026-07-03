package tests.utils;


import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import utils.CircularList;
import utils.CircularListImpl;

/**
 * A class for testing {@link utils.CircularList}.
 */
public class TestCircularList {

    private static final String PRIMO = "primo";
    private static final String SECONDO = "secondo";
    private static final String TERZO = "terzo";
    private static final String QUARTO = "quarto";
    /**
     * 
     */
    @Test
    public void behaviourTest() {
        final CircularList<String> list = new CircularListImpl<String>(
                new ArrayList<String>(Arrays.asList(PRIMO, SECONDO, TERZO, QUARTO)));
        assertEquals(list.size(), 4);
        assertEquals(list.getHead(), PRIMO);

        list.shift();
        assertEquals(list.getHead(), SECONDO);

        list.shift();
        list.shift();
        assertEquals(list.getHead(), QUARTO);

        list.remove(PRIMO);
        assertEquals(list.size(), 3);

        list.shift();
        assertEquals(list.getHead(), SECONDO);

        list.remove(SECONDO);
        assertEquals(list.getHead(), TERZO);

        list.setHead(1);
        assertEquals(list.getHead(), QUARTO);
    }
}
