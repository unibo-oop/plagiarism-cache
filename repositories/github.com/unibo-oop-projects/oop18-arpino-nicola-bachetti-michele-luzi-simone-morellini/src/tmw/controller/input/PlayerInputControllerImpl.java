package tmw.controller.input;

import java.util.List;
import tmw.common.CharacterStates;
import tmw.common.V2d;
import tmw.view.level.RoomView;

/**
 * Class that handle interaction between playerEntity and user.
 *
 */
public class PlayerInputControllerImpl implements PlayerInputController {

    private static final int FIRST_SLOT_INDEX = 0;
    private static final int SECOND_SLOT_INDEX = 1;
    private static final int THIRD_SLOT_INDEX = 2;
    private static final int FOURTH_SLOT_INDEX = 3;
    private static final int FIFTH_SLOT_INDEX = 4;
    private int itemPos;
    private V2d vel;
    private final List<CharacterStates> commandList;

    /**
     * Public constructor.
     * 
     * @param view Level view
     */
    public PlayerInputControllerImpl(final RoomView view) {
        this.commandList = view.getCommandList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CharacterStates update() {
        final CharacterStates state = commandList.isEmpty() ? CharacterStates.EMPTY_COMMAND : commandList.get(0);
        if (state != CharacterStates.EMPTY_COMMAND) {
            this.commandList.remove(0);
        }
        switch (state) {
        case MOVE_UP:
            this.vel = new V2d(0, -1);
            return CharacterStates.MOVE;
        case MOVE_DOWN:
            this.vel = new V2d(0, 1);
            return CharacterStates.MOVE;
        case MOVE_LEFT:
            this.vel = new V2d(-1, 0);
            return CharacterStates.MOVE;
        case MOVE_RIGHT:
            this.vel = new V2d(1, 0);
            return CharacterStates.MOVE;
        case SHOOT_UP:
            this.vel = new V2d(0, -1);
            return CharacterStates.SHOOT;
        case SHOOT_DOWN:
            this.vel = new V2d(0, 1);
            return CharacterStates.SHOOT;
        case SHOOT_LEFT:
            this.vel = new V2d(-1, 0);
            return CharacterStates.SHOOT;
        case SHOOT_RIGHT:
            this.vel = new V2d(1, 0);
            return CharacterStates.SHOOT;
        case ITEM1:
            this.itemPos = FIRST_SLOT_INDEX;
            return CharacterStates.INVENTORY;
        case ITEM2:
            this.itemPos = SECOND_SLOT_INDEX;
            return CharacterStates.INVENTORY;
        case ITEM3:
            this.itemPos = THIRD_SLOT_INDEX;
            return CharacterStates.INVENTORY;
        case ITEM4:
            this.itemPos = FOURTH_SLOT_INDEX;
            return CharacterStates.INVENTORY;
        case ITEM5:
            this.itemPos = FIFTH_SLOT_INDEX;
            return CharacterStates.INVENTORY;
        default:
            return CharacterStates.EMPTY_COMMAND;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public V2d getVel() {
        return this.vel;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getItemPos() {
        return this.itemPos;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void cleanCommand() {
        if (!this.commandList.isEmpty()) {
            this.commandList.remove(0);
        }

    }

}
