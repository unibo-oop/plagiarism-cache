package model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import model.aim.AimFactory;
import model.aim.AimFactoryImpl;
import model.aim.AimLabel;
import model.aim.DestroyAim;
import model.player.Player;
import model.region.Region;
import utils.CircularList;
import utils.enumerations.Color;

/**
 * 
 * Class used to assign Aim and check achievement.
 *
 */
public final class AimManager {

    private static final int MAX_NUM_PLAYER = 6;
    private CircularList<Player> players;
    private boolean initCalled;
    private AimFactory af;
    private static final AimManager SINGLETON = new AimManager();

    /**
     * 
     * @return true if AimManager has been initialized
     */
    public boolean isInitCalled() {
        return initCalled;
    }

    private AimManager() {
        this.initCalled = false;
    }

    /**
     * 
     * @return The instance of the AimManager.
     * 
     */
    public static AimManager getInstance() {
        return SINGLETON;
    }

    /**
     * 
     * This method assigns a random drawn aim to each player.
     *
     */
    private void assignAim() {
        for (final Player p : this.players) {
            p.setAim(this.af.assignAim(p));
        }
    }

    /**
     * 
     * This method assigns switches the aim to those player who were assigned a
     * "DestroyAim" but should defeat an army which hasn't been assigned to
     * anybody .
     *
     */
    private void fixDestroyAims() {
        if (this.players.size() != MAX_NUM_PLAYER) {
            final Set<Color> assigned = new HashSet<>();
            for (final Player p : this.players) {
                assigned.add(p.getColor());
            }
            for (final Player p : this.players) {
                if (p.getAim() instanceof DestroyAim && !assigned.contains(((DestroyAim) p.getAim()).getEnemy())) {
                    ((DestroyAim) p.getAim()).switchAim();
                }
            }
        }

    }

    /**
     *
     * This method initializes the field af and must be called just once.
     * 
     * @param definedAvailableRegion
     *            a collection containing the available sets of Region for the
     *            Aim "DefinedConquerAim"
     *
     * @param undefinedAvailableRegion
     *            a collection containing the available sets of Region for the
     *            Aim "UndefinedConquerAim"
     * 
     * @param defaultNumStates
     *            number of states required by DestroyAim and StateAim
     * 
     * 
     * @param doubleGarrisonNumStates
     *            number of states required by DoubleGarrisonAim
     * 
     * @param players
     *            the list of players
     * 
     *
     *
     */
    public void init(final List<Set<Region>> definedAvailableRegion, final List<Set<Region>> undefinedAvailableRegion,
            final int defaultNumStates, final int doubleGarrisonNumStates, final CircularList<Player> players) {
        if (players == null || definedAvailableRegion == null || undefinedAvailableRegion == null
                || defaultNumStates == 0 || doubleGarrisonNumStates == 0 || definedAvailableRegion.isEmpty()
                || undefinedAvailableRegion.isEmpty() || players.isEmpty()) {
            throw new IllegalArgumentException("Invalid argument");
        }
        if (!this.initCalled) {
            this.initCalled = true;
            this.players = players;
            this.af = new AimFactoryImpl(definedAvailableRegion, undefinedAvailableRegion, defaultNumStates,
                    doubleGarrisonNumStates);
            this.af.initializeFactory();
            assignAim();
            fixDestroyAims();
        }

    }

    /**
     * 
     * This method check if the current player has an aim of type
     * {@link AimLabel}.DGA if that's the case, it check if the current player
     * won.
     * 
     * @return whether the player achieved his aim or not.
     *
     */
    public boolean checkWin() {
        if (!this.initCalled) {
            throw new IllegalStateException("init method must be called before");
        }
        if (this.players.getHead().getAim().getType() == AimLabel.DGA) {
            return this.players.getHead().getAim().aimAchieved() ? true : false;
        }
        return false;
    }

    /**
     * 
     * This method check if the current player has achieved his aim in the last
     * battle. if needed the method also updates the aims of other players.
     * 
     * @param p
     *            last player defeated
     * 
     * 
     * @return whether the player achieved his aim or not.
     *
     */
    public boolean checkWin(final Player p) {
        if (p == null) {
            throw new IllegalArgumentException("p must be a non null object");
        }
        if (!this.initCalled) {
            throw new IllegalStateException("init method must be called before");
        }
        if (p.getStates().size() == 0) {
            if (this.players.getHead().getAim() instanceof DestroyAim) {
                ((DestroyAim) this.players.getHead().getAim()).setLastDefeated(p);
                if (this.players.getHead().getAim().aimAchieved()) {
                    return true;
                }
            }
            for (final Player other : this.players) {
                if (other.getAim() instanceof DestroyAim && ((DestroyAim) other.getAim()).getEnemy() == p.getColor()) {
                    ((DestroyAim) other.getAim()).switchAim();
                }
            }
        }

        return this.players.getHead().getAim().aimAchieved() ? true : false;
    }

    /**
     * This method is supposed to be called just once and only when a saved game
     * is loaded.
     * 
     * @param players
     *            the list of players
     */
    public void recovery(final CircularList<Player> players) {
        if (players == null) {
            throw new IllegalArgumentException("player must be a non null object");
        }
        if (players.isEmpty()) {
            throw new IllegalArgumentException("List of players can't be empty");
        }
        for (final Player p : players) {
            if (p.getAim() == null) {
                throw new IllegalStateException("Aims have never been assigned to players");
            }
        }
        if (!this.initCalled) {
            this.initCalled = true;
            this.players = players;
        }
    }

    /**
     * method called in test to reset initCalled field.
     */
    public void resetInitCalled() {
        this.initCalled = false;
    }

}
