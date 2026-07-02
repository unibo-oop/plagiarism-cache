package it.unibo.oop.bounce.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import it.unibo.oop.bounce.ball.BallImpl;
import it.unibo.oop.bounce.ball.BallViewImpl;
import it.unibo.oop.bounce.ball.MovementImpl;
import it.unibo.oop.bounce.game.Bounce;
import it.unibo.oop.bounce.manager.CollisionImpl;
import it.unibo.oop.bounce.manager.Manager;
import it.unibo.oop.bounce.obstacles.ObstaclesCreatorImpl;

public class PlayScreen implements Screen {
	
	private final Manager manager;
	private Bounce game;
	private TextureAtlas atlas;
	private OrthographicCamera gameCam;
	private Viewport gamePort;
	private Hud hud;
	
	private TiledMap map;
	private OrthogonalTiledMapRenderer mapRenderer;
	private World world;
	private Box2DDebugRenderer b2dr;
	
	private MovementImpl player;
	/**
	 * Scala i pixel del gioco.
	 */
	
	public static final float GAMEPPM = (float) 0.4;
	/**
	 * Scala del gioco.
	 */
	public static final float SCALE = 1;
	/**
	 * Gravità impostata nel gioco.
	 */
	public static final int GRAVITY = 40;
	/**
	 * Asse X.
	 */
	public static final int X_AXIS = 0;
	/**
	 *Asse Y.
	 */
	public static final int Y_AXIS = 0;
	/**
	 * Frequenza, quantità di tempo.
	 */
	public static final float FREQUENCY = (float) 1 / 60;
	/**
	 * Set di  Velocità e posizione delle iterazioni all'interno del mondo.
	 */
	public static final int VEL_ITERATION = 1;
	/**
	 * Posizione.
	 */
	public static final int POS_ITERATION = 1;
	
	
	public PlayScreen(final Bounce game) {
		this.game = game;
		this.manager = Manager.managerInstance;

		if (this.manager.getDisableAudio()) {
			Manager.music.play();
		//NON WORKA
		} 

	}

@Override
public final void show() {
	gameCam = new OrthographicCamera();
    gamePort = new FitViewport(Bounce.V_WIDTH * GAMEPPM, Bounce.V_HEIGHT * GAMEPPM, gameCam);
    hud = new Hud(game.batch);


    map = new TmxMapLoader().load("levels/" + "LVL" + this.manager.getLevel() + ".tmx");
    mapRenderer = new OrthogonalTiledMapRenderer(map, SCALE);
    gameCam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);
    this.world = new World((new Vector2(0, -GRAVITY)), true);
    b2dr = new Box2DDebugRenderer();
    player = new MovementImpl(new BallImpl(this, X_AXIS, Y_AXIS, this.game), new BallViewImpl());
    this.world.setContactListener(new CollisionImpl());

    ObstaclesCreatorImpl oci = new ObstaclesCreatorImpl(this, this.game);

    oci.createObstacles();

}

	

	public final void update(final float delta) {
		this.world.step(FREQUENCY, VEL_ITERATION, POS_ITERATION);

		hud.update(delta);
		player.update(delta);
		gameCam.position.x = player.getBody().getPosition().x;
		if (player.getBody().getPosition().y > gamePort.getWorldHeight() / 2) {
			gameCam.position.y = player.getBody().getPosition().y;
		}
		gameCam.update();
		mapRenderer.setView(gameCam);

	}

	@Override
	public final void dispose() {
		mapRenderer.dispose();
        map.dispose();
        hud.dispose();
        b2dr.dispose();
	}
	
	@Override
	public final void render(final float delta) {
		update(delta);

		Gdx.gl.glClearColor(0, 0, 0, 1); //(red/green/blue/alpha)
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		mapRenderer.render();
		//b2dr.render(world, gameCam.combined); 	Renderizza Le Box 2d della tiled Map, da attivare in caso di prove future per hitbox 
		game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
		game.batch.setProjectionMatrix(gameCam.combined);
		game.batch.begin(); 		//creiamo la finestra
		player.draw(game.batch);	//utilizziamo la finestra per la texture
		game.batch.end();			//chiudiamo la finestra
		hud.stage.draw();
		}
	
	@Override
	public final void resize(final int width, final int height) {
        gamePort.update(width, height);
    }
	
	public final  TextureAtlas getAtlas() {
		return atlas;
	}
	
	public final World getWorld() {
		return world;

	}
	
	public final  TiledMap getMap() {
		return map;
	}

	public final
	OrthographicCamera getCamera() {
        return this.gameCam;
    }
	
	public final MovementImpl getPlayer() {
        return player;
    }
	@Override
	public void resume() {

	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
	}


	public final  Hud getHud() {
		return hud;
	}


}
