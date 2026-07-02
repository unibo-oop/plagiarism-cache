package javagotchi.view.menu;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javagotchi.controller.menu.MenuController;
import javagotchi.model.information.Avatar;
/**
 * Implements the MenuView interface.
 * 
 * @author giulia
 *
 */
public class MenuViewImpl implements MenuView {
    private MenuManager menu;
    private final List<JLabel> imagesAvatarLabelList;
    private final List<JLabel> imagesGenderLabelList;
    private final List<String> avatarNameList;
    /**
     * 
     * @param controller **this is the MenuController**
     */
    public MenuViewImpl(final MenuController controller) {
        imagesAvatarLabelList = new ArrayList<JLabel>();
        imagesGenderLabelList = new ArrayList<JLabel>();
        avatarNameList = new ArrayList<>();

        menu = new MenuManagerImpl(controller, this);
        this.setMenu(menu);
        final URL catURL = MenuViewImpl.class.getResource("/javagotchi/cat.gif");
        final URL pandaURL = MenuViewImpl.class.getResource("/javagotchi/panda.gif");
        final URL foxURL = MenuViewImpl.class.getResource("/javagotchi/fox.gif");
        final ImageIcon cat = new ImageIcon(catURL);
        final ImageIcon panda = new ImageIcon(pandaURL);
        final ImageIcon fox = new ImageIcon(foxURL);
        final JLabel imageFox = new JLabel();
        imageFox.setIcon(fox);
        final JLabel imagePanda = new JLabel();
        imagePanda.setIcon(panda);
        final JLabel imageCat = new JLabel();
        imageCat.setIcon(cat);
        imagesAvatarLabelList.add(imageFox);
        imagesAvatarLabelList.add(imagePanda);
        imagesAvatarLabelList.add(imageCat);
        final URL femaleURL = MenuViewImpl.class.getResource("/javagotchi/female.png");
        final ImageIcon female = new ImageIcon(femaleURL);
        final JLabel imageFemale = new JLabel();
        imageFemale.setIcon(female);
        final URL maleURL = MenuViewImpl.class.getResource("/javagotchi/male.png");
        final ImageIcon male = new ImageIcon(maleURL);
        final JLabel imageMale = new JLabel();
        imageMale.setIcon(male);
        imagesGenderLabelList.add(imageMale);
        imagesGenderLabelList.add(imageFemale);
        avatarNameList.add(Avatar.FOX.toString());
        avatarNameList.add(Avatar.PANDA.toString());
        avatarNameList.add(Avatar.CAT.toString());
    }

    @Override
    public final List<JLabel> getImagesAvatarList() {
        return this.imagesAvatarLabelList;
    }
    @Override
    public final List<JLabel> getImagesGenderList() {
        return this.imagesGenderLabelList;
    }

    @Override
    public final List<String> getListAvatarName() {
        return avatarNameList;
    }

    @Override
    public final MenuManager getMenuManager() {
        return this.menu;
    }

    @Override
    public final void setMenu(final MenuManager menu) {
        this.menu = menu;
    }
}