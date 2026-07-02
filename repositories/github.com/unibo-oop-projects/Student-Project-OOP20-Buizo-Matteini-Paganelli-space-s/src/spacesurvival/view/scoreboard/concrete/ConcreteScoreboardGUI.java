package spacesurvival.view.scoreboard.concrete;

import java.awt.Color;
import java.awt.Font;
import java.awt.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import spacesurvival.utilities.LinkActionGUI;
import spacesurvival.view.AbstractGUI;
import spacesurvival.view.scoreboard.GUIScoreboard;
import spacesurvival.view.utilities.ButtonLink;

/**
 * Implement every element the scoreboard GUI must have.
 */
public class ConcreteScoreboardGUI extends AbstractGUI implements GUIScoreboard {
    private static final long serialVersionUID = -108440081332913533L;

    private final JLabel lbTitle;
    private final JTextField txtSearchName;
    private final JButton btnSearch;
    private final List scoreboard;
    private final JScrollPane scrollerScoreboard;
    private final ButtonLink btnBack;

    /**
     * Constructor of all scoreboard GUI items.
     */
    public ConcreteScoreboardGUI() {
        super();
        this.lbTitle = new JLabel();
        this.txtSearchName = new JTextField();
        this.btnSearch = new JButton();
        this.scoreboard = new List();
        this.scrollerScoreboard = new JScrollPane(scoreboard);
        this.btnBack = new ButtonLink();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public java.util.List<ButtonLink> getBtnActionLinks() {
        return java.util.List.of(this.btnBack);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setTextButtons(final java.util.List<String> listName) {
        for (int i = 0; i < listName.size(); i++) {
            this.getButton().get(i).setText(listName.get(i));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setBtnBackID(final LinkActionGUI mainAction, final LinkActionGUI action) {
        this.btnBack.setCurrentLink(mainAction);
        this.btnBack.setNextLink(action);
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
    public void setFontGUI(final Font font) {
        this.txtSearchName.setFont(font);
        this.btnSearch.setFont(font);
        this.scoreboard.setFont(font);
        this.btnBack.setFont(font);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setForegroundGUI(final Color color) {
        this.lbTitle.setForeground(color);
        this.btnSearch.setForeground(color);
        this.scoreboard.setForeground(color);
        this.btnBack.setForeground(color);
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
    public java.util.List<JButton> getButton() {
        return java.util.List.of(this.btnSearch, this.btnBack);
    }

    /**
     * Get JLabel for title GUI.
     * @return JLabel.
     */
    public JLabel getLbTitle() {
        return this.lbTitle;
    }

    /**
     * Get JTextField where put name for search in scoreboard.
     * @return JTextField.
     */
    public JTextField getTxtSearchName() {
        return this.txtSearchName;
    }

    /**
     * Get back button.
     * @return JButton for init search in scoreboard.
     */
    public JButton getBtnSearch() {
        return this.btnSearch;
    }

    /**
     * Get list of scoreboard.
     * @return list of scoreboard.
     */
    public List getScoreboard() {
        return this.scoreboard;
    }

    /**
     * Get JScrollPane of scoreboard.
     * @return JScrollPane of scoreboard.
     */
    public JScrollPane getScrollerScoreboard() {
        return this.scrollerScoreboard;
    }

    /**
     * Get back link button.
     * @return ButtonLink for link back.
     */
    public ButtonLink getBtnBack() {
        return this.btnBack;
    }
}
