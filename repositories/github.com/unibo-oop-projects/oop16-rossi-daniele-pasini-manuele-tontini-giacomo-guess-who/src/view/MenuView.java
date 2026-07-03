package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.util.Optional;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import controller.Controller;
import controller.ControllerImpl;
import utilities.Audio;
import utilities.ResourceLoader;
import utilities.Audio.Song;
import utilities.ScreenResolution;

/***/
public class MenuView {
    private static final String TITLE = "Guess Who?";
    private static final int VGAP = 30;
    private static final double FRAMEWIDTH_PROP = 0.45;
    private static final double FRAMEHEIGHT_PROP = 0.45;
    private static final double LOGOHEIGHT_PROP = 0.35;
    private static final double CMBHEIGHT_PROP = 0.10;
    private static final int R = 208;
    private static final int G = 72;
    private static final int B = 72;

    /**
     * Represents the main view. Defines the methods for starting the game.
     * 
     * 
     */
    public MenuView() {
        final Controller controller = ControllerImpl.getController();
        final GUIFactory factory = GUIFactoryImpl.getFactory();
        final MyFrame frame = new MyFrame(TITLE, Optional.empty(), new FlowLayout());
        final JPanel contentPane = factory.createPanel(Optional.empty());
        final MyLabel logo = new MyLabel(new ImageIcon(ResourceLoader.loadImage("/images/logo.png")));
        final JComboBox<String> cmbSelectPack = factory.createComboBox(controller.getPackList());
        final JLabel jlbl = factory.createLabel("Scegli il pacco di personaggi che vuoi utilizzare:");
        final JButton btn = factory.createButton("Avvia");
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        btn.addActionListener(e -> {
            Audio.playSound(Song.BUTTON_CLICK, false);
            frame.dispose();
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    controller.loadPack(cmbSelectPack.getSelectedItem().toString());
                }
            });
            Audio.playSound(Song.BACKGROUND, true);
        });
        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(final java.awt.event.MouseEvent evt) {
                btn.setForeground(Color.BLACK);
            }

            public void mouseExited(final java.awt.event.MouseEvent evt) {
                btn.setForeground(Color.WHITE);
            }
        });
        contentPane.add(logo);
        contentPane.add(Box.createRigidArea(new Dimension(0, VGAP)));
        contentPane.add(jlbl);
        contentPane.add(cmbSelectPack);
        contentPane.add(Box.createRigidArea(new Dimension(0, VGAP)));
        contentPane.add(btn);
        contentPane.add(Box.createRigidArea(new Dimension(0, VGAP)));
        frame.setPreferredSize(new Dimension((int) (ScreenResolution.getScreenWidth() * FRAMEWIDTH_PROP),
                (int) (ScreenResolution.getScreenHeight() * FRAMEHEIGHT_PROP)));
        logo.setPreferredSize(new Dimension((int) frame.getPreferredSize().getWidth(),
                (int) (frame.getPreferredSize().getHeight() * LOGOHEIGHT_PROP)));
        cmbSelectPack.setPreferredSize(new Dimension((int) frame.getPreferredSize().getWidth(),
                (int) (frame.getPreferredSize().getHeight() * CMBHEIGHT_PROP)));
        frame.getMainPanel().add(contentPane);
        frame.getMainPanel().setBackground(new Color(R, G, B));
        frame.pack();
        frame.setLocation((ScreenResolution.getScreenWidth() - frame.getWidth()) / 2,
                (ScreenResolution.getScreenHeight() - frame.getHeight()) / 2);
        frame.setVisible(true);
    }
}
