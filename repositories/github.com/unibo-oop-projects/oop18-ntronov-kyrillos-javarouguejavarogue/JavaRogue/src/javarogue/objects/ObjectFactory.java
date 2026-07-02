package javarogue.objects;

import javarogue.utility.Position;

/**
 * A factory to create game objects.
 */
public class ObjectFactory {
	
	private static ObjectFactory _instance = null;
	
	/**
	 * 
	 * @return The current instance of the ObjectFactory
	 */
	public static ObjectFactory GetInstance() {
		if(_instance == null) {
			_instance = new ObjectFactory();
		}
		
		return _instance;
	}
	
	/**
	 * Creates a new GameObject
	 * @param type The type of the object to create
	 * @param position The position of the object on the map
	 * @return The object
	 */
	public GameObject CreateObject(ObjectType type, Position position) {
		
		GameObject obj = null;
		
		switch(type) {
		case DOOR:
			obj = new Door(position, false);
			break;
		case CHEST:
			obj = new Chest(position);
			break;
		case HEALTH_POTION:
			obj = new HealthPotion(position);
			break;
		case ARMOR_1:
			obj = new Armor(position);
			break;
		case SWORD_1:
			obj = new Sword(position);
			break;
		default:
				break;
		}
		
		return obj;
	}
}
