package model.minigames.truecolor;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import utility.Pair;

/**
 * The simple random color algorithms.
 *
 */
public class SimpleRandomColor extends RandomColor {

    private final DifficultyConfiguration difficultyLevel;
    private List<Colors> colorList;

    /**
     * Constructor for {SimpleRandomColor}.
     * 
     * @param difficulty
     *          the {@DifficultyConfiguration} selected
     */
    public SimpleRandomColor(final DifficultyConfiguration difficulty) {
        this.difficultyLevel = difficulty;
        this.createListColorDifficulty(difficulty.getNumColor());
    }

    private void createListColorDifficulty(final int numberColor) {
        this.colorList = Stream.of(Colors.values()).limit(numberColor).collect(Collectors.toList());
    }

    private List<Pair<Colors, Colors>> createRandomColorList(final Integer numberBtn) {
        final List<Pair<Colors, Colors>> list = new LinkedList<>();
        for (int i = 0; i < numberBtn; i++) {
            list.add(new Pair<Colors, Colors>(this.getRandomColor(), this.getRandomColor()));
        }
        return list;
    }

    private Colors getRandomColor() {
        final Random rand = new Random();
        return this.colorList.get(rand.nextInt(this.colorList.size()));
    }

    @Override
    public final List<Pair<Colors, Colors>> getMeanColorList() {
       return createRandomColorList(difficultyLevel.getMeanButton());
    }

    @Override
    public final List<Pair<Colors, Colors>> getTrueColorList() {
        return createRandomColorList(difficultyLevel.getTrueButton());
    }

    @Override
    public void initialize() {
    }
}
