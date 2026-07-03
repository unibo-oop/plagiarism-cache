package oop.lit.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import oop.lit.model.ElementGroupModel;
import oop.lit.model.GameElementModel;
import oop.lit.util.Observer;

/**
 * A class used to update, in accordance to the Observer pattern, changes brought to groups of
 * objects of type GameElementView. 
 */
public class ElementGroupView implements Observer {

    private static final double SPACING = 20;
    private static final String ALREADY_INITIALIZED = "The Tab element has already been initialized!";
    private static final String NOT_INITIALIZED = "The Tab element has not been initialized!";

    private Tab tab;
    // Per maggiore chiarezza, il tab contiene al suo interno uno scrollPane che gli permette di allungarsi
    // in profondità a piacimento, mentre lo scrollPane contiene a sua volta una vertical box la quale fa in
    // modo che gli elementi visualizzati si impilino ordinatamente uno sopra l'altro.
    private final ScrollPane tabScroll = new ScrollPane();
    private final VBox viewBox;

    private final ElementGroupModel egm;
    private final Map<GameElementView, Pane> gevMap = new HashMap<>();

    /**
     * Public constructor to initialize the ElementGroupModel field, plus attaching the observer (the class
     * itself) to it and to build elementList as a group of elements in bijective correspondence to those
     * contained in the ElementGroupModel. 
     * 
     * @param group
     *         the ElementGroupModel supplied to the ElementGroupView class
     */
    public ElementGroupView(final ElementGroupModel group) {
        this.egm = group;
        this.egm.attach(this);
        this.viewBox = new VBox(SPACING);
        this.tabScroll.setContent(this.viewBox);
        this.tabScroll.setFitToHeight(true);
        this.tabScroll.setFitToWidth(true);
        this.viewBox.getChildren().addAll(this.retrieveImageView(this.gevMap.keySet()));
    }

    /**
     * The Tab element containing the requested element group is initialized here.
     * 
     * @throws IllegalStateException if the Tab has already been initialized
     */
    public void initTab() throws IllegalStateException {
        if (this.tab == null) {
            this.tab = new Tab(this.egm.getName().orElse("(no name)"));
            this.tab.setContent(tabScroll);
            this.notifyChange();
        } else {
            throw new IllegalStateException(ALREADY_INITIALIZED);
        }
    }

    /**
     * This method returns the tab containing the element group, but only if it was correctly
     * initialized, otherwise it throws an exception.
     * 
     * @return a new, correctly initialized Tab
     * 
     * @throws IllegalStateException if the Tab element was not initialized.
     */
    public Tab getTab() throws IllegalStateException {
        if (this.tab == null) {
            throw new IllegalStateException(NOT_INITIALIZED);
        }
        return this.tab;
    }

    /**
     * This method prepares the provided GameElementModel for being displayed.
     * 
     * @param element
     *         to initialize a new GameElementView by supplying it to the class constructor
     *
     * @return
     *         a new GameElementView created from the supplied parameter
     */
    protected GameElementView getGameElementView(final GameElementModel element) {
        final GameElementView res = new GameElementView(element);
        res.getImageView().setPreserveRatio(true);
        res.getImageView().fitWidthProperty().bind(this.tabScroll.widthProperty());
        return res;
    }

    /**
     * This method receives in input a list of GameElementView, retrieves the ImageView field in
     * each and every one of its items and collects them all in a new list of ImageView. 
     * 
     * @param wrappers
     *         the list of GameElementView we want to extract the ImageViews from
     * @return 
     *         the list of ImageView we were looking for
     */
    protected final List<ImageView> retrieveImageView(final Set<GameElementView> wrappers) {
        return wrappers.stream()
                      .map(e -> e.getImageView())
                      .collect(Collectors.toList());
    }

    /** 
     * This method receives in input a list of GameElementModel and finds the gameElementView items in gevList
     * that have matching fields of type gameElementModel.
     * 
     * @param searched
     *         a list of objects of type GameElementModel
     *
     * @return 
     *         a sub-list of the field bevList
     */
    protected List<GameElementView> retrieveGameElementView(final List<GameElementModel> searched) {
        return this.gevMap.keySet().stream()
                .filter(e -> searched.contains(e.getGameElementModel())).collect(Collectors.toList());
    }

    @Override
    public void notifyChange() {
        final List<GameElementModel> groupElements = new ArrayList<>(this.egm.getElements());
        final List<GameElementModel> viewElements = this.gevMap.keySet().stream()
                                                                .map(GameElementView::getGameElementModel)
                                                                .collect(Collectors.toList());

        final List<GameElementModel> addedElements = new ArrayList<>(groupElements);
        addedElements.removeAll(viewElements);
        final List<GameElementView> addedView = addedElements.stream()
                                                             .map(element -> getGameElementView(element))
                                                             .collect(Collectors.toList());
        final Map<GameElementView, Pane> addedMap = addedView.stream()
                .collect(Collectors.toMap(Function.identity(), e -> {
                    final Pane res = new StackPane();
                    res.getChildren().add(e.getSelectionVisualization());
                    res.getChildren().add(e.getImageView());
                    return res;
                }));
        this.viewBox.getChildren().addAll(addedMap.values());
        this.gevMap.putAll(addedMap);

        final List<GameElementModel> removedElements = new ArrayList<>(viewElements);
        removedElements.removeAll(groupElements);
        final List<GameElementView> removedGEV = this.retrieveGameElementView(removedElements);
        for (final GameElementView gev : removedGEV) {
            final Pane tmp = this.gevMap.remove(gev);
            this.viewBox.getChildren().remove(tmp);
        }
        removedGEV.forEach(GameElementView::removed);
    }

    /**
     * To be called when the tab is closed.
     */
    public void removed() {
        this.egm.detach(this);
    }

    /**
     * Closes the tab relative to this ElementGroup.
     */
    public void closeTab() {
        if (this.tab.getTabPane() != null) { //se non è null vuol dire che non è stato l'utente a chiuderlo, ma il model a richiederlo; devo quindi effettivamente rimuovere il tab.
            final EventHandler<Event> handler = this.tab.getOnClosed();
            this.tab.getTabPane().getTabs().remove(this.tab);
            if (null != handler) {
                handler.handle(null);
            }
        }
    }

    /**
     * A getter for the gevList field.
     * 
     * @return the list of gameElementView items
     */
    protected Set<GameElementView> getGevSet() {
        return new HashSet<>(this.gevMap.keySet());
    }

}
