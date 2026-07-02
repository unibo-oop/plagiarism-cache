package controller;

import model.LibraryManager;
import model.MpegInfo;

public interface MusicControllerInterface {

    /**
     * Set pause if in reproduction.
     * Set in reproduction if Paused.
     */
    void resume();
    

    /**
     * Play the next Track.
     */
    void nextTrack();
    
    /**
     * Play the previous Track.
     */
    void previuosTrack();
    

    /**
     * Play the current song in the player.
     */
    void play();
    
   
    /**
     * Set the reproduction in pause.
     * 
     */
    void pause();
 
    /**
     * Return if the player is paused.
     * @return isPaused
     */
    boolean isPaused();

    /**
     * Open Track to play
     * @param audioFilePath 
     */
	void openTrack(String audioFilePath);

    /**
     * Give Mpeg Info
     * @return MpegInfo
     */
	MpegInfo getMpegInfo();
	
	
	/**
     * Give Track Manager
     * @return LibraryManager
     */
	public LibraryManager getLibraryManager();
	
	/**
     * Title and artist of Currend Song Playing
     * @return String
     */
	public String getReproducingSongInfo() ;
	
	/**
     * Stop the music is playing
     */
	 void stop();
}
