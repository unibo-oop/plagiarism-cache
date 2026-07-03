package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import utility.GameValues;
import utility.Utilities;

/**
 * @author Paolo
 * the Test used to check Input/Output.
 */
//CHECKSTYLE: MagicNumber OFF
// usato per evitare di dover specificare una var per ogni lista "giocattolo"
public class KeyBinding {
    private static final String TXT = "Test.txt";
    private static final String EQUALSSTRING = "List should be identical";
    private static final String NOTEQUALSSTRING = "List should not be identical";

    /**
     * 
     */
    @Test
    public void testRead() {
        final List<Integer> list = Utilities.readFromFile(TXT);
        assertEquals(Arrays.asList(10, 20, 30, 40, 50, 60, 70, 80), list, KeyBinding.EQUALSSTRING);
    }

    /**
     * 
     */
    @Test
    public void testWrite() {
        final File file = new File(TXT);
        if (!file.exists()) {
            BufferedWriter writer;
            try {
                file.createNewFile();
                writer = new BufferedWriter(new FileWriter(TXT));
                Utilities.writeToFile(GameValues.DEFAULTKEYBINDING, writer);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            final List<Integer> list = Utilities.readFromFile(TXT);
            assertEquals(GameValues.DEFAULTKEYBINDING, list, KeyBinding.EQUALSSTRING);
        }
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(TXT));
            Utilities.writeToFile(Arrays.asList(10, 20, 30, 40, 50, 60, 70, 80), writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        final List<Integer> list = Utilities.readFromFile(TXT);
        assertEquals(Arrays.asList(10, 20, 30, 40, 50, 60, 70, 80), list, KeyBinding.EQUALSSTRING);
    }

    /**
     * 
     */
    @Test
    public void testContinuosReadWrite() {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(TXT));
            Utilities.writeToFile(GameValues.DEFAULTKEYBINDING, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        final List<Integer> list = Utilities.readFromFile(TXT);
        assertEquals(GameValues.DEFAULTKEYBINDING, list, KeyBinding.EQUALSSTRING);
        try {
            writer = new BufferedWriter(new FileWriter(TXT));
            Utilities.writeToFile(Arrays.asList(80, 70, 60, 50, 40, 30, 20, 10), writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        final List<Integer> list2 = Utilities.readFromFile(TXT);
        assertEquals(Arrays.asList(80, 70, 60, 50, 40, 30, 20, 10), list2, KeyBinding.EQUALSSTRING);
        try {
            writer = new BufferedWriter(new FileWriter(TXT));
            Utilities.writeToFile(Arrays.asList(50, 75, 65, 55, 45, 35, 25, 15), writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        final List<Integer> list3 = Utilities.readFromFile(TXT);
        assertNotEquals(Arrays.asList(80, 70, 60, 50, 40, 30, 20, 10), list3, KeyBinding.NOTEQUALSSTRING);
    }

}
