package gamemode;

import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * Create and return position per position the map random-generated. It insert in the base map eight of the sixteen default
 * blocks picked random. when the all X and Y position are returned it set the flag as true so the map is completed.
 * To return the all position row by row there is an array composed by the rows of all blocks drawn.
 *
 */

public class RandomMap {

	private static final int min = 1;
	private static final int max = 16;
	private static final int mapWidth = 20;
	private static final int mapHeight = 10;
	private String[][] firstBlock;
	private String[][] secondBlock;
	private String[][] thirdBlock;
	private String[][] fourthBlock;
	private int positionX=0;
	private int positionY=1;
	private String[] block= new String[128];
	private boolean ready = false;
	private int pos=-1;


	static final private String[][] block1 = {{"F", "0", "0", "F"}, {"F", "0", "0", "F"}, {"F", "0", "0", "F"}, {"F", "0", "0", "F"}};
	static final private String[][] block2 = {{"0", "0", "0", "0"}, {"0", "0", "0", "0"}, {"0", "R", "R", "0"}, {"0", "R", "R", "0"}};
	static final private String[][] block3 = {{"0", "0", "0", "0"}, {"0", "W", "W", "0"}, {"0", "W", "W", "0"}, {"0", "0", "0", "0"}};
	static final private String[][] block4 = {{"R", "R", "R", "R"}, {"0", "F", "F", "0"}, {"0", "0", "0", "0"}, {"0", "0", "0", "0"}};
	static final private String[][] block5 = {{"0", "0", "0", "0"}, {"0", "0", "0", "0"}, {"G", "G", "G", "G"}, {"G", "G", "G", "G"}};
	static final private String[][] block6 = {{"0", "W", "W", "W"}, {"0", "0", "0", "W"}, {"0", "0", "0", "W"}, {"0", "0", "0", "0"}};
	static final private String[][] block7 = {{"0", "F", "0", "0"}, {"0", "0", "0", "W"}, {"G", "0", "0", "0"}, {"0", "0", "R", "0"}};
	static final private String[][] block8 = {{"F", "F", "F", "R"}, {"F", "R", "R", "R"}, {"0", "R", "R", "0"}, {"0", "0", "0", "0"}};
	static final private String[][] block9 = {{"F", "F", "0", "0"}, {"F", "0", "0", "0"}, {"0", "0", "0", "R"}, {"0", "0", "R", "R"}};
	static final private String[][] block10 = {{"0", "G", "G", "0"}, {"0", "G", "G", "0"}, {"0", "G", "G", "0"}, {"0", "G", "G", "0"}};
	static final private String[][] block11 = {{"W", "R", "0", "0"}, {"R", "R", "0", "F"}, {"0", "0", "0", "G"}, {"0", "F", "G", "G"}};
	static final private String[][] block12 = {{"R", "R", "R", "0"}, {"R", "0", "0", "0"}, {"R", "0", "R", "R"}, {"0", "0", "R", "R"}};
	static final private String[][] block13 = {{"0", "0", "0", "0"}, {"0", "W", "R", "0"}, {"0", "F", "G", "0"}, {"0", "0", "0", "0"}};
	static final private String[][] block14 = {{"R", "R", "F", "0"}, {"F", "0", "0", "0"}, {"0", "0", "0", "F"}, {"0", "F", "R", "R"}};
	static final private String[][] block15 = {{"0", "0", "0", "0"}, {"0", "0", "0", "0"}, {"0", "W", "W", "0"}, {"0", "W", "W", "0"}};
	static final private String[][] block16 = {{"0", "0", "0", "0"}, {"0", "F", "F", "0"}, {"0", "F", "F", "0"}, {"0", "F", "F", "0"}};

	/**
	 * Return the flag that control if the map is completed
	 */
	public boolean getReady(){
		return ready;
	}

	/**
	 * Set a flag that not allow the player start play because the map isn't completed
	 */
	public void setNotReady(){
		ready = false;
	}

	/**
	 * Reset all position to let a new map to generate
	 */

	public void randomMapReset(){
		 positionX=0;
		 positionY=1;
	}


	/**
	 * Return the X position where the hard mode will spawn weed or an obstacle.
	 * Every five position there is in the map a column of weed that allows the mower
	 * reach every walkable position.
	 */
	public int getXPosition(){

		positionX++;
		if(positionX>=mapWidth){
			positionY++;
			positionX=0;
		}

		if(positionX%5==0){
			positionX++;
		}
		return positionX;
	}

	/**
	 * Return the Y position where the hard mode will spawn weed or an obstacle.
	 * Every five position there is in the map a row of weed that allows the mower
	 * reach every walkable position.
	 */
	public int getYPosition(){
		if(positionY>=mapHeight){
			randomMapReset();
			ready=true;
		}
		if(positionY%5==0){
			positionY++;
		}
		return positionY;
	}

	/**
	 * Return the entity that the hard mode need to spawn in a position.
	 */
	public String getSpawnEntity() {
		if(pos<127){
			pos++;
		}
		return block[pos];
	}

	private void mapComposer(){
		for(int j=0; j<4; j++){
			for(int i=0; i<4; i++){
				pos++;
				block[pos] = firstBlock[i][j];
			}
			for(int i = 0; i<4; i++){
				pos++;
				block[pos] = secondBlock[i][j];
			}
			for(int i = 0; i<4; i++){
				pos++;
				block[pos] = thirdBlock[i][j];
			}
			for(int i = 0; i<4; i++){
				pos++;
				block[pos] = fourthBlock[i][j];
			}
		}
	}


	private void blockComposer(){
		 firstBlock = randomBlock();
		 secondBlock = randomBlock();
		 thirdBlock = randomBlock();
		 fourthBlock = randomBlock();
	}

	private String[][] randomBlock(){
		int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
		switch(randomNum){
	    case 1:
	        return block1;
	    case 2:
	    	return block2;
	    case 3:
	        return block3;
	    case 4:
	        return block4;
	    case 5:
	        return block5;
	    case 6:
	        return block6;
	    case 7:
	        return block7;
	    case 8:
	    	return block8;
	    case 9:
	        return block9;
	    case 10:
	    	return block10;
	    case 11:
	        return block11;
	    case 12:
	        return block12;
	    case 13:
	        return block13;
	    case 14:
	        return block14;
	    case 15:
	        return block15;
	    case 16:
	    	return block16;
		}
		return block1;
	}

	/**
	 * Create the random map that will be loaded in hard mode. Call two times both methods because the upper side of the map
	 * is similar to the down side
	 */
	public void mapCreation(){
		blockComposer();
		mapComposer();
		blockComposer();
		mapComposer();
		pos=-1;
	}

}


