package spacesurvival.view.menu.concrete;

import spacesurvival.model.gui.menu.EngineMenu;
import spacesurvival.utilities.LinkActionGUI;
import spacesurvival.view.AbstractGUI;
import spacesurvival.view.menu.GUIMenu;
import spacesurvival.view.utilities.ButtonLink;

import java.awt.Color;
import java.awt.Font;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * Implement every element the menu GUI must have.
 */
public class ConcreteMenuGUI extends AbstractGUI implements GUIMenu {
    private static final long serialVersionUID = 1L;

    private final JLabel lbTitle;
    private final JTextField txfNamePlayer;
    private final List<ButtonLink> links;

   /**
    * Constructor of all menu GUI items.
    */
   public ConcreteMenuGUI() {
        super();
        this.lbTitle = new JLabel();
        this.txfNamePlayer = new JTextField();
        this.links = Stream.generate(ButtonLink::new)
                .limit(EngineMenu.N_BUTTONS).collect(Collectors.toList());
    }

   /**
    * {@inheritDoc}
    */
    @Override
    public final List<ButtonLink> getBtnActionLinks() {
        return this.links;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setTextButtons(final List<String> listName) {
        for (int i = 0; i < listName.size(); i++) {
            this.links.get(i).setText(listName.get(i));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setBtnActions(final LinkActionGUI mainAction, final List<LinkActionGUI> linksID) {
        for (int i = 0; i < linksID.size(); i++) {
            this.links.get(i).setCurrentLink(mainAction);
            this.links.get(i).setNextLink(linksID.get(i));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setForegroundGUI(final Color color) {
        this.lbTitle.setForeground(color);
        this.links.forEach(button -> button.setForeground(color));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setFontTitleGUI(final Font font) {
        this.lbTitle.setFont(font);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setFontGUI(final Font font) {
        this.txfNamePlayer.setFont(font);
        this.links.forEach(button -> button.setFont(font));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setTitleGUI(final String title) {
        this.lbTitle.setText(title);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setColumnsNamePlayer(final int sizeColumn) {
        this.txfNamePlayer.setColumns(sizeColumn);
    }

    /**
     * Get a link button in the list by index.
     * 
     * @param ind is index for list.
     * @return link button.
     */
    public final ButtonLink getButton(final int ind) {
        return this.links.get(ind);
    }

    /**
     * Get label for title menu GUI.
     * 
     * @return label for title.
     */
    public final JLabel getLabelTitle() {
        return this.lbTitle;
    }

    /**
     * Get JTextField for player name text.
     * 
     * @return JTextField for player name text. 
     */
    public final JTextField getTxfNamePlayer() {
        return this.txfNamePlayer;
    }

    /**
     * Descriptions of menu GUI concrete.
     */
    @Override
    public String toString() {
        return "GUIMenuConcrete [lbTitle=" + lbTitle + ", txfNamePlayer=" + txfNamePlayer + ", links=" + links + "]";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getTextNamePalyer() {
        return this.txfNamePlayer.getText();
    }

}
