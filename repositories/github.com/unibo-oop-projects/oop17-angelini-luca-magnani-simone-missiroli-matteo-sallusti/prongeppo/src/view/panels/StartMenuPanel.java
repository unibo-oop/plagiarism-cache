package view.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import utility.GameValues;
import utility.Utilities;
import view.ExitFrame;

/**
 * @author Luca
 *
 */
public class StartMenuPanel extends JPanel {
    /**
     * 
     */
    private static final long serialVersionUID = 6249031040307111365L;
    private final File  keytxt = new File(GameValues.KEYPATH);
    /**
     * @param layout **The layoutManager of this panel**
     * @param frame **The frame containing this panel
     * @throws IOException **
     */
    public StartMenuPanel(final LayoutManager layout, final JFrame frame) {
        super(layout);
        if (!keytxt.exists()) {
            BufferedWriter writer;
            try {
                keytxt.createNewFile();
                writer = new BufferedWriter(new FileWriter(keytxt));
                Utilities.writeToFile(GameValues.DEFAULTKEYBINDING, writer);
                } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        final JPanel grid = new JPanel(new GridLayout(3, 1));
        final JLabel label = new JLabel(new ImageIcon(getClass().getResource("/res/pong_title.png")));
        label.setOpaque(true);
        label.setBackground(Color.BLACK);

        final JButton play = this.createButton("Play");
        play.addActionListener(e -> {
            Utilities.changePanel(frame, this, new MatchChoicePanel(frame));
        });

        final JButton options = this.createButton("Options");
        options.addActionListener(e -> {
            Utilities.changePanel(frame, this, new SetOptions(frame));
            });

        final JButton exit = this.createButton("Exit");
        exit.addActionListener(e -> {
            final int n = JOptionPane.showConfirmDialog(this, "Do you really want to exit?", "Quitting..", JOptionPane.YES_NO_OPTION);
            if (n == JOptionPane.YES_OPTION) {
                new ExitFrame();
            }
        });

        grid.add(play);
        grid.add(options);
        grid.add(exit);
        this.add(label, BorderLayout.PAGE_START);
        this.add(grid, BorderLayout.CENTER);
        this.setVisible(true);
    }

    private JButton createButton(final String s) {
        return Utilities.initButton(s, Color.BLACK, Color.BLUE, GameValues.FONT_LARGE, new Dimension(GameValues.BUTTON_LARGE, GameValues.BUTTON_LARGE));
    }

}
