package utility;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
/**
 * Used to create an animation starting from the assets folder using the images given
 */
public class AnimationGenerator {

    private Animation<TextureRegion> loadingBar;
    private TextureRegion[] loadingBarFrames;
    private int folderLength;
    private final String animationFolder;
    private static final float ELAPSED_TIME = 1 / 10f;
    /**
     * 
     * @param animationFolder
     *                  The folder in which the Sprites are contained
     */
    public AnimationGenerator(final String animationFolder) {
        this.animationFolder = animationFolder;
    	this.folderLength = computeFolderLength();
        this.loadingBarFrames = new TextureRegion[this.folderLength];
        this.loadFramesOfLoadingbars();
        this.loadingBar = new Animation<>(ELAPSED_TIME, loadingBarFrames);
    }

	private int computeFolderLength() {
		int counter = 0;
        while(Gdx.files.internal(this.animationFolder + "/"+ counter +".png").exists()) {
        	counter++;
        }
        return counter;
	}

    private void loadFramesOfLoadingbars() {
        for (int i = 0; i < this.folderLength; i++) {
            this.loadingBarFrames[i] = new TextureRegion(new Texture(this.animationFolder + "/" + i + ".png"));
        }
    }

    /**
     * @return the animation created
     */
    public Animation<TextureRegion> getAnimation() {
        return this.loadingBar;
    }
}
