package maingame.menu.model;

import java.util.ArrayList;
import java.util.List;

import maingame.level.Difficulty;
import maingame.menu.menu.Option;

/**
 * Implementazione interfaccia Model.
 */
public class ModelMenuImpl implements ModelMenu {

    private int index;
    private boolean showCommand;
    private boolean showStats;
    private Difficulty diff;
    private final List<Option> optionEnum = new ArrayList<>();

    @Override
    public int getIndex() {
        return this.index;
    }

    @Override
    public void setIndex(final int value) {
        if (value >= 0 && value <= this.getNumOption()) {
            this.index = value;
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public boolean isShowCommand() {
        return this.showCommand;
    }

    @Override
    public void setShowCommand(final boolean show) {
        this.showCommand = show;
    }

    @Override
    public boolean isShowStats() {
        return this.showStats;
    }

    @Override
    public void setShowStats(final boolean show) {
        this.showStats = show;
    }

    @Override
    public Difficulty getDifficulty() {
        return this.diff;
    }

    @Override
    public void setDifficulty(final Difficulty difficulty) {
        this.diff = difficulty;
    }

    @Override
    public Option getOption(final int index) {
        if (index < optionEnum.size() && index >= 0) {
            return optionEnum.get(index);
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void addOption(final Option newOption) {
        this.optionEnum.add(newOption);
    }

    @Override
    public int getNumOption() {
        return this.optionEnum.size();
    }
}