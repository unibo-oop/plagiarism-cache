package utility;



import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.EntityType;


/**
 * Class used to load the images of the game.
 * 
 * The class is implemented with the SINGLETON pattern 
 * 
 * @author denis
 */
public class ImageLoader {
	

	
	private final static String BACKGROUND_1 = "background-1.png";
	private final static String BACKGROUND_2 = "background-2.png";
	private final static int BACKGROUND_WIDTH = 400;
	private final static int BACKGROUND_HEIGHT = 1300;
	private static final int CAR_WIDTH = 55;
    private static final int CAR_HEIGHT = 110;
    private static final double TRUCK_WIDTH = 75;
    private static final double TRUCK_HEIGHT = 200;
    private static final double MOTOR_WIDTH = 40;
    private static final double MOTOR_HEIGHT = 70;
    private static final double POWERUP_WIDTH = 60;
    private static final double POWERUP_HEIGHT = 60;
    
    
    private static ImageLoader loader;


	/**
	 *  Make the constructor private so that this class cannot be instantiated
	 */
	private ImageLoader() {
	}
	

	public static ImageLoader getInstance() {
		if (ImageLoader.loader == null) {
			ImageLoader.loader = new ImageLoader();
		}
		return ImageLoader.loader;
	}

	
	
	public ImageView getEntity(EntityType type, int id) {
		ImageView imagine = new ImageView();
		switch(type) {
		case PLAYER:
			imagine = this.getImage("player_"+String.valueOf(id)+".png",CAR_WIDTH,CAR_HEIGHT);
			break;
		case TRUCK:
			imagine = this.getImage("truck_"+String.valueOf(id)+".png", TRUCK_WIDTH, TRUCK_HEIGHT);
			break;
		case CAR:
			imagine = this.getImage("car_"+String.valueOf(id)+".png",CAR_WIDTH, CAR_HEIGHT);
			break;
		case MOTORBIKE:
			imagine =  this.getImage("motor_"+String.valueOf(id)+".png", MOTOR_WIDTH, MOTOR_HEIGHT);
			break;
		case SHIELD:
			imagine =  this.getImage("powerup_1.png", POWERUP_WIDTH, POWERUP_HEIGHT);
			break;
		case LIFE:
			imagine =  this.getImage("powerup_2.png", POWERUP_WIDTH, POWERUP_HEIGHT);
			break;
		}
		
		return imagine;
	}
	
	
	public Image getBackground1() {
		return new Image(this.getClass().getResourceAsStream("/Resources/" +BACKGROUND_1), BACKGROUND_WIDTH,BACKGROUND_HEIGHT,false, false);

	}
	
	public ImageView getBackground2() {
		Image images = new Image(this.getClass().getResourceAsStream("/Resources/" +BACKGROUND_2), BACKGROUND_WIDTH,BACKGROUND_HEIGHT,false, false);
		return new ImageView(images);
	}
	
	
	private ImageView getImage(String image, double width, double height) {
		Image images = new Image(this.getClass().getResourceAsStream("/Resources/" +image));  //test
		ImageView imageView = new ImageView(images);
		return imageView;
	}
	

	
}
