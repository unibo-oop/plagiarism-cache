package model.player;

import model.ball.Ball;

import java.io.Serializable;
import java.util.List;

public class PlayerImpl implements Serializable, Player{

    private static final long serialVersionUID = 2L;
    private int bestScore;
    private String name;

    public PlayerImpl(String name){
        this.name = name;
    }

    public PlayerImpl(String name, int bestScore){
        this.name = name;
        this.bestScore = bestScore;
    }

    public String getName(){
        return this.name;
    }

    @Override
    public void setName(String name) {

    }

    @Override
    public void addMoney(int money) {

    }

    @Override
    public void useMoney(int money) {

    }

    @Override
    public void setNewBestScore(int bestScore) {

    }

    @Override
    public void setNewScore(int newScore) {

    }

    @Override
    public Ball getCurrentBall() {
        return null;
    }

    @Override
    public void setCurrentBall(Ball ball) {

    }

    @Override
    public List<Ball> getUnlockedBalls() {
        return null;
    }

    @Override
    public void addUnlockedBall(Ball ball) {

    }

    @Override
    public int getCurrentMoney() {
        return 0;
    }

    public int getBestScore(){
        return bestScore;
    }

}
