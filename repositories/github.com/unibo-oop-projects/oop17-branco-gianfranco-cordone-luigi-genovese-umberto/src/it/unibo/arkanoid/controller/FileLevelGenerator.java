package it.unibo.arkanoid.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import it.unibo.arkanoid.model.Level;
import it.unibo.arkanoid.model.LevelBuilder;
import it.unibo.arkanoid.subject.BrickType;

/**
 * Class used for the generation of brick in a level.
 */
public class FileLevelGenerator implements LevelGenerator {

    private final Map<Character, BuilderBrickOperation> symbolsActions;

    private interface BuilderBrickOperation {
        void addBrick(LevelBuilder builder, int x, int y);
    }

    /**
     * 
     */
    public FileLevelGenerator() {
        this.symbolsActions = new HashMap<>();
        this.symbolsActions.put('0', (b, x, y) -> b.addBrick(x, y, BrickType.NO_BRICK));
        this.symbolsActions.put('1', (b, x, y) -> b.addBrick(x, y, BrickType.SIMPLE));
        this.symbolsActions.put('2', (b, x, y) -> b.addBrick(x, y, BrickType.MULTIPLE));
        this.symbolsActions.put('3', (b, x, y) -> b.addBrick(x, y, BrickType.INDESTRUCTIBLE));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Level generateLevel(final int index, final LevelBuilder levelBuilder) {

        final InputStream filePath = this.getClass().getResourceAsStream("/levels/Level_" + index + ".txt");
        try (BufferedReader r = new BufferedReader(new InputStreamReader(filePath))) {
            final List<String> lines = r.lines().collect(Collectors.toList());
            for (int i = 0; i < lines.size(); i++) {
                this.parseLine(lines.get(i), levelBuilder.getGridHeight() - i - 1, levelBuilder);
            }
            return levelBuilder.build();

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void parseLine(final String line, final int index, final LevelBuilder builder) {
        for (int i = 0; i < line.length(); i++) {
            this.symbolsActions.get(line.charAt(i)).addBrick(builder, i, index);
        }
    }
}
