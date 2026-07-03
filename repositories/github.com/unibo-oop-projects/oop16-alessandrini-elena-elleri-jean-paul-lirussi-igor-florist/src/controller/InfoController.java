package controller;

import view.InfoView;

/**
 * 
 * This class allows {@link InfoView} to view all the
 * developer and application info.
 *
 */
public final class InfoController {
    private final InfoView view;
    private static final InfoController SINGLETON = new InfoController();

    private InfoController() {
        this.view = new InfoView();
    }

    /**
     * This method return the SINGLETON.
     * 
     * @return SINGLETON
     *          return the SINGLETON
     */
    public static InfoController getInstance() {
        return SINGLETON;
    }

    /**
     * This method return the {@link InfoView}.
     * 
     * @return view
     *          {@link InfoView}
     */
    public InfoView getInfoView() {
        return this.view;
    }
}
