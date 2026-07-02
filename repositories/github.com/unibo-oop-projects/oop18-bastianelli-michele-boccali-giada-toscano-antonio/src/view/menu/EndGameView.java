package view.menu;

import common.MsgStrings;
import controller.menu.Controller;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import view.GenericView;

public class EndGameView extends GenericView {

    private static final Double ROWS = 2.0;

    public EndGameView(final Controller c) {
        super(c);
        final GridPane pane = new GridPane();
        super.init(pane, 80.0, 180.0, 0.0, 0.0);

        final RowConstraints title = new RowConstraints();
        title.setPercentHeight(super.getDimension().getHeight() / ROWS);
        final RowConstraints options = new RowConstraints();
        options.setPercentHeight(super.getDimension().getHeight() / ROWS);
        pane.getRowConstraints().addAll(title, options);

        // buttons
        final VBox optionBtn = new VBox();

        final Label endLabel = new Label(MsgStrings.END_GAME);
        endLabel.setId("title");
        final Button menuButton = new MsgEventButton(this, MsgStrings.MENU).getButton();
        final Button restartBtn = new MsgEventButton(this, MsgStrings.RESTART).getButton();

        optionBtn.getChildren().addAll(restartBtn, menuButton);

        int i = 0;
        pane.add(endLabel, 0, i++);
        pane.add(optionBtn, 0, i++);
    }

}
