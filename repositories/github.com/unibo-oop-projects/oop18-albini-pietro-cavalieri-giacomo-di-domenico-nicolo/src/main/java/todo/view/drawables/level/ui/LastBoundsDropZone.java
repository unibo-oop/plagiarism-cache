package todo.view.drawables.level.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Image;

import todo.controller.RoomController;
import todo.utils.UIConstants;

/**
 * This internal use only class places every instruction actor that is dropped
 * to the end of the program.
 */
class LastBoundsDropZone extends BaseBoundsDropZone {
    /**
     * Create a drop zone with a transparent image.
     *
     * @param roomController is the room controller
     * @param programUI is the program UI instance
     * @param width is the width of the zone
     * @param height is the height of the zone
     */
    LastBoundsDropZone(final RoomController roomController, final ProgramUI programUI, final float width,
            final float height) {
        this(roomController, programUI, new Image(UIConstants.TRANSPARENT_PATCH), width, height);
    }

    /**
     * Create a drop zone with the specified image.
     *
     * @param roomController is the room controller
     * @param programUI is the program UI instance
     * @param image is the image of the zone
     * @param width is the width of the zone
     * @param height is the height of the zone
     */
    LastBoundsDropZone(final RoomController roomController, final ProgramUI programUI, final Image image,
            final float width, final float height) {
        super(roomController, programUI, image, width, height, () -> roomController.getInstructions().size(),
                () -> roomController.getInstructions().size() - 1);
    }
}
