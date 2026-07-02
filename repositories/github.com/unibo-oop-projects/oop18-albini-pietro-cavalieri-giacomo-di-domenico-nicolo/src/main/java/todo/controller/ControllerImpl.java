package todo.controller;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import todo.controller.clipboard.ClipboardProvider;
import todo.model.level.Level;
import todo.model.level.LevelsStorage;
import todo.model.level.LevelsStorageImpl;

public class ControllerImpl implements Controller {
    private final ClipboardProvider clipboard;
    private final LevelsStorage levels;

    public ControllerImpl(final ClipboardProvider clipboard) throws IOException {
        this.clipboard = Objects.requireNonNull(clipboard);
        this.levels = new LevelsStorageImpl();
    }

    @Override
    public LevelController loadLevel(final String levelName) {
        return new LevelControllerImpl(this.levels.getLevel(levelName), this.clipboard);
    }

    @Override
    public List<String> getAvailableLevels() {
        return this.levels.getAllLevels()
                          .stream()
                          .sorted((x, y) -> Integer.compare(y.getProgressiveNumber(), x.getProgressiveNumber()))
                          .map(Level::getTitle)
                          .collect(Collectors.toList());
    }
}
