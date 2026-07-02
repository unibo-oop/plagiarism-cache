package util;

import java.io.File;
import java.util.Map;
import controller.MainController;
import javazoom.jlgui.basicplayer.BasicController;
import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerEvent;
import javazoom.jlgui.basicplayer.BasicPlayerException;
import javazoom.jlgui.basicplayer.BasicPlayerListener;

/**
 * 
 * @author rrok
 * Mp3 player 
 *
 */
public class Mp3Player implements IPlayer, BasicPlayerListener{

	private static Mp3Player mp3Player;
	private BasicController controllerBasic;
	private MainController mp3Controller;
	private int SONG_FINISHED = BasicPlayerEvent.EOM;
	
	private Mp3Player(MainController controller){
		this.controllerBasic = new BasicPlayer();
		this.mp3Controller = controller;
	}
	
	/**
	 * new mp3 istance of mp3 player, SINGLETON istance, there will be only one player
	 * @param controller MainController
	 * @return Mp3Player Mp3Player
	 */
	public static Mp3Player newInstance(MainController controller){
		if(mp3Player == null){
			mp3Player = new Mp3Player(controller);
		}
		return mp3Player;
	}


	 /**
     * {@inheritDoc}
     */
	@Override
	public void play(String songPath) {
        try {
			this.controllerBasic.open(new File(songPath));
			Thread.sleep(100);
	        this.controllerBasic.play();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	
	 /**
     * {@inheritDoc}
     */
	@Override
	public void pause() {
		try {
			controllerBasic.pause();
		} catch (BasicPlayerException e) {
			e.printStackTrace();
		}
	}
	
	
	
	 /**
     * {@inheritDoc}
     */
	@Override
	public void resume() {
		try {
			this.controllerBasic.resume();
		} catch (BasicPlayerException e) {
			e.printStackTrace();
		}
	}
	
	public void stop(){
		try {
			this.controllerBasic.stop();
		} catch (BasicPlayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	 /**
     * {@inheritDoc}
     */
	@Override
	public void opened(Object arg0, @SuppressWarnings("rawtypes") Map arg1) {
		// TODO Auto-generated method stub
		
	}

	 /**
     * {@inheritDoc}
     */
	@Override
	public void progress(int arg0, long arg1, byte[] arg2, @SuppressWarnings("rawtypes") Map arg3) {
		// TODO Auto-generated method stub
		
	}
	
	 /**
     * {@inheritDoc}
     */
	@Override
	public void setController(BasicController arg0) {
		// TODO Auto-generated method stub
		
	}

	 /**
     * {@inheritDoc}
     */
	@Override
	public void stateUpdated(BasicPlayerEvent event) {
		System.out.println("Song finished");
		if(event.getCode() == SONG_FINISHED){
			System.out.println("Song finished event launched");
			mp3Controller.onTrackFinished();
		}	
	}
}
