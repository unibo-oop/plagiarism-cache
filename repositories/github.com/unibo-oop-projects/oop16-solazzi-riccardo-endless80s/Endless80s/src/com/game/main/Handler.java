package com.game.main;

import java.awt.Graphics;
import java.util.LinkedList;

import com.game.support.ID;


public class Handler {

	public LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	public void tick(){
		for(int i = 0; i < object.size(); i++){
			GameObject tempObject = object.get(i);
			if(tempObject instanceof Tickable){
				((Tickable)tempObject).tick();
			}
		}
	}
	public void render(Graphics g){
		for(int i = 0; i < object.size(); i++){
			GameObject tempObject = object.get(i);
			tempObject.render(g);
		}
	}
	public void addObject(GameObject object){
		this.object.add(object);
	}
	public void removeObject(GameObject object){ 
		this.object.remove(object);
	}
	
	public void clearEnemys(){
		for(int i = 0; i< object.size(); i++){
			GameObject tempObject = object.get(i); 
			if(tempObject.getId() == ID.Player){
				object.clear();
				if(Game.gameState != Game.STATE.WaveEnd && Game.gameState != Game.STATE.End){
				addObject(new PlayerCustom((int)tempObject.getX(), (int)tempObject.getY(), ID.Player, this, Game.colorPlayer));
					}
			}
		}
	}
}
