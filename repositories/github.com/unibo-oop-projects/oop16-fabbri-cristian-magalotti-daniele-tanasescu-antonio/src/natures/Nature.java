package natures;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Random;

/**
 * 
 * @author Antonio
 *
 */

public class Nature implements Serializable{

    private static final long serialVersionUID = 1L;
    
    private final String name;
    private final double[] percentageModifiers;
    
    public Nature(final String name, final double[] percentageModifiers) {
        this.name = name;
        this.percentageModifiers = Arrays.copyOf(percentageModifiers, percentageModifiers.length);
    }
    
    public String getNatureName(){
        return this.name;
    }

    public double getNAtkPercentage(){
        return this.percentageModifiers[0];
    }
    
    public double getNDefPercentage(){
        return this.percentageModifiers[1];
    }
    
    public double getNSpePercentage(){
        return this.percentageModifiers[2];
    }
    
    public double getNSpAPercentage(){
        return this.percentageModifiers[3];
    }
    
    public double getNSpDPercentage(){
        return this.percentageModifiers[4];
    }
    
    public double[] getAllPercentages(){
        return Arrays.copyOf(this.percentageModifiers, this.percentageModifiers.length);
    }
    
    public static Nature getRandomNature(){
    	Random random = new Random();
    	int index = random.nextInt(25);
    	switch (index) {
    	case 0:
    	return new Adamant();
    	case 1:
    	return new Bashful();
    	case 2:
    	return new Bold();
    	case 3:
    	return new Brave();
    	case 4:
    	return new Calm();
    	case 5:
    	return new Careful();
    	case 6:
    	return new Docile();
    	case 7:
    	return new Gentle();
    	case 8:
    	return new Hardy();
    	case 9:
    	return new Hasty();
    	case 10:
    	return new Impish();
    	case 11:
    	return new Jolly();
    	case 12:
    	return new Lax();
    	case 13:
    	return new Lonely();
    	case 14:
    	return new Mild();
    	case 15:
    	return new Modest();
    	case 16:
    	return new Naive();
    	case 17:
    	return new Naughty();
    	case 18:
    	return new Quiet();
    	case 19:
    	return new Quirky();
    	case 20:
    	return new Rash();
    	case 21:
    	return new Relaxed();
    	case 22:
    	return new Sassy();
    	case 23:
    	return new Serious();
    	default:
    	return new Timid();
    	}
    }
}
