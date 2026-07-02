package model.skilltree;

import java.lang.reflect.InvocationTargetException;

import java.util.Optional;
import model.Cost;
import model.objects.structures.Capital;
import model.player.Player;

/**
 * The CapitalLevel class extends LevelAttribute with Capital as generic type.
 * This class manage the capital level structure. A player can increase the
 * level of his capital.
 */
public class CapitalLevel extends LevelAttribute<Capital> {

    private static final String ATTRIBUTE_NAME = "Capital level increase: unlock ";
    private static final String WHITELIST_PACKAGE = "model.objects.structures";
    private static final String CLASSES_IMPLEMENTING = "model.objects.structures.Capital";
    private static final String FILTER_START = "model.objects.structures.Level";
    private static final String FILTER_END = "Capital";
    private static final int INITIAL_LEVEL = 0;

    /**
     * UnitLevel constructor.
     */
    public CapitalLevel() {
        super(INITIAL_LEVEL, WHITELIST_PACKAGE, CLASSES_IMPLEMENTING, FILTER_START, FILTER_END);
    }

    /** {@inheritDoc} **/
    @Override
    protected Optional<Capital> getObject(final int level) {
        return getCapitalWithOwner(level, Optional.empty());
    }

    /** {@inheritDoc} **/
    @Override
    public String getAttributeName() {
        return ATTRIBUTE_NAME + "level " + (getCurrentValue() + 2) + " capital";
    }

    /** {@inheritDoc} **/
    @Override
    public Cost getCost() {
        return getNewObject().get().getUnlockCost();
    }

    /**
     * This method can be use to get the capital of the actual level.
     * @param owner is the owner of the SkillTree that has this attribute.
     * @return the actual level capital.
     */
    public Capital getActualCapital(final Player owner) {
        return getCapitalWithOwner(getCurrentValue(), Optional.of(owner)).get();
    }

    private Optional<Capital> getCapitalWithOwner(final int level, final Optional<Player> owner) {
        try {
            return Optional.of((Capital) Class.forName(getObjectClasses().get(level)).getConstructor(Optional.class)
                    .newInstance(owner));
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException | ClassNotFoundException | IndexOutOfBoundsException e) {
            return Optional.empty();
        }
    }

}
