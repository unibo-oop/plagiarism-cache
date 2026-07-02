package javarogue.objects;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * 
 * Enumerator that defines the object types
 *
 */
public enum ObjectType {
	CHEST,
	DOOR,
	HEALTH_POTION,
	DEFENSE_POTION,
	ARMOR_1,
	SWORD_1;
	
	// Infrastructure to get a random enum
	private static final List<ObjectType> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
	private static final int SIZE = VALUES.size();
	private static final Random rndm = new Random();
	
	/*
	 * Returns a random object type
	 * */
	public static ObjectType randomType() {
		ObjectType random_obj = VALUES.get(rndm.nextInt(SIZE));
		
		// TODO
		// Think something better. I don't like recursion
		if(random_obj == ObjectType.CHEST || random_obj == ObjectType.DOOR)
			random_obj = ObjectType.randomType();
		
		return random_obj;
	}
}
