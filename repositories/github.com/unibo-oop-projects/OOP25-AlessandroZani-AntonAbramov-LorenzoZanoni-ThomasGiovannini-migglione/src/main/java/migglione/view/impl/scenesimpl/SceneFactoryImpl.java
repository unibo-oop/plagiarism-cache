package migglione.view.impl.scenesimpl;

import javax.swing.JPanel;

import migglione.controller.api.Controller;
import migglione.view.api.scenes.SceneFactory;
import migglione.view.api.scenes.Scenes;
import migglione.view.impl.SwingViewImpl;

/**
 * Implementation of SceneFactory.
 * 
 * <p>
 * It lets the creation of the different scenes
 * up to a factory, in order to incapsulate the names
 * of the scenes and to allow extentions by simply adding
 * new values cleanly.
 */
public final class SceneFactoryImpl implements SceneFactory {

    @Override
    public JPanel createScene(final SwingViewImpl view, final Scenes scenes, final Controller controller) {
        return switch (scenes) {
            case MENU -> new Menu(view);
            case START_GAME -> new StartGame(view, controller);
            case TUTORIAL -> new Tutorial(view);
            case FIELD -> new Field(controller);
            case SCORES -> new Scores(view);
            case GALLERY -> new Gallery(view);
            case CREDITS -> new Credits(view);
        };
    }
}
