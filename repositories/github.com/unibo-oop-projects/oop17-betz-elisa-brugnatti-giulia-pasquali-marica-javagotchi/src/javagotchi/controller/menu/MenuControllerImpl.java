package javagotchi.controller.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import javagotchi.model.Javagotchi;
import javagotchi.model.JavagotchiImpl;
import javagotchi.model.information.Avatar;
import javagotchi.model.information.Gender;
/**
 * This class implements the controller to manage the menu view and the saving function .
 * @author giulia
 */
public class MenuControllerImpl implements MenuController {
   private static final MenuControllerImpl SINGLETON = new MenuControllerImpl();
   private InformationManager infoManager;
   private List<String> avatarUnavailable;
   private List<Javagotchi> listJavagotchi;
   /**
    * this is the constructor for this class.
    */
   public MenuControllerImpl() {
      this.listJavagotchi = new ArrayList<>();
      this.avatarUnavailable = new ArrayList<>();
      infoManager = new InformationManagerImpl();
   }
   @Override
   public final void setUserAvatarChoices(final String name, final Avatar avatar, final Gender gender) {
      final Javagotchi model = new JavagotchiImpl(name, gender, avatar);
      this.addAvatarToList(model);
      model.getInformation().getOlder();
   }
   /**
     * it returns a single instance of this class.
     * @return MenuController
     */
    public static MenuControllerImpl getControllerImpl() {
        return SINGLETON;
    }

    @Override
   public final InformationManager getInfoManager() {
        return this.infoManager;
    }
    @Override
   public final void setInfoManager(final InformationManager infoManager) {
        this.infoManager = infoManager;
    }

    @Override
    public final List<Javagotchi> getListJavagotchi() {
        return listJavagotchi;
     }

    @Override
     public final void addAvatarToList(final Javagotchi javagotchi) {
        this.listJavagotchi.add(javagotchi);
        this.avatarUnavailable.add(javagotchi.getInformation().getAvatar().toString());
        writeOnFile();
     }
    @Override
   public final void deleteAvatar(final String element) {
       IntStream.range(0, listJavagotchi.size()).filter(i -> listJavagotchi.get(i).getInformation().getName().equalsIgnoreCase(element)).boxed().findFirst().map(i -> listJavagotchi.remove((int) i));
       avatarUnavailable = new ArrayList<>();
       listJavagotchi.stream().forEach(e -> {
           avatarUnavailable.add(e.getInformation().getAvatar().toString());
        });
       writeOnFile();
    }
    @Override
    public final void writeOnFile() {
       this.getInfoManager().writeOnFile(listJavagotchi);
    }

   @SuppressWarnings("unchecked")
   @Override
   public final void resumeFile() {
      this.listJavagotchi = (List<Javagotchi>) this.infoManager.resumeFile();
      avatarUnavailable = new ArrayList<>();
      this.listJavagotchi.forEach(e -> {
          avatarUnavailable.add(e.getInformation().getAvatar().toString());
      });
   }

   /**
    * this method check if the naame's textfield is empty, or if it contains anything but not letters.
    * @param name , is the string to check
    * @return true if it does or false if it doesn't 
    */
   public boolean checkName(final String name) {
      String firstName = "";
      firstName = name;
      return ((firstName.equals("")  ? false : true) && firstName.chars().allMatch(Character::isLetter)) && this.listJavagotchi.stream().noneMatch(e -> e.getInformation().getName().equals(name));
   }

   @Override
   public final List<String> getListAvatarUnavailable() {
      return this.avatarUnavailable;
   }
    @Override
    public final void update(final Javagotchi javagotchi) {
        IntStream.range(0, listJavagotchi.size()).filter(i -> listJavagotchi.get(i).getInformation().getName().equalsIgnoreCase(javagotchi.getInformation().getName())).boxed().findFirst().map(i -> listJavagotchi.set((int) i, javagotchi));
    }
}