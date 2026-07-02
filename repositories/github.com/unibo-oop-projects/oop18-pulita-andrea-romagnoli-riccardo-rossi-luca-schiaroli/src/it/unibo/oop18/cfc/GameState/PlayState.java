// The main playing GameState.
// Contains everything you need for gameplay:
// Player, TileMap, Diamonds, etc.
// Updates and draws all game objects.

package it.unibo.oop18.cfc.GameState;

import java.awt.Graphics2D;

import it.unibo.oop18.cfc.HUD.DownHud;
import it.unibo.oop18.cfc.HUD.TopHud;
import it.unibo.oop18.cfc.Manager.GameStateManager;
import it.unibo.oop18.cfc.Objects.Entity.Player;
import it.unibo.oop18.cfc.Objects.Entity.PlayerImpl;
import it.unibo.oop18.cfc.TileMap.Tile;
import it.unibo.oop18.cfc.TileMap.TileMap;
import it.unibo.oop18.cfc.Util.JukeBox;
import it.unibo.oop18.cfc.Util.Position;

public class PlayState extends GameState {

    // events
    private boolean blockInput;
    private boolean eventStart;
    private boolean eventFinish;
    private int eventTick;

    // time
    private long ticks;
    // tilemap
    private TileMap tileMap;
    // hud
    private TopHud tophud;
    private DownHud downhud;
    //player
    private Player player;

    public PlayState(GameStateManager gsm) {
        super(gsm, GameStates.PLAY);
        loadMap();
        player = new PlayerImpl(new Position(7*Tile.SPRITE_SIZE, 5*Tile.SPRITE_SIZE), tileMap);
    }



    public void init() {
        // reset timer
        ticks = 0;

        player.setTotalPoints(1000);

        // load hud
        tophud = new TopHud(this);
        downhud = new DownHud(this, player.numPoints());

        // load music
        // JukeBox.load("/Music/bgmusic.mp3", "music1");
        // JukeBox.setVolume("music1", -10);
        // JukeBox.loop("music1", 1000, 1000, JukeBox.getFrames("music1") - 1000);
        // JukeBox.load("/Music/finish.mp3", "finish");
        // JukeBox.setVolume("finish", -10);

        // load sfx
        JukeBox.load("/SFX/collect.wav", "collect");
        JukeBox.load("/SFX/mapmove.wav", "mapmove");
        JukeBox.load("/SFX/tilechange.wav", "tilechange");
        JukeBox.load("/SFX/splash.wav", "splash");

        // start event
        // eventStart = true;
        // eventStart();
    }

    public void update() {

        // end game
        if (0 == 2) {
            eventFinish = true;
            blockInput = true;
        }
        ticks++;
        player.update();
    }

    // Used to update time.
    public long getTicks() {
        return ticks;
    }

    public Player getPlayer() {
        return player;
    }

    private void loadMap() {
        tileMap = new TileMap(64);
        tileMap.loadTiles("/Tilesets/tilesheet.png");
        tileMap.loadMap("/Maps/testmap1.map");
    }

    public void draw(Graphics2D g) {

        // draw tilemap
        tileMap.draw(g);

        // draw player
        player.draw(g);

        // draw hud
        tophud.draw(g);
        downhud.draw(g);
    }
}
