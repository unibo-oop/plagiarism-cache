package dungeon.gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public final class WinLossPanel extends JPanel implements GuiElement {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 3436984683168923864L;
  private static final int FONT_SIZE = 24;

  @Override
  public void drawPanel(final JFrame frame, final String text) {
    this.removeAll();
    this.setLayout(new GridLayout(3, 1));
    JPanel panel1 = new JPanel();
    JPanel panel2 = new JPanel();
    panel1.setLayout(new BorderLayout());
    JButton playAgain = new JButton("Try Again");
    playAgain.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(final ActionEvent e) {
        frame.getContentPane().removeAll();
        try {
          new InsertNamePanel(frame);
        } catch (IOException e1) {
          e1.printStackTrace();
        }
      }
    });
    panel1.add(playAgain);
    panel2.setLayout(new BorderLayout());
    JButton exitButton = new JButton("Exit");
    exitButton.addActionListener(e -> {
      frame.dispose();
    });
    panel2.add(exitButton);
    JLabel label = new JLabel();
    label.setHorizontalTextPosition(SwingUtilities.CENTER);
    label.setVerticalTextPosition(SwingUtilities.CENTER);
    label.setFont(new Font("Serif", Font.PLAIN, FONT_SIZE));
    label.setText(text);
    this.add(label);
    this.add(panel1);
    this.add(panel2);
    this.revalidate();
    this.repaint();
  }

}
