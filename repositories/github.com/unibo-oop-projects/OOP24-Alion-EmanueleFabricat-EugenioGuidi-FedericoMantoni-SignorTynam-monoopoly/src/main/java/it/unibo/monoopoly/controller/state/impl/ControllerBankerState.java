package it.unibo.monoopoly.controller.state.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.monoopoly.common.Event;
import it.unibo.monoopoly.controller.data.api.DataBuilderInput;
import it.unibo.monoopoly.controller.data.impl.DataBuilderInputImpl;
import it.unibo.monoopoly.controller.data.impl.DataInput;
import it.unibo.monoopoly.controller.data.impl.DataOutput;
import it.unibo.monoopoly.controller.main.api.MainController;
import it.unibo.monoopoly.controller.state.api.ControllerState;
import it.unibo.monoopoly.model.gameboard.api.Buildable;
import it.unibo.monoopoly.model.gameboard.api.Buyable;
import it.unibo.monoopoly.model.gameboard.api.Cell;
import it.unibo.monoopoly.model.player.api.Player;
import it.unibo.monoopoly.model.state.api.ModelState;
import it.unibo.monoopoly.view.state.api.ViewState;

/**
 * Implementation of {@link ControllerState} for the banker:
 * that call the {@link ModelState} and {@link ViewState} methods,
 * in the right order,
 * with the right input.
 * Build with {@link DataBuilderInput} all the data that need the view.
 */
public class ControllerBankerState implements ControllerState {
    private final MainController mainController;
    private final ModelState actualModelState;
    private final ViewState actualViewState;
    private final Set<Buyable> playerProperty;
    private final List<Cell> gameBoardCellList;
    private final DataBuilderInput dataBuilderInput = new DataBuilderInputImpl();
    private boolean isIndebted;

    /**
     * Constructor of the class that sets the fields.
     * 
     * @param mainController    the main controller to be set.
     * @param actualModelState  the actual {@link ModelState} to be set.
     * @param actualViewState   the actual {@link ViewState} to be set.
     * @param playerProperty    the {@link Set} of property of the actual
     *                          {@link Player}.
     * @param gameBoardCellList the {@link List} of all {@link Cell} of the game.
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "Suppressing according to pattern State")
    public ControllerBankerState(final MainController mainController, final ModelState actualModelState,
            final ViewState actualViewState, final Set<Buyable> playerProperty, final List<Cell> gameBoardCellList) {
        this.mainController = mainController;
        this.actualModelState = actualModelState;
        this.actualViewState = actualViewState;
        this.playerProperty = Set.copyOf(playerProperty);
        this.gameBoardCellList = List.copyOf(gameBoardCellList);
    }

    /**
     *
     * {@inheritDoc}
     * In this specific case,
     * then communicates this to the {@link ViewState} and calls the visualize method,
     * correctly constructing the {@link DataInput}.
     */
    @Override
    public void startControllerState() {
        isIndebted = this.actualModelState.verify();
        this.actualViewState.setter(isIndebted);
        this.actualViewState.visualize(buildDataInput());
    }

    /**
     *
     * {@inheritDoc}
     * In this specific case,
     * executes the operations in the model according to the logic,
     * passing the player's possible choices,
     * then finalizes the model's state and calls the nextPhase method of the {@link MainController}.
     */
    @Override
    public void closeControllerState(final DataOutput dataOutput) {
        this.actualModelState.doAction(dataOutput);
        this.actualModelState.closeModelState();
        mainController.nextPhase();

    }

    private DataInput buildDataInput() {
        if (!this.isIndebted) {
            return this.dataBuilderInput.build();
        }
        final Event event = this.mainController.getActualEvent().get();
        return switch (event) {
            case Event.SELL_HOUSE,
                    Event.MORTGAGE_PROPERTY ->
                this.dataBuilderInput
                        .event(event)
                        .cellMap(cellListChooser(event))
                        .build();
            case Event.BANKRUPT -> this.dataBuilderInput
                    .event(event)
                    .build();
            default -> throw new IllegalStateException(
                    "It's impossible to have an different Event if isn't indebted");
        };
    }

    private Map<Integer, Integer> cellListChooser(final Event event) {
        return switch (event) {
            case Event.SELL_HOUSE -> sellHouseList();
            case Event.MORTGAGE_PROPERTY -> propertiesMortgageableList();
            default -> throw new IllegalStateException(
                    "It's impossible to create a list of cells to show in ControllerBankerState without an Event");
        };
    }

    private Stream<Buyable> unmortgagedList(final Set<Buyable> properties) {
        return properties.stream()
                .filter(p -> !p.isMortgaged());
    }

    private Map<Integer, Integer> sellHouseList() {
        return unmortgagedList(this.playerProperty)
                .filter(p -> p instanceof Buildable)
                .map(p -> (Buildable) p)
                .filter(p -> p.getHousesNumber() > 0)
                .collect(Collectors.toMap(gameBoardCellList::indexOf, this::getHouseIncome));
    }

    private int getHouseIncome(final Buildable property) {
        return property.getSellHouseCost();
    }

    private Map<Integer, Integer> propertiesMortgageableList() {
        return unmortgagedList(this.playerProperty)
                .collect(Collectors.toMap(gameBoardCellList::indexOf, this::getMortgageIncome));
    }

    private int getMortgageIncome(final Buyable property) {
        return property.getMortgageValue();
    }

}
