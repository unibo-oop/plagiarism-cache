
package view.gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import controller.Controller;
import view.gui.SimulationPanel;

/**
 * 
 * 
 * Stop button class to close the simulation and open the final statistics.
 * 
 *
 */
public class ActionPanel extends JPanel implements ActionListener {

        private static final long serialVersionUID = 5233557063053665457L;
        private final JButton close = new JButton("STOP");

        public ActionPanel(final Controller view, final SimulationPanel main) {
            super();
            close.addActionListener(new ActionListener() {
                public void actionPerformed(final ActionEvent e) {
                    main.close();
                }

            });
        }

        @Override
        public void actionPerformed(final ActionEvent e) {
            // TODO Auto-generated method stub

        }
}
	
	

