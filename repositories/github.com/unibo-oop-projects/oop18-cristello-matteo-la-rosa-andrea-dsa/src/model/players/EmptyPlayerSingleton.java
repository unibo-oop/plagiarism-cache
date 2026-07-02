package model.players;

/**
 * 
 * class used to menage the case of empty player on a tile.
 *
 */
public final class EmptyPlayerSingleton extends PlayerImpl{
	
	private static EmptyPlayerSingleton emptyPlayer;
	
	private EmptyPlayerSingleton() {
		super("",null);
	}
	
	/**
	 * this method is used to return an istance of ampty player if needed a player
	 * where player can be optional
	 * 
	 * @return the istance of emptyplayer
	 * 
	 */

	public static EmptyPlayerSingleton getInstance() {
		
		synchronized (EmptyPlayerSingleton.class) {
			if(emptyPlayer == null) {
				emptyPlayer = new EmptyPlayerSingleton();
			}
		}
		return emptyPlayer;
	}
}
