package it.unibo.game.app.view.jswing.implementation;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.game.Pair;
import it.unibo.game.app.view.jswing.api.UIController;

import java.util.Timer;
import java.util.TimerTask;
import java.util.Map.Entry;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.Optional;

/**
 * takes care of showing the objects of the game and their position during the
 * game.
 */
public final class GameViewImpl extends JPanel implements KeyListener, ActionListener {

  private UIController observer;
  private boolean see = true;
  private static final int TIME = 2000;
  private static final double ROW = 5d;

  /**
   * constructor of this class.
   * 
   * @param control
   */
  
  public GameViewImpl(final UIController control) {
    setFocusable(true);
    addKeyListener(this);
    setFocusTraversalKeysEnabled(false);
    this.observer = control;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void paintComponent(final Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    // Determina la dimensione del pannello
    int panelWidth = getWidth();
    int panelHeight = getHeight();

    g.setColor(Color.WHITE);
    g.fillRect(0, 0, panelWidth, panelHeight);

    observer.getList().entrySet().stream().forEach(x -> {
      this.drawBrick(x.getKey(), observer.getDimensionBrick().getX(),
          observer.getDimensionBrick().getY(), peekColor(x), g2d);
    });

    observer.getBall().stream().forEach(x -> {
      this.drawOval(x, observer.getRBall(), Color.GREEN, g2d);
    });

    observer.getSurprise().stream().forEach(x -> {
      this.drawOval(x, observer.getRBall(), Color.RED, g2d);
    });

    this.drawRectangle(observer.getPadPos(), observer.getPadHeight(),
        observer.getPadWight(), Color.BLACK, g2d);

    this.drawLabel("SCORE: " + this.observer.getScore(), observer.getLabelPos().get(0),
        g2d);

    this.drawLabel("LIVES: " + this.observer.getLife(), observer.getLabelPos().get(1),
        g2d);

    if (!observer.getStringSur().isEmpty()) {
      if (see) {
        Timer timer = new Timer();
        see = false;
        TimerTask task = new TimerTask() {

          @Override
          public void run() {
            observer.deleteString();
            see = true;
          }
        };
        timer.schedule(task, TIME);

      }
      this.drawLabel(observer.getStringSur(), observer.getLabelPos().get(2), g2d);
    }

    g2d.dispose();

  }

  private Color peekColor(final Entry<Pair<Double, Double>, Optional<Integer>> x) {
    return x.getValue().isEmpty() ? Color.BLACK
        : x.getValue().get() == 2 ? Color.LIGHT_GRAY
            : x.getKey().getY().intValue() == observer.getRowC(0d).intValue() ? Color.RED
                : x.getKey().getY().intValue() == observer.getRowC(1d).intValue()
                    ? Color.BLUE
                    : x.getKey().getY().intValue() == observer.getRowC(2d).intValue()
                        ? Color.YELLOW
                        : x.getKey().getY().intValue() == observer.getRowC(3d).intValue()
                            ? Color.MAGENTA
                            : x.getKey().getY().intValue() == observer.getRowC(4d)
                                .intValue()
                                    ? Color.ORANGE
                                    : x.getKey().getY().intValue() == observer
                                        .getRowC(ROW).intValue() ? Color.CYAN
                                            : Color.GREEN;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void actionPerformed(final ActionEvent arg0) {
    repaint();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void keyPressed(final KeyEvent arg0) {
    if (arg0.getKeyCode() == KeyEvent.VK_RIGHT) {
      observer.movePadRight();
    }
    if (arg0.getKeyCode() == KeyEvent.VK_LEFT) {
      observer.movePadLeft();
    } else if (arg0.getKeyCode() == KeyEvent.VK_SPACE) {
      observer.pauseMenu();
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void keyReleased(final KeyEvent arg0) {
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void keyTyped(final KeyEvent arg0) {
  }

  /**
   * method to draw balls.
   * 
   * @param pos    ball position
   * @param radius ball radius
   * @param c      color of the ball
   * @param g      object Graphics2D
   */
  private void drawOval(final Pair<Double, Double> pos, final Double radius,
      final Color c, final Graphics2D g) {
    g.setColor(c);
    g.fill(new Ellipse2D.Double(pos.getX(), pos.getY(), radius, radius));
  }

  /**
   * method to draw rectangles.
   * 
   * @param pos    object's position
   * @param height object's height
   * @param width  object's width
   * @param c      color of the object
   * @param g      object Graphics2D
   */
  private void drawRectangle(final Pair<Double, Double> pos, final double height,
      final double width, final Color c, final Graphics2D g) {
    g.setColor(c);
    g.fill(new Rectangle2D.Double(pos.getX(), pos.getY(), width, height));
  }

  /**
   * method to draw bricks.
   * 
   * @param pos    brick's position
   * @param height brick's height
   * @param width  brick's width
   * @param c      color of the brick
   * @param g      object Graphics2D
   */
  private void drawBrick(final Pair<Double, Double> pos, final double height,
      final double width, final Color c, final Graphics2D g) {
    this.drawRectangle(pos, height, width, c, g);
    g.setColor(Color.BLACK);
    g.draw(new Rectangle2D.Double(pos.getX(), pos.getY(), width, height));
  }

  /**
   * method to draw labels that are useful to the player.
   * 
   * @param str message
   * @param pos position where to collocate the label
   * @param g   object Graphics2D
   */
  private void drawLabel(final String str, final Pair<Double, Double> pos,
      final Graphics2D g) {
    g.setFont(new Font("myFont", Font.ITALIC, observer.getSizeFont()));
    g.setColor(Color.RED);
    g.drawString(str, pos.getX().intValue(), pos.getY().intValue());
  }

}
