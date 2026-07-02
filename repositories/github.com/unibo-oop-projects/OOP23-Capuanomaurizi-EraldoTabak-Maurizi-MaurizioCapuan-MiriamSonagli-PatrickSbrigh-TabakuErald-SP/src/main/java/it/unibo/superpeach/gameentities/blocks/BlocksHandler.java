package it.unibo.superpeach.gameentities.blocks;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

/**
 * Blocks handler class with manages two lists that contains every block of the
 * game.
 * 
 * @author Maurizio Capuano
 */
public final class BlocksHandler {

    private final List<MapFixedBlock> fixedBlocks;
    private final List<Block> backgroundBlocks;

    /**
     * Constructor which initializes lists.
     */
    public BlocksHandler() {
        fixedBlocks = new ArrayList<>();
        backgroundBlocks = new ArrayList<>();
    }

    /**
     * Renderization of every block.
     * 
     * @param g Graphics reference
     */
    public void renderBlocks(final Graphics g) {
        for (final MapFixedBlock block : fixedBlocks) {
            block.render(g);
        }
        for (final Block block : backgroundBlocks) {
            block.render(g);
        }
    }

    /**
     * @param b FixedBlock to add
     */
    public void addFixedBlock(final MapFixedBlock b) {
        fixedBlocks.add(b);
    }

    /**
     * @param b BackGroundBlock or FixedBlock which does not need to interact
     */
    public void addBackgroundBlock(final Block b) {
        backgroundBlocks.add(b);
    }

    /**
     * @param b Block to remove
     */
    public void removeFixedBlock(final MapFixedBlock b) {
        fixedBlocks.remove(b);
    }

    /**
     * @return a safe copy of the FixedBlocks list
     */
    public List<MapFixedBlock> getBlocks() {
        return List.copyOf(fixedBlocks);
    }

}
