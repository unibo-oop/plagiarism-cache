package powpaw.map.view.impl;

import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import powpaw.map.controller.api.MapController;
import powpaw.map.controller.impl.MapControllerImpl;
import powpaw.map.model.impl.BlockImpl;
import powpaw.map.view.api.MapRender;

/**
 * Class creates a pane and draws blocks on it using a ImagePattern.
 * 
 * @author Giacomo Grassetti
 */
public final class MapRenderImpl implements MapRender {

    private final List<BlockImpl> terrains;
    private final ImagePattern textureBlock;

    /**
     * Constructor of MapRenderImpl that gets the list of platforms from the
     * mapController object and assigns it to the terrains variable, and creates a
     * new ImagePattern object with the block image.
     */
    public MapRenderImpl() {
        final MapController mapController = new MapControllerImpl();
        this.terrains = mapController.getPlatforms();
        this.textureBlock = new ImagePattern(new Image("/block.png"));
    }

    @Override
    public Pane createPane() {
        final Pane worldPane = new Pane();
        drawBlocks();
        return worldPane;
    }

    @Override
    public List<BlockImpl> getTerrains() {
        return this.terrains;
    }

    /**
     * Setter that fill color of the hitbox shape of each terrain block to a texture
     * block.
     */
    private void drawBlocks() {
        this.terrains.stream().forEach(b -> b.getHitbox().getShape().setFill(this.textureBlock));
    }

}
