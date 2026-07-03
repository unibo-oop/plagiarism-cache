package controller;

import java.util.List;

import model.Ball.Combo;
import javafx.util.Pair;
import model.Ball;
import model.Bar;
import utility.GameValues;
import utility.Direction;

/**
 * @author Simone
 * An AI that can play, in different Difficult, this game.
 */
public final class AIImpl implements Input {

    /**
     * @author Simone
     *
     */
    public enum Difficult {
        /** 
         * an easy AI.
         */
        EASY, 
        /**
         * good difficult for single ball.
         */
        MEDIUM, 
        /**
         * multiball difficult.
         */
        HARD
    }

    private final Difficult actualDifficult;
    private final List<Ball> ballList;
    private final Bar bar;

    /**
     * @param difficult **the AI difficult**
     * @param ballList **the list of all Balls in game**
     * @param bar **the bar controlled by AI**
     */
    public AIImpl(final Difficult difficult, final List<Ball> ballList, final Bar bar) {
        this.actualDifficult = difficult;
        this.bar = bar;
        this.ballList = ballList;
    }

    @Override
    public void moving() { 
        switch (this.actualDifficult) {
        case EASY:
            this.easyMoving(this.bar.getPosition().getY(), this.ballList.get(0));
            break;
        case MEDIUM:
            this.mediumMoving(this.bar.getPosition().getY(), this.ballList.get(0));
            break;
        case HARD:
            this.hardMoving(this.bar.getPosition().getY());
            break;
        default:
            throw new IllegalStateException();
        }
    }

    private void hardMoving(final double barPosition) {
        final int indice = this.getIndexOfFirstBallComing();
        if (this.timeToBar(this.ballList.get(indice)) == Double.MAX_VALUE) {
            this.compareAndChoose(barPosition, GameValues.WORLDHEIGHT / 2);
        } else {
            if (this.isMediumMovingBetter(indice)) {
                this.mediumMoving(barPosition, this.ballList.get(indice));
            } else {
                this.easyMoving(barPosition, this.ballList.get(indice));
            }
        }
    }

    private boolean isMediumMovingBetter(final int indice) { //Strategy
        return !this.ballList.get(indice).getCombo().equals(Combo.ZIGZAG);
    }

    private int getIndexOfFirstBallComing() {
        return this.ballList.stream().map(ball -> {
            return new Pair<Double, Integer>(this.timeToBar(ball), this.ballList.indexOf(ball)); }).sorted((map1, map2) -> {
                return map1.getKey() > map2.getKey() ? 1 : -1; // sort to have 1 ball coming in 1 position
            }).findFirst().get().getValue(); //get and return the value
    }

    private double timeToBar(final Ball ball) {
        final double time = (this.bar.getPosition().getX() - ball.getPosition().getX()) 
                                            / ball.getSpeed().getX();
        return  time < 0 ? Double.MAX_VALUE : time;
    }

    private void mediumMoving(final double barPosition, final Ball ball) {
        final double timeToBar = this.timeToBar(ball);
        final double totalHigh = ball.getPosition().getY() + ball.getSpeed().getY() * timeToBar;
        final int bounceToWall = Math.abs((int) (totalHigh / GameValues.WORLDHEIGHT));
        this.compareAndChoose(barPosition, (bounceToWall % 2 == 0 ? Math.abs(totalHigh % GameValues.WORLDHEIGHT) 
                                         : GameValues.WORLDHEIGHT - Math.abs(totalHigh % GameValues.WORLDHEIGHT)));
    }

    private void easyMoving(final double barPosition, final Ball ball) {
        this.compareAndChoose(barPosition, ball.getPosition().getY());
    }

    private void compareAndChoose(final double positionBar, final double positionBall) {
        final double centerOfBar = positionBar + GameValues.BARY / 2;
        final double centerOfBall = positionBall + GameValues.BALL_DIMENSION / 2;
        if (centerOfBar < centerOfBall && centerOfBar + this.bar.getSpeed() < centerOfBall) {
            this.bar.move(Direction.DOWN);
        } else if (centerOfBar > centerOfBall && centerOfBar + this.bar.getSpeed() > centerOfBall) { 
            this.bar.move(Direction.UP);
        } else {
            this.bar.move(Direction.STOP);
        }
    }

}
