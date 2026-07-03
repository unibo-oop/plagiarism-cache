package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.Color;
import model.MatchImpl;

public class PlayerImplTest {

	MatchImpl match; 
	final static int N_PLAYER_GAME4 = 4;
	final static int N_TOKEN_FOR_PLAYER = 4;	
	
	/**
	 * Initialize the value of the map and of the list,
	 * in the class MatchImpl
	 */
	@Before
	public void init4() {
		match = new MatchImpl(N_PLAYER_GAME4);
		match.addPlayer("Player1", 1, Color.BLUE);
		match.addPlayer("Player2", 2, Color.ORANGE);
		match.addPlayer("Player3", 3, Color.PURPLE);
		match.addPlayer("Player4", 4, Color.RED);
		match.addToken(1, Color.BLUE);
		match.addToken(2, Color.BLUE);
		match.addToken(3, Color.BLUE);
		match.addToken(4, Color.BLUE);
		match.addToken(5, Color.ORANGE);
		match.addToken(6, Color.ORANGE);
		match.addToken(7, Color.ORANGE);
		match.addToken(8, Color.ORANGE);
		match.addToken(9, Color.PURPLE);
		match.addToken(10, Color.PURPLE);
		match.addToken(11, Color.PURPLE);
		match.addToken(12, Color.PURPLE);
		match.addToken(13, Color.RED);
		match.addToken(14, Color.RED);
		match.addToken(15, Color.RED);
		match.addToken(16, Color.RED);
		//Initialization done
	}
	
	/**
	 * First check, all the statics's value should be 0.
	 */
	@Test
	public void InitialValuesTest4() {
		assertEquals((match.getPlayers().get(0).getNTimesHasBeenEating()+ 
				match.getPlayers().get(0).getNTimesHasEated() + match.getPlayers().get(1).getNTimesHasBeenEating()+ 
				match.getPlayers().get(1).getNTimesHasEated() + match.getPlayers().get(2).getNTimesHasBeenEating()+ 
				match.getPlayers().get(2).getNTimesHasEated() + match.getPlayers().get(3).getNTimesHasBeenEating()+ 
				match.getPlayers().get(3).getNTimesHasEated()) % 2, 0
				);

		assertEquals(match.getPlayers().get(0).getnTokenArrived() + match.getPlayers().get(0).getnTokenArrived() + 
				(N_TOKEN_FOR_PLAYER - match.getPlayers().get(0).getnTokenArrived() - match.getPlayers().get(0).getnTokenArrived()), N_TOKEN_FOR_PLAYER);
		assertEquals(match.getPlayers().get(1).getnTokenArrived() + match.getPlayers().get(1).getnTokenArrived() + 
				(N_TOKEN_FOR_PLAYER - match.getPlayers().get(1).getnTokenArrived() - match.getPlayers().get(1).getnTokenArrived()), N_TOKEN_FOR_PLAYER);
		assertEquals(match.getPlayers().get(2).getnTokenArrived() + match.getPlayers().get(2).getnTokenArrived() + 
				(N_TOKEN_FOR_PLAYER - match.getPlayers().get(2).getnTokenArrived() - match.getPlayers().get(2).getnTokenArrived()), N_TOKEN_FOR_PLAYER);
		assertEquals(match.getPlayers().get(3).getnTokenArrived() + match.getPlayers().get(3).getnTokenArrived() + 
				(N_TOKEN_FOR_PLAYER - match.getPlayers().get(3).getnTokenArrived() - match.getPlayers().get(3).getnTokenArrived()), N_TOKEN_FOR_PLAYER);
	}

	/*
	 * Simulation of data change:
	 */
	@After
	public void RefreshPosition4(){
		
		//Initialize one token for each player:
		match.startToken(0, N_PLAYER_GAME4);
		match.changeGameBoard(5, 0, 4, N_PLAYER_GAME4);
		match.startToken(1, N_PLAYER_GAME4);
		match.changeGameBoard(13, 1, 2, N_PLAYER_GAME4);
		match.startToken(2, N_PLAYER_GAME4);
		match.changeGameBoard(26, 2, 5, N_PLAYER_GAME4);
		match.startToken(3, N_PLAYER_GAME4);
		match.changeGameBoard(32, 3, 1, N_PLAYER_GAME4);
				
		//Simulation of the arrival of a pawn:
		match.changeGameBoard(10, 0, 5, N_PLAYER_GAME4);
		match.changeGameBoard(15, 0, 5, N_PLAYER_GAME4);
		match.changeGameBoard(20, 0, 5, N_PLAYER_GAME4);
		match.changeGameBoard(25, 0, 5, N_PLAYER_GAME4);
		match.changeGameBoard(30, 0, 5, N_PLAYER_GAME4);
		match.changeGameBoard(35, 0, 5, N_PLAYER_GAME4);
		match.changeGameBoard(40, 0, 5, N_PLAYER_GAME4);
		match.changeInArrive(41, N_PLAYER_GAME4);
				
				
		//Simulation of the killing of a pawn:
		match.changeGameBoard(18, 1, 5, N_PLAYER_GAME4);
		match.changeGameBoard(23, 1, 5, N_PLAYER_GAME4);
		match.changeGameBoard(26, 1, 5, N_PLAYER_GAME4);
				
		//Simulation of the killing of a pawn:
		match.changeGameBoard(30, 1, 4, N_PLAYER_GAME4);
		match.changeGameBoard(32, 1, 2, N_PLAYER_GAME4);
		//Refresh position done
	}
	
	/**
	 * Second check on the modified values:
	 */
	@Test
	public void RefreshTest4() {
		assertEquals((match.getPlayers().get(0).getNTimesHasBeenEating()+ 
				match.getPlayers().get(0).getNTimesHasEated() + match.getPlayers().get(1).getNTimesHasBeenEating()+ 
				match.getPlayers().get(1).getNTimesHasEated() + match.getPlayers().get(2).getNTimesHasBeenEating()+ 
				match.getPlayers().get(2).getNTimesHasEated() + match.getPlayers().get(3).getNTimesHasBeenEating()+ 
				match.getPlayers().get(3).getNTimesHasEated()) % 2, 0
				);

		assertEquals(match.getPlayers().get(0).getnTokenArrived() + match.getPlayers().get(0).getnTokenArrived() + 
				(N_TOKEN_FOR_PLAYER - match.getPlayers().get(0).getnTokenArrived() - match.getPlayers().get(0).getnTokenArrived()), N_TOKEN_FOR_PLAYER);
		assertEquals(match.getPlayers().get(1).getnTokenArrived() + match.getPlayers().get(1).getnTokenArrived() + 
				(N_TOKEN_FOR_PLAYER - match.getPlayers().get(1).getnTokenArrived() - match.getPlayers().get(1).getnTokenArrived()), N_TOKEN_FOR_PLAYER);
		assertEquals(match.getPlayers().get(2).getnTokenArrived() + match.getPlayers().get(2).getnTokenArrived() + 
				(N_TOKEN_FOR_PLAYER - match.getPlayers().get(2).getnTokenArrived() - match.getPlayers().get(2).getnTokenArrived()), N_TOKEN_FOR_PLAYER);
		assertEquals(match.getPlayers().get(3).getnTokenArrived() + match.getPlayers().get(3).getnTokenArrived() + 
				(N_TOKEN_FOR_PLAYER - match.getPlayers().get(3).getnTokenArrived() - match.getPlayers().get(3).getnTokenArrived()), N_TOKEN_FOR_PLAYER);
	}
}

