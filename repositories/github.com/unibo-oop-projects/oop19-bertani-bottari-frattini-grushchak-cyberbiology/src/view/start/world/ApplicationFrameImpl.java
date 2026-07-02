package view.start.world;

import java.awt.Color;
import java.awt.Component;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.world.CellInfosImp;
import model.world.World;
import model.world.WorldImp;
import utilities.Icon;
import view.clock.ViewClock;
import view.menu.data.setting.AddElem;
import view.menu.data.setting.AddElemValue;

/**
 * Class that implements the main window of the program with the specifications related to the world, the clock, button of stop,
 * the world and the ability to reset the language and filter for updating the world.
 */
public class ApplicationFrameImpl implements ApplicationFrame {

    private AddElemValue<Boolean> stop = stopButton();
    private AddElem world;
    private AddElem specific;
    private AddElemValue<JLabel> clockPanel = new ViewClock();
    private JFrame startFrame = new JFrame();

    /**
     * Builder that creates a graphical window by entering the world view from the one passed by parameter and adding 
     * the clock, the specifications always referred to wordImpl and the menu for setting the filters and language.
     * @param worldImpl the implemented world containing the elements you want to display
     */
    public ApplicationFrameImpl(final World worldImpl) {
        this.world = new ViewWorldImpl(worldImpl);
        this.specific = new SpecificWorld(new CellInfosImp(worldImpl));
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.add(world.getElem());
        panel.add(otherPanel());
        AddElem menuUpdate = new ChangeDate((ViewWorldImpl) world, (SpecificWorld) specific, (ViewClock) clockPanel);
        panel.add(menuUpdate.getElem());
        startFrame.add(panel);
        startFrame.setIconImage(Icon.APPLICATION.getIcon().getImage());
        startFrame.setTitle("GENOMY");
        startFrame.pack();
        startFrame.setResizable(false);
        startFrame.setLocationRelativeTo(null);
    }

    @Override
    public final UpdateWorld getWorld() {
        return (UpdateWorld) world;
    }

    @Override
    public final AddElemValue<Boolean> getButton() {
        return stop;
    }

    @Override
    public final JLabel getClockView() {
        return clockPanel.getValue();
    }

    @Override
    public final void show() {
        startFrame.setVisible(true);
    }

    @Override
    public final UpdateSpecific getSpecific() {
        return (UpdateSpecific) specific;
    }

    private JPanel otherPanel() {
        JPanel panel = new JPanel();
        JPanel stopanel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(clockPanel.getElem());
        stopanel.add(stop.getElem());
        panel.add(specific.getElem());
        panel.add(stopanel);
        return panel;
    }

    private AddElemValue<Boolean> stopButton() {
        JButton jb = new JButton("STOP");
        jb.addActionListener(a -> {
            jb.setEnabled(false);
            jb.setBackground(Color.LIGHT_GRAY);
        });
        return new AddElemValue<Boolean>() {
            @Override
            public Component getElem() {
                return jb;
            }

            @Override
            public Boolean getValue() {
                return jb.isEnabled();
            }
        };
    }
}
