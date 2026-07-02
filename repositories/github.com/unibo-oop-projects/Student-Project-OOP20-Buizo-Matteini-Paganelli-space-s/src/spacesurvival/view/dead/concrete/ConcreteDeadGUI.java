package spacesurvival.view.dead.concrete;

import spacesurvival.model.gui.dead.EngineDead;
import spacesurvival.utilities.LinkActionGUI;
import spacesurvival.view.AbstractGUI;
import spacesurvival.view.dead.GUIDead;
import spacesurvival.view.utilities.ButtonLink;
import java.awt.Color;
import java.awt.Font;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.swing.JLabel;

/**
 * Implement every element the dead GUI must have.
 */
public class ConcreteDeadGUI extends AbstractGUI implements GUIDead {
    private static final long serialVersionUID = -517918898273724798L;
    private final JLabel lbTitleDead;
    private final List<ButtonLink> links;

    /**
     * Constructor of all dead GUI items.
     */
    public ConcreteDeadGUI() {
        super();
        this.lbTitleDead = new JLabel();
        this.links = Stream.generate(ButtonLink::new)
                .limit(EngineDead.N_BUTTONS).collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ButtonLink> getBtnActionLinks() {
        return this.links;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setTextButtons(final List<String> listNames) {
        for (int i = 0; i < listNames.size(); i++) {
            this.links.get(i).setText(listNames.get(i));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setBtnActions(final LinkActionGUI mainAction, final List<LinkActionGUI> actions) {
        for (int i = 0; i < actions.size(); i++) {
            this.links.get(i).setCurrentLink(mainAction);
            this.links.get(i).setNextLink(actions.get(i));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setFontTitleGUI(final Font font) {
        this.lbTitleDead.setFont(font);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setTitleGUI(final String titleGUI) {
        this.lbTitleDead.setText(titleGUI);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setForegroundTitle(final Color color) {
        this.lbTitleDead.setForeground(color);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setFontGUI(final Font font) {
        this.links.forEach(link -> link.setFont(font));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setForegroundGUI(final Color color) {
        this.links.forEach(link -> link.setForeground(color));
    }

    /**
     * Get label for title dead GUI.
     * 
     * @return label for title.
     */
    public JLabel getLabelTitle() {
        return this.lbTitleDead;
    }

}
