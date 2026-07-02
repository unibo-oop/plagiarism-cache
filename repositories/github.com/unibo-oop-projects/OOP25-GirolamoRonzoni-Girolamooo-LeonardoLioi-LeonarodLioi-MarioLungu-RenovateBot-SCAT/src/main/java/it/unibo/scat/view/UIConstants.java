package it.unibo.scat.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.swing.border.Border;
import javax.swing.border.LineBorder;

/**
 * Utility class containing all the constants related to the UI.
 */
public final class UIConstants {
        // PATHS
        public static final String FONT_PATH = "/fonts/vcr.ttf";
        public static final String INVADER_SHOT_PATH = "/entities/shots/invader_shot.png";
        public static final List<String> PLAYER_SHOT_PATHS = List.of(
                        "/entities/shots/player_shot1.png",
                        "/entities/shots/player_shot2.png",
                        "/entities/shots/player_shot3.png");
        public static final String MENU_BACKGROUND1_PATH = "/backgrounds/menu_background1.jpg";
        public static final String MENU_BACKGROUND2_PATH = "/backgrounds/menu_background2.jpg";
        public static final List<String> GAME_BACKGROUNDS_PATHS = List.of(
                        "/backgrounds/game_background0.jpg",
                        "/backgrounds/game_background1.jpg",
                        "/backgrounds/game_background2.jpg",
                        "/backgrounds/game_background3.jpg");
        public static final List<String> PLAYER_PATHS = List.of(
                        "/entities/player/ship1.png",
                        "/entities/player/ship2.png",
                        "/entities/player/ship3.png");
        public static final List<String> BUNKER_PATHS = List.of(
                        "/entities/bunkers/bunker1.png",
                        "/entities/bunkers/bunker2.png",
                        "/entities/bunkers/bunker3.png");
        public static final List<String> INVADER1_PATHS = List.of(
                        "/entities/invaders/invader_1_1.png",
                        "/entities/invaders/invader_1_2.png");
        public static final List<String> INVADER2_PATHS = List.of(
                        "/entities/invaders/invader_2_1.png",
                        "/entities/invaders/invader_2_2.png");
        public static final List<String> INVADER3_PATHS = List.of(
                        "/entities/invaders/invader_3_1.png",
                        "/entities/invaders/invader_3_2.png");
        public static final String BONUS_INVADER_PATH = "/entities/invaders/invader_4_1.png";
        public static final List<String> LIFE_PANEL_PATHS = List.of(
                        "/images/life/life_0.png",
                        "/images/life/life_1.png",
                        "/images/life/life_2.png",
                        "/images/life/life_3.png");
        public static final String PAUSE_BUTTON_PATH = "/images/pause.png";
        public static final String NULL_PATH = "/entities/null.png";

        // GIFS
        public static final String GIF1_PATH = "/entities/gifs/gif1.gif";
        public static final String GIF2_PATH = "/entities/gifs/gif2.gif";
        public static final String GIF3_PATH = "/entities/gifs/gif3.gif";
        public static final String GIF4_PATH = "/entities/gifs/gif4.gif";

        // COLORS
        public static final Color PANELS_BG_COLOR = Color.BLACK;
        public static final Color WHITE_50_OPACITY = new Color(255, 255, 255, 125);
        public static final Color ARCADE_BLACK = Color.BLACK;
        public static final Color ARCADE_GREEN = new Color(51, 255, 51);

        // PANELS_BORDER
        public static final Border PANELS_BORDER = new LineBorder(ARCADE_GREEN, 2);

        // FONTS
        public static final Font FONT_XXL;
        public static final Font FONT_XXL_HOVER;
        public static final Font FONT_XL;
        public static final Font FONT_L;
        public static final Font FONT_M;
        public static final Font FONT_S;
        public static final Font FONT_XS;

        static {
                final float xxl = 75f;
                final float xxlHover = 80f;
                final float xl = 55f;
                final float l = 45f;
                final float medium = 40f;
                final float mediumSmall = 32f;
                final float small = 24f;

                try (InputStream is = UIConstants.class.getResourceAsStream(FONT_PATH)) {

                        final Font base = Font.createFont(Font.TRUETYPE_FONT, is);
                        GraphicsEnvironment
                                        .getLocalGraphicsEnvironment()
                                        .registerFont(base);

                        FONT_XXL = base.deriveFont(xxl);
                        FONT_XXL_HOVER = base.deriveFont(xxlHover);
                        FONT_XL = base.deriveFont(xl);
                        FONT_L = base.deriveFont(l);
                        FONT_M = base.deriveFont(medium);
                        FONT_S = base.deriveFont(mediumSmall);
                        FONT_XS = base.deriveFont(small);

                } catch (final IOException | FontFormatException e) {
                        throw new ExceptionInInitializerError(e);
                }
        }

        /**
         * Private constructor to avoid initialization.
         */
        private UIConstants() {
        }

}
