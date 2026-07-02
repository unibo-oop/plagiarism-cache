package boardgames.controller;

public interface Statistics {

	/*
	 * 
	 */
	public void addMatchPlayed(String game, boolean timeGame);
	
	/*
	 * 
	 */
	public int getMatchesPlayed(String game, boolean timeGame);
	
	/*	ritorna le vittoroe del giocatore 1 nel
	 * 	gioco indicato
	 */
	public int getWonP1(String game, boolean timeGame);
	
	/*	ritorna le vittorie del giocatore 2 nel
	 * 	gioco indicato
	 */
	public int getWonP2(String game, boolean timeGame);
	
	/*	aggiunge una vittoria al giocatore 1 nel
	 * 	gioco indicato
	 */
	public void addWonP1(String game, boolean timeGame);
	
	/*	aggiunge una vittoria al giocatore 2 nel
	 * 	gioco indicato
	 */
	public void addWonP2(String game, boolean timeGame);
	
	/*
	 * 
	 */
	public int getLostP1(String game, boolean timeGame);
	
	/*
	 * 
	 */
	public int getLostP2(String game, boolean timeGame);
	
	/*
	 * 
	 */
	public void addLostP1(String game, boolean timeGame);
	
	/*
	 * 
	 */
	public void addLostP2(String game, boolean timeGame);
	
	/*
	 * 
	 */
	public int getParP(String game, boolean timeGame);

	/*
	 * 
	 */
	public void addParP(String game, boolean timeGame);

	
}
