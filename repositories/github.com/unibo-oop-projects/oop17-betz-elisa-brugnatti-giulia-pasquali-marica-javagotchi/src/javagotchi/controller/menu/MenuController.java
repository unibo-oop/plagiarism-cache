package javagotchi.controller.menu;

import java.util.List;
import javagotchi.model.Javagotchi;
import javagotchi.model.information.Avatar;
import javagotchi.model.information.Gender;
/**
 * This is theController for the menus.
 * @author giulia
 *
 */
public interface MenuController {
    /**
     * This method set the Javagotchi's fields like the user asked.
     * @param name **this is the name of the Javagotchi**
     * @param avatar **this is the avatar of the Javagotchi**
     * @param gender **this is the gender of the Javagotchi**
     */
    void setUserAvatarChoices(String name, Avatar avatar, Gender gender);

    /**
     * This method return the Javagotchi saved list.
     * @return list **this is the list of the Javagotchi saved**
     */
    List<Javagotchi> getListJavagotchi();
    /**
     * This method return the Avatar's unavailable list.
     * @return list **this is the list of the avatar unavailable**
     */
    List<String> getListAvatarUnavailable();
    /**
     * this method check if the naame's textfield is empty, or if it contains anything but not letters.
     * It returns true if it doesn't and false if it does.
     * @param name **this is the name the function must check**
     * @return true if the name follow the rules, false if it doesn't
     */
    boolean checkName(String name);
    /**
     * this method add an avatar to the list of the Javagotchi saved.
     * @param javagotchi **this is the Javagotchi to save on the list**
     */
    void addAvatarToList(Javagotchi javagotchi);
    /**
     *this method delete an avatar to the list of the Javagotchi saved.
     * @param name **this is the name of the Javagotchi the user wants to delete**
     */
    void deleteAvatar(String name);
    /**
     * this method ask to the infoManager to resume Javagotchi list previously saved.
     */
    void resumeFile();
    /**
     * this method ask to the infoManager to write on the file the Javagotchi list in order to save it.
     */
    void writeOnFile();
    /**
     * this method return the object InfoManger.
     * @return InfoManager
     */
    InformationManager getInfoManager();
    /**
     *  this method set the object InfoManger.
     * @param infoManager **this is the object infoManager saved**
     */
    void setInfoManager(InformationManager infoManager);
    /**
     *  this method override, in the list of the javagotchi saved, the Javagotchi used for playing, in order to update its levels needs.
     * @param javagotchi **javagotchi used for playing***
     */
    void update(Javagotchi javagotchi);
}
