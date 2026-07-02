package javagotchi.view.minigame.menu;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javagotchi.controller.minigame.main.MiniGame;
import javagotchi.utility.Utility;
import javagotchi.view.minigame.FactoryView;
import javagotchi.view.minigame.MenuDefault;

/**
 * 
 * @author marica
 *
 */
public class MenuMiniGame extends MenuDefault {

    private static final long serialVersionUID = 285023351395574949L;

    private final JLabel menu = new JLabel("Menu");
    private static final JButton NEWGAME = new JButton("New Game");
    private static final JButton CONT = new JButton("Continue");
    private static final JButton OPTIONS = new JButton("Options");
    private static final JButton EXIT = new JButton("Exit");
    private static final String PATHIMAGE = "/minigame/MenuImage.jpg";

    /**
     * Constructor for MenuMiniGame.
     */
    public MenuMiniGame() {
        super();
        this.setTitle(menu.getText());
        final JPanel pane = super.getImagePanel(PATHIMAGE);
        pane.add(super.getNorthPanel(menu), BorderLayout.NORTH);
        this.addButtonsInCenterPanel(super.getContainerCenterPanel());
        pane.add(getCenterPanel(), BorderLayout.CENTER);
        this.isGameSaved();
        this.setEvent();
    }

    private void isGameSaved() {
        if (MiniGame.getFactoryController().getControllerMiniGame().getSavedData().existFileSaveGame()) {
            CONT.setEnabled(true);
        } else {
            CONT.setEnabled(false);
        }
    }

    @Override
    protected final void addButtonsInCenterPanel(final GridBagConstraints constraints) {
        constraints.gridy = 0;
        super.getCenterPanel().add(NEWGAME, constraints);
        constraints.gridy++;
        super.getCenterPanel().add(CONT, constraints);
        constraints.gridy++;
        super.getCenterPanel().add(OPTIONS, constraints);
        constraints.gridy++;
        super.getCenterPanel().add(EXIT, constraints);
    }

    @Override
    protected final void setEvent() {
        NEWGAME.addActionListener(e -> {
            Utility.log("Click New Game ...");
            this.hideWindow();
            MiniGame.getFactoryController().getControllerMiniGame().newGame();
        });

        CONT.addActionListener(e -> {
            Utility.log("Click Continue ...");
            this.hideWindow();
            MiniGame.getFactoryController().getControllerMiniGame().continueGame();
        });

        OPTIONS.addActionListener(e -> {
            Utility.log("Click Options ...");
            this.hideWindow();
            FactoryView.createViewOptions().display();
        });

        EXIT.addActionListener(e -> {
            Utility.log("Click Exit ...");
            MiniGame.getFactoryController().getControllerMiniGame().exit();
            this.dispose();
        });
    }

    /**
     * Sets {@link MenuMiniGame#CONT}.
     * 
     * @param cond
     *            if true, the saved game file exists otherwise it doesn't exist.
     */
    public void setEnableContinueGame(final boolean cond) {
        CONT.setEnabled(cond);
    }

}
