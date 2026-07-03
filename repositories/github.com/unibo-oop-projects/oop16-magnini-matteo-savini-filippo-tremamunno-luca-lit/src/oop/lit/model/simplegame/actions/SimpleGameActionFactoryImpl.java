package oop.lit.model.simplegame.actions;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import oop.lit.model.Action;
import oop.lit.model.PlayerModel;
import oop.lit.model.actions.AbstractAction;
import oop.lit.model.actions.Actions;
import oop.lit.model.groups.GroupViewer;
import oop.lit.model.simplegame.SimpleBoard;
import oop.lit.model.simplegame.SimpleGame;
import oop.lit.model.simplegame.SimplePlayer;
import oop.lit.model.simplegame.SimplePlayerHand;
import oop.lit.model.simplegame.SimplePlayerManager;
import oop.lit.model.simplegame.elements.BasicSBEImpl;
import oop.lit.model.simplegame.elements.FlippableSBEImpl;
import oop.lit.model.simplegame.elements.GroupSBEImpl;
import oop.lit.model.simplegame.elements.SimpleBoardElement;
import oop.lit.model.simplegame.elements.actions.FlippableSBEActionFactory;
import oop.lit.model.simplegame.elements.actions.GroupSBEActionFactory;
import oop.lit.model.simplegame.elements.builders.BasicSBEBuilder;
import oop.lit.model.simplegame.elements.builders.BasicSBEBuilderImpl;
import oop.lit.model.simplegame.elements.builders.FlippableSBEBuilder;
import oop.lit.model.simplegame.elements.builders.FlippableSBEBuilderImpl;
import oop.lit.model.simplegame.elements.builders.GroupSBEBuilder;
import oop.lit.model.simplegame.elements.builders.GroupSBEBuilderImpl;
import oop.lit.model.util.images.ImageLoader;
import oop.lit.model.util.images.WrappedImage;
import oop.lit.util.CollectionsUtils;
import oop.lit.util.FileType;
import oop.lit.util.IllegalInputException;
import oop.lit.util.InputRequest;
import oop.lit.util.InputRequestsFactory;

/**
 * A simpleGameActionFactory implementation.
 */
public class SimpleGameActionFactoryImpl implements SimpleGameActionFactory {
    /**
     * 
     */
    private static final long serialVersionUID = 2707527727675709457L;
    private static final String ERR_FILE_FNF_MSG = "Can't use provided file";
    private static final String ERR_FILE_IO_MSG = "Error while using provided file";
    private static final String ERR_FILE_WRONG_MSG = "The provided file can't be used for this action.";
    private final SimpleGame game;
    private final SimpleBoard board;
    private final SimplePlayerManager pManager;
    private final GroupViewer gViewer;
    private final FlippableSBEActionFactory flippableActionFactory;
    private final GroupSBEActionFactory groupElementActionFactory;
    private final GroupActionFactory groupActionFactory;
    private final ImageLoader imageLoader;

    /**
     * @param game
     *      the SimpleGame whici will ask actions from this factory
     * @param flippableActionFactory
     *      the game flippable element action factory.
     * @param groupElementActionFactory
     *      the game group element action factory.
     * @param groupActionFactory
     *      the game group action factory.
     * @param imageLoader
     *      the game image loader.
     */
    public SimpleGameActionFactoryImpl(final SimpleGame game, final FlippableSBEActionFactory flippableActionFactory,
            final GroupSBEActionFactory groupElementActionFactory, final GroupActionFactory groupActionFactory,
            final ImageLoader imageLoader) {
        super();
        this.game = game;
        this.board = this.game.getBoard();
        this.pManager = this.game.getPlayerManager();
        this.gViewer = this.game.getGroupViewer();
        this.flippableActionFactory = flippableActionFactory;
        this.groupElementActionFactory = groupElementActionFactory;
        this.groupActionFactory = groupActionFactory;
        this.imageLoader = imageLoader;
    }

    @Override
    public Action getAddPlayerAction() {
        return new AbstractAction("Add player") {
            private Optional<InputRequest<String>> nameIR = Optional.empty();
            private Optional<InputRequest<Boolean>> gmIR = Optional.empty();

            @Override
            public List<InputRequest<?>> getRequests(final InputRequestsFactory irFactory) {
                this.checkPerformable();
                nameIR = Optional.of(irFactory.getStringInputRequest("Player name", Optional.empty()));
                gmIR = Optional.of(irFactory.getBooleanInputRequest("Make gm", Optional.of(false)));
                return Arrays.asList(nameIR.get(), gmIR.get());
            }
            @Override
            public void perform() throws IllegalInputException {
                this.checkPerformable();
                final String name = Actions.checkPresentAndGet(this.nameIR);
                final boolean gm = Actions.checkPresentAndGet(this.gmIR);
                final SimplePlayer player = new SimplePlayer(name, gm);
                if (!pManager.addPlayer(player)) {
                    throw new IllegalInputException("A player with that name already exists");
                }
                pManager.addPlayerHand(player, new SimplePlayerHand(player, groupActionFactory));
                nameIR = Optional.empty();
                gmIR = Optional.empty();
            }
        };
    }
    @Override
    public Action getRemovePlayerAction() {
        return new AbstractAction("Remove player") {
            private Optional<InputRequest<SimplePlayer>> playerIR = Optional.empty();
            @Override
            public boolean canBePerformed() {
                return !pManager.getPlayers().isEmpty();
            }
            @Override
            public List<InputRequest<?>> getRequests(final InputRequestsFactory irFactory) {
                this.checkPerformable();
                playerIR = Optional.of(irFactory.getChoiceInputRequest(
                        "Player",
                        CollectionsUtils.mapWithDifferentStrings(pManager.getPlayers(), PlayerModel::getName),
                        Optional.empty()));
                return Arrays.asList(playerIR.get());
            }
            @Override
            public void perform() throws IllegalInputException {
                this.checkPerformable();
                if (!pManager.removePlayer(Actions.checkPresentAndGet(playerIR))) {
                    throw new IllegalInputException("Provided player is not valid");
                }
                playerIR = Optional.empty();
            }
        };
    }
    @Override
    public Action getLoadImageAction() {
        return new AbstractAction("Load image") {
            private Optional<InputRequest<File>> fileIR = Optional.empty();
            private Optional<InputRequest<String>> nameIr = Optional.empty();
            @Override
            public List<InputRequest<?>> getRequests(final InputRequestsFactory irFactory) {
                this.checkPerformable();
                this.fileIR = Optional.of(irFactory.getFileInputRequest("Image", false, FileType.IMAGE));
                this.nameIr = Optional.of(irFactory.getStringInputRequest("Image name", Optional.empty()));
                return Arrays.asList(this.fileIR.get(), this.nameIr.get());
            }
            @Override
            public void perform() throws IllegalInputException {
                this.checkPerformable();
                try {
                    imageLoader.loadImage(Actions.checkPresentAndGet(this.fileIR), Actions.checkAndGet(this.nameIr));
                } catch (IllegalArgumentException e) {
                    throw new IllegalInputException(e.getMessage());
                } catch (IOException e)  {
                    throw new IllegalInputException(ERR_FILE_IO_MSG);
                }
                fileIR = Optional.empty();
                nameIr = Optional.empty();
            }
        };
    }
    @Override
    public Action getShowHandAction() {
        return new AbstractAction("Show player hand") {
            private Optional<InputRequest<SimplePlayerHand>> handRequest = Optional.empty();
            @Override
            public boolean canBePerformed() {
                return pManager.getPlayingPlayer().isPresent()
                       && (pManager.getPlayerHand(pManager.getPlayingPlayer().get()).isPresent()
                          || (pManager.getPlayingPlayer().get().isGM()
                             && !pManager.getAllPlayersHand().values().isEmpty()));
            }
            @Override
            public List<InputRequest<?>> getRequests(final InputRequestsFactory irFactory) {
                this.checkPerformable();
                if (pManager.getPlayingPlayer().get().isGM()) {
                    handRequest = Optional.of(irFactory.getChoiceInputRequest(
                            "Player hand",
                            CollectionsUtils.mapWithDifferentStrings(pManager.getAllPlayersHand().values(),
                                    hand -> hand.getGroupName().orElse("(no name)")),
                            Optional.empty()));
                    return Arrays.asList(handRequest.get());
                }
                handRequest = Optional.empty();
                return Collections.emptyList();
            }
            @Override
            public void perform() throws IllegalInputException {
                this.checkPerformable();
                if (pManager.getPlayingPlayer().get().isGM()) {
                    gViewer.showSelectable(Actions.checkPresentAndGet(this.handRequest));
                    this.handRequest = Optional.empty();
                } else {
                    gViewer.showSelectable(pManager.getPlayerHand(pManager.getPlayingPlayer().get()).get());
                }
            }
        };
    }
    @Override
    public Action getSetTurnAction() {
        return new AbstractAction("Set turn") {
            private Optional<InputRequest<SimplePlayer>> playerIR = Optional.empty();
            @Override
            public boolean canBePerformed() {
                return !pManager.getPlayers().isEmpty();
            }
            @Override
            public List<InputRequest<?>> getRequests(final InputRequestsFactory irFactory) {
                this.checkPerformable();
                this.playerIR = Optional.of(irFactory.getChoiceInputRequest(
                        "Player",
                        CollectionsUtils.mapWithDifferentStrings(pManager.getPlayers(), PlayerModel::getName),
                        Optional.empty()));
                return Arrays.asList(this.playerIR.get());
            }
            @Override
            public void perform() throws IllegalInputException {
                this.checkPerformable();
                pManager.setActivePlayer(Actions.checkPresentAndGet(playerIR));
                this.playerIR = Optional.empty();
            }
        };
    }
    @Override
    public Action getAddBasicElementAction() {
        return new AbstractAction("Add element") {
            private Optional<InputRequest<String>> nameIR = Optional.empty();
            private Optional<InputRequest<WrappedImage>> imageIR = Optional.empty();

            @Override
            public List<InputRequest<?>> getRequests(final InputRequestsFactory irFactory) {
                this.checkPerformable();
                this.nameIR = Optional.of(irFactory.getStringInputRequest("Name", Optional.empty()));
                this.imageIR = Optional.of(
                        irFactory.getImageChoiceInputRequest("Image", imageLoader.getImagesMap(), Optional.empty()));
                return Arrays.asList(this.nameIR.get(), this.imageIR.get());
            }
            @Override
            public void perform() throws IllegalInputException {
                this.checkPerformable();
                final Optional<String> name = Actions.checkAndGet(this.nameIR);
                final Optional<WrappedImage> image = Actions.checkAndGet(this.imageIR);
                final BasicSBEBuilder builder = new BasicSBEBuilderImpl(board, flippableActionFactory);
                if (name.isPresent()) {
                    builder.setName(name.get());
                }
                if (image.isPresent()) {
                    builder.setImage(image.get());
                }
                board.addElement(builder.build());
                this.nameIR = Optional.empty();
                this.imageIR = Optional.empty();
            }
        };
    }
    @Override
    public Action getAddGroupElementAction() {
        return new AbstractAction("Add group element") {
            private Optional<InputRequest<String>> nameIR = Optional.empty();
            private Optional<InputRequest<WrappedImage>> imageIR = Optional.empty();

            @Override
            public List<InputRequest<?>> getRequests(final InputRequestsFactory irFactory) {
                this.checkPerformable();
                this.nameIR = Optional.of(irFactory.getStringInputRequest("Name", Optional.empty()));
                this.imageIR = Optional.of(
                        irFactory.getImageChoiceInputRequest("Image", imageLoader.getImagesMap(), Optional.empty()));
                return Arrays.asList(this.nameIR.get(), this.imageIR.get());
            }
            @Override
            public void perform() throws IllegalInputException {
                this.checkPerformable();
                final Optional<String> name = Actions.checkAndGet(this.nameIR);
                final Optional<WrappedImage> image = Actions.checkAndGet(this.imageIR);
                final GroupSBEBuilder builder = new GroupSBEBuilderImpl(groupElementActionFactory, groupActionFactory);
                if (name.isPresent()) {
                    builder.setName(name.get());
                }
                if (image.isPresent()) {
                    builder.setImage(image.get());
                }
                board.addElement(builder.build());
                this.nameIR = Optional.empty();
                this.imageIR = Optional.empty();
            }
        };
    }
    @Override
    public Action getAddFlippableElementAction() {
        return new AbstractAction("Add flippable element") {
            private Optional<InputRequest<String>> nameIR = Optional.empty();
            private Optional<InputRequest<WrappedImage>> imageIR = Optional.empty();
            private Optional<InputRequest<WrappedImage>> backImageIR = Optional.empty();

            @Override
            public List<InputRequest<?>> getRequests(final InputRequestsFactory irFactory) {
                this.checkPerformable();
                this.nameIR = Optional.of(irFactory.getStringInputRequest("Name", Optional.empty()));
                this.imageIR = Optional.of(
                        irFactory.getImageChoiceInputRequest("Front image", imageLoader.getImagesMap(), Optional.empty()));
                this.backImageIR = Optional.of(
                        irFactory.getImageChoiceInputRequest("Back image", imageLoader.getImagesMap(), Optional.empty())); 
                return Arrays.asList(this.nameIR.get(), this.imageIR.get(), this.backImageIR.get());
            }
            @Override
            public void perform() throws IllegalInputException {
                this.checkPerformable();
                final Optional<String> name = Actions.checkAndGet(this.nameIR);
                final Optional<WrappedImage> frontImage = Actions.checkAndGet(this.imageIR);
                final Optional<WrappedImage> backImage = Actions.checkAndGet(this.backImageIR);
                final FlippableSBEBuilder builder = new FlippableSBEBuilderImpl(board, flippableActionFactory);
                if (name.isPresent()) {
                    builder.setName(name.get());
                }
                if (frontImage.isPresent()) {
                    builder.setFrontImage(frontImage.get());
                }
                if (backImage.isPresent()) {
                    builder.setBackImage(backImage.get());
                }
                board.addElement(builder.build());
                this.nameIR = Optional.empty();
                this.imageIR = Optional.empty();
                this.backImageIR = Optional.empty();
            }
        };
    }
    @Override
    public Action getChangePlayerAction() {
        return new AbstractAction("Change player") {
            private Optional<InputRequest<SimplePlayer>> playerIR = Optional.empty();
            @Override
            public boolean canBePerformed() {
                return !pManager.getPlayers().isEmpty();
            }
            @Override
            public List<InputRequest<?>> getRequests(final InputRequestsFactory irFactory) {
                this.playerIR = Optional.of(irFactory.getChoiceInputRequest("Player",
                        CollectionsUtils.mapWithDifferentStrings(pManager.getPlayers(), PlayerModel::getName),
                        Optional.empty()));
                return Arrays.asList(playerIR.get());
            }
            @Override
            public void perform() throws IllegalInputException {
                pManager.setPlayingPlayer(Actions.checkPresentAndGet(this.playerIR));
            }
        };
    }
    @Override
    public Action getSaveAction() {
        return new AbstractAction("Save") {
            private Optional<InputRequest<File>> fileIR = Optional.empty();
            public List<InputRequest<?>> getRequests(final InputRequestsFactory irFactory) {
                this.fileIR = Optional.of(irFactory.getFileInputRequest("Save to", true, FileType.LITSAV));
                return Arrays.asList(this.fileIR.get());
            }
            @Override
            public void perform() throws IllegalInputException {
                final File saveFile = Actions.checkPresentAndGet(this.fileIR);
                try (ObjectOutputStream outputStream = new ObjectOutputStream(
                        new BufferedOutputStream(new FileOutputStream(saveFile)));) {
                    outputStream.writeObject(game);
                } catch (FileNotFoundException e) {
                    throw new IllegalInputException(ERR_FILE_FNF_MSG);
                } catch (IOException e) {
                    throw new IllegalInputException(ERR_FILE_IO_MSG);
                }
            }
        };
    }
    @Override
    public Action getImportAction() {
        return new AbstractAction("Import") {
            private Optional<InputRequest<File>> fileIR = Optional.empty();
            public List<InputRequest<?>> getRequests(final InputRequestsFactory irFactory) {
                this.fileIR = Optional.of(irFactory.getFileInputRequest("Import from", false, FileType.LITSAV));
                return Arrays.asList(this.fileIR.get());
            }
            @Override
            public void perform() throws IllegalInputException {
                final File file = Actions.checkPresentAndGet(this.fileIR);

                try (ObjectInputStream inputStream = new ObjectInputStream(
                        new BufferedInputStream(new FileInputStream(file)))) {
                    final Object readObj = inputStream.readObject();
                    if (readObj instanceof SimpleGame) {
                        final SimpleGame importedGame = (SimpleGame) readObj;
                        final List<SimpleBoardElement> importedElements = new ArrayList<>();
                        try {
                            importedElements.addAll(importedGame.getBoard().getElements().stream()
                                    .map(this::importElement).collect(Collectors.toList()));
                        } catch (IllegalArgumentException e) {
                            throw new IllegalInputException(ERR_FILE_WRONG_MSG);
                        }
                        importedElements.forEach(game.getBoard()::addElement);
                    } else {
                        throw new IllegalInputException(ERR_FILE_WRONG_MSG);
                    }
                } catch (FileNotFoundException e) {
                    throw new IllegalInputException(ERR_FILE_FNF_MSG);
                } catch (IOException e) {
                    throw new IllegalInputException(ERR_FILE_IO_MSG);
                } catch (ClassNotFoundException e) {
                    throw new IllegalInputException(ERR_FILE_WRONG_MSG);
                }
            }

            private SimpleBoardElement importElement(final SimpleBoardElement element) {
                if (element instanceof GroupSBEImpl) {
                    return new GroupSBEImpl((GroupSBEImpl) element, groupElementActionFactory, groupActionFactory, imageLoader);
                }
                if (element instanceof FlippableSBEImpl) {
                    return new FlippableSBEImpl((FlippableSBEImpl) element, board, flippableActionFactory, imageLoader);
                }
                if (element instanceof BasicSBEImpl) {
                    return new BasicSBEImpl((BasicSBEImpl) element, board, flippableActionFactory, imageLoader);
                }
                throw new IllegalArgumentException();
            }
        };
    }
}
