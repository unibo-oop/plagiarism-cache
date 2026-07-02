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
public class OptionsMenu extends MenuDefault {

    private static final long serialVersionUID = 1268905697508565448L;

    private final JLabel optionsLab = new JLabel("Options");
    private final JButton minitut = new JButton("Tutorial");
    private final JButton back = new JButton("Back");
    private final JButton classification = new JButton("Classification");
    private static final String PATHIMAGE = "/minigame/OptionsImage.jpg";

    /**
     * Constructor for OptionsMenu.
     */
    public OptionsMenu() {
        super();
        this.setTitle(optionsLab.getText());
        final JPanel pane = super.getImagePanel(PATHIMAGE);
        pane.add(super.getNorthPanel(optionsLab), BorderLayout.NORTH);
        this.addButtonsInCenterPanel(super.getContainerCenterPanel());
        pane.add(getCenterPanel(), BorderLayout.CENTER);
        this.isGamePlayed();
        this.setEvent();
    }

    @Override
    protected final void addButtonsInCenterPanel(final GridBagConstraints constraints) {
        constraints.gridy = 0;
        super.getCenterPanel().add(super.getMusic(), constraints);
        constraints.gridy++;
        super.getCenterPanel().add(minitut, constraints);
        constraints.gridy++;
        super.getCenterPanel().add(classification, constraints);
        constraints.gridy++;
        super.getCenterPanel().add(back, constraints);
    }

    private void isGamePlayed() {
        if (MiniGame.getFactoryController().getControllerMiniGame().getSavedData().existFileBestScore()) {
            classification.setEnabled(true);
        } else {
            classification.setEnabled(false);
        }
    }

    @Override
    protected final void setEvent() {

        minitut.addActionListener(e -> {
            Utility.log("Click Tutorial ...");
            this.hideWindow();
            FactoryView.createMiniTutorial();
            if (MiniGame.getFactoryController().getMusic().isOn()) {
                MiniGame.getFactoryController().getMusic().stopAudio();
                super.getMusic().setIconMusic();
            }
        });

        classification.addActionListener(e -> {
            Utility.log("Click Classification ...");
            FactoryView.createBestView().display();
        });

        back.addActionListener(e -> {
            Utility.log("Click Back ...");
            this.hideWindow();
            MiniGame.getFactoryController().getControllerMiniGame().getView().getMainMenu().display();
        });
    }
}
