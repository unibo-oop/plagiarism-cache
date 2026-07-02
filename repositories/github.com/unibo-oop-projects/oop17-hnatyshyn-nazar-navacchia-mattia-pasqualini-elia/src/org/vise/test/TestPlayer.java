package org.vise.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.vise.controller.RemoteController;
import org.vise.controller.RemoteControllerImpl;
import org.vise.model.playlist.song.Song;
import org.vise.model.playlist.song.SongImpl;

/**
 * 
 * @author eliapasqualini
 * This class test the player
 *
 */
public final class TestPlayer {


    private final RemoteController player = new RemoteControllerImpl();
    private final Song song = new SongImpl("res/songs/song1.mp3");

    /**
     * 
     */
    @Test
    public void testInit() {
        assertFalse("Player should not be loaded", this.player.isLoaded());
        assertTrue("Player should be in single modality reproduction", this.player.isSingleReproductionModality());
        assertTrue("Player should be in offline modality", !this.player.isPlayerOnlineMode());
    }

    /**
     * 
     */
    @Test
    public void testLoadingSong() {
        this.player.loadSong(this.song);
        assertTrue("Player should be loaded", this.player.isLoaded());
    }

    /**
     * 
     */
    @Test
    public void testPlayAndPause() {
        this.player.loadSong(this.song);
        this.player.play();
        assertTrue("Player should be playing", this.player.isPlaying());
        this.player.pause();
        assertFalse("Player should not be playing", this.player.isPlaying());
        this.player.play();
        this.player.pause();
        this.player.play();
        this.player.pause();
        this.player.play();
        this.player.pause();
        this.player.play();
        this.player.pause();
        this.player.play();
        this.player.pause();
        this.player.play();
        this.player.pause();
        this.player.play();
        this.player.stop();
        assertFalse("Player should not be playing", this.player.isPlaying());
    }

    /**
     * 
     */
    @Test
    public void testPlay() {
        this.player.loadSong(this.song);
        this.player.play();
        try {
            Thread.sleep(10L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertTrue("Player should be playing", this.player.isPlaying());
    }

    /**
     * 
     */
    @Test 
    public void testPlaylist() {

        this.player.addPlaylist("prova");
        assertTrue("Current playlist should be empty", this.player.getCurrentPlaylist().isEmpty());
        this.player.addSongToPlaylist("res/songs/song1.mp3");
        assertFalse("Current playlist should not be empty", this.player.getCurrentPlaylist().isEmpty());
        assertEquals("prova", this.player.getCurrentPlaylist().getName());
        this.player.getCurrentPlaylist().setName("provaNome");
        assertEquals("provaNome", this.player.getCurrentPlaylist().getName());
        this.player.getCurrentPlaylist().getPlaylistContent().remove(0);
        assertTrue("Current playlist should be empty", this.player.getCurrentPlaylist().isEmpty());
        this.player.getPlaylists().remove(0);
        assertEquals(0, this.player.getPlaylists().size());
        this.player.addPlaylist("prova");
        this.player.addPlaylist("prova1");
        this.player.addPlaylist("prova2");
        assertEquals(3, this.player.getPlaylists().size());


    }

}
