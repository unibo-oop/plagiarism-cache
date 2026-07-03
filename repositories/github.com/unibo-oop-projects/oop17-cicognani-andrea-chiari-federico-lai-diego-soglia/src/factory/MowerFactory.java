package factory;

import com.almasb.fxgl.entity.*;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.entity.view.EntityView;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.texture.Texture;


import javafx.geometry.Point2D;
import javafx.util.Duration;

import static com.almasb.fxgl.app.DSLKt.texture;

import com.almasb.fxgl.app.FXGL;

import controller.PlayerControl;

/**
 *
 * @author Nicola
 * @author Andrea
 *
 * This class containes the entities that are generated on the game field
 *
 */
public class MowerFactory implements TextEntityFactory {

	/**
	 *
	 * @param data
	 * @return the Entity block
	 */
    @SpawnSymbol('1')
    public Entity newBlock(SpawnData data) {
        EntityView view = new EntityView(texture("wall.png"));

        return Entities.builder().from(data).type(MowerType.BLOCK).viewFromNodeWithBBox(view)
                .renderLayer(RenderLayer.BACKGROUND).build();

    }

    /**
     *
     * @param data
     * @return the Entity grass
     */
    @Spawns("grass")
	@SpawnSymbol(' ')
    public Entity newGrass(SpawnData data) {
        EntityView view = new EntityView(texture("grassimage.png"));

        return Entities.builder().from(data).type(MowerType.GRASS).viewFromNodeWithBBox(view)
                .renderLayer(RenderLayer.BACKGROUND).build();

    }

    /**
     *
     * @param data
     * @return Entity weed
     */
    @Spawns("weed")
    @SpawnSymbol('0')
    public Entity newWeedPatch(SpawnData data) {
        EntityView view = new EntityView(texture("weedimage.png"));

        return Entities.builder().from(data).type(MowerType.WEED).bbox(new HitBox("Main", BoundingShape.box(40, 40)))
                .viewFromNodeWithBBox(view).renderLayer(RenderLayer.BACKGROUND).with(new CollidableComponent(true))
                .build();
    }

    /**
     *
     * @param data
     * @return Entity player
     */
    @SpawnSymbol('P')
    public Entity newPlayer(SpawnData data) {
        // Texture view = texture("mower.png").toAnimatedTexture(2, Duration.seconds(0.33));
        Texture view = texture("mower.png").toAnimatedTexture(3, Duration.seconds(0.33));

        return Entities.builder().from(data).type(MowerType.PLAYER)
                //.bbox(new HitBox("PLAYER_BODY", new Point2D(2, 2), BoundingShape.box(36, 36))).viewFromNode(view)
                .bbox(new HitBox("PLAYER_BODY", new Point2D(2, 2), BoundingShape.box(33, 33))).viewFromNode(view)
                .with(new CollidableComponent(true)).with(new PlayerControl()).build();
    }

    /**
     *
     * @param data
     * @return	Entity flower
     */
    @Spawns("flower")
    @SpawnSymbol('F')
    public Entity newFlowerPatch(SpawnData data) {
        EntityView view = new EntityView(texture("flowersimageV2.png"));
        view.setTranslateX(2.5);

        return Entities.builder().from(data).type(MowerType.FLOWER).bbox(new HitBox("Main", BoundingShape.box(40, 40)))
                .viewFromNodeWithBBox(view).renderLayer(RenderLayer.BACKGROUND).with(new CollidableComponent(true))
                .build();
    }


    /**
     *
     * @param data
     * @return	Entity Rock
     */
    @Spawns("rock")
    @SpawnSymbol('R')
    public Entity newRock(SpawnData data) {
        EntityView view = new EntityView(texture("rocksimageV2.png"));
        view.setTranslateX(2.5);

        return Entities.builder().from(data).type(MowerType.ROCK).bbox(new HitBox("Main", BoundingShape.box(40, 40)))
                .viewFromNodeWithBBox(view).renderLayer(RenderLayer.BACKGROUND).with(new CollidableComponent(true))
                .build();
    }

    /**
     *
     * @param data
     * @return	Entity Fuel
     */
    @Spawns("fuel")
    @SpawnSymbol('T')
    public Entity newTank(SpawnData data) {
        EntityView view = new EntityView(texture("fuelimage.png"));
        view.setTranslateX(2.5);

        FXGL.getAudioPlayer().playSound("spawn.wav");

        return Entities.builder().from(data).type(MowerType.FUEL).bbox(new HitBox("Main", BoundingShape.box(40, 40)))
                .viewFromNodeWithBBox(view).renderLayer(RenderLayer.BACKGROUND).with(new CollidableComponent(true))
                .build();
    }

    /**
     *
     * @param data
     * @return	Entity gravel
     */
    @Spawns("gravel")
    @SpawnSymbol('G')
    public Entity newGravel(SpawnData data) {
        EntityView view = new EntityView(texture("gravel.png"));
        view.setTranslateX(2.5);

        return Entities.builder().from(data).type(MowerType.GRAVEL).bbox(new HitBox("Main", BoundingShape.box(40, 40)))
                .viewFromNodeWithBBox(view).renderLayer(RenderLayer.BACKGROUND).with(new CollidableComponent(true))
                .build();
    }

    /**
     *
     * @param data
     * @return	Entity Sgravel
     */
    @Spawns("sgravel")
    public Entity newSGravel(SpawnData data) {
        EntityView view = new EntityView(texture("sgravel.png"));
        view.setTranslateX(2.5);

        FXGL.getAudioPlayer().playSound("sgravel.wav");

        return Entities.builder().from(data).type(MowerType.SGRAVEL).bbox(new HitBox("Main", BoundingShape.box(40, 40)))
                .viewFromNodeWithBBox(view).renderLayer(RenderLayer.BACKGROUND).with(new CollidableComponent(true))
                .build();
    }

    /**
     *
     * @param data
     * @return	Entity water
     */
    @Spawns("water")
    @SpawnSymbol('W')
    public Entity newWater(SpawnData data) {
        EntityView view = new EntityView(texture("water.png"));
        view.setTranslateX(2.5);

        return Entities.builder().from(data).type(MowerType.WATER).bbox(new HitBox("Main", BoundingShape.box(40, 40)))
                .viewFromNodeWithBBox(view).renderLayer(RenderLayer.BACKGROUND).with(new CollidableComponent(true))
                .build();
    }

    /**
     * @return the standard height of a block
     */
    @Override
    public int blockHeight() {

        return 40;
    }

    /**
     * @return the standard width of a block
     */
    @Override
    public int blockWidth() {

        return 40;
    }

    /**
     * @return a empty char
     */
    @Override
    public char emptyChar() {

        return ' ';
    }

}