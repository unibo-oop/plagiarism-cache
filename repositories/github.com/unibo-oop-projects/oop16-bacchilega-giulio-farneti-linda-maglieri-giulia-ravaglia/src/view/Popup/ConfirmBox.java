package view.Popup;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import view.BoardLayout;
import view.Utilities.Utilities;
/**
 * 
 * Author: Giulia Maglieri.
 *
 */
public final class ConfirmBox {
 /* with this class i create a comunication between the two windows created*/
 private static boolean answer;
 
 private ConfirmBox() { }
 /**
  * 
  * @param title String
  * @param message String 
  * @return boolean
  */
 public static boolean display(final String title, final String message) {
     Stage window = new Stage();
         window.initModality(Modality.APPLICATION_MODAL);
         VBox layout = new VBox(10);
         Scene scene = new Scene(layout, Utilities.POPUP_WIDTH, Utilities.POPUP_HEIGHT);
         window.setTitle(title);
         Button y = new Button("yes");
         y.setPrefSize(Utilities.BTN_WIDTH, Utilities.BTN_HEIGHT);
         Button n = new Button("no");
         n.setPrefSize(Utilities.BTN_WIDTH, Utilities.BTN_HEIGHT);
         Label label = new Label();
         label.setText(message);
         layout.setAlignment(Pos.CENTER);
         layout.getChildren().addAll(label, y, n);
         y.setOnAction(e -> {
             answer = true;
             window.close();
             closeProgram();
             BoardLayout.getLog().getStage().setOnCloseRequest(e1 -> {
                 e1.consume();
                 closeProgram();
                 BoardLayout.getLog().getStage().close();
             });
         });
         n.setOnAction(e -> {
             answer = false;
             window.close();
         });
         window.setScene(scene);
         scene.getStylesheets().addAll(ConfirmBox.class.getResource("/view/confirm.css").toExternalForm());
         window.initModality(Modality.APPLICATION_MODAL);
         window.initStyle(StageStyle.UNDECORATED);
         window.showAndWait();
         return answer;
 }
     /**
      * 
      */
      private static void closeProgram() {
          if (answer) {
                  BoardLayout.getLog().getStage().close();
              }
       }
    }
