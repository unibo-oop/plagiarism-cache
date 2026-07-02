package casim.ui.view.codi;

import casim.ui.components.page.PageContainer;
import casim.ui.utils.AlertBuilderImpl;

/**
 *  Utility class for CoDi view.
 */
public final class CoDiViewUtils {

    private static final String NEWLINE = System.lineSeparator();
    private static final String LAYER_INFO = "Remember you can change layer! (Default 0)"
            + NEWLINE + "A -> go left (-1)"
            + NEWLINE + "D -> go right (+1)";

    private CoDiViewUtils() {
    }

    /**
     * Create and show the initial alert of the CoDi's view.
     * 
     * @param container the container which holds the pages, used to get the owner of the alert.
     */
    public static void showStartAlert(final PageContainer container) {
        new AlertBuilderImpl()
        .buildDefaultInfo(LAYER_INFO, container.getOwner())
        .showAndWait();
    }

}
