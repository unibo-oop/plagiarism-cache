package oop.lit.model.simplegame.elements;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import oop.lit.model.Action;
import oop.lit.model.GameElementModel;
import oop.lit.model.PlayerModel;
import oop.lit.model.SelectableElementGroupModel;
import oop.lit.model.simplegame.SimplePlayer;
import oop.lit.model.simplegame.SimpleSelectableGroup;
import oop.lit.model.simplegame.actions.GroupActionFactory;
import oop.lit.model.simplegame.elements.actions.GroupSBEActionFactory;
import oop.lit.model.util.images.ImageLoader;
import oop.lit.model.util.images.WrappedImage;
import oop.lit.model.util.permission.PermissionHolder;
import oop.lit.util.Vector2D;

/**
 * A groupSBE implementation.
 */
public class GroupSBEImpl extends AbstractSBE implements GroupSBE {
    /**
     * 
     */
    private static final long serialVersionUID = 3590344525078158315L;

    private enum ActionType {
        SHUFFLE, DRAW_TOP, DRAW_BOT, DRAW_RAN, DRAW_SPEC, SEE, USE
    };

    private final SimpleSelectableGroup<BasicSBE> group;
    private final PermissionHolder addToGroupPermission = new PermissionHolder("Can add");
    private final GroupSBEActionFactory actionFactory;
    private final Map<ActionType, PermissionHolder> permissionMap = new HashMap<>();

    private transient SelectableElementGroupModel wrappedGroup;
    private transient Action seeGroupAction;
    private transient Action useGroupAction;

    /**
     * @param name
     *      the name of this element.
     * @param initialPosition
     *      the initial position of this board element. If the optional is empty a default value will be used.
     * @param initialScale
     *      the initial scale of this board element. If the optional is empty a default value will be used.
     * @param initialRotation
     *      the initial rotation of this board element. If the optional is empty a default value will be used.
     * @param image
     *      the image to be used to show this element. If the optional is empty a default image will be shown (chosen by the view).
     * @param actionFactory
     *      the action factory for this element.
     * @param groupActionFactory
     *      the action factory for the inner group.
     */
    public GroupSBEImpl(final Optional<String> name, final Optional<Vector2D> initialPosition,
            final Optional<Double> initialScale, final Optional<Double> initialRotation,
            final Optional<WrappedImage> image, final GroupSBEActionFactory actionFactory,
            final GroupActionFactory groupActionFactory) {
        super(name, initialPosition, initialScale, initialRotation, actionFactory, image);
        this.group = new SimpleSelectableGroup<BasicSBE>(name, groupActionFactory);
        this.actionFactory = actionFactory;
        this.initNonFinal();
    }

    /**
     * Creates a new GroupSBE based on the provided one. The inner group of the new element will be empty.
     * @param element
     *      a GroupSBE to be used as base for the new one.
     * @param actionFactory
     *      the action factory for the new element.
     * @param groupActionFactory
     *      the action factory for the inner group.
     * @param imageLoader
     *      the game imageLoader.
     *      If the image used by the provided element is not contained in the provided image loader it will also be added to it. 
     */
    public GroupSBEImpl(final GroupSBEImpl element, final GroupSBEActionFactory actionFactory,
            final GroupActionFactory groupActionFactory, final ImageLoader imageLoader) {
        super(element, actionFactory, imageLoader);
        this.group = new SimpleSelectableGroup<BasicSBE>(element.getName(), groupActionFactory);
        this.actionFactory = actionFactory;
        this.initNonFinal();
    }

    private void initNonFinal() {
        this.getActionsManager().addPermission(addToGroupPermission);
        this.initActions();
    }

    @Override
    public void shuffle() {
        this.group.shuffle();
    }

    @Override
    public int getContainedElementsSize() {
        return this.group.getElements().size();
    }

    @Override
    public void draw(final int position) {
        this.group.drawElement(position).sendToBoard();
    }

    @Override
    public Optional<Action> getMainAction(final PlayerModel playingPlayer, final List<? extends PlayerModel> turnPlayers) {
        final boolean isPlayerGM = SimplePlayer.isPlayerGM(playingPlayer);
        final boolean isPlayerTurn = turnPlayers.contains(playingPlayer);
        if (this.useGroupAction.canBePerformed()
                && (this.getActionsManager().canPlayerPerform(this.useGroupAction, playingPlayer, isPlayerTurn)
                        || isPlayerGM)) {
            return Optional.of(this.useGroupAction);
        }
        if (this.seeGroupAction.canBePerformed()
                && (this.getActionsManager().canPlayerPerform(this.seeGroupAction, playingPlayer, isPlayerTurn)
                        || isPlayerGM)) {
            return Optional.of(this.seeGroupAction);
        }
        return Optional.empty();
    }

    @Override
    public boolean addElement(final BasicSBE element, final int position) {
        return group.addInPosition(element, position);
    }
    @Override
    public boolean removeElement(final GameElementModel element) {
        return group.removeElement(element);
    }
    @Override
    public boolean canPlayerAdd(final PlayerModel player, final boolean isPlayerTurn) {
        return SimplePlayer.isPlayerGM(player) || addToGroupPermission.canPlayerDo(player, isPlayerTurn);
    }

    @Override
    public SelectableElementGroupModel getSelectableElementGroupModel() {
        if (this.wrappedGroup == null) {
            this.wrappedGroup = new SelectableElementGroupModel(group);
        }
        return this.wrappedGroup;
    }

    @Override
    public void removed() {
        super.removed();
        this.group.removed();
    }

    private void initActions() {
        final Action shuffleAction = actionFactory.getShuffleAction(this);
        final Action drawFromTopAction = actionFactory.getDrawFromTopAction(this);
        final Action drawFromBottomAction = actionFactory.getDrawFromBottomAction(this);
        final Action drawRandomAction = actionFactory.getDrawRandomAction(this);
        final Action drawSpecificAction = actionFactory.getDrawSpecificAction(this);
        this.useGroupAction = actionFactory.getUseGroupAction(this);
        this.seeGroupAction = actionFactory.getShowGroupAction(this);

        this.addActionType(shuffleAction, GroupActionTypes.SHUFFLE);

        this.addToManager(shuffleAction, ActionType.SHUFFLE, this.permissionMap);
        this.addToManager(drawFromTopAction, ActionType.DRAW_TOP, this.permissionMap);
        this.addToManager(drawFromBottomAction, ActionType.DRAW_BOT, this.permissionMap);
        this.addToManager(drawRandomAction, ActionType.DRAW_RAN, this.permissionMap);
        this.addToManager(drawSpecificAction, ActionType.DRAW_SPEC, this.permissionMap);
        this.addToManager(this.useGroupAction, ActionType.USE, this.permissionMap);
        this.addToManager(this.seeGroupAction, ActionType.SEE, this.permissionMap);
    }

    private void readObject(final ObjectInputStream in) throws ClassNotFoundException, IOException {
        in.defaultReadObject();
        this.initActions();
    }
}
