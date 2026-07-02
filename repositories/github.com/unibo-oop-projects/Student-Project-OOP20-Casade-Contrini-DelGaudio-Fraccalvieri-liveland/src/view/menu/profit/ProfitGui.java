package view.menu.profit;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.EnvironmentControllerImpl;
import view.menu.ActivityInsertionPanelBox;
import view.menu.fair.ActivityGui;
import view.menu.fair.WrongParametersException;
import view.model.activity.ActivityAlreadyPresentException;
import view.model.activity.ActivityType;
import view.model.activity.ViewActivityImpl;

/**
 * This class displays a dialog asking the user to set the parameters
 * to add a new profitable activity.
 */
public final class ProfitGui implements ActivityGui {
    private final JFrame frame = new JFrame();
    private final JPanel canvas = new JPanel();
    private final JTextField textField = new JTextField();
    private final SettingPanel settingPanel;
    private final JButton done = new JButton("Done");

    public ProfitGui(final EnvironmentControllerImpl view, final ActivityInsertionPanelBox gui, final ActivityType type) {
        canvas.setLayout(new BorderLayout());
        settingPanel = new SettingPanel(type);
        switch (type) {
        case SHOP:
            textField.setText("***Please, set the following shop fields***");
            break;
        case REST:
            textField.setText("***Please, set the following restaurant fields***");
        default:
            break;
        }
        textField.setBackground(Color.lightGray);
        textField.setEditable(false);
        canvas.add(textField, BorderLayout.NORTH);
        canvas.add(settingPanel, BorderLayout.CENTER);
        canvas.add(done, BorderLayout.SOUTH);

        frame.setContentPane(canvas);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        done.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                try {
                    final ViewActivityImpl newProfit = ProfitGui.this.settingPanel.buildNewProfitActivity();
                    try {
                        view.addNewActivity(newProfit);
                        gui.getGui().setActivityList(newProfit);
                        ProfitGui.this.frame.setVisible(false);
                        ProfitGui.this.frame.dispose();
                    } catch (ActivityAlreadyPresentException exc) {
                        ProfitGui.this.textField.setText(exc.getMessage());
                        ProfitGui.this.textField.setForeground(Color.RED);
                        ProfitGui.this.reset();
                    }
                } catch (WrongParametersException exc) {
                    ProfitGui.this.textField.setText(exc.getMessage());
                    ProfitGui.this.textField.setForeground(Color.RED);
                    ProfitGui.this.reset();
                }
            }
        });

        frame.pack();
        frame.setLocationByPlatform(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void display() {
        frame.setVisible(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void reset() {
        this.settingPanel.getNamePanel().setTextName("");
        this.settingPanel.getRangePanel().getTextMin().setText("");
        this.settingPanel.getRangePanel().getTextMax().setText("");
    }

}
