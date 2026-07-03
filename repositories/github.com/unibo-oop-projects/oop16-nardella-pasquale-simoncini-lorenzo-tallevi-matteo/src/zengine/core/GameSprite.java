package zengine.core;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import zengine.interfaces.GameEngine;

/**
 * This class models an image composed by one or more frames (also called
 * subimages). Frames can be displayed in sequence to give an illusion of
 * animation, or they can be used to store in a single sprite more versions of
 * the same image (for example, the same enemy but with different colors). The
 * subimages wrap a java.awt.Image each.
 */
final class GameSprite {
    private final GameEngine ze = Zengine.getEngine();
    private final ZengineGUI gui = ZengineGUI.getGUI();
    private final List<Image> imageList;
    private int xOffset;
    private int yOffset;
    private int width;
    private int height;

    /**
     * builds a new sprite that contains all the images contained in the given
     * list.
     * 
     * @param subimages
     *            List of images to put inside the sprite
     */
    GameSprite(final List<Image> subimages) {
        this.imageList = new ArrayList<>();
        this.imageList.addAll(subimages);
        if (imageList.isEmpty()) {
            imageList.add(null);
        }
        this.width = 0;
        this.height = 0;
        xOffset = 0;
        yOffset = 0;
        imageList.forEach(i -> {
            if (i != null) {
                width = ze.max(i.getWidth(gui), width);
                height = ze.max(i.getHeight(gui), height);
            }
        });
    }

    /**
     * returns one of the frames of this sprite.
     * 
     * @param x
     *            index of the requested frame
     * @return the requested frame
     */
    public Image getSprite(final double x) {
        return imageList.get(validSubimageValue(x));
    }

    /**
     * returns the width of a single subimage, in pixels.
     * 
     * @param subimage
     *            index of the requested subimage
     * @return width of the subimage, or -1 if something wrong happened.
     */
    public int getSubimageWidth(final double subimage) {
        return imageList.get(validSubimageValue(subimage)).getWidth(gui);
    }

    /**
     * returns the height of a single subimage, in pixels.
     * 
     * @param subimage
     *            index of the requested subimage
     * @return height of the subimage, or -1 if something wrong happened.
     */
    public int getSubimageHeight(final double subimage) {
        return imageList.get(validSubimageValue(subimage)).getHeight(gui);
    }

    /**
     * returns the number of subimages contained in this sprite.
     * 
     * @return the number of subimages contained in this sprite.
     */
    public int getNumber() {
        return imageList.size();
    }

    /**
     * returns the global width of this sprite, in pixels, which is based on all
     * of its subimages.
     * 
     * @return the width of the sprite
     */
    public int getWidth() {
        return width;
    }

    /**
     * returns the global height of this sprite, in pixels, which is based on
     * all of its subimages.
     * 
     * @return the height of the sprite
     */
    public int getHeight() {
        return height;
    }

    /**
     * returns the XOffset of this sprite, which is the x coordinate of its
     * origin point around where the sprite is rotated and/or scaled.
     * 
     * @return the XOffset of this sprite, in pixels
     */
    public int getxOffset() {
        return xOffset;
    }

    /**
     * returns the YOffset of this sprite, which is the x coordinate of its
     * origin point around where the sprite is rotated and/or scaled.
     * 
     * @return the YOffset of this sprite, in pixels
     */
    public int getyOffset() {
        return yOffset;
    }

    /**
     * sets the XOffset of this sprite, which is the x coordinate of its origin
     * point around where the sprite is rotated and/or scaled.
     */
    public void setxOffset(final int xOffset) {
        this.xOffset = xOffset;
    }

    /**
     * sets the YOffset of this sprite, which is the x coordinate of its origin
     * point around where the sprite is rotated and/or scaled.
     */
    public void setyOffset(final int yOffset) {
        this.yOffset = yOffset;
    }

    /**
     * calculates a valid subimage value for this sprite based on the requested
     * subimage value
     * 
     * @param x
     *            requested frame
     * @return index of the actual frame to be displayed
     */
    private int validSubimageValue(final double x) {
        if (imageList == null) {
            return 0;
        } else {
            if (!imageList.isEmpty()) {
                final int n = imageList.size();
                int r = (int) x % n;
                if (r < 0) {
                    r += n;
                }
                return r;
            } else {
                return 0;
            }
        }
    }
}
