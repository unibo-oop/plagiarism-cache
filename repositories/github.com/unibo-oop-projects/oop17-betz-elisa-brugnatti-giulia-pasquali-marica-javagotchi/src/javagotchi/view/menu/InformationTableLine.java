package javagotchi.view.menu;
/**
 * This class manage entry of the information table.
 *
 * @author giulia
 *
 */
public class InformationTableLine {
    private final String name;
    private final String avatar;
    private final String gender;
    private final Integer age;
    private final Boolean alive;
    /**
     *  This is the constructor for this class.
     * @param name **this is the Javagotchi's name**
     * @param avatar **this is the Javagotchi's avatar**
     * @param gender **this is the Javagotchi's gender**
     * @param age **this is the Javagotchi's age**
     * @param alive **this is the Javagotchi's alive**
     */
    public InformationTableLine(final String name, final String avatar, final String gender, final Integer age, final Boolean alive) {
        super();
        this.name = name;
        this.avatar = avatar;
        this.gender = gender;
        this.age = age;
        this.alive = alive;
    }
    /**
     * 
     * @return string **the string that represent to the field name**
     */
    public String getName() {
        return name;
    }
    /**
     * 
     * @return string **the string that represent to the field avatar**
     */
    public String getAvatar() {
        return avatar;
    }
    /**
     * 
     * @return string **the string that represent to the field gender**
     */
    public String getGender() {
        return gender;
    }
    /**
     * 
     * @return the Javagotchi's age
     */
    public Integer getAge() {
        return age;
    }
    /**
     * 
     * @return string **the string that represent to the field alive**
     */
    public String getAlive() {
        return alive.toString();
    }
}
