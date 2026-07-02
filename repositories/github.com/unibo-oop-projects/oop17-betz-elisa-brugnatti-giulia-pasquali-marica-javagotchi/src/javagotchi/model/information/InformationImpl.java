package javagotchi.model.information;

import java.io.Serializable;
/**
 * 
 * @author giulia
 *
 */
public class InformationImpl implements Information, Serializable {
    private static final long serialVersionUID = 6317661835084379622L;
    private String name;
    private int age;
    private Gender gender;
    private Avatar avatar;
    
    /**
     * Constructor; it sets the initial field of the Javagotchi.
     * @param name **this is the name of the Javagotchi**
     * @param gender **this is the name of the Javagotchi**
     * @param avatar **this is the name of the Javagotchi**
     */
    public InformationImpl(final String name, final Gender gender, final Avatar avatar) {
        this.name = name;
        this.gender = gender;
        this.avatar = avatar;
        this.age = 0;
    }

    /**
     * this method return the name of the Javagotchi.
     * @return name
     */
    public String getName() {
        return name;
    }
    
    /**
     * this method set the name for the Javagotchi.
     * @param name **this is the name of the Javagotchi**
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * this method set the gender of the Javagotchi.
     * @param gender **this is the gender of the Javagotchi**
     */
    public void setGender(final Gender gender) {
        this.gender = gender;
    }
    
    /**
     * this method return the gender of the Javagotchi.
     * @return gender **this is the gender of the Javagotchi**
     */
    public Gender getGender() {
        return gender;
    }
    
    /**
     * this method return the avatar of the Javagotchi.
     * @return avatar
     */
    public Avatar getAvatar() {
        return avatar;
    }

    /**
     * this method to set the avatar of the Javagotchi.
     * @param avatar **this is the avatar of the Javagotchi**
     */
    public void setAvatar(final Avatar avatar) {
        this.avatar = avatar;
    }
    /**
     * this is the age of the Javagotchi.
     * @return age
     */
    public Integer getAge() {
        return age;
    }
    
    /**
     * this method make the Javagotchi get older.
     */
    public void getOlder() {
        this.age++;
    }
}
