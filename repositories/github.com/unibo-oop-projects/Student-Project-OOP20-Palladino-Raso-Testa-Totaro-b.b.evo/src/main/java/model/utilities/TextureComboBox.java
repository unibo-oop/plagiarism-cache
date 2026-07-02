package model.utilities;

import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;
import resource.routing.BallTexture;
import resource.routing.BrickTexture;
import resource.routing.PaddleTexture;

/**
 * This class manage the ComboBox on ControllerMapEditor.
 * Simple load the texture on it as preview.
 */
public class TextureComboBox {

    private static final int COMBOBOX_HEIGHT = 30;

    private final ComboBox<String> ballTexture;
    private final ComboBox<String> paddleTexture;
    private final ComboBox<String> brickTexture;


    /**
     * @param ballTexture get ballTexture Combobox
     * @param paddleTexture get paddleTexture ComboBox
     * @param brickTexture get brickTexture ComboBox
     */
    public TextureComboBox(final ComboBox<String> ballTexture, final ComboBox<String> paddleTexture, final ComboBox<String> brickTexture) {
        super();
        this.ballTexture = ballTexture;
        this.paddleTexture = paddleTexture;
        this.brickTexture = brickTexture;
    }

    /**
     * Load the Ball texture preview.
     */
    public void loadBallTexture() {
        this.ballTexture.getItems().addAll(BallTexture.getBallTexturedNames());
        this.ballTexture.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {

            @Override
            public ListCell<String> call(final ListView<String> p) {
                return new ListCell<String>() {

                    @Override
                    //Use suppressWarnings because if try catch generate NullPointerException the icon assume another path
                    protected void updateItem(final String item, final boolean empty) {
                        super.updateItem(item, empty);
                        setText(item);
                        if (item == null || empty) {
                            setGraphic(null);
                        } else {
                            Image icon;
                            try {
                                final String iconPath = BallTexture.getBallTextureByName(item).getPath();
                                icon = new Image(Thread.currentThread().getContextClassLoader().getResourceAsStream(iconPath));
                            } catch (NullPointerException ex) {
                                // in case the above image doesn't exist, use a default one
                                final String iconPath = "Images/ball/defaultBall.png";
                                icon = new Image(Thread.currentThread().getContextClassLoader().getResourceAsStream(iconPath));
                            }
                            final ImageView iconImageView = new ImageView(icon);
                            iconImageView.setFitHeight(COMBOBOX_HEIGHT);
                            iconImageView.setPreserveRatio(true);
                            setGraphic(iconImageView);
                        }
                    }
                };
            }
        });
    }

    /**
     * Load the Paddle texture preview.
     */
    public void loadPaddleTexture() {
        this.paddleTexture.getItems().addAll(PaddleTexture.getPaddleTexturedNames());
        this.paddleTexture.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {

            @Override
            public ListCell<String> call(final ListView<String> p) {
                return new ListCell<String>() {

                    @Override
                    protected void updateItem(final String item, final boolean empty) {
                        super.updateItem(item, empty);
                        setText(item);
                        if (item == null || empty) {
                            setGraphic(null);
                        } else {
                            Image icon;
                            try {
                                final String iconPath = PaddleTexture.getPaddleTextureByName(item).getPath();
                                icon = new Image(Thread.currentThread().getContextClassLoader().getResourceAsStream(iconPath));
                            } catch (NullPointerException ex) {
                                // in case the above image doesn't exist, use a default one
                                final String iconPath = "Images/paddle/defaultPaddle.png";
                                icon = new Image(Thread.currentThread().getContextClassLoader().getResourceAsStream(iconPath));
                            }
                            final ImageView iconImageView = new ImageView(icon);
                            iconImageView.setFitHeight(COMBOBOX_HEIGHT);
                            iconImageView.setPreserveRatio(true);
                            setGraphic(iconImageView);
                        }
                    }
                };
            }
        });
    }

    /**
     * Load the Brick texture preview.
     */
    public void loadBrickTexture() {
        this.brickTexture.getItems().addAll(BrickTexture.getBrickTextureNamesVisible());
        this.brickTexture.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {

            @Override
            public ListCell<String> call(final ListView<String> p) {
                return new ListCell<String>() {

                    @Override
                    @SuppressWarnings({ "PMD.AvoidCatchingGenericException", "PMD.AvoidCatchingNPE" })
                    protected void updateItem(final String item, final boolean empty) {
                        super.updateItem(item, empty);
                        setText(item);
                        if (item == null || empty) {
                            setGraphic(null);
                        } else {
                            Image icon;
                            try {
                                final String iconPath = BrickTexture.getBrickTextureByName(item).getPath();
                                icon = new Image(Thread.currentThread().getContextClassLoader().getResourceAsStream(iconPath));
                            } catch (NullPointerException ex) {
                                // in case the above image doesn't exist, use a default one
                                final String iconPath = "Images/brick/defaultBrick.png";
                                icon = new Image(Thread.currentThread().getContextClassLoader().getResourceAsStream(iconPath));
                            }
                            final ImageView iconImageView = new ImageView(icon);
                            iconImageView.setFitHeight(COMBOBOX_HEIGHT);
                            iconImageView.setPreserveRatio(true);
                            setGraphic(iconImageView);
                        }
                    }
                };
            }
        });
    }
}
