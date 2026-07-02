package model.skilltree;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import model.Cost;
import model.objects.unit.Unit;
import model.player.Player;

/**
 * The UnitLevel class extends LevelAttribute with Unit as generic type. This
 * class manage the unit level structure. If a user increase is unit level, he
 * unlock new unit.
 */
public class UnitLevel extends LevelAttribute<Unit> {

    private static final String ATTRIBUTE_NAME = "Unit level increase: unlock ";
    private static final String WHITELIST_PACKAGE = "model.objects.unit";
    private static final String CLASSES_IMPLEMENTING = "model.objects.unit.Unit";
    private static final String FILTER_START = "model.objects.unit.Level";
    private static final String FILTER_END = "Unit";
    private static final int INITIAL_LEVEL = 1;

    /**
     * UnitLevel constructor.
     */
    public UnitLevel() {
        super(INITIAL_LEVEL, WHITELIST_PACKAGE, CLASSES_IMPLEMENTING, FILTER_START, FILTER_END);
    }

    /** {@inheritDoc} **/
    @Override
    protected Optional<Unit> getObject(final int level) {
        return getUnitWithOwner(level, Optional.empty());
    }

    /** {@inheritDoc} **/
    @Override
    public String getAttributeName() {
        return ATTRIBUTE_NAME + getNewObject().get().getName();
    }

    /** {@inheritDoc} **/
    @Override
    public Cost getCost() {
        verify();
        return getNewObject().get().getUnlockCost();
    }

    /**
     * This method can be use to get the list of all possible unit that a owner can create.
     * @param player is the player that own the SkillTree.
     * @return all possible unit that the player can create.
     */
    public List<Unit> getPossibleUnit(final Player player) {
        return IntStream.range(0, getCurrentValue() + 1).mapToObj(i -> getUnitWithOwner(i, Optional.of(player)).get())
                .collect(Collectors.toList());
    }

    private Optional<Unit> getUnitWithOwner(final int level, final Optional<Player> owner) {
        try {
            if (owner.isPresent()) {
                return Optional.of((Unit) Class.forName(getObjectClasses().get(level)).getConstructor(Player.class)
                        .newInstance(owner.get()));
            } else {
                return Optional.of((Unit) Class.forName(getObjectClasses().get(level)).getConstructor().newInstance());
            }
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException | ClassNotFoundException | IndexOutOfBoundsException e) {
            return Optional.empty();
        }
    }

}
