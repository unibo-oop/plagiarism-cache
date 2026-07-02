package controller.image;


import javax.swing.ImageIcon;

/**
 * Interface of an ImageIcon manager for the board.
 * Andrea Serafini.
 *
 */
public interface BoardImageManagerInterface {

    /**
     *
     * @return the icon for the hero
     */
    ImageIcon getHero();

    /**
     *
     * @return the icon for the window
     */
    ImageIcon getIcon();

    /**
     *
     * @return the icon for the minotaurus
     */
    ImageIcon getMinotaurus();

    /**
     *
     * @return the icon for the scared hero
     */
    ImageIcon getSelectedHero();

    /**
     *
     * @param width
     *            the width to be set
     * @param height
     *            the width to be set
     */
    void resize(int width, int height);

    /**
    *
    * @return the background for the board
    */
    ImageIcon getBackground();

    /**
    *
    * @return the background for the side view
    */
    ImageIcon getPaper();

}
