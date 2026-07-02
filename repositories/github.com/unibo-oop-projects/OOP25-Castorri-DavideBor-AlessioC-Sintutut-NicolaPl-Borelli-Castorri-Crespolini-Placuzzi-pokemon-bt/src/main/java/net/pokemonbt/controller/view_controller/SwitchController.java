package net.pokemonbt.controller.view_controller;

import java.util.Locale;
import java.util.Map;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import net.pokemonbt.controller.battle.BattleEnvironment;
import net.pokemonbt.controller.resources.GameSession;
import net.pokemonbt.controller.resources.ResourceManager;
import net.pokemonbt.model.pokemon.PokeStatType;
import net.pokemonbt.model.pokemon.PokeType;
import net.pokemonbt.model.pokemon.Pokemon;
import net.pokemonbt.model.pokemon.TeamType;
import net.pokemonbt.utility.Refreshable;

/**
 * Controller for the 'switch' section of the battle.
 */
public class SwitchController implements Refreshable {
    private static final int IMG_INDEX = 0;
    private static final int INFO_INDEX = 1;
    private static final int ACTIVE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HP_INDEX = 2;
    private static final int TYPE_INDEX = 3;

    private static final String FRONT_IMAGE_PATH = "/img/front/";
    private static final String TYPE_IMAGE_PATH = "/img/types/";
    private static final String IMG_EXT = ".png";

    private static final String ALIVE_CLASS = "pokemon-alive";
    private static final String KO_CLASS = "pokemon-ko";
    private static final String MISSING_CLASS = "pokemon-missing";

    private final BattleEnvironment be;
    private Map<String, Pokemon> team;

    @FXML
    private GridPane teamGrid;

    @FXML
    private Button backBtn;

    /**
     * Creates the controller of the view for switching the active Pokemon
     * during a battle.
     * 
     * @param be The Environment the battle is taking place in.
     */
    public SwitchController(final BattleEnvironment be) {
        this.be = be;
    }

    /**
     * Set's the view elements with the correct pokemon values of
     * the current Team.
     */
    public void initialize() {
        this.team = be.getAllPokemons(TeamType.PLAYER);

        // final var rm = new ResourceManager();
        final var iter = this.team.entrySet().iterator();

        ViewUtilities.addShadow(backBtn);

        // For each slot of the grid.
        for (final var slot : teamGrid.getChildren()) {
            final var infos = ((VBox) ((HBox) slot).getChildren().get(INFO_INDEX)).getChildren();

            if (iter.hasNext()) {
                final var pokemon = iter.next();

                // Set image.
                final var img = ResourceManager.getResourceAsImage(
                        FRONT_IMAGE_PATH
                                .concat(pokemon.getKey())
                                .concat(IMG_EXT));
                ((ImageView) ((HBox) slot).getChildren().get(IMG_INDEX)).setImage(img);

                // Set name.
                ((Label) infos.get(NAME_INDEX)).setText(
                        pokemon.getValue().getName());

                // Set types.
                final String firstT = TYPE_IMAGE_PATH.concat(
                        pokemon.getValue()
                                .getStatComponent()
                                .getPrimaryType()
                                .displayAs()
                                .concat(IMG_EXT));
                final String secondT = TYPE_IMAGE_PATH.concat(
                        pokemon.getValue()
                                .getStatComponent()
                                .getSecondaryType()
                                .displayAs()
                                .concat(IMG_EXT));

                final HBox types = (HBox) infos.get(TYPE_INDEX);
                types.getChildren()
                        .add(new ImageView(
                                ResourceManager.getResourceAsImage(firstT.toLowerCase(Locale.ROOT))));

                // Add second type only if it is actually present.
                if (!secondT.contains(PokeType.NONE.displayAs())) {
                    types.getChildren()
                            .add(new ImageView(
                                    ResourceManager.getResourceAsImage(secondT.toLowerCase(Locale.ROOT))));
                }
            } else {
                slot.getStyleClass().add(MISSING_CLASS);
                ((ImageView) ((HBox) slot).getChildren().get(IMG_INDEX)).setFitWidth(0);
                ((Label) infos.get(NAME_INDEX)).setText("Missing...");
                infos.get(HP_INDEX).setVisible(false);
                slot.setDisable(true);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void refresh() {

        backBtn.setDisable(
                be.getDeadPokemons(TeamType.PLAYER)
                        .containsValue(be.getCurrentOwn(TeamType.PLAYER)));

        this.team = be.getAllPokemons(TeamType.PLAYER);

        final var iter = this.team.entrySet().iterator();

        // For each slot of the grid.
        for (final var slot : teamGrid.getChildren()) {
            final var infos = ((VBox) ((HBox) slot).getChildren().get(INFO_INDEX)).getChildren();

            if (iter.hasNext()) {
                final var pokemon = iter.next();

                // Set corresponding background.
                if (pokemon.getValue().getStatComponent().getHP() > 0) {
                    slot.getStyleClass().add(ALIVE_CLASS);
                } else {
                    slot.getStyleClass().add(KO_CLASS);
                }

                // Set "active" state label.
                if (pokemon.getKey().equals(
                        be.getCurrentOwn(TeamType.PLAYER)
                                .getID())) {
                    infos.get(ACTIVE_INDEX).setVisible(true);
                } else {
                    infos.get(ACTIVE_INDEX).setVisible(false);
                }

                // Set HealthBar.
                final double baseSize = ((StackPane) infos.get(HP_INDEX)).getPrefWidth();
                final Pane healthBar = (Pane) ((StackPane) infos.get(HP_INDEX))
                        .getChildren()
                        .getFirst();

                final double ratio = (double) pokemon.getValue()
                        .getStatComponent()
                        .getHP() / pokemon.getValue()
                                .getStatComponent()
                                .getBaseStat(PokeStatType.HP_MAX);

                healthBar.setMaxWidth(baseSize * ratio);
            }
        }
    }

    /**
     * Called by clicking a {@link Pokemon} in the team.
     * 
     * @param e The source of the selection.
     */
    public void onSelection(final Event e) {
        final VBox slot = (VBox) ((HBox) e.getSource()).getChildren()
                .stream()
                .filter(child -> child.getId().contains("info"))
                .findAny()
                .get();

        final Label name = (Label) slot.getChildren()
                .stream()
                .filter(child -> child.getId().contains("name"))
                .findAny()
                .get();

        final Pokemon selection = team.values()
                .stream()
                .filter(pk -> pk.getDisplayName().equals(name.getText()))
                .findAny()
                .get();

        GameSession.addMember(selection);
        SceneManager.setSceneWithBE(SceneManager.POKE_INFO_PATH, be);

    }

    /**
     * Called by clicking the "Back" button trying to cancel the
     * switch of the current active {@link Pokemon}.
     */
    public void onBack() {
        SceneManager.setScene(SceneManager.BATTLE_PATH);
    }
}
