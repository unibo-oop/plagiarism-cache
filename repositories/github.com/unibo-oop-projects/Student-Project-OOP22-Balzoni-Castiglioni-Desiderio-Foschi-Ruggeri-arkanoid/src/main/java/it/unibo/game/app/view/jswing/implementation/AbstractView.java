package it.unibo.game.app.view.jswing.implementation;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.game.app.view.jswing.api.UIController;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Implements a common JPanel for Pause, GameOver and Victory.
 */
public abstract class AbstractView extends JPanel {
  private UIController observer;
  private JLabel titleLabel;
  private JPanel buttonsPanel;
  private JButton quitBtn;
  private JButton menuBtn;
  private JButton button;
  /**
   * Use SIZE_BTN for new CustomBtn size in the subclasses.
   */
  protected static final int SIZE_BTN = 30;
  private static final int SIZE_TITLE = 60;

  /**
   * Constructor of the class.
   * 
   * @param uiCtrl         is the controller that will change the views
   * @param title          the text that will appear in the panel
   * @param button         the button that will appear in the panel
   * @param actionListener the action that will implement this button
   */
  
  public AbstractView(final UIControllerImpl uiCtrl, final String title,
      final JButton button, final ActionListener actionListener) {
    this.observer = uiCtrl;
    this.titleLabel = new JLabel();
    this.titleLabel.setText(title);
    this.button = button;
    this.button.addActionListener(actionListener);
    this.menuBtn = new CustomBtn(SIZE_BTN, "Start Menu");
    this.quitBtn = new CustomBtn(SIZE_BTN, "Quit");

    JPanel titlePanel = new JPanel();
    buttonsPanel = new JPanel();

    this.setLayout(new BorderLayout());
    buttonsPanel.setLayout(new GridLayout(3, 1, 0, 1));
    buttonsPanel.add(this.button);
    buttonsPanel.add(this.menuBtn);
    buttonsPanel.add(this.quitBtn);
    titleLabel.setFont(new Font("Serif", Font.BOLD, SIZE_TITLE));
    titleLabel.setForeground(Color.WHITE);

    titlePanel.add(titleLabel);

    titlePanel.setBackground(Color.BLACK);
    buttonsPanel.setBackground(Color.BLACK);
    this.setBackground(Color.BLACK);

    this.add(titlePanel, BorderLayout.NORTH);
    this.add(buttonsPanel, BorderLayout.SOUTH);

    this.menuBtn.addActionListener(new ActionListener() {

      /**
       * {@inheritDoc}
       */
      @Override
      public void actionPerformed(final ActionEvent e) {
        observer.initialView();
      }

    });
    this.quitBtn.addActionListener(new ActionListener() {

      /**
       * {@inheritDoc}
       */
      @SuppressFBWarnings("DM_EXIT")
      @Override
      public void actionPerformed(final ActionEvent e) {
        System.exit(0);
      }

    });
  }
}
