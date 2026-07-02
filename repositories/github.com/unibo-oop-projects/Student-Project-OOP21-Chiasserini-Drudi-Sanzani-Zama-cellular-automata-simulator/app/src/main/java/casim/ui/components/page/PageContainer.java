package casim.ui.components.page;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Optional;

import casim.utils.Empty;
import casim.utils.Result;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Window;

/**
 * A container that can hold multiple ui "pages" and shows the most recent one.
 */
public class PageContainer extends AnchorPane {

    private static final String NO_PREVIOUS_PAGE = "There is no previous page"; 
    private static final int MIN_FONT_SIZE = 15;
    private static final int MAX_FONT_SIZE = 40;
    //We want the pixel size to be ~20 at 1080p and it should change as: fontSize(pixels) = RATIO * pixels
    //With fontSize(1080 * 1920) = 20 we get RATIO ~= 0.00000964506
    private static final double PIXEL_FONT_RATIO = 0.000_009_645_06;

    private final Deque<Node> pages;
    private final Window owner;
    private Optional<Runnable> onClose = Optional.empty();

    /**
     * Construct a {@link PageContainer}.
     * 
     * @param owner the {@link Window} which own the {@link PageContainer}.
     */
    public PageContainer(final Window owner) {
        this.pages = new ArrayDeque<>();
        this.owner = owner;
        this.widthProperty().addListener(this.getSizeChangeHandler());
        this.heightProperty().addListener(this.getSizeChangeHandler());
    }

    /**
     * Get the owner.
     * 
     * @return the owner of the container.
     */
    public Window getOwner() {
        return this.owner;
    }

    /**
     * Sets a runnable to be run when the application is closed.
     * 
     * @param function the runnable to run.
     */
    public void setOnClose(final Runnable function) {
        this.onClose = Optional.of(function);
    }

    /**
     * Add a page to the container and show display it.
     * 
     * @param page the page to add.
     */
    public void addPage(final Node page) {
        this.pages.push(page);
        this.showCurrentPage();
    }

    /**
     * Remove the top page, and display the previous one if present.
     * Returns:
     *  - {@link Result} of {@link Empty} is there is no error.
     *  - {@link Result} with IllegalStateException if there is no previous page.
     * 
     * @return {@link Result} with the outcome of the operation.
     */
    public Result<Empty> popPage() {
        final var output = Result.ofEmpty()
            .require(x -> this.pages.size() > 1, new IllegalStateException(NO_PREVIOUS_PAGE));
        output.ifPresent(x -> {
            this.pages.pop();
            this.showCurrentPage();
        });

        return output;
    }

    /**
     * Method called on close.
     */
    public void close() {
        this.onClose.ifPresent(x -> x.run());
    }

    private void showCurrentPage() {
        this.getChildren().clear();
        if (!this.pages.isEmpty()) {
            this.getChildren().add(this.pages.peek());
        }
    }

    private <T> ChangeListener<T> getSizeChangeHandler() {
        return (final ObservableValue<? extends T> observable, final T oldValue, final T newValue) -> {
            final var fontSize = this.getFontSize(this.getWidth() * this.getHeight());
            this.setStyle("-fx-font-size: " + fontSize);
        };
    }

    private int getFontSize(final double pixels) {
        final var fontSize = Math.max(MIN_FONT_SIZE, Math.min(MAX_FONT_SIZE, pixels * PIXEL_FONT_RATIO));
        return (int) Math.round(fontSize);
    }
}
