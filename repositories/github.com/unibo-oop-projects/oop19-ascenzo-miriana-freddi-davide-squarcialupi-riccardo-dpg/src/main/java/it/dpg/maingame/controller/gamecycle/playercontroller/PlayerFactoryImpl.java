package it.dpg.maingame.controller.gamecycle.playercontroller;

import it.dpg.maingame.controller.gamecycle.turnmanagement.GameState;
import it.dpg.maingame.model.character.CharacterImpl;
import it.dpg.maingame.model.character.Difficulty;
import it.dpg.maingame.model.grid.Grid;
import it.dpg.maingame.view.grid.GridView;

public class PlayerFactoryImpl implements PlayerFactory {

    private final GameState state;
    private final GridView view;
    private final Grid grid;
    private int currentId = 0;

    public PlayerFactoryImpl(GameState state, GridView view, Grid grid) {
        this.grid = grid;
        this.view = view;
        this.state = state;
    }

    @Override
    public PlayerController createHumanPlayer(String name) {
        currentId++;
        return new HumanPlayerController(state, view, new CharacterImpl(currentId, name, grid));
    }

    @Override
    public PlayerController createCpu(String name, Difficulty difficulty) {
        currentId++;
        return new CpuPlayerController(state, view, new CharacterImpl(currentId, name, grid), difficulty);
    }
}
