package javagotchi.view.menu;

import java.util.List;

import javax.swing.JLabel;
/**
 *  This is the interface of the MenuView;
 *  it contains the main methods to accede at the MenuManager and to set it properly.
 * @author giulia
 *
 */
public interface MenuView {
   /**
    * this method set the object MenuManager.
    * @param menu **this is the MenuManager**
    */
   void setMenu(MenuManager menu);
   /**
    * this method return the object MenuManager.
    * @return menu 
    */
   MenuManager getMenuManager();
   /**
    * this method return the list of the avatar name.
    * @return list
    */
   List<String> getListAvatarName();

   /**
    * this method return the list of the Avatar images.
    * @return list
    */
   List<JLabel> getImagesAvatarList();
   /**
    * this method return the list of the Gender images.
    * @return list
    */
   List<JLabel> getImagesGenderList();
}
