package controller.session;

import java.util.List;
import java.util.stream.Collectors;

import controller.player.AI;
import controller.player.Player;
import controller.player.User;
import model.RaceDirection;
import model.exception.CanBoxException;
import model.exception.CrashException;
import model.exception.EndRaceException;
import utility.Direction;
import utility.Driver;
import utility.Pair;
import utility.Position;
import view.View;

/**
 * A class that represent the qualifying session.
 */
public class QualifyingSession extends AbstractSession {

    private static final double SLEEP_SECONDS = 0.75;

    /**
     * Constructor.
     * @param raceDir the RaceDirection used
     * @param view the View used
     * @param list the players playing the game
     */
    public QualifyingSession(final RaceDirection raceDir, final View view, final List<Player> list) {
        super(raceDir, view, list);
    }

    @Override
    public List<Player> startSession() {
        final QualifyingRank  rank = new QualifyingRankImpl();
        this.getPlayerList().forEach(elem -> {
            int turn = 0;
            boolean over = false;
            while (!over) {
                try {
                    over = this.moveAndUpdate(elem);
                } catch (CrashHappenedException e) {
                    over = e.isLapEnd();
                }
                turn++;
                this.waitNext(elem);
            }
            rank.addPlayer(elem.getDriver(), turn);
            this.getView().rankQualifying(rank.getRank(), false);
        });
        final List<Pair<Driver, Integer>> quali = rank.getRank();
        this.getView().rankQualifying(quali, true);
        this.updatePlayerOrder(quali.stream()
                                    .map(x -> x.getX())
                                    .collect(Collectors.toList()));
        return this.getPlayerList();
    }

    @Override
    protected int throwDice(final Driver drv) {
        try {
            return this.getRaceDir().trowDice(drv);
        } catch (CanBoxException e) {
            return e.getNum();
        }
    }

    @Override
    protected Direction userChoose(final User drv, final int number, final Pair<Boolean, Boolean> dir) {
        //the players can't go to box
        Direction tmp;
        do {
            tmp = this.getView().throwDice(number, dir, false);
        } while (tmp == Direction.BOX);
        return tmp;
    }

    @Override
    protected Direction cpuChoose(final AI drv, final Pair<Boolean, Boolean> pair) {
        Direction dir = drv.getDirection();
        if (dir.equals(Direction.LEFT) && !pair.getX()
            || dir.equals(Direction.RIGHT) && !pair.getY()) {
            dir = Direction.STRAIGHT;
        }
        return dir;
    }

    @Override
    protected Pair<Position, Boolean> move(final Player ply, final Direction dir, final int diceNum) 
            throws EndRaceException, CrashException {
        return this.moveTheDriver(ply.getDriver(), dir, diceNum);
    }

    @Override
    protected void waitNext(final Player ply) {
        if (ply instanceof AI) {
            try {
                Thread.sleep((long) (SLEEP_SECONDS * 1000));
            } catch (InterruptedException e) {
                System.out.println("Error while waiting");
            }
        }
    }
}
