package com.jlearn.view.factories;

import org.kordamp.ikonli.material.Material;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXPopup;
import com.jlearn.view.screens.FXMLScreens;
import com.jlearn.view.utilities.ViewUtilities;
import com.jlearn.view.utilities.enums.IconDim;

import javafx.scene.control.Label;

/**
 * {@link GeneralFactory} that creates general components.
 */
public final class GeneralFactory {
    private static final int DIM_LIST_VIEW_WIDTH = 100;

    private GeneralFactory() {
    }

    /**
     * Build the Popup.
     *
     * @param menuButton
     *            the button menu
     * @param popup
     *            the {@link JFXPopup}
     * @param menuListView
     *            the {@link JFXListView}
     * @param screen
     *            the {@link FXMLScreens}
     */
    public static void buildPopup(final JFXButton menuButton, final JFXPopup popup,
            final JFXListView<Label> menuListView, final FXMLScreens screen) {
        final String help = "Help";
        final Label menu1;
        final Label menu2;
        final Label menu3;
        final Label menu4;
        final Label menu5;
        final Label menu6;
        final Label menu7;
        final Label menu8;

        menu2 = new Label("Resize App");
        menu3 = new Label("Full Screen");
        menu4 = new Label("Full Screen Off");
        menu5 = new Label("Toggle FX Audio");
        menu6 = new Label("Change FX Volume");
        menu7 = new Label("Toggle Voice Command");
        menu8 = new Label("Exit");

        menu2.setGraphic(ViewUtilities.iconSetter(Material.TRANSFORM, IconDim.SMALL));
        menu3.setGraphic(ViewUtilities.iconSetter(Material.FULLSCREEN, IconDim.SMALL));
        menu4.setGraphic(ViewUtilities.iconSetter(Material.FULLSCREEN_EXIT, IconDim.SMALL));
        menu5.setGraphic(ViewUtilities.iconSetter(Material.VOLUME_OFF, IconDim.SMALL));
        menu6.setGraphic(ViewUtilities.iconSetter(Material.VOLUME_DOWN, IconDim.SMALL));
        menu7.setGraphic(ViewUtilities.iconSetter(Material.MIC, IconDim.SMALL));
        menu8.setGraphic(ViewUtilities.iconSetter(Material.EXIT_TO_APP, IconDim.SMALL));

        switch (screen) {
        case MENU:
            menu1 = new Label("About");
            menu1.setGraphic(ViewUtilities.iconSetter(Material.HELP, IconDim.SMALL));
            menuListView.getItems().addAll(menu1, menu2, menu3, menu4, menu5, menu6, menu7, menu8);
            break;
        case THEORY:
            menu1 = new Label(help);
            menu1.setGraphic(ViewUtilities.iconSetter(Material.HELP, IconDim.SMALL));
            menuListView.getItems().addAll(menu1, menu2, menu3, menu4, menu5, menu6, menu7, menu8);
            break;
        case EXERCISE:
            menu1 = new Label(help);
            menu1.setGraphic(ViewUtilities.iconSetter(Material.HELP, IconDim.SMALL));
            menuListView.getItems().addAll(menu1, menu2, menu3, menu4, menu5, menu6, menu7, menu8);
            break;
        default:
        }
        menuListView.setMinSize(DIM_LIST_VIEW_WIDTH, 0);
        popup.setPopupContent(menuListView);
        menuListView.setId("popup-list-view");
        popup.setId("popup-list-view");
        menuListView.getItems().forEach(e -> {
            e.setId("label-list-view");
        });
    }
}
