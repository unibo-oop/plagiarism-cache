package view.gamescreen;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.jfoenix.controls.JFXButton;

import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import utils.enumerations.Status;
import view.ViewImpl;

/**
 * 
 * Sidebar pane that displays all info about player and about a state (when clicked).
 * It is composed of three parts, the first displays all player's info, the second displays
 * state info and the last shows all player's bonus card.
 *
 */
public class PlayerInfoSidebar extends BorderPane {

    private static final double SIDEBAR_HEIGHT = Screen.getPrimary().getBounds().getHeight() / 1.20;
    private static final double SIDEBAR_WIDTH = Screen.getPrimary().getBounds().getWidth() / 6;
    private static final double TILE_SIZE = 76;
    private static final double SPACING = 100;
    private static final double LBL_AIM_FONT_SIZE = 28;

    private final TilePane cardsContainer = new TilePane(Orientation.HORIZONTAL, 20, 20);
    private final Label playerAim = new Label("");
    private final TextArea logger = new TextArea();

    private final List<Card> cardList = new ArrayList<>(3);

    /**
     * 
     * Class constructor.
     * 
     */
    public PlayerInfoSidebar() {
        /* Player aim labels */
        final VBox playerAimContainer = new VBox();
        playerAimContainer.setMaxWidth(SIDEBAR_WIDTH);
        playerAim.setPrefWidth(SIDEBAR_WIDTH);
        playerAim.setWrapText(true);
        final Label target = new Label("Aim: ");
        target.setFont(new Font(LBL_AIM_FONT_SIZE));
        playerAimContainer.setMinHeight(SIDEBAR_HEIGHT / 3);
        playerAimContainer.getChildren().addAll(target, playerAim);

        /* Bonus card container implementation */
        final VBox cardsArea = new VBox();
        final JFXButton sendCombo = new JFXButton("Use Combo");
        final JFXButton bestCombo = new JFXButton("Use Best Combo");
        final HBox btnContainer = new HBox(sendCombo, bestCombo);
        final ScrollPane scrollingArea = new ScrollPane(cardsContainer);
        scrollingArea.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
        scrollingArea.setHbarPolicy(ScrollBarPolicy.NEVER);
        cardsContainer.setPrefColumns((int) (SIDEBAR_WIDTH / TILE_SIZE / 2));
        cardsContainer.setMaxWidth(SIDEBAR_WIDTH);
        cardsContainer.setPrefTileHeight(TILE_SIZE);
        cardsContainer.setPrefTileWidth(TILE_SIZE);

        /* Combo buttons handlers */
        sendCombo.setOnMouseClicked(e -> {
            if (isComboUsable()) {
                ViewImpl.getIstance().getController().tradeCombo(new ArrayList<Card>(cardList).stream().map(c -> c.getCardType()).collect(Collectors.toList()));
                ViewImpl.getIstance().setTanksToDeploy(ViewImpl.getIstance().getPlayerList().getHead().getTanksToDeploy());
                cardList.removeIf(c -> cardList.contains(c));
                ViewImpl.getIstance().updateInfoPlayer();
            } else {
                ViewImpl.getIstance().printError("AVAILABLE ONLY IN DEPLOYMENT PHASE!");
            }
        });
        bestCombo.setOnMouseClicked(e -> {
            if (isComboUsable()) {
                if (ViewImpl.getIstance().getController().getBestCombo().isEmpty()) {
                    ViewImpl.getIstance().printError("No combo available!");
                } else {
                    ViewImpl.getIstance().getController().tradeCombo(ViewImpl.getIstance().getController().getBestCombo());
                    ViewImpl.getIstance().setTanksToDeploy(ViewImpl.getIstance().getPlayerList().getHead().getTanksToDeploy());
                    cardList.removeIf(c -> ViewImpl.getIstance().getController().getBestCombo().contains(c.getCardType()));
                    ViewImpl.getIstance().updateInfoPlayer();
                }
            } else {
                ViewImpl.getIstance().printError("AVAILABLE ONLY IN DEPLOYMENT PHASE!");
            }
        });

        logger.setEditable(false);

        /* Defining components size */
        sendCombo.setMaxWidth(Double.MAX_VALUE);
        bestCombo.setMaxWidth(Double.MAX_VALUE);
        cardsArea.getChildren().addAll(scrollingArea, btnContainer);
        btnContainer.setPrefWidth(SIDEBAR_WIDTH);
        btnContainer.setAlignment(Pos.CENTER);
        HBox.setHgrow(sendCombo, Priority.ALWAYS);
        HBox.setHgrow(bestCombo, Priority.ALWAYS);
        scrollingArea.setPrefSize(SIDEBAR_WIDTH, SIDEBAR_HEIGHT / 3 - SPACING);
        logger.setMinSize(SIDEBAR_WIDTH, SIDEBAR_HEIGHT / 3 - SPACING);
        scrollingArea.setFitToWidth(true);

        this.setTop(playerAimContainer);
        this.setCenter(cardsArea);
        this.setBottom(logger);
    }

    /**
     * 
     * Add a card to its container.
     * 
     */
    public void updateCards() {
       cardsContainer.getChildren().clear();
       cardList.clear();
       if (!ViewImpl.getIstance().getPlayerList().getHead().getBonusCardsList().isEmpty()) {
           ViewImpl.getIstance().getPlayerList().getHead().getBonusCardsList().forEach(c -> {
               final Card card = new Card(c);
               cardsContainer.getChildren().add(card);
               cardSelectionManager(card);
           });
       }
    }

    /**
     * 
     * Add a line to logger.
     * 
     * @param msg
     *              The line to be added.
     * 
     */
    public void addLogInfo(final String msg) {
        this.logger.appendText(msg + '\n');
    }

    /**
     * 
     * Update current player's aim.
     * 
     */
    public void updateAim() {
        this.playerAim.setText(ViewImpl.getIstance().getPlayerList().getHead().getAim().toString());
    }

    private void cardSelectionManager(final Card c) {
        c.setOnMouseClicked((e -> {
            if (!c.isSelected() && this.cardList.size() < 3) {
                this.cardList.add(c);
                c.setSelected();
            } else
                if (!c.isSelected() && this.cardList.size() >= 3) {
                    this.cardList.get(0).setSelected();
                    this.cardList.remove(0);
                    this.cardList.add(c);
                    c.setSelected();
                }  else
                    if (c.isSelected()) {
                        c.setSelected();
                        this.cardList.remove(c);
                    }
        }));
    }

    private boolean isComboUsable() {
        return ViewImpl.getIstance().getController().getGameStatus().equals(Status.DEPLOYMENT) && ViewImpl.getIstance().getPlayerList().getHead().getTanksToDeploy() == ViewImpl.getIstance().getTanksToDeploy();
    }

}
