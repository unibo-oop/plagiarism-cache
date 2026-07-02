package spacesurvival.view.game.utilities;

import spacesurvival.utilities.dimension.ScaleOf;
import spacesurvival.utilities.dimension.Screen;
import spacesurvival.utilities.path.Icon;
import spacesurvival.view.utilities.ButtonLink;
import spacesurvival.view.utilities.FactoryGUIs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Implements a button that changes image when the mouse enters.
 */
public class BtnPauseLinkAction extends ButtonLink implements MouseListener {
    private static final long serialVersionUID = -768157071922765184L;
    private final String pathIconEnter;
    private final String pathIconExit;

    /**
     * Implements the components of the pause button and its MouseListener.
     */
    public BtnPauseLinkAction() {
        super();
        super.setBorder(null);
        this.pathIconExit = Icon.PAUSE;
        this.pathIconEnter = Icon.PAUSE_2FACE;

        FactoryGUIs.setIconJButtonFromRate(this, this.pathIconExit, ScaleOf.ICON_FULL, Screen.WIDTH_FULLSCREEN);
        this.addMouseListener(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseEntered(final MouseEvent e) {
        FactoryGUIs.setIconJButtonFromRate(this,
                this.pathIconEnter, ScaleOf.ICON_FULL, Screen.WIDTH_FULLSCREEN);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseExited(final MouseEvent e) {
        FactoryGUIs.setIconJButtonFromRate(this,
                this.pathIconExit, ScaleOf.ICON_FULL, Screen.WIDTH_FULLSCREEN);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseClicked(final MouseEvent e) { }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mousePressed(final MouseEvent e) { }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseReleased(final MouseEvent e) { }
}
