package it.unibo.aknightstale.controllers.interfaces;

import it.unibo.aknightstale.views.interfaces.MainMenuView;

public interface MainMenuController extends Controller<MainMenuView> {
    void showScoreboard();

    void showMapView();
}
