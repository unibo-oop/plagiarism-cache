package todo.view.screens;

import java.util.function.Consumer;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;

import todo.view.drawables.Drawable;
import todo.view.drawables.level.ui.ProgramUI;
import todo.view.drawables.level.ui.dialogs.DialogResponse;
import todo.view.drawables.level.ui.dialogs.GameDialogImpl;
import todo.view.drawables.level.ui.playback.PlaybackControls;
import todo.view.entities.Entity;
import todo.view.rooms.Room;

/**
 * This interface represents the entry point for the view, the room screen.
 */
public interface RoomScreen extends Screen {
    /**
     * @return the room to display
     */
    Room getRoom();

    /**
     * @return the stage for the UI
     */
    Stage getStage();

    /**
     * @return the program UI
     */
    ProgramUI getProgramUI();

    /**
     * @return the playback controls
     */
    PlaybackControls getPlaybackControls();

    /**
     * Get the drawable given the entity.
     *
     * @param entity is the entity whose drawable will be returned
     * @return the found drawable
     */
    Drawable<? extends Entity> getDrawableOf(Entity entity);

    /**
     * Add the list of entities to the screen.
     *
     * @param entities is a comma-separated list of entities
     */
    void addEntities(Entity... entities);

    /**
     * Remove the specified entity from the screen.
     *
     * @param entity is the entity to be removed
     */
    void removeEntity(Entity entity);

    /**
     * Show a dialog to the user.
     * 
     * @param text is the text of the dialog
     * @param onButtonPress is the callback that is called when a button is pressed
     * @param color is the color of the button(s)
     * @param allowedResponses is a comma-separated list of allowed responses
     */
    void showDialog(String text, Consumer<DialogResponse> onButtonPress, GameDialogImpl.ButtonColor color,
            DialogResponse... allowedResponses);

    /**
     * Show a dialog with the level description.
     */
    void showLevelDescription();
}
