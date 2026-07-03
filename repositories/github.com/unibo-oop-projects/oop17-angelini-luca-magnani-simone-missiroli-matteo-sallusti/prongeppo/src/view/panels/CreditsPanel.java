package view.panels;


import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import utility.GameValues;
import utility.Utilities;
/**
 * 
 * @author Paolo
 *
 */
public class CreditsPanel extends JPanel {
    /**
     * 
     */
    private static final long serialVersionUID = -193224537274262575L;
    /**
     * 
     * @param frame ** the main frame **
     */
    public CreditsPanel(final JFrame frame) {
        super(new GridLayout(4, 1, 10, 10));

        this.setBackground(Color.BLACK);
        this.add(this.createLabel("Angelini Luca: Gestione del menù principale e personalizzazione degli elementi", Color.WHITE, Color.RED));
        this.add(this.createLabel("Magnani Simone: Gestione AI e mondo di gioco", Color.BLUE, Color.GREEN));
        this.add(this.createLabel("Missiroli Matteo: Gestione pallina, power up e grafiche", Color.GREEN, Color.BLUE));
        this.add(this.createLabel("Sallusti Paolo: Gestione delle barre e Opzioni di gioco", Color.RED, Color.WHITE));

        this.setVisible(true);
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(final MouseEvent e) { }
            @Override
            public void mousePressed(final MouseEvent e) { }
            @Override
            public void mouseExited(final MouseEvent e) { }
            @Override
            public void mouseEntered(final MouseEvent e) { }
            @Override
            public void mouseClicked(final MouseEvent e) {
                Utilities.changePanel(frame, CreditsPanel.this, new SetOptions(frame));
            }
        });
    }
    /**
     * @param s **string text of this label**
     * @param bordercolor **color border of this label**
     * @param fgColor **color text of this label**
     * @return **this label**
     */
    private JLabel createLabel(final String s, final Color bordercolor, final Color fgColor) {
        return Utilities.initLabel(s, SwingConstants.CENTER, GameValues.FONT_SMALL, bordercolor, fgColor);
    }
}
