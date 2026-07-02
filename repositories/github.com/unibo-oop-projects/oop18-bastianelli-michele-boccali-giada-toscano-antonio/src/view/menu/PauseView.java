package view.menu;

import common.MsgStrings;
import controller.menu.Controller;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import view.GenericView;

public class PauseView extends GenericView {

    private static final Double ROWS = 10.0;

    public PauseView(final Controller c) {
        super(c);
        final GridPane pane = new GridPane();
        super.init(pane, 80.0, 180.0, 0.0, 0.0);

        final RowConstraints title = new RowConstraints();
        title.setPercentHeight(super.getDimension().getHeight() / ROWS);
        final RowConstraints options = new RowConstraints();
        options.setPercentHeight(super.getDimension().getHeight() / ROWS * 2);
        pane.getRowConstraints().addAll(title, options);

        // buttons
        final Label titleLabel = new Label(MsgStrings.PAUSE);
        titleLabel.setId("title");

        final VBox optionBtn = new VBox();

        final Button menuBtn = new MsgEventButton(this, MsgStrings.MENU).getButton();
        final Button resumeBtn = new MsgEventButton(this, MsgStrings.RESUME).getButton();
        final Button audioBtn = new AudioEventButton(this).getButton();

        optionBtn.getChildren().addAll(menuBtn, resumeBtn, audioBtn);

        int i = 0;
        pane.add(titleLabel, 0, i++);
        pane.add(optionBtn, 0, i++);
    }

}
