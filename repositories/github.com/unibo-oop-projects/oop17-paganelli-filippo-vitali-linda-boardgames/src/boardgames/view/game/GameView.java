package boardgames.view.game;

import java.util.Map;

import javax.swing.JButton;

import boardgames.model.board.Box;

/**
 * 
 *
 */
public interface GameView {

    /**
     * 
     */
    void drawGraphics();

    /**
     * @param from 
     * @param to 
     * @param isEated 
     */
    void drawMove(Box from, Box to, boolean isEated);

    /**
     * @param b 
     */
    void setMovement(JButton b);
    


    /**
     * @param jb 
     */
    void lightUpPossibleMoves(JButton jb);

    /**
     * 
     */
    void sendPieceToGraveyard();

    /**
     * 
     */
    void updateCheckStatus();

    /**
     * 
     */
    void updateCheckMateStatus();

    /**
     * 
     */
    void updatePromotionStatus();

}
