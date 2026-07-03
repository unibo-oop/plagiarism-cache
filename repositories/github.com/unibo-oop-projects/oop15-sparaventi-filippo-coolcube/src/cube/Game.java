package cube;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Canvas;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import cube.entity.Entity;
import cube.graphics.Sprite;
import cube.graphics.SpriteSheet;
import cube.graphics.gui.Launcher;
import cube.input.KeyInput;
import cube.input.MouseInput;

public class Game extends Canvas implements Runnable {
	
	public static final int WIDTH = 270;
	public static final int HEIGHT = WIDTH/14*10;
	public static final int SCALE = 4;
	
	private Thread thread;
	private boolean running = false;
	
	private static BufferedImage [] levels;
	
	public static int coins = 0;
	public static int lives = 5;
	public static int level = 0;
	
	public static int deathScreenTime = 0;
	public static boolean showDeathScreen = true;
	public static boolean gameOver = false;
	public static boolean winner = false;
	
	public static boolean playing = false;
	
	public static Handler handler;
	public static SpriteSheet sheet;
	public static Camera cam;
	public static Launcher launcher;
	public static MouseInput mouse;
	
	public static Sprite grass;
	public static Sprite player;
	public static Sprite playerCool;
	public static Sprite mushroom;
	public static Sprite goomba;
	public static Sprite flag;
	public static Sprite coin;
	
	public static Sound themeTone;
	public static Sound dieTone;
	public static Sound gameOverTone;
	public static Sound levelUpTone;
	public static Sound coinTone;
	public static Sound winnerTone;
	public static Sound superCoolTone;
	public static Sound killTone;
	
	public Game(){
		Dimension size = new Dimension(WIDTH*SCALE, HEIGHT*SCALE);
		
		setPreferredSize(size);
		setMaximumSize(size);
		setMinimumSize(size);
	}
	
	/**
	 * inizializzo tutto ciò che è possibile vedere all'interno del gioco
	 *
	 */
	private void init(){
		handler = new Handler();
		sheet = new SpriteSheet("/spritesheet.png");
		cam = new Camera();
		launcher = new Launcher();
		mouse = new MouseInput();
		
		addKeyListener(new KeyInput());
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
		
		player = new Sprite(sheet, 1, 1);
		grass = new Sprite(sheet, 2, 1);
		mushroom = new Sprite(sheet, 3, 1);
		goomba = new Sprite(sheet, 4, 1);
		coin = new Sprite(sheet, 5, 1);
		flag = new Sprite(sheet, 6, 1);
		playerCool = new Sprite(sheet, 7, 1);
		
		levels = new BufferedImage[3];
		
		//handler.addEntity(new Player(300, 512, 64, 64, true, Id.player, handler));
		
		try {
			levels[0] = ImageIO.read(getClass().getResource("/level.png"));
			levels[1] = ImageIO.read(getClass().getResource("/level2.png"));
			levels[2] = ImageIO.read(getClass().getResource("/level3.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		themeTone = new Sound("/audio/themeTone.wav");
		gameOverTone = new Sound("/audio/gameOverTone.wav");
		levelUpTone = new Sound("/audio/levelUpTone.wav");
		coinTone = new Sound("/audio/coinTone.wav");
		winnerTone = new Sound("/audio/winnerTone.wav");
		dieTone = new Sound("/audio/dieTone.wav");
		superCoolTone = new Sound("/audio/superCoolTone.wav");
		killTone = new Sound("/audio/killTone.wav");
	}
	
	/**
	 * Gestisco lo start del Thread
	 *
	 */
	private synchronized void start(){
		System.out.println("Partito!");
		if(running) return;
		running = true;
		thread = new Thread(this, "Thread");
		thread.start();
	}
	
	/**
	 * Gestisco lo stop del Thread
	 *
	 */
	private synchronized void stop(){
		if(!running) return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	public void run(){
		init();
		requestFocus();
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		double delta = 0;
		double ns = 1000000000.0/60.0;
		int frames = 0;
		int ticks = 0;
		
		while(running){
			long now = System.nanoTime();
			delta+=(now-lastTime)/ns;
			lastTime = now;
			while(delta>=1){
				tick();
				ticks++;
				delta--;
				
			}
			render();
			frames++;
			if(System.currentTimeMillis()-timer>1000){
				timer+=1000;
				frames = 0;
				ticks = 0;
			}
		}
		stop();
	}
	
	/**
	 * Data una schermata, a seconda della situazione in cui mi trovo creo il contenuto 
	 * DeathScreen, GameOver, Win
	 *
	 */
	public void render(){
		BufferStrategy bs = getBufferStrategy();
		if(bs==null){
			createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, getWidth(), getHeight());
		if(!showDeathScreen){
			g.drawImage(Game.coin.getBufferedImage(), 20, 20, 75, 75, null);
			g.setColor(Color.white);
			g.setFont(new Font("courier", Font.BOLD, 30));
			g.drawString("X"+coins, 135, 95);
		}
		if(showDeathScreen){
			if(winner){
				g.setColor(Color.green);
				g.setFont(new Font("courier", Font.BOLD, 110));
				g.drawString("You Win!!!", 200, 350);
			}else if(!gameOver){
				g.setColor(Color.white);
				g.drawImage(Game.player.getBufferedImage(), 500, 300, 100, 100, null);
				g.setFont(new Font("courier", Font.BOLD, 50));
				g.drawString("X"+lives, 610, 400);
			}else{
				g.setColor(Color.white);
				g.setFont(new Font("courier", Font.BOLD, 100));
				g.drawString("Game Over!",	200, 350);
			}	
		}
		
		if(playing) g.translate(cam.getX(), cam.getY());
		if(!showDeathScreen && playing) handler.render(g);
		else if(!playing){
			launcher.render(g);
		}
		g.dispose();
		bs.show();
	}
	
	
	/**
	 * Gestisco quello che avviene all'interno del gioco
	 * Es: creo i livelli, cancello i livella alla morte, 
	 * 
	 *
	 */
	public void tick(){
		if(playing) handler.tick();
		
		for(int i=0; i<handler.entity.size();i++ ) {
			Entity e = handler.entity.get(i);
			if(e.getId()==Id.player){
				cam.tick(e);
			}
		}
		
		if(showDeathScreen && !gameOver) deathScreenTime++;
		if(deathScreenTime >= 180){
			showDeathScreen = false;
			deathScreenTime = 0;
			handler.clearLevel();
			handler.createLevel(levels[level]);
			
			themeTone.play();
			
			}
	}
	
	/**
	 * Restituisco la larghezza della schermata
	 *
	 * @return widht*scale
	 */
	public static int getFrameWidth(){
		return WIDTH*SCALE;
	}
	/**
	 * Restituisco l'altezza della schermata
	 *
	 * @return height*scale
	 */
	public static int getFrameHEIGHT(){
		return HEIGHT*SCALE;
	}
	
	/**
	 * gestisco il cambio di livello quando ad esempio viene raggiunta la bandiera.
	 * gestisco anche gli effetti sonori
	 *
	 */
	public static void switchLevel(){
		Game.level++;
		
		handler.clearLevel();
		handler.createLevel(levels[level]);
		themeTone.stop();
		levelUpTone.play();
		themeTone.play();
	}
	
	/**
	 * Restituisco l'area visibile all'interno del gioco
	 *
	 * @return Rectangle() con le posizioni oppure nullw
	 */
	public static Rectangle getVisibleArea(){
		for(int i=0; i<handler.entity.size(); i++){
			Entity e = handler.entity.get(i);
			if(e.getId()==Id.player) return new Rectangle(e.getX()-(getFrameWidth()/2-5), e.getY()-(getFrameHEIGHT()/2-5), 
					getFrameWidth()+10, getFrameHEIGHT()+10);
		}
		return null;
	}
	
	public static void main(String[] args){
		Game game = new Game();
		JFrame frame = new JFrame("The Cube");
		frame.add(game);
		frame.pack();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		game.start();
	}
	
}
