package view.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 * Implements GenericGUI adding the characteristics needed for the background
 */
public class MapGUI implements GenericGUI {
    private Label mapName;
    private Image mapImage;
    private ImageButton arrowImageSx;
    private ImageButton arrowImageDx;
    private final float arrowSize = 75;

    private Skin skin;

    private Table table;
    private Table tableMap;
    private final float tableMapWidth = 150;
    private final float tableMapHeigth = 75;
    private Sprite selected;
	/**
	 * Creates the GUI used to choose the background, it also creates two buttons to switch between images
	 */
    public MapGUI() {
        this.table = new Table();
        this.tableMap = new Table();
        this.skin = new Skin(Gdx.files.internal("Skin/comic-ui.json"));

        this.mapName = new Label("MAPPA", skin, "title");
        this.mapName.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        this.arrowImageSx = new ImageButton(new TextureRegionDrawable(new Texture("Untitled-1.png")));
        this.arrowImageDx = new ImageButton(new TextureRegionDrawable(new Texture("Untitled-2.png")));
    }

    /**
     * @return the background selected by the player
     */
    public Sprite getSelected() {
        this.selected.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        return this.selected;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Table GUI() {
        this.mapImage = new Image(this.selected);
        this.tableMap.add(this.mapImage);
        this.tableMap.add();
        this.table.add(this.mapName);
        this.table.add().expandX();
        this.table.add(this.arrowImageSx).size(this.arrowSize);
        this.table.add(this.tableMap).size(this.tableMapWidth, this.tableMapHeigth);
        this.table.add(this.arrowImageDx).size(this.arrowSize);

        return table;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Table getTable() {
        return this.tableMap;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImageButton getArrowImageSx() {
        return this.arrowImageSx;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImageButton getArrowImageDx() {
        return this.arrowImageDx;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setListener(final ClickListener listener) {
        this.arrowImageSx.addListener(listener);
        this.arrowImageDx.addListener(listener);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void setSelected(final Sprite selected) {
        this.selected = selected;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setIndexSelected(final int indexSelected) {
    }
}
