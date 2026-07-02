package jwhale.view.controller;

import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import jwhale.controller.EnvPreView;
/**
 * List Cell customization for listview in environment scene.
 */
public class EnvCell extends ListCell<EnvPreView> {
    private final Text name = new Text();
    private final Text address = new Text();
    private final VBox envInfo = new VBox(name, address);
    private final HBox row;
    private static final Double HBOX_SPACE = 20.0;

    public EnvCell() {
        super();
        row = new HBox(envInfo);
        row.setSpacing(HBOX_SPACE);
    }

    protected final void updateItem(final EnvPreView env, final boolean b) {
        super.updateItem(env, b);
        if (!b || env != null) {
            name.setText(env.getName());
            address.setText(env.getUrl() + ":" + env.getPort());
            setGraphic(row);
        } else {
            setGraphic(null);
        }
    }
}
