package it.unibo.aknightstale.views.factories;

import it.unibo.aknightstale.controllers.interfaces.MainMenuController;
import it.unibo.aknightstale.views.BaseViewTest;
import it.unibo.aknightstale.views.interfaces.MainMenuView;
import it.unibo.aknightstale.views.interfaces.View;
import javafx.stage.Stage;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

/**
 * Test class for {@link ViewFactory}.
 */
@ExtendWith(ApplicationExtension.class)
class ViewFactoryTest extends BaseViewTest<MainMenuController, MainMenuView> {

    ViewFactoryTest() {
        super(MainMenuView.class, MainMenuController.class);
    }

    @Start
    @Override
    public void start(final Stage stage) {
        super.start(stage);
    }

    /**
     * Test executed with MainMenuView.
     */
    @Test
    @DisplayName("Test loadView()")
    void loadView() {
        final var view = View.of(MainMenuView.class).get();
        Assertions.assertThat(view).isInstanceOf(MainMenuView.class);
    }
}
