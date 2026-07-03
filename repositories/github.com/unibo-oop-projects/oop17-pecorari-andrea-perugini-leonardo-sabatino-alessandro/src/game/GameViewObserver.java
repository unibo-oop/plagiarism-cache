package game;

import java.util.List;

import characters.CharacterImpl;
import javafx.stage.Stage;

public interface GameViewObserver {
	
	void createDodge();
	
	void checkCollisions();
	
	void removeCharacters(List<? extends CharacterImpl> characterList);
	
	void checkLife(Stage primaryStage);

}
