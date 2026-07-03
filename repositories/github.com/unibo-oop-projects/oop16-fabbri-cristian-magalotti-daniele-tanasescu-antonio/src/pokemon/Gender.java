package pokemon;

import java.util.Random;

/**
 * 
 * @author daniele
 *
 */

public enum Gender {
	MALE,
    FEMALE,
    GENDERLESS;
	
	//creazione e ritorno di un gender random
	public static Gender getRandomGender(){
		Random random = new Random();
		if(random.nextBoolean()){
		return MALE;
		}
		return FEMALE;
	}
}
