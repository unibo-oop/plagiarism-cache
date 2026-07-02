package test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import view.ScanMiniGames;
import view.TrainingArea;

class ScanMiniGamesTest {

    @Test
    public void test() {
        assertTrue(ScanMiniGames.getMiniGames(TrainingArea.ATTENTION).contains("True Color"));
        assertTrue(ScanMiniGames.getMiniGames(TrainingArea.BRAIN_SPEED).contains("One Way"));
        assertTrue(ScanMiniGames.getMiniGames(TrainingArea.MEMORY).contains("Perilous Path"));
        assertTrue(ScanMiniGames.getMiniGames(TrainingArea.REASONING).contains("Size Count"));
        assertTrue(ScanMiniGames.getMiniGames(TrainingArea.VISUAL_SKILL).contains("Color Gama"));
    }
}
