
package view.frames;

import javafx.animation.FadeTransition;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import view.AnimationManager;
import view.Category;
import view.CustomViewComponent;
import view.View.Frames;

/**
 * The main frame of the game.
 */
public class GameFrame extends AbstractRefreshableViewFrame {
    private static final String INVENTORY = "INVENTORY";
    private static final String SHOP = "SHOP";
    private final String defaultUrl = GameFrame.class.getClassLoader().getResource("tama.png").toExternalForm();
    private BorderPane mainPane = new BorderPane();
    private AnchorPane animationPane = new AnchorPane();
    private ImageView tamagotchiImage;
    private VBox itemsBox = new VBox();
    private VBox statsBox = new VBox();
    private Text ageLabel = new Text("");

    @Override
    public void start(final Stage newPrimaryStage) throws Exception {
        setStage(newPrimaryStage);
        setFrame();
        setScene(mainPane, SCREEN_WIDTH, SCREEN_HEIGHT);
        setExitOperation();
    }

    @Override
    public void setFrame() {
        try {
            this.tamagotchiImage = new ImageView(new Image(getController().getTamagotchiUrl()));
        } catch (NullPointerException ex) {
            this.tamagotchiImage = new ImageView(new Image(defaultUrl));
        }
        getStage().setTitle("Tamagotchi");
        HBox btnBox = new HBox();
        ageLabel.setText("Age: " + ((Double) getController().getAge()).intValue());
        ageLabel.setId("coloredPacificoText");
        ageLabel.setStyle("-fx-font-size: " + AGEFONTSIZE + "em;");
        Button inventoryBtn = new Button(INVENTORY);
        Button shopBtn = new Button(SHOP);
        mainPane.setId("defaultBackGroundColor");
        animationPane.setId("animationPane");
        shopBtn.setId("brickRedButton");
        inventoryBtn.setId("brickRedButton");
        shopBtn.setStyle("-fx-font-size: " + GAMEFRAMEBUTTONSFONTSIZE + "em;");
        inventoryBtn.setStyle("-fx-font-size: " + GAMEFRAMEBUTTONSFONTSIZE + "em;");

        mainPane.setTop(btnBox);
        mainPane.setCenter(animationPane);
        AnchorPane.setBottomAnchor(tamagotchiImage, TAMAGOTCHIBOTTOM);
        AnchorPane.setLeftAnchor(tamagotchiImage, TAMAGOTCHILEFT);
        btnBox.setPadding(new Insets(GAMEFRAMEBUTTONSINSETTOPBOTTOM, 0, GAMEFRAMEBUTTONSINSETTOPBOTTOM, 0));
        btnBox.setSpacing(PANEGAP);

        tamagotchiImage.setFitHeight(LOGOMAINSIZE);
        tamagotchiImage.setFitWidth(LOGOMAINSIZE);
        AnimationManager.jumpAnimation(tamagotchiImage);

        animationPane.getChildren().add(tamagotchiImage);
        HBox inventoryShopBox = new HBox();
        inventoryShopBox.setSpacing(SHOPINVBTNSPACING);
        inventoryShopBox.getChildren().addAll(inventoryBtn, shopBtn);
        btnBox.getChildren().addAll(ageLabel, inventoryShopBox);
        btnBox.setAlignment(Pos.TOP_LEFT);
        btnBox.setSpacing(BTNAGESPACING);
        mainPane.getStylesheets().add(GameFrame.class.getClassLoader().getResource("application.css").toExternalForm());

        /*
         * Listeners
         */
        shopBtn.setOnAction((ActionEvent event) -> {
            try {
                this.getView().startFrame(Frames.SHOP);
            } catch (IllegalArgumentException e) {
            }
        });
        inventoryBtn.setOnAction((ActionEvent event) -> {
            try {
                this.getView().startFrame(Frames.INVENTORY);
            } catch (IllegalArgumentException e) {
            }
        });
        setInventory();
        setStats();
    }

    @Override
    public void clearStage() {
        getStage().close();
        mainPane.getChildren().clear();
        animationPane.getChildren().clear();
        itemsBox.getChildren().clear();
        statsBox.getChildren().clear();
    }

    @Override
    public void clearAndSave() {
        clearStage();
        getController().saveFile();
    }

    /**
     * Set the animation for each preferred item.
     * 
     * @param imageView
     *            is the image of the item
     */
    private void manageAnimationForItem(final Category cat, final ImageView img) {
        switch (cat) {
        case Happiness:
            happinessTransition(img);
            break;
        case Hungry:
            hungryTransition(img);
            break;
        case Health:
            healthTransition(img);
            break;
        case Cleanness:
            cleannesTransition(img);
            break;
        default:
            break;
        }
    }

    /**
     * Sets the content of the stats box on the right part of the screen.
     */
    private void setStats() {
        statsBox.getChildren().clear();
        statsBox.setPadding(new Insets(STATSINSETS));
        statsBox.setSpacing(STATSSPACING);
        getController().getStats().forEach(s -> {
            Label name = new Label(s.getName());
            ProgressBar prog = new ProgressBar(s.getValue() / 1000);
            prog.setMaxWidth(Double.MAX_VALUE);
            name.setId("brickRedLabel");
            statsBox.getChildren().addAll(name, prog);
        });
        mainPane.setRight(statsBox);
    }

    /**
     * Sets the content of the favourite item's box on the left part of the screen.
     */
    private void setInventory() {
        itemsBox.getChildren().clear();
        itemsBox.setPadding(new Insets(ITEMSINSETSSPACING));
        itemsBox.setSpacing(ITEMSINSETSSPACING);
        mainPane.setLeft(itemsBox);
        for (Category cat : Category.values()) {
            ItemButton e = new ItemButton(cat);
            try {
                ImageView img = new ImageView(new Image(getController().getMainItem(e.getStats().toString())));
                e.setImage(img);
                setListener(e, img);
            } catch (NullPointerException exc) {
                e.setXImage();
            }
            itemsBox.getChildren().add(e);
        }
    }

    /**
     * Sets what the button has to do when clicked.
     * 
     * @param e
     *            is the button of the item
     * @param img
     *            is the image on the button
     */
    private void setListener(final ItemButton e, final ImageView img) {
        e.setOnAction(ev -> {
            try {
                manageAnimationForItem(e.getStats(), img);
            } catch (Exception ex) {
            }
            getController().modStat(e.getStats().toString());
            getController().checkInventory();
            refresh(true);
            this.getView().refreshInventory(e.stat);
        });
    }

    @Override
    public void refresh(final boolean both) {
        if (both) {
            setStats();
            setInventory();
        } else {
            setStats();
        }
    }

    @Override
    public void refreshAge() {
        this.ageLabel.setText("Age: " + (int) getController().getAge());
    }

    /**
     * Is the animation for the items of the Happiness category.
     * 
     * @param imageView
     *            is the image of the item
     */
    private void happinessTransition(final ImageView imageView) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                AnchorPane.setTopAnchor(imageView, 0.0);
                AnchorPane.setLeftAnchor(imageView, 0.0);
                imageView.setFitHeight(ITEMMAINANIMATIONSIZE);
                imageView.setFitWidth(ITEMMAINANIMATIONSIZE);
                animationPane.getChildren().add(imageView);
                TranslateTransition traslateToRight = new TranslateTransition();
                AnimationManager.traslate(traslateToRight, imageView, ANIMATIONMOVEDURATION, ANIMATIONXRIGTHBORDER, 0,
                        e -> {
                            itemsBox.getChildren().stream().map(ib -> (ItemButton) ib).forEach(x -> x.setDisable(true));
                            TranslateTransition traslateToLeftBack = new TranslateTransition();
                            AnimationManager.traslate(traslateToLeftBack, imageView, ANIMATIONMOVEDURATION,
                                    ANIMATIONXLEFTBORDER, ANIMATIONYTOPBORDER, ev -> {
                                        TranslateTransition traslateToBottom = new TranslateTransition();
                                        AnimationManager.traslate(traslateToBottom, imageView, ANIMATIONMOVEDURATION,
                                                ANIMATIONXCENTERVALUE, ANIMATIONYBOTTOMBORDER, eve -> {
                                                    TranslateTransition traslateToTopBack = new TranslateTransition();
                                                    AnimationManager.traslate(traslateToTopBack, imageView,
                                                            ANIMATIONMOVEDURATION, ANIMATIONXCENTERVALUE,
                                                            ANIMATIONYCENTERVALUE, even -> {
                                                                RotateTransition rotate = new RotateTransition();
                                                                AnimationManager.rotate(rotate, imageView,
                                                                        ANIMATIONROTATEDURATION, 180, 1, event -> {
                                                                            animationPane.getChildren().remove(1);
                                                                            itemsBox.getChildren().stream()
                                                                                    .map(ib -> (ItemButton) ib)
                                                                                    .forEach(x -> x.setDisable(false));
                                                                        });
                                                            });
                                                });
                                    });
                        });
            }
        });
    }

    /**
     * Is the animation for the items of the Hungry category.
     * 
     * @param imageView
     *            is the image of the item
     */
    private void hungryTransition(final ImageView imageView) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                AnchorPane.setTopAnchor(imageView, 1.0);
                AnchorPane.setLeftAnchor(imageView, HUNGRYLEFTANCHOR);
                imageView.setFitHeight(ITEMMAINANIMATIONSIZE);
                imageView.setFitWidth(ITEMMAINANIMATIONSIZE);
                animationPane.getChildren().add(imageView);
                TranslateTransition traslateToBottom = new TranslateTransition();
                AnimationManager.traslate(traslateToBottom, imageView, ANIMATIONMOVEDURATION, 0, ANIMATIONHUNGRYVALUE,
                        e -> {
                            itemsBox.getChildren().stream().map(ib -> (ItemButton) ib).forEach(x -> x.setDisable(true));
                            FadeTransition fade = new FadeTransition();
                            AnimationManager.fade(fade, imageView, ANIMATIONFADEDURATION, 1, 0, 1, ev -> {
                                animationPane.getChildren().remove(1);
                                itemsBox.getChildren().stream().map(ib -> (ItemButton) ib)
                                        .forEach(x -> x.setDisable(false));
                            });
                        });
            }
        });
    }

    /**
     * Is the animation for the items of the Health category.
     * 
     * @param imageView
     *            is the image of the item
     */
    private void healthTransition(final ImageView imageView) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                AnchorPane.setTopAnchor(imageView, 1.0);
                AnchorPane.setLeftAnchor(imageView, 1.0);
                imageView.setFitHeight(ITEMMAINANIMATIONSIZE);
                imageView.setFitWidth(ITEMMAINANIMATIONSIZE);
                animationPane.getChildren().add(imageView);
                TranslateTransition traslateToBottomRight = new TranslateTransition();
                AnimationManager.traslate(traslateToBottomRight, imageView, ANIMATIONMOVEDURATION,
                        ANIMATIONXCENTERVALUE, ANIMATIONYCENTERVALUE, e -> {
                            itemsBox.getChildren().stream().map(ib -> (ItemButton) ib).forEach(x -> x.setDisable(true));
                            FadeTransition fade = new FadeTransition();
                            AnimationManager.fade(fade, imageView, ANIMATIONFADEDURATION, 1, 0, 1, ev -> {
                                animationPane.getChildren().remove(1);
                                itemsBox.getChildren().stream().map(ib -> (ItemButton) ib)
                                        .forEach(x -> x.setDisable(false));
                            });
                        });
            }
        });
    }

    /**
     * Is the animation for the items of the Cleannes category.
     * 
     * @param imageView
     *            is the image of the item
     */
    private void cleannesTransition(final ImageView imageView) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                AnchorPane.setTopAnchor(imageView, ANIMATIONYCENTERVALUE);
                AnchorPane.setLeftAnchor(imageView, ANIMATIONYCENTERVALUE);
                imageView.setFitHeight(ITEMMAINANIMATIONSIZE);
                imageView.setFitWidth(ITEMMAINANIMATIONSIZE);
                animationPane.getChildren().add(imageView);
                TranslateTransition traslateToRight = new TranslateTransition();
                AnimationManager.traslate(traslateToRight, imageView, ANIMATIONMOVEDURATION, CLEANESSXSTOP, 0, e -> {
                    itemsBox.getChildren().stream().map(ib -> (ItemButton) ib).forEach(x -> x.setDisable(true));
                    TranslateTransition traslateToLeftBack = new TranslateTransition();
                    AnimationManager.traslate(traslateToLeftBack, imageView, ANIMATIONMOVEDURATION, CLEANXSTART, 0,
                            ev -> {
                                TranslateTransition traslateToRightBack = new TranslateTransition();
                                AnimationManager.traslate(traslateToRightBack, imageView, ANIMATIONMOVEDURATION * 2,
                                        CLEANESSXSTOP, 0, eve -> {
                                            TranslateTransition traslateAgainToLeftBack = new TranslateTransition();
                                            AnimationManager.traslate(traslateAgainToLeftBack, imageView,
                                                    ANIMATIONMOVEDURATION, CLEANXSTART, 0, even -> {
                                                        FadeTransition fade = new FadeTransition();
                                                        AnimationManager.fade(fade, imageView, 1000, 1, 0, 1, event -> {
                                                            animationPane.getChildren().remove(1);
                                                            itemsBox.getChildren().stream().map(ib -> (ItemButton) ib)
                                                                    .forEach(x -> x.setDisable(false));
                                                        });
                                                    });
                                        });
                            });
                });
            }
        });
    }

    /**
     * Defines an image linked to a Stat
     */
    private static class ItemButton extends Button implements CustomViewComponent {
        private Category stat;
        private static final int IMGDIM = 120;
        private static final String NOITEM = "NO ITEM CHOOSEN";
        private static final String URL = "X.png";

        /**
         * @param newImage
         * @param newStat
         */
        ItemButton(final Category newStat) {
            this.stat = newStat;
        }

        protected Category getStats() {
            return this.stat;
        }

        protected void setImage(final ImageView img) {
            img.setFitWidth(IMGDIM);
            img.setFitHeight(IMGDIM);
            this.setGraphic(img);
            this.setId("yellowImage");
        }

        protected void setXImage() {
            this.setImage(new ImageView(new Image(GameFrame.class.getClassLoader().getResource(URL).toExternalForm())));
            Tooltip tip = new Tooltip(NOITEM);
            this.setTooltip(tip);
            this.setOnAction(null);
        }
    }
}
