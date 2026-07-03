package it.unibo.tetris.mino;

import java.awt.Color;
import java.awt.Graphics2D;

import it.unibo.tetris.GamePanel;
import it.unibo.tetris.KeyHandler;
import it.unibo.tetris.PlayManager;
import it.unibo.tetris.mino.api.Block;
import it.unibo.tetris.mino.api.Mino;

/**
* Class {@link Mino_Impl} that implement the interface {@link Mino}.
*/
public class Mino_Impl implements Mino {
    /**
     * Utility constants.
     */
    private static final int NUM_OF_BLOCK_OF_A_MINO = 4;
    private static final int STARTING_DIRECTION = 1;
    /**
     * Create and instantiate 2 array of {@link Block}s 
     * of {@link NUM_OF_BLOCK_OF_A_MINO} spaces.
     */
    public Block b[] = new Block[NUM_OF_BLOCK_OF_A_MINO];
    public Block tempB[] = new Block[NUM_OF_BLOCK_OF_A_MINO];

    /**
     * Utility counters.
     */
    private int autoDropCounter = 0;
    private int deactivateCounter = 0;

    /**
     * Starting direction.
     */
    public int direction = STARTING_DIRECTION;

    /**
     * Boolean for detect collisions
     * and for activate and deactivate {@link Mino}s.
     */
    private boolean leftCollision,
                    rightCollision,
                    bottomCollision;
    public boolean active = true;
    public boolean deactivating;

    /**
     * Creates a new {@link Mino_Impl} with the given {@link Color}.
     *
     * @param c The {@link Color} of the Mino_Impl.
     */
    @Override
    public void create(Color c) {
        b[0] = new Block(c);
        b[1] = new Block(c);
        b[2] = new Block(c);
        b[3] = new Block(c);
        tempB[0] = new Block(c);
        tempB[1] = new Block(c);
        tempB[2] = new Block(c);
        tempB[3] = new Block(c);
    }
    
    @Override
    public void setXY(int x, int y) { 
    }

    /**
     * Check rotation collion and update position.
     */
    @Override
    public void updateXY(int direction) {
        checkRotationCollision();

        if (leftCollision == false &&
                rightCollision == false &&
                bottomCollision == false) {
            /**
             * We use a temp variable to handle the collision.
             */
            this.direction = direction;
            b[0].x = tempB[0].x;
            b[0].y = tempB[0].y;
            b[1].x = tempB[1].x;
            b[1].y = tempB[1].y;
            b[2].x = tempB[2].x;
            b[2].y = tempB[2].y;
            b[3].x = tempB[3].x;
            b[3].y = tempB[3].y;
        }

    }

    @Override
    public void getNextDirection(int direction) {
    }

    /**
     * Checks for collisions when moving the {@link Mino}.
     */
    @Override
    public void checkMovementCollision() {
 
        leftCollision = false;
        rightCollision = false;
        bottomCollision = false;

        /*
         * Check StaticBlock collision.
         */ 
        checkStaticBlockCollision();

        /*
         * Check left wall collision.
         */
        for (int i = 0; i < b.length; i++) {
            if (b[i].x == PlayManager.left_x) {
                leftCollision = true;
            }
        }
        /*
         * Check right wall collision.
         */
        for (int i = 0; i < b.length; i++) {
            if (b[i].x + Block.SIZE == PlayManager.right_x) {
                rightCollision = true;
            }
        }
        /*
         * Check bottom bound collision.
         */
        for (int i = 0; i < b.length; i++) {
            if (b[i].y + Block.SIZE == PlayManager.bottom_y) {
                bottomCollision = true;
            }
        }
    }

    /**
     * Checks for collisions when rotating the {@link Mino}.
     */
    @Override
    public void checkRotationCollision() {

        leftCollision = false;
        rightCollision = false;
        bottomCollision = false;

        /*
         * Check StaticBlock collision.
         */
        checkStaticBlockCollision();

        /*
         * Check left wall collision.
         */
        for (int i = 0; i < b.length; i++) {
            if (tempB[i].x < PlayManager.left_x) {
                leftCollision = true;
            }
        }
        /*
         * Check right wall collision.
         */
        for (int i = 0; i < b.length; i++) {
            if (tempB[i].x + Block.SIZE > PlayManager.right_x) {
                rightCollision = true;
            }
        }
        /*
         * Check bottom bound collision.
         */
        for (int i = 0; i < b.length; i++) {
            if (tempB[i].y + Block.SIZE > PlayManager.bottom_y) {
                bottomCollision = true;
            }
        }
    }

    /**
     * Checks for collisions when the {@link Mino} becomes static.
     */
    @Override
    public void checkStaticBlockCollision() {
        for (int i = 0; i < PlayManager.staticBlocks.size(); i++) {

            int targetX = PlayManager.staticBlocks.get(i).x;
            int targetY = PlayManager.staticBlocks.get(i).y;

            /*
             * Check bottom bound collision
             */
            for (int j = 0; j < b.length; j++) {
                if (b[j].y + Block.SIZE == targetY && b[j].x == targetX) {
                    bottomCollision = true;
                }
            }

            /*
             * Check left wall collision
             */
            for (int j = 0; j < b.length; j++) {
                if (b[j].x - Block.SIZE == targetX && b[j].y == targetY) {
                    leftCollision = true;
                }
            }

            /*
             * Check right wall collision
             */
            for (int j = 0; j < b.length; j++) {
                if (b[j].x + Block.SIZE == targetX && b[j].y == targetY) {
                    rightCollision = true;
                }
            }
        }
    }

    /**
     * Updates the {@link Mino}'s state based on its current state and game rules.
     */
    @Override
    public void update() {

        if (deactivating) {
            deactivating();
        }

        /*
         * Rotate the Mino.
         */
        if (KeyHandler.upPressed) {

            /*
             * Rotate the Mino when thw "W" Key where pressed.
             */
            getNextDirection(direction);

            KeyHandler.upPressed = false;
            GamePanel.soundEffect.play(3, false);
        }

        checkMovementCollision();

        /*
         * Bring down the Mino.
         */
        if (KeyHandler.downPressed) {

            /*
             * If the Mino isn't hitting the bottom bound 
             * can go down.
             */
            if (bottomCollision == false) {
                b[0].y += Block.SIZE;
                b[1].y += Block.SIZE;
                b[2].y += Block.SIZE;
                b[3].y += Block.SIZE;

                autoDropCounter = 0;
            }
            KeyHandler.downPressed = false;
        }

        /*
         * Move left the Mino.
         */
        if (KeyHandler.leftPressed) {

            if (leftCollision == false) {
                b[0].x -= Block.SIZE;
                b[1].x -= Block.SIZE;
                b[2].x -= Block.SIZE;
                b[3].x -= Block.SIZE;
            }
            KeyHandler.leftPressed = false;
        }

        /*
         * Move right the Mino.
         */
        if (KeyHandler.rightPressed) {

            if (rightCollision == false) {
                b[0].x += Block.SIZE;
                b[1].x += Block.SIZE;
                b[2].x += Block.SIZE;
                b[3].x += Block.SIZE;
            }
            KeyHandler.rightPressed = false;
        }

        /*
         * Mino hit the bottom bound.
         */
        if (bottomCollision) {
            if (deactivating == false) {
                GamePanel.soundEffect.play(4, false);
            }
            deactivating = true;
        } else {
            autoDropCounter++; 

            if (autoDropCounter == PlayManager.dropInterval) {
                b[0].y += Block.SIZE;
                b[1].y += Block.SIZE;
                b[2].y += Block.SIZE;
                b[3].y += Block.SIZE;
                autoDropCounter = 0;
            }
        }

    }

    /**
     * Deactivate the {@link Mino}.
     */
    public void deactivating() {

        deactivateCounter++;

        /*
         * Wait 45 frames before deactivate the Mino.
         */
        if (deactivateCounter == 45) {

            deactivateCounter = 0;
            checkMovementCollision(); 

            if (bottomCollision) {
                active = false;
            }
        }
    }

    /**
     * Draws the {@link Mino} on the given {@link Graphics2D} object.
     *
     * @param g2 The {@link Graphics2D} object to draw on.
     */
    @Override
    public void draw(Graphics2D g2) {
        g2.setColor(b[0].c);
        g2.fillRoundRect(b[0].x + Block.MARGIN,
                b[0].y + PlayManager.TITLE_HEIGHT + Block.MARGIN,
                Block.SIZE - (Block.MARGIN * 2),
                Block.SIZE - (Block.MARGIN * 2), 8, 8);
        g2.fillRoundRect(b[1].x + Block.MARGIN,
                b[1].y + PlayManager.TITLE_HEIGHT + Block.MARGIN,
                Block.SIZE - (Block.MARGIN * 2),
                Block.SIZE - (Block.MARGIN * 2), 8, 8);
        g2.fillRoundRect(b[2].x + Block.MARGIN,
                b[2].y + PlayManager.TITLE_HEIGHT + Block.MARGIN,
                Block.SIZE - (Block.MARGIN * 2),
                Block.SIZE - (Block.MARGIN * 2), 8, 8);
        g2.fillRoundRect(b[3].x + Block.MARGIN,
                b[3].y + PlayManager.TITLE_HEIGHT + Block.MARGIN,
                Block.SIZE - (Block.MARGIN * 2),
                Block.SIZE - (Block.MARGIN * 2), 8, 8);
    }

    /**
     * Draws the {@link Mino} on the given {@link Graphics2D} object 
     * adding an offset.
     *
     * @param g2 The {@link Graphics2D} object to draw on.
     */
    public void draw(Graphics2D g2, int offset) {
        g2.setColor(b[0].c);
        g2.fillRoundRect(b[0].x + offset + Block.MARGIN,
                b[0].y + PlayManager.TITLE_HEIGHT + Block.MARGIN,
                Block.SIZE - (Block.MARGIN * 2),
                Block.SIZE - (Block.MARGIN * 2), 8, 8);
        g2.fillRoundRect(b[1].x + offset + Block.MARGIN,
                b[1].y + PlayManager.TITLE_HEIGHT + Block.MARGIN,
                Block.SIZE - (Block.MARGIN * 2),
                Block.SIZE - (Block.MARGIN * 2), 8, 8);
        g2.fillRoundRect(b[2].x + offset + Block.MARGIN,
                b[2].y + PlayManager.TITLE_HEIGHT + Block.MARGIN,
                Block.SIZE - (Block.MARGIN * 2),
                Block.SIZE - (Block.MARGIN * 2), 8, 8);
        g2.fillRoundRect(b[3].x + offset + Block.MARGIN,
                b[3].y + PlayManager.TITLE_HEIGHT + Block.MARGIN,
                Block.SIZE - (Block.MARGIN * 2),
                Block.SIZE - (Block.MARGIN * 2), 8, 8);
    }


    /**
     * Getter of leftCollision value.
     * @return leftCollision.
     */
    public boolean isLeftCollision() {
        return leftCollision;
    }

    /**
     * Getter of rightCollision value.
     * @return rightCollision.
     */
    public boolean isRightCollision() {
        return rightCollision;
    }

    /**
     * Getter of bottomCollision value.
     * @return bottomCollision.
     */
    public boolean isBottomCollision() {
        return bottomCollision;
    }

    /**
     * Getter deactivating status.
     * @return deactivating.
     */
    public boolean isDeactivating() {
        return deactivating;
    }
}
