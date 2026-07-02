package zombieversity.view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Pair;

public class GameView implements Initializable {

    // ----------------VIEW STUFF---------------------
    @FXML
    private AnchorPane root;
    @FXML
    private Canvas map;
    @FXML
    private Label timer;
    @FXML
    private ImageView weaponType;
    @FXML
    private Label ammos;
    @FXML
    private Label round;
    @FXML
    private Canvas miniMap;
    @FXML
    private Label lblHP;

    @Override
    public final void initialize(final URL location, final ResourceBundle resources) {
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        root.setPrefWidth(screenSize.getWidth() / 2);
        root.setPrefHeight(screenSize.getHeight() / 2);
        map.setWidth(root.getPrefWidth());
        map.setHeight(root.getPrefHeight());
    }

    public final void resize(final double w, final double h) {
        map.setWidth(w);
        map.setHeight(h);
    }

    public final void render(final Image img, final Point2D pos) {
        this.map.getGraphicsContext2D().drawImage(img, pos.getX(), pos.getY());
    }

    public final void renderAmmoLabel(final String ammo) {
        this.ammos.setText(ammo);
    }

    public final void renderHPLabel(final String hp) {
        this.lblHP.setText("HP: " + hp);
    }

    public final double getWidth() {
        return root.getPrefWidth();
    }

    public final double getHeight() {
        return root.getPrefHeight();
    }

    public final GraphicsContext getRenderer() {
        return this.map.getGraphicsContext2D();
    }

    public final GraphicsContext getMiniMapRenderer() {
        return this.miniMap.getGraphicsContext2D();
    }

    public final Label getTimerLabel() {
        return this.timer;
    }

    public final Label getRoundLabel() {
        return this.round;
    }

    public final Label getHPLabel() {
        return this.lblHP;
    }

    public final void clear() {
        this.map.getGraphicsContext2D().clearRect(0, 0, this.map.getWidth(), this.map.getHeight());
    }

    public final void completeRender(final Set<Pair<Point2D, Image>> obj, final Point2D start, final Point2D end,
            final Point2D offset, final double ts) {
        final Point2D step = new Point2D(ts, ts);
        final Point2D s = start.subtract(step);
        final Point2D e = end.add(step);

        obj.forEach(p -> {
            final Point2D point = p.getKey();
            if (isBetween(point, s, e)) {
                render(p.getValue(), p.getKey().subtract(offset));
            }
        });
    }

    private boolean isBetween(final Point2D p, final Point2D s, final Point2D e) {
        final double x = p.getX();
        final double y = p.getY();
        return x >= s.getX() && x <= e.getX() && s.getY() <= y && y <= e.getY();
    }

}
