package todo.view.menu;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * This interface adds a method to return the {@link Stage} of the
 * {@link Screen}.
 */
public interface ObservableScreen extends Screen {
    /**
     * @return the {@link Stage} for this {@link Screen}
     */
    Stage getStage();

    /**
     * Set a new {@link MenuObserver} for this {@link Screen}.
     *
     * @param observer the {@link MenuObserver} to be registered
     */
    void setObserver(MenuObserver observer);
}
