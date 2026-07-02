package it.unibo.view.gamelaunchedview.renderers.skinregistry.impl;

import it.unibo.view.gamelaunchedview.renderers.skinregistry.api.SkinSet;
import it.unibo.view.utilities.SpriteEnum;

/**
 * Implementation of {@link SkinSet}.
 * 
 * @param left the {@link SpriteEnum} for the left-facing sprite
 * @param right the {@link SpriteEnum} for the right-facing sprite
 */
public record SkinSetImpl(SpriteEnum left, SpriteEnum right) implements SkinSet { }
