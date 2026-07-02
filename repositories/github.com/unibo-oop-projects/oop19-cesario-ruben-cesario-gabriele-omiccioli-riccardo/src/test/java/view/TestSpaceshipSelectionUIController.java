package view;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.api.FxRobotException;
import org.testfx.assertions.api.Assertions;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import view.arena.SpaceshipSelectionUIController;
import view.display.ScreenUtilities;

/**
 * Tests SpaceshipSelectionUIController class checking the correct response
 * of its graphic components to the inputs and checking if the constraints
 * placed to start a game are correctly passed or not.
 */
@ExtendWith(ApplicationExtension.class)
public class TestSpaceshipSelectionUIController {

    /* Defines the id of the view component representing the panel where the user can insert a name. */
    private static final String PLAYER_NAME_PANEL_ID = "#playerNamePanel";
    /* Defines the id of the view component representing the image of one of the available spaceship. */
    private static final String FIRST_SELECTION_ITEM_ID = "#firstSelectionItem";
    /* Defines the id of the view component representing the image of one of the available spaceship. */
    private static final String SECOND_SELECTION_ITEM_ID = "#secondSelectionItem";
    /* Defines the id of the view component representing the image of one of the available spaceship. */
    private static final String THIRD_SELECTION_ITEM_ID = "#thirdSelectionItem";
    /* Defines the id of the view component which allows the user to start a game. */
    private static final String START_GAME_ITEM_ID = "#startGame";

    @Start
    public final void start(final Stage stage) throws IOException {
        final FXMLLoader fxmlLoader = new FXMLLoader(ClassLoader.getSystemResource("layouts/spaceshipSelection.fxml"));
        stage.setScene(new Scene(fxmlLoader.load()));
        //Set a convenient resolution available for all computers.
        ScreenUtilities.setResolution(1_600, 900);
        stage.setWidth(ScreenUtilities.getCurrentWidth());
        stage.setHeight(ScreenUtilities.getCurrentHeight());
        stage.show();
        stage.toFront();
        ((SpaceshipSelectionUIController) fxmlLoader.getController()).draw();
    }

    @Test
    public void testCorrectWindow(final FxRobot robot) {
        /* FxRobotException must be thrown if an action is performed in a non existing node. */
        Assertions.assertThatExceptionOfType(FxRobotException.class).isThrownBy(() -> robot.clickOn("#nonExistingNode"));
        /* No Exception must be thrown if an action is performed in an existing node. */
        Assertions.assertThatCode(() -> robot.clickOn(PLAYER_NAME_PANEL_ID)).doesNotThrowAnyException();
        Assertions.assertThatCode(() -> robot.clickOn(FIRST_SELECTION_ITEM_ID)).doesNotThrowAnyException();
        Assertions.assertThatCode(() -> robot.clickOn(SECOND_SELECTION_ITEM_ID)).doesNotThrowAnyException();
        Assertions.assertThatCode(() -> robot.clickOn(THIRD_SELECTION_ITEM_ID)).doesNotThrowAnyException();
        Assertions.assertThatCode(() -> robot.clickOn(START_GAME_ITEM_ID)).doesNotThrowAnyException();
    }

    @Test
    public void testNameInputAndSpaceshipSelection(final FxRobot robot) {
        final TextField node = new FxRobot().lookup(PLAYER_NAME_PANEL_ID).queryAs(TextField.class);
        Assertions.assertThat(node.getPromptText()).isEqualTo("Insert your name here");
        /* This name is longer than 10 letters so it must not be accepted. */
        robot.clickOn(PLAYER_NAME_PANEL_ID).write("TooMuchLenghtName");
        /* Check if the node correctly take the input. */
        Assertions.assertThat(node.getText()).isEqualTo("TooMuchLenghtName");
        /* When this button is pressed the controls to the name typed and
           the selection of the ship are performed. */
        robot.clickOn(START_GAME_ITEM_ID);
        Assertions.assertThat(node.getPromptText()).isEqualTo("Please insert a valid name");
        /* This name contains a special character so it must not be accepted. */
        robot.clickOn(PLAYER_NAME_PANEL_ID).write("NameWith@");
        Assertions.assertThat(node.getText()).isEqualTo("NameWith@");
        robot.clickOn(START_GAME_ITEM_ID);
        Assertions.assertThat(node.getPromptText()).isEqualTo("Please insert a valid name");
        /* This name is valid so it must be accepted. */
        robot.clickOn(PLAYER_NAME_PANEL_ID).write("ValidName");
        Assertions.assertThat(node.getText()).isEqualTo("ValidName");
        robot.clickOn(START_GAME_ITEM_ID);
        /* No spaceship is selected so it must not start the game. */
        Assertions.assertThat(node.getPromptText()).isEqualTo("You must select your spaceship first");
        robot.clickOn(THIRD_SELECTION_ITEM_ID);
        robot.clickOn(START_GAME_ITEM_ID);
        /* No name is inserted so it must not start the game. */
        Assertions.assertThat(node.getPromptText()).isEqualTo("Please insert a valid name");
        Assertions.assertThatCode(() -> robot.clickOn(PLAYER_NAME_PANEL_ID).write("ValidName")).doesNotThrowAnyException();
    }

}
