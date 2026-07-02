package gameEnvironment;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import util.Resource;

public class Sky extends Ambientation {
	
	private List<ImageCloud> listOfCloud;	//list of all type of cloud
	private BufferedImage cloud;			//image of cloud
	
	private MainCharacter mainCharacter;

	/* Create all Cloud */
	public Sky(int width) {
		cloud = Resource.getResouceImage("res/cloud.PNG");	//load image of cloud
		listOfCloud = new ArrayList<ImageCloud>();			
		
		ImageCloud imageCloud = new ImageCloud();			//create a cloud
		imageCloud.positionX = 0;							//set x pos
		imageCloud.positionY = 30;							//set y pos
		listOfCloud.add(imageCloud);						//put image in the array
		
		imageCloud = new ImageCloud();						//create a cloud
		imageCloud.positionX = 150;							//set x pos
		imageCloud.positionY = 40;							//set y pos
		listOfCloud.add(imageCloud);						//put image in the array
		
		imageCloud = new ImageCloud();						//create a cloud
		imageCloud.positionX = 300;							//set x pos
		imageCloud.positionY = 500;							//set y pos
		listOfCloud.add(imageCloud);						//put image in the array
		
		imageCloud = new ImageCloud();						//create a cloud
		imageCloud.positionX = 450;							//set x pos
		imageCloud.positionY = 20;							//set y pos
		listOfCloud.add(imageCloud);						//put image in the array
		
		imageCloud = new ImageCloud();						//create a cloud
		imageCloud.positionX = 600;							//set x pos
		imageCloud.positionY = 60;							//set y pos
		listOfCloud.add(imageCloud);						//put image in the array
	}
	
	/* Structure of Cloud */
	private class ImageCloud {
		float positionX;		//x pos in the environment of cloud
		int positionY;			//y pos in the environment of cloud
	}

	/* Update Environment */
	@Override
	public void update() {
		Iterator<ImageCloud> itr = listOfCloud.iterator();			//take all cloud from array
		ImageCloud firstElement = itr.next();
		firstElement.positionX -= mainCharacter.getSpeedX()/8;	
		while(itr.hasNext()) {										//update all position of cloud 
			ImageCloud element = itr.next();
			element.positionX -= mainCharacter.getSpeedX()/8;
		}
		if(firstElement.positionX < -cloud.getWidth()) {			//if the cloud is out of screen...
			listOfCloud.remove(firstElement);						//...remove it
			firstElement.positionX = GameWindow.SCREEN_WIDTH;
			listOfCloud.add(firstElement);							//add new cloud
		}		
	}

	/* Print Environment */
	@Override
	public void drawEnvironment(Graphics env) {
		for(ImageCloud imgLand : listOfCloud) {										//take all image of cloud...
			env.drawImage(cloud, (int) imgLand.positionX, imgLand.positionY, null);	//...and print it
		}
	}
}