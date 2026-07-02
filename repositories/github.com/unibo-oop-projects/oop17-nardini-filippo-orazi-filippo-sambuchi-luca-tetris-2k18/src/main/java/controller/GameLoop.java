package main.java.controller;

public interface GameLoop {
	void start();
	
	void togglePause();
	
	boolean isPaused();
	
	boolean isRunning();
	
}
