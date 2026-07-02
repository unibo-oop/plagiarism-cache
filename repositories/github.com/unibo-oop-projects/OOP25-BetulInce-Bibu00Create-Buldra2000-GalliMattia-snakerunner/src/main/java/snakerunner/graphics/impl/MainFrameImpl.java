package snakerunner.graphics.impl;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import snakerunner.graphics.MainFrame;
import snakerunner.graphics.panel.BasePanel;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * MainFrameImpl is the concrete class of the MainFrame'Interface.
 */
public final class MainFrameImpl extends JFrame implements MainFrame {

    private static final long serialVersionUID = 1L;
    private static final String TITLE = "Snake Runner";
    private static final String WON_MESSAGE = "You Won!";
    private static final String LOSE_MESSAGE = "You Lose!";
    private static final String LEVEL_COMPLETE = "Level Complete! Get ready for the next level!";
    private static final String SNAKE = "/images/snake.png";
    private static final double PROPORTION = 0.5;
    private BasePanel menuPanel;
    private BasePanel gamePanel;
    private BasePanel optionPanel;
    private BasePanel tutorialPanel;

    /**
     * Constructor of MainFrameImpl.
     */
    public MainFrameImpl() {
        super(TITLE);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setDimensionFrame();
        setResizable(false);
    }

    @Override
    public void display() {
        setVisible(true);
    }

    @SuppressFBWarnings
    @Override
    public void setPanels(final BasePanel menu, final BasePanel game, final BasePanel option, final BasePanel tutorial) {
       this.menuPanel = menu;
       this.tutorialPanel = tutorial;
       this.gamePanel = game;
       this.optionPanel = option;
    }

    /**
     * Set automatically proportion of dimension MainFrame.
     */
    private void setDimensionFrame() {
        final Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
        final int width = (int) (screensize.width * PROPORTION);
        final int height = (int) (screensize.height * PROPORTION);
        setSize(width, height);
    }

    @Override
    public void showMenu() {
        setContentPane((JPanel) menuPanel);
        revalidate();
        repaint();
    }

    @Override
    public void showTutorial() {
        setContentPane((JPanel) tutorialPanel);
        revalidate();
        repaint();
    }

    @Override
    public void showGame() {
        setContentPane((JPanel) gamePanel);
        revalidate();
        repaint();
    }

    @Override
    public void showOption() {
        setContentPane((JPanel) optionPanel);
        revalidate();
        repaint();
    }

    @Override
    public void won() {
        final var url = getClass().getResource(SNAKE);
        JOptionPane.showMessageDialog(this, WON_MESSAGE, TITLE, JOptionPane.INFORMATION_MESSAGE, new ImageIcon(url));
        showMenu();
    }

    @Override
    public void lose() {
        final var url = getClass().getResource(SNAKE);
        JOptionPane.showMessageDialog(this, LOSE_MESSAGE, TITLE, JOptionPane.INFORMATION_MESSAGE, new ImageIcon(url));
        showMenu();
    }

    @Override
    public void levelComplete() {
        final var url = getClass().getResource(SNAKE);
        JOptionPane.showMessageDialog(this, LEVEL_COMPLETE, TITLE, JOptionPane.INFORMATION_MESSAGE, new ImageIcon(url));
    }

    @Override
    public void refresh() {
        this.repaint();
    }

}

