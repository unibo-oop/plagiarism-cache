package view.menu;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import view.AbstractScene;
import view.View;
import view.ViewImpl;

/**
 * layout of optionMenu.
 */
public class OptionMenu extends AbstractScene {
    /**
     * @param width of the scene
     * @param height of the scene
     * @param v view reference
     */
    public OptionMenu(final double width, final double height, final View v) {
        super(new GridPane(), width, height);
        final GridPane pane = (GridPane) getRoot();
        final View view = v;
        final Button back = new Button("Back");
        final Label modLabel = new Label("Mod Spawing");
        final CheckBox modOpt = new CheckBox();
        final Label difLabel = new Label("Difficulty Level");
        final ChoiceBox<String> difOpt = new ChoiceBox<>();
        difOpt.getItems().addAll("EASY", "MEDIUM", "HARD");
        final Label fpsLabel = new Label("FPS");
        final ChoiceBox<String> fpsOpt = new ChoiceBox<>();
        fpsOpt.getItems().addAll("60", "30");
        final Button save = new Button("Save");
        pane.setAlignment(Pos.CENTER); 
        pane.add(modLabel, 0, 0);
        pane.add(modOpt, 2, 0);
        pane.add(difLabel, 0, 1);
        pane.add(difOpt, 2, 1);
        pane.add(fpsLabel, 0, 2);
        pane.add(fpsOpt, 2, 2);
        pane.add(back, 0, 4);
        pane.add(save, 2, 4);
        pane.setHgap(50);
        pane.setVgap(20);
        back.setOnAction(e -> view.changeScene(ViewImpl.GameScreen.MAINMENU));
        save.setOnAction(e -> System.out.println("Settings saved"));
}

    @Override
    public void initialize() {   }
}
