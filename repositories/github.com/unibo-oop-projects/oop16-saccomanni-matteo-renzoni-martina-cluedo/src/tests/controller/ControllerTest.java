package tests.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import model.CareMementoTaker;
import model.GameInit;
import model.Model;
import model.ModelMemento;
import utilities.enumerations.CharacterCard;
import utilities.enumerations.PlayerType;

/**
 * Test to check the Controller.
 */
public class ControllerTest {

    private static final String SAVING_DIR_PATH = System.getProperty("user.home") + System.getProperty("file.separator")
            + ".cluedo" + System.getProperty("file.separator");

    /**
     * Constructor.
     */
    public ControllerTest() {
        final File dir = new File(SAVING_DIR_PATH);
        if (!dir.exists()) {
            final boolean success = dir.mkdir();
            if (!success) {
                System.out.println("Cannot create the folder for saves");
            }
        }
    }

    /**
     * Test to check if the saving is correct.
     */
    @Test
    public void savingTest() {
        // Game creation
        try {
            Model model;
            final Map<CharacterCard, PlayerType> m = new HashMap<>();
            m.put(CharacterCard.GREEN, PlayerType.HUMAN);
            m.put(CharacterCard.PEACOCK, PlayerType.AI);
            m.put(CharacterCard.SCARLETT, PlayerType.AI);
            model = GameInit.getInstance().initGame(m);

            // Saving
            model.saveGame();
            String nameFile = "Cluedo_" + Instant.now();
            nameFile = nameFile.trim();
            nameFile = nameFile.replace(':', '-');
            nameFile = nameFile.substring(0, nameFile.lastIndexOf('.'));
            nameFile = SAVING_DIR_PATH + nameFile;
            OutputStream file;
            file = new FileOutputStream(nameFile);
            final OutputStream bstream = new BufferedOutputStream(file);
            final ObjectOutputStream ostream = new ObjectOutputStream(bstream);
            final ModelMemento memebefore = CareMementoTaker.getInstance().getMemento();
            ostream.writeObject(memebefore);
            ostream.close();

            // Resuming
            final InputStream file2 = new FileInputStream(nameFile);
            final InputStream bstream2 = new BufferedInputStream(file2);
            final ObjectInputStream ostream2 = new ObjectInputStream(bstream2);
            final ModelMemento meme = (ModelMemento) ostream2.readObject();
            ostream2.close();
            assertEquals(memebefore.getCurrentPlayer(), meme.getCurrentPlayer());
            assertEquals(memebefore.getPlayers(), meme.getPlayers());
            assertEquals(memebefore.getCommonCards(), meme.getCommonCards());
            assertEquals(memebefore.getSolution(), meme.getSolution());
            assertTrue(new File(nameFile).delete());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}