package view.frames;

import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * The Ranking view.
 */
public class RankingFrame extends AbstractViewFrame {
    private GridPane mainPane = new GridPane();

    @Override
    public void start(final Stage newPrimaryStage) throws Exception {
        setStage(newPrimaryStage);
        setFrame();
        setScene(mainPane, SCREEN_WIDTH / 3, SCREEN_HEIGHT);
        setExitOperation();
    }

    @Override
    public void setFrame() {
        getStage().setTitle("Ranking");
        mainPane.setHgap(RANKINGHGAP);
        mainPane.add(new Text("Nome"), 1, 1);
        mainPane.add(new Text("Età"), 2, 1);
        getController().getRanking().forEach(e -> {
            mainPane.add(new Text(e.getFirst()), 1, getController().getRanking().indexOf(e) + 2);
            mainPane.add(new Text("" + e.getSecond().intValue()), 2, getController().getRanking().indexOf(e) + 2);
        });
        mainPane.setId("defaultBackGroundColor");
        mainPane.getChildren().forEach(e -> {
            if (GridPane.getRowIndex(e) == 1) {
                e.setId("coloredPacificoText");
                e.setStyle("-fx-font-size: " + RANKINGCOLUMNSFONTSIZE + "em;");
            } else {
                e.setStyle("-fx-font-size: " + RANKINGCELLSFONTSIZE + "em;");
            }
        });
        mainPane.setAlignment(Pos.TOP_CENTER);
        mainPane.getStylesheets().add(RankingFrame.class.getClassLoader().getResource("application.css").toExternalForm());
    }

    @Override
    public void clearStage() {
        getStage().getScene().getWindow().hide();
        getStage().close();
        getStage().setScene(null);
        mainPane.getChildren().clear();
    }

    @Override
    public void setExitOperation() {
        getStage().setOnCloseRequest(e -> {
            clearStage();
        });
    }
}
