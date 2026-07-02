/**
 * This class represents a personage consisting of an image,
 * a position (x,y), height and width.
 * Getter and Setter included
 * @author Giacomo Grassetti
 * @version 1.1 
 * 
 */

package model;

import java.io.FileInputStream; 
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Character {
   
    private boolean jump=false;

    private String path;
    private Image sprite_image;
    private ImageView sprite;
    
    private static final double SPEED = 350 ; // pixels/second
    
    /**
     * Booleans indicative of the direction our character is pointing to.
     */
    private boolean up ;
    private boolean down ;
    private boolean left ;
    private boolean right ;
    
    /**
     * Double type values about the size of the scene where the character can move.
     */
    private final double minX ;
    private final double maxX ;
    private final double minY ;
    private final double maxY ;
    //gravity
    private double speed_jump=12;

    /**
     * Constructor 
     * Set position (x,y), height and width.
     * @param path, @param minX, @param minY, @param maxX, @param maxY
     * @throws FileNotFoundException if path is not valid.
     */

    public Character(String path, double minX, double maxX, double minY, double maxY) throws FileNotFoundException {
        
        this.path = path.toString();
        this.sprite_image = new Image(new FileInputStream(this.path));
        this.sprite = new ImageView(this.sprite_image); 
        
        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;
        
        //Setting the position of the image default
        sprite.setX(0);
        sprite.setY(475);
        
        //setting the fit height and width of the image view default 
        sprite.setFitHeight(400); 
        sprite.setFitWidth(200); 
        
        //Setting the preserve ratio of the image view  default
        sprite.setPreserveRatio(true);  
    }
    
    /** @return the ImageView related to the path */
    public ImageView getImageView() {
        return this.sprite;
    }
    
    /** @return the Image related to the path */
    public Image getImage() {
        return this.sprite_image;
    }
    
    /** set position x of the Character */
    public void setX(double value){
        this.sprite.setX(value);
        
    }
    
    /** set position y of the Character */

    public void setY(double value){
        this.sprite.setY(value);
    }
    
    /**
     * Updates the current position of the character based to the current speed and to the elapsed time.
     * @param elapsedSeconds since the last call of the method
     */
    public void move(double elapsedSeconds) {
        int horiz = 0 ;
        if (isLeft()) horiz = horiz - 3 ;
        if (isRight()) horiz = horiz + 3 ;

        setX(clamp(getX() + horiz * SPEED * elapsedSeconds, minX, maxX));

        int vert = 0 ;
        if (isUp() && !this.jump) {
            this.jump=true;
        }
        if(this.jump) {
            vert-=speed_jump; // Move the player on the y-axis based on the strength of the jump.
            speed_jump -= 0.45;
        }
        if(this.getY() >= 450) {
            speed_jump=12;
            this.jump=false;
            this.setY(450);
        }
        if (isDown()) vert = vert + 1 ;

        setY(clamp(getY() + vert * SPEED * elapsedSeconds, minY, maxY));
    }
    
    /**
     * 
     * @param value of the character in a "clamped" moment
     * @param minValue is the minimum our value could be
     * @param maxValue is the maximum our value could be
     * @return value if it is a legal position, otherwise minValue or maxValue based on context.
     */
    private double clamp(double value, double minValue, double maxValue) {
        if (value < minValue) return minValue ;
        if (value > maxValue) return maxValue ;
        return value ;
    }
    
    
    /** set position x of the Character */
    public double getX() {
        return this.sprite.getX();
    }
    
    /** @return position y of the Character  */
    public double getY() {
        return this.sprite.getY();
    }
    
    /** @return width of the Character */
    public double getWidth() {
        return this.sprite.getFitWidth();
    }
    
    /** @return height of the Character */
    public double getHeight() {
        return this.sprite.getFitHeight();
    }
    
    /**
     * A set of methods to get and set the current direction of the character.
     * 
     */
    public final boolean isUp() { return up ;}
    
    public final void setUp(boolean up) { this.up = up ;}
    
    public final boolean isDown() { return down ; }
    
    public final void setDown(boolean down) { this.down = down ; }
    
    public final boolean isLeft() { return left; }
    
    public final void setLeft(boolean left) { this.left = left ;}   
    
    public final boolean isRight() { return right ; }
    
    public final void setRight(boolean right) { this.right = right ;} 
    
}
