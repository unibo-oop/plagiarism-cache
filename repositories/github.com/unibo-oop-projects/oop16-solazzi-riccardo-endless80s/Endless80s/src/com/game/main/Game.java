package com.game.main;


import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

import com.game.support.AudioPlayer;
import com.game.support.ID;
import com.game.support.KeyInput;
import com.game.support.Window;

public class Game extends Canvas implements Runnable, Tickable{
	
	public static final int WIDTH = 640, HEIGHT = WIDTH /12*9;
	public static final int PLAYABLEHEIGHT = HEIGHT - 50;
	private Thread thread;
	private boolean running = false;
	public static boolean paused = false;
	private Handler handler;
	private Random r = new Random();
	private HUD hud;
	private Spawn spawner;
	private Menu menu;
	public AudioPlayer audioPlayer;
	public static Color colorPlayer;
	public enum STATE {
		Menu,
		Game,
		Help,
		WaveEnd,
		End
	};

	public static STATE gameState = STATE.Menu;
	
	public Game(){
		handler = new Handler();
		hud = new HUD();
		menu = new Menu(this, handler, hud);
		this.addKeyListener(new KeyInput(handler, this));
		this.addMouseListener(menu);
		audioPlayer = new AudioPlayer();
		audioPlayer.music();
		spawner = new Spawn(handler, hud, this);
		new Window(WIDTH, HEIGHT, "Endless 80s", this);
		if(gameState == STATE.Menu){
			for(int i = 0; i <= 4; i++){
				handler.addObject(new MenuParticle(r.nextInt(WIDTH), (r.nextInt(HEIGHT)), ID.MenuParticle, handler));
			}
		}
	}
	
	private static final long serialVersionUID = -473349850293143017L;
	public synchronized void start(){
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop(){
		try{
			thread.join();
			running = false;
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void run(){
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1){
				tick();
				delta--;
			}
			if(running)
				render();	
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
			}
		}
		stop();
	}
	
	public void tick(){
		if(gameState == STATE.WaveEnd){
			handler.clearEnemys();
			handler.tick();
			menu.tick();
			spawner.tick();
		}
		if(!paused && gameState != STATE.WaveEnd){
			spawner.tick();
			handler.tick();
		if(gameState == STATE.Game){
			hud.tick();
			if (HUD.HEALTH <= 0){
				HUD.HEALTH = 300;
				gameState = STATE.End;
				handler.clearEnemys();
				for(int i = 0; i <= 4; i++){
					handler.addObject(new MenuParticle(r.nextInt(WIDTH), (r.nextInt(HEIGHT)), ID.MenuParticle, handler));
					}
				}
			}
		} else if (gameState == STATE.Menu || gameState == STATE.End || gameState == STATE.WaveEnd){
			menu.tick();
			handler.tick();
		}
	}
	
	private void render(){
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		handler.render(g);
		if(paused && Game.gameState != STATE.WaveEnd){
			g.setColor(Color.WHITE);
			g.drawString("PAUSED", WIDTH/2-25, HEIGHT/2-10);
		}
		
		if(gameState == STATE.Game){
			hud.render(g);
		}else if (gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.End || gameState == STATE.WaveEnd){
			menu.render(g);
		}
		g.dispose();
		bs.show();	
	}
	
	public static float clamp(float var, float min, float max){
		if(var >= max)
			return var = max;
		else if(var <= min)
			return var = min;
		else
			return var;
	}
	
	
	public static void main (String args[]){
		new Game();
	}
	
}
