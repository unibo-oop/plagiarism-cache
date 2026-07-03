package oop.lit.view;

import javafx.beans.value.ChangeListener;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import oop.lit.model.BoardElementModel;
import oop.lit.util.Vector2D;
import oop.lit.util.Vector2DImpl;

/**
 * An object used to show a BoardElement to the user.
 */
public class BoardElementView extends GameElementView {

    /**
     * Width of the ImageView border. It's a protected field so that it can be
     * accessed in the subclasses of GameElementView too.
     */
    private static final double RECTANGLE_STROKE_WIDTH = 10.0;
    private static final double CIRCLE_RADIUS = 8.0;
    private static final double CIRCLE_OFFSET = 30.0;

    private final BoardElementModel bem;
    private final Circle circle;
    private final Rectangle rectangle;
    private final Camera camera;
    private final ChangeListener<Number> cameraObserver = (observable, oldValue, newValue) -> {
        this.refresh();
    };
    private boolean dragAndDrop = true;
    private boolean resize = true;
    private double width = this.getImageView().getImage().getWidth();
    private double height = this.getImageView().getImage().getHeight();

    /**
     * Public constructor for initializing the BoardElementModel field,
     * attaching an observer (the class itself) to it, defining its
     * characteristics (position, size and rotation angle) and those of the
     * field of type Circle.
     * 
     * @param bem
     *         to create a field which has access to all the methods of the
     *         BoardElementModel interface
     * @param camera
     *         the camera associated with the view of the elements of the board.
     */
    public BoardElementView(final BoardElementModel bem, final Camera camera) {
        super(bem);
        this.camera = camera;
        this.camera.getNodeOffsetXproperty().addListener(cameraObserver);
        this.camera.getNodeOffsetYproperty().addListener(cameraObserver);
        this.bem = bem;
        this.bem.attach(this);
        this.circle = new Circle();
        this.rectangle = new Rectangle();
        this.rectangle.setFill(Color.TRANSPARENT);
        this.rectangle.setStrokeWidth(RECTANGLE_STROKE_WIDTH);
        this.rectangle.setStroke(Color.AQUAMARINE);
        this.circle.setRadius(CIRCLE_RADIUS);
        this.circle.setFill(Color.GREEN);
        this.rectangle.setVisible(false);
        this.circle.setVisible(false);
        this.refresh();
    }

    /**
     * The main method for the correct implementation of the Observer pattern.
     * First, it calls the method of the same name in the superclass to update
     * the GameElementModel, then the refresh() method to notify the changes
     * in size, location and rotation of the BoardElementModel.
     */
    @Override
    public void notifyChange() {
        super.notifyChange();
        this.refresh();
    }

    // Esegue il "refresh", l'aggiornamento delle caratteristiche del BoardElementModel alla situazione
    // corrente, poi fa lo stesso per i campi oldPosition e oldRotation che serviranno alla prossima
    // trasformazione (di rotazione o traslazione).
    private void refresh() {
        this.refreshTranslate();
        this.getImageView().setRotate(bem.getRotation());
        this.setSelectionBorder();
        this.width = this.getImageView().getImage().getWidth() * bem.getScale();
        this.height = this.getImageView().getImage().getHeight() * bem.getScale();
        this.getImageView().setScaleX(bem.getScale());
        this.getImageView().setScaleY(bem.getScale());
        this.setCircle();

    }

    private void refreshTranslate() {
        this.getImageView().setTranslateX(bem.getPosition().getX() + camera.getNodeOffsetXproperty().doubleValue());
        this.getImageView().setTranslateY(bem.getPosition().getY() + camera.getNodeOffsetYproperty().doubleValue());
    }

    // L'override del metodo setSelectionBorder nella superclasse fa sì che nella board gli elementi
    // selezionati non presentino solo il riquadro evidenziato, ma anche il cerchietto che permette
    // all'utente di manipolarli.
    @Override
    public void setSelectionItems(final boolean state) {
        this.rectangle.setVisible(state);
        this.circle.setVisible(state);
    }

    // Il rettangolo di selezione, dopo essere stato inizializzato nella superclasse, è reso traslabile,
    // ruotabile e scalabile.
    private void setSelectionBorder() {
        this.rectangle.setTranslateX((this.getImageView().translateXProperty().getValue() - RECTANGLE_STROKE_WIDTH / 2)
                - (((this.bem.getScale() - 1) / 2) * this.getImageView().getImage().getWidth()));
        this.rectangle.setTranslateY((this.getImageView().translateYProperty().getValue() - RECTANGLE_STROKE_WIDTH / 2)
                - (((this.bem.getScale() - 1) / 2) * this.getImageView().getImage().getHeight()));
        this.rectangle.setWidth(this.bem.getScale() * this.getImageView().getImage().getWidth()
                + RECTANGLE_STROKE_WIDTH);
        this.rectangle.setHeight(this.bem.getScale() * this.getImageView().getImage().getHeight()
                + RECTANGLE_STROKE_WIDTH);
        this.rectangle.setRotate(this.getImageView().rotateProperty().getValue());
    }

    /**
     * @return the wrapped element.
     */
    public BoardElementModel getBoardElement() {
        return this.bem;
    }

    private void setCircle() {
        this.circle.setCenterX(getPosition().getX()
                + ((Math.sin(Math.toRadians(bem.getRotation()))) * ((height / 2) + CIRCLE_OFFSET)));
        this.circle.setCenterY(getPosition().getY()
                - ((Math.cos(Math.toRadians(bem.getRotation()))) * ((height / 2) + CIRCLE_OFFSET)));
    }

    /**
     * @return the value of the DragAndDrop boolean
     */
    public final boolean isDragAndDrop() {
        return dragAndDrop;
    }

    /**
     * @param dragAndDrop
     *         the dragAndDrop to set
     */
    public final void setDragAndDrop(final boolean dragAndDrop) {
        this.dragAndDrop = dragAndDrop;
    }

    /**
     * @return the vale of the isResize boolean
     */
    public final boolean isResize() {
        return resize;
    }

    /**
     * @param resize
     *         the resize to set
     */
    public final void setResize(final boolean resize) {
        this.resize = resize;
    }

    /**
     * @return the center position of this element.
     */
    public Vector2D getPosition() {
        return new Vector2DImpl(super.getImageView().getBoundsInParent().getMinX()
                + super.getImageView().getBoundsInParent().getWidth() / 2, super.getImageView().getBoundsInParent()
                .getMinY()
                + super.getImageView().getBoundsInParent().getHeight() / 2);
    }

    /**
     * @return the center position of this element.
     */
    public Vector2D getScenePosition() {
        final Bounds boundsInScene = super.getImageView().localToScene(super.getImageView().getBoundsInLocal());
        return new Vector2DImpl(boundsInScene.getMinX() + boundsInScene.getWidth() / 2, boundsInScene.getMinY()
                + boundsInScene.getHeight() / 2);
    }

    /**
     * @return the center position of the node used to visualize rotation.
     */
    public Vector2D getRotatePosition() {
        return new Vector2DImpl(circle.getCenterX(), circle.getCenterY());
    }

    /**
     * @return the actual height of the image.
     */
    public final double getHeight() {
        return this.height;
    }

    /**
     * @return the actual width of the image.
     */
    public final double getWidth() {
        return this.width;
    }

    /**
     * @return the object (in this case a Circle) used to allow rotation as a Node.
     */
    public Node getRotateVisualization() {
        return this.circle;
    }

    @Override
    public Node getSelectionVisualization() {
        return rectangle;
    }

    @Override
    public void removed() {
        super.removed();
        this.camera.getNodeOffsetXproperty().removeListener(cameraObserver);
        this.camera.getNodeOffsetYproperty().removeListener(cameraObserver);
    }
}
