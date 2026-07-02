package view.menu;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import controller.EnvironmentControllerImpl;

/**
 * This is the bottom panel containing buttons Save, Reset and Default setting.
 */
public class BottomPanel extends JPanel {

    private static final long serialVersionUID = 2507932941249841282L;
    private final JButton def = new JButton("Default activity setting");
    private final JButton start = new JButton("Start");
    private final JButton reset = new JButton("Reset");

    public BottomPanel(final EnvironmentControllerImpl view, final GraphicalUserInterface gui) {
        this.setLayout(new FlowLayout(FlowLayout.RIGHT));
        this.add(def);
        this.add(start);
        this.add(reset);

        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                try {
                    view.start();
                    gui.dispose();
                } catch (EmptyEnvironmentException exc) {
                    gui.getWelcomePanel().setWelcomeText(exc.getMessage());
                }
            }
        });

        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                gui.getMenuPanel().reset();
                view.resetActivityLists();
                BottomPanel.this.enableDisableButtons(false);
            }
        });

        def.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                new DefaultSetting(view, gui);
            }
        });
    }

    /**
     * It enables/disables Start and Default setting buttons.
     * @param flag needed to decide whether enable or disable action is needed
     */
    public void enableDisableButtons(final boolean flag) {
        this.start.setEnabled(flag);
        this.def.setEnabled(flag);
    }

}
