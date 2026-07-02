package it.unibo.game.app.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import java.util.stream.Collectors;

import it.unibo.game.Pair;
import it.unibo.game.app.api.AppController;
import it.unibo.game.app.api.Direction;
import it.unibo.game.app.api.Model;
import it.unibo.game.app.api.MovingObject;
import it.unibo.game.app.model.ModelImpl;
import it.unibo.game.app.model.SizeCalculation;
import it.unibo.game.app.model.dynamic.DirectionImpl;
import it.unibo.game.app.view.jswing.api.UIController;
import it.unibo.game.app.view.jswing.implementation.UIControllerImpl;

/**
 * Controller class that acts as an intermediary between the model and the view.
 */
public class ControllerImpl implements AppController {

  private static final int FONT_SIZE = 20;
  private UIController uiContr;
  private Model model;
  private GameEngine gameEngine;
  private static final int SCORE_X = 10;
  private static final int SCORE_LIFE_Y = 20;
  private static final int LIFE_X = 225;
  private static final int BONUS_X = 100;
  private static final int BONUS_Y = 150;

  /**
   * {@inheritDoc}
   */
  @Override
  public void play() {
    this.gameEngine.resume();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void onPause() {
    this.gameEngine.pause();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setView() {
    this.uiContr = new UIControllerImpl();
    this.uiContr.set(this);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setModel() {
    this.model = new ModelImpl();
    this.model.setController(this);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void chooseLevel(final int numLevel) {
    this.model.chooseLevel(numLevel);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Pair<Double, Double> getWorldDimension() {
    return SizeCalculation.getWorldSize();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Map<Pair<Double, Double>, Optional<Integer>> getBrickList() {
    return Collections
        .unmodifiableMap(
            this.model.getBrickList().entrySet().stream()
                .collect(
                    Collectors.toMap(
                        m -> new Pair<>(m.getKey().getX() * this.delta().getX(),
                            m.getKey().getY() * this.delta().getY()),
                        m -> m.getValue())));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Pair<Double, Double> getBrickDimension() {
    return new Pair<Double, Double>(
        this.model.getBrickDimension().getX() * this.delta().getY(),
        this.model.getBrickDimension().getY() * this.delta().getX());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Pair<Double, Double>> getBall() {
    List<Pair<Double, Double>> list = new ArrayList<>();
    this.model.getBall().stream().forEach(x -> {
      list.add(new Pair<Double, Double>(x.getX() * this.delta().getX(),
          x.getY() * this.delta().getY()));
    });
    return Collections.unmodifiableList(list);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Pair<Double, Double> getPad() {
    return new Pair<Double, Double>(this.model.getPad().getX() * this.delta().getX(),
        this.model.getPad().getY() * this.delta().getY());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Double getPadWight() {
    return this.model.getPadWight() * this.delta().getX();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Double getPadHeight() {
    return this.model.getPadHeight() * this.delta().getY();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void updatePoints(final String name, final String passWord) {
    this.model.updatePoints(name, passWord);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Double getRBall() {
    var dt = this.delta().getX() < this.delta().getY() ? this.delta().getX()
        : this.delta().getY();
    return this.model.getRBall() * dt;
  }

  /**
   * @param b list of ball.
   * @return list of position of ball update relative to frame size.
   */
  private List<Pair<Double, Double>> getPairList(final List<MovingObject> b) {
    return Collections.unmodifiableList(
        b.stream().map(ball -> new Pair<>(ball.getPos().getX() * this.delta().getX(),
            ball.getPos().getY() * delta().getY())).collect(Collectors.toList()));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Pair<Double, Double>> getSurprise() {
    return Collections.unmodifiableList(getPairList(this.model.getSurprise()));
  }

  /**
   * 
   * @return how much the frame size has changed compared to the model size.
   */
  private Pair<Double, Double> delta() {
    return new Pair<Double, Double>(
        uiContr.windowDim().getX() / this.getWorldDimension().getY(),
        uiContr.windowDim().getY() / this.getWorldDimension().getX());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void rPaint() {
    this.uiContr.rPaint();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void nextRound() {
    this.model.nextRound();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Double getRow(final Double x) {
    return this.model.getRow(x) * this.delta().getY();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setGameEngine() {
    this.gameEngine = new GameEngine(this);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getFontSize() {
    var dt = this.delta().getX() < this.delta().getY() ? this.delta().getX()
        : this.delta().getY();
    return FONT_SIZE * dt.intValue();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Pair<Double, Double>> getLabelPos() {
    return List.of(new Pair<>(SCORE_X * delta().getX(), SCORE_LIFE_Y * delta().getY()),
        new Pair<>(LIFE_X * delta().getX(), SCORE_LIFE_Y * delta().getY()),
        new Pair<>(BONUS_X * delta().getX(), BONUS_Y * delta().getY()));
  }

  /**
   * {@inheritDoc}
   */
  public void update() {
    this.model.update();
  }

  /**
   * {@inheritDoc}
   */
  public List<Pair<String, Integer>> getBestFive() {
    return Collections.unmodifiableList(this.model.getBestFive());
  }

  /**
   * {@inheritDoc}
   */
  public int getScore() {
    return this.model.getScore();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setGameOver() {
    this.uiContr.gameOver();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean updateLife() {
    return this.model.updateLife();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void restoreBall() {
    this.model.restoreInitialPosition();
  }

  /**
   * method to change pad position.
   * 
   * @param d new pad direction
   */
  private void movePad(final Direction d) {
    this.model.setPadPos(d);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void mvPadR() {
    var d = new DirectionImpl();
    d.setDirectionRight();
    movePad(d);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void mvPadL() {
    var d = new DirectionImpl();
    d.setDirectionLeft();
    movePad(d);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean checkRound() {
    return this.model.checkRound();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setVictory() {
    this.uiContr.victory();
    this.gameEngine.setWin();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getLife() {
    return this.model.getLife();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void restartWin() {
    this.gameEngine.resetWin();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getStringSur() {
    return model.getString();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void deleteString() {
    this.model.deleteString();
  }

}
