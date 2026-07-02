package tmw.controller.world;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Observable;
import java.util.Optional;
import java.util.Random;

import tmw.common.GameStatesList;
import tmw.controller.entities.MilkController;
import tmw.controller.level.SelectLevelController;
import tmw.model.world.WorldEvents;
import utils.Rooms;

/**
 * This class implements {@link RoomSwitcherRoutine}. Listens to "switch" event
 * and execute switch of the room.
 *
 */
public class RoomSwitcherRoutineImpl implements RoomSwitcherRoutine {

    private final WorldController worldController;
    private final SelectLevelController levelController;

    private final List<Rooms> availableRooms = new ArrayList<Rooms>(Arrays.asList(Rooms.values()));

    /**
     * Public constructor.
     * 
     * @param worldController {@link WorldController} worldController from which
     *                        listen to "switch" event.
     * @param levelController {@link SelectLevelController} level controller to
     *                        which delegate switching
     */
    public RoomSwitcherRoutineImpl(final WorldController worldController, final SelectLevelController levelController) {
        this.worldController = worldController;
        this.levelController = levelController;
        this.availableRooms.remove(Rooms.BOSS_ROOM);
        this.availableRooms.remove(Rooms.TUTORIAL);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final Observable o, final Object arg) {
        if (arg.equals(GameStatesList.SWITCH_ROOM)) {
            this.loadNextRoom(worldController.getPlayer());
        }

        if (arg.equals(WorldEvents.PLAYER_DEATH)) {
            this.levelController.goToGamerOver();
        }

        if (arg.equals(GameStatesList.TUTORIAL_DONE)) {
            this.levelController.goBack();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void loadNextRoom(final MilkController playerController) {

        this.worldController.resetEntities();
        if (!availableRooms.isEmpty()) {
            final int nextInt = new Random().nextInt(availableRooms.size());
            final Rooms randomRoom = availableRooms.get(nextInt);
            final Optional<WorldDispenser> randomDispenser = utils.AvailableRoomsUtils.getRoom(randomRoom,
                    worldController);
            availableRooms.remove(nextInt);
            if (randomDispenser.isPresent()) {

                this.levelController.changeRoom(playerController, randomDispenser.get(),
                        utils.ImageUtils.getRoomBackground(randomRoom));
            }
        } else {
            this.levelController.goToEndLevel();
        }
    }

}
