package mission;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import javax.swing.JFrame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.embed.swing.JFXPanel;
import model.Model;
import model.ModelImpl;
import model.mission.Mission;
import model.mission.MissionFactoryImpl;
import model.mission.MissionManager;
import model.player.JumpState;
import model.player.Player;
import model.statistic.Statistics;
import sound.SoundFactoryImpl;

class MissionTest {

    private static final double SCREEN_WIDTH = 854.0;
    private static final double SCREEN_HEIGHT = 480.0;
    private static final int REWARD_AMOUNT = 20;
    private static final int MAX_DISTANCE_MISSION = 1000;
    private static final int MIN_DISTANCE_MISSION = 200;
    private static final int MAX_COINS_TO_COLLECT_MISSION = 50;
    private static final int MIN_COINS_TO_COLLECT_MISSION = 20;
    private static final int MAX_JUMP_NUMBER_MISSION = 50;
    private static final int MIN_JUMP_NUMBER_MISSION = 20;

    private Model model;
    private MissionManager missionManager;
    private Statistics statistics;
    private Player player;

    @BeforeEach
    void init() {
        // Initialize JavaFX environment
        final JFrame frame = new JFrame();
        final JFXPanel jfxPanel = new JFXPanel();
        frame.add(jfxPanel);
        this.model = new ModelImpl(SCREEN_WIDTH, SCREEN_HEIGHT, new SoundFactoryImpl());
        this.missionManager = this.model.getMissionManager();
        this.statistics = this.model.getStatistics();
        this.player = this.model.getGameState().getPlayer();
    }

    @Test
    void testCreateMission() {
        final Optional<Mission> mission = this.missionManager.getMission();
        assertTrue(mission.isPresent());
        assertEquals(0, mission.get().getCounter());
    }

    @Test
    void testDistanceMission() {
        final Mission mission = new MissionFactoryImpl(this.model).createDistanceMission();
        assertFalse(mission.isCompleted());
        int distance = 0;
        while (distance < MAX_DISTANCE_MISSION && !mission.isCompleted()) {
            this.statistics.update();
            mission.updateCounter();
            distance = this.statistics.getDistance();
        }
        assertTrue(mission.isCompleted());
        assertTrue(mission.getCounter() >= MIN_DISTANCE_MISSION && mission.getCounter() <= MAX_DISTANCE_MISSION);
        assertEquals(this.statistics.getGameCoins() + REWARD_AMOUNT, mission.getReward() + this.statistics.getGameCoins());
    }

    @Test
    void testCoinMission() {
        final Mission mission = new MissionFactoryImpl(this.model).createCollectedCoinMission();
        assertFalse(mission.isCompleted());
        int coinCounter = 0;
        while (coinCounter < MAX_COINS_TO_COLLECT_MISSION && !mission.isCompleted()) {
            this.statistics.increaseCoin(1);
            mission.updateCounter();
            coinCounter++;
        }
        assertTrue(mission.isCompleted());
        assertTrue(mission.getCounter() >= MIN_COINS_TO_COLLECT_MISSION && mission.getCounter() <= MAX_COINS_TO_COLLECT_MISSION);
        assertEquals(this.statistics.getGameCoins() + REWARD_AMOUNT, mission.getReward() + this.statistics.getGameCoins());
    }

    @Test
    void testJumpMission() {
        final Mission mission = new MissionFactoryImpl(this.model).createNumberOfJumpMission();
        assertFalse(mission.isCompleted());
        int jumpCounter = 0;
        while (jumpCounter < MAX_JUMP_NUMBER_MISSION && !mission.isCompleted()) {
            this.player.jump();
            while (!this.player.getJumpState().equals(JumpState.NOT_JUMPING)) {
                this.player.updateJump();
            }
            mission.updateCounter();
            jumpCounter++;
        }
        assertTrue(mission.isCompleted());
        assertTrue(mission.getCounter() >= MIN_JUMP_NUMBER_MISSION && mission.getCounter() <= MAX_JUMP_NUMBER_MISSION);
        assertEquals(this.statistics.getGameCoins() + REWARD_AMOUNT, mission.getReward() + this.statistics.getGameCoins());
    }

}
