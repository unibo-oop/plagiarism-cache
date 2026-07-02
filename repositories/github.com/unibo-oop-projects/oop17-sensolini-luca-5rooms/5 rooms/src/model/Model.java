package model;

import java.awt.event.MouseEvent;

import game.Controller;
import minigames.MiniGame;

public class Model implements Runnable, ModelInterface{
	
	private final Controller controller;
	
	private boolean running = false;
	private Thread thread;
	
	public Model(Controller controller) {
		this.controller = controller;
		this.start();
	}

	public synchronized void start(){
		thread = new Thread(this);
		thread.start();
		running = true;
	}

	public synchronized void stop(){
		try{
			//thread.join();
			running = false;
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public void run(){
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1){
				tick();
				delta--;
			}
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				//System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();
	}
	
	public void tick(){
		controller.getAll().forEach(o -> o.tick());
	}
	
	public int startGame(MiniGame game){
		return 1;
	}

	public void keyPressed (int key){
		controller.getAll().forEach(o -> o.keyPressed(key));
	}
	
	public void keyReleased (int key){
		controller.getAll().forEach(o -> o.keyReleased(key));
	}
	
	public void mouseDragged(MouseEvent e) {
		controller.getAll().forEach(o -> o.mouseDragged(e));
	}

	public void mouseMoved(MouseEvent e) {
		controller.getAll().forEach(o -> o.mouseMoved(e));
	}
	
	public void mouseClicked(MouseEvent e){
		controller.getAll().forEach(o -> o.mouseClicked(e));
	}

}
