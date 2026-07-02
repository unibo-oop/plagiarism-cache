package minigames;

import java.util.LinkedList;

import game.Controller;
import game.GameObject;

public class MiniGameImpl implements MiniGame {
	
	private final LinkedList<GameObject> gObjects = new LinkedList<GameObject>();

	public int StartGame() {
		gObjects.stream().forEach(o ->Controller.getInstance().addObject(o));
		return 0;
	}
	
	public String asString() {
		return gObjects.toString();
	}
	
	public void addObject(GameObject o) {
		gObjects.add(o);
	}

	@Override
	public LinkedList<GameObject> getObjects() {
		return gObjects;
	}

}
