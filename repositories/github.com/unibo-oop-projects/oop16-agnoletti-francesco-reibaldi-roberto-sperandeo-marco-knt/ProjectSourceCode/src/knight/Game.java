package knight;
import java.awt.image.*;
import java.io.IOException;
import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import input.*;
import entity.Entity;
import graphics.*;
import graphics.gui.*;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 7526472295622776147L;
	
	public static final int WIDTH = 270;
	public static final int HEIGHT = WIDTH/14*10;
	public static final int SCALE = 4;

	private Thread thread;
	private boolean running = false;

	private static BufferedImage[] levels;

	public static double totalPoints=0;
	public static int crystalGet=0;
	public static int totCrystalGet=0;
	public static int firstGhostKill=0;
	public static int thirdGhostKill=0;
	public static int totGhostKill=0;
	public static int boss_1Kill=0;
	public static int boss_2Kill=0;
	public static int lives=3;
	public static int level=0;
	public static String nome;

	public static int deathScreenTime=0;
	public static boolean showDeathScreen=true;
	public static boolean gameOver=false;
	public static boolean winner=false;
	public static boolean beginGame=true;
	public static boolean playing=false;
	public static boolean rankb=false;
	public static boolean infob=false;
	public static int frames=0;
	public static int ticks=0;

	public static Handler handler;
	public static SpriteSheet sheet;
	public static Camera cam;
	public static Launcher launcher;
	public static Rank rank;
	public static Info info;
	public static MouseInput mouse;
	public static KeyInput keyBoard;
	public static Chronometer chrono;

	public static Sprite playerSx_1;
	public static Sprite playerSx_2;
	public static Sprite playerDx_1;
	public static Sprite playerDx_2;
	public static Sprite playerJumpDx;
	public static Sprite playerJumpSx;
	public static Sprite playerFireDx;
	public static Sprite playerFireSx;
	public static Sprite ground;
	public static Sprite wall;
	public static Sprite powerUp;
	public static Sprite energyWave;
	public static Sprite energyWaveBossDx;
	public static Sprite energyWaveBossSx;
	public static Sprite firstGhostDx;
	public static Sprite firstGhostSx;
	public static Sprite secondGhostDx;
	public static Sprite secondGhostSx;
	public static Sprite thirdGhostDx;
	public static Sprite thirdGhostSx;
	public static Sprite firstBossDx;
	public static Sprite firstBossSx;
	public static Sprite secondBossDx;
	public static Sprite secondBossSx;
	public static Sprite door;
	public static Sprite crown;
	public static Sprite crystal;
	public static Sprite specialCrystal;

	public static Sound themeTone;
	public static Sound waveHit;
	public static Sound explode;
	public static Sound playerHit;
	public static Sound playerAttack;
	public static Sound getPowerUp;
	public static Sound gameOverTone;
	public static Sound levelUpTone;
	public static Sound getObj;
	public static Sound winnerTone;

	public Game() {
		Dimension size = new Dimension(WIDTH*SCALE,HEIGHT*SCALE);

		setPreferredSize(size);
		setMaximumSize(size);
		setMinimumSize(size);
	}

	/**
	 * inizializzo tutto ciò che è possibile vedere all'interno del gioco
	 *
	 */
	private void init() throws IOException {
		handler = new Handler();
		sheet = new SpriteSheet("/spritesheet.png");
		cam = new Camera();
		launcher = new Launcher();
		chrono = new Chronometer();
		rank = new Rank();
		info = new Info();
		mouse = new MouseInput();
		keyBoard = new KeyInput();
		
		addKeyListener(keyBoard);
		addMouseListener(mouse);
		addMouseMotionListener(mouse);

		playerDx_1 = new Sprite(sheet, 5, 1);
		playerDx_2 = new Sprite(sheet, 6, 1);
		playerSx_1 = new Sprite(sheet, 2, 1);
		playerSx_2 = new Sprite(sheet, 1, 1);
		playerJumpDx = new Sprite(sheet, 7, 1);
		playerJumpSx = new Sprite(sheet, 3, 1);
		playerFireDx = new Sprite(sheet, 8, 1);
		playerFireSx = new Sprite(sheet, 4, 1);
		ground = new Sprite(sheet, 4, 3);
		wall = new Sprite(sheet, 3, 3);
		powerUp = new Sprite(sheet, 7, 3);
		energyWave = new Sprite(sheet, 6, 3);
		energyWaveBossDx = new Sprite(sheet, 3, 8);
		energyWaveBossSx = new Sprite(sheet, 4, 8);
		door = new Sprite(sheet, 1, 3);
		crown = new Sprite(sheet, 8, 3);
		crystal = new Sprite(sheet, 5, 3);
		specialCrystal = new Sprite(sheet, 9, 3);
		firstGhostDx = new Sprite(sheet, 3, 6);
		firstGhostSx = new Sprite(sheet, 2, 6);
		secondGhostDx = new Sprite(sheet, 5, 6);
		secondGhostSx = new Sprite(sheet, 6, 6);
		thirdGhostDx = new Sprite(sheet, 9, 6);
		thirdGhostSx = new Sprite(sheet, 8, 6);
		firstBossDx = new Sprite(sheet, 1, 8);
		firstBossSx = new Sprite(sheet, 2, 8);
		secondBossDx = new Sprite(sheet, 8, 8);
		secondBossSx = new Sprite(sheet, 7, 8);

		levels = new BufferedImage[3];

		try {
			levels[0] = ImageIO.read(getClass().getResource("/lev_1.png"));
			levels[1] = ImageIO.read(getClass().getResource("/lev_2.png"));
			levels[2] = ImageIO.read(getClass().getResource("/lev_3.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		themeTone = new Sound("/audio/themeTone.wav");
		waveHit = new Sound("/audio/wavehit.wav");
		playerHit = new Sound("/audio/playerhit.wav");
		playerAttack = new Sound("/audio/playerattack.wav");
		explode = new Sound("/audio/explode.wav");
		getPowerUp = new Sound("/audio/powerup.wav");
		gameOverTone = new Sound("/audio/gameOverTone.wav");
		levelUpTone = new Sound("/audio/levelUpTone.wav");
		getObj = new Sound("/audio/coinTone.wav");
		winnerTone = new Sound("/audio/winnerTone.wav");
	}

	/**
	 * Gestisco lo start del Thread
	 *
	 */
	private synchronized void start() {
		System.out.println("Partito!");
		if (running)
			return;
		running = true;
		thread = new Thread(this, "Thread");
		thread.start();
	}

	/**
	 * Gestisco lo stop del Thread
	 *
	 */
	private synchronized void stop() {
		if (!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		try {
			init();
		} catch (IOException e) {
			e.printStackTrace();
		}
		requestFocus();
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		double delta = 0;
		double ns = 1000000000.0 / 60.0;
		frames = 0;
		ticks = 0;

		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				ticks++;
				delta--;
			}
			render();
			frames++;
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				frames = 0;
				ticks = 0;
			}
		}
		stop();
	}

	/**
	 * Data una schermata, a seconda della situazione in cui mi trovo, creo il
	 * contenuto DeathScreen, GameOver, Win
	 *
	 */
	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		Graphics g2 = bs.getDrawGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, getWidth(), getHeight());
		if (!showDeathScreen) {
			g.setColor(Color.white);
			g.setFont(new Font("courier", Font.BOLD, 20));
			g.drawImage(Game.crystal.getBufferedImage(), 20, 20, 40, 40, null);
			g.drawString("X" + crystalGet + "/" + handler.totCrystal, 60, 55);
			g.drawImage(Game.firstGhostDx.getBufferedImage(), 150, 20, 40, 40, null);
			g.drawString("X" + firstGhostKill + "/" + handler.totFirstGhost, 190, 55);
			g.drawImage(Game.secondGhostDx.getBufferedImage(), 260, 20, 40, 40, null);
			g.drawString("X" + handler.totSecondGhost, 300, 55);
			g.drawImage(Game.thirdGhostDx.getBufferedImage(), 360, 20, 40, 40, null);
			g.drawString("X" + thirdGhostKill + "/" + handler.totThirdGhost, 400, 55);
		}
		if (showDeathScreen) {
			if (winner) {
				g.setColor(Color.green);
				g.drawImage(Game.playerDx_1.getBufferedImage(), 200, 300, 100, 100, null);
				g.setFont(new Font("courier", Font.BOLD, 90));
				g.drawString("You Win!", 350, 400);
				g.drawImage(Game.playerSx_1.getBufferedImage(), 800, 300, 100, 100, null);
				g2.setColor(Color.green);
				g2.setFont(new Font("courier", Font.BOLD, 30));
				g2.drawString("Tempo di gioco: "+chrono.totalTime+" secondi", 290, 470);
				g2.drawString("Nemici sconfitti: "+totGhostKill, 290, 520);
				g2.drawString("Cristalli raccolti: "+totCrystalGet, 290, 570);
				g2.drawString("Punteggio totale realizzato: "+Game.totalPoints, 290, 620);
			} else if (!gameOver) {
				g.setColor(Color.white);
				g.drawImage(Game.playerDx_1.getBufferedImage(), 420, 300, 100, 100, null);
				g.setFont(new Font("courier", Font.BOLD, 70));
				g.drawString("X" + lives, 520, 400);
			} else {
				g.setColor(Color.red);
				g.drawImage(Game.secondGhostDx.getBufferedImage(), 190, 300, 100, 100, null);
				g.setFont(new Font("courier", Font.BOLD, 90));
				g.drawString("Game Over!", 290, 400);
				g.drawImage(Game.thirdGhostSx.getBufferedImage(), 810, 300, 100, 100, null);
				g2.setColor(Color.red);
				g2.setFont(new Font("courier", Font.BOLD, 30));
				g2.drawString("Tempo di gioco: "+chrono.totalTime+" secondi", 290, 470);
				g2.drawString("Nemici sconfitti: "+totGhostKill, 290, 520);
				g2.drawString("Cristalli raccolti: "+totCrystalGet, 290, 570);
				g2.drawString("Punteggio totale realizzato: "+Game.totalPoints, 290, 620);
			}
		}

		if (playing)
			g.translate(cam.getX(), cam.getY());
		if (!showDeathScreen && playing)
			handler.render(g);
		else if (!playing) {
			if (!rankb || !infob) {
				launcher.render(g);
			}
			if (rankb) {
				rank.render(g);
			} else if (infob) {
				info.render(g, g2);
			}
		}
		g.dispose();
		bs.show();
	}

	/**
	 * Gestisco quello che avviene all'interno del gioco Es: creo/cancello i
	 * livelli
	 *
	 */
	public void tick() {
		if (playing)
			handler.tick();

		for (int i = 0; i < handler.entity.size(); i++) {
			Entity e = handler.entity.get(i);
			if (e.getId() == Id.player) {
				cam.tick(e);
			}
		}

		if (showDeathScreen)
			deathScreenTime++;
		if (deathScreenTime >= 120 && !gameOver) {
			showDeathScreen = false;
			deathScreenTime = 0;
			handler.clearLevel();
			handler.createLevel(levels[level]);
			themeTone.play();
			if (beginGame) {
				chrono.startTime();
			}
		}
		if (deathScreenTime >= 240 && (gameOver || winner)) {
			chrono.stopTime();
			deathScreenTime = 0;
			gameOver = false;
			winner = false;
			beginGame = true;
			Game.playing = false;
			Game game = new Game();
			lives = 3;
			level = 0;
			// controllare di azzerare eventuali variabili inserite dopo, tipo mobkill
			game.start();
		}
	}

	/**
	 * gestisco il cambio di livello quando viene raggiunta la porta.
	 *
	 */
	public static void switchLevel() {
		Game.level++;
		totGhostKill = totGhostKill+(firstGhostKill+thirdGhostKill);
		totCrystalGet = totCrystalGet+crystalGet;
		handler.totCrystal = 0; // azzero il totale degli oggetti creati nel livello
		handler.totFirstGhost = 0;
		handler.totSecondGhost = 0;
		handler.totThirdGhost = 0;
		firstGhostKill = 0;
		thirdGhostKill = 0;
		crystalGet = 0;
		handler.clearLevel();
		handler.createLevel(levels[level]);
		themeTone.stop();
		levelUpTone.play();
		themeTone.play();
	}

	/**
	 * Restituisco la larghezza della schermata
	 *
	 * @return widht*scale
	 */
	public static int getFrameWidth() {
		return WIDTH * SCALE;
	}

	/**
	 * Restituisco l'altezza della schermata
	 *
	 * @return height*scale
	 */
	public static int getFrameHEIGHT() {
		return HEIGHT * SCALE;
	}

	/**
	 * Restituisco l'area visibile all'interno del gioco
	 *
	 * @return Rectangle() con le posizioni oppure null ??
	 */
	public static Rectangle getVisibleArea() {
		for (int i = 0; i < handler.entity.size(); i++) {
			Entity e = handler.entity.get(i);
			if (e.getId() == Id.player)
				return new Rectangle(e.getX() - (getFrameWidth() / 2 - 5),
						e.getY() - (getFrameHEIGHT() / 2 - 5),
						getFrameWidth() + 10, getFrameHEIGHT() + 10);
		}
		return null;
	}

	public static void main(String[] args) {
		Game game = new Game();
		JFrame frame = new JFrame("The knight's tale");
		frame.add(game);
		frame.pack();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		game.start();
	}

}