package oop.lit.model.simplegame.elements;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import oop.lit.model.Action;
import oop.lit.model.PlayerModel;
import oop.lit.model.elements.PermissionsBoardElement;
import oop.lit.model.simplegame.SimplePlayer;
import oop.lit.model.simplegame.elements.actions.SBEActionFactory;
import oop.lit.model.util.images.ImageLoader;
import oop.lit.model.util.images.WrappedImage;
import oop.lit.model.util.permission.PermissionHolder;
import oop.lit.util.Vector2D;

/**
 * A boardElement using as image one provided by the constructor.
 */
public abstract class AbstractSBE extends PermissionsBoardElement implements SimpleBoardElement {
    /**
     * 
     */
    private static final long serialVersionUID = 5947088387805916879L;
    private final SBEActionFactory actionFactory;
    private final WrappedImage image;
    private final PermissionHolder removePermission = new PermissionHolder("Remove");
    private transient List<Action> noManagerActions = new ArrayList<>();
    private transient Map<Action, GroupActionTypes> actionTypes = new HashMap<>();

    /**
     * @param name
     *      the name of this element.
     * @param initialPosition
     *      the initial position of this board element. If the optional is empty a default value will be used.
     * @param initialScale
     *      the initial scale of this board element. If the optional is empty a default value will be used.
     * @param initialRotation
     *      the initial rotation of this board element. If the optional is empty a default value will be used.
     * @param actionFactory
     *      an actionFactory for a SimpleBoardElement
     * @param image
     *      the image to be used to show this element. If the optional is empty a default image will be shown (chosen by the view).
     */
    protected AbstractSBE(final Optional<String> name, final Optional<Vector2D> initialPosition,
            final Optional<Double> initialScale, final Optional<Double> initialRotation,
            final SBEActionFactory actionFactory, final Optional<WrappedImage> image) {
        super(name, initialPosition, initialScale, initialRotation);
        Objects.requireNonNull(image);
        Objects.requireNonNull(actionFactory);
        this.actionFactory = actionFactory;
        this.image = image.orElse(null);
        this.getActionsManager().addPermission(this.removePermission);
    }

    /**
     * Creates a new AbstractSBE based on the provided one.
     * @param element
     *      an AbstractSBE to be used as base for the new one.
     * @param actionFactory
     *      the action factory for the new element.
     * @param imageLoader
     *      the game imageLoader.
     *      If the image used by the provided element is not contained in the provided image loader it will also be added to it. 
     */
    protected AbstractSBE(final AbstractSBE element, final SBEActionFactory actionFactory, final ImageLoader imageLoader) {
        this(element.getName(), Optional.of(element.getPosition()), Optional.of(element.getScale()),
                Optional.of(element.getRotation()), actionFactory, Optional.ofNullable(element.image));
        if (element.image != null) {
            imageLoader.addImage(image, Optional.empty());
        }
    }

    @Override
    public boolean canMove(final PlayerModel playingPlayer, final List<? extends PlayerModel> turnPlayers) {
        return SimplePlayer.isPlayerGM(playingPlayer) || super.canMove(playingPlayer, turnPlayers);
    }
    @Override
    public boolean canRotate(final PlayerModel playingPlayer, final List<? extends PlayerModel> turnPlayers) {
        return SimplePlayer.isPlayerGM(playingPlayer) || super.canRotate(playingPlayer, turnPlayers);
    }
    @Override
    public boolean canScale(final PlayerModel playingPlayer, final List<? extends PlayerModel> turnPlayers) {
        return SimplePlayer.isPlayerGM(playingPlayer) || super.canScale(playingPlayer, turnPlayers);
    }
    @Override
    public Optional<BufferedImage> getImage() {
        if (this.image != null) {
            return Optional.of(this.image.get());
        }
        return Optional.empty();
    }

    @Override
    public Set<GroupActionTypes> getPossibleGroupActionsTypes(final PlayerModel playingPlayer, final List<? extends PlayerModel> turnPlayers) {
        final Set<GroupActionTypes> res = this.getActions(playingPlayer, turnPlayers).stream()
                .filter(this.actionTypes::containsKey).map(this.actionTypes::get).collect(Collectors.toSet());
        if ((this.removePermission.canPlayerDo(playingPlayer, turnPlayers.contains(playingPlayer)))
                || SimplePlayer.isPlayerGM(playingPlayer)) {
            res.add(GroupActionTypes.REMOVE);
        }
        return res;
    }

    @Override
    public List<Action> getActions(final PlayerModel playingPlayer, final List<? extends PlayerModel> turnPlayers) {
        final List<Action> res = this.noManagerActions.stream().filter(Action::canBePerformed).collect(Collectors.toList());
        if (SimplePlayer.isPlayerGM(playingPlayer)) {
            res.addAll(actionFactory.getPermissionEditActions(this.getActionsManager()));
            res.addAll(this.getActionsManager().getAllActions().stream().filter(Action::canBePerformed)
                    .collect(Collectors.toList()));
        } else {
            res.addAll(super.getActions(playingPlayer, turnPlayers));
        }
        return res;
    }

    /**
     * Adds actions that any player, regardless of permission, will be able to do.
     * @param actions
     *      the actions to be added.
     */
    protected void addActions(final List<Action> actions) {
        this.noManagerActions.addAll(actions);
    }

    /**
     * Associate the provided action with the provided type.
     * The association will be used for the "getPossibleGroupActionsTypes" method.
     * @param action
     *      an action.
     * @param type
     *      the provided action type.
     */
    protected void addActionType(final Action action, final GroupActionTypes type) {
        this.actionTypes.put(action, type);
    }

    /**
     * Adds the provided action to this ActionsManager.
     * If there is a PermissionHolder in the provided map corresponding to the provided key,
     * that value will be the provided action PermissionHolder in the map.
     * If there is no mapping for the provided key, a default PermissionHolder will
     * be assigned to the provided action (depends on the ActionsManager),
     * then that permission holder will also be mapped to the provided key.
     *
     * @param action
     *            the action.
     * @param key
     *            the key.
     * @param permissionMap
     *            the map associating some keys to permissionHolders
     *
     * @param <T>
     *            the type of the key object in the provided map.
     */
    protected <T> void addToManager(final Action action, final T key, final Map<T, PermissionHolder> permissionMap) {
        final PermissionHolder holder = permissionMap.get(key);
        if (holder == null) {
            this.getActionsManager().addAction(action);
            permissionMap.put(key, this.getActionsManager().getActionPermissionHolder(action));
        } else {
            this.getActionsManager().addAction(action, holder);
        }
    }

    private void readObject(final ObjectInputStream in) throws ClassNotFoundException, IOException {
        in.defaultReadObject();
        this.noManagerActions = new ArrayList<>();
        this.actionTypes = new HashMap<>();
    }
}
