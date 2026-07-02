package it.unibo.jurassiko.view.windows;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.jurassiko.controller.api.MainController;
import it.unibo.jurassiko.core.api.GamePhase.Phase;
import it.unibo.jurassiko.model.territory.api.Ocean;
import it.unibo.jurassiko.model.territory.api.Territory;
import it.unibo.jurassiko.model.territory.impl.OceanFactoryImpl;
import it.unibo.jurassiko.model.territory.impl.TerritoryFactoryImpl;
import it.unibo.jurassiko.view.gamescreen.api.View;
import it.unibo.jurassiko.view.gamescreen.impl.ViewImpl;

/**
 * Class that implements the window containing the buttons to select a territory
 * or an ocean.
 */
public class TerritorySelector extends JFrame implements View {

    private static final long serialVersionUID = -4064915483089661582L;
    private static final double WIDTH_RATIO = 0.475;
    private static final double HEIGHT_RATIO = 0.189;
    private static final double X_RATIO = 0.429;
    private static final double Y_RATIO = 0.015;
    private static final int ROWS = 5;
    private static final int HGAP = 5;
    private static final int VGAP = 5;

    private final Map<String, JButton> territoryButtons;
    private final Map<String, JButton> oceanButtons;

    private final transient MainController mainContr;
    private int totalClicks;
    @SuppressFBWarnings("SE_BAD_FIELD")
    private Optional<String> selectedTerritory;

    /**
     * Creates a TerritorySelector window.
     * 
     * @param mainContr the main controller instance
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP2", justification = "MainController instance is needed on this class by design")
    public TerritorySelector(final MainController mainContr) {
        this.mainContr = mainContr;
        this.territoryButtons = new HashMap<>();
        this.oceanButtons = new HashMap<>();
        this.selectedTerritory = Optional.empty();
        final Set<Territory> allTerritories = new TerritoryFactoryImpl().createTerritories();
        final Set<Ocean> allOceans = new OceanFactoryImpl().createOceans();

        final Set<String> territoryNames = allTerritories.stream().map(Territory::getName).collect(Collectors.toSet());
        final Set<String> oceanNames = allOceans.stream().map(Ocean::getName).collect(Collectors.toSet());
        territoryNames.stream().forEach(t -> this.territoryButtons.put(t, createJButton(t)));
        oceanNames.stream().forEach(t -> this.oceanButtons.put(t, createJButton(t)));

        final int width = (int) (WIDTH_RATIO * ViewImpl.getScreenSize().getWidth());
        final int height = (int) (HEIGHT_RATIO * ViewImpl.getScreenSize().getHeight());
        final int x = (int) (X_RATIO * ViewImpl.getScreenSize().getWidth());
        final int y = (int) (Y_RATIO * ViewImpl.getScreenSize().getHeight());

        this.setLayout(new GridLayout(ROWS, 1, 0, VGAP));
        setAllPanels(allTerritories, allOceans);
        this.setTitle("Seleziona un territorio");
        this.setPreferredSize(new Dimension(width, height));
        this.setLocation(x, y);
        this.setResizable(false);
    }

    /**
     * Adds the created panels with all buttons to the main panel.
     * 
     * @param allTerritories the Set containing all game territories
     * @param allOceans      the Set containing all game oceans
     */
    private void setAllPanels(final Set<Territory> allTerritories, final Set<Ocean> allOceans) {
        final JPanel nordAmericaButtons = createContinentPanel(allTerritories, "Nord America");
        final JPanel eurasiaButtons = createContinentPanel(allTerritories, "Eurasia");
        final JPanel gondwanaOccidentaleButtons = createContinentPanel(allTerritories, "Gondwana Occidentale");
        final JPanel gondwanaOrientaleButtons = createContinentPanel(allTerritories, "Gondwana Orientale");
        final JPanel oceanButtons = createOceansPanel(allOceans);

        this.add(nordAmericaButtons);
        this.add(eurasiaButtons);
        this.add(gondwanaOccidentaleButtons);
        this.add(gondwanaOrientaleButtons);
        this.add(oceanButtons);
    }

    /**
     * Initializes the panel and creates the buttons for all territories of a
     * specified continent.
     * 
     * @param allTerritories the Set containing all game territories
     * @param continent      the continent of the panel
     * @return a JPanel containing the buttons for the specified continent
     */
    private JPanel createContinentPanel(final Set<Territory> allTerritories, final String continent) {
        final List<String> territories = filterByContinent(allTerritories, continent);
        final JPanel continentPanel = new JPanel(new GridLayout(1, territories.size(), HGAP, 0));
        this.territoryButtons.keySet().stream()
                .filter(territories::contains)
                .sorted()
                .forEach(t -> continentPanel.add(this.territoryButtons.get(t)));
        return continentPanel;
    }

    /**
     * Initializes the panel and creates the buttons for the oceans.
     * 
     * @param allOceans the Set containing all game oceans
     * @return JPanel containing the buttons for the oceans
     */
    private JPanel createOceansPanel(final Set<Ocean> allOceans) {
        final List<String> oceans = allOceans.stream().map(Ocean::getName).sorted().toList();
        final JPanel oceansPanel = new JPanel(new GridLayout(1, oceans.size(), HGAP, 0));
        this.oceanButtons.keySet().stream()
                .sorted()
                .forEach(o -> oceansPanel.add(this.oceanButtons.get(o)));
        return oceansPanel;
    }

    /**
     * Returns the territory names of the specified continent sorted alphabetically.
     * 
     * @param territories the Set containing all game territories
     * @param continent   the continent from which territory names have to be
     *                    extracted
     * @return a List with the names of the territories of the given continent,
     *         sorted alphabetically
     */
    private List<String> filterByContinent(final Set<Territory> territories, final String continent) {
        return territories.stream()
                .filter(t -> t.getContinent().equals(continent))
                .map(Territory::getName)
                .sorted()
                .toList();
    }

    private JButton createJButton(final String name) {
        final var button = new JButton(name);
        button.addActionListener(e -> {
            mainContr.manageSelection(name);
            switch (mainContr.getGamePhase()) {
                case PLACEMENT -> totalClicks++;
                case ATTACK_FIRST_PART -> {
                    selectedTerritory = Optional.of(name);
                    mainContr.setGamePhase(Phase.ATTACK_SECOND_PART);
                }
                case ATTACK_SECOND_PART -> {
                    selectedTerritory = Optional.empty();
                    mainContr.closeTerritorySelector();
                    mainContr.setGamePhase(Phase.ATTACK_FIRST_PART);
                }
                case MOVEMENT_FIRST_PART -> {
                    selectedTerritory = Optional.of(name);
                    mainContr.setGamePhase(Phase.MOVEMENT_SECOND_PART);
                }
                case MOVEMENT_SECOND_PART -> {
                    selectedTerritory = Optional.empty();
                    mainContr.closeTerritorySelector();
                    mainContr.endTurn();
                }
                default -> throw new IllegalArgumentException("Cannot be in this Phase");
            }
            mainContr.startGameLoop();
        });
        return button;
    }

    /**
     * Get the total amount of Clicks of the all the buttons.
     * 
     * @return total amount of clicks
     */
    public int getTotalClicks() {
        return totalClicks;
    }

    /**
     * Reset the total amount of Clicks of the all the buttons.
     */
    public void resetTotalClicks() {
        totalClicks = 0;
    }

    /**
     * Gets the Selected Territory.
     * 
     * @return An Optional of the SelectedTerritory
     */
    public Optional<String> getSelectedTerritory() {
        return selectedTerritory.isPresent() ? selectedTerritory : Optional.empty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void display() {
        super.pack();
        this.setVisible(true);
    }

    /**
     * Set this frame not visible.
     */
    public void closeView() {
        this.setVisible(false);
    }

    /**
     * Update the Enabled Buttons based on the Phase of the Game.
     */
    public void updateButtons() {
        disableAllJButtons();
        switch (mainContr.getGamePhase()) {
            case PLACEMENT -> {
                if (totalClicks == 0 && !mainContr.isFirstTurn()) {
                    activateButton(oceanButtons.values(), t -> true);
                } else {
                    activateButton(territoryButtons.values(), t -> mainContr.isAllyTerritory(t));
                }
            }
            case ATTACK_FIRST_PART -> {
                if (selectedTerritory.isEmpty()) {
                    activateButton(territoryButtons.values(),
                            t -> mainContr.isAllyTerritoryWithMoreThanOne(t) && mainContr.hasAdjEnemy(t));
                }
            }
            case ATTACK_SECOND_PART -> {
                if (selectedTerritory.isPresent()) {
                    activateButton(territoryButtons.values(),
                            t -> mainContr.getAdj(selectedTerritory.get()).contains(t)
                                    && !mainContr.isAllyTerritory(t));
                }
            }
            case MOVEMENT_FIRST_PART -> {
                if (selectedTerritory.isEmpty()) {
                    activateButton(territoryButtons.values(),
                            t -> mainContr.isAllyTerritoryWithMoreThanOne(t) && mainContr.hasAdjAlly(t));
                }
            }
            case MOVEMENT_SECOND_PART -> {
                if (selectedTerritory.isPresent()) {
                    activateButton(territoryButtons.values(),
                            t -> mainContr.getAdj(selectedTerritory.get()).contains(t)
                                    && mainContr.isAllyTerritory(t));
                }
            }
            default -> throw new IllegalArgumentException("Invalid Phase");
        }

    }

    /**
     * Set all Buttons disabled.
     */
    private void disableAllJButtons() {
        for (final var jb : territoryButtons.values()) {
            jb.setEnabled(false);
        }
        for (final var jb : oceanButtons.values()) {
            jb.setEnabled(false);
        }
    }

    /**
     * Given certain condition activate the button.
     * 
     * @param buttons   Collection of JButtons to modify
     * @param condition if the condition is true will activate the button
     */
    private void activateButton(final Collection<JButton> buttons, final Predicate<String> condition) {
        for (final var jb : buttons) {
            if (condition.test(jb.getText())) {
                jb.setEnabled(true);
            }
        }
    }
}
