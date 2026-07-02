package model.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import java.util.LinkedList;
/**
 *  Used to create a LinkedList of the images used by the fighters and the map GUI
 */
public class Assets {

    private int folderLength;
    private final LinkedList<Sprite> images;
    private final String folder;

    /**
     * Loads all the images given in the folder
     * @param folder
     *          The folder from which the images are extracted
     */
    public Assets(final String folder) {
        this.folder = folder;
        this.images = this.loadImages();
    }

    private LinkedList<Sprite> loadImages() {
    	this.folderLength = computeFolderLenght();

        
        LinkedList<Sprite> temp = new LinkedList<>();

        for (int i = 0; i < this.folderLength; i++) {
            temp.add(new Sprite(new Texture(this.folder + "/" + i + ".jpg")));
        }
        return temp;
    }
    private int computeFolderLenght() {
    	int counter = 0;
        while(Gdx.files.internal(this.folder + "/"+ counter +".jpg").exists()) {
        	counter++;
        }
        return counter;
	}

	/**
     * @param index
     * @return the Sprite at the specified index
     */
    public Sprite getSprite(final int index) {
        return this.images.get(index);
    }
    /**
     * @return the length of the LinkedList created
     */
    public int getFolderLenght() {
        return this.folderLength;
    }
}
