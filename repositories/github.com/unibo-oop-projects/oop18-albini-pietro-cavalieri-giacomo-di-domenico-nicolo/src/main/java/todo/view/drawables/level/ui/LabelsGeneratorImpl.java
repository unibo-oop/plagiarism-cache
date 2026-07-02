package todo.view.drawables.level.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LabelsGeneratorImpl implements LabelsGenerator {
    private List<Integer> toSkip;
    private int current;

    public LabelsGeneratorImpl() {
        this.toSkip = new ArrayList<>();
        this.current = 1;
    }

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public Integer next() {
        // Find the first gap in the to skip and reuse it
        while (this.toSkip.contains(this.current)) {
            this.current++;
        }
        return this.current++;
    }

    @Override
    public void reset() {
        this.current = 1;
    }

    @Override
    public void setLabelsToSkip(final List<Integer> labels) {
        this.toSkip = new ArrayList<>(Objects.requireNonNull(labels));
    }
}
