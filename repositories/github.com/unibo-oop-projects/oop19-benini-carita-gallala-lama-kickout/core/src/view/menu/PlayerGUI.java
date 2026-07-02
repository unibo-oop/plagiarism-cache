package view.menu;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 * Implements GenericGUI adding the characteristics needed for the Players
 */
public class PlayerGUI implements GenericGUI {

    private Label playerName;
    private Label character;
    private final float distanceCharacter = 10;
    private TextField playerText;
    private Image charactersImage;
    private ImageButton arrowImageSx;
    private ImageButton arrowImageDx;
    private final float arrowSize = 75;

    private Sprite selected;

    private Skin skin;

    private Table table;
    private Table tablePlayers;
    private final float tablePlayersWidth = 150;
    private final float tablePlayersHeigth = 75;

    private int indexSelected;

    /**
     * Instantiates the elements (2x ImageButton TextField etc.) that will create the architecture of the GUI
     */
    public PlayerGUI() {
        this.table = new Table();
        this.tablePlayers = new Table();
        this.skin = new Skin(Gdx.files.internal("Skin/comic-ui.json"));
        this.playerName = new Label("Inserisci nome:", skin);
        this.character = new Label("Scegli personaggio", skin);
        this.playerText = new TextField("", skin);

        this.arrowImageSx = new ImageButton(new TextureRegionDrawable(new Texture("Untitled-1.png")));
        this.arrowImageDx = new ImageButton(new TextureRegionDrawable(new Texture("Untitled-2.png")));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Table GUI() {
        this.charactersImage = new Image(this.selected);
        this.tablePlayers.add(this.charactersImage);
        this.table.add(this.playerName);
        this.table.add();
        this.table.add();
        this.table.add(this.character).padBottom(this.distanceCharacter);
        this.table.row();
        this.table.add(this.playerText);
        this.table.add().expandX();
        this.table.add(this.arrowImageSx).size(this.arrowSize);
        this.table.add(this.tablePlayers).size(this.tablePlayersWidth, this.tablePlayersHeigth);
        this.table.add(this.arrowImageDx).size(this.arrowSize);

        return table;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Table getTable() {
        return this.tablePlayers;
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
    public ImageButton getArrowImageSx() {
        return this.arrowImageSx;
    }

    /**
     * @return the selected Sprite
     */
    public Sprite getSelected() {
        return this.selected;
    }

    /**
     * @return the Player's name
     */
    public String getPlayertext() {
        return this.playerText.getText();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setListener(final ClickListener listener) {
        arrowImageSx.addListener(listener);
        arrowImageDx.addListener(listener);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void setSelected(final Sprite selected) {
        this.selected = selected;
    }
    /**
     * @return the selected image
     */
    public int getIndexselected() {
        return this.indexSelected;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void setIndexSelected(final int indexSelected) {
        this.indexSelected = indexSelected;
    }
}
