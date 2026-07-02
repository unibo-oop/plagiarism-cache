package gameEnemiesManager;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 * @author Poggi Giovanni
 * 
 * This class manage the obstacles named "Cactus" and refresh the element in the game everytimes something call
 * the method update. It also do the creation and the delete of the cactus in the game.   
 */
public class Cactus extends Enemy {

	/**
	 * The following fields, in order of declaration, are used to save the vertical position
	 * of the cactus in the game, to save the x position of the obstacle, to save the width and the
	 * height of the cactus, to save one of the two differents image of cactus, to create the rectangle
	 * to put the cactus, to save the main character position. 
	 */
	private static final int POSY_IN_THE_LAND = 125;//vertical position of cactus, it change if the pos of land change
	private int positionX;							//x position
	private int width;								//width of rectangle of cactus
	private int height;								//height of rectangle of cactus
	private BufferedImage image;					//place to uplad one of the two images of cactus	
	private Rectangle rectangle;					//rectangle where put cactus
	private MainCharacter mainCharacter;
	
	/**
	 * Prepare the fields to use
	 * 
	 * @param positionX set the x position of cactus
	 * @param width set the width of cactus
	 * @param height set the height of cactus
	 * @param image set one of the two images of the cactus
	 * @param mainCharacter save the main character
	 */
	public Cactus(int positionX, int width, int height, BufferedImage image, MainCharacter mainCharacter) {
		this.positionX = positionX;		//set x position of cactus
		this.width = width;				//set width of cactus
		this.height = height;			//set height of cactus
		this.image = image;				//set one of the two images of the cactus
		this.mainCharacter = mainCharacter;
		rectangle = new Rectangle();	//create a rectangle for the cactus
	}
	
	/* Create the Rectangle of the Image of Cactus */
	@Override
	public Rectangle getBound() {
		rectangle = new Rectangle();
		rectangle.x = (int) positionX + (image.getWidth() - width)/2;						//set x pos
		rectangle.y = POSY_IN_THE_LAND - image.getHeight() + (image.getHeight() - height)/2;//set y pos
		rectangle.width = width;															//set width
		rectangle.height = height;															//set height
		return rectangle;
	}
	
	@Override
	public boolean isOutOfScreen() {
		if(positionX < -image.getWidth()) {
			return true;
		}
		return false;
	}
	
	@Override
	public void update() {
		positionX -= mainCharacter.getSpeedX();
	}
	
	@Override
	public void drawEnvironment(Graphics env) {
		env.drawImage(image, positionX, POSY_IN_THE_LAND - image.getHeight(), null);	//print image of cactus
		//env.setColor(Color.red); +++useless???+++
	}
}