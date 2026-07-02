package spacesurvival.controller.gui.focusgui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import spacesurvival.controller.gui.ManagerControllerGUI;
import spacesurvival.model.gui.Visibility;
import spacesurvival.utilities.LinkActionGUI;
import spacesurvival.utilities.StateLevelGUI;
import spacesurvival.view.GUI;

/**
 * Implements MouseListener for focus of GUIs.
 */
public class FocusGUI implements MouseListener {
    private final ManagerControllerGUI managerControllerGUI;

    /**
     * Initialize focus from ManagerControllerGUI.
     * @param managerControllerGUI is a ManagerControllerGUI.
     */
    public FocusGUI(final ManagerControllerGUI managerControllerGUI) {
        this.managerControllerGUI = managerControllerGUI;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseClicked(final MouseEvent e) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mousePressed(final MouseEvent e) {
        final LinkActionGUI id = managerControllerGUI.getControllerGUIFromGUI((GUI) e.getSource()).get().getMainLink();
        final int indexDifferent = 1;

        if (id.getStateLevel().equals(StateLevelGUI.FOREGROUND) 
                && this.managerControllerGUI.getCurrentGUI().get().getStateLevel().equals(StateLevelGUI.OVERLAY)) {

            int sizeList = this.managerControllerGUI.getChronology().size() - indexDifferent;
            while (this.managerControllerGUI.getChronology().get(sizeList).getStateLevel().equals(StateLevelGUI.OVERLAY)) {
                this.managerControllerGUI.getManagerGui().get(this.managerControllerGUI.getChronology().get(sizeList)).turn(Visibility.HIDDEN);
                this.managerControllerGUI.getChronology().remove(sizeList--);
            }
            this.managerControllerGUI.getCtrlSound().checkChangeSoundLoop(this.managerControllerGUI.getCurrentGUI().get());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseReleased(final MouseEvent e) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseEntered(final MouseEvent e) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseExited(final MouseEvent e) {
    }

}
