package it.unibo.game.app.view.jswing.implementation;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import it.unibo.game.app.view.jswing.api.UIController;

import java.util.ArrayList;
import java.util.List;

import it.unibo.game.Pair;

/**
 * class that shows best five players.
 */
public class LeaderBoardView extends JPanel implements ActionListener {

  private static final String COLOR = "#293132";
  private List<Pair<String, Integer>> best = new ArrayList<>();
  private List<JLabel> tx = new ArrayList<>();
  private static final int MAX = 5;
  private static final int DIM = 25;
  private static final int GAP = 15;
  private static final int TITLE_SIZE = 35;
  private UIController control;

  /**
   * add components to JPanel.
   * 
   * @param control UIcontroller to get information about who are best five
   *                players
   */
  
  public LeaderBoardView(final UIController control) {
    this.control = control;
    this.best = control.getBestFive();
    this.setLayout(new GridLayout(MAX + 2, 1, GAP, GAP));
    this.setBorder(BorderFactory.createEmptyBorder(DIM * 2, DIM, DIM * 2, DIM));
    this.setBackground(Color.decode(COLOR));

    JLabel title = new JLabel("LEADERBOARD: ");
    title.setFont(new Font("myFont", Font.ITALIC, TITLE_SIZE));
    title.setBackground(Color.decode(COLOR));
    title.setForeground(Color.YELLOW);
    this.add(title);

    for (int i = 0; i < MAX; i++) {
      JLabel text = new JLabel();
      text.setFont(new Font("myFont", Font.ITALIC, DIM));
      text.setBackground(Color.decode(COLOR));
      text.setForeground(Color.WHITE);
      this.tx.add(text);
      this.add(text);
    }

    this.best.forEach(x -> {
      int indx = this.best.indexOf(x);
      this.tx.get(indx).setText(Integer.toString(indx + 1) + "\u00B0" + "     " + x.getX()
          + "    pt:" + Integer.toString(x.getY()));
    });

    JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    JButton jb = new JButton("MENU");
    jb.addActionListener(e -> control.initialView());
    jb.setBorderPainted(true);
    panel.add(jb);
    panel.setBackground(Color.decode(COLOR));
    this.add(panel);

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void paintComponent(final Graphics g) {
    super.paintComponent(g);
    this.best = this.control.getBestFive();
    this.best.forEach(x -> {
      int indx = this.best.indexOf(x);
      this.tx.get(indx).setText(Integer.toString(indx + 1) + "\u00B0" + "     " + x.getX()
          + "    pt:" + Integer.toString(x.getY()));
    });
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void actionPerformed(final ActionEvent e) {

    this.revalidate();
    this.repaint();
  }
}
