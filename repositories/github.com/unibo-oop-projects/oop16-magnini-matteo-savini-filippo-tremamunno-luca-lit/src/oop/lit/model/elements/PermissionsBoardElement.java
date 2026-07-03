package oop.lit.model.elements;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import oop.lit.model.Action;
import oop.lit.model.PlayerModel;
import oop.lit.model.util.permission.ActionsManager;
import oop.lit.model.util.permission.PermissionHolder;
import oop.lit.util.Vector2D;

/**
 * A BoardElement managing who can perform actions, move, scale and rotate the element through a BEActionsManager.
 * Subclasses should add actions to the ActionsManager.
 * Permission information about actions are not serialized.
 */
public abstract class PermissionsBoardElement extends AbstractBoardElement {
    /**
     * 
     */
    private static final long serialVersionUID = 8062532569753463527L;
    private final ActionsManager am = new ActionsManager();
    private final PermissionHolder movePermission = new PermissionHolder("Can move");
    private final PermissionHolder scalePermission = new PermissionHolder("Can scale");
    private final PermissionHolder rotatePermission = new PermissionHolder("Can rotate");

    /**
     * @param name
     *      the name of this element.
     * @param initialPosition
     *      the initial position of this board element. If the optional is empty a default value will be used.
     * @param initialScale
     *      the initial scale of this board element. If the optional is empty a default value will be used.
     * @param initialRotation
     *      the initial rotation of this board element. If the optional is empty a default value will be used.
     */
    protected PermissionsBoardElement(final Optional<String> name, final Optional<Vector2D> initialPosition,
            final Optional<Double> initialScale, final Optional<Double> initialRotation) {
        super(name, initialPosition, initialScale, initialRotation);
        this.am.addPermission(movePermission);
        this.am.addPermission(scalePermission);
        this.am.addPermission(rotatePermission);
    }

    /**
     * @return
     *      this element ActionsManager
     */
    protected ActionsManager getActionsManager() {
        return this.am;
    }

    @Override
    public boolean canMove(final PlayerModel playingPlayer, final List<? extends PlayerModel> turnPlayers) {
        return this.movePermission.canPlayerDo(playingPlayer, turnPlayers.contains(playingPlayer));
    }

    @Override
    public boolean canScale(final PlayerModel playingPlayer, final List<? extends PlayerModel> turnPlayers) {
        return this.scalePermission.canPlayerDo(playingPlayer, turnPlayers.contains(playingPlayer));
    }

    @Override
    public boolean canRotate(final PlayerModel playingPlayer, final List<? extends PlayerModel> turnPlayers) {
        return this.rotatePermission.canPlayerDo(playingPlayer, turnPlayers.contains(playingPlayer));
    }

    @Override
    public List<Action> getActions(final PlayerModel playingPlayer, final List<? extends PlayerModel> turnPlayers) {
        return this.am.getActions(playingPlayer, turnPlayers.contains(playingPlayer)).stream()
                .filter(Action::canBePerformed).collect(Collectors.toList());
    }
}
