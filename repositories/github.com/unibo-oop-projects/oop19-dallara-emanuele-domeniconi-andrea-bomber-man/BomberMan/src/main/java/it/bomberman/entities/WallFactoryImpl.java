package it.bomberman.entities;

import java.awt.Graphics;
import java.util.Random;
import it.bomberman.collisions.Body;
import it.bomberman.collisions.ICollidable;
import it.bomberman.collisions.Rectangle;
import it.bomberman.collisions.Vector2;
import it.bomberman.entities.PowerUp.PowerUpType;
import it.bomberman.gfx.Assets;

public class WallFactoryImpl implements WallFactory {
	public static final int DEFAULT_WALL_WIDTH = 100;
	public static final int POWER_UP_DROP_PROBABILITY = 50; // 75%

	@Override
	public Wall mapLimitWall(int x, int y, EntityController controller) {
		class MapLimitWall extends HardWall {

			public MapLimitWall(int x, int y, EntityController controller) {
				super(x, y, controller);
			}

			@Override
			public void render(Graphics g) {
				// non va disegnato
				// super.render(g);
			}
		}
		return new MapLimitWall(x, y, controller);
	}

	@Override
	public Wall simpleWall(int x, int y, EntityController controller) {
		return new Wall() {

			private int width = DEFAULT_WALL_WIDTH;
			private Body body = new Body(new Rectangle(x, y, width, width));
			private EntityController contr = controller;

			@Override
			public Vector2 getPosition() {
				return Vector2.unmodifiableVector2(new Vector2(x, y));
			}

			@Override
			public boolean shouldCollide(ICollidable collidable) {
				if (collidable instanceof Player) {
					return true;
				}
				if (collidable instanceof Explosion) {
					return true;
				}
				return false;
			}

			@Override
			public void collision(ICollidable collidable) {
				if (collidable instanceof Explosion) {
					this.collision((Explosion) collidable);
				}

			}

			public void collision(Explosion explosion) {
				// this.dispose();
				// ovvero: Rimozione da vari Observer, Listerner
				this.dispose();
			}

			@Override
			public void tick() {
				// DO NOTHING
				// E' un muro, non fa niente
			}

			@Override
			public void render(Graphics g) {
				// le immagini sono da scalare in quanto il crop delle sprite non � perfetto
				// ma contiene un bordo trasparente in canale alfa.
				// Deve essere corretto in t.bomberman.gfx. Assets
				double scale = 1.18;
				int w = (int) (this.width * scale);
				int h = (int) (this.width * scale);
//				 this.body.render(g, Color.GRAY);
				g.drawImage(Assets.simpleWall, x, y, w, h, null);
			}

			@Override
			public Body getBody() {
				// restituire versione non modificabile?
				return this.body;
			}

			@Override
			public void dispose() {
				// random
				Random random = new Random();
				final PowerUpType type = PowerUpType.values()[random.nextInt(PowerUpType.values().length)];
				if(random.nextInt(100) < POWER_UP_DROP_PROBABILITY){
					PowerUp up = PowerUp.PowerUpBuilder.newBuilder()
							.setX(x + this.width/4)
							.setY(y + this.width/4)
							.setType(type).setValue(1)
							.setController(this.contr).build();
					this.contr.register(up);
				}
				this.contr.notifyDisposal(this);
			}
		};
	}

	@Override
	public Wall hardWall(int x, int y, EntityController controller) {

		return new HardWall(x, y, controller);
	}

	class HardWall implements Wall {
		Wall inner;
		private EntityController controller;

		public HardWall(int x, int y, EntityController controller) {
			this.inner = simpleWall(x, y, controller);
			this.controller = controller;
		}

		@Override
		public Vector2 getPosition() {
			return inner.getPosition();
		}

		@Override
		public Body getBody() {
			return inner.getBody();
		}

		@Override
		public boolean shouldCollide(ICollidable collidable) {
			return collidable instanceof Player;
		}

		@Override
		public void collision(ICollidable collidable) {
			// DO NOTHING
		}

		@Override
		public void tick() {
			// DO NOTHING
		}

		@Override
		public void render(Graphics g) {
			// scala dovuta  a correzione del bordo del crop
			double scale = 1.18;
			int w = (int) (DEFAULT_WALL_WIDTH * scale);
			int h = (int) (DEFAULT_WALL_WIDTH * scale);
//			 this.body.render(g, Color.GRAY);
			g.drawImage(Assets.wall, this.inner.getPosition().getX(), this.inner.getPosition().getY(), w, h, null);
		}

		@Override
		public void dispose() {
			controller.notifyDisposal(this);
		}
	}
	
	// Funzionalita' opzionale non implementata
	@Override
	public Wall deathWall(int x, int y, EntityController contr) {
		return null;

	}

}
