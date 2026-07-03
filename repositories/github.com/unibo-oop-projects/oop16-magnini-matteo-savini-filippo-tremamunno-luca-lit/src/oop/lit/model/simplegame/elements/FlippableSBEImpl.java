package oop.lit.model.simplegame.elements;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import oop.lit.model.Action;
import oop.lit.model.PlayerModel;
import oop.lit.model.simplegame.SimpleBoard;
import oop.lit.model.simplegame.SimplePlayer;
import oop.lit.model.simplegame.elements.actions.FlippableSBEActionFactory;
import oop.lit.model.util.images.ImageLoader;
import oop.lit.model.util.images.WrappedImage;
import oop.lit.model.util.permission.PermissionHolder;
import oop.lit.util.Vector2D;

/**
 * An implementation for a FlippableSBE.
 */
public class FlippableSBEImpl extends BasicSBEImpl implements FlippableSBE {
    /**
     * 
     */
    private static final long serialVersionUID = -5888061712718216689L;

    private enum ActionType {
        FLIP
    };
    private boolean faceUp;
    private final WrappedImage backImage;
    private final FlippableSBEActionFactory actionFactory;
    private final Map<ActionType, PermissionHolder> permissionMap = new HashMap<>();

    private transient Action flipAction;

    /**
     * @param name
     *      the name of this element.
     * @param initialPosition
     *      the initial position of this board element. If the optional is empty a default value will be used.
     * @param initialScale
     *      the initial scale of this board element. If the optional is empty a default value will be used.
     * @param initialRotation
     *      the initial rotation of this board element. If the optional is empty a default value will be used.
     * @param frontImage
     *      the image to be used to show this element face. If the optional is empty a default image will be shown (chosen by the view).
     * @param backImage
     *      the image to be used to show this element back. If the optional is empty a default image will be shown (chosen by the view).
     * @param board
     *      the board this element belongs to.
     * @param actionFactory
     *      an action factory for this element.
     */
    public FlippableSBEImpl(final Optional<String> name, final Optional<Vector2D> initialPosition,
            final Optional<Double> initialScale, final Optional<Double> initialRotation,
            final Optional<WrappedImage> frontImage, final Optional<WrappedImage> backImage,
            final SimpleBoard board, final FlippableSBEActionFactory actionFactory) {
        super(name, initialPosition, initialScale, initialRotation, frontImage, board, actionFactory);
        Objects.requireNonNull(backImage);
        this.backImage = backImage.orElse(null);
        this.actionFactory = actionFactory;
        this.initActions();
    }

    /**
     * Creates a new FlippableSBE based on the provided one. The inner group of the new element will be empty.
     * @param element
     *      an FlippableSBE to be used as base for the new one.
     * @param board
     *      the board this element belongs to.
     * @param actionFactory
     *      the action factory for this element..
     * @param imageLoader
     *      the game imageLoader.
     *      If the images used by the provided element are not contained in the provided image loader they will also be added to it. 
     */
    public FlippableSBEImpl(final FlippableSBEImpl element, final SimpleBoard board, final FlippableSBEActionFactory actionFactory, final ImageLoader imageLoader) {
        super(element, board, actionFactory, imageLoader);
        this.backImage = element.backImage;
        if (this.backImage != null) {
            imageLoader.addImage(this.backImage, Optional.empty());
        }
        this.actionFactory = actionFactory;
        this.initActions();
    }
    @Override
    public void flip() {
        this.faceUp = !this.faceUp;
        this.notifyObservers();
    }

    @Override
    public Optional<BufferedImage> getImage() {
        if (this.faceUp) {
            return super.getImage();
        }
        if (this.backImage != null) {
            return Optional.of(this.backImage.get());
        }
        return Optional.empty();
    }

    @Override
    public Optional<Action> getMainAction(final PlayerModel playingPlayer, final List<? extends PlayerModel> turnPlayers) {
        final Optional<Action> superMain = super.getMainAction(playingPlayer, turnPlayers);
        if (superMain.isPresent()) {
            return superMain;
        }
        if ((this.getActionsManager().canPlayerPerform(this.flipAction, playingPlayer, turnPlayers.contains(playingPlayer))
                || SimplePlayer.isPlayerGM(playingPlayer))
                && this.flipAction.canBePerformed()) {
            return Optional.of(this.flipAction);
        }
        return Optional.empty();
    }

    private void initActions() {
        this.flipAction = this.actionFactory.getFlipAction(this);
        this.addActionType(this.flipAction, GroupActionTypes.FLIP);
        this.addToManager(this.flipAction, ActionType.FLIP, this.permissionMap);
    }

    private void readObject(final ObjectInputStream in) throws ClassNotFoundException, IOException {
        in.defaultReadObject();
        this.initActions();
    }
}
