package oop.lit.view;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.shape.Rectangle;
import oop.lit.model.BoardElementModel;
import oop.lit.model.BoardModel;
import oop.lit.util.Observer;
import oop.lit.view.controller.ViewControllerManager;

/**
 * Wrapper for Board Model.
 */
public class BoardView implements Observer {

    private static final String ALREADY_INITIALIZED = "The Pane element has already been initialized!";
    private static final String NOT_INITIALIZED = "The Pane element has not been initialized!";

    private Pane boardPane;
    private final Camera camera;
    private final BoardModel board;
    private final List<BoardElementView> bevList = new ArrayList<>();
    private final ViewControllerManager vcm;

    /*
     * private final ElementGroupModel groupT = new ElementGroupModel(new
     * TestGroup(Optional.of("Gruppo generico"))); private final
     * ElementGroupView egv = new ElementGroupView(groupT, new Tab()); private
     * final TestElement test = new TestElement(Optional.of("Im256"),
     * "256.png"); private final TestElement test2 = new
     * TestElement(Optional.of("Im256"), "256.png"); private final
     * BoardElementView gev = new BoardElementView(test, egv); private final
     * BoardElementView gev2 = new BoardElementView(test2, egv);
     */

    /**
     * The class constructor.
     *
     * @param board
     *         the game board, which notifies observers when contained or
     *         selected elements change
     * @param vcm
     *         the ViewControllerManagerImpl behind this application's view.
     * @param camera
     *         the camera associated with this board, to reach any element in its
     *         theoretically infinite surface
     */
    public BoardView(final BoardModel board, final ViewControllerManager vcm, final Camera camera) {
        this.camera = camera;
        this.vcm = vcm;
        this.board = board;
    }

    // Questo metodo fa sì che gli elementi di gioco siano visualizzabili solo ed esclusivamente all'interno
    // della BoardView.
    private static void clipChildren(final Region region) {
        final Rectangle outputClip = new Rectangle();
        region.setClip(outputClip);
        region.layoutBoundsProperty().addListener((ov, oldValue, newValue) -> {
            outputClip.setWidth(newValue.getWidth());
            outputClip.setHeight(newValue.getHeight());
        });
    }

    /**
     * The Pane element representing the game board of this application is initialized here. 
     * 
     * @throws IllegalStateException if the Pane has already been initialized
     */
    public void initPane() throws IllegalStateException {
        if (this.boardPane == null) {
            this.boardPane = new Pane();
            clipChildren(this.boardPane);
            this.board.attach(this);
            this.notifyChange();
        } else {
            throw new IllegalArgumentException(ALREADY_INITIALIZED);
        }
    }

    /**
     * 
     * 
     * @return a new, correctly initialized TabPane
     * 
     * @throws IllegalStateException if the TabPane element was not initialized.
     */
    public Pane getPane() throws IllegalStateException {
        if (this.boardPane == null) {
            throw new IllegalStateException(NOT_INITIALIZED);
        }
        return this.boardPane;
    }

    // This method receives in input a list of boardElementModel and finds the boardElementView items
    // in bevList that have matching fields of type boardElementModel.
    private List<BoardElementView> retrieveBoardElementView(final List<BoardElementModel> searched) {
        return this.bevList.stream()
                .filter(e -> searched.contains(e.getBoardElement())).collect(Collectors.toList());
    }

    @Override
    public void notifyChange() {
        for (final BoardElementView bev : this.bevList) {
            bev.setSelectionItems(false);
            this.board.getSelectedBoardElements().remove(bev.getRotateVisualization());
            this.board.getSelectedBoardElements().remove(bev.getSelectionVisualization());
        }

        final List<BoardElementModel> boardElements = new ArrayList<>(this.board.getBoardElements());
        final List<BoardElementModel> viewElements = this.bevList.stream().map(BoardElementView::getBoardElement)
                .collect(Collectors.toList());

        final List<BoardElementModel> addedElements = new ArrayList<>(boardElements);
        addedElements.removeAll(viewElements);

        addedElements.stream().map(this::setBEV).forEach(bev -> {
            this.bevList.add(bev);
            this.boardPane.getChildren().add(bev.getSelectionVisualization());
            this.boardPane.getChildren().add(bev.getImageView());
            this.boardPane.getChildren().add(bev.getRotateVisualization());
        });

        final List<BoardElementModel> removedElements = new ArrayList<>(viewElements);
        removedElements.removeAll(boardElements);

        final List<BoardElementView> removedBEV = retrieveBoardElementView(removedElements);
        this.bevList.removeAll(removedBEV);
        for (final BoardElementView element : removedBEV) {
            this.boardPane.getChildren().remove(element.getImageView());
            this.boardPane.getChildren().remove(element.getRotateVisualization());
            this.boardPane.getChildren().remove(element.getSelectionVisualization());
        }
        removedBEV.forEach(GameElementView::removed);

        final List<BoardElementView> toSelect = this.retrieveBoardElementView(this.board.getSelectedBoardElements());
        for (final BoardElementView bev : toSelect) {
            bev.setSelectionItems(true);
        }
    }

    private BoardElementView setBEV(final BoardElementModel bem) {
        final BoardElementView bev = new BoardElementView(bem, camera);
        vcm.getSelectionAndAction().editSelectionAndActionOnSource(bev, this.board);
        vcm.getDragAndDrop().makeSourceDraggableAndDroppable(bev, this.board);
        vcm.getRotate().makeSourceRotatable(bev);
        vcm.getResize().makeSourceResizable(bev);
        return bev;
    }

    /**
     * A getter for the bevList field.
     * 
     * @return the list of boardElementView items
     */
    public List<BoardElementView> getBevList() {
        return new ArrayList<>(this.bevList);
    }
}
