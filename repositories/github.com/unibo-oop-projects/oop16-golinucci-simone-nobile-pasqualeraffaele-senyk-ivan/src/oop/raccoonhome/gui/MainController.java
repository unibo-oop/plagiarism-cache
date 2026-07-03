package oop.raccoonhome.gui;

import java.io.IOException;

import javax.annotation.PostConstruct;

import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialog.DialogTransition;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXPopup.PopupHPosition;
import com.jfoenix.controls.JFXPopup.PopupVPosition;
import com.jfoenix.controls.JFXRippler;

import io.datafx.controller.FXMLController;
import io.datafx.controller.flow.Flow;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.flow.FlowHandler;
import io.datafx.controller.flow.container.AnimatedFlowContainer;
import io.datafx.controller.flow.container.ContainerAnimations;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

/**
 * A Class that controls the Main View of RaccoonHome.
 */
@FXMLController(value = "/resources/fxml/Main.fxml", title = " Set Main Controller")

public class MainController {
    private static final int DURATE = 320;
    private static final int POPUPXINIT = -12;
    private static final int POPUPYINIT = 15;

    @FXMLViewFlowContext
    private ViewFlowContext context;

    @FXML
    private StackPane root;

    @FXML
    private StackPane titleBurgerContainer;
    @FXML
    private JFXHamburger titleBurger;

    @FXML
    private StackPane optionsBurger;
    @FXML
    private JFXRippler optionsRippler;

    @FXML
    private JFXDrawer drawer;
    @FXML
    private JFXPopup toolbarPopup;

    @FXML
    private JFXListView<?> toolbarPopupList;

    private FlowHandler flowHandler;
    private FlowHandler sideMenuFlowHandler;

    /**
     * 
     * @throws FlowException
     *             Throws an exception in case of loads Flow fails.
     */
    @PostConstruct
    public void init() throws FlowException {
        JFXDialog dialog = new JFXDialog();
        try {
            AnchorPane prova = FXMLLoader.load(getClass().getResource("/resources/dialog/Team.fxml"));
            dialog.setContent(prova);
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        // init the title hamburger icon whit the desired Animation

        drawer.setOnDrawerOpening((e) -> {
            titleBurger.getAnimation().setRate(1);
            titleBurger.getAnimation().play();
        });
        drawer.setOnDrawerClosing((e) -> {
            titleBurger.getAnimation().setRate(-1);
            titleBurger.getAnimation().play();
        });
        titleBurgerContainer.setOnMouseClicked((e) -> {
            if (drawer.isHidden() || drawer.isHidding()) {
                drawer.open();
            } else {
                drawer.close();
            }
        });

        // init Popup

        toolbarPopup.setPopupContainer(root);
        toolbarPopup.setSource(optionsRippler);
        root.getChildren().remove(toolbarPopup);

        optionsBurger.setOnMouseClicked((e) -> {
            toolbarPopup.show(PopupVPosition.TOP, PopupHPosition.RIGHT, POPUPXINIT, POPUPYINIT);
        });
        toolbarPopupList.setOnMouseClicked(event -> {
            if (toolbarPopupList.getSelectionModel().getSelectedIndex() == 0) {
                dialog.setTransitionType(DialogTransition.CENTER);
                dialog.show((StackPane) context.getRegisteredObject("ContentPane"));
            }
        });

        // create the inner flow

        context = new ViewFlowContext();

        // set the default controller shown at startUp

        Flow innerFlow = new Flow(HomePageController.class);

        flowHandler = innerFlow.createHandler(context);
        context.register("ContentFlowHandler", flowHandler);
        context.register("ContentFlow", innerFlow);
        drawer.setContent(
                flowHandler.start(new AnimatedFlowContainer(Duration.millis(DURATE), ContainerAnimations.SWIPE_LEFT)));
        context.register("ContentPane", drawer.getContent().get(0));

        // side controller will add links to the content flow

        Flow sideMenuFlow = new Flow(SideMenuController.class);
        sideMenuFlowHandler = sideMenuFlow.createHandler(context);
        drawer.setSidePane(sideMenuFlowHandler
                .start(new AnimatedFlowContainer(Duration.millis(DURATE), ContainerAnimations.SWIPE_LEFT)));
    }
}
