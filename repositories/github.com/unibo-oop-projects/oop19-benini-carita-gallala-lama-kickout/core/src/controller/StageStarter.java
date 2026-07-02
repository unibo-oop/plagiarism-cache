package controller;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

import controller.input.InputController;

import com.badlogic.gdx.physics.box2d.World;
import java.awt.Point;
import model.Direction;
import model.Fighter;
import model.Platform;
import model.Trap;
import utility.KeyMapper;
import utility.StageData;
import static utility.Constants.DOP;
import static utility.Constants.WIDTH_FIGHTER;
import static utility.Constants.HEIGHT_FIGHTER;
import view.StageView;
import view.stage.FighterView;
import view.stage.TrapView;
/**
 * Controls the stage instantiating every element needed
 */
public class StageStarter {
    private StageView playMap;
    @SuppressWarnings("unused")
	private PlatformController platformController;
    private Platform platform;
    private Point platformPos;
    private FighterView playersView;
    private Fighter player1;
    private final Point player1Pos;
    @SuppressWarnings("unused")
	private InputController player1Controller;
    private Fighter player2;
    private final Point player2Pos;
    @SuppressWarnings("unused")
	private InputController player2Controller;
    private TrapView trapView;
    @SuppressWarnings("unused")
	private TrapController trapControl;
    private Trap trap;
    private final Point trapPos;

    private World world;
    private OrthographicCamera camera;

    private static final float GRAVITY = (float) -(9.8 * 20);
    private static final float WIDTH_TRAP = 5;
    private static final float HEIGHT_TRAP = 5;
    private static final float WIDTH_PLATFORM = 95;
    private static final float HEIGHT_PLATFORM = 12;

    /**
     * Declares the gravity used, starts the views and the controllers of Platform, Trap and Fighters and calls CollisionHandler
     * @param game
     *          The game to start
     * @param info
     *          The data passed through the main menu
     */
    public StageStarter(final Game game, final StageData info) {
        Gdx.graphics.setResizable(true);
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, Gdx.graphics.getWidth() / DOP, Gdx.graphics.getHeight() / DOP);

        this.world = new World(new Vector2(0, GRAVITY), true);
        this.trapView = new TrapView();
        this.playersView = new FighterView();
        this.playMap = new StageView(this.world, this.camera, this.playersView, this.trapView, info.getImgMap(), game);

        this.platformPos = new Point((int) (camera.viewportWidth / 2), (int) ((camera.viewportHeight / 2) - 25));
        this.platform = new Platform(this.platformPos, WIDTH_PLATFORM, HEIGHT_PLATFORM, BodyType.StaticBody, world);
        this.platformController = new PlatformController(this.platform, this.playMap);

        this.trapPos = new Point(this.platformPos.x, this.platformPos.y + 50);
        this.trap = new Trap(this.trapPos, WIDTH_TRAP, HEIGHT_TRAP, BodyType.StaticBody, this.world);
        this.trapControl = new TrapController(this.trap, this.trapView);

        this.player1Pos = new Point(this.platformPos.x, this.platformPos.y + 20);
        this.player1 = new Fighter(this.player1Pos, WIDTH_FIGHTER, HEIGHT_FIGHTER, BodyType.DynamicBody,
                             world, Direction.RIGHT, "Player" + info.getIndexPlayer1(), info.getPlayer1());
        this.player1Controller = new InputController(this.player1, this.playersView, new KeyMapper("keyPlayer1"));

        this.player2Pos = new Point(this.platformPos.x + 20, this.platformPos.y + 20);
        this.player2 = new Fighter(this.player2Pos, WIDTH_FIGHTER, HEIGHT_FIGHTER, BodyType.DynamicBody,
                              world, Direction.LEFT, "Player" + info.getIndexPlayer2(), info.getPlayer2());
        this.player2Controller = new InputController(player2, this.playersView, new KeyMapper("keyPlayer2"));

        this.world.setContactListener(new CollisionHandler());
        this.world.setContactFilter(new PlayerCollisionFilter());

        game.setScreen(this.playMap);
    }
}
