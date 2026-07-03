package view.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import utility.GameValues;
import utility.Utilities;


/**
 * @author Paolo
 *
 */
public class KeyBinding extends JPanel {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private final File  keytxt = new File(GameValues.KEYPATH);
    /**
     * @param frame **The frame containing this panel**
     */
    public KeyBinding(final JFrame frame) {
        super(new BorderLayout(10, 10));
        final JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));
        final List<Integer> keys = Utilities.readFromFile(GameValues.KEYPATH);
        final List<KeyBindingPanel> players = new LinkedList<>();
        IntStream.range(0, 4).forEach(i -> players.add(new KeyBindingPanel(i, keys)));
        players.forEach(KBpanel -> panel.add(KBpanel));
        final JButton back = this.createButton("Back");
        final JLabel label = new JLabel();
        label.setText("Controls");
        label.setFont(new Font("Controls", Font.BOLD, GameValues.FONT_MEDIUM));
        label.setForeground(Color.RED);

        back.addActionListener(e -> {
            BufferedWriter writer;
            try {
                writer = new BufferedWriter(new FileWriter(keytxt));
                Utilities.writeToFile(keys, writer);
            } catch (IOException e1) {
                e1.printStackTrace();
                if (!keytxt.exists()) {
                    BufferedWriter writer2;
                    try {
                        keytxt.createNewFile();
                        writer2 = new BufferedWriter(new FileWriter(keytxt));
                        Utilities.writeToFile(GameValues.DEFAULTKEYBINDING, writer2);
                    } catch (IOException e3) {
                        e1.printStackTrace();
                    }
                }
            }
            Utilities.changePanel(frame, this, new SetOptions(frame));
        });
        this.add(label, BorderLayout.PAGE_START);
        this.add(panel, BorderLayout.CENTER);
        this.add(back, BorderLayout.PAGE_END);
        this.setVisible(true);
    }
    /**
     * @param s **string text of this button**
     * @return **this button**
     */
    private JButton createButton(final String s) {
        return Utilities.initButton(s, Color.BLACK, Color.WHITE, GameValues.FONT_MEDIUM, new Dimension(GameValues.BUTTON_MEDIUM, GameValues.BUTTON_SMALL));
    }
}
