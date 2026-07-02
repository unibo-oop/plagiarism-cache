package javagotchi.view.minigame.menu;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javagotchi.controller.minigame.main.MiniGame;
import javagotchi.utility.Utility;
import javagotchi.view.minigame.MenuDefault;

/**
 * 
 * @author marica
 *
 */
public final class PauseMenu extends MenuDefault {

    private static final long serialVersionUID = 1268905697508565448L;

    private static final int HEIGHT = 700;
    private final JLabel paused = new JLabel("Pause");
    private final JButton save = new JButton("Saved");

    private final JButton reset = new JButton("Reset");
    private final JButton resume = new JButton("Resume");
    private final JButton back = new JButton("Back");

    private static final String PATHIMAGE = "/minigame/PauseImage.jpg";

    /**
     * Constructor for PauseMenu.
     */
    public PauseMenu() {
        super(HEIGHT);
        this.setTitle(paused.getText());
        final JPanel pane = super.getImagePanel(PATHIMAGE);
        pane.add(super.getNorthPanel(paused), BorderLayout.NORTH);
        this.addButtonsInCenterPanel(super.getContainerCenterPanel());
        pane.add(getCenterPanel(), BorderLayout.CENTER);
        this.setEvent();
    }

    @Override
    protected void addButtonsInCenterPanel(final GridBagConstraints constraints) {
        constraints.gridy = 0;
        super.getCenterPanel().add(super.getMusic(), constraints);
        constraints.gridy++;
        super.getCenterPanel().add(save, constraints);
        constraints.gridy++;
        super.getCenterPanel().add(resume, constraints);
        constraints.gridy++;
        super.getCenterPanel().add(reset, constraints);
        constraints.gridy++;
        super.getCenterPanel().add(back, constraints);
    }

    @Override
    protected void setEvent() {

        save.addActionListener(e -> {
            Utility.log("Click Save ...");
            MiniGame.getFactoryController().getControllerMiniGame().saveGame();
            this.hideWindow();
        });

        resume.addActionListener(e -> {
            Utility.log("Click Resume ...");
            this.hideWindow();
            MiniGame.getFactoryController().getControllerMiniGame().resumeGame();
        });

        reset.addActionListener(e -> {
            Utility.log("Click Reset");
            this.hideWindow();
            MiniGame.getFactoryController().getControllerMiniGame().resetGame();
        });

        back.addActionListener(e -> {
            Utility.log("Click Back ...");
            this.hideWindow();
            MiniGame.getFactoryController().getControllerMiniGame().backToMenu();
        });

    }

}
