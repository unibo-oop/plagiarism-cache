package it.unibo.monoopoly.view.position.impl;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import it.unibo.monoopoly.view.position.api.PositionAllocator;
import it.unibo.monoopoly.view.position.api.PositionsFactory;

/**
 * Class that takes lists of player positions, cells, prison and houses
 * and creates a list of objects that will be displayed on the gameBoard,
 * implementing {@link PositionAllocator} interface.
 */
public class PositionAllocatorImpl implements PositionAllocator {

    private final Map<Color, List<Position>> playersPositions;
    private final Map<Integer, Position> propertyPositions;
    private final Map<Integer, Position> housesPositions;
    private final Map<Color, Position> prisonPositions;
    private final Map<String, Color> playersColors;

    /**
     * initialize all fields needed.
     * 
     * @param mainFrameHeight height of frame
     * @param playersColors data to associate colors to players
     * @param colors all possible colors
     */
    public PositionAllocatorImpl(final int mainFrameHeight, final Map<String, Color> playersColors,
            final List<Color> colors) {
        this.playersColors = new HashMap<>(playersColors);
        final PositionsFactory positionsFactory = new PositionsFactoryImpl(mainFrameHeight, colors);
        this.playersPositions = positionsFactory.createPlayersPositions();
        this.propertyPositions = positionsFactory.createPropertyPositions();
        this.housesPositions = positionsFactory.createHousesPositions();
        this.prisonPositions = positionsFactory.createPrisonPositions();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<NumberAndCirclePosition> createListCircleNumberPosition(final Map<String, Integer> newPlayersPositions,
            final Map<Integer, Optional<String>> cellsOwners,
            final List<String> prisonedPlayers,
            final Map<Integer, Integer> nBuiltHouses,
            final List<Integer> mortgagedProperties) {
        final List<NumberAndCirclePosition> newList = new ArrayList<>();

        newList.addAll(createCircleOfPlayersPositions(newPlayersPositions));
        newList.addAll(createCircleOfPropertyPositions(cellsOwners));
        newList.addAll(createCircleOfPrisonedPlayers(prisonedPlayers));
        newList.addAll(createCircleOfMortgagedProperties(mortgagedProperties));
        newList.addAll(createNumberOfHousesBuilded(nBuiltHouses));

        return newList;
    }

    private List<NumberAndCirclePosition> createCircleOfPlayersPositions(
            final Map<String, Integer> newPlayersPositions) {
        return this.playersColors.entrySet().stream()
                .filter(e -> newPlayersPositions.containsKey(e.getKey()))
                .map(e -> new NumberAndCirclePosition.Builder()
                        .x((int) getX(e, newPlayersPositions))
                        .y((int) getY(e, newPlayersPositions))
                        .circle(true)
                        .color(this.playersColors.get(e.getKey()))
                        .build())
                .toList();
    }

    private List<NumberAndCirclePosition> createCircleOfPropertyPositions(
            final Map<Integer, Optional<String>> cellsOwners) {
        return cellsOwners.entrySet().stream()
                .filter(e -> e.getValue().isPresent())
                .map(e -> new NumberAndCirclePosition.Builder()
                        .x((int) this.propertyPositions.get(e.getKey()).x())
                        .y((int) this.propertyPositions.get(e.getKey()).y())
                        .circle(true)
                        .color(this.playersColors.get(e.getValue().get()))
                        .build())
                .toList();
    }

    private List<NumberAndCirclePosition> createCircleOfPrisonedPlayers(final List<String> prisonedPlayers) {
        return prisonedPlayers.stream()
                .map(this.playersColors::get)
                .map(c -> new NumberAndCirclePosition.Builder()
                        .x((int) this.prisonPositions.get(c).x())
                        .y((int) this.prisonPositions.get(c).y())
                        .circle(true)
                        .color(c)
                        .build())
                .toList();
    }

    private List<NumberAndCirclePosition> createCircleOfMortgagedProperties(final List<Integer> mortgagedProperties) {
        return mortgagedProperties.stream()
                .map(c -> new NumberAndCirclePosition.Builder()
                        .x((int) this.propertyPositions.get(c).x())
                        .y((int) this.propertyPositions.get(c).y())
                        .circle(true)
                        .color(Color.BLACK)
                        .build())
                .toList();
    }

    private List<NumberAndCirclePosition> createNumberOfHousesBuilded(final Map<Integer, Integer> nBuiltHouses) {
        return nBuiltHouses.entrySet().stream()
                .map(e -> new NumberAndCirclePosition.Builder()
                        .x((int) this.housesPositions.get(e.getKey()).x())
                        .y((int) this.housesPositions.get(e.getKey()).y())
                        .circle(false)
                        .color(Color.BLACK)
                        .number(e.getValue().toString())
                        .build())
                .toList();
    }

    private double getX(final Map.Entry<String, Color> entry, final Map<String, Integer> newPlayersPositions) {
        return this.playersPositions.get(entry.getValue()).get(newPlayersPositions.get(entry.getKey())).x();
    }

    private double getY(final Map.Entry<String, Color> entry, final Map<String, Integer> newPlayersPositions) {
        return this.playersPositions.get(entry.getValue()).get(newPlayersPositions.get(entry.getKey())).y();
    }
}
