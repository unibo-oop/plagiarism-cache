package it.unibo.monoopoly.controller.main.impl;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import it.unibo.monoopoly.common.Event;
import it.unibo.monoopoly.controller.data.impl.ViewUpdateDTO;
import it.unibo.monoopoly.controller.main.api.MainController;
import it.unibo.monoopoly.controller.state.api.ControllerState;
import it.unibo.monoopoly.controller.state.impl.ControllerBankerState;
import it.unibo.monoopoly.controller.state.impl.ControllerBuildHouseState;
import it.unibo.monoopoly.controller.state.impl.ControllerCardState;
import it.unibo.monoopoly.controller.state.impl.ControllerCheckActionState;
import it.unibo.monoopoly.controller.state.impl.ControllerMovementState;
import it.unibo.monoopoly.controller.state.impl.ControllerPrisonState;
import it.unibo.monoopoly.controller.state.impl.ControllerUnmortgageState;
import it.unibo.monoopoly.model.gameboard.api.Buildable;
import it.unibo.monoopoly.model.gameboard.api.Buyable;
import it.unibo.monoopoly.model.gameboard.api.Cell;
import it.unibo.monoopoly.model.main.api.MainModel;
import it.unibo.monoopoly.model.main.impl.MainModelImpl;
import it.unibo.monoopoly.model.player.api.Player;
import it.unibo.monoopoly.model.state.impl.ModelBankerState;
import it.unibo.monoopoly.model.state.impl.ModelBuildHouseState;
import it.unibo.monoopoly.model.state.impl.ModelCardState;
import it.unibo.monoopoly.model.state.impl.ModelCheckActionState;
import it.unibo.monoopoly.model.state.impl.ModelMovementState;
import it.unibo.monoopoly.model.state.impl.ModelPrisonState;
import it.unibo.monoopoly.model.state.impl.ModelUnmortgageState;
import it.unibo.monoopoly.view.main.api.MainView;
import it.unibo.monoopoly.view.main.impl.MainViewImpl;
import it.unibo.monoopoly.view.state.impl.ViewBankerState;
import it.unibo.monoopoly.view.state.impl.ViewBuildHouseState;
import it.unibo.monoopoly.view.state.impl.ViewCardState;
import it.unibo.monoopoly.view.state.impl.ViewCheckActionState;
import it.unibo.monoopoly.view.state.impl.ViewMovementState;
import it.unibo.monoopoly.view.state.impl.ViewPrisonState;
import it.unibo.monoopoly.view.state.impl.ViewUnmortgageState;

/**
 * Implementation of {@link MainController}.
 */
public class MainControllerImpl implements MainController {

    private final MainView mainView;
    private final MainModel model;
    private ControllerState actualState;

    /**
     * Constructor that creates the model (TurnImpl) and the main view.
     * 
     * @param playersName list of players' names
     */
    public MainControllerImpl(final List<String> playersName) {
        this.model = new MainModelImpl(playersName);
        final List<String> cellsNames = model.getGameBoard().getCellsNames();
        this.mainView = new MainViewImpl(this, playersName, cellsNames);
        this.mainView.display();
        this.mainView.update();
        this.actualState = new ControllerPrisonState(this, this.model.getState(), this.mainView.getViewState(),
                this.model.getPlayerWrapper());
        this.actualState.startControllerState();
        // this.nextPhase();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void nextPhase() {
        if (this.model.getGameBoard().isGameEnded()) {
            this.mainView.endGame(this.model.getGameBoard().getCurrentPlayer().getName());
            return;
        }
        this.mainView.update();
        switch (this.model.getState()) {
            case final ModelPrisonState p -> {
                this.mainView.setState(new ViewPrisonState(mainView));
                this.actualState = new ControllerPrisonState(this, model.getState(),
                        mainView.getViewState(), this.model.getPlayerWrapper());
            }
            case final ModelMovementState m -> {
                this.mainView.setState(new ViewMovementState(mainView));
                this.actualState = new ControllerMovementState(this,
                        model.getState(), mainView.getViewState(), this.model.getGameBoard().getDices());
            }
            case final ModelCheckActionState ca -> {
                this.mainView.setState(new ViewCheckActionState(mainView));
                this.actualState = new ControllerCheckActionState(this, model.getState(), mainView.getViewState(),
                        model.getCellWrapper());
            }
            case final ModelCardState c -> {
                this.mainView.setState(new ViewCardState(mainView));
                this.actualState = new ControllerCardState(this, model.getState(),
                        mainView.getViewState(), this.model.getDeckWrapper());
            }
            case final ModelBankerState b -> {
                this.mainView.setState(new ViewBankerState(mainView));
                this.actualState = new ControllerBankerState(this, model.getState(),
                        mainView.getViewState(), this.model.getGameBoard().getCurrentPlayer().getProperties(),
                        this.model.getGameBoard().getCellsList());
            }
            case final ModelBuildHouseState bh -> {
                this.mainView.setState(new ViewBuildHouseState(mainView));
                this.actualState = new ControllerBuildHouseState(this,
                        model.getState(), mainView.getViewState(), this.model.getPlayerWrapper(),
                        this.model.getGameBoard().getCellsList());
            }
            case final ModelUnmortgageState u -> {
                this.mainView.setState(new ViewUnmortgageState(mainView));
                this.actualState = new ControllerUnmortgageState(this, model.getState(),
                        mainView.getViewState(), this.model.getPlayerWrapper(),
                        this.model.getGameBoard().getCellsList());
            }
            default -> throw new IllegalArgumentException("Implementation of ModelState not supported");
        }
        this.actualState.startControllerState();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Event> getActualEvent() {
        return this.model.getEvent();
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public ControllerState getControllerState() {
        return this.actualState;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ViewUpdateDTO getViewUpdateData() {
        return new ViewUpdateDTO(
                model.getGameBoard().getPlayersList().stream().filter(Predicate.not(Player::isPrisoned))
                        .collect(Collectors.toMap(Player::getName, Player::getActualPosition)),
                model.getGameBoard().getCellsList().stream().filter(Cell::isBuyable)
                        .filter(p -> !((Buyable) p).isMortgaged())
                        .collect(Collectors.toMap(this::cellToIndex,
                                c -> ((Buyable) c).getOwner().map(Player::getName))),
                model.getGameBoard().getCellsList().stream().filter(Cell::isBuildable)
                        .filter(c -> !((Buyable) c).isAvailable())
                        .collect(Collectors.toMap(this::cellToIndex, c -> ((Buildable) c).getHousesNumber())),
                model.getGameBoard().getPlayersList().stream().filter(Player::isPrisoned).map(Player::getName).toList(),
                model.getGameBoard().getCellsList().stream().filter(Cell::isBuyable)
                        .filter(p -> ((Buyable) p).isMortgaged()).map(this::cellToIndex).toList(),
                model.getGameBoard().getPlayersList().stream()
                        .collect(Collectors.toMap(Player::getName, Player::getMoneyAmount)),
                model.getGameBoard().getCurrentPlayer().getName());
    }

    private int cellToIndex(final Cell cell) {
        return this.model.getGameBoard().getCellsList().indexOf(cell);
    }
}
