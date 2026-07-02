package net.pokemonbt.controller.view_controller;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import net.pokemonbt.controller.battle.BattleEnvironment;
import net.pokemonbt.controller.resources.GameSession;
import net.pokemonbt.controller.resources.ResourceManager;
import net.pokemonbt.model.pokemon.PokeStatType;
import net.pokemonbt.model.pokemon.PokeType;
import net.pokemonbt.model.pokemon.Pokemon;
import net.pokemonbt.model.pokemon.TeamType;
import net.pokemonbt.model.pokemon.components.MoveComponent;
import net.pokemonbt.utility.Refreshable;

/**
 * Controller for the Pokemon Info scene layout.
 */
public final class PokemonInfoController implements Refreshable {
    private static final int TEAM_SIZE = 6;
    private static final String FRONT_IMAGE_PATH = "img/front/";
    private static final String SWITCH_SCENE = "/layouts/switch.fxml";
    private static final String BATTLE_SCENE = "/layouts/battle.fxml";
    private static final String PARTY_SELECT_PATH = "/layouts/partySelect.fxml";
    private static final String IMG_EXT = ".png";
    private final List<Pokemon> alreadySelected = new LinkedList<>();
    // private final ResourceManager re = new ResourceManager();
    private final BattleEnvironment be;
    private List<VBox> moveWrappers;
    private List<ImageView> categoryList;
    private List<Text> moveNameList;
    private List<Text> moveDamageList;
    private List<Text> moveDescriptionList;

    @FXML
    private Pane nameTypesWrapper;
    @FXML
    private Pane addButtonWrapper;
    @FXML
    private Pane backButtonWrapper;
    @FXML
    private Pane removeButtonWrapper;
    @FXML
    private Pane switchInButtonWrapper;

    @FXML
    private StackPane imageWrapper;

    @FXML
    private GridPane moveGrid;

    @FXML
    private Text pokemonName;
    @FXML
    private Text hpValue;
    @FXML
    private Text atkValue;
    @FXML
    private Text defValue;
    @FXML
    private Text satkValue;
    @FXML
    private Text sdefValue;
    @FXML
    private Text speValue;
    @FXML
    private Text moveNameText1;
    @FXML
    private Text moveNameText2;
    @FXML
    private Text moveNameText3;
    @FXML
    private Text moveNameText4;
    @FXML
    private Text moveDamage1;
    @FXML
    private Text moveDamage2;
    @FXML
    private Text moveDamage3;
    @FXML
    private Text moveDamage4;
    @FXML
    private Text moveDescription1;
    @FXML
    private Text moveDescription2;
    @FXML
    private Text moveDescription3;
    @FXML
    private Text moveDescription4;

    @FXML
    private VBox move1;
    @FXML
    private VBox move2;
    @FXML
    private VBox move3;
    @FXML
    private VBox move4;

    @FXML
    private Button addButton;

    @FXML
    private ImageView category1;
    @FXML
    private ImageView category2;
    @FXML
    private ImageView category3;
    @FXML
    private ImageView category4;
    @FXML
    private ImageView type1;
    @FXML
    private ImageView type2;

    private final ImageView icon = new ImageView();

    /**
     * Constructor for the Pokemon Info scene controller.
     * 
     * @param be The reference to the Battle Enviroment, may be null.
     */
    public PokemonInfoController(final BattleEnvironment be) {
        this.be = be;
    }

    /**
     * Initializes the pokemon info controller.
     */
    @FXML
    public void initialize() {
        ViewUtilities.addShadow(this.imageWrapper);
        ViewUtilities.setupClipping((Pane) this.imageWrapper.getChildren().getFirst(), 1000);
        ViewUtilities.addShadow(this.nameTypesWrapper);

        this.moveGrid.getChildren().forEach(moveWrapper -> {
            final Pane movePane = (Pane) moveWrapper;
            ViewUtilities.addShadow(movePane);
        });

        ViewUtilities.addShadow(this.addButtonWrapper);
        ViewUtilities.addShadow(this.backButtonWrapper);
        ViewUtilities.addShadow(this.removeButtonWrapper);

        ViewUtilities.hideParent(this.removeButtonWrapper);

        this.moveNameList = List.of(this.moveNameText1, this.moveNameText2, this.moveNameText3, this.moveNameText4);
        this.moveDamageList = List.of(this.moveDamage1, this.moveDamage2, this.moveDamage3, this.moveDamage4);
        this.moveDescriptionList = List.of(this.moveDescription1, this.moveDescription2, this.moveDescription3,
                this.moveDescription4);
        this.moveWrappers = List.of(this.move1, this.move2, this.move3, this.move4);
        this.categoryList = List.of(this.category1, this.category2, this.category3, this.category4);
    }

    @Override
    public void refresh() {
        this.setData();
        this.setButtons();

        System.out.println("Info page refreshed"); // NOPMD
    }

    /**
     * Gets all the data from the model to setup the pokemon info page.
     */
    private void setData() {
        final Pokemon pokemon = GameSession.getCopyOfTeam().getLast();

        // name
        this.pokemonName.setText(pokemon.getDisplayName());

        // stats
        this.hpValue.setText(String.valueOf(pokemon.getStatComponent().getHP()));
        this.atkValue.setText(String.valueOf(pokemon.getStatComponent().getStat(PokeStatType.ATK)));
        this.defValue.setText(String.valueOf(pokemon.getStatComponent().getStat(PokeStatType.DEF)));
        this.satkValue.setText(String.valueOf(pokemon.getStatComponent().getStat(PokeStatType.SPA)));
        this.sdefValue.setText(String.valueOf(pokemon.getStatComponent().getStat(PokeStatType.SPD)));
        this.speValue.setText(String.valueOf(pokemon.getStatComponent().getStat(PokeStatType.SPE)));

        this.setupMoves(pokemon.getMoveComponent());

        icon.setImage(ResourceManager.getResourceAsImage(FRONT_IMAGE_PATH.concat(pokemon.getID()).concat(IMG_EXT)));

        if (imageWrapper.getChildren().size() > 1) {
            imageWrapper.getChildren().removeLast();
        }
        imageWrapper.getChildren().add(icon);

        type1.setImage(ResourceManager.getResourceAsImage(
                "img/types/".concat(
                        pokemon.getStatComponent()
                                .getPrimaryType()
                                .displayAs()
                                .toLowerCase(Locale.ROOT))
                        .concat(IMG_EXT)));
        if (PokeType.NONE != pokemon.getStatComponent().getSecondaryType()) {
            type2.setVisible(true);
            type2.setImage(
                    ResourceManager.getResourceAsImage(
                            "img/types/".concat(
                                    pokemon.getStatComponent()
                                            .getSecondaryType()
                                            .displayAs()
                                            .toLowerCase(Locale.ROOT))
                                    .concat(IMG_EXT)));
        } else {
            type2.setVisible(false);
        }

    }

    /**
     * Sets up move data for each one on the pokemon move set.
     * 
     * @param moveComponent the {@code moveComponent} used to set up the move set.
     */
    private void setupMoves(final MoveComponent moveComponent) {
        final var moveIterator = moveComponent.getMoveSet().iterator();
        final Iterator<Text> nameIterator = moveNameList.iterator();
        final Iterator<Text> damageIterator = moveDamageList.iterator();
        final Iterator<Text> descriptionIterator = moveDescriptionList.iterator();
        final var moveWrapperIterator = moveWrappers.iterator();
        final var categoryIterator = categoryList.iterator();

        moveGrid.getChildren().forEach(wrapper -> {
            // System.out.println(move.getDescription());
            if (moveIterator.hasNext()) {
                final var move = moveIterator.next();
                nameIterator.next().setText(move.getDisplayName());

                damageIterator.next().setText(
                        String.valueOf(
                                move.getPower() > 0
                                        ? move.getPower()
                                        : ""));
                categoryIterator.next().setImage(
                        ResourceManager.getResourceAsImage(
                                "img/".concat(move.getCategory().categoryType().toLowerCase(Locale.ROOT))
                                        .concat(IMG_EXT)));

                descriptionIterator.next().setText(move.getDescription());

                // Modify the BG color to indicate the corresponding type of the move.
                final var moveStyle = moveWrapperIterator.next().getStyleClass();

                moveStyle.clear();
                moveStyle.addAll(
                        "move",
                        move.getType()
                                .displayAs()
                                .toLowerCase(Locale.ROOT));
            } else {
                wrapper.setVisible(false);
            }
        });
    }

    /**
     * Shows the right button between the add and the remove one.
     */
    public void setButtons() {
        if (SceneManager.isInBattle()) {
            ViewUtilities.showParent(this.switchInButtonWrapper);
            ViewUtilities.hideParent(this.addButtonWrapper);
            ViewUtilities.hideParent(this.removeButtonWrapper);

            this.switchInButtonWrapper.setDisable(
                    be.getCurrentOwn(TeamType.PLAYER)
                            .getID()
                            .equals(
                                    GameSession.getCopyOfTeam()
                                            .getLast()
                                            .getID()));

        } else {
            if (this.alreadySelected.contains(GameSession.getCopyOfTeam().getLast())) {
                ViewUtilities.showParent(this.removeButtonWrapper);
                ViewUtilities.hideParent(this.addButtonWrapper);
                ViewUtilities.hideParent(this.switchInButtonWrapper);
            } else {
                ViewUtilities.showParent(this.addButtonWrapper);
                ViewUtilities.hideParent(this.removeButtonWrapper);
                ViewUtilities.hideParent(this.switchInButtonWrapper);
            }

            if (this.alreadySelected.size() >= TEAM_SIZE) {
                this.addButton.setDisable(true);
            } else {
                this.addButton.setDisable(false);
            }
        }
    }

    /**
     * Handles the selection of the new team member.
     */
    public void onAddToTeam() {
        // add pokemon to the team list!
        this.alreadySelected.add(GameSession.getCopyOfTeam().getLast());
        SceneManager.setScene(PARTY_SELECT_PATH);
    }

    /**
     * Handles the click on the back button.
     */
    public void onBack() {
        if (SceneManager.isInBattle()) {
            GameSession.clearTeam();
            SceneManager.setScene(SWITCH_SCENE);
        } else {
            GameSession.removeLast();
            SceneManager.setScene(PARTY_SELECT_PATH);
        }
    }

    /**
     * Handles the remove of a team member.
     */
    public void onRemove() {
        this.alreadySelected.removeAll(GameSession.getCopyOfTeam().stream()
                .filter(t -> t.equals(GameSession.getCopyOfTeam().getLast())).toList());

        GameSession.removeAllCopyOfMember(GameSession.getCopyOfTeam().getLast());
        SceneManager.setScene(PARTY_SELECT_PATH);
    }

    /**
     * When is clicked the "switch" button this method is called and (if it is
     * possible)
     * swaps the current {@link Pokemon} with the selected one.
     */
    public void onSwitch() {
        final String pokeID = GameSession.getCopyOfTeam().getLast().getID();

        if (be.getAlivePokemons(TeamType.PLAYER)
                .containsKey(pokeID)
                &&
                !be.getCurrentOwn(TeamType.PLAYER)
                        .getID()
                        .equals(pokeID)) {
            be.swapPokemon(
                    TeamType.PLAYER,
                    pokeID);

            GameSession.clearTeam();
            SceneManager.setScene(BATTLE_SCENE);
        }
    }
}
