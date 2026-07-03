package model.aim;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import model.player.PlayerInfo;
import model.region.Region;
import utils.enumerations.Color;

/**
 * 
 * This Class is a factory for objects of those Classes which implement
 * interface "Aim".
 *
 */
public class AimFactoryImpl implements AimFactory {

    private static final int MAX_AIM = 1;
    private final List<Set<Region>> definedAvailableRegion;
    private final List<Set<Region>> undefinedAvailableRegion;
    private final List<Color> availableEnemies;
    private final int availableDoubleGarrisonAim;
    private final int availableStateAim;
    private final List<AimLabel> aimList;
    private final Random random;
    private boolean initialized;
    private final int defaultNumStates;
    private final int doubleGarrisonNumStates;

    /**
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
     */
    public AimFactoryImpl(final List<Set<Region>> definedAvailableRegion,
            final List<Set<Region>> undefinedAvailableRegion, final int defaultNumStates,
            final int doubleGarrisonNumStates) {
        this.definedAvailableRegion = new ArrayList<>(definedAvailableRegion);
        this.undefinedAvailableRegion = new ArrayList<>(undefinedAvailableRegion);
        this.defaultNumStates = defaultNumStates;
        this.doubleGarrisonNumStates = doubleGarrisonNumStates;
        this.random = new Random();
        this.availableDoubleGarrisonAim = MAX_AIM;
        this.availableStateAim = MAX_AIM;
        this.availableEnemies = new ArrayList<>();
        this.aimList = new ArrayList<>();
        this.initialized = false;

    }

    /**
     * @return copy of list definedAvailableRegion.
     */
    public List<Set<Region>> getDefinedAvailableRegion() {
        return new LinkedList<Set<Region>>(this.definedAvailableRegion);
    }

    /**
     * @return copy of list undefinedAvailableRegion.
     */
    public List<Set<Region>> getUndefinedAvailableRegion() {
        return new LinkedList<>(this.undefinedAvailableRegion);
    }

    /**
     * @return copy of list availableEnemies.
     */
    public List<Color> getAvailableEnemies() {
        return new LinkedList<>(this.availableEnemies);
    }

    /**
     * @return copy of list aimList.
     */
    public List<AimLabel> getAimList() {
        return new LinkedList<>(this.aimList);
    }

    @Override
    public void initializeFactory() {
        if (!this.initialized) {
            for (final Color c : Color.values()) {
                this.availableEnemies.add(c);
            }
            for (int i = 0; i < this.availableEnemies.size(); i++) {
                this.aimList.add(AimLabel.DA);
            }
            for (int i = 0; i < this.definedAvailableRegion.size(); i++) {
                this.aimList.add(AimLabel.DCA);
            }
            for (int i = 0; i < this.undefinedAvailableRegion.size(); i++) {
                this.aimList.add(AimLabel.UCA);
            }
            for (int i = 0; i < this.availableDoubleGarrisonAim; i++) {
                this.aimList.add(AimLabel.DGA);
            }
            for (int i = 0; i < this.availableStateAim; i++) {
                this.aimList.add(AimLabel.SA);
            }
            this.initialized = true;
        } else {
            throw new IllegalStateException("AimFactory already initialized");
        }

    }

    private Set<Region> getRandomDefinedSet() {
        final int pos = random.nextInt(this.definedAvailableRegion.size());
        return this.definedAvailableRegion.remove(pos);
    }

    private Set<Region> getRandomUndefinedSet() {
        final int pos = random.nextInt(this.undefinedAvailableRegion.size());
        return this.undefinedAvailableRegion.remove(pos);
    }

    private Color getRandomEnemy() {
        final int pos = random.nextInt(this.availableEnemies.size());
        return this.availableEnemies.remove(pos);
    }

    private AimLabel drawAimType() {
        final int pos = random.nextInt(this.aimList.size());
        return this.aimList.remove(pos);
    }

    @Override
    public Aim assignAim(final PlayerInfo player) {
        if (player == null) {
            throw new IllegalArgumentException("player must be a non null object");
        }
        if (!this.initialized) {
            throw new IllegalStateException("init method must be called before");
        }

        final AimLabel label = drawAimType();

        switch (label) {

        case DA:
            return new DestroyAim(this.defaultNumStates, player, getRandomEnemy());
        case SA:
            return new StateAim(this.defaultNumStates, player);
        case DCA:
            return new DefinedConquerAim(getRandomDefinedSet(), player);
        case DGA:
            return new DoubleGarrisonAim(this.doubleGarrisonNumStates, player);
        case UCA:
            return new UndefinedConquerAim(getRandomUndefinedSet(), player);
        default:
            throw new IllegalStateException();
        }
    }

}
