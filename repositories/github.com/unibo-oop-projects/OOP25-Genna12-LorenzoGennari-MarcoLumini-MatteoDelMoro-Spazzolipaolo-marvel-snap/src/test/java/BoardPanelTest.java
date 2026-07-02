import static org.junit.jupiter.api.Assertions.*;

import java.awt.Dimension;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.marvelsnap.util.DeckType;
import com.marvelsnap.view.GamePanel;
import com.marvelsnap.controller.GameController;
import com.marvelsnap.model.Card;
import com.marvelsnap.model.Game;
import com.marvelsnap.model.BasicCard;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.timing.Condition;
import org.assertj.swing.timing.Pause;
import org.assertj.swing.edt.GuiActionRunner;

import javax.swing.*;

/**
 * Test class for BoardPanel and LocationPanel.
 */
public class BoardPanelTest {

    private Game testGame;
    private GamePanel testGamePanel;
    private JFrame testMainFrame;
    private FrameFixture testWindow;
    
    /**
     * Setup method called before the actual test. It creates a simulated game condition in which player one has a test card, that is
     * ready to be played. It also creates a FrameFixture, in order to make the test completely automatic.
     */
    @BeforeEach
    public void setUp() {
        this.testGame = new Game();

        this.testMainFrame = GuiActionRunner.execute(() -> {
            testGame.startGame("Player1", DeckType.AVENGERS, "Player2", DeckType.VILLAINS);
                
            Card testCard = new BasicCard(100, "Test", 0, 1, "Descrizione", "Nessuna");
            testGame.getPlayer1().getHand().add(testCard);

            testGamePanel = new GamePanel();
            new GameController(testGame, testGamePanel); // keeping a reference is unnecessary for this test.
            testGamePanel.getHandPanel().setHand(testGame.getPlayer1().getHand());

            JFrame frame = new JFrame();
            frame.add(testGamePanel);
            frame.setPreferredSize(new Dimension(1280, 720));
            frame.pack();
            return frame;
        });

        this.testWindow = new FrameFixture(this.testMainFrame);
        this.testWindow.show();
        this.testMainFrame.setSize(1280, 720);
        GuiActionRunner.execute(() -> {
        testMainFrame.toFront();
        testMainFrame.requestFocus();
        });
        Pause.pause(500);
        this.testWindow.robot().waitForIdle();
    }

    /**
     * Simulates a mouse click on the test card, in order to select it, then a click on the 
     * first location, in order to play the card. 
     */
    @Test
    public void testLocationClick() {
        testWindow.focus();
        this.testWindow.robot().waitForIdle();
        testWindow.panel("CardPanel100").click();
        this.testWindow.robot().waitForIdle();

        testWindow.panel("locationPanel0").click();
        this.testWindow.robot().waitForIdle();

        Pause.pause(new Condition("Attende l'inserimento effettivo della carta nella location") {
            @Override
            public boolean test() {
                return testGame.getLocations().getFirst().getCards(0).size() == 1;
            }
        }, 2000);

        assertEquals(1,testGame.getLocations().getFirst().getCards(0).size());
    }
}