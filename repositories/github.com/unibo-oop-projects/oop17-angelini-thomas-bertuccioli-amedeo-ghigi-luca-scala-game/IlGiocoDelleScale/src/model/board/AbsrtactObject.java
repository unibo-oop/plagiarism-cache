package model.board;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public abstract class AbsrtactObject implements UdStrategy {
	
	protected final int tableHeight = 0;
	protected final int tableWidth = 0;
	
	public final List<Coordinate> tableLiStart  = new LinkedList<>();
	public final List<Coordinate> tableLiStop = new LinkedList<>();
	Random random = new Random();


	protected int randInt(int min, int max) {
	    int randomNum = random.nextInt((max - min) + 1) + min;
	    return randomNum;
	}

	public abstract UpsideDownImpl snake();
	public abstract UpsideDownImpl stairs();
	

}
