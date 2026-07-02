package javagotchi.model.information;
/**
 * This interface contains the main methods 
 * to manage the Javagotchi informations.
 * 
 * @author giulia
 */
public interface Information {
    /**
     * this method return the name of the Javagotchi.
     * @return name
     */
    String getName();
    /**
     * this method return the avatar of the Javagotchi.
     * @return avatar
     */
    Avatar getAvatar();
    /**
     * this method return the gender of the Javagotchi.
     * @return gender
     */
    Gender getGender();
    /**
     * this method return the age of the Javagotchi.
     * @return age
     */
    Integer getAge();
    /**
     * this method make the Javagotchi get older.
     */
    void getOlder();
    /**
     * this method set the name for the Javagotchi.
     * @param name **the name of the Javagotchi**
     */
    void setName(String name);
}
