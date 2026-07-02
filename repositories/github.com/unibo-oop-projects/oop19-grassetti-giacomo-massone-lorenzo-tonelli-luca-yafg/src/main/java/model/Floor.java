/** 
 * This class represents the the playground floor consisting of an image,
 * a position (x,y), height and width.
 * Getter and Setter included
 * @author Luca Tonelli
 * @version 1.1 
 */
package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Floor{
    private String path;
    private Image floor_image;
    private ImageView floor;
    
    /**
     * Constructor 
     * Set position (x,y), height and width.
     * @param path required
     * @throws FileNotFoundException if path is not valid.
     */
    public Floor(String path) throws FileNotFoundException {
     this.path = path.toString();
     this.floor_image = new Image(new FileInputStream(this.path));
     this.floor = new ImageView(this.floor_image);
     
     //Setting the position of the image 
     //floor.setX(0);
     //floor.setY(500);
     
     //setting the fit height and width of the image view 
     floor.setFitHeight(300);
     
     //Setting the preserve ratio of the image view 
     floor.setPreserveRatio(true);  
    }
    
    /** @return the ImageView related to the path */
    public ImageView getImageView() {
        return this.floor;
    }
    
    /** @return the Image related to the path */
    public Image getImage() {
        return this.floor_image;
    }
    
    /** set position x of the Floor */
    public void setX(double value){
        this.floor.setX(value);
    }
    
    /** v position y of the Floor */
    public void setY(double value){
        this.floor.setY(value);
    }
    
    /** @return position x of the Floor */
    public double getX() {
        return this.floor.getX();
    }
    
    /** @return position y of the Floor */
    public double getY() {
        return this.floor.getY();
    }
    
    /** @return width of the Floor */
    public double getWidth() {
        return this.floor.getFitWidth();
    }
    
    /** @return height of the Floor */
    public double getHeight() {
        return this.floor.getFitHeight();
    }
    
    public void setHeight(double value) {
        this.floor.setFitHeight(value);
    }
    
    public void setWidth(double value) {
        this.floor.setFitWidth(value);
    }
}
