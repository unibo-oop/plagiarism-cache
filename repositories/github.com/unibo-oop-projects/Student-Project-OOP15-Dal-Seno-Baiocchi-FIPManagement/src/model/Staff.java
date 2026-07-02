package model;

import java.util.Date;
/**
 * Model a staff member
 * @author lucadalseno
 *
 */
public class Staff extends PersonImpl {
    /**
     * 
     */
    private static final long serialVersionUID = 4869245935781128086L;
    private ROLE role;

    public Staff(String name, String surname, Date birth, String cf,ROLE role) {
        super(name, surname, birth, cf);
        this.role = role;
    }
    
    /**
     * Getter for role
     * @return the role of the staff member
     */
    public ROLE getRole(){
        return this.role;
    }
    
    /**
     * Setter for role
     * @param r: the role to set
     */
    public void setRole(ROLE r){
        this.role = r;
    }
    
    /**
     * The role a staff member can have
     * @author lucadalseno
     *
     */
    public static enum ROLE{
        HEADCOACH,ASSISTANT_COACH,SCOUT,SPORTS_DIRECTOR,PRESIDENT,VICE_PRESIDENT,DOCTOR,MASSEUR,PHYSIOTERAPIST
    }
}