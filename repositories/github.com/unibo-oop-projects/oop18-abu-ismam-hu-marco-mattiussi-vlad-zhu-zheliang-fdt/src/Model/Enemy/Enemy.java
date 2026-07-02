/**
 * 
 */
package model.enemy;

import java.util.ArrayList;

import model.entity.Entity;
import model.map.MapTile;

public interface Enemy extends Entity{
	public enum Direction {
		UP,
		DOWN,
		LEFT,
		RIGHT
	}
	
	void walk(); //moving logics
	
	void spawn(); //enemy appearing on the map, ready to walk
	
	void death();// muore
	
	int getHP();
	
	int getValue(); //gold dropped when killed
	
	int getSpeed();
	
	public void setDamage(int damage);

	void setPath(ArrayList<MapTile> sentiero);
	
	Direction getDirezione();
	
	public void despawn();

	
	
}
