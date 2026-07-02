package view;

import controller.HighscoreManager;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Pair;
import model.GameMode;

public class HighscoreView extends AbstractMenuView {

    private static final String TITLE = "Highscore";
    private static final Font SCORE_FONT = new Font("Serif", 18);
    private static final Insets SCORE_PADDING = new Insets(10);

    public HighscoreView(BorderPane layout, Stage stage) {
        super(layout, stage);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Node centerPane() {
        TabPane tab = new TabPane();
        tab.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);

        for (GameMode gm : GameMode.values()) {
            Tab t = new Tab(gm.toString());
            ScrollPane scroll = new ScrollPane();
            GridPane grid = new GridPane();
            scroll.setContent(grid);
            scroll.setHbarPolicy(ScrollBarPolicy.NEVER);
            t.setContent(scroll);

            grid.setAlignment(Pos.CENTER);

            int i = 0;
            for (Pair<String, Integer> pair : HighscoreManager.getInstance().getScore(gm)) {
                Label l1 = new Label(pair.getKey().toString());
                setLabel(l1);
                Label l2 = new Label(pair.getValue().toString());
                setLabel(l2);

                grid.add(l1, 0, i);
                grid.add(l2, 1, i);
                i++;
            }
            tab.getTabs().add(t);
        }

        return tab;
    }

    @Override
    protected final String getTitle() {
        return TITLE;
    }

    private void setLabel(final Label l) {
        l.setFont(SCORE_FONT);
        l.setTextFill(Color.BLUEVIOLET);
        l.setPadding(SCORE_PADDING);
        l.setPrefWidth(this.stage.getWidth() / 2);
        l.setAlignment(Pos.CENTER);
    }
}
