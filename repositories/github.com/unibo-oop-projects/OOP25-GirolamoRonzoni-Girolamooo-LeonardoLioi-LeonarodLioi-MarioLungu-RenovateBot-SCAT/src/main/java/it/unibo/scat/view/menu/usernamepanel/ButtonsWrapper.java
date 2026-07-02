package it.unibo.scat.view.menu.usernamepanel;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import it.unibo.scat.view.UIConstants;
import it.unibo.scat.view.api.ViewActionsInterface;
import it.unibo.scat.view.components.CustomButton;

/**
 * A container panel responsible for displaying and managing the ship selection
 * buttons.
 */
public final class ButtonsWrapper extends JPanel {
    private static final long serialVersionUID = 1L;

    /**
     * Initializes the panel with selectable ship buttons.
     * 
     * @param menuActionsInterface the menu actions interface.
     */
    public ButtonsWrapper(final ViewActionsInterface menuActionsInterface) {

        setLayout(new GridLayout(1, 3, 10, 10));

        final int buttonsCounter = 3;
        final CustomButton[] buttons = new CustomButton[buttonsCounter];

        for (int i = 0; i < buttonsCounter; i++) {
            final int index = i;
            buttons[i] = new CustomButton(UIConstants.PLAYER_PATHS.get(i));

            buttons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent e) {
                    menuActionsInterface.setChosenShipIndex(index);

                    for (int a = 0; a < buttonsCounter; a++) {
                        buttons[a].setSelection(false);
                        buttons[a].repaint();
                    }

                    buttons[index].setSelection(true);
                }
            });

            add(buttons[i]);
        }

    }
}
