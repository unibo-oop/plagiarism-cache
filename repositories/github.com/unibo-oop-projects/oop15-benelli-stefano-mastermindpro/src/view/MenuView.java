package view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import view.navigator.Navigable;
import view.navigator.Navigator;
import view.utils.FrameHelper;

/**
 * 
 * @author Stefano Benelli
 * This class represent the Menu Frame
 */
public class MenuView extends JFrame implements Navigable {

	private static final long serialVersionUID = 3742758020392137899L;

	public MenuView(final Navigator navigator) {

		FrameHelper.setupWindow(this, new Dimension(720, 450));

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Main Menu");

		JPanel pane = new JPanel(new GridLayout(3,1,10,10));
		pane.setBorder(new EmptyBorder(20, 20, 20, 20));
		this.setContentPane(pane);

        final JButton newGame = new JButton("PLAY");
        pane.add(newGame);
        newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent event) {
            	navigator.newGame();
            }
        });

        final JButton standings = new JButton("STANDINGS");
        pane.add(standings);
        standings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent event) {
            	navigator.standings();
            }
        });

        final JButton quit = new JButton("QUIT");
        pane.add(quit);
        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent event) {
            	navigator.quit();
            }
        });

        this.setResizable(false);
		this.setVisible(true);
		this.pack();
	}
}