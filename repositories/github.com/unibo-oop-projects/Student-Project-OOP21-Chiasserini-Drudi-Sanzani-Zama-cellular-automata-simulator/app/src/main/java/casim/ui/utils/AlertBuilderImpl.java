package casim.ui.utils;

import casim.utils.BaseBuilder;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Region;
import javafx.stage.Window;

/**
 * Implementation for a builder of {@link Alert}.
 */
public class AlertBuilderImpl implements AlertBuilder {
    private static final String ALERT_TYPE_NULL = "Alert type cannot be null.";
    private static final String TITLE_NULL = "Title cannot be null.";
    private static final String MESSAGE_NULL = "Message cannot be null.";
    private static final String OWNER_NULL = "Owner cannot be null.";
    private static final String DEFAULT_INFO_TITLE = "Info";
    private static final String DEFAULT_ERROR_TITLE = "Error";

    private final BaseBuilder base = new BaseBuilder();

    private AlertType type;
    private String title;
    private String message;
    private Window owner;

    @Override
    public Alert buildDefaultInfo(final String message, final Window owner) {
        return this.buildCustom(AlertType.INFORMATION, DEFAULT_INFO_TITLE, message, owner);
    }

    @Override
    public Alert buildDefaultError(final String message, final Window owner) {
        return this.buildCustom(AlertType.ERROR, DEFAULT_ERROR_TITLE, message, owner);
    }

    @Override
    public Alert buildCustom(final AlertType type, final String title, final String message, final Window owner) {
        this.base.registerCall();
        this.type(type)
            .title(title)
            .owner(owner)
            .message(message);
        return this.getAlert();
    }

    private AlertBuilderImpl message(final String message) {
        this.base.registerCall();
        this.message = this.base.checkNonNullValue(message, MESSAGE_NULL);
        return this;
    }

    private AlertBuilderImpl owner(final Window owner) {
        this.base.registerCall();
        this.owner = this.base.checkNonNullValue(owner, OWNER_NULL);
        return this;
    }

    private AlertBuilderImpl type(final AlertType type) {
        this.base.registerCall();
        this.type = this.base.checkNonNullValue(type, ALERT_TYPE_NULL);
        return this;
    }

    private AlertBuilderImpl title(final String title) {
        this.base.registerCall();
        this.title = this.base.checkNonNullValue(title, TITLE_NULL);
        return this;
    }

    private Alert getAlert() {
        final var alert = new Alert(this.type);
        alert.setTitle(this.title);
        alert.setHeaderText(this.title);
        alert.setContentText(this.message);
        alert.initOwner(this.owner);
        alert.getDialogPane().setMinSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        return alert;
    }
}
