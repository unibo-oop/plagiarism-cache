package aoc.utilities;

import javafx.scene.image.Image;

import java.io.FileInputStream;

import aoc.view.GamePaths;

public enum Images { 
    
    MOTHER(GamePaths.IMAGES_MOTHER_FOLDER + "/motherDraft.gif"),
    DUMB_CHILD(GamePaths.IMAGES_CHILD_FOLDER + "/childDraft.gif"),
    FAT_CHILD(GamePaths.IMAGES_CHILD_FOLDER + "/childDraft.gif"),
    NERD_CHILD(GamePaths.IMAGES_CHILD_FOLDER + "/childDraft.gif"),
    ATHLETIC_CHILD(GamePaths.IMAGES_CHILD_FOLDER + "/childDraft.gif"),
    RICH_CHILD(GamePaths.IMAGES_CHILD_FOLDER + "/childDraft.gif"),
    SLIPPER(GamePaths.IMAGES_SLIPPER_FOLDER + "/slipperDraft.gif");
    
    private String path;
    private Image image;
    
    private Images(final String path) {
	this.path = path;
        try {
        this.image = new Image(new FileInputStream(path));
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
    
    public Image getImage() {
	return this.image;
    }
    
    public String getPath() {
	return this.path;
    }
}
