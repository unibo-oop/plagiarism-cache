package iuniversity.view;

import javafx.stage.Stage;

public interface FXView extends View {

    /**
     * 
     * @return the current stage.
     */
    Stage getStage();

    /**
     * 
     * @param stage the stage to which display scenes
     */
    void setStage(Stage stage);

}
