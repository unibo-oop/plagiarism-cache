package view.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.KeyListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import utility.GameValues;
import utility.Utilities;
import java.awt.event.KeyEvent;

/**
 * @author Paolo
 */
public class KeyBindingPanel extends JPanel {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private static final String UP = "Up: ";
    private static final String DOWN = "Down: ";
    private final JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    /**
     * @param num **The player index**
     * @param list **The list of all registered KeyEvents**
     */
    public KeyBindingPanel(final int num, final List<Integer> list) {
        super(new BorderLayout());
        this.setBackground(Color.GRAY);
        this.panel.setBackground(Color.GRAY);
        this.setFocusable(true);
        final JButton su = this.createButton(UP + KeyEvent.getKeyText(list.get(num * 2)));
        final JButton giu = this.createButton(DOWN + KeyEvent.getKeyText(list.get(num * 2 + 1)));
        this.add(this.createLabel("Player " + (num + 1), Color.BLACK, Color.RED), BorderLayout.PAGE_START);
        panel.add(su);
        panel.add(giu);
        this.add(panel, BorderLayout.CENTER);

        class MyBindingKeyListener implements KeyListener {
            private final int index;
            private final JButton button;
            private final String string;
            /**
             * 
             * @param num **the index where it has to read in list**
             * @param button **button where it will be add a KeyListener**
             * @param stringa **first part of button text (UP, DOWN)**
             */
            MyBindingKeyListener(final int num, final JButton button, final String stringa) {
                this.index = num;
                this.button = button;
                this.string = stringa;
            }
            @Override
            public void keyTyped(final KeyEvent e) { }
            @Override
            public void keyReleased(final KeyEvent e) { } 
            @Override
            public void keyPressed(final KeyEvent e) {
                if (!list.contains(e.getExtendedKeyCode()) || list.get(this.index).equals(e.getExtendedKeyCode())) {
                    list.set(this.index, e.getExtendedKeyCode());
                    final String keyText = KeyEvent.getKeyText(e.getExtendedKeyCode());
                    button.setText(this.string + keyText);
                    panel.removeKeyListener(this);
                } else {
                    final String[] options = { "OK", "CANCEL" };
                    JOptionPane.showOptionDialog(null, "Key already used", "Warning", 
                            JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
                            null, options, options[0]);
                    panel.removeKeyListener(this);
                }
            }
        }

        su.addActionListener(e -> {
            panel.addKeyListener(new MyBindingKeyListener(num * 2, su, UP));
            panel.grabFocus();
        });
        giu.addActionListener(l -> {
            panel.addKeyListener(new MyBindingKeyListener(num * 2 + 1, giu, DOWN));
            panel.grabFocus();
        });
    }
    /**
     * @param s **string text of this button**
     * @return **this button**
     */
    private JButton createButton(final String s) {
        return Utilities.initButton(s, Color.BLACK, Color.WHITE, GameValues.FONT_SMALL, null);
    }
    /**
     * @param s **string text of this label**
     * @param bordercolor **color border of this label**
     * @param fgColor **color text of this label**
     * @return **this label**
     */
    private JLabel createLabel(final String s, final Color bordercolor, final Color fgColor) {
        return Utilities.initLabel(s, SwingConstants.LEFT, GameValues.FONT_MEDIUM, bordercolor, fgColor);
    }
}
