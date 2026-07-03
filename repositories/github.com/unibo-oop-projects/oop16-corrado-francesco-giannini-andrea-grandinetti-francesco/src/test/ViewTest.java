package test;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import controller.Controller;
import controller.file.ViewFile;
import javafx.stage.Stage;
import utility.Direction;
import utility.Driver;
import utility.Pair;
import utility.Position;
import utility.TyreType;
import view.View;
import view.game.InfoDriver;

/**
 * View test for class.
 */
public class ViewTest implements View {

    @Override
    public void startMenu(final Stage stage) throws IOException {
    }

    @Override
    public void setController(final Controller ctr) {
    }

    @Override
    public void setViewFile(final ViewFile fileContainer) {
    }

    @Override
    public void start() {
    }

    @Override
    public void startRace() {
    }

    @Override
    public Direction throwDice(final int number, final Pair<Boolean, Boolean> canDir, final boolean canBox) {
        return Direction.STRAIGHT;
    }

    @Override
    public void update(final Driver drv, final Position pos, final boolean block) {
    }

    @Override
    public void updatePlayer(final InfoDriver info) {
    }

    @Override
    public void crash(final Map<Driver, Optional<String>> crashedPlayers) {
    }

    @Override
    public void retire(final Map<Driver, Optional<String>> retiredPlayers) {
    }

    @Override
    public void rankQualifying(final List<Pair<Driver, Integer>> rank, final boolean isEnded) {
    }

    @Override
    public void rankRace(final List<Driver> rank, final boolean isEnded) {
    }

    @Override
    public TyreType box(final String name) {
        return TyreType.S;
    }

    @Override
    public void disqualify(final Map<Driver, Optional<String>> disqualifiedPlayers) {
    }

}
