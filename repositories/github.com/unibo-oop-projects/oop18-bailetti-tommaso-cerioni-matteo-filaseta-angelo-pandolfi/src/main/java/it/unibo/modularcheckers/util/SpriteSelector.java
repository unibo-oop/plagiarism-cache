package it.unibo.modularcheckers.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import it.unibo.modularcheckers.model.Color;
import it.unibo.modularcheckers.model.GameType;
import it.unibo.modularcheckers.model.Pair;
import it.unibo.modularcheckers.model.piece.PieceType;

/**
 * Makes easy the selection of sprites.
 */
public class SpriteSelector {

    private static final String BASE_URI = "/images/";
    private static final String CHECKERS_URI = "checkersSprite.png";
    private static final int CHECKERS_SPACING = 64;
    private final Map<Pair<PieceType, Color>, ImageIcon> sprites;

    /**
     *
     */
    public SpriteSelector() {
        sprites = new HashMap<>();
        generate();
    }

    private void generate() {
        Arrays.stream(GameType.values()).forEach(gameType -> {
            if (GameType.CHECKERS.equals(gameType)) {
                try {
                    final URI uri = getClass().getResource(BASE_URI + CHECKERS_URI).toURI();
                    final BufferedImage bi = ImageIO.read(new File(uri));
                    sprites.put(new Pair<>(PieceType.KING, Color.BLACK), new ImageIcon(bi.getSubimage(0, 0, CHECKERS_SPACING, CHECKERS_SPACING)));
                    sprites.put(new Pair<>(PieceType.MAN, Color.BLACK), new ImageIcon(bi.getSubimage(CHECKERS_SPACING, 0, CHECKERS_SPACING, CHECKERS_SPACING)));
                    sprites.put(new Pair<>(PieceType.MAN, Color.WHITE), new ImageIcon(bi.getSubimage(0, CHECKERS_SPACING, CHECKERS_SPACING, CHECKERS_SPACING)));
                    sprites.put(new Pair<>(PieceType.KING, Color.WHITE), new ImageIcon(bi.getSubimage(CHECKERS_SPACING, CHECKERS_SPACING, CHECKERS_SPACING, CHECKERS_SPACING)));
                } catch (IOException | URISyntaxException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Returns the sprite.
     * @param piece a Pair<PieceType, Color> defining a only one image
     * @return the ImageIcon needed for the JButtons to work properly
     */
    public ImageIcon getSprite(final Pair<PieceType, Color> piece) {
        return sprites.get(piece);
    }
}
