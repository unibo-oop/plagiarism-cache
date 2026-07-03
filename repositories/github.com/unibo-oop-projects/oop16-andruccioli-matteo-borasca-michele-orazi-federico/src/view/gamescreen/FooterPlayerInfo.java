package view.gamescreen;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import model.player.PlayerInfo;
import view.gamescreen.events.InfoPlayerEvent;
import view.gamescreen.handlers.InfoPlayerListener;



/**
* 
* This class represent bottom row of gameScreen view. It contains:
* <ul>
* <li>Attacking state
* <li>Attacking dice
* <li>Battle confirmation button
* <li>Attacking dice
* <li>Defending state
* </ul>
*/
public class FooterPlayerInfo extends StackPane {

   private static final double WIDTH = Screen.getPrimary().getBounds().getWidth();
   private static final double HEIGHT = Screen.getPrimary().getBounds().getHeight() / 20;
   private static final double SPACING = 25;

   private static final String HEADER_STYLE = "hdr-label";
   private static final String CONTENT_STYLE = "cnt-label";

   private final Label playerName = new Label("");
   private final Rectangle playerColor = new Rectangle(24, 24);
   private final Label stateName = new Label("");
   private final Label stateTanks = new Label("");
   private final Label stateOwner = new Label("");
   private final Label tanksRemaining = new Label("");

   /**
    * 
    * Class constructor.
    * 
    */
   public FooterPlayerInfo() {
       final Label currentPlayer = new Label("Current Player:");
       final HBox playerInfoContainer = new HBox(SPACING);
       final Label pName = new Label("Name: ");
       final Label pColor = new Label("Color: ");
       final Label tanksToDeploy = new Label("Tanks: ");
       playerColor.setFill(javafx.scene.paint.Color.TRANSPARENT);
       playerInfoContainer.getChildren().addAll(pName, playerName, pColor, playerColor, tanksToDeploy, tanksRemaining);

       final Label selectedState = new Label("Selected State:");
       final HBox stateInfoContainer = new HBox(SPACING);
       final Label state = new Label("State: ");
       final Label owner = new Label("Owner: ");
       final Label tanks = new Label("Tanks: ");
       stateInfoContainer.getChildren().addAll(state, stateName, tanks, stateTanks, owner, stateOwner);
 
       this.setPrefHeight(HEIGHT);
       this.setMaxHeight(HEIGHT);
       this.setPrefWidth(WIDTH);
       this.setMaxWidth(WIDTH);

       playerInfoContainer.setPrefSize(WIDTH / 2, HEIGHT);
       stateInfoContainer.setPrefSize(WIDTH / 2, HEIGHT);
       playerInfoContainer.setAlignment(Pos.BOTTOM_LEFT);
       stateInfoContainer.setAlignment(Pos.BOTTOM_RIGHT);

       this.getChildren().addAll(currentPlayer, selectedState, stateInfoContainer, playerInfoContainer);
       StackPane.setAlignment(currentPlayer, Pos.TOP_LEFT);
       StackPane.setAlignment(selectedState, Pos.TOP_RIGHT);
       StackPane.setAlignment(playerInfoContainer, Pos.BOTTOM_LEFT);
       StackPane.setAlignment(stateInfoContainer, Pos.BOTTOM_CENTER);

       HBox.setHgrow(state, Priority.ALWAYS);
       HBox.setHgrow(tanks, Priority.ALWAYS);
       HBox.setHgrow(owner, Priority.ALWAYS);
       HBox.setHgrow(pName, Priority.ALWAYS);
       HBox.setHgrow(pColor, Priority.ALWAYS);
       HBox.setHgrow(tanksToDeploy, Priority.ALWAYS);
       HBox.setHgrow(stateName, Priority.SOMETIMES);
       HBox.setHgrow(stateTanks, Priority.SOMETIMES);
       HBox.setHgrow(stateOwner, Priority.SOMETIMES);
       HBox.setHgrow(playerName, Priority.SOMETIMES);
       HBox.setHgrow(playerColor, Priority.SOMETIMES);
       HBox.setHgrow(tanksRemaining, Priority.SOMETIMES);

       this.addEventHandler(InfoPlayerEvent.PLAYER_INFO_UPDATE, new InfoPlayerListener());
 
       currentPlayer.getStyleClass().add(HEADER_STYLE);
       selectedState.getStyleClass().add(HEADER_STYLE);
       tanksToDeploy.getStyleClass().add(CONTENT_STYLE);
       state.getStyleClass().add(CONTENT_STYLE);
       tanks.getStyleClass().add(CONTENT_STYLE);
       owner.getStyleClass().add(CONTENT_STYLE);
       stateName.getStyleClass().add(CONTENT_STYLE);
       stateTanks.getStyleClass().add(CONTENT_STYLE);
       stateOwner.getStyleClass().add(CONTENT_STYLE);
       pName.getStyleClass().add(CONTENT_STYLE);
       pColor.getStyleClass().add(CONTENT_STYLE);
       playerName.getStyleClass().add(CONTENT_STYLE);
       tanksRemaining.getStyleClass().add(CONTENT_STYLE);
   }

   /**
    * 
    * Update player name text with a given name.
    * 
    * @param player
    *              Text to be added to the Label.
    * 
    */
   public void updatePlayerInfo(final PlayerInfo player) {
       playerName.setText(player.getName());
       playerColor.setFill(player.getColor().getPaint());
   }

   /**
    * 
    * Update state name text with a given string.
    * 
    * @param state
    *              Text to be added to the Label.
    * 
    */
   public void updateStateName(final String state) {
       stateName.setText(state);
   }

   /**
    * 
    * Update state owner name text with a given string.
    * 
    * @param owner
    *              Text to be added to the Label.
    * 
    */
   public void updateOwnerName(final String owner) {
       stateOwner.setText(owner);
   }

   /**
    * 
    * Update state tanks number with a given int.
    * 
    * @param tanks
    *              Text to be added to the Label.
    * 
    */
   public void updateTanksNumber(final int tanks) {
       stateTanks.setText(String.valueOf(tanks));
   }
 
   /**
    * 
    * Rest all info about a state.
    * 
    */
   public void resetStateInfo() {
       this.updateOwnerName("");
       this.updateStateName("");
       this.stateTanks.setText("");
   }
 
   /**
    * 
    * Update remaining tanks.
    * 
    */
   public void updateRemainingTanks() {
       this.tanksRemaining.setText(String.valueOf(Integer.parseInt(this.tanksRemaining.getText()) - 1));
   }

   /**
    * 
    * Update remaining tanks.
    * 
    * @param tanksToDeploy
    *                       Number of tanks to be deployed in this turn.
    * 
    */
   public void setRemainingTanks(final int tanksToDeploy) {
       this.tanksRemaining.setText(String.valueOf(tanksToDeploy));
   }
}
