package bzzbomber.view;

import java.util.LinkedList;
import java.util.List;

import javax.swing.ImageIcon;

/**
 * This class implements a Collection of @Avatar . This supplies the method for
 * manage the entity of the BzzBomber.
 *
 */
public class CollectionAvatar {

    /**
     * It's the number of @Avatar in the game.
     */
    public static final int NUMBERAVATAR = 3;
    private static final int INITLIST = 0;

    private final List<Avatar> avatarList = new LinkedList<>();
    private int index;

    /**
     * The constructor of the class @CollectionAvatar .
     * 
     */
    public CollectionAvatar() {
        this.avatarList.add(new Avatar("/avatar/beekeeper.png", "Bee Killer"));
        this.avatarList.add(new Avatar("/avatar/insectsman.png", "Bug Killer"));
        this.avatarList.add(new Avatar("/avatar/insetticidaMan.png", "Uomo Spruzzino"));
        this.index = 0;
    }

    /**
     * Getter of the Avatar's Icon.
     * 
     * @return The @ImageIcon .
     */
    public ImageIcon getActualIcon() {
        return this.avatarList.get(this.index).getCard();
    }

    /**
     * Getter of the Avatar's index in the collection.
     * 
     * @return The index .
     */
    public int getIndex() {
        return this.index;
    }

    /**
     * Getter of the Avatar's name.
     * 
     * @return The @String of the name .
     */
    public String getActualText() {
        return this.avatarList.get(this.index).getName();
    }

    /**
     * Function for shift the selection of @Avatar to the right.
     * 
     */
    public void slideRight() {
        if (this.index < (NUMBERAVATAR - 1)) {
            this.index++;
        } else {
            this.index = INITLIST;
        }
    }

    /**
     * Function for shift the selection of @Avatar to the left.
     * 
     */
    public void slideLeft() {
        if (this.index > INITLIST) {
            this.index--;
        } else {
            this.index = (NUMBERAVATAR - 1);
        }
    }

}
