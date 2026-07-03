package view.Items;

import controller.ControllerImp;


import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import utilities.Players;
import view.Utilities.Utilities;
import view.Menu.OptionsMenu;
import view.Utilities.ScreenDimension.ScreenDim;
import view.*;

/**
 * Class that contains the timer labels and the label that shows the actual player.
 * Author: Giulia Maglieri
 *
 */
public class TopBox {
    private static TopBox tb;
    private Scene scene;
    private HBox topBox = new HBox(180);
    private Label labelWhite = new Label(" ");
    private Label labelBlack = new Label(" ");
    private Label turn = new Label();
    /**
     * 
     * @param title String
     * @return Node
     * @throws Exception Exception
     */
        public Node display(final String title) throws Exception {
            labelWhite.setAlignment(Pos.TOP_CENTER);
            labelBlack.setAlignment(Pos.TOP_RIGHT);
            topBox.setPrefHeight(Utilities.HBOX_HEIGHT);
            turn.setAlignment(Pos.TOP_LEFT);
            topBox.setId("top-box");
            //topBox.getChildren().add(turn);
            //if(ControllerImp.getLog().getTime()){
            topBox.getChildren().addAll(turn);
            if (ControllerImp.getLog().getTime()) {
                topBox.getChildren().addAll(labelWhite, labelBlack);
              }
            //}
            scene = new Scene(topBox, ScreenDim.getWidth(), Utilities.HBOX_HEIGHT);
      scene.getStylesheets().addAll(TopBox.class.getResource("/view/"+OptionsMenu.getLog().boardChoice()+"Board.css").toExternalForm());
            //scene.getStylesheets().addAll(this.getClass().getResource("blueBoard.css").toExternalForm());

            return topBox;
            }
     /**
      * @param s the string received from the controller
      */
     public void setTextWhite(final String s) {
             labelWhite.setText("Timer White:" + s);
     }
     /**
      * @param s the string received from the controller
      */
     public void setTextBlack(final String s) {
             labelBlack.setText("Timer Black:" + s);
     }
    /**
      * @param p the actual player received from the controller
      * 
      */
     public void refresh(final Players p) {
         if (p.equals(Players.playerWhite)) {
             turn.setText("ActualPlayer: White");
         } else {
             turn.setText("ActualPlayer: Black");
         }
  }
     /**
      * 
      * @return TopBox
      */
     public static synchronized TopBox getLog() {
         if (tb == null) {
             tb = new TopBox();
         }
             return tb;
     }
}
