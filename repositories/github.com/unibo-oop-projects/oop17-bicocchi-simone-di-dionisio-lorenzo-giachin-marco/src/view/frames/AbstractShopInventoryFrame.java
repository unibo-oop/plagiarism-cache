package view.frames;

import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import view.Category;
import view.CustomViewComponent;

/**
 *
 */
public abstract class AbstractShopInventoryFrame extends AbstractViewFrame {
    static final String HAPPINESS = "Happiness";
    static final String HUNGRY = "Hungry";
    static final String HEALTH = "Health";
    static final String CLEANESS = "Cleaness";
    static final double IMAGESIZE = 50;
    static final double CATEGORYINSETSTOP = 150;
    static final double CATEGORYINSETSBOTTOMRIGTH = 10;
    static final double CATEGORYSPACING = 20;
    static final double SHOPINVENTORYSCREENHEIGHT = 650;

    private Button happinessButton = new Button(HAPPINESS);
    private Button hungryButton = new Button(HUNGRY);
    private Button healthButton = new Button(HEALTH);
    private Button cleanessButton = new Button(CLEANESS);
    private BorderPane mainPane = new BorderPane();
    private VBox categoryBox = new VBox();
    private Label money = new Label();
    private HBox wallet = new HBox();

    @Override
    public abstract void start(Stage primaryStage) throws Exception;

    @Override
    public void setFrame() {
        getHappinessButton().setId("brickRedButton");
        getHungryButton().setId("brickRedButton");
        getHealthButton().setId("brickRedButton");
        getCleanessButton().setId("brickRedButton");
        money.setId("coloredPacificoText");
        getHappinessButton().setStyle("-fx-font-size: " + 3 + "em;");
        getHungryButton().setStyle("-fx-font-size: " + 3 + "em;");
        getHealthButton().setStyle("-fx-font-size: " + 3 + "em;");
        getCleanessButton().setStyle("-fx-font-size: " + 3 + "em;");
        money.setStyle("-fx-font-size: " + 3 + "em;");

        setItemToShow(Category.Happiness);
        mainPane.setId("defaultBackGroundColor");

        money.setText(getController().getBalance() + "€");
        mainPane.setLeft(categoryBox);
        mainPane.setBottom(wallet);
        ImageView img = new ImageView(new Image(AbstractShopInventoryFrame.class.getClassLoader().getResource("wallet.png").toExternalForm()));
        img.setFitHeight(IMAGESIZE);
        img.setFitWidth(IMAGESIZE);
        wallet.getChildren().addAll(img, money);
        wallet.setAlignment(Pos.BASELINE_CENTER);
        wallet.setPadding(new Insets(2, 0, 0, 0));

        categoryBox.getChildren().addAll(getHappinessButton(), getHungryButton(), getHealthButton(),
                getCleanessButton());
        categoryBox.setPadding(new Insets(CATEGORYINSETSTOP, 0, CATEGORYINSETSBOTTOMRIGTH, CATEGORYINSETSBOTTOMRIGTH));
        categoryBox.setSpacing(CATEGORYSPACING);
        mainPane.getStylesheets().add(AbstractShopInventoryFrame.class.getClassLoader().getResource("application.css").toExternalForm());

        this.happinessButton.setOnAction(e -> {
            setItemToShow(Category.Happiness);
        });
        this.hungryButton.setOnAction(e -> {
            setItemToShow(Category.Hungry);
        });
        this.healthButton.setOnAction(e -> {
            setItemToShow(Category.Health);
        });
        this.cleanessButton.setOnAction(e -> {
            setItemToShow(Category.Cleanness);
        });
    };

    /**
     * @param newCategory
     *            is the category of the item
     */
    protected void setItemToShow(final Category newCategory) {
        switch (newCategory) {
        case Happiness:
            generateImages(newCategory);
            break;
        case Hungry:
            generateImages(newCategory);
            break;
        case Health:
            generateImages(newCategory);
            break;
        case Cleanness:
            generateImages(newCategory);
            break;
        default:
            break;
        }
    };

    @Override
    public void clearStage() {
        if (this.getStage() != null) {
            getStage().close();
            mainPane.getChildren().clear();
            categoryBox.getChildren().clear();
            wallet.getChildren().clear();
        }
    }

    @Override
    public void setExitOperation() {
        getStage().setOnCloseRequest(e -> {
            clearStage();
        });
    }

    /**
     * 
     * @param category
     *            is the category of the item
     * @return List<Integer> is the list of quantities for the items of the choosen
     *         category
     */
    protected List<Integer> getQuantity(final String category) {
        List<Integer> list = new ArrayList<>();
        getController().getInventory().get(category).forEach(e -> list.add(e.getSecond()));
        return list;
    }

    /**
     * @param category
     *            is the category of the item
     */
    protected abstract void generateImages(Category category);

    /**
     * @return happinessButton
     */
    public Button getHappinessButton() {
        return happinessButton;
    }

    /**
     * @return hungryButton
     */
    public Button getHungryButton() {
        return hungryButton;
    }

    /**
     * @return healthButton
     */
    public Button getHealthButton() {
        return healthButton;
    }

    /**
     * @return cleanessButton
     */
    public Button getCleanessButton() {
        return cleanessButton;
    }

    /**
     * @return categoryBox
     */
    public VBox getCategoryBox() {
        return categoryBox;
    }

    /**
     * @return mainPane
     */
    public BorderPane getMainPane() {
        return mainPane;
    }

    /**
     * @return money
     */
    public Label getMoney() {
        return money;
    }

    /**
     * @return wallet
     */
    public HBox getWallet() {
        return wallet;
    }

    /**
     * Customized label with style.
     */
    protected class CustomLabel extends Label implements CustomViewComponent {

        /**
         * @param s 
         */
        public CustomLabel(final String s) {
            this.setId("coloredPacificoText");
            this.setText(s);
            this.setStyle("-fx-font-size: " + 4 + "em;");
        }
    }

    /**
     * Refreshes the view.
     * 
     * @param cat
     *            is the category of the choosen item
     */
    public abstract void refreshInventory(Category cat);

    /**
     * Set the value of the money.
     */
    public void setMoneyValue() {
        try {
            getMoney().setText(getController().getBalance() + "€");
        } catch (NullPointerException e) {
        }
    }

}
