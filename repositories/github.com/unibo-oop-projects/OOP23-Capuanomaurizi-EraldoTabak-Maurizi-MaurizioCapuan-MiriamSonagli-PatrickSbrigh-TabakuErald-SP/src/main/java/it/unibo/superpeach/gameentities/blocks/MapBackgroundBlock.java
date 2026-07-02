package it.unibo.superpeach.gameentities.blocks;

import java.awt.Graphics;

/**
 * Blocks class that inherits from Block class and represents a background block
 * which cannot be interacted with other entities.
 * 
 * @author  Maurizio Capuano
 */
public final class MapBackgroundBlock extends Block {

    /**
     * @param x
     * @param y
     * @param width
     * @param height
     * @param scale
     * @param type block type
     */
    public MapBackgroundBlock(final int x, final int y, final int width, final int height, final int scale,
            final BlockType type) {
        super(x, y, width, height, scale);
        setType(type);
        switch (type) {
            case CLOUD_TOP_LEFT:
            case CLOUD_TOP_MIDDLE:
            case CLOUD_TOP_RIGHT:
            case CLOUD_BOT_LEFT:
            case CLOUD_BOT_MIDDLE:
            case CLOUD_BOT_RIGHT:
                setSprites(getTexturer().getCloud());
                break;
            case BUSH_LEFT:
            case BUSH_MIDDLE:
            case BUSH_RIGHT:
                setSprites(getTexturer().getBush());
                break;
            case HILL_UP:
            case HILL_BLANK:
            case HILL_SPOTS1:
            case HILL_SPOTS2:
            case HILL_TOP:
            case HILL_DOWN:
                setSprites(getTexturer().getHill());
                break;
            case FLAG_LEFT:
            case FLAG_RIGHT:
                setSprites(getTexturer().getFlag());
                break;
            default:
                setSprites(getTexturer().getTerrain());
                break;
        }
    }

    @Override
    public void render(final Graphics g) {
        final int castleWindowLeftSprite = 11;
        final int castleWindowRightSprite = 13;
        final int fifthDownSprite = 5;
        switch (getType()) {
            case CLOUD_TOP_LEFT:
                g.drawImage(getSprites()[0], getX(), getY(), getWidth(), getHeight(), null);
                break;
            case CLOUD_TOP_MIDDLE:
                g.drawImage(getSprites()[1], getX(), getY(), getWidth(), getHeight(), null);
                break;
            case CLOUD_TOP_RIGHT:
                g.drawImage(getSprites()[2], getX(), getY(), getWidth(), getHeight(), null);
                break;
            case CLOUD_BOT_LEFT:
                g.drawImage(getSprites()[3], getX(), getY(), getWidth(), getHeight(), null);
                break;
            case CLOUD_BOT_MIDDLE:
                g.drawImage(getSprites()[4], getX(), getY(), getWidth(), getHeight(), null);
                break;
            case CLOUD_BOT_RIGHT:
                g.drawImage(getSprites()[fifthDownSprite], getX(), getY(), getWidth(), getHeight(), null);
                break;
            case BUSH_LEFT:
                g.drawImage(getSprites()[0], getX(), getY(), getWidth(), getHeight(), null);
                break;
            case BUSH_MIDDLE:
                g.drawImage(getSprites()[1], getX(), getY(), getWidth(), getHeight(), null);
                break;
            case BUSH_RIGHT:
                g.drawImage(getSprites()[2], getX(), getY(), getWidth(), getHeight(), null);
                break;
            case HILL_UP:
                g.drawImage(getSprites()[1], getX(), getY(), getWidth(), getHeight(), null);
                break;
            case HILL_BLANK:
                g.drawImage(getSprites()[3], getX(), getY(), getWidth(), getHeight(), null);
                break;
            case HILL_SPOTS1:
                g.drawImage(getSprites()[2], getX(), getY(), getWidth(), getHeight(), null);
                break;
            case HILL_SPOTS2:
                g.drawImage(getSprites()[4], getX(), getY(), getWidth(), getHeight(), null);
                break;
            case HILL_TOP:
                g.drawImage(getSprites()[0], getX(), getY(), getWidth(), getHeight(), null);
                break;
            case HILL_DOWN:
                g.drawImage(getSprites()[fifthDownSprite], getX(), getY(), getWidth(), getHeight(), null);
                break;
            case FLAG_LEFT:
                g.drawImage(getSprites()[2], getX(), getY(), getWidth(), getHeight(), null);
                break;
            case FLAG_RIGHT:
                g.drawImage(getSprites()[3], getX(), getY(), getWidth(), getHeight(), null);
                break;
            case CASTLE_BRICK:
                g.drawImage(getSprites()[2], getX(), getY(), getWidth(), getHeight(), null);
                break;
            case CASTLE_BALCONY1:
                g.drawImage(getSprites()[4], getX(), getY(), getWidth(), getHeight(), null);
                break;
            case CASTLE_BALCONY2:
                g.drawImage(getSprites()[fifthDownSprite], getX(), getY(), getWidth(), getHeight(), null);
                break;
            case CASTLE_WINDOW_LEFT:
                g.drawImage(getSprites()[castleWindowLeftSprite], getX(), getY(), getWidth(), getHeight(), null);
                break;
            case CASTLE_WINDOW_RIGHT:
                g.drawImage(getSprites()[castleWindowRightSprite], getX(), getY(), getWidth(), getHeight(), null);
                break;
            default:
                break;
        }
    }

}
