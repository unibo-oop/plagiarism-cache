package it.unibo.game.app.view.jswing.implementation;

import it.unibo.game.app.view.jswing.api.UIController;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * Implements the panel for StartMenu view.
 */
public class StartMenu extends JPanel {
  private transient UIController uiControllerImpl;
  private static final int EASY_LEVEL = 1;
  private static final int MEDIUM_LEVEL = 2;
  private static final int ROWS = 2;
  private static final int COLUMS = 1;
  private static final int HARD_LEVEL = 3;
  private static final int ROWS_BUTTONS = 5;
  private static final int COLUMNS_BUTTONS = 1;
  private static final int HGAP = 10;
  private static final int VGAP = 10;
  private static final int TOP_BORDER = 50;
  private static final int BOTTOM_BORDER = 50;
  private static final int LEFT_BORDER = 25;
  private static final int RIGHT_BORDER = 25;
  private static final int FONT_SIZE = 60;
  private static final int BTN_SIZE = 25;

  /**
   * Constructor of the class.
   * 
   * @param ui is the controller that will change the views
   */
  
  public StartMenu(final UIControllerImpl ui) {
    this.uiControllerImpl = ui;
    JButton easy = new CustomBtn(BTN_SIZE, "EASY");
    JButton medium = new CustomBtn(BTN_SIZE, "MEDIUM");
    JButton hard = new CustomBtn(BTN_SIZE, "HARD");
    JButton top5 = new CustomBtn(BTN_SIZE, "LEADERBOARD");
    JButton commands = new CustomBtn(BTN_SIZE, "GAME COMMANDS");
    JLabel title = new JLabel("ARKANOID");
    JPanel buttonContainer = new JPanel();

    buttonContainer.setLayout(new GridLayout(ROWS_BUTTONS, COLUMNS_BUTTONS, HGAP, VGAP));

    this.setBackground(Color.BLACK);
    buttonContainer.setBackground(Color.BLACK);

    Font f = new Font("Serif", Font.BOLD, FONT_SIZE);
    title.setFont(f);
    title.setForeground(Color.WHITE);
    title.setHorizontalAlignment(SwingConstants.CENTER);

    this.setLayout(new GridLayout(ROWS, COLUMS, HGAP, VGAP));
    this.setBorder(BorderFactory.createEmptyBorder(TOP_BORDER, LEFT_BORDER, BOTTOM_BORDER,
        RIGHT_BORDER));

    this.add(buttonContainer, 0, 0);
    this.add(title, 2, 0);
    buttonContainer.add(easy);
    buttonContainer.add(medium);
    buttonContainer.add(hard);
    buttonContainer.add(top5);
    buttonContainer.add(commands);

    top5.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(final java.awt.event.ActionEvent e) {
        uiControllerImpl.leaderBoardView();
      }

    });

    commands.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(final java.awt.event.ActionEvent e) {
        uiControllerImpl.gameCommands();
      }

    });
    easy.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(final java.awt.event.ActionEvent e) {
        uiControllerImpl.level(EASY_LEVEL);
        uiControllerImpl.gameView();
      }

    });

    medium.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(final java.awt.event.ActionEvent e) {
        uiControllerImpl.level(MEDIUM_LEVEL);
        uiControllerImpl.gameView();
      }

    });

    hard.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(final java.awt.event.ActionEvent e) {
        uiControllerImpl.level(HARD_LEVEL);
        uiControllerImpl.gameView();
      }

    });

  }

}
