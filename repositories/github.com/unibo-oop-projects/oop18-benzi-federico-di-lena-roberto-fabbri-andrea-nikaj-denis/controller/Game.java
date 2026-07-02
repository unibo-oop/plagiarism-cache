package controller;

import java.lang.Thread;
import java.util.ArrayList;
import java.util.List;
import controller.command.Command;
import controller.command.CommandController;

import model.EntityFactory;
import model.HighScoreManager;
import model.Player;
import view.View;
import model.Entity;

public class Game implements Runnable {

	private View view;

	private boolean running = false;
	private Thread thread;
	private int score = 0;
	private ControllerSpawn cs = new ControllerSpawn();
	private CommandController contrCom;
	private Player player = new Player();
	private HighScoreManager hm = new HighScoreManager();

	int spawnTime = 100;
	private final static double WINDOWS_HEIGHT = 700;

	EntityFactory factor = new EntityFactory();
	Entity entity;

	public Game(final View v, final CommandController c) {
		this.view = v;
		this.contrCom = c;
	}

	/**
	 * Initialize method of the Game loop load the background animation
	 */
	public void init() {
		Game.this.view.initializeRoad();
	}

	public synchronized void start() {
		this.running = true;
		thread = new Thread(this);
		thread.start();
	}

	private synchronized void stop() {
		if (this.player.getHealth() == 0) {
			this.running = false;
		}
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void run() {
		this.player.resetPosition();
		List<Entity> enemies = new ArrayList<Entity>();

		cs.start();

		synchronized (enemies) {
			enemies.add(this.player);
		}
		this.init();
		long lastTime = System.nanoTime();
		final double ticks = 60.0;
		double ns = 1000000000 / ticks;
		double delta = 0;


		long timer = System.currentTimeMillis();

		while (running) {

			long now = System.nanoTime();

			delta += (now - lastTime) / ns;
			lastTime = now;

			if (delta >= 1) {
				delta--;
				this.score++;
				this.getInput(this.player);
				synchronized (enemies) {
					this.update(enemies);
				}

			}

			Thread enemiesThread = new Thread(() -> {
				Game.this.view.updateText(Game.this.player.getHealth() / 100, Game.this.player.getShield(),
						Game.this.score);
				spawnTime++;

				if (spawnTime == 200) {
					synchronized (enemies) {
						cs.spawnEntities().forEach(e -> {
							enemies.add(e);
						});
					}

					spawnTime = 0;
				}
				synchronized (enemies) {
					if (!enemies.isEmpty()) {
						Game.this.view.drawEntities(enemies);
						Game.this.player.collision(enemies);
					}
				}

			});
			enemiesThread.start();
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
			}

			synchronized (enemies) {
				this.remove(enemies);
			}

			if (this.player.getHealth() == 0) {
				this.hm.addScore(this.view.getSceneManager().getNickname(), this.score);
				this.view.gameOver();
				this.stop();

			}
		}
	}

	private void getInput(Player player) {
		Command command = this.contrCom.pollCommand();
		if (command != null) {
			command.execute(this.player);
		}
	}

	/**
	 * Update object in listEntities
	 * 
	 * @param listEntities
	 */
	private void update(List<Entity> listEntities) {
		if (!listEntities.isEmpty()) {
			listEntities.forEach(entity -> {
				entity.update();
			});
		}
	}

	/**
	 * Remove object out of map
	 * 
	 * @param listEntities
	 */
	private void remove(List<Entity> listEntities) {
		if (!listEntities.isEmpty()) {
			List<Entity> toRemove = new ArrayList<Entity>();
			listEntities.forEach(entity -> {
				if (entity.getPosition().getY() > WINDOWS_HEIGHT) {
					toRemove.add(entity);
				}
			});
			listEntities.removeAll(toRemove);
		}

	}

}
