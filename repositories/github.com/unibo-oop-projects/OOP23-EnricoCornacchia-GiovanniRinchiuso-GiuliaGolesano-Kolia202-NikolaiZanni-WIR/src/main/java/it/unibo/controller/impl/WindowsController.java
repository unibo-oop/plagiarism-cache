package it.unibo.controller.impl;

import it.unibo.common.Pair;
import it.unibo.controller.api.Controller;
import it.unibo.model.api.ComponentType;
import it.unibo.model.api.GamePerformance;
import it.unibo.model.api.Entity;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.model.impl.EntityFactoryImpl;
import it.unibo.model.impl.FixedWindowsComponent;

/**
 * Class to control a window entity.
 */
public class WindowsController implements Controller {
    private final EntityFactoryImpl entityFactoryImpl;
    private final GamePerformance gamePerformance;
    private static final int WIDTH = 53;
    private static final int HEIGHT = 80;
    private static final int SPACING = -1;
    private static final int ROWS = 3;
    private static final int COLS = 5;
    private static final int OFFSET_X = 280;
    private static final int OFFSET_Y = 317;
    /**
     * Constructor.
     * @param gamePerformance the game performance.
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP2", justification = "We need the original object")
    public WindowsController(final GamePerformance gamePerformance) {
        this.gamePerformance = gamePerformance;
        this.entityFactoryImpl = new EntityFactoryImpl(this.gamePerformance);
    }
    /**
     * Method that check if there are broken windows.
     * @return true if the game is won, false otherwise.
     */
    public boolean won() {
        return this.gamePerformance.getWindows().stream()
            .allMatch(w -> w.getTheComponent(ComponentType.FIXEDWINDOWS)
                            .map(c -> ((FixedWindowsComponent) c).isFixed())
                            .orElse(false));
    }
    /**
     * Method that create the grid according to the level.
     * @param broken
     * @return the set of entities.
     */
    public Set<Entity> windowsGrid(final int broken) {
        final Set<Entity> entities = new HashSet<>();
        final List<Boolean> windowStates = new ArrayList<>(Collections.nCopies(ROWS * COLS, true));
        for (int i = 0; i < broken; i++) {
            windowStates.set(i, false);
        }
        Collections.shuffle(windowStates);

        int index = 0;
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                if (index < windowStates.size()) {
                    final double x = OFFSET_X + col * (WIDTH + SPACING);
                    final double y = OFFSET_Y + row * (HEIGHT + SPACING);
                    final boolean state = windowStates.get(index);
                    final Entity window = this.entityFactoryImpl.createWindows(new Pair<>(x, y), state);
                    entities.add(window);
                    index++;
                    this.gamePerformance.addEntity(window);
                }
            }
        }
        return entities;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void update() {
    }
}
