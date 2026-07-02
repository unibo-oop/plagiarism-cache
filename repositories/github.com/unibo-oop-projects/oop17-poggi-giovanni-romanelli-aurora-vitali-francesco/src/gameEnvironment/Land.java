package gameEnvironment;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import util.Resource;

public class Land extends Ambientation {
	
	private static final int POSY_OF_LAND = 103;//vertical position of land, it is the best value after tried tons of values
	private List<ImageLand> listOfAllLand;		//array with all of the kind of image of land
	private BufferedImage firstSpriteOfLand;	//first type of land
	private BufferedImage secondSpriteOfLand;	//second type of land
	private BufferedImage thirdSpriteOfLand;	//third type of land

	private MainCharacter mainCharacter;
	
	/* Set Environment of Land */
	public Land(int width) {
		this.mainCharacter = mainCharacter;
		firstSpriteOfLand = Resource.getResouceImage("res/land1.png");	//load first type of land
		secondSpriteOfLand = Resource.getResouceImage("res/land2.png");	//load second type of land
		thirdSpriteOfLand = Resource.getResouceImage("res/land3.png");	//load third type of land
		int numberOfLand = width / firstSpriteOfLand.getWidth(); 		//calculate how many lands to insert-AAA-try [...]+ 2; is it better or not?!
		listOfAllLand = new ArrayList<ImageLand>();						//create an array to insert of all image of lands
		for(int i = 0; i < numberOfLand; i++) {							//for to insert random lands on array
			ImageLand imageLand = new ImageLand();						//create variable to set image of land
			imageLand.positionX = i * firstSpriteOfLand.getWidth();		//set an increase position of image of land
			setLand(imageLand);											//choose a land from 3 type of it
			listOfAllLand.add(imageLand);								//add image of lands into list of all land
		}
	}
	
	/* Choose Randomly what type of Land to Insert */
	private int getTypeOfLand() {
		Random rand = new Random();			//create a random variable
		int landToChoose = rand.nextInt(3);	//choose randomly 0, 1 or 2, type of image of land
		switch (landToChoose) {
		case 0: 
			return 1;
		case 1: 
			return 3;
		default:
			return 1;
		}
	}
	
	/* Set one of the all Type of Lands in the Environment */
	private void setLand(ImageLand imgLand) {
		int type = getTypeOfLand();
		switch (type) {
		case 1:
			imgLand.image = firstSpriteOfLand;
			break;
		case 2: 
			imgLand.image = secondSpriteOfLand;
			break;
		default:
			imgLand.image = thirdSpriteOfLand;
			break;
		}
	}
	
	/* Class that Set Environment of Game */
	private class ImageLand {
		float positionX;		//position of the image of land
		BufferedImage image;	//image of land to display
	}

	/* Update Environment */
	@Override
	public void update() {
		Iterator<ImageLand> itr = listOfAllLand.iterator();							//create an iterator of all lands
		ImageLand firstElement = itr.next();										//first land to print
		firstElement.Xposition -= mainCharacter.getSpeedX();						//move the pos of land
		float previousPosX = firstElement.positionX;								//save previous pos
		while(itr.hasNext()) {														//set all new position of land
			ImageLand anotherLand = itr.next();
			anotherLand.positionX = previousPosX + firstSpriteOfLand.getWidth();	//move position of a land
			previousPosX = anotherLand.positionX;									//save old pos
		}
		if(firstElement.positionX < -firstSpriteOfLand.getWidth()) {				//when land is out of screen...
			listOfAllLand.remove(firstElement);										//...delete it
			firstElement.positionX = previousPosX + firstSpriteOfLand.getWidth();	//update pos
			setLand(firstElement);													//add another land
			listOfAllLand.add(firstElement);										//add land to list of all land
		}
	}
	
	/* Print Environment */
	@Override
	public void drawEnvironment(Graphics env) {
		for(ImageLand imgLand : listOfAllLand) {										//take all image of land...
			env.drawImage(imgLand.image, (int) imgLand.positionX, POSY_OF_LAND, null);	//...and print it
		}
	}
	
	//+++TRY TO ADD ISOUTOFSCREEN IN THIS PLACE+++ is the last if in update method
}