package controller.session;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import controller.player.AI;
import controller.player.Player;
import controller.player.User;
import model.RaceDirection;
import model.exception.BlockException;
import model.exception.CrashException;
import model.exception.EndRaceException;
import utility.Direction;
import utility.Driver;
import utility.Pair;
import utility.Position;
import view.View;
import view.game.InfoDriver;
import view.game.InfoDriverBuilder;

/**
 * An abstract implementation of the Session interface.
 */
public abstract class AbstractSession implements Session {

    private static final int SLEEP_SECONDS = 1;

    private final RaceDirection raceDir;
    private final View view;
    private final List<Player> playerList;

    AbstractSession(final RaceDirection raceDir, final View view, final List<Player> list) {
        this.raceDir = raceDir;
        this.view = view;
        this.playerList = list;
    }

    /**
     * Move the player and update his position and info.
     * @param drv the player
     * @return if the player has finished the session or not
     * @throws CrashHappenedException if the player crashed into other players.
     */
    protected boolean moveAndUpdate(final Player drv) throws CrashHappenedException {
        CrashException crash = null;
        final int number = this.throwDice(drv.getDriver());
        final Direction dir = this.chooseDirection(drv, number);
        Pair<Position, Boolean> info;
        boolean isLapOver = false;
        try {
            try {
                info = this.move(drv, dir, number);
            } catch (CrashException e) {
                crash = e;
                info = new Pair<>(e.getPos(), false);
            }
        } catch (EndRaceException e) {
            info = new Pair<>(this.raceDir.getPosition(drv.getDriver()), false);
            isLapOver = true;
        }
        // Now I can update the view with all the informations it needs
        view.update(drv.getDriver(), info.getX(), info.getY());
        if (crash != null) {
            throw new CrashHappenedException(crash, isLapOver);
        }
        return isLapOver;
    }

    /**
     * Getter.
     * @return The RaceDirection used by the class
     */
    protected RaceDirection getRaceDir() {
        return raceDir;
    }

    /**
     * Getter.
     * @return The View used by the class
     */
    protected View getView() {
        return view;
    }

    /**
     * Getter.
     * @return The list of the current players in this session
     */
    protected List<Player> getPlayerList() {
        return this.playerList;
    }

    /**
     * Let choose to the User which direction he wants to take.
     * @param drv the user
     * @param number the number of the dice's throw
     * @param dir a pair of boolean that indicates the directions he can take
     * @return the direction chosen by the user
     */
    protected abstract Direction userChoose(User drv, int number, Pair<Boolean, Boolean> dir);

    /**
     * Let choose to the AI which direction it wants to take.
     * @param drv the AI
     * @param pair a pair of boolean that indicates the directions it can take
     * @return the direction chosen by the AI
     */
    protected abstract Direction cpuChoose(AI drv, Pair<Boolean, Boolean> pair);

    /**
     * Let throw the dice to the player.
     * @param drv the player's driver
     * @return the number he did
     */
    protected abstract int throwDice(Driver drv);

    /**
     * Let move the player in the direction he wants.
     * @param ply the player
     * @param dir the direction he chosen
     * @param diceNum the number of the dice's throw
     * @return a pair of the position he is and if he blocked or not
     * @throws EndRaceException if the player end the session
     * @throws CrashException if the player crashed into other players
     */
    protected abstract Pair<Position, Boolean> move(Player ply, Direction dir, int diceNum) throws EndRaceException, CrashException;

    /**
     * Let move the player in a direction that isn't the box direction.
     * @param drv the player's driver
     * @param dir the direction chosen
     * @param diceNum the number of the dice's throw
     * @return a pair of the position he is and if he blocked or not
     * @throws EndRaceException if the player end the session
     * @throws CrashException if the player crashed into other players
     */
    protected Pair<Position, Boolean> moveTheDriver(final Driver drv, final Direction dir, final int diceNum)
            throws EndRaceException, CrashException {
        try {
            final Position pos = raceDir.move(drv, dir, diceNum);
            return new Pair<>(pos, false);
        } catch (BlockException e) {
            return new Pair<>(e.getPos(), true);
        }
    }

    /**
     * Method to wait a certain time before the next player can play.
     * @param ply the last player who player
     */
    protected void waitNext(final Player ply) {
        int index = this.playerList.indexOf(ply) + 1;
        if (index >= this.playerList.size()) {
            index = 0;
        }
        if (ply instanceof AI || this.playerList.get(index) instanceof AI) {
            try {
                Thread.sleep(SLEEP_SECONDS * 1000);
            } catch (InterruptedException e) {
                System.out.println("Error while waiting");
            }
        }
    }

    /**
     * Method to update the order of the players.
     * @param list the new ordered list
     */
    protected void updatePlayerOrder(final List<Driver> list) {
        final List<Player> oldPlayerList = new ArrayList<>(this.playerList);
        this.playerList.clear();
        list.forEach(x -> {
            oldPlayerList.stream()
                         .filter(y -> x.equals(y.getDriver()))
                         .forEach(z -> this.playerList.add(z));
        });
    }

    private Direction chooseDirection(final Player drv, final int number) {
        final Pair<Boolean, Boolean> pair = 
                this.raceDir.checkDirections(drv.getDriver());
        // Is the actual player a user? If yes, we must wait until he throws the dice
        if (drv instanceof User) {
            updatePlayerInfo(drv);
            return userChoose((User) drv, number, pair);
        } else {
            updatePlayerInfo(drv);
            return cpuChoose((AI) drv, pair);
        }
    }

    private void updatePlayerInfo(final Player drv) {
        final boolean user = drv instanceof User;
        final Driver driver = drv.getDriver();
        final InfoDriver info = new InfoDriverBuilder()
                              .namePlayer(user ? Optional.of(((User) drv).getName()) : Optional.empty())
                              .driver(driver)
                              .deg(this.raceDir.getDeg(driver))
                              .tyre(this.raceDir.getTyreType(driver))
                              .position(this.playerList.indexOf(drv) + 1)
                              .shellPoints(this.raceDir.getCarFrameByDriver(driver))
                              .isUser(user)
                              .build();
        this.view.updatePlayer(info);
    }
}
