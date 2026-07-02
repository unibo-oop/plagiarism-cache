package controller.selection;

import java.util.List;
import java.util.Optional;

import javafx.util.Pair;
import model.GameCommandsImpl;
import model.gamerules.GameRules;
import model.objects.GameObject;
import model.objects.structures.Structure;
import model.objects.terrains.Terrain;
import model.objects.unit.Unit;
import model.player.Player;
import util.Coordinates;

/**
 * adds selection commands to the GameCommands.
 */
public class GameCommandsWithSelection extends GameCommandsImpl implements SelectionCommands {

    private Optional<Pair<Coordinates, GameObject>> actualSelection = Optional.empty();

    /**
     * 
     * @param players the players of the game
     * @param rules   the rules to be used
     */
    public GameCommandsWithSelection(final List<Player> players, final GameRules rules) {
        super(players, rules);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void selectPosition(final Coordinates cords) {
        final Optional<Selection> newSelection = getCaseSelection(cords); // the one that also appears in the
        if (!newSelection.isPresent()) {
            selectNext(cords);
        } else {
            final Selection selection = newSelection.get();
            if (selection instanceof GameObjectSelection) {
                selectNext(cords);
            } else if (selection instanceof BasicUnitActionSelection) {
                final BasicUnitActionSelection action = (BasicUnitActionSelection) selection;
                switch (action) {
                case MOVEMENT:
                    super.moveUnit(this.actualSelection.get().getKey(), cords);
                    updateUnitSelection(cords, (Unit) this.actualSelection.get().getValue());
                    break;
                case ATTACK:
                    final Optional<Coordinates> battleResult = super.makeUnitFight(this.actualSelection.get().getKey(),
                            cords);
                    if (!battleResult.isPresent()) {
                        this.actualSelection = Optional.empty();
                    } else {
                        updateUnitSelection(battleResult.get(), (Unit) this.actualSelection.get().getValue());
                    }
                    break;
                default:
                    throw new IllegalStateException();
                }
            } else {
                throw new IllegalStateException();
            }
        }
    }
    
    private void selectNext(final Coordinates cords) {
        final Optional<Unit> newUnit = super.getUnit(cords);
        final Optional<Structure> newStruct = super.getStructure(cords);
        Terrain newTerrain = super.getTerrain(cords);
        if (!this.actualSelection.isPresent() || !this.actualSelection.get().getKey().equals(cords)) {
            setActualSelection(cords,
                    newUnit.isPresent() ? newUnit.get() : (newStruct.isPresent() ? newStruct.get() : newTerrain));
        } else {
            final GameObject actualGo = this.actualSelection.get().getValue();
            if (actualGo instanceof Terrain) {
                this.actualSelection = Optional.empty();
            } else if (actualGo instanceof Structure) {
                setActualSelection(cords, super.getTerrain(cords));
            } else if (actualGo instanceof Unit) {
//                if (actualGo instanceof Vehicle) { //enables the unit on the vehicle to be selected
//                    setActualSelection(cords, ((Vehicle) actualGo).getPassenger());
//                } else {
                setActualSelection(cords, super.getStructure(cords).isPresent() ? super.getStructure(cords).get()
                        : super.getTerrain(cords));
//                }
            }
        }
    }

    private void updateUnitSelection(final Coordinates cords, final Unit unit) {
        if (!this.actualSelection.isPresent() || !(this.actualSelection.get().getValue() instanceof Unit)) {
            throw new IllegalStateException();
        }
        if (super.getValidUnitMovements(cords).isEmpty() && super.getValidUnitAttacks(cords).isEmpty()) {
            this.actualSelection = Optional.empty();
        } else {
            setActualSelection(cords, super.getUnit(cords).get());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Selection> getCaseSelection(final Coordinates cords) {
        if (!this.getActualSelection().isPresent()) {
            return Optional.empty();
        } else {
            final Pair<Coordinates, GameObject> selection = this.actualSelection.get();
            if (selection.getKey().equals(cords)) {
                return Optional.of(getGameObjectType(selection.getValue()));
            } else if (getGameObjectType(selection.getValue()).equals(GameObjectSelection.UNIT)) {
                if (super.getValidUnitAttacks(selection.getKey()).contains(cords)) {
                    return Optional.of(BasicUnitActionSelection.ATTACK);
                } else if (super.getValidUnitMovements(selection.getKey()).contains(cords)) {
                    return Optional.of(BasicUnitActionSelection.MOVEMENT);
                }
            }
        }
        return Optional.empty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Pair<Coordinates, GameObject>> getActualSelection() {
        return this.actualSelection;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void select(final Coordinates cords, final GameObjectSelection selection) {
        if (!isValidSelection(cords, selection)) {
            throw new IllegalArgumentException();
        }
        switch (selection) {
        case UNIT:
            setActualSelection(cords, super.getUnit(cords).get());
            break;
        case STRUCTURE:
            setActualSelection(cords, super.getStructure(cords).get());
            break;
        case TERRAIN:
            setActualSelection(cords, super.getTerrain(cords));
            break;

        default:
            throw new IllegalStateException();
        }
    }

    private void setActualSelection(final Coordinates cords, final GameObject gameObject) {
        this.actualSelection = Optional.of(new Pair<>(cords, gameObject));
    }

    private GameObjectSelection getGameObjectType(final GameObject gameObject) {
        if (gameObject instanceof Unit) {
            return GameObjectSelection.UNIT;
        } else if (gameObject instanceof Structure) {
            return GameObjectSelection.STRUCTURE;
        } else if (gameObject instanceof Terrain) {
            return GameObjectSelection.TERRAIN;
        } else {
            throw new IllegalArgumentException();
        }
    }

    private boolean isValidSelection(final Coordinates cords, final GameObjectSelection selection) {
        switch (selection) {
        case UNIT:
            if (super.getUnit(cords).isPresent()) {
                return true;
            }
            break;
        case STRUCTURE:
            if (super.getStructure(cords).isPresent()) {
                return true;
            }
            break;
        case TERRAIN:
            return true;
        default:
            return false;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void resetSelection() {
        this.actualSelection = Optional.empty();
    }

}
