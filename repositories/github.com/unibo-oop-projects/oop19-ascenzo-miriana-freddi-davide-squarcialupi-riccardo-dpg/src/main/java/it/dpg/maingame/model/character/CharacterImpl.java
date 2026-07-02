package it.dpg.maingame.model.character;

import it.dpg.maingame.model.grid.Cell;
import it.dpg.maingame.model.grid.CellType;
import it.dpg.maingame.model.grid.Grid;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Basic implementation of Character interface
 * @see Character
 * @author Davide Picchiotti
 * */

public class CharacterImpl implements Character {

    private final int id;
    private final String name;
    private final Grid grid;

    private int turn;
    private int remainingMoves;
    private int lastMinigameScore;
    private Cell position;
    private Dice dice;

    public CharacterImpl(final int id, final String name, final Grid grid) {
        this.id = id;
        this.name = name;
        this.grid = grid;

        this.position = grid.getFirst();
        this.dice = Dice.D6;
        this.lastMinigameScore = 0;
        this.remainingMoves = 0;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setTurn(final int turn) {
        this.turn = turn;
    }

    @Override
    public int getTurn() {
        return this.turn;
    }

    @Override
    public void setPosition(final Pair<Integer, Integer> coordinates) {
        this.position = this.grid.getCellByCoordinates(
                coordinates.getLeft(),
                coordinates.getRight()
        );
    }

    @Override
    public Pair<Integer, Integer> getPosition() {
        return this.position.getCoordinates();
    }

    @Override
    public Set<Pair<Integer, Integer>> getAdjacentPositions() {
        return this.position.getNext().stream()
                .map(Cell::getCoordinates)
                .collect(Collectors.toSet());
    }

    @Override
    public CellType getCellType() {
        return this.position.getType();
    }

    @Override
    public boolean stepForward() {
        if(!this.position.isAFork()) {
            return this.stepInDirection(this.position.getNext().iterator().next().getCoordinates());
        } else {
            throw new UnsupportedOperationException("Fork detected in the grid");
        }
    }

    @Override
    public void stepBackward() {
        position = position.getPrevious();
    }

    @Override
    public boolean stepInDirection(final Pair<Integer, Integer> coordinates) {
        if(this.remainingMoves > 0) {
            this.remainingMoves--;
            this.position = this.position.getNext().stream()
                    .filter(c -> c.getCoordinates().equals(coordinates))
                    .findAny()
                    .orElseThrow(
                            () -> new IllegalArgumentException("No cell found with coordinates: " + coordinates.getLeft() + ", " + coordinates.getRight())
                    );
            return this.remainingMoves > 0;
        } else {
            throw new UnsupportedOperationException("No more remaining moves");
        }
    }

    @Override
    public void setDice(final Dice dice) {
        this.dice = dice;
    }

    @Override
    public Dice getDice() {
        return this.dice;
    }

    @Override
    public int throwDice() {
        this.remainingMoves = new Random().nextInt(this.dice.getFaces()) + 1;
        return this.remainingMoves;
    }

    @Override
    public void setMinigameScore(final int score) {
        this.lastMinigameScore = score;
    }

    @Override
    public int getMinigameScore() {
        return this.lastMinigameScore;
    }
}
