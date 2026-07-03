package com.game.main;

import java.util.Random;

import com.game.main.Game.STATE;
import com.game.support.ID;

public class Spawn implements Tickable{

	private Handler handler;
	private HUD hud;
	private Random r = new Random();
	private int scoreKeep;
	private int lvl;
	boolean coinState = false;
	private int coinDecider = 0;
	int lvlC;
	
	
	public Spawn(Handler handler, HUD hud, Game game){
		this.handler = handler;
		this.hud = hud;
		this.scoreKeep = hud.getScore();
	}
	
	public void tick(){
		if(Game.gameState == STATE.Game){
		scoreKeep++;
		lvl = hud.getLevel();
		if(scoreKeep >= 250-(hud.getWave()-1*25)){
			int casual  = (Math.random() <= 0.5) ? 1 : 2;
			if(casual == 1) handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH/2-32),r.nextInt(Game.PLAYABLEHEIGHT/2-32),ID.BasicEnemy, handler, lvl));
			else handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH/2-32),r.nextInt(Game.PLAYABLEHEIGHT/2-32),ID.FastEnemy, handler));
			HUD.HEALTH = HUD.HEALTH+5;
			hud.setLevel(lvl+1);
			//inserimento moneta
			if(coinState == false){
				coinDecider = (Math.random() <= 0.5) ? 1 : 2;
				if(hud.getLevel() > 7) coinDecider = 1;
				if(coinDecider == 1) {
					int casualCoin = (Math.random() <= 0.5) ? 1 : 2;
					if(casualCoin == 1) handler.addObject(new Coin(r.nextInt(Game.WIDTH/2-32),r.nextInt(Game.PLAYABLEHEIGHT/2-32), ID.Coin, handler));
					else handler.addObject(new CoinBomb(r.nextInt(Game.WIDTH/2-32),r.nextInt(Game.PLAYABLEHEIGHT/2-32), ID.CoinBomb, handler)); 
					coinState = true;
					lvlC = hud.getLevel();
				}
			}
			if(coinState == true){
				if(hud.getLevel() == lvlC+1){
					for(int i = 0; i < handler.object.size(); i++){
						GameObject tempObject = handler.object.get(i);
						if(tempObject.getId() == ID.Coin  || tempObject.getId() == ID.CoinBomb){
							handler.removeObject(tempObject);
						}
					}
				}
			}
			
			scoreKeep = 0;
		}
		if (hud.getLevel()-hud.getWave() == 9){
			hud.setLevel(1);
			hud.setScore(0);
			hud.setWave(hud.getWave()+1);
			coinState = false;
			Game.paused = true;
			Game.gameState = STATE.WaveEnd;
			}}
	}
}
