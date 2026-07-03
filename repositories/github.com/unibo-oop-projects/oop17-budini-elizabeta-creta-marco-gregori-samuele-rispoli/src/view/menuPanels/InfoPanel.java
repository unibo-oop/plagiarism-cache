package view.menuPanels;

import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import utilities.ImageLoader;
import view.BackgroundPanel;

/**
 * This is the Info Panel which displays the application informations ant
 * 
 */
public class InfoPanel extends JPanel {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public InfoPanel() {

        final ImageIcon background = ImageLoader.getInstance().getImage("images/background2.jpg");
        final ImageIcon credits = ImageLoader.getInstance().getImage("images/credits.png");
        final ImageIcon elizabeta = ImageLoader.getInstance().getImage("images/elizabeta_budini.png");
        final ImageIcon luca = ImageLoader.getInstance().getImage("images/luca_rispoli.png");
        final ImageIcon marco = ImageLoader.getInstance().getImage("images/marco_creta.png");
        final ImageIcon samuele = ImageLoader.getInstance().getImage("images/samuele_gregori.png");
        final ImageIcon image = ImageLoader.getInstance().getImage("icons/donkey2.png");

        final JLabel labelcred = new JLabel();
        final JLabel imagelab = new JLabel();
        imagelab.setIcon(image);
        imagelab.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelcred.setIcon(credits);
        labelcred.setHorizontalAlignment(SwingConstants.CENTER);
        final JLabel lab1 = new JLabel();
        lab1.setIcon(luca);
        lab1.setAlignmentX(Component.CENTER_ALIGNMENT);
        final JLabel lab2 = new JLabel();
        lab2.setIcon(samuele);
        lab2.setAlignmentX(Component.CENTER_ALIGNMENT);
        final JLabel lab3 = new JLabel();
        lab3.setIcon(marco);
        lab3.setAlignmentX(Component.CENTER_ALIGNMENT);
        final JLabel lab4 = new JLabel();
        lab4.setIcon(elizabeta);
        lab4.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JPanel panelName = new JPanel();
        panelName.setLayout(new BoxLayout(panelName, BoxLayout.Y_AXIS));
        panelName.add(imagelab);
        panelName.add(lab1);
        panelName.add(lab2);
        panelName.add(lab3);
        panelName.add(lab4);

        BackgroundPanel backgroundPanel = new BackgroundPanel(background.getImage(), BackgroundPanel.SCALED, 0.0f,
                0.0f);

        backgroundPanel.setLayout(new BorderLayout());

        backgroundPanel.add(panelName, BorderLayout.CENTER);
        backgroundPanel.add(labelcred, BorderLayout.NORTH);

        this.setLayout(new BorderLayout());
        this.add(backgroundPanel);

    }

}
