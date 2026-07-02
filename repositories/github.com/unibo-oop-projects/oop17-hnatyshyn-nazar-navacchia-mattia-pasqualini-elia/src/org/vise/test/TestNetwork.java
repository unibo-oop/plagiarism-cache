package org.vise.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.json.JSONException;
import org.junit.Test;
import org.vise.controller.RemoteController;
import org.vise.controller.RemoteControllerImpl;
import org.vise.model.user.User;
import org.vise.network.NetworkUtility;
import org.vise.network.PlayerNetwork;
import org.vise.network.RLFormNetwork;

/**
 * 
 * @author nazarhnatyshyn
 *              This class tests the network package.
 */
public final class TestNetwork {
    private final String userEmail = "testuser@mail.com";
    private final String userPWD = "testuserPWD";
    private final String secondUserEmail = "secondtestuser@mail.com";
    private final String secondUserPWD = "testuserPWD";

    private User user, secondUser;

    private final RemoteController player = new RemoteControllerImpl();

    /**
     * 
     */
    @Test
    public void testInit() {
        try {
            RLFormNetwork.makeRegistration("testUser", this.userEmail, this.userPWD);
            RLFormNetwork.makeRegistration("secondTestUser", this.secondUserEmail, this.secondUserPWD);
            this.user = RLFormNetwork.makeLogin(this.userEmail, this.userPWD).getValue().getValue();
            this.secondUser = RLFormNetwork.makeLogin(this.secondUserEmail, this.secondUserPWD).getValue().getValue();
            assertNotNull(this.user);
            assertNotNull(this.secondUser);
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 
     */
    @Test
    public void testNetworking() {
        assertTrue("Network should be avalable", NetworkUtility.isNetworkingAvailable());
    }

    /**
     * 
     */
    @Test
    public void testLogin() {
        try {
            assertTrue("Result should be success", RLFormNetwork.makeLogin(userEmail, userPWD).getKey());
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 
     */
    @Test
    public void testFriendship() {
        testInit();
        try {
            assertTrue("Result should be success", PlayerNetwork.addFriendship(this.user.getUserID(), this.secondUser.getUserID()).getKey());
            assertTrue("Result should be success", PlayerNetwork.removeFriendship(this.user.getUserID(), this.secondUser.getUserID()).getKey());
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 
     */
    @Test
    public void testActivity() {
        testInit();
        try {
            assertTrue("Result should be success", PlayerNetwork.addActivity(user.getUserID(), "testSong").getKey());
            assertTrue("Result should be success", PlayerNetwork.removeActivity(user.getUserID()).getKey());
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 
     */
    @Test
    public void testPlaylist() {
        testInit();
        try {
            this.player.addPlaylist("prova");
            assertTrue("Current playlist should be empty", this.player.getCurrentPlaylist().isEmpty());
            assertTrue("Result should be success", PlayerNetwork.addPlaylist(this.player.getPlaylists().get(0), this.user.getUserID()).getKey());
            assertTrue("Result should be success", PlayerNetwork.removePlaylist(this.player.getPlaylists().get(0).getPlaylistID().toString(), this.user.getUserID()).getKey());
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
    }
}
