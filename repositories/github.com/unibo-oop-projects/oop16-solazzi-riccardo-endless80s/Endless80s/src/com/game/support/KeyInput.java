package com.game.support;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

import com.game.main.Game;
import com.game.main.GameObject;
import com.game.main.Handler;
import com.game.main.PlayerCustom;
import com.game.main.SmartEnemy;
import com.game.main.Game.STATE;

public class KeyInput extends KeyAdapter {
	
	private Handler handler;
	private boolean[] keyDown = new boolean[4];
	Random r = new Random();
	Game game;
	
	public KeyInput(Handler handler, Game game){
		this.handler = handler;
		
		this.game = game;
		
		keyDown[0] = false; //w
		keyDown[1] = false; //S
		keyDown[2] = false; //D
		keyDown[3] = false; //A
	}
	
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId()==ID.Player){
				//key events for player 1
				if(key == KeyEvent.VK_W) {tempObject.setVelY(-5); keyDown[0] = true;}
				if(key == KeyEvent.VK_S) {tempObject.setVelY(5); keyDown[1] = true;}
				if(key == KeyEvent.VK_D) {tempObject.setVelX(5); keyDown[2] = true;}
				if(key == KeyEvent.VK_A) {tempObject.setVelX(-5); keyDown[3] = true;}
			}
		}
	}

	public void keyReleased (KeyEvent e){
		int key = e.getKeyCode();
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId()==ID.Player){
				//key events for player 1
				if(key == KeyEvent.VK_W) keyDown[0] = false; 
				if(key == KeyEvent.VK_S) keyDown[1] = false; 
				if(key == KeyEvent.VK_D) keyDown[2] = false; 
				if(key == KeyEvent.VK_A) keyDown[3] = false; 
				
				//Vertical movement
				if(!keyDown[0] && !keyDown[1]) tempObject.setVelY(0);
				//Horizontal movement
				if(!keyDown[2] && !keyDown[3]) tempObject.setVelX(0);
			
			}
		}	
		if(key == KeyEvent.VK_P){
			if(Game.gameState != STATE.WaveEnd){
			if(Game.paused) Game.paused = false;
			else Game.paused = true;
			}
		}

		if(key == KeyEvent.VK_SPACE){
			if(Game.gameState == STATE.WaveEnd){
				Game.gameState = STATE.Game;
				Game.paused = false;
				handler.clearEnemys();
				handler.addObject(new PlayerCustom((Game.WIDTH/2-32),(Game.HEIGHT/2-32),ID.Player, handler, Game.colorPlayer));
				handler.addObject(new SmartEnemy(r.nextInt((Game.WIDTH)/2-32),r.nextInt((Game.PLAYABLEHEIGHT)/2-32), ID.SmartEnemy, handler));
			}
		}
		
		if(key == KeyEvent.VK_ESCAPE) System.exit(1);
	}
}
