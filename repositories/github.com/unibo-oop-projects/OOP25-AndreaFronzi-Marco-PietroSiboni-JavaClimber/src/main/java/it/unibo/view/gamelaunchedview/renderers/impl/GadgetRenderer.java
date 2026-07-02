package it.unibo.view.gamelaunchedview.renderers.impl;

import it.unibo.view.gamelaunchedview.renderers.api.EntityRenderer;
import it.unibo.view.utilities.SpriteEnum;
import it.unibo.view.utilities.SpriteManager;
import it.unibo.model.gameobj.api.Gadget;

import java.awt.Graphics2D;
import java.awt.Image;
import java.util.List;
import java.util.Objects;

/**
 * Renderer for {@link Gadget} entities.
 */
public final class GadgetRenderer implements EntityRenderer<Gadget> {

  /**
   * The {@link SpriteManager} used to get the gadget sprite.
   */
  private final SpriteManager spriteManager;

  /**
   * Constructor for GadgetRenderer.
   * 
   * @param spriteManager the sprite manager to use
   */
  public GadgetRenderer(final SpriteManager spriteManager) {
    this.spriteManager = spriteManager;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void render(final List<Gadget> gadgets, final Graphics2D g) {
    final Image sprite = this.spriteManager.get(SpriteEnum.GADGET);
    if (Objects.nonNull(sprite)) {
      for (final Gadget gadget : gadgets) {
        g.drawImage(sprite, (int) gadget.getPosX(), (int) gadget.getPosY(), (int) gadget.getWidth(),
            (int) gadget.getHeight(), null);
      }
    }
  }
}
