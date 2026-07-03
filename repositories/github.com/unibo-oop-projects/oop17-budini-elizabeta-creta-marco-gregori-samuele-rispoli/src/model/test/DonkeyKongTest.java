package model.test;

import static org.junit.Assert.*;

import org.junit.Test;
import model.BasicModel;
import model.entities.DonkeyKong;

public class DonkeyKongTest {

    @Test
    public void testBarrelsCreation() throws InterruptedException {
        final BasicModel model = new BasicModel(); 
        final DonkeyKong dk = model.getDonkeyKong();
        dk.startDonkeyKongThreads();
        Thread.sleep(400);
        assertEquals("One barrel was supposed to be created", 1, dk.getBarrelsList().size());
        dk.stopThreads();
        assertTrue("The list was supposed to be empty", dk.getBarrelsList().isEmpty());
    }
}
