package test.minigames.sizecount;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import model.DifficultyLevel;
import model.minigames.sizecount.IntegerOperation;
import model.minigames.sizecount.SizeCountModel;
import model.minigames.sizecount.SizeCountModelImpl;

class TestSizeCountModel {

    private SizeCountModel model;

    @Test
    public void testEasy() {
        this.model = new SizeCountModelImpl(DifficultyLevel.EASY);
        final List<IntegerOperation> operations = this.model.getOperations();
        final int max = operations.stream().mapToInt(e -> e.getResult()).max().getAsInt();
        Optional<IntegerOperation> op = Optional.empty();
        int c = 0;
        for (final IntegerOperation e : operations) {
            if (e.getResult() == max) {
                op = Optional.of(e);
                c++;
            }
        }
        if (c == operations.size()) {
            assertTrue(this.model.isCorrectAnswer("SAME"));
        } else {
            assertTrue(this.model.isCorrectAnswer(op.get().toString()));
        }
    }

    @Test
    public void testNormal() {
        this.model = new SizeCountModelImpl(DifficultyLevel.NORMAL);
        final List<IntegerOperation> operations = this.model.getOperations();
        final int max = operations.stream().mapToInt(e -> e.getResult()).max().getAsInt();
        Optional<IntegerOperation> op = Optional.empty();
        int c = 0;
        for (final IntegerOperation e : operations) {
            if (e.getResult() == max) {
                op = Optional.of(e);
                c++;
            }
        }
        if (c == operations.size()) {
            assertTrue(this.model.isCorrectAnswer("SAME"));
        } else {
            assertTrue(this.model.isCorrectAnswer(op.get().toString()));
        }
    }

    @Test
    public void testHard() {
        this.model = new SizeCountModelImpl(DifficultyLevel.HARD);
        final List<IntegerOperation> operations = this.model.getOperations();
        final int max = operations.stream().mapToInt(e -> e.getResult()).max().getAsInt();
        Optional<IntegerOperation> op = Optional.empty();
        int c = 0;
        for (final IntegerOperation e : operations) {
            if (e.getResult() == max) {
                op = Optional.of(e);
                c++;
            }
        }
        if (c == operations.size()) {
            assertTrue(this.model.isCorrectAnswer("SAME"));
        } else {
            assertTrue(this.model.isCorrectAnswer(op.get().toString()));
        }
    }

}
