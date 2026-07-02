package main.java.model;

import java.util.List;

import main.java.model.Tetromino.RotationSense;

public interface Model {
	
	Integer scoreCurrent();
	
	/*void scritturaFile();*/
	
	void reset();
	
	void moveDown();
	
	void moveLeft();
	
	void moveRight();
	
	void rotationClockWise();
	
	void rotationCounterClockWise();
	
	void eliminazioneRiga(int numero);
	
	boolean gameOver();
}
