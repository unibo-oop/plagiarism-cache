package controller.selection;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javafx.util.Pair;
import model.gamerules.GameRules;
import model.objects.GameObject;
import model.objects.structures.City;
import model.objects.structures.Structure;
import model.objects.terrains.Terrain;
import model.objects.unit.Unit;
import model.player.Player;
import model.skilltree.SkillTreeAttribute;
import util.Coordinates;

/**
 * Implementation of GameCommandsUsingSelection.
 */
public class GameCommandsUsingSelectionImpl implements GameCommandsUsingSelection {

    private final GameCommandsWithSelection gameCommands;

    /**
     * 
     * @param players the playing players
     * @param gameMode the game mode to play
     */
    public GameCommandsUsingSelectionImpl(final List<Player> players, final GameRules gameMode) {
        this.gameCommands = new GameCommandsWithSelection(players, gameMode);
    }

    /** {@inheritDoc} **/
    @Override
    public void selectPosition(final Coordinates cords) {
        this.gameCommands.selectPosition(cords);
    }

    /** {@inheritDoc} **/
    @Override
    public Optional<Selection> getCaseSelection(final Coordinates cords) {
        return this.gameCommands.getCaseSelection(cords);
    }

    /** {@inheritDoc} **/
    @Override
    public Optional<Pair<Coordinates, GameObject>> getActualSelection() {
        return this.gameCommands.getActualSelection();
    }

    /** {@inheritDoc} **/
    @Override
    public void select(final Coordinates cords, final GameObjectSelection selection) {
        this.gameCommands.select(cords, selection);
    }

    /** {@inheritDoc} **/
    @Override
    public Pair<Integer, Integer> getMapSize() {
        return this.gameCommands.getMapSize();
    }

    /** {@inheritDoc} **/
    @Override
    public Terrain getTerrain(final Coordinates cords) {
        return this.gameCommands.getTerrain(cords);
    }

    /** {@inheritDoc} **/
    @Override
    public Optional<Structure> getStructure(final Coordinates cords) {
        return this.gameCommands.getStructure(cords);
    }

    /** {@inheritDoc} **/
    @Override
    public Optional<Unit> getUnit(final Coordinates cords) {
        return this.gameCommands.getUnit(cords);
    }

    /** {@inheritDoc} **/
    @Override
    public Map<Coordinates, List<GameObject>> toMap() {
        return this.gameCommands.toMap();
    }

    /** {@inheritDoc} **/
    @Override
    public List<Unit> getPossibleUnit() {
        return this.gameCommands.getPossibleUnit();
    }

    /** {@inheritDoc} **/
    @Override
    public List<SkillTreeAttribute> getSkillTreeUpgradableAttribute() {
        return this.gameCommands.getSkillTreeUpgradableAttribute();
    }

    /** {@inheritDoc} **/
    @Override
    public void upgradeAttribute(final SkillTreeAttribute attribute) {
        this.gameCommands.upgradeAttribute(attribute);
    }

    /** {@inheritDoc} **/
    @Override
    public void nextTurn() {
        this.resetSelection();
        this.gameCommands.nextTurn();
    }

    /** {@inheritDoc} **/
    @Override
    public Optional<Player> getWinnerPlayer() {
        return this.gameCommands.getWinnerPlayer();
    }

    /** {@inheritDoc} **/
    @Override
    public Player getCurrentPlayer() {
        return this.gameCommands.getCurrentPlayer();
    }

    /** {@inheritDoc} **/
    @Override
    public String getPlayerInfo() {
        return this.gameCommands.getPlayerInfo();
    }

    /** {@inheritDoc} **/
    @Override
    public void createUnitFromSelectedCity(final Unit unit) {
        if (isActualSelectionInstanceOfCity()) {
            this.gameCommands.createUnitFromCity(unit, this.getActualSelection().get().getKey());
        } else {
            throw new IllegalArgumentException();
        }
    }

    /** {@inheritDoc} **/
    @Override
    public boolean canCreateUnit(final Unit unit) {
        return this.gameCommands.canCreateUnitFromCity(unit, this.getActualSelection().get().getKey());
    }

    /** {@inheritDoc} **/
    @Override
    public boolean canSelectedCityCreate() {
        return isActualSelectionInstanceOfCity()
                && this.gameCommands.canCityCreate(this.getActualSelection().get().getKey());
    }

    /** {@inheritDoc} **/
    @Override
    public boolean canUpgradeAttribute(final SkillTreeAttribute attributeToUpgrade) {
        return this.gameCommands.canUpgradeAttribute(attributeToUpgrade);
    }

    private boolean isActualSelectionInstanceOfCity() {
        return this.getActualSelection().isPresent() && (this.getActualSelection().get().getValue() instanceof City);
    }

    /** {@inheritDoc} **/
    @Override
    public void resetSelection() {
        this.gameCommands.resetSelection();
    }

}
