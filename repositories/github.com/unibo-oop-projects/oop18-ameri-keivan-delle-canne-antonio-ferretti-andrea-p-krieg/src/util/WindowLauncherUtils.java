package util;

import java.util.List;

import controller.BottomSideMenuController;
import controller.BottomSideMenuControllerImpl;
import controller.MainControllerObserver;
import controller.MainControllerObserverImpl;
import controller.OverlayViewController;
import controller.OverlayViewControllerImpl;
import controller.TopSideMenuController;
import controller.map.MapControllerImpl;
import controller.selection.GameCommandsUsingSelection;
import controller.selection.GameCommandsUsingSelectionImpl;
import gameselectionmenu.controller.SelectionMenuController;
import gameselectionmenu.controller.SelectionMenuControllerImpl;
import gameselectionmenu.model.SelectionMenuModelImpl;
import gameselectionmenu.view.SelectionMenuViewImpl;
import javafx.util.Pair;
import model.gamerules.GameRules;
import model.player.Player;
import startmenu.controller.MainController;
import startmenu.controller.MainControllerImpl;
import startmenu.model.ModelControllerImpl;
import startmenu.view.ViewControllerImpl;
import view.BottomSideMenuView;
import view.MainView;
import view.MainViewImpl;
import view.OverlayView;
import view.TopSideMenuView;
import view.map.MapView;

public class WindowLauncherUtils {

    public static void mainMenu(final Pair<Double, Double> screenDimension) {
        final MainController main = new MainControllerImpl(new ViewControllerImpl(screenDimension), new ModelControllerImpl());
        main.showMenu();
    }

    public static void playerSelectMenuLauncher(final Pair<Double, Double> screenDimension, final GameRules gameMode) {
        final int defaulInitialPlayers = 2;
        final SelectionMenuController menu = new SelectionMenuControllerImpl(new SelectionMenuViewImpl(screenDimension),
                new SelectionMenuModelImpl(defaulInitialPlayers, gameMode));
        menu.showMenu();
    }

    public static void gameWithSideMenu(final Pair<Double, Double> screenDimension, final List<Player> players, final GameRules gameMode) {
        final GameCommandsUsingSelection model = new GameCommandsUsingSelectionImpl(players, gameMode);
        final TopSideMenuView topSideMenuView = new TopSideMenuView();
        final BottomSideMenuView bottomSideMenuView = new BottomSideMenuView();
        final OverlayView overlayView = new OverlayView();
        final MapView mapView = new MapView();
        final OverlayViewController overlayViewController = new OverlayViewControllerImpl();
        overlayView.setController(overlayViewController);
        final MainView mainView = new MainViewImpl(screenDimension, topSideMenuView, bottomSideMenuView, mapView);
        final TopSideMenuController topSideMenuController = new TopSideMenuController(topSideMenuView, model,
                overlayView, mainView);
        topSideMenuView.setMenuController(topSideMenuController);
        final BottomSideMenuController bottomSideMenuController = new BottomSideMenuControllerImpl(bottomSideMenuView,
                model);
        bottomSideMenuView.setMenuController(bottomSideMenuController);
        final MapControllerImpl mapController = new MapControllerImpl(mapView, model);
        mapView.setController(mapController);

        final MainControllerObserver main = new MainControllerObserverImpl(mainView);
        main.addController(mapController);
        main.addController(topSideMenuController);
        main.addController(bottomSideMenuController);
        topSideMenuController.addObserver(main);
        bottomSideMenuController.addObserver(main);
        mapController.addObserver(main);
        model.nextTurn();
        main.update();
    }
}
