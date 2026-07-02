package menuController;

import menu.Board;
import menu.State;
import util.AudioImpl;
import util.AudioTrack;
import util.Constants;

public class menuControllerImpl implements menuController{
	
	private Board board;
	private AudioImpl audio = new AudioImpl();
	private boolean isReturningFromGame = false;
	private boolean isReturningFromMenuGame = true;

	/**
	 * Implementation of {@link menuController}
	 */
	public menuControllerImpl(Board board) {
		this.board = board;
		audio.play(AudioTrack.SOUND_TRACK, Constants.AudioConstants.IN_LOOP);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void changeState(State state) {
		this.board.setCurrentState(state);
		if(state.getClass().getSimpleName().equals("StateMenu")) {
			if(this.isReturningFromGame == true) {
				this.audio.stop();
				this.audio.play(AudioTrack.SOUND_TRACK, Constants.AudioConstants.IN_LOOP);
				this.isReturningFromGame = false;
				this.isReturningFromMenuGame = true;
			}
		} else if(state.getClass().getSimpleName().equals("StateGame")) {
			if(this.isReturningFromMenuGame == true) {
				this.audio.stop();
				this.audio.play(AudioTrack.GAME_TRACK, Constants.AudioConstants.IN_LOOP);
				this.isReturningFromGame = true;
				this.isReturningFromMenuGame = false;
			}
		} 
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AudioImpl getAudio() {
		return this.audio;
	}

}
