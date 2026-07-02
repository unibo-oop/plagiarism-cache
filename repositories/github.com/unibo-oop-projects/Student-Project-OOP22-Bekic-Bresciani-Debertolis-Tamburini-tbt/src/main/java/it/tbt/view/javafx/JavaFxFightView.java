package it.tbt.view.javafx;

import it.tbt.commons.resourceloader.ImageLoader;
import it.tbt.controller.modelmanager.FightState;
import it.tbt.controller.modelmanager.FightStateImpl;
import it.tbt.controller.viewcontrollermanager.api.ViewController;
import it.tbt.model.entities.characters.Ally;
import it.tbt.model.entities.characters.Enemy;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * JavaFxFightView is a JavaFX view class that provides the graphical user
 * interface for a fight scene in a game.
 * It extends the AbstractJavaFxView class and implements the rendering of the
 * fight scene using JavaFX components.
 */
public final class JavaFxFightView extends AbstractJavaFxView {

    private static final int BORDER_SCALE = 25;
    private static final int BUTTON_SCALE = 150;
    private static final int HGAP = 150;
    private static final int SPRITE_SIZE = 75;
    private static final String BLUE = "#0000FF";
    private static final String RED = "-fx-background-color: red;";
    private final FightState main;
    private final double height;
    private final double width;
    private final Image enemyImage;
    private final Image allyImage;
    private final Image defeatedImage;
    private final Background bg;

    /**
     * Constructs a JavaFxFightView object with the specified stage, scene,
     * fightController, and main FightState.
     *
     * @param stage           the JavaFX stage object
     * @param scene           the JavaFX scene object
     * @param fightController the view controller for the fight scene
     * @param main            the main FightState object
     */
    public JavaFxFightView(final Stage stage, final Scene scene, final ViewController fightController,
            final FightState main) {
        super(fightController, stage, scene);
        if (fightController == null || main == null) {
            throw new IllegalArgumentException(
                    "è stato passato un argomento non lecito alla creazione di JavaFxFightView");
        }

        this.height = scene.getHeight() - (scene.getHeight() / JavaFxFightView.BORDER_SCALE);
        this.width = scene.getWidth() - (scene.getWidth() / JavaFxFightView.BORDER_SCALE);
        this.main = main;
        this.defeatedImage = new Image(
                getClass().getClassLoader().getResource("tbt/images/transparent.png").toExternalForm());
        this.enemyImage = new Image(ImageLoader.getInstance().getFilePath(Enemy.class));
        this.allyImage = new Image(ImageLoader.getInstance().getFilePath(Ally.class));
        this.bg = new Background(
                new BackgroundImage(
                        new Image(ImageLoader.getInstance().getFilePath(FightStateImpl.class)),
                        BackgroundRepeat.REPEAT,
                        BackgroundRepeat.REPEAT,
                        BackgroundPosition.DEFAULT,
                        new BackgroundSize(1.0, 1.0, true, true, false, false)));

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(final KeyEvent event) {
                // System.out.println("ff15" + event.getCode());
                fightController.onKeyPressed(event.getCode().getCode());
            }
        });
    }

    /**
     * Sets the label style for the selected target.
     *
     * @param l1 the first label
     * @param l2 the second label
     * @param l3 the third label
     * @param l4 the fourth label
     */
    private void setSelectedLable(final Label l1, final Label l2, final Label l3, final Label l4) {
        switch (main.getSelectedTargetIndex()) {
            case 0:
                l1.setTextFill(Color.web(JavaFxFightView.BLUE));
                break;
            case 1:
                l2.setTextFill(Color.web(JavaFxFightView.BLUE));
                break;
            case 2:
                l3.setTextFill(Color.web(JavaFxFightView.BLUE));
                break;
            default:
                l4.setTextFill(Color.web(JavaFxFightView.BLUE));
                break;
        }
    }

    /**
     * Sets the label text for the ally entity.
     *
     * @param name the label for the name
     * @param hp   the label for the health points
     * @param i    the index of the ally entity
     */
    private void setLableTextAlly(final Label name, final Label hp, final int i) {
        if (name == null || hp == null || i < 0) {
            throw new IllegalArgumentException("è stato passato un argomento non lecito a setLableTextAlly");
        }
        if (main.getAllies().get(i).getMaxHealth() != 0) {
            if (main.getAllies().get(i).equals(main.getCurrentAlly())) {
                name.setTextFill(Color.web("#A020F0"));
            }
            name.setText(main.getAllies().get(i).getName());
            hp.setText(main.getAllies().get(i).getHealth() + "/" + main.getAllies().get(i).getMaxHealth());
        } else {
            name.setText("");
            hp.setText("");
        }
    }

    /**
     * Sets the label text for the enemy entity.
     *
     * @param name the label for the name
     * @param hp   the label for the health points
     * @param i    the index of the enemy entity
     */
    private void setLableTextEnemy(final Label name, final Label hp, final int i) {
        if (name == null || hp == null || i < 0) {
            throw new IllegalArgumentException("è stato passato un argomento non lecito a setLableTextEnemy");
        }
        if (main.getEnemies().get(i).getMaxHealth() != 0) {
            name.setText(main.getEnemies().get(i).getName());
            hp.setText(main.getEnemies().get(i).getHealth() + "/" + main.getEnemies().get(i).getMaxHealth());
        } else {
            name.setText("");
            hp.setText("");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render() {
        Platform.runLater(() -> {
            final StackPane root = new StackPane();
            root.getChildren().clear();

            root.setBackground(this.bg);
            final BorderPane pane = new BorderPane();
            pane.setMaxHeight(this.height);
            pane.setMaxWidth(this.width);
            // Creazione delle etichette per gli alleati
            final Label allyLabel1 = new Label();
            final Label allyLabel2 = new Label();
            final Label allyLabel3 = new Label();
            final Label allyLabel4 = new Label();
            final Label allyHpLabel1 = new Label();
            final Label allyHpLabel2 = new Label();
            final Label allyHpLabel3 = new Label();
            final Label allyHpLabel4 = new Label();

            setLableTextAlly(allyLabel1, allyHpLabel1, 0);
            setLableTextAlly(allyLabel2, allyHpLabel2, 1);
            setLableTextAlly(allyLabel3, allyHpLabel3, 2);
            setLableTextAlly(allyLabel4, allyHpLabel4, 3);

            // Creazione del layout per le etichette degli alleati
            final GridPane allyGrid = new GridPane();
            allyGrid.setAlignment(Pos.BOTTOM_CENTER);
            allyGrid.setHgap(JavaFxFightView.HGAP);
            allyGrid.setVgap(10);
            allyGrid.addRow(0, allyLabel1, allyLabel2, allyLabel3, allyLabel4);
            allyGrid.addRow(1, allyHpLabel1, allyHpLabel2, allyHpLabel3, allyHpLabel4);

            // Creazione delle etichette per i nemici
            final Label enemyLabel1 = new Label();
            final Label enemyLabel2 = new Label();
            final Label enemyLabel3 = new Label();
            final Label enemyLabel4 = new Label();
            final Label enemyHpLabel1 = new Label();
            final Label enemyHpLabel2 = new Label();
            final Label enemyHpLabel3 = new Label();
            final Label enemyHpLabel4 = new Label();

            setLableTextEnemy(enemyLabel1, enemyHpLabel1, 0);
            setLableTextEnemy(enemyLabel2, enemyHpLabel2, 1);
            setLableTextEnemy(enemyLabel3, enemyHpLabel3, 2);
            setLableTextEnemy(enemyLabel4, enemyHpLabel4, 3);

            // Creazione del layout per le etichette dei nemici
            final GridPane enemyGrid = new GridPane();
            enemyGrid.setAlignment(Pos.TOP_CENTER);
            enemyGrid.setHgap(JavaFxFightView.HGAP);
            enemyGrid.setVgap(10);
            enemyGrid.addRow(0, enemyLabel1, enemyLabel2, enemyLabel3, enemyLabel4);
            enemyGrid.addRow(1, enemyHpLabel1, enemyHpLabel2, enemyHpLabel3, enemyHpLabel4);

            // Creazione dei bottoni
            String skillButtonText;
            if (main.getCurrentAlly().getSkills().get(0).getRemainingCooldown() == 0) {
                skillButtonText = "Skill: x" + main.getCurrentAlly().getSkills().get(0).getAttackMultiplier();
            } else {
                skillButtonText = "CD: " + main.getCurrentAlly().getSkills().get(0).getRemainingCooldown();
            }
            final Button skillButton = new Button(skillButtonText);
            final Button attackButton = new Button(
                    "Attacco: " + (main.getCurrentAlly().getAttack() + main.getCurrentAlly().getWeaponAttack()));
            final Button potionButton = new Button("Pozione");
            final Button antidoteButton = new Button("Antidoto");

            if (main.isUsingSkill()) {
                skillButton.setStyle(JavaFxFightView.RED);
                setSelectedLable(enemyLabel1, enemyLabel2, enemyLabel3, enemyLabel4);
            } else if (main.isUsingAntidote()) {
                antidoteButton.setStyle(JavaFxFightView.RED);
                setSelectedLable(allyLabel1, allyLabel2, allyLabel3, allyLabel4);
            } else if (main.isUsingPotion()) {
                potionButton.setStyle(JavaFxFightView.RED);
                setSelectedLable(allyLabel1, allyLabel2, allyLabel3, allyLabel4);
            } else {
                attackButton.setStyle(JavaFxFightView.RED);
                setSelectedLable(enemyLabel1, enemyLabel2, enemyLabel3, enemyLabel4);
            }

            // Creazione del layout per i bottoni
            final VBox buttonBox = new VBox(root.getLayoutX());
            buttonBox.setPrefWidth(JavaFxFightView.BUTTON_SCALE);
            buttonBox.setAlignment(Pos.CENTER_RIGHT);
            buttonBox.getChildren().addAll(skillButton, attackButton, potionButton, antidoteButton);

            skillButton.setMinWidth(buttonBox.getPrefWidth());
            antidoteButton.setMinWidth(buttonBox.getPrefWidth());
            potionButton.setMinWidth(buttonBox.getPrefWidth());
            attackButton.setMinWidth(buttonBox.getPrefWidth());

            for (final javafx.scene.Node b : buttonBox.getChildren()) {
                b.setFocusTraversable(false);
            }

            int i = 0;
            for (final var x : this.main.getEnemies()) {
                // Loads Image of the entity
                ImageView img;
                if (((Enemy) x).getHealth() == 0) {
                    img = new ImageView(defeatedImage);
                } else {
                    img = new ImageView(enemyImage);
                }
                img.setFitWidth(SPRITE_SIZE);
                img.setFitHeight(SPRITE_SIZE);
                enemyGrid.add(img, i, 2);
                i++;
            }
            i = 0;
            for (final var x : this.main.getAllies()) {
                // Loads Image of the entity
                ImageView img;
                if (((Ally) x).getHealth() == 0) {
                    img = new ImageView(defeatedImage);
                } else {
                    img = new ImageView(allyImage);
                }
                img.setFitWidth(SPRITE_SIZE);
                img.setFitHeight(SPRITE_SIZE);
                allyGrid.add(img, i, 2);
                i++;
            }

            // Posizionamento delle etichette e dei bottoni nel layout principale
            pane.setTop(enemyGrid);
            pane.setCenter(buttonBox);
            pane.setBottom(allyGrid);

            root.setAlignment(Pos.CENTER);

            root.getChildren().add(pane);
            this.getScene().setRoot(root);
        });
    }
}
