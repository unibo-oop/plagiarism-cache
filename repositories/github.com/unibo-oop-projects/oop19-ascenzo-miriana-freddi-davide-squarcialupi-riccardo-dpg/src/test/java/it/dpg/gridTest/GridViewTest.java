package it.dpg.gridTest;

import it.dpg.maingame.controller.gamecycle.GameCycle;
import it.dpg.maingame.controller.grid.GridGenerator;
import it.dpg.maingame.model.character.Dice;
import it.dpg.maingame.model.grid.GridType;
import it.dpg.maingame.view.grid.GridView;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.util.HashMap;
import java.util.Map;

@ExtendWith(ApplicationExtension.class)
public class GridViewTest{

    GameCycle gc = Mockito.mock(GameCycle.class);

    GridView view;

    @Start
    public void start(Stage stage) {

        GridGenerator generator = new GridGenerator(GridType.GRID_ONE, gc);
        var pair = generator.generate();
        this.view = pair.getRight();
        view.setView();
    }

    @Test
    public void diceIsClickable(FxRobot robot) {
        Button dice = robot.lookup("#dice_button").queryAs(Button.class);
        //asserts that dice isn't clickable
        Assertions.assertTrue(dice.isDisabled());

        view.enableDiceThrow(Dice.D4);
        dice = robot.lookup("#dice_button").queryAs(Button.class);
        //asserts that dice is clickable
        Assertions.assertFalse(dice.isDisabled());
    }

    @Test
    public void playerPlacementIsCorrect(FxRobot robot){

        Map<Integer, Pair<Integer, Integer>> players = new HashMap<>();
        players.put(1, new ImmutablePair<>(1,0));

        view.updatePlayers(players);

        FlowPane pane = robot.lookup("#pane10").queryAs(FlowPane.class);
        Rectangle player = robot.lookup("#player1").queryAs(Rectangle.class);

        //asserts that player 1 is in the right Pane (first Cell)
        Assertions.assertTrue(pane.getChildren().contains(player));
    }

    @Test
    public void textIsCorrect(FxRobot robot) {
        //verifies that the label is correctly set in the view
        view.showText("this is a test");
        Label label = robot.lookup("#main_text").queryAs(Label.class);
        Assertions.assertEquals("this is a test", label.getText());
    }

}
