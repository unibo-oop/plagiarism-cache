package jwhale.view.controller;

import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import jwhale.controller.ContainerPreView;
/**
 * List Cell customization for listview in environment scene.
 */
public class ContainerCell extends ListCell<ContainerPreView> {
    private final Text name = new Text();
    private final Text image = new Text();
    private final Text status = new Text();
    private final Text network = new Text();
    private final Text volume = new Text();
    private final Text creationTime = new Text();
    private final VBox basicBox = new VBox(name, image);
    private final VBox itemBox = new VBox(network, volume);
    private final VBox statusBox = new VBox(status, creationTime);
    private final HBox row;
    private static final Double HBOX_SPACE = 20.0;

    public ContainerCell() {
        super();
        row = new HBox(basicBox, statusBox, itemBox);
        row.setSpacing(HBOX_SPACE);
    }

    protected final void updateItem(final ContainerPreView container, final boolean b) {
        super.updateItem(container, b);
        if (!b || container != null) {
            name.setText("Name: " + container.getName());
            image.setText("Image: " + container.getImage());
            status.setText("Status: " + container.getStatus());
            creationTime.setText("Date: " + container.getCreationTime());
            network.setText("Network: " + container.getNetwork());
            volume.setText("Volume: " + container.getVolume());
            setGraphic(row);
        } else {
            setGraphic(null);
        }
    }

}
