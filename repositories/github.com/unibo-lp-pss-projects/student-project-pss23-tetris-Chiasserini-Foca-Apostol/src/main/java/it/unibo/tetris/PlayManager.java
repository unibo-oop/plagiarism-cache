package it.unibo.tetris;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

import it.unibo.tetris.mino.*;
import it.unibo.tetris.mino.api.*;

/**
 * Class {@link PlayManager}.
 * Contains the core of the game.
 */
public class PlayManager {
    /**
     * Utility constant for border thickness.
     */
    private static final int BORDER = 4;

    /**
     * Utility constant for height of the title.
     */ 
    public static final int TITLE_HEIGHT = 200;

    /**
     * Utility constant for PlayArea measures.
     */
    final int WIDTH = 360;
    final int HEIGHT = 600;

    /**
     * PlayArea bounds.
     */
    public static int left_x;
    public static int right_x;
    public static int top_y;
    public static int bottom_y;

    /**
     * Variables for Score.
     */
    int level = 1;
    int lines;
    int score;

    /**
     * Current {@link Mino}
     */
    Mino_Impl currentMino;
    public static int MINO_START_X; 
    public static int MINO_START_Y;

    /**
     * Next {@link Mino} that will come into play.
     */
    Mino_Impl nextMino;
    final int NEXTMINO_X;
    final int NEXTMINO_Y;

    /**
     * {@link ArrayList}<{@link Block}> for storage static Blocks.
     * One {@link Block} is static when is touching bottom bound.
     */
    public static ArrayList<Block> staticBlocks = new ArrayList<>();

    /**
     * Number of frames from one autodrop of the {@link Mino} to the next.
     */ 
    public static int dropInterval = 60; 

    /**
     * Gameover flag.
     */
    boolean gameOver;

    /**
     * Variables for Effects.
     */
    boolean effectCounterOn;
    int effectCounter;
    ArrayList<Integer> effectY = new ArrayList<>();

    /**
     * Set Playarea bounds.
     */
    public PlayManager() {
        left_x = 50;
        right_x = left_x + WIDTH;
        top_y = 50;
        bottom_y = top_y + HEIGHT;

        /*
         * Set the starting Mino position.
         */
        MINO_START_X = left_x + (WIDTH / 2) - Block.SIZE;
        MINO_START_Y = top_y + Block.SIZE;

        /*
         * Set the nextMino position.
         */
        NEXTMINO_X = right_x + 175;
        NEXTMINO_Y = top_y + 500;

        /*
         * Pick the nextMino and associate it the "Next Mino" position.
         */ 
        nextMino = pickMino();
        nextMino.setXY(NEXTMINO_X, NEXTMINO_Y);

        /*
         * Pick the first Mino and associate it the starting position.
         */ 
        currentMino = pickMino();
        currentMino.setXY(MINO_START_X, MINO_START_Y);
    }

    /**
     * Method that select a {@link Random} {@link Mino} and returns it
     * 
     * @return mino - a random {@link Mino}.
     */
    public Mino_Impl pickMino() {
        Mino_Impl mino = null;
        int i = new Random().nextInt(7);

        switch (i) {
            case 0:
                mino = new Mino_L1();
                break;
            case 1:
                mino = new Mino_L2();
                break;
            case 2:
                mino = new Mino_Square();
                break;
            case 3:
                mino = new Mino_Bar();
                break;
            case 4:
                mino = new Mino_T();  
                break;
            case 5:
                mino = new Mino_Z1();
                break;
            case 6:
                mino = new Mino_Z2();
                break;
        }
        return mino;
    }

    /**
     * Method that put the currentMino in staticBlocks.
     * If the game is not over
     * put the next {@link Mino} in the starting position:
     */
    public void update() {
        /*
         * Check if the currentMino is active
         */
        if (currentMino.active == false) {
            /*
             * If the mino is not active put in the staticBlocks
             */
            staticBlocks.add(currentMino.b[0]);
            staticBlocks.add(currentMino.b[1]);
            staticBlocks.add(currentMino.b[2]);
            staticBlocks.add(currentMino.b[3]);

            /*
             * Check gameover
             */
            if (currentMino.b[0].x == MINO_START_X && 
                currentMino.b[0].y == MINO_START_Y) {
                /*
                 * If the currentMino x and y are the same of se statring position
                 * it means that the game is over.
                 */
                gameOver = true;
                GamePanel.music.stop();
                GamePanel.soundEffect.play(2, false);
            }

            currentMino.deactivating = false;

            /*
             * Raplace the currentMino with the neaxtMino.
             */
            currentMino = nextMino;
            currentMino.setXY(MINO_START_X, MINO_START_Y);
            nextMino = pickMino();
            nextMino.setXY(NEXTMINO_X, NEXTMINO_Y);

            /*
             * Check if some lines can be deleted. 
             */
            checkDelete();
        } else {
            currentMino.update();
        }
    }

    /**
     * Check if some lines can be deleted and update the score.
     */
    public void checkDelete() {
        int x = left_x;
        int y = top_y;
        int blockCount = 0;
        int lineCount = 0;
    
        /*
         * Count the number of staticBlocks. 
         */ 
        while (x < right_x && y < bottom_y) {
            for (int i = 0; i < staticBlocks.size(); i++) {
                if (staticBlocks.get(i).x == x && staticBlocks.get(i).y == y) {
                    blockCount++;
                }
            }
    
            x += Block.SIZE;
    
            if (x == right_x) {
                /*
                 * If blockCount is equal to 12 means a line is full
                 * and must be deleted.
                 */
                if (blockCount == 12) {
    
                    /*
                     * Remove all blocks at this y-coordinate
                     */
                    for (int i = staticBlocks.size() - 1; i > -1; i--) {
                        if (staticBlocks.get(i).y == y) {
                            staticBlocks.remove(i);
                        }
                    }
    
                    lineCount++;
                    lines++;
    
                    /*
                     * Increase the level and the drop speed of the blocks. 
                     */
                    if (lines % 10 == 0 && dropInterval > 1) {
                        level++;
                        if (dropInterval > 10) {
                            dropInterval -= 10;
                        } else {
                            dropInterval -= 1;
                        }
                    }
    
                    /*
                     * Shift down the remaining Block after a line deleted.
                     */
                    for (int i = 0; i < staticBlocks.size(); i++) {
                        if (staticBlocks.get(i).y < y) {
                            staticBlocks.get(i).y += Block.SIZE;
                        }
                    }
                }
    
                blockCount = 0;
                x = left_x;
                y += Block.SIZE;
            }
        }

        /*
         * Increase the score.
         */
        if (lineCount > 0) {
            GamePanel.soundEffect.play(1, false);
            int singleLineScore = 10 * level;
            score += singleLineScore * lineCount;
        }
    }

    /**
     * Method that draw graphic elements.
     * 
     * @param g2 {@link Graphics2D} element that rappresent the Game Window.
     */
    public void draw(Graphics2D g2) {
        /*
         * Draw the PlayArea.
         */        
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(4f));
        g2.drawRoundRect(left_x - BORDER, 
                         top_y + TITLE_HEIGHT - BORDER, 
                         WIDTH + BORDER * 2, 
                         HEIGHT + BORDER * 2, 
                         25,
                         25);

        /*
         * Draw the nextMino window.
         */
        int x = right_x + 130;
        int y = bottom_y - 200;
        g2.drawRoundRect(x, y + TITLE_HEIGHT, 200, 200, 25, 25);
        g2.setFont(new Font("Arial", Font.PLAIN, 30));
        g2.drawString("NEXT", x + 60, y + TITLE_HEIGHT + 60);

        /*
         * Draw Scoreboard
         */
        g2.drawRoundRect(x, top_y + TITLE_HEIGHT, 250, 300, 25, 25);
        x += 40;
        y = top_y + TITLE_HEIGHT + 90;
        g2.drawString("LEVEL:  " + level, x, y);
        y += 70;
        g2.drawString("LINES:  " + lines, x, y);
        y += 70;
        g2.drawString("SCORE:  " + score, x, y);
        y += 70;

        /*
         * Draw Game Title.
         */
        x = WIDTH/4;
        y = top_y + 30;
        g2.setColor(Color.white);
        g2.setFont(new Font("Verdana", Font.ITALIC, 60));
        g2.drawString("Alex - Raul - Tommy", x + 20, y);
        g2.setFont(new Font("Verdana", Font.BOLD, 80));
        g2.drawString("TETRIS", x * 2 + 60, y + 100);

        /**
         * Draw currentMino.
         */
        if (currentMino != null) {
            currentMino.draw(g2);
        }

        /**
         * Draw nextMino.
         */
        nextMino.draw(g2, 40);

        /**
         * Draw staticBlocks.
         */
        for (int i = 0; i < staticBlocks.size(); i++) {
            staticBlocks.get(i).draw(g2);
        }

        /**
         * Draw effects.
         */
        if (effectCounterOn) {
            effectCounter++;

            g2.setColor(Color.red);
            for (int i = 0; i < effectY.size(); ++i) {
                g2.fillRect(left_x, 
                            effectY.get(i) + PlayManager.TITLE_HEIGHT, 
                            WIDTH, 
                            Block.SIZE);
            }

            if (effectCounter == 10) {
                effectCounterOn = false;
                effectCounter = 0;
                effectY.clear();
            }
        }

        /**
         * Draw Pause.
         */
        g2.setColor(Color.red);
        g2.setFont(g2.getFont().deriveFont(50f));
        if (KeyHandler.pausePressed) {
            x = left_x + 70;
            y = top_y + 320;
            g2.drawString("PAUSED", x, y);
        }

        /**
         * Draw Gameover.
         */
        g2.setColor(Color.red);
        g2.setFont(g2.getFont().deriveFont(50f));
        if (gameOver) {
            x = left_x + 25;
            y = top_y + TITLE_HEIGHT + 320;
            g2.drawString("GAME OVER", x, y);
        }
    }
}
