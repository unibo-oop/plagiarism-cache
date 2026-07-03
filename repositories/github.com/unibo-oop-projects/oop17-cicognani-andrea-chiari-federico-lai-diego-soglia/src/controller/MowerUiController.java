package controller;

import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.app.listener.StateListener;
import com.almasb.fxgl.ui.ProgressBar;
import com.almasb.fxgl.ui.UIController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import launcher.MowerApp;

/**
 * Class for user interface
 *
 * @author Daniele
 *
 *
 */
public class MowerUiController implements UIController, StateListener {

    @FXML
    private Pane root;

    private ProgressBar timeBar;
    private ProgressBar percentBar;

    @FXML
    private Label labelScore;
    @FXML
    private Label labelLives;
    @FXML
    private Label labelWeed;

    /**
     * 
     * @return the label lableWeed
     * 
     */

    public Label getLabelWeed() {
        return labelWeed;
    }
    
    /**
     * 
     * @return the label lableScore
     * 
     */


    public Label getLabelScore() {
        return labelScore;
    }
    
    /**
     * 
     * @return the label lableLives
     * 
     */


    public Label getLabelLives() {
        return labelLives;
    }
    
    /**
     * method for initialization and creation of in-game UI
     *
     */

    @Override
    public void init() {
        timeBar = new ProgressBar(false);
        timeBar.setHeight(20);
        timeBar.setTranslateX(300);
        timeBar.setTranslateY(10);
        timeBar.setRotate(0);
        timeBar.setFill(Color.BLUE);
        timeBar.setLabelVisible(false);
        timeBar.setMaxValue(MowerApp.getTimePerLevel());
        timeBar.setMinValue(0);
        timeBar.currentValueProperty().bind(FXGL.getApp().getGameState().intProperty("gas"));

        percentBar = new ProgressBar(false);
        percentBar.setHeight(20);
        percentBar.setTranslateX(700);
        percentBar.setTranslateY(210);
        percentBar.setRotate(-90);
        percentBar.setFill(Color.GREEN);
        percentBar.setLabelVisible(false);
        percentBar.setMinValue(0);
        percentBar.currentValueProperty().bind(FXGL.getApp().getGameState().intProperty("weedtiles"));

        root.getChildren().addAll(timeBar, percentBar);

        if (MowerApp.gameMode){

        	labelLives.setVisible(false);

        }

        labelScore.setFont(FXGL.getUIFactory().newFont(15));
        labelLives.setFont(FXGL.getUIFactory().newFont(30));
        labelWeed.setFont(FXGL.getUIFactory().newFont(15));

    }
    
    /**
     * 
     * @return the ProgressBar percentBar
     * 
     */

    public ProgressBar getPercentBar(){

    	return percentBar;

    }
    
    /**
     * 
     * @return the label lableLives
     * 
     */


    @Override
    public void onUpdate(double tpf) {

    }
}