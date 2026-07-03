package view;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import view.Items.PawnsRemoved;
import view.Items.TopBox;
import view.Utilities.ScreenDimension.ScreenDim;
/**
 * 
 * Author: Giulia Maglieri.
 *
 */
public class BoardLayout implements BoardInterface {
        private Stage stage;
        private Scene scene;
        private SideMenu verticalBox;
        private BorderPane b;
        private static BoardLayout board;
        private String color = "brown";
        /**
         * 
         */
        public void start() {
            stage = new Stage();
            b = new BorderPane();
            scene = new Scene(b, ScreenDim.getWidth(), ScreenDim.getHeight());
            verticalBox = new SideMenu();
            stage.setTitle("CHESSBOARD");
            b.setRight(verticalBox.display("sideMenu"));
            try {
                b.setCenter(Chess.getLog().display("chessBoard"));
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                b.setTop(TopBox.getLog().display("topBox"));
            } catch (Exception e) {
                e.printStackTrace();
            }
            b.setLeft(PawnsRemoved.getLog().getPane());
             if (this.color == null) {
                this.color = "brown";
            }
            String path = this.color.concat("Board.css");
            scene.getStylesheets().addAll(BoardLayout.class.getResource(path).toExternalForm());
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/Menu/chessIcon.png")));
            stage.setScene(scene);
            //stage.setResizable(false);
            //stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
        }
        /**
         * method for setting the board color.
         * @param s String
         */
            public void setColor(final String s) {
                this.color = s;
            }
            /**
             * method that return the BoardLayout object.
             * @return BoardLayout
             */
              public static BoardLayout getLog() {
                  if (board == null) {
                      board = new BoardLayout();
                  }
                  return board;
              }
              /**
               * return the primary stage.
               * @return Stage
               */
                  public Stage getStage() {
                      return this.stage;
                   }
}
