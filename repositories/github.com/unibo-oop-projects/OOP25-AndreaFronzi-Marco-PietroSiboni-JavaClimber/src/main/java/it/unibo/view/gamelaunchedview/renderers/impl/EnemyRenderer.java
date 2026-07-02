package it.unibo.view.gamelaunchedview.renderers.impl;

import it.unibo.view.gamelaunchedview.renderers.api.EntityRenderer;
import it.unibo.view.utilities.SpriteEnum;
import it.unibo.view.utilities.SpriteManager;
import it.unibo.model.gameobj.api.Enemy;

import java.awt.Graphics2D;
import java.awt.Image;
import java.util.List;
import java.util.Objects;

/**
 * Renderer for {@link Enemy} entities.
 */
public final class EnemyRenderer implements EntityRenderer<Enemy> {

  /**
   * The {@link SpriteManager} used to get the enemy sprite.
   */
  private final SpriteManager spriteManager;

  /**
   * Constructor for EnemyRenderer.
   * 
   * @param spriteManager the sprite manager to use
   */
  public EnemyRenderer(final SpriteManager spriteManager) {
    this.spriteManager = spriteManager;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void render(final List<Enemy> enemies, final Graphics2D g) {
    final Image sprite = this.spriteManager.get(SpriteEnum.ENEMY);
    if (Objects.nonNull(sprite)) {
      for (final Enemy enemy : enemies) {
        g.drawImage(sprite, (int) enemy.getPosX(), (int) enemy.getPosY(), (int) enemy.getWidth(),
            (int) enemy.getHeight(), null);
      }
    }
  }
}
