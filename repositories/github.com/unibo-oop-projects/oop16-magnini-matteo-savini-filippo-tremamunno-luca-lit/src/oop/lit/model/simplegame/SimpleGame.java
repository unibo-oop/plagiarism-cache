package oop.lit.model.simplegame;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import oop.lit.model.Action;
import oop.lit.model.game.Game;
import oop.lit.model.groups.GroupViewer;
import oop.lit.model.simplegame.actions.GroupActionFactory;
import oop.lit.model.simplegame.actions.GroupActionFactoryImpl;
import oop.lit.model.simplegame.actions.SimpleGameActionFactory;
import oop.lit.model.simplegame.actions.SimpleGameActionFactoryImpl;
import oop.lit.model.simplegame.elements.actions.FlippableSBEActionFactory;
import oop.lit.model.simplegame.elements.actions.FlippableSBEActionFactoryImpl;
import oop.lit.model.simplegame.elements.actions.GroupSBEActionFactory;
import oop.lit.model.simplegame.elements.actions.GroupSBEActionFactoryImpl;
import oop.lit.model.util.images.ImageLoader;
import oop.lit.util.ObservableImpl;
import oop.lit.view.ViewRequests;

/**
 * Main class for a simple game.
 */
public class SimpleGame extends ObservableImpl implements Serializable, Game<SimpleBoard, SimplePlayerManager> {
    /**
     * 
     */
    private static final long serialVersionUID = 5120464345297557866L;
    private final SimpleBoard board;
    private final SimplePlayerManager pManager;
    private final GroupViewer gViewer;
    private final SimpleGameActionFactory actionFactory;
    private transient List<Action> gmOnlyActions;
    private transient List<Action> commonActions;
    private transient Action addPlayerAction;
    private transient Action showHandAction;

    /**
     * 
     */
    public SimpleGame() {
        this.pManager = new SimplePlayerManager();
        this.gViewer = new GroupViewer();
        final FlippableSBEActionFactory flippableActionFactory = new FlippableSBEActionFactoryImpl(this.pManager);
        final GroupSBEActionFactory groupElementActionFactory = new GroupSBEActionFactoryImpl(this.pManager, this.gViewer);
        final GroupActionFactory groupActionFactory = new GroupActionFactoryImpl(flippableActionFactory, groupElementActionFactory);
        this.board = new SimpleBoard(groupActionFactory);
        this.actionFactory = new SimpleGameActionFactoryImpl(this, flippableActionFactory, groupElementActionFactory,
                groupActionFactory, new ImageLoader());

        this.initTransient();
    }
    @Override
    public SimpleBoard getBoard() {
        return this.board;
    }

    @Override
    public SimplePlayerManager getPlayerManager() {
        return this.pManager;
    }

    @Override
    public GroupViewer getGroupViewer() {
        return this.gViewer;
    }

    @Override
    public List<Action> getActions() {
        final List<Action> res = new ArrayList<>();
        if (!pManager.getPlayingPlayer().isPresent()) {
            res.add(this.addPlayerAction);
        } else if (pManager.getPlayingPlayer().get().isGM()) {
            res.addAll(this.gmOnlyActions);
            res.add(this.showHandAction);
            res.add(this.addPlayerAction);
        } else {
            res.add(this.showHandAction);
        }
        res.addAll(this.commonActions);

        return res.stream().filter(Action::canBePerformed).collect(Collectors.toList());
    }

    private void initTransient() {
        this.gmOnlyActions = new ArrayList<>();
        this.commonActions = new ArrayList<>();
        this.gmOnlyActions.add(actionFactory.getAddBasicElementAction());
        this.gmOnlyActions.add(actionFactory.getAddFlippableElementAction());
        this.gmOnlyActions.add(actionFactory.getAddGroupElementAction());
        this.gmOnlyActions.add(actionFactory.getRemovePlayerAction());
        this.gmOnlyActions.add(actionFactory.getLoadImageAction());
        this.gmOnlyActions.add(actionFactory.getSetTurnAction());
        this.gmOnlyActions.add(actionFactory.getImportAction());
        this.showHandAction = actionFactory.getShowHandAction();
        this.addPlayerAction = actionFactory.getAddPlayerAction();
        this.commonActions.add(actionFactory.getChangePlayerAction());
        this.commonActions.add(actionFactory.getSaveAction());

        this.pManager.attach(() -> {
            this.notifyObservers();
            this.gViewer.stopShowingAll();
            this.board.clearSelection();
        });
    }

    private void readObject(final ObjectInputStream in) throws ClassNotFoundException, IOException {
        in.defaultReadObject();
        this.initTransient();
    }
    @Override
    public void setView(final ViewRequests view) {
    }
}
