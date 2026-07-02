package model;


/**
 * The factory class of template method used for entitites
 * @author denis
 *
 */
public class EntityFactory {

	
	public EntityFactory() {}
	
	public Entity getEntity (EntityType type){
        Entity retval = null;
        switch (type){
            case PLAYER:
                retval = new Player();
                break;
            case CAR:
                retval = new Enemy();
                break;
            case TRUCK:
                retval = new Enemy();
                break;
            case MOTORBIKE:
                retval = new Enemy();
                break;
		default:
			break;
        }
        return retval;
    }
}
