package model;

import java.awt.Point;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import controller.GraphicManager;
import controller.Input;
import javafx.util.Pair;
import model.Ball.Combo;
import utility.GameValues;
import utility.Utilities;
import view.panels.SetOptions;

/**
 * @author Simone
 * the Implementation of World.
 */
public class WorldImpl implements World {

    private final List<Ball> ballList;
    private List<PickUp> pickUpList;
    private final List<Input> inputList;
    private Pair<Integer, Integer> score;
    private final List<Bar> team1;
    private final List<Bar> team2;
    private boolean reset;
    private final Clip goalClip;
    private final List<BallManager> ballManagerList;
    private final Pair<Combo, Combo> comboes;
    private final GraphicManager gmanager;



    /**
     * @param ballList ** the list of all ball in game **
     * @param comboes ** the Pair<comboTeam1, comboTeam2> **
     * @param gmanager ** the graphicManager used to interact with the view **
     * @param inputList ** the list of Input**
     * @param team2 **team 2 !with X position ready!**
     * @param team1 **team 1 !with X position ready!**
     */
    public WorldImpl(final List<Ball> ballList, final Pair<Combo, Combo> comboes, final GraphicManager gmanager,
                     final List<Bar> team1, final List<Bar> team2, final List<Input> inputList) {
        this.goalClip = Utilities.createClip();
        this.pickUpList = new LinkedList<>();
        this.gmanager = gmanager;
        this.team1 = team1;
        this.team2 = team2;
        this.ballList = ballList;
        this.inputList = inputList;
        this.ballManagerList = this.ballList.stream().map(ball -> new BallManagerImpl(ball)).collect(Collectors.toList());
        this.comboes = comboes;
        this.score = new Pair<>(0, 0);
        this.resetState();
    }

    /**
     * when called it update the state of world moving each element than check collision.
     */
    @Override
    public void updateState() {
        if (SetOptions.isAudio() && !this.goalClip.isRunning()) {
            this.goalClip.close();
        }

        if (Math.random() < GameValues.PICK_UP_PROBABILITY && this.pickUpList.size() < GameValues.PU_MAX_NUM) {
            this.pickUpList.add(Utilities.createPickUp(this.gmanager));
        }
        this.reset = false;
        this.inputList.forEach(input -> input.moving());
        this.useBallManager();
        this.ballList.forEach(ball -> ball.move());
        if (this.ballList.stream().anyMatch(ball -> (ball.getPosition().x + GameValues.BALL_DIMENSION / 2) < 0)) {
            this.score = new Pair<>(this.score.getKey(), this.score.getValue() + 1);
            this.resetState();
        } else if (this.ballList.stream().anyMatch(ball -> (ball.getPosition().x + GameValues.BALL_DIMENSION / 2) > GameValues.WORLDWIDTH)) {
            this.score = new Pair<>(this.score.getKey() + 1, this.score.getValue());
            this.resetState();
        }
    }

    /**
     * @return sum of comboes done in a Pair(team1, team2)
     */
    @Override
    public Pair<Integer, Integer> getComboSum() {
        return new Pair<>(this.team1.size() == 1 ? 0 : this.ballManagerList.stream().mapToInt(ballMan -> ballMan.getComboCount().getKey()).sum(), 
                          this.team2.size() == 1 ? 0 : this.ballManagerList.stream().mapToInt(ballMan -> ballMan.getComboCount().getValue()).sum());
    }

    /**
     * @return if the game is finished
     */
    @Override
    public boolean isItGameOver() {
        return this.score.getKey() >= GameValues.MAX_SCORE || this.score.getValue() >= GameValues.MAX_SCORE;
    }

    /**
     * @return the pair score taken from a map
     */
    public Pair<Integer, Integer> getPairScore() {
        return this.score;
    }

    /**
     * @return the boolean reset
     */
    @Override
    public boolean isWorldReset() {
        return this.reset;
    }

    private void setBallSpeed() {
        IntStream.range(0, this.ballList.size()).forEach(i -> 
        this.ballList.get(i).setSpeed(this.randomSpeed()));
    }

    private Point randomSpeed() {
        if (Math.random() < 1 / 4d) {
            return new Point(this.intOfSpeed(), this.intOfSpeed());
        } else if (Math.random() < 2 / 4d) {
            return new Point(-this.intOfSpeed(), -this.intOfSpeed());
        } else if (Math.random() < 3 / 4d) {
            return new Point(this.intOfSpeed(), -this.intOfSpeed());
        } else {
            return new Point(-this.intOfSpeed(), this.intOfSpeed());
        }
    }

    private int intOfSpeed() {
       return (int) (Math.random() * GameValues.BALL_SPEED) + 1;
    }

    private void resetState() {
        this.ballManagerList.forEach(ballMan -> ballMan.reset());
        this.setBar(this.team1);
        this.setBar(this.team2);
        this.setBallPos();
        this.setBallSpeed();
        this.reset = true;
        this.gmanager.removePongElement(this.pickUpList.stream().map(pickup -> pickup.getGraphic()).collect(Collectors.toList()));
        this.pickUpList = new LinkedList<>();
        if (SetOptions.isAudio() && !this.getPairScore().equals(new Pair<>(0, 0))) {
            if (this.goalClip.isOpen()) {
                this.goalClip.close();
            }
            try {
                final AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(this.getClass().getResource("/APPLAUSI.wav"));
                this.goalClip.open(audioInputStream);
                this.goalClip.start();
            } catch (UnsupportedAudioFileException | IOException  | LineUnavailableException e) {
                e.printStackTrace();
            }
        }
    }

    private void setBallPos() {
        this.ballList.forEach(ball -> ball.setPosition(
                       new Point(GameValues.WORLDWIDTH / 2, GameValues.WORLDHEIGHT / 2)));
    }

    private void setBar(final List<Bar> team) {
        if (team.size() == 1) {
            team.get(0).setPosition(new Point(team.get(0).getPosition().x, 
                                              GameValues.WORLDHEIGHT / 2));
        } else if (team.size() == 2) {
            team.get(0).setPosition(new Point(team.get(0).getPosition().x, 
                                              GameValues.WORLDHEIGHT / 4));
            team.get(1).setPosition(new Point(team.get(0).getPosition().x, 
                                              3 * GameValues.WORLDHEIGHT / 4));
        }
    }

    private void useBallManager() {
        this.ballManagerList.forEach(ballManager -> {
            ballManager.collisionCheck(this.team1, this.comboes.getKey());
            ballManager.collisionCheck(this.team2, this.comboes.getValue());
            ballManager.comboHandler();
            final List<PickUp> temp = ballManager.collectedPickups(pickUpList);
            if (temp.size() != 0) {
                this.pickUpList.removeAll(temp);
                this.gmanager.removePongElement(temp.stream().map(pickUp -> pickUp.getGraphic()).collect(Collectors.toList()));
            }
        });
    }

}
