package Model;

import java.io.Serializable;

/**
 * This class implements the class Room, passing the name
 * 
 * @author Francesco Ceroni
 * 
 */


public class RoomImpl implements Room,Serializable {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    protected String nameRoom;
    
    public RoomImpl(final String name){
        this.nameRoom = name;
    }

    public String getNameRoom() {
        
        return this.nameRoom;
    }

}
