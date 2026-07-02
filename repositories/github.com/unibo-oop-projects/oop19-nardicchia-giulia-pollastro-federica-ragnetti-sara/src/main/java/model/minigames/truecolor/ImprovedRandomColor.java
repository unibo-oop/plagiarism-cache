package model.minigames.truecolor;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import utility.Pair;

/**
 * The improved random color algorithms.
 *
 */
public class ImprovedRandomColor extends RandomColor {

    private final DifficultyConfiguration difficultyConfig;
    private final List<Colors> colorList;
    private final List<Colors> colorListTmp = new LinkedList<>();
    private final List<Pair<Colors, Colors>> meanColorList = new LinkedList<>();
    private boolean statusMatchingColor;

    private final Function<List<Colors>, Colors> getAndRemoveColor = (list) -> {
        final Colors randomColor = super.getRandomColor(list);
        this.colorListTmp.remove(randomColor);
        return randomColor;
    };

    /**
     * Constructor for {@ImprovedRandomColor}.
     * 
     * @param difficulty
     *          the {@DifficultyConfiguration} selected
     */
    public ImprovedRandomColor(final DifficultyConfiguration difficulty) {
        this.changeProbabilityOfMatching();
        this.difficultyConfig = difficulty;
        this.colorList = Stream.of(Colors.values()).limit(difficultyConfig.getNumColor()).collect(Collectors.toList());
        this.copyTempList();
    }

    private void changeProbabilityOfMatching() {
        final Random rd = new Random();
        this.statusMatchingColor = rd.nextBoolean();
    }

    private List<Pair<Colors, Colors>> createRandomColorListMeanAndTrue(final StatusColor status) {
        final List<Pair<Colors, Colors>> list = status.equals(StatusColor.MEANCOLOR) ? this.meanColorList
                                                                                     : new LinkedList<>();
        final int numberOfColor = status.equals(StatusColor.MEANCOLOR) ? difficultyConfig.getMeanButton()
                                                                       : difficultyConfig.getTrueButton();
        IntStream.range(0, numberOfColor).forEach(
                i -> list.add(new Pair<Colors, Colors>(this.getXRandomColor(status), getYRandomColor(status))));
        return list;
    }

    private Colors getXRandomColor(final StatusColor status) {
        return status.equals(StatusColor.MEANCOLOR) ? this.getAndRemoveColor.apply(this.colorList)
                                                    : super.getRandomColor(this.colorList);
    }

    private Colors getYRandomColor(final StatusColor status) {
        if (status.equals(StatusColor.TRUECOLOR)) {
            if (this.statusMatchingColor) {
                this.statusMatchingColor = false;
                return this.getAndRemoveColor.apply(tempMeanColorList());
            } else {
                return super.getRandomColor(this.colorListTmp);
            }
        } else {
            return super.getRandomColor(this.colorList);
        }
    }

    private List<Colors> tempMeanColorList() {
        return this.meanColorList.stream().map(p -> p.getX()).collect(Collectors.toList());
    }

    private void copyTempList() {
        this.colorListTmp.clear();
        this.colorList.forEach((Colors color) -> this.colorListTmp.add(color));
    }

    @Override
    public final void initialize() {
        this.changeProbabilityOfMatching();
        this.copyTempList();
        this.meanColorList.clear();
    }

    @Override
    public final List<Pair<Colors, Colors>> getMeanColorList() {
        return createRandomColorListMeanAndTrue(StatusColor.MEANCOLOR);
    }

    @Override
    public final List<Pair<Colors, Colors>> getTrueColorList() {
        return createRandomColorListMeanAndTrue(StatusColor.TRUECOLOR);
    }
}
