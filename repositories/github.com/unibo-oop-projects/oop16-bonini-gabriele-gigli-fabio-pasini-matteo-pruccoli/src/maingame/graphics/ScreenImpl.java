package maingame.graphics;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.io.IOException;
import java.util.Random;

import maingame.entity.item.NormalChest;
import maingame.entity.mob.player.Player;
import maingame.game.Game;
import maingame.level.Level;
import maingame.level.LevelEnum;
import maingame.level.LevelImpl;
import maingame.sound.SoundImpl;
import util.Color;
import util.Vector2;
import util.Vector2Impl;

/** Classe Screen. */

public final class ScreenImpl implements Screen {

    private final Dimension dimension;
    private int[] pixels;
    private int[] originalPixels;
    private final Vector2<Integer> offset = new Vector2Impl<Integer>(0, 0);
    private static final Screen SINGLETON = new ScreenImpl(Game.getGame().getDimension());

    private static final int SCALE = 3;
    private static final int XHUD = 40;
    private static final int YHUD = 80;
    private static final int YHUD2 = 110;
    private static final int XOFFSETLEVELUP = 70;
    private static final int YOFFSETLEVELUP = 55;
    private static final int XOFFSETGATHER = 30;
    private static final int YOFFSETPLAYER = 40;
    private static final int XOFFSETNOTKEY = 150;
    private static final int XOFFSETEXP = 45;
    private static final int YOFFSETEXP = 30;
    private static final int TIME60 = 60;
    private static final int TIME80 = 80;
    private static final int TIME180 = 180;
    private static final int XHEART = 290;
    private static final int YHEART = 15;
    private static final int BACKCOLOR = 0xFFFF00FF;
    private static final int WHITECOLOR = 0xFFFFFF;
    private static final int BLACKCOLOR = 0x000000;
    private static final int NIGHT_LUMINOSITY = 150;
    private static final int OFFSETHEART = 5;
    private static final Vector2<Integer> POS_KEY = new Vector2Impl<Integer>(4, 40);
    private static final Vector2<Integer> POS_PROJ = new Vector2Impl<Integer>(5, 19);
    private static final Vector2<Integer> POS_SUPPROJ = new Vector2Impl<Integer>(5, 29);
    private static final int LUMGAMEOVER = 210;
    private static final String FONT_PATH = "/font/myFont.ttf";
    private static final int FONT_SIZE = 25;
    private static final int FONT_SIZE2 = 60;
    private static final int FONT_SIZE3 = 15;
    private static final Font DEFAULT_FONT = new Font("Algerian", Font.BOLD, FONT_SIZE);
    private int oldTimeLevelUp;
    private int oldTimeGather;
    private int oldTimePotion;
    private int oldTime2;
    private int oldTime3;
    private int oldTimeNotKey;
    private int oldTimeExp;
    private int expToDraw;
    private boolean expAnim;
    private static final int MAX_BRIGHTNESS = 210;
    /** Indica il calo di luminosità qunado piove. */
    public static final int RAIN_BRIGHTNESS = 40;
    private int randomThunder;
    private boolean thunder;
    private int timeStartedRain;
    private int timeStartedThunder;
    private static final int FLASH_TIME = 25;
    private static final int CRASH_TIME = 120;
    private static final int SEC40 = 2400;
    private static final int SEC25 = 1500;
    private static final int SEC7 = 420;
    private final Random random = new Random();
    private int randomRain = random.nextInt(SEC40) + SEC40; // 40-80 secondi
    private int timeRain;
    private Font font;
    private Font font2;
    private Font font3;
    private boolean endGame;
    private boolean rain;
    private int lum;
    private int lastBrigh;

    private ScreenImpl(final Dimension dimension) {
        this.dimension = dimension;
        pixels = new int[(int) (dimension.getWidth() * dimension.getHeight())];
        originalPixels = new int[(int) (dimension.getWidth() * dimension.getHeight())];
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, ScreenImpl.class.getResourceAsStream(FONT_PATH))
                    .deriveFont(Font.BOLD, FONT_SIZE);
            font2 = Font.createFont(Font.TRUETYPE_FONT, ScreenImpl.class.getResourceAsStream(FONT_PATH))
                    .deriveFont(Font.BOLD, FONT_SIZE2);
            font3 = Font.createFont(Font.TRUETYPE_FONT, ScreenImpl.class.getResourceAsStream(FONT_PATH))
                    .deriveFont(Font.BOLD, FONT_SIZE3);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Ritorna lo screen del gioco singleton. Serve per accervi.
     * 
     * @return screen Singleton.
     */
    public static Screen getScreen() {
        return SINGLETON;
    }

    @Override
    public void render(final Vector2<Integer> position, final Sprite sprite, final double intensity,
            final boolean damaged, final boolean fixed) {
        final Vector2<Integer> pos = new Vector2Impl<>(position);
        if (!fixed) {
            pos.set(position.getX() - offset.getX(), position.getY() - offset.getY());
        }
        final Vector2<Integer> p = new Vector2Impl<>(0, 0);
        for (int y = 0; y < sprite.getDimension().getHeight(); y++) {
            p.setY(y + pos.getY());
            for (int x = 0; x < sprite.getDimension().getWidth(); x++) {
                p.setX(x + pos.getX());
                if (p.getX() < -sprite.getDimension().getWidth() || p.getX() >= dimension.getWidth() || p.getY() < 0
                        || p.getY() >= dimension.getHeight()) {
                    break;
                }
                if (p.getX() < 0) {
                    p.setX(0);
                }
                if (p.getY() < 0) {
                    p.setY(0);
                }
                int col = sprite.getPixels()[x + y * (int) sprite.getDimension().getWidth()];
                if (col != BACKCOLOR) {
                    originalPixels[p.getX() + p.getY() * (int) dimension.getWidth()] = col;
                    if (!damaged) {
                        col = Color.alphaBlending(col, (int) (Game.getGame().getLevel().getBrightness() * intensity),
                                BLACKCOLOR);
                    } else {
                        col = WHITECOLOR;
                    }
                    pixels[p.getX() + p.getY() * (int) dimension.getWidth()] = col;
                }
            }
        }
    }

    @Override
    public void renderHud(final int health, final int maxHealth) {
        final Level level = Game.getGame().getLevel();
        final Player player = level.getPlayer();

        // Controllo se il player è danneggiato e renderizzo il sangue su
        // schermo.
        if (player.isInjured()) {
            render(new Vector2Impl<Integer>(0, 0), SpriteImpl.BLOOD.getSprite(), 1.0, false, true);
        } else {
            // Controllo che il passaggio notte-giorno nel level.MAIN dia la
            // musica
            // corretta.
            if (Game.getGame().getNamelevel() == LevelEnum.MAIN && !Game.getGame().isGamewin() && !thunder
                    && !Game.getGame().isGameOver()) {
                if (SoundImpl.BACKGROUND.isStopped()) {
                    SoundImpl.BACKGROUND.play(true);
                }
                if ((rain ? level.getBrightness() - RAIN_BRIGHTNESS : level.getBrightness()) > NIGHT_LUMINOSITY) {
                    if (SoundImpl.NIGHT.isStopped()) {
                        SoundImpl.NIGHT.play(true);
                    }
                } else {
                    if (!SoundImpl.NIGHT.isStopped()) {
                        SoundImpl.NIGHT.stop();
                    }
                }
            }

        }

        if (rain && Game.getGame().getNamelevel() == LevelEnum.MAIN) {
            render(new Vector2Impl<Integer>(0, 0), SpriteImpl.RAIN.getSprite(), 0.0, false, true);
            if (!thunder && !player.isInjured() && LevelImpl.getTime() >= randomThunder) {
                lum = level.getBrightness();
                thunderStart();
            }
            if (thunder) {
                if (LevelImpl.getTime() > FLASH_TIME + timeStartedThunder) {

                    level.setLuminosity(lum);
                }
                if (LevelImpl.getTime() > CRASH_TIME + timeStartedThunder) {
                    thunderStop();
                }
            }
        }

        // Se il gioco è terminato non renderizzo l'Hud
        if (!Game.getGame().isGamewin() && !Game.getGame().isGameOver()) {
            for (int i = 0; i < Math.ceil(health / Math.ceil(maxHealth / 10.0)); i++) {
                render(new Vector2Impl<Integer>(
                        i * (int) (SpriteImpl.HEART.getSprite().getDimension().getWidth() - OFFSETHEART), OFFSETHEART),
                        SpriteImpl.HEART.getSprite(), 0.0, false, true);
            }

            // Render Hud della chiave se raccolta
            if (player.isGotKey()) {
                render(POS_KEY, SpriteImpl.KEY, 0.0, false, true);
            }

            // Render Hud dei proiettili, con controllo con skin 1 o skin 2
            render(POS_PROJ, SpriteImpl.PROJECTILE_HUD, 0.0, false, true);

            if (player.getSkin() == 0 || player.getSkin() == 2) {
                render(POS_SUPPROJ, SpriteImpl.SUPERPROJECTILE_HUD, 0.0, false, true);
            } else if (player.getSkin() == 1) {
                render(POS_SUPPROJ, SpriteImpl.REDSUPERPROJECTILE_HUD, 0.0, false, true);

            }

        }

    }

    private void rainStart() {
        final Level level = Game.getGame().getLevel();
        setRain(true);
        // Tempo di inizio del primo tuono.
        randomThunder = random.nextInt(SEC7) + SEC7 + LevelImpl.getTime();

        // Time di quando è iniziato a piovere.
        timeStartedRain = LevelImpl.getTime();

        timeRain = random.nextInt(SEC25) + SEC25; // 25-50 sec Tempo durata
        lastBrigh = level.getBrightness();
        level.setLuminosity(level.getBrightness() + RAIN_BRIGHTNESS > MAX_BRIGHTNESS ? MAX_BRIGHTNESS
                : level.getBrightness() + RAIN_BRIGHTNESS);

        if (SoundImpl.RAIN.isStopped() && SoundImpl.HEART.isStopped()) {
            SoundImpl.RAIN.play(true);
        }
    }

    private void rainStop() {
        setRain(false);
        Game.getGame().getLevel().setLuminosity(-1);
        SoundImpl.RAIN.stop();
        randomRain = random.nextInt(SEC40) + SEC40 + LevelImpl.getTime();

        Game.getGame().getLevel().setBrightness(lastBrigh);
    }

    private void thunderStart() {
        thunder = true;
        Game.getGame().getLevel().setLuminosity(0);
        timeStartedThunder = LevelImpl.getTime();
        SoundImpl.THUNDER.play(false);

    }

    private void thunderStop() {

        thunder = false;
        SoundImpl.THUNDER.stop();
        if (SoundImpl.RAIN.isStopped() && rain) {
            SoundImpl.RAIN.play(true);
        }

        randomThunder = random.nextInt(SEC7) + SEC7 + LevelImpl.getTime(); // 7-14
                                                                           // secondi
                                                                           // fino
                                                                           // al
                                                                           // prossimo
                                                                           // tuono.

    }

    @Override
    public void renderDraws(final Graphics g) {
        final Level level = Game.getGame().getLevel();
        final Player player = level.getPlayer();
        g.setColor(java.awt.Color.WHITE);
        try {
            g.setFont(font);
        } catch (Exception e) {
            g.setFont(DEFAULT_FONT);
            System.out.println(e);
        }

        if (Game.getGame().getNamelevel() == LevelEnum.MAIN) {
            // controllo se il tempo attuale è maggiore del tempo in cui deve
            // iniziare una pioggia. Si aggiorna ogni pioggia che finisce.
            if (LevelImpl.getTime() >= randomRain && !isRain()) {
                rainStart();
            }

            // controllo se il tempo attuale è maggiore del tempo in cui finirà
            // una
            // pioggia.
            if (LevelImpl.getTime() >= timeStartedRain + timeRain && isRain()) {
                rainStop();
            }
        }

        if (!(Game.getGame().isGameOver()) && !(Game.getGame().isGamewin())) {
            g.drawString(": " + player.getAmmo()[0], XHUD, YHUD);
            g.drawString(": " + player.getAmmo()[1], XHUD, YHUD2);
        }

        // ------------Animazione LevelUp
        if (!player.isLevelUpAnimation()) {
            oldTimeLevelUp = LevelImpl.getTime();
        }
        if (player.isLevelUpAnimation()) {
            g.drawString("LEVEL UP!", level.getPlayerScreenPosition().getX() - XOFFSETLEVELUP,
                    level.getPlayerScreenPosition().getY() - YOFFSETLEVELUP);

            if ((LevelImpl.getTime() - oldTimeLevelUp) >= TIME80) {
                player.setLevelUpAnimation(false);
            }
        }

        // ------------Animazione richiesta Key
        if (!player.isHasnotKey()) {
            oldTimeNotKey = LevelImpl.getTime();
        }
        if (player.isHasnotKey()) {
            g.drawString("YOU NEED A KEY!", level.getPlayerScreenPosition().getX() - XOFFSETNOTKEY,
                    level.getPlayerScreenPosition().getY() - YOFFSETPLAYER);

            if ((LevelImpl.getTime() - oldTimeNotKey) >= TIME60) {
                player.setHasnotKey(false);
            }
        }

        try {
            g.setFont(font3);
        } catch (Exception e) {
            g.setFont(DEFAULT_FONT);
            System.out.println(e);
        }

        // ------------Animazione gatherAmmo
        if (!player.isGatherAmmo()) {
            oldTimeGather = LevelImpl.getTime();
        }
        if (player.isGatherAmmo()) {
            g.drawString("+" + NormalChest.getNormalammo() + " +" + NormalChest.getSuperammo(),
                    level.getPlayerScreenPosition().getX() - XOFFSETGATHER,
                    level.getPlayerScreenPosition().getY() - YOFFSETPLAYER);
            if ((LevelImpl.getTime() - oldTimeGather) >= TIME60) {
                player.setGatherAmmo(false);
            }
        }
        // ------------Animazione Exp
        if (!expAnim) {
            oldTimeExp = LevelImpl.getTime();
        }
        if (expAnim) {

            g.drawString("+" + expToDraw + " exp", level.getPlayerScreenPosition().getX() - XOFFSETEXP,
                    level.getPlayerScreenPosition().getY() - YOFFSETEXP);

            if ((LevelImpl.getTime() - oldTimeExp) >= TIME60) {
                expAnim = false;
            }
        }

        // ------------Animazione MaxPotion
        if (!player.isDrinkPotion()) {
            oldTimePotion = LevelImpl.getTime();
        }
        if (player.isDrinkPotion()) {
            if (player.getHealth() == player.getMaxHealth()) {
                g.drawString("max", XHEART, YHEART);

            }
            if ((LevelImpl.getTime() - oldTimePotion) >= TIME60) {
                player.setDrinkPotion(false);
            }
        }

        // Animazioni GameOver e WinGame
        if (!(Game.getGame().isGameOver())) {
            oldTime2 = LevelImpl.getTime();
        }

        if (!(Game.getGame().isGamewin())) {
            oldTime3 = LevelImpl.getTime();
        }

        if (Game.getGame().isGameOver()) {
            drawGameOver(g);
            if ((LevelImpl.getTime() - oldTime2) >= TIME180) {
                endGame = true;
            }
        }

        if (Game.getGame().isGamewin()) {
            drawGameWin(g);
            if ((LevelImpl.getTime() - oldTime3) >= TIME180) {
                endGame = true;
            }
        }
    }

    private void drawGameOver(final Graphics g) {
        final Level level = Game.getGame().getLevel();
        final Player player = level.getPlayer();

        try {
            g.setFont(font2);
        } catch (Exception e) {
            g.setFont(DEFAULT_FONT);
            System.out.println(e);
        }
        level.setLuminosity(LUMGAMEOVER);
        level.setBrightness(LUMGAMEOVER);
        g.drawString("GAME OVER", dimension.width / 2 + XOFFSETLEVELUP, dimension.height / 2 * SCALE);
        g.setFont(font);
        g.drawString("Punteggio: " + player.getExp(), dimension.width / 2 + XOFFSETLEVELUP,
                dimension.height / 2 * SCALE + YOFFSETPLAYER);
    }

    private void drawGameWin(final Graphics g) {
        final Level level = Game.getGame().getLevel();
        final Player player = level.getPlayer();
        g.setColor(java.awt.Color.YELLOW);
        try {
            g.setFont(font2);
        } catch (Exception e) {
            g.setFont(DEFAULT_FONT);
            System.out.println(e);
        }

        level.setLuminosity(LUMGAMEOVER);
        level.setBrightness(LUMGAMEOVER);
        g.drawString("YOU WIN!", dimension.width / 2 + XOFFSETLEVELUP, dimension.height / 2 * SCALE);
        g.setFont(font);
        g.drawString("Punteggio: " + player.getExp(), dimension.width / 2 + XOFFSETLEVELUP,
                dimension.height / 2 * SCALE + YOFFSETPLAYER);
    }

    @Override
    public void setOffset(final Vector2<Integer> offset) {
        this.offset.set(offset);
    }

    @Override
    public int[] getPixels() { // Non ritorno un nuovo array perchè l'eccessiva
                               // dimensione causa errore.
        return pixels;
    }

    @Override
    public int[] getOriginalPixels() {
        return originalPixels;
    }

    @Override
    public void setPixels(final int i, final int color) {
        pixels[i] = color;
    }

    @Override
    public Dimension getDimension() {
        return new Dimension(dimension);
    }

    @Override
    public boolean isEndGame() {
        return endGame;
    }

    @Override
    public boolean isRain() {
        return rain;
    }

    @Override
    public boolean isThunder() {
        return thunder;
    }

    @Override
    public void setRain(final boolean rain) {
        this.rain = rain;
    }

    @Override
    public void setEndGame(final boolean end) {
        endGame = end;
    }

    @Override
    public void setExpAnim(final boolean b, final int exp) {
        expAnim = b;
        expToDraw = exp;
    }

    @Override
    public void reset() {
        // Resetta i valori nel caso di un nuovo avvio di game.
        randomThunder = 0;
        thunder = false;
        timeStartedRain = 0;
        timeStartedThunder = 0;
        randomRain = random.nextInt(SEC40) + SEC40;
        timeRain = 0;
        rain = false;
        lastBrigh = 0;
    }

}