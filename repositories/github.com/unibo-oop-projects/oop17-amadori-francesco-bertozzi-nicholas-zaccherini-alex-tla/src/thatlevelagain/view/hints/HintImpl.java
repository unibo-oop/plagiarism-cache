package thatlevelagain.view.hints;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;

import thatlevelagain.view.panel.GamePanel;
import thatlevelagain.view.panel.JPanelColor;
import thatlevelagain.view.state.various_state.LevelStateGeneral;
import thatlevelagain.view.various_jbutton.CloseHint;
import thatlevelagain.view.various_jbutton.ViewZone;

/**
 * 
 * show hint for level 1.
 *
 */
public class HintImpl implements Hint {

    private final String name;
    private final String message;
    private static final int TRE = 3;
    private static final int CINQUE = 5;
    private static final int LVL_DOG = 7;

    /**
     * constructor.
     * 
     * @param name
     *         hint's name
     * @param message
     *         second message to draw to screen
     */
    public HintImpl(final String name, final String message) {
        this.name = name;
        this.message = message;
    }
    /**
     * show a dialog with level 1 hint.
     * @param gamePanel
     *         panel
     * @param level
     *          actual level
     */
    public void getHint(final GamePanel gamePanel, final LevelStateGeneral level) {
        final JDialog dialog = new JDialog();
        final JPanelColor panel1 = new JPanelColor(new FlowLayout());
        final JPanelColor panel2 = new JPanelColor(new FlowLayout());
        final JPanelColor panel3 = new JPanelColor(new FlowLayout());
        final JLabel label = new JLabel(this.name);
        label.setForeground(Color.WHITE);
        dialog.getContentPane().setLayout(new BoxLayout(dialog.getContentPane(), BoxLayout.Y_AXIS));
        label.setFont(new Font("Comic Sans", Font.BOLD, GamePanel.BLOCK_WIDTH / CINQUE * TRE));
        panel1.add(label);
        dialog.add(panel1);
        panel2.add(new CloseHint(dialog, level));
        dialog.add(panel2);
        if (level.getMap().getLevel() == LVL_DOG) {
            panel3.add(new ViewZone(level));
            dialog.add(panel3);
        }
        dialog.pack();
        dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }
    /**
     * @return
     *         message to draw in frame
     */
    public String getMessage() {
        return this.message;
    }
}
