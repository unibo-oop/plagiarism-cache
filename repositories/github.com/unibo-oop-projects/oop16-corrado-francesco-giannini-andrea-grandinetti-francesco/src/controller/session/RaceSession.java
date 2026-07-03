package controller.session;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import controller.TyreController;
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
import utility.TyreType;
import view.View;

/**
 * A class that represent the race session.
 */
public class RaceSession extends AbstractSession {

    private static final String TYRE_ERROR  = "The tyre used isn't allowed in this circuit!";

    private final List<Player> deletedPlayers;
    private final TyreController ctr;
    private TyreType tyre;
    private boolean canBox;

    /**
     * Constructor.
     * @param raceDir the RaceDirection used
     * @param view the View used
     * @param list the players playing the game
     * @param ctr the tyreController currently used in the game
     */
    public RaceSession(final RaceDirection raceDir, final View view, final List<Player> list, final TyreController ctr) {
        super(raceDir, view, list);
        this.ctr = ctr;
        this.deletedPlayers = new ArrayList<>();
    }

    @Override
    public List<Player> startSession() {
        final List<Player> playerList = this.getPlayerList();
        while (this.deletedPlayers.size() != playerList.size()) {
            playerList.forEach(x -> {
                if (!this.deletedPlayers.contains(x)) {
                    boolean over;
                    try {
                        over = this.moveAndUpdate(x);
                    } catch (CrashHappenedException e) {
                        this.crashEvent(e.getCrashMap());
                        over = e.isLapEnd();
                    }
                    if (over) {
                        deletedPlayers.add(x);
                    }
                    this.getView().rankRace(this.getRaceDir().getPlacement(), false);
                    this.waitNext(x);
                }
            });
            this.updatePlayerOrder(this.getRaceDir().getPlacement());
        }
        return this.getPlayerList();
    }

    @Override
    protected int throwDice(final Driver drv) {
        try {
            final int number = this.getRaceDir().trowDice(drv);
            this.canBox = false;
            return number;
        } catch (CanBoxException e) {
            this.canBox = true;
            return e.getNum();
        }
    }

    @Override
    protected  Direction userChoose(final User drv, final int number, final Pair<Boolean, Boolean> dir) {
        //if the user is in the box it doesn't have to throw the dice
        if (drv.isInBox()) {
            this.tyre = null;
            return Direction.STRAIGHT;
        } else {
            final Direction tmp = this.getView().throwDice(number, dir, canBox);
            if (tmp == Direction.BOX) {
                this.tyre = this.getView().box(drv.getName());
            } else {
                this.tyre = null;
            }
            return tmp;
        }
    }

    @Override
    protected Direction cpuChoose(final AI drv, final Pair<Boolean, Boolean> pair) {
        if (canBox) {
            final Optional<TyreType> tyre = drv.changeTyre(this.getRaceDir().getDeg(drv.getDriver()));
            if (!tyre.equals(Optional.empty())) {
                if (this.ctr.isTheWrongTyre(tyre.get())) {
                    throw new IllegalStateException(TYRE_ERROR);
                }
                this.tyre = tyre.get();
                return Direction.BOX;
            }
        }
        tyre = null;
        Direction dir = drv.getDirection();
        if (dir.equals(Direction.LEFT) && !pair.getX()
            || dir.equals(Direction.RIGHT) && !pair.getY()) {
            dir = Direction.STRAIGHT;
        }
        return dir;
    }

    @Override
    protected Pair<Position, Boolean> move(final Player ply, final Direction dir,  final int diceNum)
            throws EndRaceException, CrashException {
        if (dir == Direction.BOX) {
            return new Pair<>(this.box(ply, tyre), false);
        } else if (ply.isInBox()) {
            return new Pair<>(this.turnInBox(ply), false);
        } else {
            return this.moveTheDriver(ply.getDriver(), dir, diceNum);
        }
    }

    private Position box(final Player ply, final TyreType tyre) {
        ply.goToBox();
        this.getRaceDir().box(ply.getDriver(), tyre);
        ply.addStint(tyre);
        return this.getRaceDir().getPosition(ply.getDriver());
    }

    private Position turnInBox(final Player ply) throws EndRaceException {
        final boolean hasFinishedBoxing = this.getRaceDir().lapInBox(ply.getDriver());
        if (hasFinishedBoxing) {
            ply.exitBox();
        }
        return this.getRaceDir().getPosition(ply.getDriver());
    }

    private void crashEvent(final Map<Driver, Boolean> map) {
        final List<Player> crashedDrivers = 
              this.getPlayerList().stream()
                                  .filter(x -> map.containsKey(x.getDriver()))
                                  .collect(Collectors.toList());
        final Map<Driver, Optional<String>> crashMap = new HashMap<>();
        final Map<Driver, Optional<String>> retireMap = new HashMap<>();
        crashedDrivers.forEach(x -> {
            Optional<String> name = Optional.empty();
            if (x instanceof User) {
                name = Optional.of(((User) x).getName());
            }
            if (map.get(x.getDriver())) {
                x.retire();
                this.deletedPlayers.add(x);
                retireMap.put(x.getDriver(), name);
            }
            crashMap.put(x.getDriver(), name);
        });
        this.getView().crash(crashMap);
        if (!retireMap.isEmpty()) {
            this.getView().retire(retireMap);
            retireMap.keySet().forEach(x -> 
            this.getView().update(x, this.getRaceDir().getPosition(x), false));
        }
    }
}
