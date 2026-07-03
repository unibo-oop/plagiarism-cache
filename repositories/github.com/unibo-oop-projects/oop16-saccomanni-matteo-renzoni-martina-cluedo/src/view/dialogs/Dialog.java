package view.dialogs;

import java.awt.Toolkit;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import view.ViewImpl;

/**
 * Interface of Dialog windows.
 */
public class Dialog {
    private static final int COL_W = 33;
    private static final int PADDING = 20;
    private static final int COMBO_W = 150;
    private static final double SCREEN_H = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    private static final double SCREEN_W = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    private static final double IMG_H = SCREEN_H * 0.25;
    private static final double IMG_CARD = SCREEN_H * 0.20;
    private static final double STAGE_H = SCREEN_H * 0.4;
    private static final double MIN_STAGE_W = SCREEN_W * 0.25;
    private static final double MAX_STAGE_W = SCREEN_W * 0.5;

    /**
     * Getter for SCREEN_H.
     * 
     * @return SCREEN_H.
     */
    protected double getScreenH() {
        return SCREEN_H;
    }

    /**
     * Getter for SCREEN_W.
     * 
     * @return SCREEN_W.
     */
    protected double getScreenW() {
        return SCREEN_W;
    }

    /**
     * Getter for COL_W.
     * 
     * @return COL_W.
     */
    protected int getColW() {
        return COL_W;
    }

    /**
     * Getter for PADDING.
     * 
     * @return PADDING.
     */
    protected int getPadding() {
        return PADDING;
    }

    /**
     * Getter for COMBO_W.
     * 
     * @return COMBO_W.
     */
    protected int getComboW() {
        return COMBO_W;
    }

    /**
     * Getter for IMG_H.
     * 
     * @return IMG_H.
     */
    protected double getImgH() {
        return IMG_H;
    }

    /**
     * Getter for the dimension of the cards in CardsDialog.
     * 
     * @return IMG_CARD.
     */
    protected double getImgCard() {
        return IMG_CARD;
    }

    /**
     * Getter for STAGE_H.
     * 
     * @return STAGE_H.
     */
    protected double getStageH() {
        return STAGE_H;
    }

    /**
     * Getter for MIN_STAGE_W.
     * 
     * @return MIN_STAGE_W.
     */
    protected double getMinStageW() {
        return MIN_STAGE_W;
    }

    /**
     * Getter for MAX_STAGE_W.
     * 
     * @return MAX_STAGE_W.
     */
    protected double getMaxStageW() {
        return MAX_STAGE_W;
    }

    /**
     * Method to set the stage in the dialogs to remove redundant code.
     * 
     * @return the stage with settings.
     */
    protected Stage setStage() {
        final Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.centerOnScreen();
        stage.initOwner(ViewImpl.getPrimaryStage());
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        return stage;
    }
}