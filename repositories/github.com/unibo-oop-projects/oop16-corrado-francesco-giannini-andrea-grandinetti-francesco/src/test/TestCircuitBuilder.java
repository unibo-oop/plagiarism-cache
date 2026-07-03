package test;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

import model.circuit.CircuitCreatorImpl;
import model.circuit.Track;

/**
 * 
 */
public class TestCircuitBuilder {

    private static final int VAL1 = 2;
    private static final int VAL2 = 3;
    /**
     * 
     */
    @Test
    public void testClass() {
        final String fileName = "/circuits/Melbourne/Track.txt";
        final InputStream is = this.getClass().getResourceAsStream(fileName);
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        } catch (UnsupportedEncodingException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        List<Track> list = new ArrayList<>();
        list = CircuitCreatorImpl.getSingleton().createCircuit(br);
        assertEquals(VAL1, list.get(0).getLength());
        assertEquals(VAL2, list.get(1).getWidth());
    }

}
