package view;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Il Panel che contiene i button dei colori
 * @author redim
 *
 */
@SuppressWarnings("serial")
public class ColorPanel extends JPanel{

    ArrayList<Color> colors;


    public ColorPanel() {
       colors =  new ArrayList<Color>();
       colors.add(Color.BLACK);
       colors.add(Color.BLUE);
       colors.add(Color.CYAN);
       colors.add(Color.DARK_GRAY);
       colors.add(Color.GRAY);
       colors.add(Color.GREEN);
       colors.add(Color.LIGHT_GRAY);
       colors.add(Color.MAGENTA);
       colors.add(Color.ORANGE);
       colors.add(Color.PINK);
       colors.add(Color.RED);
       colors.add(Color.WHITE);
       colors.add(Color.YELLOW);
       
       this.setLayout(new GridLayout(1, 0, 0, 0));

        for(Color color : colors) {
            this.add(buttonPanel(color));
        }
    } 

        private JButton buttonPanel(Color color) {
                  JButton bt = new JButton();
                  bt.setBackground(color);
                  bt.setContentAreaFilled(false);
                  bt.setOpaque(true);
                  return bt;
        }
}