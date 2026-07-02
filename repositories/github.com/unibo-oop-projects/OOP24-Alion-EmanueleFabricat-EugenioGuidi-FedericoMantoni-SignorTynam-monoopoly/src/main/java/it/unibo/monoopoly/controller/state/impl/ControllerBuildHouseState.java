package it.unibo.monoopoly.controller.state.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.monoopoly.controller.data.api.DataBuilderInput;
import it.unibo.monoopoly.controller.data.impl.DataBuilderInputImpl;
import it.unibo.monoopoly.controller.data.impl.DataOutput;
import it.unibo.monoopoly.controller.main.api.MainController;
import it.unibo.monoopoly.controller.state.api.ControllerState;
import it.unibo.monoopoly.model.gameboard.api.Buildable;
import it.unibo.monoopoly.model.gameboard.api.Cell;
import it.unibo.monoopoly.model.player.api.Player;
import it.unibo.monoopoly.model.player.impl.PlayerWrapper;
import it.unibo.monoopoly.model.state.api.ModelState;
import it.unibo.monoopoly.view.state.api.ViewState;

/**
 * Implementation of the controller for the house building state.
 * Coordinates the interactions between the model and the view.
 */
public class ControllerBuildHouseState implements ControllerState {

    private static final int MAX_HOUSES = 5;
    private final ModelState modelState;
    private final ViewState viewState;
    private final MainController mainController;
    private boolean canBuild;
    private final List<Cell> gameBoardCellList;
    private final PlayerWrapper playerWrapper;

    /**
     * Constructs the controller for the house building state.
     * 
     * @param mainController    the main controller
     * @param modelState        the model state
     * @param viewState         the view state
     * @param playerWrapper     the wrap of the actual {@link Player}.
     * @param gameBoardCellList the {@link List} of all {@link Cell} of the game.
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "Suppressing according to pattern State and pattern Proxy")
    public ControllerBuildHouseState(final MainController mainController, final ModelState modelState,
            final ViewState viewState, final PlayerWrapper playerWrapper, final List<Cell> gameBoardCellList) {
        this.modelState = modelState;
        this.viewState = viewState;
        this.mainController = mainController;
        this.gameBoardCellList = List.copyOf(gameBoardCellList);
        this.playerWrapper = playerWrapper;
    }

    /**
     * Starts the house building state.
     * Verifies if building is possible and sets the mode in the view.
     */
    @Override
    public void startControllerState() {
        canBuild = modelState.verify();
        viewState.setter(canBuild);
        final Map<Integer, Integer> buildableCells = canBuild
                ? this.playerWrapper.getProperties().stream()
                        .filter(p -> p instanceof Buildable)
                        .map(p -> (Buildable) p)
                        .filter(p -> p.getHousesNumber() < MAX_HOUSES && !p.isMortgaged())
                        .filter(p -> this.playerWrapper.isPayable(p.getHouseCost()))
                        .collect(Collectors.toMap(this.gameBoardCellList::indexOf, Buildable::getHouseCost))
                : new HashMap<>();
        final DataBuilderInput dataBuilder = new DataBuilderInputImpl();
        viewState.visualize(dataBuilder.cellMap(buildableCells).build());
    }

    /**
     * Continues the execution of the state by performing the action corresponding
     * to the user's choice.
     * 
     * @param data the data related to the cell chosen by the user
     */
    @Override
    public void closeControllerState(final DataOutput data) {
        if (canBuild) {
            modelState.doAction(data);
        }
        modelState.closeModelState();
        mainController.nextPhase();
    }
}
