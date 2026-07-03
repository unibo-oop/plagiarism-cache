package view;
import java.util.HashMap;


import java.util.List;
import java.util.Map;
import java.util.Set;

import controller.ControllerImp;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.chessboard.Chessboard;
import model.utilities.pawns.Pawn;
import model.utilities.pawns.PawnTypes;
import utilities.Pair;
import utilities.Players;
import view.Items.ChoiceBox;
import view.Items.TopBox;
import view.Menu.MainMenu;
import view.Menu.OptionsMenu;
import view.Popup.ChessBox;
import view.Popup.TimeUp;
import view.Popup.VictoryPanel;
import view.Utilities.Utilities;
/**
 * 
 * Author: Giulia Maglieri.
 *
 */
public class Chess implements ChessInterface  {
    private static Chess  chess = new Chess();
    private Scene scene;
    private Map<Pair<Integer, Integer>, Button> coordinateMap;
    private Map<Button, Pair<Integer, Integer>> button;
    private ChoiceBox choice = new ChoiceBox();
    private Players actualPlayer = null;
    private MainMenu main;
    private StackPane s;
    private GridPane g;
    private String style;
    private DropShadow shadow;
    /**
     * 
     */
      public Chess() {
          main = new MainMenu();
          coordinateMap = new HashMap<>();
          button = new HashMap<>();
          s = new StackPane();
          g = new GridPane();
          shadow = new DropShadow();
          scene = new Scene(s, Utilities.WIDTH_CHESSBOARD, Utilities.HEIGHT_CHESSBOARD);
      }
    /**
     * 
     * @param title String
     * @return Node
     */
    public Node display(final String title) {
            g.setPrefSize(Utilities.WIDTH_CHESSBOARD, Utilities.HEIGHT_CHESSBOARD);
            for (int column = (int) (Utilities.NUMBER_OF_TILE - 1); column >= 0; column--) {
                for (int row = 0; row < Utilities.NUMBER_OF_TILE; row++) {
                    Button b = new Button();
                    if ((row + column) % 2 == 0) {
                        b.setId("dark-chess-button");
                    } else {
                        b.setId("light-chess-button");
                    }
                    Pair<Integer, Integer> p = new Pair<Integer, Integer>(row, column);
                    button.put(b, p);
                    this.coordinateMap.put(p, b);
                    b.setPrefSize(Utilities.WIDTH_TILE, Utilities.HEIGHT_TILE);
                    g.add(b, row, (int) (Utilities.NUMBER_OF_TILE - column));
                    b.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(final ActionEvent event) {
                        ControllerImp.getLog().onChessboardHit(button.get(event.getSource()));
                    }
                    });
                    b.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(final MouseEvent event) { 
                        b.setEffect(shadow);
                        }
                    });
                    b.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(final MouseEvent event) { 
                            b.setEffect(null);
                            }
                    });
                }
            }
                    g.setAlignment(Pos.CENTER);
                    s.getChildren().add(g);
                    scene.getStylesheets().addAll(BoardLayout.class.getResource(OptionsMenu.getLog().boardChoice() + "Board.css").toExternalForm());
                    this.setPawn();
                    return g;
                     }
                    /**
                     * method that delete the image from the current tile of the chessboard.
                     */
                    public void reset() {
                            for (Button b: this.coordinateMap.values()) {
                                b.setGraphic(null);
                            }
                    }
                    /**
                     * 
                     * @param map map of player and list of pawns received from the model.
                     */
                    public void setPawnsIntoChessBoard(final Map<Players, List<Pawn>> map) {
                        for (Players p : map.keySet()) {
                            for (Pawn pw : map.get(p)) {
                                this.coordinateMap.get(pw.getActualPosition()).setGraphic(this.getGraphic(pw.getPlayer(), pw.getType()));
                            }
                        }
                    }
                    /**
                     * 
                     */
                     public void setPawn() {
                         for (Players p : Chessboard.getLog().getMapOfPlayers().keySet()) {
                             for (Pawn pw : Chessboard.getLog().getMapOfPlayers().get(p)) {
                                 this.coordinateMap.get(pw.getActualPosition()).setGraphic(this.getGraphic(pw.getPlayer(), pw.getType()));
                             }
                         }
                     }
                     /**
                      * method that sets the new position or the selected pawn.
                      * @param p Pawn
                      * @param startPosition pair of integer
                      * @param endPosition pair of integer
                      */
                     public void setNextMove(final Pawn p, final Pair<Integer, Integer> startPosition, final Pair<Integer, Integer> endPosition) {
                         this.coordinateMap.get(startPosition).setGraphic(null);
                         this.coordinateMap.get(endPosition).setGraphic(this.getGraphic(p.getPlayer(), p.getType()));
                     }
                     /**
                      * method who remove the pawn from the chessBoard and put it into the removed pawns box.
                      * @param endPosition pair of integer
                      */
                        public void removePawn(final Pair<Integer, Integer> endPosition) {
                            this.coordinateMap.get(endPosition).setGraphic(null);
                         }
                        /**
                         * method for choosing the style of the pawns.
                         * @param s String
                         */
                        public void setPawnsStyle(final String s) {
                            this.style = s;
                        }
                        /**
                         * method that return the pawn's style chosen.
                         * @return String
                         */
                         public String getPawnStyle() {
                             if (this.style == null) {
                                 this.style = "Standard";
                             }
                             return this.style;
                         }
                         /**
                          * method useful for the view for putting the images of the pawns into the chessboard.
                          * @param p players
                          * @param type
                          * @return imageView
                          */
                         private ImageView getGraphic(final Players p, final PawnTypes type) {
                             String v = "/Pawns/" + this.getPawnStyle() + "/";
                             String s = p.equals(Players.playerWhite) ? "w_" + type + ".png" : "b_" + type + ".png"; 
                             v = v.concat(s);
                             ImageView iv = new ImageView(new Image(Chess.class.getResource(v).toExternalForm()));
                             iv.setFitWidth(Utilities.DIMENSION);
                             iv.setFitHeight(Utilities.DIMENSION);
                             return iv;
                         }
                         /**
                          * method that shows all the threatened pawns.
                          * @param set set of pair of integer
                          */
                          public void showThreatenedPawns(final Set<Pair<Integer, Integer>> set) {
                              for (Pair<Integer, Integer> pair : set) {
                                  this.coordinateMap.get(pair).setId("button-threatenedPawns");
                              }
                          }
                          /**
                           * method that shows all the allowed moves for a chosen pawn.
                           * @param set1 set of pair of integer
                           */
                          public void showAllowedMoves(final Set<Pair<Integer, Integer>> set1) {
                              for (Pair<Integer, Integer> pair : set1) {
                                  this.coordinateMap.get(pair).setId("button-allowedMoves");
                              }
                          }
                          /**
                           * method useful for refresh the scene after every change or move.
                           */
                          public void repaintTable() {
                              for (int column = (int) (Utilities.NUMBER_OF_TILE - 1); column >= 0; column--) {
                                  for (int row = 0; row < Utilities.NUMBER_OF_TILE; row++) {
                                      Pair<Integer, Integer> pair = new Pair<>(row, column);
                                      if ((row + column) % 2 == 0) {
                                          this.coordinateMap.get(pair).setId("dark-chess-button");
                                      } else {
                                          this.coordinateMap.get(pair).setId("light-chess-button");
                                      }
                                  }
                              }
                          }
                          /**
                           * method that shows the check mate panel.
                           * @param p players
                           */
                           public void checkMate(final Players p) {
                               VictoryPanel.display("Victory Panel");
                           }
                           /**
                            * method that appears when the king is checkmated.
                            * @param p players
                            */
                           public void checkMated(final Players p) {
                               ChessBox.show();
                           }
                           /**
                            * method for choosing the new pawn when the simplePawn reach the limit of the chessboard.
                            */
                           public void changePawnWith() {
                               choice.show(this.actualPlayer);
                           }
                           /**
                            * method called when the time is up.
                            * @param p players
                            */
                           public void timeIsUp(final Players p) {
                               TimeUp.show();
                               BoardLayout.getLog().getStage().close();
                           }
                           /**
                            * method that shows the actual player.
                            * @param p players
                            * @return String
                            */
                           public String actualTurn(final Players p) {
                               TopBox.getLog().refresh(p);
                               if (p.equals(Players.playerWhite)) {
                                   this.actualPlayer = p;
                                   return  "White";
                               } else {
                                   this.actualPlayer = p;
                                   return "Black";
                               }
                           }
                           /**
                            * method that returns the actual player.
                            * @return players
                            */
                           public Players getActualPlayer() {
                               return this.actualPlayer;
                           }
                           /**
                            * methos that return the ches object.
                            * @return chess
                            */
                           public static Chess getLog() {
                               if (chess == null) {
                                   chess = new Chess();
                               }
                               return chess;
                           }
}
