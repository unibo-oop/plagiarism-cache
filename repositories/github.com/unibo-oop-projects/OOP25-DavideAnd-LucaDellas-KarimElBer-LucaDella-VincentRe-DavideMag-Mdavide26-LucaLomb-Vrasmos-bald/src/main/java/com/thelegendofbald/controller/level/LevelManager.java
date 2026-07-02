package com.thelegendofbald.controller.level;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.thelegendofbald.combat.Projectile;

import com.thelegendofbald.model.entity.Bald;
import com.thelegendofbald.model.entity.DummyEnemy;
import com.thelegendofbald.model.entity.FinalBoss;
import com.thelegendofbald.model.entity.LifeComponent;
import com.thelegendofbald.model.item.ItemGenerator;
import com.thelegendofbald.model.item.ItemManager;
import com.thelegendofbald.model.item.loot.LootGenerator;
import com.thelegendofbald.model.item.map.MapItemLoader;
import com.thelegendofbald.model.system.CombatManager;
import com.thelegendofbald.utils.LoggerUtils;
import com.thelegendofbald.view.render.Tile;
import com.thelegendofbald.view.render.TileMap;

/**
 * Manages the game levels, including map transitions, entity spawning, and item
 * management.
 */
public class LevelManager {

    private static final String MAP_1 = "map_1";
    private static final String MAP_2 = "map_2";
    private static final String MAP_3 = "map_3";
    private static final String MAP_4 = "map_4";

    private static final int ID_PORTAL = 4;
    private static final int ID_SPAWN = 5;
    private static final int ID_SHOP = 6;
    private static final int ID_ENEMY = 7;
    private static final int ID_PREV_PORTAL = 8;
    private static final int ID_BOSS = 9;
    private static final int ID_NEXT_MAP_TRIGGER = 10;

    private static final int ENEMY_W = 60;
    private static final int ENEMY_H = 60;
    private static final int BOSS_W = 96;
    private static final int BOSS_H = 96;

    private static final int BOSS_HP = 500;

    private final TileMap tileMap;
    private final ItemManager itemManager;
    private final LootGenerator lootGenerator;
    private final List<DummyEnemy> enemies;

    private String currentMapName = MAP_1;
    private FinalBoss boss;

    private final Map<String, String> mapTransitions = Map.of(
            MAP_1, MAP_2,
            MAP_2, MAP_3,
            MAP_3, MAP_4);

    private final Map<String, String> reverseTransitions = Map.of(
            MAP_2, MAP_1,
            MAP_3, MAP_2);

    private Integer pendingEntryTileId;
    private Integer pendingEntryIndex;
    private Boolean pendingFacingRight;

    /**
     * Constructs a new LevelManager.
     *
     * @param width    the map width.
     * @param height   the map height.
     * @param tileSize the tile size.
     */
    public LevelManager(final int width, final int height, final int tileSize) {
        this.tileMap = new TileMap(width, height, tileSize);
        this.enemies = new java.util.LinkedList<>();

        final int healthPotion = 7;
        final int strengthPotion = 8;
        final int coin = 9;

        this.lootGenerator = new LootGenerator(new ItemGenerator(),
                List.of(healthPotion, strengthPotion, coin));
        this.itemManager = new ItemManager(tileMap, new ItemGenerator(), new MapItemLoader(), lootGenerator);
    }

    /**
     * Loads the initial map and spawns the player.
     *
     * @param bald the player character.
     */
    public void loadInitialMap(final Bald bald) {
        itemManager.loadItemsForMap(MAP_1);
        tileMap.changeMap(MAP_1);
        bald.setTileMap(tileMap);
        bald.setSpawnPosition(ID_SPAWN, tileMap.getTileSize());

        final Point spawnPoint = tileMap.findSpawnPoint(ID_SPAWN);
        if (spawnPoint != null) {
            final int tileSize = tileMap.getTileSize();
            bald.setPosX(spawnPoint.x + (tileSize - bald.getWidth()) / 2);
            bald.setPosY(spawnPoint.y - bald.getHeight());
        }
    }

    /**
     * Switches to the next map in the sequence.
     *
     * @param bald          the player.
     * @param combatManager the combat manager.
     */
    public void switchToNextMap(final Bald bald, final CombatManager combatManager) {
        final String nextMapName = mapTransitions.get(currentMapName);
        if (nextMapName != null) {
            pendingEntryTileId = ID_SPAWN;
            pendingEntryIndex = 0;
            setFacingForTransition(currentMapName, nextMapName);
            changeAndLoadMap(nextMapName, bald, combatManager);
        } else {
            LoggerUtils.error("No next map defined.");
        }
    }

    /**
     * Switches to the previous map in the sequence.
     *
     * @param bald          the player.
     * @param combatManager the combat manager.
     */
    public void switchToPreviousMap(final Bald bald, final CombatManager combatManager) {
        final String prevMapName = reverseTransitions.get(currentMapName);
        if (prevMapName != null) {
            pendingEntryTileId = ID_PORTAL;
            pendingEntryIndex = 0;
            setFacingForTransition(currentMapName, prevMapName);
            changeAndLoadMap(prevMapName, bald, combatManager);
        } else {
            LoggerUtils.error("No previous map defined.");
        }
    }

    private void changeAndLoadMap(final String mapName, final Bald bald, final CombatManager combatManager) {
        boss = null;
        currentMapName = mapName;

        tileMap.changeMap(mapName);
        bald.setTileMap(tileMap);

        boolean placed = false;

        if (pendingEntryTileId != null && pendingEntryIndex != null) {
            final List<Point> entries = tileMap.findAllWithId(pendingEntryTileId);
            if (!entries.isEmpty()) {
                final int idx = Math.max(0, Math.min(pendingEntryIndex, entries.size() - 1));
                final Point topLeft = entries.get(idx);
                final int ts = tileMap.getTileSize();
                bald.setPosX(topLeft.x + (ts - bald.getWidth()) / 2);
                bald.setPosY(topLeft.y - bald.getHeight());
                placed = true;
            }
        }

        if (!placed) {
            final List<Point> portals = tileMap.findAllWithId(ID_PORTAL);
            if (!portals.isEmpty()) {
                final Point topLeft = portals.get(0);
                final int ts = tileMap.getTileSize();
                bald.setPosX(topLeft.x + (ts - bald.getWidth()) / 2);
                bald.setPosY(topLeft.y - bald.getHeight());
                placed = true;
            }
        }

        pendingEntryTileId = null;
        pendingEntryIndex = null;

        if (!placed) {
            bald.setSpawnPosition(ID_SPAWN, tileMap.getTileSize());
        }

        bald.setSpeedX(0);
        bald.setSpeedY(0);

        if (pendingFacingRight != null) {
            bald.setFacingRight(pendingFacingRight);
            pendingFacingRight = null;
        }

        spawnActorsFromMap();
        itemManager.loadItemsForMap(mapName);
        combatManager.setBoss(boss);
    }

    private void spawnActorsFromMap() {
        enemies.clear();
        boss = null;

        final List<Point> enemyTiles = tileMap.findAllWithId(ID_ENEMY);
        final List<Point> bossTiles = tileMap.findAllWithId(ID_BOSS);

        enemyTiles.forEach(this::spawnEnemyAt);
        bossTiles.stream().findFirst().ifPresent(this::spawnBossAt);
    }

    private void spawnEnemyAt(final Point topLeft) {
        final int ts = tileMap.getTileSize();
        final int x = topLeft.x + (ts - ENEMY_W) / 2;
        final int y = topLeft.y + (ts - ENEMY_H) / 2;
        enemies.add(new DummyEnemy(x, y, ENEMY_W, "ZioBilly", 10, tileMap));
    }

    private void spawnBossAt(final Point topLeft) {
        if (boss != null) {
            return;
        }

        final int ts = tileMap.getTileSize();
        final int x = topLeft.x + (ts - BOSS_W) / 2;
        final int y = topLeft.y + (ts - BOSS_H) / 2;

        final int bossHp = BOSS_HP;
        final int bossAtk = 1;
        final LifeComponent life = new LifeComponent(bossHp);

        boss = new FinalBoss(
                x, y,
                "Final Boss",
                bossHp,
                bossAtk,
                life,
                tileMap);
    }

    private void setFacingForTransition(final String from, final String to) {
        if (MAP_2.equals(from) && MAP_3.equals(to)) {
            pendingFacingRight = false;
        } else if (MAP_3.equals(from) && MAP_2.equals(to)) {
            pendingFacingRight = false;
        } else if (MAP_3.equals(from) && MAP_4.equals(to)) {
            pendingFacingRight = true;
        } else {
            pendingFacingRight = null;
        }
    }

    /**
     * Resets the level manager to its initial state.
     *
     * @param bald          the player.
     * @param combatManager the combat manager.
     */
    public void reset(final Bald bald, final CombatManager combatManager) {
        currentMapName = MAP_1;
        tileMap.changeMap(MAP_1);
        itemManager.loadItemsForMap(MAP_1);
        spawnActorsFromMap();
        combatManager.setBoss(boss);

        bald.setTileMap(tileMap);
        bald.setSpawnPosition(ID_SPAWN, tileMap.getTileSize());
    }

    /**
     * Updates enemy logic and removes dead enemies.
     *
     * @param bald the player to interact with.
     */
    public void updateEnemies(final Bald bald) {
        enemies.removeIf(DummyEnemy::isRemovable);
        enemies.forEach(enemy -> {
            if (enemy.isCloseTo(bald)) {
                enemy.followPlayer(bald);
                enemy.updateAnimation();
            }
        });
    }

    /**
     * Updates boss logic.
     *
     * @param bald the player to interact with.
     */
    public void updateBoss(final Bald bald) {
        if (boss != null && boss.isAlive()) {
            boss.followPlayer(bald);
            boss.updateAnimation();
        }
    }

    /**
     * Updates item manager logic.
     *
     * @param bald the player collecting items.
     */
    public void updateItems(final Bald bald) {
        itemManager.updateAll();
        itemManager.handleItemCollection(bald);
    }

    /**
     * Moves the specified entity taking tile collisions into account.
     *
     * @param entity    the entity to move (e.g. Bald).
     * @param deltaTime time elapsed since last frame.
     */
    public void moveEntity(final Bald entity, final double deltaTime) {
        entity.move(tileMap, deltaTime);
    }

    /**
     * Moves projectiles and checks for collisions with the map.
     * 
     * @param projectiles the projectiles to move.
     */
    public void moveProjectiles(final List<Projectile> projectiles) {
        projectiles.forEach(p -> p.move(tileMap));
    }

    /**
     * Renders the map background and tiles.
     * 
     * @param g2d graphics context.
     */
    public void renderMap(final Graphics2D g2d) {
        tileMap.paint(g2d);
    }

    /**
     * Renders the items.
     * 
     * @param g2d graphics context.
     */
    public void renderItems(final Graphics2D g2d) {
        itemManager.renderAll(g2d);
    }

    /**
     * Renders the enemies.
     * 
     * @param g2d graphics context.
     */
    public void renderEnemies(final Graphics2D g2d) {
        enemies.forEach(enemy -> enemy.render(g2d));
    }

    /**
     * Renders the boss.
     * 
     * @param g2d graphics context.
     */
    public void renderBoss(final Graphics2D g2d) {
        if (boss != null && boss.isAlive()) {
            boss.render(g2d);
        }
    }

    /**
     * Returns an unmodifiable view of the enemies list.
     *
     * @return the enemies list.
     */
    public List<DummyEnemy> getEnemies() {
        return Collections.unmodifiableList(enemies);
    }

    /**
     * Returns the name of the current map.
     *
     * @return the current map name.
     */
    public String getCurrentMapName() {
        return currentMapName;
    }

    /**
     * Checks if the player is touching a specific tile type (next map trigger).
     * 
     * @param bald the player.
     * @return true if touching next map trigger.
     */
    public boolean isTouchingNextMapTrigger(final Bald bald) {
        return isBaldTouchingTile(bald, ID_NEXT_MAP_TRIGGER);
    }

    /**
     * Checks if the player is touching a specific tile type (previous map portal).
     * 
     * @param bald the player.
     * @return true if touching previous map portal.
     */
    public boolean isTouchingPrevMapPortal(final Bald bald) {
        return isBaldTouchingTile(bald, ID_PREV_PORTAL);
    }

    /**
     * Checks if the player is touching a tile with the specified ID.
     *
     * @param bald   the player.
     * @param tileId the ID of the tile to check.
     * @return true if touching, false otherwise.
     */
    public boolean isBaldTouchingTile(final Bald bald, final int tileId) {
        final int ts = tileMap.getTileSize();

        final int x1 = bald.getX();
        final int y1 = bald.getY();
        final int x2 = x1 + bald.getWidth() - 1;
        final int y2 = y1 + bald.getHeight() - 1;

        final int leftIn = Math.max(0, x1 / ts);
        final int rightIn = Math.max(0, x2 / ts);
        final int topIn = Math.max(0, y1 / ts);
        final int bottomIn = Math.max(0, y2 / ts);

        for (int ty = topIn; ty <= bottomIn; ty++) {
            for (int tx = leftIn; tx <= rightIn; tx++) {
                if (tileHasId(tx, ty, tileId)) {
                    return true;
                }
            }
        }

        final int leftEdgeCol = Math.max(0, (x1 - 1) / ts);
        final int rightEdgeCol = Math.max(0, (x2 + 1) / ts);
        final int topEdgeRow = Math.max(0, (y1 - 1) / ts);
        final int bottomEdgeRow = Math.max(0, (y2 + 1) / ts);

        for (int ty = topIn; ty <= bottomIn; ty++) {
            if (tileHasId(leftEdgeCol, ty, tileId)) {
                return true;
            }
            if (tileHasId(rightEdgeCol, ty, tileId)) {
                return true;
            }
        }
        for (int tx = leftIn; tx <= rightIn; tx++) {
            if (tileHasId(tx, topEdgeRow, tileId)) {
                return true;
            }
            if (tileHasId(tx, bottomEdgeRow, tileId)) {
                return true;
            }
        }

        return false;
    }

    private boolean tileHasId(final int tx, final int ty, final int id) {
        return Optional.ofNullable(tileMap.getTileAt(tx, ty))
                .map(t -> t.getId() == id)
                .orElse(false);
    }

    /**
     * Checks for shop tile near player.
     * 
     * @param bald the player.
     * @return true if near shop.
     */
    public boolean isNearShop(final Bald bald) {
        final int tileSize = tileMap.getTileSize();
        final int baldX = bald.getX();
        final int baldY = bald.getY();
        final int baldW = bald.getWidth();
        final int baldH = bald.getHeight();

        final int feetY = baldY + baldH;
        final int feetYInside = Math.max(0, feetY - 1);

        final int tileFeetY = feetYInside / tileSize;
        final int tileCenterX = (baldX + baldW / 2) / tileSize;

        final Tile tileUnderFeet = tileMap.getTileAt(tileCenterX, tileFeetY);
        return tileUnderFeet != null && tileUnderFeet.getId() == ID_SHOP;
    }

    /**
     * Checks if the boss is alive.
     * 
     * @return true if boss is present and alive.
     */
    public boolean isBossAlive() {
        return boss != null && boss.isAlive();
    }

    /**
     * Attempts to interact with nearby items.
     * 
     * @param bald the player.
     */
    public void tryInteract(final Bald bald) {
        itemManager.getItems().stream()
                .filter(item -> bald.getBounds().intersects(item.getBounds()))
                .filter(item -> item instanceof com.thelegendofbald.model.item.Interactable)
                .map(item -> (com.thelegendofbald.model.item.Interactable) item)
                .findFirst()
                .ifPresent(com.thelegendofbald.model.item.Interactable::interact);
    }

    /**
     * Gets the boss health.
     * 
     * @return boss health or 0.
     */
    public int getBossHealth() {
        return boss != null ? boss.getHealth() : 0;
    }

    /**
     * Gets the boss max health.
     * 
     * @return boss max health or 0.
     */
    public int getBossMaxHealth() {
        return boss != null ? boss.getMaxHealth() : 0;
    }

    /**
     * Gets the tile size.
     * 
     * @return tile size.
     */
    public int getTileSize() {
        return tileMap.getTileSize();
    }
}
