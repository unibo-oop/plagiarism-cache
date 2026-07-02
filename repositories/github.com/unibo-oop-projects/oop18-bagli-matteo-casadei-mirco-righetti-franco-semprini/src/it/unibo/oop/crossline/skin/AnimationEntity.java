package it.unibo.oop.crossline.skin;

import java.awt.Dimension;
import java.util.Optional;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.utils.Array;

import it.unibo.oop.crossline.game.actor.player.PlayerImpl;

/**
 *
 *class that model an animation,it's uses an .atlas extension file an various .png image
 *to assemble an array of images that follow each other
 *draw an animation on the screen.
 *
 */
public class AnimationEntity {


    //Field to store images.

    private TextureAtlas skinAtlas;
    private TextureRegion currentFrameToDraw;
    private Animation<?> skinAnimation;
    private Array<AtlasRegion> arrayFrames;

    //filed to set propriety

    private float elapsedTime = 0f;
    private final float frameDuration;
    private TextureRegion firstTexture;
    private static final float IMAGE_PIXEL = 16f;
    private final float scale = Gdx.graphics.getDensity() * IMAGE_PIXEL;
    private final Dimension dim;

    /**
     *
     * @param pathAtlas Path of file image.atlas
     * @param nameAnimation name of animation_XXX.png where nameAnimation is animation and XXX is image number frame
     * @param speedAnimation speed with which the images follow each other
     * @param type loop type
     */
    public AnimationEntity(final String pathAtlas, final String nameAnimation, final float speedAnimation, final PlayMode type) {

        super();
        frameDuration = speedAnimation;
        this.createTextureAtlasFromFile(pathAtlas, nameAnimation);
        this.createAnimation(arrayFrames, type);
        firstTexture = arrayFrames.first();
        dim = new Dimension(getWidth(), getHeight());
    }


    /**
     * To call before batch.begin().
     *
     */
    public void swichFrameByTime() {

        //check the limit of memory
        if (elapsedTime > Double.SIZE / 2) {
            elapsedTime = 0f;
        }
        elapsedTime += Gdx.graphics.getDeltaTime();
        createFrameToShow(skinAnimation);
    }

     /**
    *
    *   to call between batch.begin() and batch.end().
    *
    * @param batch place where draw
    * @param pos is vector where draw animation
    */
     void drawOnSpriteBatch(final SpriteBatch batch, final Vector2 pos) {

        Vector2 correctpos = correctPosition(pos);

        if (Optional.of(currentFrameToDraw).isPresent()) {
            batch.draw(currentFrameToDraw, correctpos.x, correctpos.y, scale / (float) dim.getWidth(), scale / (float) dim.getHeight());
        }

    }

    /**
     * Let resource to be free.
     */
     void dispose() {

          skinAtlas.dispose();
      }

      private Vector2 correctPosition(final Vector2 pos) {

      return new Vector2(pos.x - PlayerImpl.getCircleRadius(), pos.y - PlayerImpl.getCircleRadius());

    }




    /**
     *
     * @param path of resources packed with "GDX Texture Packer"
     * @param nameAnimation is the name of each frame
     * @return this because is build pattern
     */
    private AnimationEntity createTextureAtlasFromFile(final String path, final String nameAnimation) {

      this.skinAtlas = new TextureAtlas(Gdx.files.internal(path));
      this.arrayFrames = skinAtlas.findRegions(nameAnimation);
      return this;

    }


    /**
    * Create an frame to show based of elapsed time.
    * @param animation
    *
    */
    private void createFrameToShow(final Animation<?> animation) {

          currentFrameToDraw = (TextureRegion) animation.getKeyFrame(elapsedTime);

  }

    /**
     * Create an animation.
     * @param arrayFrames every image to be loop
     * @param type loop mode
     * @return
     */
    private AnimationEntity createAnimation(final Array<AtlasRegion> arrayFrames, final PlayMode type) {

        skinAnimation = new Animation<>(frameDuration, arrayFrames, type);
        return this;
    }


    private int getHeight() {

        int a = firstTexture.getRegionHeight();
        return a;
    }

    private int getWidth() {

        int a = firstTexture.getRegionWidth();
        return a;
    }


}
