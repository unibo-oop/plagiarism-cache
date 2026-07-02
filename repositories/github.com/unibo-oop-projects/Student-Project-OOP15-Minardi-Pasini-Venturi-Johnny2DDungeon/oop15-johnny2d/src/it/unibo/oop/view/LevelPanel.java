package it.unibo.oop.view;

import static it.unibo.oop.utilities.CharactersSettings.*;
import static it.unibo.oop.utilities.Direction.*;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JLabel;

import it.unibo.oop.exceptions.SpritesNotSplittableException;
import it.unibo.oop.model.BasicMonster;
import it.unibo.oop.model.Bullet;
import it.unibo.oop.model.GameState;
import it.unibo.oop.model.GameStateImpl;
import it.unibo.oop.model.HealthBonus;
import it.unibo.oop.model.InvisibleMonster;
import it.unibo.oop.model.ScoreBonus;
import it.unibo.oop.model.Wall;
import it.unibo.oop.utilities.Direction;

/**
 * The {@link javax.swing.JPanel} containing the graphics elements of the game's
 * main level.
 */
public class LevelPanel extends BackgroundPanel {

    private static final long serialVersionUID = 8057405927611227670L;
    private static final int SMALL_SPACING = 10;
    private static final int MEDIUM_SPACING = 20;
    private static final int LONG_SPACING = 30;

    private Map<Direction, BufferedImage> mainCharacterSprites;
    private Map<Direction, BufferedImage> enemySprites;
    private Map<Direction, BufferedImage> invisibleEnemySprites;
    private Map<Integer, String> arenasMap;
    private BufferedImage arena;
    private BufferedImage wall;
    private BufferedImage scoreBonus;
    private BufferedImage healthBonus;
    private BufferedImage bullet;
    private BufferedImage health;
    private final JLabel stats;
    private final GameState gs;

    /**
     * Builds the {@link javax.swing.JPanel} and loads every {@link SpriteSheet}.
     */
    public LevelPanel() {
        super("/level.jpg");
        this.gs = GameStateImpl.getInstance();
        this.loadArenas();
        this.loadSprites();
        this.stats = MyLabel.createLabel(null, new Font("Verdana", 1, 40), Color.RED);
        this.setLayout(new FlowLayout(FlowLayout.RIGHT));
        this.add(this.stats);
    }

    private void loadArenas() {
        this.arenasMap = new HashMap<>();
        this.arenasMap.put(0, "/grass_template_straightpath.jpg");
        this.arenasMap.put(1, "/grass_template2.jpg");
        this.arenasMap.put(2, "/light_sand_template.jpg");
        this.arenasMap.put(3, "/light_sand_template_straightpath.jpg");
        this.arenasMap.put(4, "/sand_template.jpg");
        this.arenasMap.put(5, "/sand_template_straightpath.jpg");
        this.arenasMap.put(6, "/snow_template.jpg");
        this.arenasMap.put(7, "/snow_template_decorated.jpg");
        this.arenasMap.put(8, "/snow_template_nodeco.jpg");
        this.arenasMap.put(9, "/snow_template1.jpg");
    }

    private void loadSprites() {
        try {
            final SpriteSheet mainCharacterSheet = new SpriteSheet("/mainCharacter.png");
            this.mainCharacterSprites = mainCharacterSheet.split(MAIN_CHARACTER.getWidth(), MAIN_CHARACTER.getHeight());
            final SpriteSheet enemySheet = new SpriteSheet("/enemy.png");
            this.enemySprites = enemySheet.split(BASIC_ENEMY.getWidth(), BASIC_ENEMY.getHeight());
            final SpriteSheet invisibleEnemySheet = new SpriteSheet("/invisibleEnemy.png");
            this.invisibleEnemySprites = invisibleEnemySheet.split(INVISIBLE_ENEMY.getWidth(),
                    INVISIBLE_ENEMY.getHeight());
            this.wall = ImageLoader.load("/wall.png");
            this.scoreBonus = ImageLoader.load("/coin.png");
            this.healthBonus = ImageLoader.load("/heart.png");
            this.bullet = ImageLoader.load("/bullet.png");
            this.health = ImageLoader.load("/health.png");
        } catch (IOException | SpritesNotSplittableException e) {
            e.printStackTrace();
        }
    }

    public void setArena(final int levelNumber) {
        try {
            this.arena = ImageLoader.load("/field" + this.arenasMap.get(levelNumber));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.arena, this.gs.getArena().getPlayableRectangle().x,
                this.gs.getArena().getPlayableRectangle().y, this.gs.getArena().getPlayableRectangle().width,
                this.gs.getArena().getPlayableRectangle().height, this);
        this.drawStables(g);
        this.drawMovables(g);
        this.drawMainCharacter(g);
        this.drawStats(g);
    }

    private void drawStables(final Graphics g) {
        if (!this.gs.getStableList().isEmpty()) {
            this.gs.getStableList().forEach(e -> {
                if (e instanceof Wall) {
                    g.drawImage(this.wall, e.getTopLeftPos().getIntX(), e.getTopLeftPos().getIntY(), this);
                }
                if (e instanceof ScoreBonus) {
                    g.drawImage(this.scoreBonus, e.getTopLeftPos().getIntX(), e.getTopLeftPos().getIntY(), this);
                }
                if (e instanceof HealthBonus) {
                    g.drawImage(this.healthBonus, e.getTopLeftPos().getIntX(), e.getTopLeftPos().getIntY(), this);
                }
            });
        }
    }

    private void drawMovables(final Graphics g) {
        if (!this.gs.getMovableList().isEmpty()) {
            this.gs.getMovableList().forEach(e -> {
                if (e instanceof BasicMonster) {
                    g.drawImage(this.enemySprites.get(e.getFaceDirection()), e.getTopLeftPos().getIntX(),
                            e.getTopLeftPos().getIntY(), this);
                }
                if ((e instanceof InvisibleMonster) && ((InvisibleMonster) e).isVisible()) {
                    g.drawImage(this.invisibleEnemySprites.get(e.getFaceDirection()), e.getTopLeftPos().getIntX(),
                            e.getTopLeftPos().getIntY(), this);
                }
                if (e instanceof Bullet) {
                    g.drawImage(this.bullet, e.getTopLeftPos().getIntX(), e.getTopLeftPos().getIntY(), this);
                }
            });
        }
    }

    private void drawMainCharacter(final Graphics g) {
        if (!this.gs.getMainChar().get().isDead()) {
            g.drawImage(this.mainCharacterSprites.get(this.gs.getMainChar().get().getFaceDirection()),
                    this.gs.getMainChar().get().getTopLeftPos().getIntX(),
                    this.gs.getMainChar().get().getTopLeftPos().getIntY(), this);
        }
    }

    private void drawStats(final Graphics g) {
        g.drawImage(this.mainCharacterSprites.get(DOWN), this.getX() + MEDIUM_SPACING, this.getY() + MEDIUM_SPACING,
                this);
        for (int i = 0; i < this.gs.getMainChar().get().getHealth().getCurrentHealth(); i++) {
            g.drawImage(this.health, this.getX() + MAIN_CHARACTER.getWidth() + LONG_SPACING
                    + (this.health.getWidth() + SMALL_SPACING) * i, this.getY() + LONG_SPACING, this);
        }
        this.stats.setText(this.gs.getMainChar().get().getScore().toString());
    }
}