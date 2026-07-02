package controller.mainController;

import controller.ballscontroller.BallsController;
import model.block.BlockImpl;
import model.ball.Ball;
import model.collision.CollisionDetectedImpl;
import model.collision.CollisionImpl;
import model.player.Player;
import utility.Observer;
import utility.Source;
import javafx.util.Pair;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class MainControllerImpl implements MainController{

    enum GameState{
        PAUSED,
        WAIT_END_LAUNCH,
        READY
    }

    private Optional<Player> player;
    private int currentScore;
    private Map<Pair<Integer, Integer>, BlockImpl> blocks;
    private BallsController ballsController;
    private GameState gameState;

    public MainControllerImpl(){

    }



    @Override
    public boolean isLaunchPossible() {
        return false;
    }

    @Override
    public void notifyHittedBlocks(List<BlockImpl> blocks) {

    }

    @Override
    public boolean isGameFinished() {
        return false;
    }

    @Override
    public int getScore() {
        return 0;
    }

    @Override
    public void addScore() {

    }

    @Override
    public void addMoney(int money) {

    }

    @Override
    public void addBall() {

    }

    @Override
    public void setPositionLaunch(int x, int y) {

    }

    @Override
    public void updateGridBlocks(List<BlockImpl> newRow) {

    }


    @Override
    public List<BlockImpl> getColumnOfBlocks(int colIndex) {
        return null;
    }

    @Override
    public List<BlockImpl> getRowOfBlocks(int rowIndex) {
        return null;
    }

    @Override
    public List<Ball> getBalls() {
        return null;
    }

    @Override
    public List<BlockImpl> getBlocks() {
        return null;
    }

    @Override
    public void allBallsBackToHome() {

    }


    @Override
    public void setBallsObservers() {
        //TODO modificare e aggiornare
        CollisionImpl collController = new CollisionImpl();

        Observer checkCollision = new Observer<Ball>() {
            @Override
            public void update(Source source, Ball arg) {
                CollisionDetectedImpl cd = collController.checkCollision(arg).get();

                if(cd.getBlocks().isPresent() && cd.getNewCenterPosition().isEmpty() && cd.getNewDirection().isEmpty()){
                    //TODO politiche di gestione bonus
                }
                if(cd.getBlocks().isPresent() && cd.getNewCenterPosition().isPresent() && cd.getNewDirection().isPresent()){
                    arg.collision(cd.getNewCenterPosition().get(), cd.getNewDirection().get());
                    //cambiare con BloxkView
                    //notifyHittedBlocks(cd.getBlocks().get());
                }
                if(cd.getBlocks().isEmpty() && cd.getNewCenterPosition().isPresent() && cd.getNewDirection().isPresent()){
                    arg.collision(cd.getNewCenterPosition().get(), cd.getNewDirection().get());
                }
                if(cd.getBlocks().isEmpty() && cd.getNewCenterPosition().isPresent() && cd.getNewDirection().isEmpty()){
                    arg.collision(null, null);
                    //TODO avviso bottom border colpito
                }
            }
        };

        Observer endLaunchControl = new Observer<Ball>() {
            @Override
            public void update(Source source, Ball argument) {
                allBallsBackToHome();
            }
        };
    }

    @Override
    public void pauseGame() {

    }

    @Override
    public void restartGame() {

    }

    @Override
    public boolean isPaused() {
        return false;
    }
}
