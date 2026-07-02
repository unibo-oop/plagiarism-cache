package it.unibo.oop.supermario.enemies;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

import it.unibo.oop.supermario.gamemanager.GameManager;
import it.unibo.oop.supermario.screens.PlayScreen;

/**
 * This class implements the Enemies Controller interface.
 */
public class EnemiesControllerImpl implements EnemiesController {
    private final Array<KoopaImpl> koopas;
    private final Array<GoombaImpl> goombas;
    private final Array<KoopaView> koopasView;
    private final Array<GoombaView> goombasView;

    /** Get the specific number of blocks layer of the map. */
    public static final int GOOMBA_BLOCKS = 6,
            KOOPA_BLOCKS = 7;

    /**
     * Constructor of Enemies controller.
     * 
     * @param screen the play screen
     * @param map the map of all characters
     * 
     */
    public EnemiesControllerImpl(final PlayScreen screen, final TiledMap map) {
        goombas = new Array<>();
        goombasView = new Array<>();
        koopas = new Array<>();
        koopasView = new Array<>();
        for (final MapObject object : map.getLayers().get(GOOMBA_BLOCKS).getObjects().getByType(RectangleMapObject.class)) {
            final Rectangle rect = ((RectangleMapObject) object).getRectangle();
            goombas.add(new GoombaImpl(screen, rect.getX() / GameManager.PPM, rect.getY() / GameManager.PPM));
            goombasView.add(new GoombaView(goombas.peek()));
        }
        for (final MapObject object : map.getLayers().get(KOOPA_BLOCKS).getObjects().getByType(RectangleMapObject.class)) {
            final Rectangle rect = ((RectangleMapObject) object).getRectangle();
            koopas.add(new KoopaImpl(screen, rect.getX() / GameManager.PPM, rect.getY() / GameManager.PPM));
            koopasView.add(new KoopaView(koopas.peek()));
        }
    }

    @Override
    public final void update(final float dt) {
        for (int i = 0; i < goombas.size; i++) {
            goombasView.get(i).update(dt);
            goombas.get(i).update(dt);
        }
        for (int i = 0; i < koopas.size; i++) {
            koopasView.get(i).update(dt);
            koopas.get(i).update(dt);
        }
    }

    @Override
    public final void draw(final Batch batch) {
        for (int i = 0; i < goombas.size; i++) {
            goombasView.get(i).draw(batch);
        }
        for (int i = 0; i < koopas.size; i++) {
            koopasView.get(i).draw(batch);
        }
    }
}
