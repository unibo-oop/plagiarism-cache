package view.navy_builder_interface;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import controller.navy_builder.NavyBuilderUIController;
import controller.navy_builder.NavyBuilderUIControllerImpl;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import model.basic_component.StaticPoint2D;
import model.basic_component.StaticPoint2DImpl;
/**
 * Implementation of {@link NavyBuilderUI} using the JAVAFX framework.
 */
public final class NavyBuilderUIImpl implements NavyBuilderUI {

    @FXML private GridPane navyGrid;
    @FXML private GridPane informationGrid;
    @FXML private Button buttonReset;
    @FXML private Button buttonConfirm;

    private final List<Pair<Button, StaticPoint2D>> gridButtons = new ArrayList<>();
    private final List<Label> missingLabelList = new ArrayList<>();
    private final List<Button> sizeButtonList = new ArrayList<>();

    private final NavyBuilderUIController controller = new NavyBuilderUIControllerImpl(this);


    @FXML
    @Override
    public void initialize() {
        //grid construction
        for (int x = 0; x < controller.getGridSize(); x++) {
            for (int y = 0; y < controller.getGridSize(); y++) {
                final int xGrid = x;
                final int yGrid = y;
                final Button b = new Button();
                final StaticPoint2D point = new StaticPoint2DImpl(xGrid, yGrid);
                gridButtons.add(new ImmutablePair<>(b, point));
                b.setOnAction(e -> {
                    controller.setCoord(point);
                });
                //b.setPrefSize(BUTTON_GRID_SIZE, BUTTON_GRID_SIZE);ù
                b.setPrefHeight(Integer.MAX_VALUE);
                b.setPrefWidth(Integer.MAX_VALUE);
                navyGrid.add(b, xGrid, yGrid);
            }
        }
        navyGrid.setPrefWidth(navyGrid.getPrefHeight());
        //sidepanel construction
        IntStream.range(0, controller.getMissingShipCount().size()).forEach(i -> {
            final Button addShipButton = new Button("Add Ship of size " + (i + 1));
            sizeButtonList.add(addShipButton);
            addShipButton.setOnAction(e -> {
                controller.setCurrentShipSize(i + 1);
                update();
            });
            GridPane.setMargin(addShipButton, new Insets(10, 10, 10, 10));
            missingLabelList.add(new Label());
            GridPane.setMargin(missingLabelList.get(i), new Insets(10, 10, 10, 10));
            informationGrid.add(addShipButton, 0, i);
            informationGrid.add(new Label("Missing: "), 1, i);
            informationGrid.add(missingLabelList.get(i), 2, i);
        });
        update();
        disableConfirm();
    }

    /**
     * Disable all the buttons which are not available.
     * @param availabelPoint
     */
    @Override
    public void update() {
        setButtonStatus();
        setButtonColor();
        setMissingShip();
        setButtonMissingList();
        setButtonConfirm();
    }

    private void setButtonConfirm() {
        if (controller.canConfirm()) {
            enableConfirm();
        } else {
            disableConfirm();
        }
    }

    private void setButtonMissingList() {
        IntStream.range(0, controller.getMissingShipCount().size()).forEach(index -> {
            sizeButtonList.get(index).setDisable(controller.getMissingShipCount().get(index) == 0);
        });
    }

    @Override
    @FXML
    public void reset() {
        controller.reset();
        update();
    }

    @Override
    @FXML
    public void confirm() {
        controller.confirm();
    }

    private void setMissingShip() {
        IntStream.range(0, controller.getMissingShipCount().size()).forEach(i -> missingLabelList.get(i).setText(String.valueOf(controller.getMissingShipCount().get(i))));
    }

    private void setButtonColor() {
        gridButtons.forEach(b -> {
            b.getKey().setStyle(controller.getOccupiedPosition().contains(b.getValue()) ? "-fx-background-color: red;" : "");

        });
    }

    private void setButtonStatus() {
        gridButtons.forEach(b -> b.getKey().setDisable(!controller.getAvaiableCoord().contains(b.getValue())));
    }

    @Override
    public void random() {
        controller.newRandomDisposition();
    }

    @Override
    public void enableConfirm() {
        buttonConfirm.setDisable(false);
    }

    @Override
    public void disableConfirm() {
        buttonConfirm.setDisable(true);
    }

}
