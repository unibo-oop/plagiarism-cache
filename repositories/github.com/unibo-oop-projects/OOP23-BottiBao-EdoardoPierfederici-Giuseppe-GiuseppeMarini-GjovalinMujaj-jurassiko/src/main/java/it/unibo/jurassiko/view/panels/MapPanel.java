package it.unibo.jurassiko.view.panels;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import it.unibo.jurassiko.controller.api.MainController;
import it.unibo.jurassiko.model.player.api.Player.GameColor;
import it.unibo.jurassiko.reader.impl.SpritePositionsReader;
import it.unibo.jurassiko.view.gamescreen.impl.ViewImpl;

/**
 * Set the map for the GUI.
 */
public class MapPanel extends JPanel {

    private static final long serialVersionUID = -6881386592874173612L;
    private static final String OCEAN_PATH = "config/spritepositions/oceanpositions.json";
    private static final String TERRITORY_PATH = "config/spritepositions/territorypositions.json";
    private static final double HEIGHT_RATIO = 0.8;
    private static final double WIDTH_RATIO = 0.8;
    private static final String URL_IMAGE = "images/map.png";

    private final Map<String, DinoDisplay> territoryViews;
    private final Map<String, DinoDisplay> oceanViews;
    private final transient MainController controller;

    /**
     * Set the map in the relevant label and add it to the LayeredPane.
     * 
     * @param controller is the MainController
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP2", justification = "MainController instance is needed on this class by design")
    public MapPanel(final MainController controller) {
        this.controller = controller;
        this.territoryViews = new HashMap<>();
        this.oceanViews = new HashMap<>();

        BufferedImage mapImage;
        try {
            mapImage = ImageIO.read(ClassLoader.getSystemResource(URL_IMAGE));
        } catch (final IOException e) {
            throw new IllegalStateException("Failed to read the map file", e);
        }

        final int width = (int) (WIDTH_RATIO * ViewImpl.getScreenSize().getWidth());
        final int height = (int) (HEIGHT_RATIO * ViewImpl.getScreenSize().getHeight());
        final ImageIcon map = ViewImpl.scaleImage(mapImage, width, height);
        final JLabel mapLabel = new JLabel(map);
        mapLabel.setBounds(0, 0, width, height);

        final JLayeredPane layPane = new JLayeredPane();
        layPane.add(mapLabel, JLayeredPane.DEFAULT_LAYER);

        final SpriteLoader spriteLoader = new SpriteLoader();
        createTerritoryDisplays(spriteLoader, width, height);
        this.territoryViews.entrySet().stream()
                .forEach(t -> layPane.add(t.getValue(), JLayeredPane.PALETTE_LAYER));

        createOceanDisplays(spriteLoader, width, height);
        this.oceanViews.entrySet().stream()
                .forEach(o -> layPane.add(o.getValue(), JLayeredPane.PALETTE_LAYER));

        layPane.setPreferredSize(new Dimension(width, height));
        this.setLayout(new BorderLayout());
        this.add(layPane);
    }

    /**
     * Update this Panel in order to Display the Correct amount of dino and color.
     */
    public void updateBoard() {
        final var territoriesMap = this.controller.getTerritoriesMap();
        final var currentOcean = this.controller.getCurrentOcean();
        territoriesMap.entrySet().stream().forEach(t -> {
            final String territoryName = t.getKey().getName();
            final DinoDisplay display = this.territoryViews.get(territoryName);
            display.setSpriteColor(t.getValue().x());
            display.setNumber(t.getValue().y());
        });

        this.oceanViews.values().forEach(o -> o.setSpriteColor(GameColor.DEFAULT));
        if (currentOcean.isPresent()) {
            final var currentOceanContent = currentOcean.get();
            this.oceanViews.get(currentOceanContent.x().getName()).setSpriteColor(currentOceanContent.y());
        }
    }

    private void createTerritoryDisplays(final SpriteLoader spriteLoader, final int width, final int height) {
        final var territoryPositions = new SpritePositionsReader().readFileData(TERRITORY_PATH);
        territoryPositions.entrySet().stream()
                .forEach(t -> this.territoryViews.put(t.getKey(),
                        new DinoDisplay(spriteLoader, false, calculatePosition(t.getValue().x(), width),
                                calculatePosition(t.getValue().y(), height))));
    }

    private void createOceanDisplays(final SpriteLoader spriteLoader, final int width, final int height) {
        final var oceanPositions = new SpritePositionsReader().readFileData(OCEAN_PATH);
        oceanPositions.entrySet().stream()
                .forEach(t -> this.oceanViews.put(t.getKey(),
                        new DinoDisplay(spriteLoader, true, calculatePosition(t.getValue().x(), width),
                                calculatePosition(t.getValue().y(), height))));
    }

    /**
     * Calculate the position of dino in proportion of panel.
     * 
     * @param percentage the position in percent
     * @param size       size of the panel
     * @return the calculated position in the map
     */
    private int calculatePosition(final double percentage, final int size) {
        return (int) (percentage / 100 * size);
    }

}
