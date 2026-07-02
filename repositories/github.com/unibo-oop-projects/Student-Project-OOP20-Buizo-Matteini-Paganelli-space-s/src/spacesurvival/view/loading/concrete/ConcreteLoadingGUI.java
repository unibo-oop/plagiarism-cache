package spacesurvival.view.loading.concrete;

import spacesurvival.view.AbstractGUI;
import spacesurvival.view.loading.GUILoading;
import spacesurvival.view.utilities.ButtonLink;
import spacesurvival.view.utilities.FactoryGUIs;

import java.awt.Color;
import java.awt.Font;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;

public class ConcreteLoadingGUI extends AbstractGUI implements GUILoading {
    private static final long serialVersionUID = 7233089560220623525L;
    private final JLabel lbTitle;
    private final JProgressBar progressBar;

    public ConcreteLoadingGUI() {
        super();
        this.lbTitle = new JLabel();
        this.progressBar = new JProgressBar(SwingConstants.HORIZONTAL, 
                FactoryGUIs.MIN_PROGRESS, FactoryGUIs.MAX_PROGRESS);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ButtonLink> getBtnActionLinks() {
        return List.of();
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
    public void setForegroundGUI(final Color color) {
        this.lbTitle.setForeground(color);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLoading(final int value) {
        this.progressBar.setValue(value);
        this.progressBar.validate();
        this.validate();
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
        this.progressBar.setFont(font);
    }

    /**
     * Get label for title loading GUI.
     * 
     * @return label for title.
     */
    public JLabel getLbTitle() {
        return this.lbTitle;
    }

    /**
     * Get progressBar for loading.
     * @return JProgressBar.
     */
    public JProgressBar getProgressBar() {
        return this.progressBar;
    }
}
