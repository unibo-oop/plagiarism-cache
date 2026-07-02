package util;

/**
 * An enum with which the various topologies of sounds are managed.
 */

public enum AudioTrack {
	
	/**
	 * The sound track of the principal menu.
	 */
	SOUND_TRACK("menuSong.wav", AudioType.MUSIC),
	
	/**
	 * The sound of the botton when it is pressed.
	 */
	BUTTON_PRESSED("popMenu.wav", AudioType.EFFECT),
	
	/**
	 * The soung track of the game.
	 */
	GAME_TRACK("gameSong.wav", AudioType.MUSIC);
	
	private String path;
	private AudioType type;
	
	private AudioTrack(String path, AudioType type) {
		this.path = path;
		this.type = type;
	}

	/**
	 * @return the path of the track.
	 */
	public String getPath() {
		return path;
	}
	
	/**
	 * @return the type of the track.
	 */
	public AudioType getAudioType() {
		return this.type;
	}
}
