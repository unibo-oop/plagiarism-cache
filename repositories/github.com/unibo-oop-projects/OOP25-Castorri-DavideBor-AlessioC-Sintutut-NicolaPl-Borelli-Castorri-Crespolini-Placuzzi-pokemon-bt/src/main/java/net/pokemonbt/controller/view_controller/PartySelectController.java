package net.pokemonbt.controller.view_controller;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import net.pokemonbt.controller.battle.BattleEnvironment;
import net.pokemonbt.controller.resources.GameSession;
import net.pokemonbt.controller.resources.ResourceManager;
import net.pokemonbt.model.pokemon.Pokemon;
import net.pokemonbt.utility.RandomUtility;
import net.pokemonbt.utility.Refreshable;

/**
 * Controller for the Party Selection scene layout.
 */
public class PartySelectController implements Refreshable {
    private static final int GENERIC_CLIPPING_AMOUNT = 80;
    private static final String BOX_IMAGE_PATH = "img/box/";
    private static final String POKEMON_LIST_PATH = "json/pokemons.json";
    private static final String POKEMON_INFO_PATH = "/layouts/pokemonInfo.fxml";
    private final ResourceManager rm = new ResourceManager();

    private List<Pokemon> pokeList = new LinkedList<>();
    private final Map<String, Image> spriteList = new LinkedHashMap<>();
    private List<Text> allPokemonTexts;
    private List<ImageView> allPokemonIcons;

    @FXML
    private BorderPane titleContainer;

    @FXML
    private GridPane boxGrid;

    @FXML
    private VBox partyColumn;

    @FXML
    private Text txtPoke;
    @FXML
    private Text txtPoke1;
    @FXML
    private Text txtPoke2;
    @FXML
    private Text txtPoke3;
    @FXML
    private Text txtPoke4;
    @FXML
    private Text txtPoke5;

    @FXML
    private Button backButton;
    @FXML
    private Button startButton;

    @FXML
    private Pane backWrapper;
    @FXML
    private Pane startWrapper;

    @FXML
    private ImageView partyIcon;
    @FXML
    private ImageView partyIcon1;
    @FXML
    private ImageView partyIcon2;
    @FXML
    private ImageView partyIcon3;
    @FXML
    private ImageView partyIcon4;
    @FXML
    private ImageView partyIcon5;

    /**
     * Initializes Party Selection View.
     */
    @FXML
    public void initialize() {
        this.rm.initializeMoveList("json/moves.json");
        // gui graphic setup
        ViewUtilities.setupClipping(titleContainer, GENERIC_CLIPPING_AMOUNT);
        ViewUtilities.setupClipping(boxGrid, GENERIC_CLIPPING_AMOUNT);

        partyColumn.getChildren().forEach(pokemon -> {
            ViewUtilities.addShadow(pokemon);
        });

        ViewUtilities.setupClipping(backButton, 1000);
        ViewUtilities.addShadow(backWrapper);
        ViewUtilities.setupClipping(startButton, 1000);
        ViewUtilities.addShadow(startWrapper);

        // pokemon and sprites list setups

        this.allPokemonTexts = List.of(txtPoke, txtPoke1, txtPoke2, txtPoke3, txtPoke4, txtPoke5);
        this.allPokemonIcons = List.of(partyIcon, partyIcon1, partyIcon2, partyIcon3, partyIcon4, partyIcon5);
        this.pokeList = this.rm.getPokemonList(POKEMON_LIST_PATH);
        for (final var poke : this.pokeList) {
            this.spriteList.put(
                    poke.getID(),
                    ResourceManager.getResourceAsImage(BOX_IMAGE_PATH.concat(poke.getID()).concat(".png")));
        }

        this.setGrid();
    }

    /**
     * Refresh method for the party selection controller, execute every time the
     * party selection scene
     * loads.
     */
    @Override
    public void refresh() {
        System.out.println("Party Selection scene refreshed"); // NOPMD
        this.setTeam();
    }

    /**
     * Loads the pokemon info page, taking as active pokemon id the name of the
     * button clicked.
     * 
     * @param e the click event on the "Pokemon" {@link Button}.
     */
    public void onPokemonSelection(final Event e) {
        final Region selectedPokemon = (Region) e.getSource();
        final Optional<Pokemon> pokemon = pokeList.stream()
                .filter(x -> x.getID().equals(selectedPokemon.getId()))
                .findFirst();
        GameSession.addMember(pokemon.get());
        SceneManager.setSceneWithBE(POKEMON_INFO_PATH, null);
    }

    /**
     * Handles click on the "back" button.
     */
    public void onBack() {
        SceneManager.setScene("/layouts/menu.fxml");
    }

    /**
     * Handles click on the "start" button, initializes the
     * {@link BattleEnvironment}.
     */
    public void onStart() {
        if (!GameSession.getCopyOfTeam().isEmpty()) {
            SceneManager.startBattle(this.enemyTeamGenerator(GameSession.getEnemyTeamSize()));
        }
    }

    /**
     * Sets up the {@code boxGrid} view.
     */
    private void setGrid() {
        final Iterator<Pokemon> pokemonIterator = this.pokeList.iterator();
        boxGrid.getChildren().forEach(node -> {
            final Pokemon pokemon = pokemonIterator.next();
            final VBox box = (VBox) node;

            box.setId(pokemon.getID());
            box.getChildren().add(new ImageView(this.spriteList.get(pokemon.getID())));
        });
    }

    /**
     * Sets up the {@code partyColumn} view.
     */
    private void setTeam() {
        final Iterator<Text> textIterator = this.allPokemonTexts.iterator();
        final Iterator<Pokemon> pokeIterator = GameSession.getCopyOfTeam().iterator();
        final Iterator<ImageView> iconsIterator = this.allPokemonIcons.iterator();

        this.partyColumn.getChildren().forEach(cell -> {
            final ImageView imageView = iconsIterator.next();

            if (pokeIterator.hasNext()) {
                final Pokemon pokemon = pokeIterator.next();
                textIterator.next().setText(pokemon.getDisplayName());
                imageView.setImage(spriteList.get(pokemon.getID()));
                cell.setId(pokemon.getID());
            } else {
                textIterator.next().setText("");
                cell.setId("");
                imageView.setImage(null);
            }
        });
    }

    /**
     * @param size the size of the enemy team.
     * @return a new {@link List} of {@link Pokemon} used to initialize the
     *         {@link BattleEnvironment}.
     */
    private List<Pokemon> enemyTeamGenerator(final int size) {
        final List<Pokemon> enemyTeam = new LinkedList<>();
        final List<Pokemon> copyList = new LinkedList<>(
                this.rm.getPokemonList(POKEMON_LIST_PATH));

        for (int i = 0; i < size; i++) {
            enemyTeam.add(copyList.remove((int) RandomUtility.nextInteger(copyList.size())));
        }
        return enemyTeam;
    }
}
