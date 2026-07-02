package spacesurvival.view.pause.concrete;

import spacesurvival.model.gui.pause.EnginePause;
import spacesurvival.utilities.LinkActionGUI;
import spacesurvival.view.AbstractGUI;
import spacesurvival.view.pause.GUIPause;
import spacesurvival.view.utilities.ButtonLink;

import java.awt.Color;
import java.awt.Font;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.JLabel;

/**
 * Implement every element the pause GUI must have.
 */
public class ConcretePauseGUI extends AbstractGUI implements GUIPause {
    private static final long serialVersionUID = 3292682835050526269L;
    private final JLabel lbTitle;
    private final List<ButtonLink> links;

    /**
     * Constructor of all pause GUI items.
     */
    public ConcretePauseGUI() {
        super();
        this.lbTitle = new JLabel();
        this.links = Stream.generate(ButtonLink::new)
                .limit(EnginePause.N_BUTTONS).collect(Collectors.toList());
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
    public ButtonLink getActionBtn(final int ind) {
        return this.links.get(ind);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setTextButtons(final List<String> listNames) {
        for (int i = 0; i < EnginePause.N_BUTTONS; i++) {
            this.links.get(i).setText(listNames.get(i));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLinkActionButtons(final LinkActionGUI mainAction, final List<LinkActionGUI> linksID) {
        for (int i = 0; i < linksID.size(); i++) {
            this.links.get(i).setCurrentLink(mainAction);
            this.links.get(i).setNextLink(linksID.get(i));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setFontTitleGUI(final Font font) {
        this.lbTitle.setFont(font);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setTitleGUI(final String title) {
        this.lbTitle.setText(title);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setForegroundGUI(final Color color) {
        this.lbTitle.setForeground(color);
        this.links.forEach(btn -> btn.setForeground(color));
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
    public void setBackgroundButtons(final Color color) {
        this.links.forEach(btn -> btn.setBackground(color));
    }

    /**
     * Get label for title pause GUI.
     * 
     * @return label for title.
     */
    public JLabel getLbTitle() {
        return this.lbTitle;
    }
}
