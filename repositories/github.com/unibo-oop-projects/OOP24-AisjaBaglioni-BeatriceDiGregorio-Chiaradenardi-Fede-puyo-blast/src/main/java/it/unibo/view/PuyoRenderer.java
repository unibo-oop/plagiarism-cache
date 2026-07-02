package it.unibo.view;

import it.unibo.controller.interfaces.TickListenerInterface;
import it.unibo.model.Grid;
import it.unibo.model.Scale;
import it.unibo.model.interfaces.PuyoInterface;
import it.unibo.view.interfaces.PuyoRendererInterface;

import javax.swing.ImageIcon;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.net.URL;

/**
 * This class is responsible for drawing and rendering the individual
 * Puyos on the grid, and updates them on every tick.
 */
public class PuyoRenderer implements TickListenerInterface, PuyoRendererInterface {
    /**
     * A map associating the color of the Puyos with the corresponding
     * row of the spritesheet.
     */
    private final Map<String, Integer> colorMap;
    /**
     * A spritesheet
     */
    private final Image sprites;
    /**
     * The graphics scaling
     */
    private Scale scale;
    /**
     * The game ticks on which the animations are rendered
     */
    private int ticks;
    /**
     * The last four bits of each integer determine the Puyo type to display from
     * the spritesheet, based on 16 possible neighbor configurations.
     * Each bit represents a direction where a neighboring Puyo is present.
     * For example, 0101 means the Puyo has a neighbor above and below but none on
     * the left or right.
     */
    private static final int[] SPRITE_MAPPER = {
            0, /** 0000 */ 
            1, /** 0001 */
            4, /** 0010 */
            5, /** 0011 */
            2, /** 0100 */
            3, /** 0101 */
            6, /** 0110 */
            7, /** 0111 */
            8, /** 1000 */
            9, /** 1001 */ 
            12, /** 1010 */
            13, /** 1011 */ 
            10, /** 1100 */
            11, /** 1101 */
            14, /** 1110 */
            15 /** 1111 */
    };

    /**
     * Constructs a PuyoRenderer with a given {@link Scale},
     * initializing the ticks to zero and setting the colors and value in the
     * colorMap.
     * 
     * @param scale
     */
    public PuyoRenderer(Scale scale) {
        this.ticks = 0;
        this.scale = scale;
        colorMap = new HashMap<>();
        colorMap.put("red", 0);
        colorMap.put("green", 1);
        colorMap.put("blue", 2);
        colorMap.put("yellow", 3);
        colorMap.put("purple", 4);
        colorMap.put("pink", 5);
        colorMap.put("cyan", 6);
        URL image_path = getClass().getClassLoader().getResource("images/puyosprites.png");
        sprites = new ImageIcon(image_path).getImage();
    }

    /**
     * Checks if a Puyo in [row, col] has the same color as the given parameter
     */
    @Override
    public int sameColorNeighbour(Grid grid, int row, int col, String color) {
        if (row < 0 || col < 0 || row >= grid.getRows() || col >= grid.getCols()) {
            return 0;
        }
        PuyoInterface puyo = grid.getPuyo(col, row);
        if (puyo == null) {
            return 0;
        }

        if (puyo.getColor().equals(color)) {
            return 1;
        }
        return 0;
    }

    /**
     * Displays the Puyo at [row, col] inside the {@link Grid}
     * 
     * The neighbor mapping is determined using the |= operator to set the
     * rightmost bit whether a neighbor is present
     * at the corresponding position, followed by the <<= operator
     * to shift all bits left, adding a 0 at the rightmost bit.
     * 
     * If a Puyo has no neighbors, it triggers a custom animation with a probability
     * of 2/101 every 5 ticks.
     * 
     * If the DeathClock is present, the Puyo briefly animates with
     * bubbles before disappearing from the Grid.
     * 
     * If the FreezeClock is present, the Puyo is rendered using
     * rows 7-13 of the spritesheet, displaying a block of ice containing
     * the Puyo.
     */
    @Override
    public void render(Graphics g, Grid grid, int row, int col) {
        PuyoInterface puyo = grid.getPuyo(col, row);
        if (puyo == null) {
            return;
        }
        int cellsize = this.scale.getScale() / 16;
        int offset_gridx = cellsize * 4;
        int offset_gridy = cellsize;
        int offset_animation = 0;

        Integer puyoRow = colorMap.get(puyo.getColor().toLowerCase());
        int x = puyo.getX() * cellsize;
        int y = puyo.getY() * cellsize;

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        String puyoColor = puyo.getColor();
        int mask = 0;
        /** null, null, null, null */
        mask |= sameColorNeighbour(grid, row, col - 1, puyoColor);
        /** null, null, null, sinistra */
        mask <<= 1;
        /** null, null, sinistra, null */
        mask |= sameColorNeighbour(grid, row - 1, col, puyoColor);
        /** null, null, sinistra, su */
        mask <<= 1;
        /** null, sinistra, su, null */
        mask |= sameColorNeighbour(grid, row, col + 1, puyoColor);
        /** null, sinistra, su, destra */
        mask <<= 1;
        /** sinistra, su, destra, null */
        mask |= sameColorNeighbour(grid, row + 1, col, puyoColor);
        /** sinistra, su, destra, giù */

        if (mask != 0) {
            offset_animation = SPRITE_MAPPER[mask] * 32;
        } else {
            if (((this.ticks / 5) ^ puyo.getIdentifier()) % 101 < 2) {
                offset_animation = 32 * 16;
            }
        }
        if (puyo.getDeathClock().isPresent()) {
            g2d.drawImage(
                    sprites,
                    x + offset_gridx,
                    y + offset_gridy,
                    x + cellsize + offset_gridx,
                    y + cellsize + offset_gridy,
                    puyoRow * 48,
                    32 * 15,
                    puyoRow * 48 + 32,
                    32 * 16,
                    null);
        } else if (puyo.getFreezeClock().isPresent()) {
            g2d.drawImage(
                    sprites,
                    x + offset_gridx,
                    y + offset_gridy,
                    x + cellsize + offset_gridx,
                    y + cellsize + offset_gridy,
                    offset_animation,
                    32 * (puyoRow + 7),
                    offset_animation + 32,
                    32 * (puyoRow + 8),
                    null);
        } else {
            g2d.drawImage(
                    sprites,
                    x + offset_gridx,
                    y + offset_gridy,
                    x + cellsize + offset_gridx,
                    y + cellsize + offset_gridy,
                    offset_animation,
                    32 * puyoRow,
                    offset_animation + 32,
                    32 * puyoRow + 32,
                    null);
        }
    }

    /**
     * Increases the ticks
     */
    @Override
    public void onTick() {
        this.ticks++;
    }
}