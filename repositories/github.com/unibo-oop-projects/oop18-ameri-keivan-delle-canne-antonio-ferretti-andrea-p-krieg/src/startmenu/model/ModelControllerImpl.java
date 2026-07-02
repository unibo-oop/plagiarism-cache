package startmenu.model;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfoList;
import io.github.classgraph.ScanResult;
import model.gamerules.GameRules;
import model.gamerules.LastAliveGameMode;

/**
 * Handles the communications by the controller.
 */
public class ModelControllerImpl implements ModelController {

    private GameRules mode = LastAliveGameMode.getLastAliveGameMode();

    /** {@inheritDoc} **/
    @Override
    public List<GameRules> getGameMode() {
        return Collections.unmodifiableList(this.getGameRules());
    }

    /**
     * @return the list of GameRules implemented
     */
    private List<GameRules> getGameRules() {
        final List<GameRules> gameRules = new LinkedList<>();
        try (ScanResult scanResult = new ClassGraph().enableAllInfo().whitelistPackages("model.gamerules").scan()) {
            final ClassInfoList widgetClasses = scanResult.getClassesImplementing("model.gamerules.GameRules");
            final List<String> widgetClassNames = widgetClasses.getNames();

            widgetClassNames.forEach(i -> {
                try {
                    final Class<?> clazz = Class.forName(i);
                    final GameRules rule = (GameRules) clazz.getDeclaredMethod("get" + clazz.getSimpleName())
                            .invoke(clazz);
                    gameRules.add((GameRules) rule);
                } catch (ClassNotFoundException e) {
                    throw new IllegalAccessError();
                } catch (SecurityException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
            });
        }
        return gameRules;
    }

    /** {@inheritDoc} **/
    @Override
    public void setGameMode(final int index) {
        this.mode = this.getGameRules().get(index);
    }

    /** {@inheritDoc} **/
    @Override
    public GameRules getChosenGameMode() {
        return this.mode;
    }

}
