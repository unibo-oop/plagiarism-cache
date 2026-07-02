package casim.ui.components.page;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.shape.Circle;

/**
 * Test class for {@link PageContainer}.
 */
class PageContainerTest {

    private final Node testCanvas = new Canvas();
    private final Node testShape = new Circle();

    /**
     * Test {@link PageContainer#popPage()} method.
     */ 
    @Test
    void testPopPage() {
        final var container = new PageContainer(null);
        assertThrows(NoSuchElementException.class, () -> container.popPage().getValue());
        assertEquals(IllegalStateException.class, container.popPage().getError().getClass());
        container.addPage(this.testCanvas);
        container.addPage(this.testShape);
        assertTrue(container.popPage().isPresent());
        assertThrows(NoSuchElementException.class, () -> container.popPage().getValue());
    }
}
