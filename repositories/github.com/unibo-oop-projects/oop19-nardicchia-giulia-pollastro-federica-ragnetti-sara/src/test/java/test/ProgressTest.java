package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.junit.jupiter.api.Test;

import model.score.Progress;
import model.score.ProgressImpl;
import view.TrainingArea;

/**
 * Class to test if {@link Progress} works correctly.
 *
 */
public class ProgressTest {

    private Progress progress;

    /**
     * Test average.
     */
    @Test
    public void testBasicAverage() {
        this.progress = new ProgressImpl();
        this.progress.addMiniGameScore(TrainingArea.ATTENTION, "True Color", 120);
        this.progress.addMiniGameScore(TrainingArea.ATTENTION, "True Color", 40);
        this.progress.addMiniGameScore(TrainingArea.ATTENTION, "True Color", 80);
        this.progress.addMiniGameScore(TrainingArea.BRAIN_SPEED, "One Way", 100);
        this.progress.addMiniGameScore(TrainingArea.BRAIN_SPEED, "One Way", 60);
        final int avarageAttention = (int) this.progress.getHistoryScore().get(TrainingArea.ATTENTION).entrySet().stream()
                .mapToInt(s -> s.getValue().getAverage()).average().getAsDouble();
        final int avarageBrainSpeed = (int) this.progress.getHistoryScore().get(TrainingArea.BRAIN_SPEED).entrySet().stream()
                .mapToInt(s -> s.getValue().getAverage()).average().getAsDouble();
        assertEquals(avarageAttention, this.progress.getAverageTrainingAreaScore().get(TrainingArea.ATTENTION));
        assertEquals(avarageBrainSpeed, this.progress.getAverageTrainingAreaScore().get(TrainingArea.BRAIN_SPEED));
    }

    /**
     * Test scores' history.
     */
    @Test
    public void testHistoryScore() {
        final Date date = new Date();
        final DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT, Locale.ITALY);
        final String s = dateFormat.format(date);

        this.progress = new ProgressImpl();
        this.progress.addMiniGameScore(TrainingArea.MEMORY, "Perilous Path", 60);
        this.progress.addMiniGameScore(TrainingArea.MEMORY, "Perilous Path", 50);
        this.progress.addMiniGameScore(TrainingArea.REASONING, "Size Count", 110);
        this.progress.addMiniGameScore(TrainingArea.REASONING, "Size Count", 90);
        assertEquals(55, this.progress.getHistoryScore().get(TrainingArea.MEMORY).get("Perilous Path").getMiniGameHistory().get(s));
        assertEquals(100, this.progress.getHistoryScore().get(TrainingArea.REASONING).get("Size Count").getMiniGameHistory().get(s));
    }
}
