package minigames;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;

import game.AngryBall;
import game.GunDirection;
import game.ID;
import game.KillCount;
import game.Player;
import game.Timer;

public class MiniGameFactoryImpl {

	private static final int PWIDTH = 640, PHEIGHT = PWIDTH / 12 * 9;
    /**
	 * This enum keeps track of all existing game recipes.
	 * The methods within map any one Enum's position to its value.
	 * @author StackOverflow
	 */
	public enum GAMES_ID{
		
		Cube_Invaders(0),
		Cube_Destroyer(1),
		//Simon_says(2),
		;
	     private static final Map<Integer,GAMES_ID> lookup 
         = new HashMap<Integer,GAMES_ID>();

	    static {
	         for(GAMES_ID w : EnumSet.allOf(GAMES_ID.class))
	              lookup.put(w.getCode(), w);
	    }
	
	    private int code;
	
	    private GAMES_ID(int code) {
	         this.code = code;
	    }

	    public int getCode() { return code; }
	
	    public static GAMES_ID get(int code) { 
	         return lookup.get(code); 
	    }
	}
	
	public void StartGame(GAMES_ID id, int difficulty){
		if(id == GAMES_ID.Cube_Invaders) {
			MiniGameImpl game = CreateGame(GAMES_ID.Cube_Invaders, difficulty);
			game.StartGame();
		}
		if(id == GAMES_ID.Cube_Destroyer) {
			MiniGameImpl game = CreateGame(GAMES_ID.Cube_Destroyer, difficulty);
			game.StartGame();
		}
	}		

	public MiniGameImpl CubeInvaders(int difficulty){
		MiniGameImpl game = new MiniGameImpl();
		game.addObject(new Player(PWIDTH/2-32, PHEIGHT/2-32, ID.Player));
		game.addObject(new Timer(PWIDTH/15, PHEIGHT/15, ID.GUI, false, 600));
		Random rand = new Random();
		for(int i = difficulty*2; i > 0; i--)
			game.addObject(new AngryBall(rand.nextInt(600)+200, rand.nextInt(600)+200, ID.Enemy));
		return game;
	}
	
	public MiniGameImpl CubeDestroyer(int difficulty){
		MiniGameImpl game = new MiniGameImpl();
		game.addObject(new Player(PWIDTH/2-32, PHEIGHT/2-32, ID.Player));
		game.addObject(new GunDirection(PWIDTH/2-32, PHEIGHT/2-32, ID.Gun));
		game.addObject(new Timer(PWIDTH/15, PHEIGHT/15, ID.GUI, true));
		game.addObject(new KillCount(PWIDTH - PWIDTH/15, PHEIGHT/15, difficulty*4));
		Random rand = new Random();
		for(int i = difficulty*4; i > 0; i--)
			game.addObject(new AngryBall(rand.nextInt(600)+400, rand.nextInt(600)+400, ID.Enemy));
		return game;
	}
	
	public MiniGameImpl SimonSays(int difficulty){
		MiniGameImpl game = new MiniGameImpl();
		//game.addObject(new game.SimonSays(0, 0, ID.GUI, difficulty));
		return game;
	}
	
	public MiniGameImpl CreateGame(GAMES_ID id, int difficulty){
		switch(id.ordinal()){
		case 0:
			return CubeInvaders(difficulty);
		case 1:
			return CubeDestroyer(difficulty);
		case 2:
			return SimonSays(difficulty);
		default:
			return CubeInvaders(difficulty);
		}
	}
	
	public LinkedList<String> allNames() {
		LinkedList<String> names = new LinkedList<String>();
		for (GAMES_ID ID : GAMES_ID.values()){
			names.add(ID.toString());
		}
		return names;
	}
	
	public LinkedList<MiniGame> increasingRecipe(boolean repeat) {

		LinkedList<GAMES_ID> added = new LinkedList<GAMES_ID>();
		LinkedList<MiniGame> games = new LinkedList<MiniGame>();
		int difficulty = 1;
		
		int max = (GAMES_ID.values().length < 5) && !repeat ? GAMES_ID.values().length : 5;

		for (int i = max; i > 0; i--){
			Random rand = new Random();
			int  n = rand.nextInt(GAMES_ID.values().length);
			
			if(!repeat){
				while (added.contains(GAMES_ID.get(n))){
					n = rand.nextInt(GAMES_ID.values().length);
				}
			}//end of if
			games.add(CreateGame(GAMES_ID.get(n), difficulty++));
			added.add(GAMES_ID.get(n));
		}//end of for
		return games;
	}
}
