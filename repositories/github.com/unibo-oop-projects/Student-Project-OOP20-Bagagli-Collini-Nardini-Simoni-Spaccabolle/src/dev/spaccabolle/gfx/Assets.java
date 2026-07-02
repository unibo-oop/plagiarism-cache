/**
 * This class load the images for the game. There are two type of images
 * The first one is an array because it's change is color when someone pressed it
 * The seconde one is a static image
 * @author Sofia Bagagli
 */
package dev.spaccabolle.gfx;

import java.awt.image.BufferedImage;

// TODO: Auto-generated Javadoc
/**
 * The Class Assets.
 */
public class Assets {
	
	/** The Constant NUM_BOLLE. */
	private static final int NUM_BOLLE = 5;
	
	/** The Constant NUM_BUTTONS. */
	private static final int NUM_BUTTONS = 2;
	
	/** The Constant NUMBERS. */
	private static final int NUMBERS = 10;
	
	/** The numbers. */
	public static BufferedImage[] btn_start,btn_exit,btn_load,trasparent, ballGroup, numbers;

	/** The hard. */
	public static BufferedImage demo, cannon,arrow,dark_background, logo, dragon, dragon2, btn_pause, btn_save, btn_exit_statoGioco, game_over, victory, quitGame, exit, black, home, pause, easy, normal, hard;

	/**
	 * Inits the.
	 */
	public static void init(){
		
		logo = ImageLoader.loadImage("/res/textures/Logo.png");
		dark_background=ImageLoader.loadImage("/res/textures/dark_background.jpg");
		dragon = ImageLoader.loadImage("/res/textures/draghetto.png");
		cannon=ImageLoader.loadImage("/res/textures/cannoneFreccia.png");
		btn_save = ImageLoader.loadImage("/res/textures/save2.png");
	    btn_pause = ImageLoader.loadImage("/res/textures/Pause4.png");
	    btn_exit_statoGioco = ImageLoader.loadImage("/res/textures/exit3.png");
	    black = ImageLoader.loadImage("/res/textures/Blackground.png");
	    dragon2 = ImageLoader.loadImage("/res/textures/draghetto4.png");
	    home = ImageLoader.loadImage("/res/textures/home.png");
	    pause = ImageLoader.loadImage("/res/textures/pause2.png");
		exit = ImageLoader.loadImage("/res/textures/power button.png");
		easy = ImageLoader.loadImage("/res/textures/easy2.png");
		normal = ImageLoader.loadImage("/res/textures/normal2.png");
		hard = ImageLoader.loadImage("/res/textures/hard2.png");
		quitGame = ImageLoader.loadImage("/res/textures/quitGame.png");
		game_over = ImageLoader.loadImage("/res/textures/gameOver3.png");
	    victory = ImageLoader.loadImage("/res/textures/youWin.png");
	    demo = ImageLoader.loadImage("/res/textures/demo.png");
	    
		btn_start = new BufferedImage[NUM_BUTTONS];
			btn_start[0] = ImageLoader.loadImage("/res/textures/PLAY.png");
			btn_start[1] = ImageLoader.loadImage("/res/textures/PlayDark.png");
	    btn_load = new BufferedImage[NUM_BUTTONS];
            btn_load[0] = ImageLoader.loadImage("/res/textures/load.png");
            btn_load[1] = ImageLoader.loadImage("/res/textures/loadDark.png");
		btn_exit = new BufferedImage[NUM_BUTTONS];
            btn_exit[0] = ImageLoader.loadImage("/res/textures/exit.png");
            btn_exit[1] = ImageLoader.loadImage("/res/textures/exitDark.png");
        trasparent = new BufferedImage[NUM_BUTTONS];
			trasparent[0] = ImageLoader.loadImage("/res/textures/trasparent_button.png");
			trasparent[1] = ImageLoader.loadImage("/res/textures/trasparent_button.png");
			
    	numbers = new BufferedImage[NUMBERS];
    		numbers[0] = ImageLoader.loadImage("/res/textures/number_zero.png");
    		numbers[1] = ImageLoader.loadImage("/res/textures/number_one.png");
    		numbers[2] = ImageLoader.loadImage("/res/textures/number_two.png");
    		numbers[3] = ImageLoader.loadImage("/res/textures/number_three.png");
    		numbers[4] = ImageLoader.loadImage("/res/textures/number_four.png");
    		numbers[5] = ImageLoader.loadImage("/res/textures/number_five.png");
    		numbers[6] = ImageLoader.loadImage("/res/textures/number_six.png");
    		numbers[7] = ImageLoader.loadImage("/res/textures/number_seven.png");
    		numbers[8] = ImageLoader.loadImage("/res/textures/number_eight.png");
    		numbers[9] = ImageLoader.loadImage("/res/textures/number_nine.png");
    		
        ballGroup = new BufferedImage[NUM_BOLLE];
		    ballGroup[1] = ImageLoader.loadImage("/res/textures/staticRedBall.png");
		    ballGroup[2] = ImageLoader.loadImage("/res/textures/staticBlueBall.png");
		    ballGroup[3] = ImageLoader.loadImage("/res/textures/staticGreenBall.png");
		    ballGroup[4] = ImageLoader.loadImage("/res/textures/staticYellowBall.png");

	}
	
}