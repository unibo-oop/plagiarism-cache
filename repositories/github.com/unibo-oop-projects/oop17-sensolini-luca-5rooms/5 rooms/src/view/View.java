package view;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.Box;
import game.Controller;
import game.KeyInput;
import javax.swing.JFrame;
import javax.swing.WindowConstants;



public class View extends Canvas implements Runnable, ViewInterface{

	public static final int WIDTH = 820, HEIGHT = WIDTH / 12 * 9;
	private static final long serialVersionUID = 6688505729253666938L;
	private static final int MILLISECONDSPERDRAW = 40;
	
	private final Controller controller;
	private final KeyInput input;
	private final GUIView gui;
	
	private JFrame frame;
	private Window gameWindow;
	
	private Thread thread;
	private boolean running = false;

	public View (Controller controller){
		this.controller = controller;
		frame = new JFrame();
		gameWindow = new Window(WIDTH, HEIGHT, "5 Rooms", this);
		//frame.add(gameWindow);
		gui = new GUIView();
		input = new KeyInput(controller);
		this.addKeyListener(input);
		this.addMouseMotionListener(input);
		this.addMouseListener(input);
	}

	public synchronized void start(){
		thread = new Thread(this);
		thread.start();
		running = true;
		this.requestFocus();
	}

	public synchronized void stop(){
		try{
			//thread.join();
			running = false;
			hide();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public synchronized void run(){
		
		
		while(running){
			render();
			try {
				this.wait(MILLISECONDSPERDRAW);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		stop();
	}
	
	public void render(){
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		controller.getAll().forEach(o -> o.render(g));
		
		g.dispose();
		bs.show();
	}
	
	public void hide(){
		gameWindow.setVisible(false);
		gameWindow.hide();
	}
	
	public void show(){
		gameWindow.setVisible(true);
	}

	public Canvas getMainCanvas(){
		return this;
	}
	
	public void menuHide(){
		gui.hide();
	}
	
	public void menuShow(){
		gui.show();
	}
	
	public void WinScreen(){
		hide();
		frame = new JFrame();
		BufferedImage img = null;
		try {
		    img = ImageIO.read(View.class.getResource("/win.png"));
		} catch (IOException e) {
		}
		
		BackgroundPanel bg = new BackgroundPanel(img);
		Dimension minSize = new Dimension(WIDTH, HEIGHT);
		Dimension prefSize = new Dimension(WIDTH, HEIGHT);
		Dimension maxSize = new Dimension(WIDTH, HEIGHT);
		bg.add(new Box.Filler(minSize, prefSize, maxSize));
		
		frame.getContentPane().add(bg);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);	
		frame.setPreferredSize(prefSize);
		frame.pack();
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
