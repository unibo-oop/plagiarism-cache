package main.java.controller;

public enum GameEvent {

	       DROP("/main/resources/drop.mp3"),
	       
	       
	       GAMEOVER("/main/resources/gameover.mp3"),
	       
	       
	       LINE("/main/resources/line.mp3"),
	       
	       
	       MOVE("/main/resources/move.mp3"),
	       
	       
	       
	       ROTATE("/main/resources/rotate.mp3"),
	       
	       
	       TETRIS("/main/resources/tetris.mp3"),
	       
	       
	       THEME("/main/resources/theme.mp3");
	       
	       
	       
	        
	        private final String path;

	    
	        GameEvent(final String path) {
	            this.path = path;
	        }
	        
	        public String getpath() {
	            return this.path;
	        }
	        
	 
}
