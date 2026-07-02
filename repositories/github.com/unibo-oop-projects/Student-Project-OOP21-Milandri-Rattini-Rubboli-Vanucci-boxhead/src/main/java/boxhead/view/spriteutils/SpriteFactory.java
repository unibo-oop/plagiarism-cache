package boxhead.view.spriteutils;

import boxhead.model.entities.EntityType;

public class SpriteFactory {
	public SpriteFactory() {
	}
	
	public static Sprite createSprite(final EntityType type) {
		return new Sprite(type);
	}
}
	
