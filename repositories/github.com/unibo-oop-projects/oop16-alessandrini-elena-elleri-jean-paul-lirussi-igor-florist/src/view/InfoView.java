package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.net.URL;

import javax.swing.Box;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 *info view.
 */
public class InfoView extends JPanel implements View {

    private static final long serialVersionUID = -1849749696533486845L;
    private static final int DIMENSION = 100;
    /**
     * 
     */
    public InfoView() {

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(10, 1));

        //set icon
        final URL iconURL = getClass().getResource("/the_blooming_florist.png");
        ImageIcon imageIcon = new ImageIcon(new ImageIcon(iconURL).getImage().getScaledInstance(DIMENSION, DIMENSION, Image.SCALE_DEFAULT));
        JLabel icon = new JLabel();
        icon.setIcon(imageIcon);

        //set text
        JLabel title = new JLabel("THE BLOOMING FLORIST");
        title.setHorizontalAlignment(JLabel.CENTER);

        JLabel text = new JLabel("Created and develop by");
        text.setHorizontalAlignment(JLabel.CENTER);
        JLabel name = new JLabel("Alessandrini Elena");
        name.setHorizontalAlignment(JLabel.CENTER);
        JLabel name1 = new JLabel("Elleri Jean Paul");
        name1.setHorizontalAlignment(JLabel.CENTER);
        JLabel name2 = new JLabel("Lirussi Igor");
        name2.setHorizontalAlignment(JLabel.CENTER);


        panel.add(Box.createVerticalGlue());
        panel.add(title, BorderLayout.CENTER);
        panel.add(Box.createVerticalGlue());
        panel.add(text, BorderLayout.CENTER);
        panel.add(name, BorderLayout.CENTER);
        panel.add(name1, BorderLayout.CENTER);
        panel.add(name2, BorderLayout.CENTER);
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, panel.getMinimumSize().height));
        panel.setBackground(Colors.getBackgroundColor());

        //add to panel
        this.add(icon, BorderLayout.CENTER);
        this.add(panel, BorderLayout.CENTER);
        this.setBackground(Colors.getBackgroundColor());



    }

    @Override
    public void init() { }
}