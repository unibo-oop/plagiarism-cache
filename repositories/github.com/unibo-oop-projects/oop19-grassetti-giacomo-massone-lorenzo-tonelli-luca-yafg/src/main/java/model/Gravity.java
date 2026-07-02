/** 
 * This class represents the gravity logic.
 * @author Luca Tonelli
 * @version 1.1 
 */
package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Gravity {
    public final static int GRAVITY=10;
    private int speed_jump=70;
    public Pair<Double,Double> nextPosExpected(Pair <Double,Double> start_pos, double speed, int dir ) {
        // Basic movement on the x-axis.
        double x=start_pos.getX();
        double y=start_pos.getY();
        
        if ( y >= 450) // Must be on the ground to jump.

        
            y -= speed_jump; // Move the player on the y-axis based on the strength of the jump.
            speed_jump -= 5; // Gradually decrease the strength of the jump by the player's weight.
        
        if (y >= 450) {
            speed_jump=70;
            y = 450; // Ensure the player does not fall through the floor.
        }
        
        return new Pair<Double,Double> (x,y);
    
  }
}
