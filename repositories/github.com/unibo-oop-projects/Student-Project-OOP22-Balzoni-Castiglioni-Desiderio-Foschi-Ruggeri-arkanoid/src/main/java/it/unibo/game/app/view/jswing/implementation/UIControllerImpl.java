package it.unibo.game.app.view.jswing.implementation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.game.Pair;
import it.unibo.game.app.api.AppController;
import it.unibo.game.app.view.jswing.api.UIController;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

/**
 * class that interacts between the Controller and the GameView.
 */
public class UIControllerImpl implements UIController {

  private static final int LONGER_SIDE = 3;
  private static final int SMALLER_SIDE = 2;
  private JFrame window;
  private AppController appController;
  private final CardLayout layout = new CardLayout();
  private JPanel deck;
  private final Map<PAGES, JPanel> views = new HashMap<>();

  /**
   * {@inheritDoc}
   */

  
  public void set(final AppController control) {
    this.appController = control;

    var screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    /* make dimension like old 4/3 monitor */
    Dimension minDim = screenSize.width > screenSize.height
        ? new Dimension(screenSize.height / SMALLER_SIDE, screenSize.width / LONGER_SIDE)
        : new Dimension(screenSize.height / LONGER_SIDE, screenSize.width / SMALLER_SIDE);
    /* run jframe on EDT */
    SwingUtilities.invokeLater(() -> {
      window = new JFrame("Arkanoid");
      this.window.setMinimumSize(minDim);
      this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.window.setVisible(true);
      this.window.requestFocusInWindow();
    });

    this.deck = new JPanel(layout);
    views.putAll(Map.of(PAGES.GAME, new GameViewImpl(this), PAGES.START_MENU,
        new StartMenu(this), PAGES.PAUSE_MENU, new PauseMenu(this), PAGES.TOP_5,
        new LeaderBoardView(this), PAGES.VICTORY, new Victory(this), PAGES.GAME_OVER,
        new GameOver(this), PAGES.GAME_COMMANDS, new CommandsView(this)));

    views.entrySet().stream().forEach(x -> deck.add(x.getValue(), x.getKey().getName()));
    window.add(deck, BorderLayout.CENTER);
    initialView();
  }

  /**
   * method that changes the view.
   * 
   * @param p pages
   */

  private void chargeView(final PAGES p) {
    this.layout.show(deck, p.getName());
    this.window.setTitle(p.getName());
    this.views.get(p).requestFocusInWindow();
    if (p.equals(PAGES.GAME)) {
      this.appController.play();
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void initialView() {
    chargeView(PAGES.START_MENU);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void pauseMenu() {
    chargeView(PAGES.PAUSE_MENU);
    appController.onPause();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void gameView() {
    chargeView(PAGES.GAME);

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void leaderBoardView() {
    chargeView(PAGES.TOP_5);
  }

  /**
   * {@inheritDoc}
   */
  public Map<Pair<Double, Double>, Optional<Integer>> getList() {
    return appController.getBrickList();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void level(final int numLevel) {
    appController.chooseLevel(numLevel);
  }

  /**
   * {@inheritDoc}
   */
  public Pair<Double, Double> getDimension() {
    return appController.getWorldDimension();
  }

  /**
   * {@inheritDoc}
   */
  public Pair<Double, Double> getDimensionBrick() {
    return appController.getBrickDimension();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Pair<Double, Double>> getBall() {
    return appController.getBall();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Pair<Double, Double> getPadPos() {
    return appController.getPad();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Double getPadWight() {
    return appController.getPadWight();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Double getPadHeight() {
    return appController.getPadHeight();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Double getRBall() {
    return appController.getRBall();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void rPaint() {
    Toolkit.getDefaultToolkit().sync();
    this.window.repaint();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void gameOver() {
    chargeView(PAGES.GAME_OVER);
    appController.onPause();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void gameCommands() {
    chargeView(PAGES.GAME_COMMANDS);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void victory() {
    chargeView(PAGES.VICTORY);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Double getRowC(final Double x) {
    return this.appController.getRow(x);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Pair<String, Integer>> getBestFive() {
    return this.appController.getBestFive();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void movePadRight() {
    appController.mvPadR();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void movePadLeft() {
    appController.mvPadL();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Pair<Double, Double> windowDim() {
    return new Pair<Double, Double>(Integer.valueOf(this.deck.getWidth()).doubleValue(),
        Integer.valueOf(this.deck.getHeight()).doubleValue());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Pair<Double, Double>> getSurprise() {
    return this.appController.getSurprise();
  }

  // @Override
  // public List<Pair<Double, Double>> getExtraBalls(){
  // return this.AppController.getNewBalls();
  // }
  /**
   * {@inheritDoc}
   */
  @Override
  public int getScore() {
    return this.appController.getScore();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getLife() {
    return this.appController.getLife();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void updatePoints(final String name, final String passWord) {
    this.appController.updatePoints(name, passWord);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Pair<Double, Double>> getLabelPos() {
    return this.appController.getLabelPos();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getSizeFont() {
    return this.appController.getFontSize();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getStringSur() {
    return this.appController.getStringSur();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void deleteString() {
    this.appController.deleteString();
  }

}
