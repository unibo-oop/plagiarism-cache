package oop.lit.model.simplegame.elements;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import oop.lit.model.Action;
import oop.lit.model.PlayerModel;
import oop.lit.model.simplegame.SimpleBoard;
import oop.lit.model.simplegame.SimplePlayerHand;
import oop.lit.model.simplegame.elements.actions.BasicSBEActionFactory;
import oop.lit.model.util.images.ImageLoader;
import oop.lit.model.util.images.WrappedImage;
import oop.lit.model.util.permission.PermissionHolder;
import oop.lit.util.Vector2D;

/**
 * An implementation for a BasicSBE.
 */
public class BasicSBEImpl extends AbstractSBE implements BasicSBE {
    /**
     * 
     */
    private static final long serialVersionUID = 2406386827273204823L;

    private enum ActionType {
        HAND, GROUP_TOP, GROUP_BOT, GROUP_RAN, GROUP_SPEC
    };
    private final BasicSBEActionFactory actionFactory;
    private final Map<ActionType, PermissionHolder> permissionMap = new HashMap<>();
    private final SimpleBoard board;
    private GroupSBE group;
    private SimplePlayerHand playerHand;

    private transient Action sendToBoardAction;

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
     * @param board
     *      the board this element belongs to.
     * @param actionFactory
     *      the action factory for this element.
     */
    public BasicSBEImpl(final Optional<String> name, final Optional<Vector2D> initialPosition,
            final Optional<Double> initialScale, final Optional<Double> initialRotation,
            final Optional<WrappedImage> image, final SimpleBoard board,
            final BasicSBEActionFactory actionFactory) {
        super(name, initialPosition, initialScale, initialRotation, actionFactory, image);
        Objects.requireNonNull(board);
        this.board = board;
        this.actionFactory = actionFactory;

        this.initActions();
    }

    /**
     * Creates a new BasicSBE based on the provided one. The inner group of the new element will be empty.
     * @param element
     *      a BasicSBE to be used as base for the new one.
     * @param board
     *      the board this element belongs to.
     * @param actionFactory
     *      the action factory for this element..
     * @param imageLoader
     *      the game imageLoader.
     *      If the image used by the provided element is not contained in the provided image loader it will also be added to it. 
     */
    public BasicSBEImpl(final BasicSBEImpl element, final SimpleBoard board, final BasicSBEActionFactory actionFactory, final ImageLoader imageLoader) {
        super(element, actionFactory, imageLoader);
        this.board = board;
        this.actionFactory = actionFactory;

        this.initActions();
    }

    @Override
    public boolean sendToGroup(final GroupSBE group, final int position) {
        Objects.requireNonNull(group);
        if (this.group != null && this.group.equals(group)) {
            return false;
        }
        this.removeAndSetNull();
        group.addElement(this, position);
        this.group = group;
        return true;
    }
    @Override
    public Set<GroupSBE> getPossibleGroups() {
        return this.board.getGroups();
    }
    @Override
    public boolean sendToBoard() {
        if (this.group == null && this.playerHand == null) {
            return false;
        }
        this.removeAndSetNull();
        this.board.addElement(this);
        return true;
    }
    @Override
    public boolean sendToHand(final SimplePlayerHand hand) {
        Objects.requireNonNull(hand);
        if (this.playerHand != null && this.playerHand.equals(hand)) {
            return false;
        }
        this.removeAndSetNull();
        hand.addElement(this);
        this.playerHand = hand;
        return true;
    }
    @Override
    public boolean isOnBoard() {
        return this.playerHand == null && this.group == null;
    }

    @Override
    public Optional<Action> getMainAction(final PlayerModel playingPlayer, final List<? extends PlayerModel> turnPlayers) {
        if (this.sendToBoardAction.canBePerformed()) {
            return Optional.of(this.sendToBoardAction);
        }
        return Optional.empty();
    }

    /**
     * Removes the element from the board/
     */
    private void removeAndSetNull() {
        if (this.playerHand != null) {
            this.playerHand.removeElement(this);
        } else if (this.group != null) {
            this.group.removeElement(this);
        } else {
            this.board.removeElement(this);
        }
        this.group = null;
        this.playerHand = null;
    }

    private void initActions() {
        this.sendToBoardAction = actionFactory.getSendToBoardAction(this);
        this.addActions(Arrays.asList(this.sendToBoardAction));

        final Action sendToHand = actionFactory.getSendToHandAction(this);
        final Action sendToGroupTop = actionFactory.getSendToGroupTopAction(this);
        final Action sendToGroupBottom = actionFactory.getSendToGroupBottomAction(this);
        final Action sendToGroupRandom = actionFactory.getSendToGroupRandomAction(this);
        final Action sendToGroupSpecific = actionFactory.getSendToGroupSpecificAction(this);
        this.addActionType(sendToHand, GroupActionTypes.SEND_TO_HAND);
        this.addActionType(sendToGroupTop, GroupActionTypes.SEND_TO_GROUP_TOP);
        this.addActionType(sendToGroupBottom, GroupActionTypes.SEND_TO_GROUP_BOTTOM);
        this.addActionType(sendToGroupRandom, GroupActionTypes.SEND_TO_GROUP_RANDOM);
        this.addActionType(sendToGroupSpecific, GroupActionTypes.SEND_TO_GROUP_SPECIFIC);
        this.addToManager(sendToHand, ActionType.HAND, this.permissionMap);
        this.addToManager(sendToGroupTop, ActionType.GROUP_TOP, this.permissionMap);
        this.addToManager(sendToGroupBottom, ActionType.GROUP_BOT, this.permissionMap);
        this.addToManager(sendToGroupRandom, ActionType.GROUP_RAN, this.permissionMap);
        this.addToManager(sendToGroupSpecific, ActionType.GROUP_SPEC, this.permissionMap);
    }

    private void readObject(final ObjectInputStream in) throws ClassNotFoundException, IOException {
        in.defaultReadObject();
        this.initActions();
    }
}
