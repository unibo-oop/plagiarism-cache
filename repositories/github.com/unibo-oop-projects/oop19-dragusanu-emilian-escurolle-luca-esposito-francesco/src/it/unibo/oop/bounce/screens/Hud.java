package it.unibo.oop.bounce.screens;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import it.unibo.oop.bounce.game.Bounce;

public class Hud {
	
	/**
	 * Stage.
	* */
	public Stage stage;
	private Viewport viewport;
	
	private final Image lifeImage;
	private final Image ringImage;
	
	private Label lifeImageLabel;
	private Label ringImageLabel;
	private Label lifeLabel;
	private Label ringLabel;
	/**
	 * Number of rings in game.
	* */
	private int ringCounter = 6;
	private int lifeCounter = 3;
	
	private static final int  IMAGE_LIFE_PAD_RIGHT = 1100;
	private static final int  LIFE_PAD_RIGHT = 1010;
	private static final int  IMAGE_RING_PAD_RIGHT = 300;
	private static final int  RING_PAD_RIGHT = 215;
	private static final int  LABEL_LIFE_PAD_RIGHT = 950;
	private static final int  LABEL_RING_PAD_RIGHT = 160;
	private static final int PAD_10 = 10;
	private static final int PAD_30 = 30;
	private static final int PAD_40 = 40;
	private static final int SIZE_X = 18;
	private static final int SIZE_Y = 32;
	
	
	
	public Hud(final SpriteBatch sb) {
		lifeImage = new Image();
		lifeImage.setDrawable(new TextureRegionDrawable(new TextureRegion(new Texture("sprites/Lifes_Ball1.png"))));

		ringImage = new Image();
		ringImage.setDrawable(new TextureRegionDrawable(new TextureRegion(new Texture("sprites/Bar_Ring.png"))));

		viewport = new FitViewport(Bounce.V_WIDTH, Bounce.V_HEIGHT, new OrthographicCamera());
		stage = new Stage(viewport, sb);
		Table table = new Table();
		table.top();
		table.setFillParent(true);
		lifeImageLabel = new Label(" LIVES : ", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		ringImageLabel = new Label(" RINGS : ", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		lifeLabel = new Label(String.format("X %d", lifeCounter), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		ringLabel = new Label(String.format("X %d", ringCounter), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		table.add(lifeImageLabel).expandX().padTop(PAD_10).padRight(IMAGE_LIFE_PAD_RIGHT);
		table.row();
		table.add(lifeImage).expandX().padTop(-PAD_30).padRight(LIFE_PAD_RIGHT);
		table.add(ringImageLabel).expandX().padTop(PAD_10);
		table.row();
		table.add(ringImageLabel).expandX().padTop(-PAD_40).padRight(IMAGE_RING_PAD_RIGHT);
		table.row();
		table.add(ringImage).expandX().padTop(-PAD_40).padRight(RING_PAD_RIGHT).prefSize(SIZE_X, SIZE_Y);
		table.row();
		table.add(lifeLabel).expandX().padTop(-PAD_40).padRight(LABEL_LIFE_PAD_RIGHT);
		table.row();
		table.add(ringLabel).expandX().padTop(-PAD_40).padRight(LABEL_RING_PAD_RIGHT);
		stage.addActor(table);
	}

	public final void dispose() {
		stage.dispose();

	}
	
	public final void lessLives() {
		this.lifeCounter--;	
	}

	public final int getLifeCounter() {
		return lifeCounter;
	}
	
	public final void lessRings() {
		ringCounter--;
		ringLabel  = new Label(String.format("X %d", ringCounter), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		}
	
	public final int getRingCounter() {
		return ringCounter;
	}

	public void update(final float delta) {
	}
}
