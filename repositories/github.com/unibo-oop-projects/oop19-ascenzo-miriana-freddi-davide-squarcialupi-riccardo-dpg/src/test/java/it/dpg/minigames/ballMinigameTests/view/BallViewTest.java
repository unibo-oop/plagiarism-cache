package it.dpg.minigames.ballMinigameTests.view;

import it.dpg.minigames.ballgame.controller.BallMinigameLevel;
import it.dpg.minigames.ballgame.controller.BallObserver;
import it.dpg.minigames.ballgame.view.BallView;
import it.dpg.minigames.ballgame.view.BallViewImpl;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ApplicationExtension.class)
public class BallViewTest {

    BallObserver observer = Mockito.mock(BallObserver.class);

    BallView view = new BallViewImpl(600, observer);

    @Start
    public void start(Stage primaryStage) {
        view.setupLevel(BallMinigameLevel.LEVEL1);
        view.setView();
    }

    @Test
    public void testView(FxRobot robot) throws InterruptedException {
        Circle c = robot.lookup("#ball_node").queryAs(Circle.class);
        assertEquals(Color.RED, c.getFill());
        assertEquals(18, c.getRadius());
        assertEquals(105, c.centerXProperty().getValue());
        assertEquals(525, c.centerYProperty().getValue());
        Text score = robot.lookup("#score_node").queryAs(Text.class);
        assertEquals("Score: 000", score.getText());
        Group g = robot.lookup("#group_node").queryAs(Group.class);
        view.setGo();
        TimeUnit.MILLISECONDS.sleep(50);
        TimeUnit.MILLISECONDS.sleep(50);
        Text go = robot.lookup("#go_node").queryAs(Text.class);
        assertTrue(g.getChildren().contains(go));
        view.removeGo();
        TimeUnit.MILLISECONDS.sleep(50);
        assertFalse(g.getChildren().contains(go));
        view.setReady();
        TimeUnit.MILLISECONDS.sleep(50);
        Text ready = robot.lookup("#ready_node").queryAs(Text.class);
        assertTrue(g.getChildren().contains(ready));
        view.removeReady();
        TimeUnit.MILLISECONDS.sleep(50);
        assertFalse(g.getChildren().contains(ready));
        view.setVictory();
        TimeUnit.MILLISECONDS.sleep(50);
        Text victory = robot.lookup("#victory_node").queryAs(Text.class);
        assertTrue(g.getChildren().contains(victory));
        view.setScore(666);
        TimeUnit.MILLISECONDS.sleep(50);
        assertEquals("Score: 666", score.getText());
        view.positionBall(50, 50);
        TimeUnit.MILLISECONDS.sleep(50);
        assertEquals(300, c.centerXProperty().getValue());
        assertEquals(300, c.centerYProperty().getValue());
    }
}
