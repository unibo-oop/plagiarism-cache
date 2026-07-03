package oop.raccoonhome.gui;

import javax.annotation.PostConstruct;

import com.jfoenix.controls.JFXListView;

import io.datafx.controller.FXMLController;
import io.datafx.controller.flow.Flow;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.flow.FlowHandler;
import io.datafx.controller.flow.action.ActionTrigger;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;

/**
 *  A Class that controls Side menu actions.
 */
@FXMLController(value = "/resources/fxml/SideMenu.fxml", title = "Side Menu Controller")

public class SideMenuController {

    @FXMLViewFlowContext
    private ViewFlowContext context;

    // reference to SideMenu.fxml of ListView

    @FXML
    @ActionTrigger("BedRoom")
    private Label button;
    @FXML
    @ActionTrigger("Sala")
    private Label sala;
    @FXML
    @ActionTrigger("Bagno")
    private Label bagno;
    @FXML
    @ActionTrigger("Garage")
    private Label garage;
    @FXML
    @ActionTrigger("Virtualizzazzione")
    private Label virtualize;
    @FXML
    @ActionTrigger("Home")
    private Label home;

    @FXML
    private JFXListView<Label> sideList;

    /**
     * 
     * @throws FlowException
     *             Throws a Flow Exception in case the handling of Flow Container fails
     */
    @PostConstruct
    public void init() throws FlowException  {

        // Init the action to select a side menu option and loads it into the parent flow

        FlowHandler contentFlowHandler = (FlowHandler) context.getRegisteredObject("ContentFlowHandler");
        sideList.propagateMouseEventsToParent();
        sideList.setOnMouseClicked(event -> {
            try {
                contentFlowHandler.handle(sideList.getSelectionModel().getSelectedItem().getId());
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
        Flow contentFlow = (Flow) context.getRegisteredObject("ContentFlow");

        // binding Controller Class to a label of list view in Side Menu

        bindNodeToController(button, BedRoomController.class, contentFlow, contentFlowHandler);
        bindNodeToController(bagno, BathRoomController.class, contentFlow, contentFlowHandler);
        bindNodeToController(garage, GarageController.class, contentFlow, contentFlowHandler);
        bindNodeToController(sala, LivingRoomController.class, contentFlow, contentFlowHandler);
        bindNodeToController(virtualize, VirtualizeController.class, contentFlow, contentFlowHandler);

    }

    /**
     *  A Method that binds Node of list view to a determinate controller.
     * @param node
     *  The node ID
     * @param controllerClass
     *  Controller that i want to load
     * @param flow
     *  The Flow where controller Loads
     * @param flowHandler
     *  the Handler that manage the Flow Actions
     */


    private void bindNodeToController(final Node node, final Class<?> controllerClass, final Flow flow,
            final FlowHandler flowHandler) {
        flow.withGlobalLink(node.getId(), controllerClass);
    }

}
