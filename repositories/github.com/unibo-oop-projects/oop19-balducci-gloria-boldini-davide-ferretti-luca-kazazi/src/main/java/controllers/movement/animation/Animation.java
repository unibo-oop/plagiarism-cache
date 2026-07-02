package controllers.movement.animation;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Objects;

/**
 * 
 * Implementation of Animation.
 *
 */
public class Animation implements AnimationInterface {

    private final int speed;
    private int index;
    private int count;

    private BufferedImage current;

    /**
     * Constructor for Animation.
     * 
     * @param speed velocity to change image
     */
    public Animation(final int speed) {
        this.speed = Objects.requireNonNull(speed);
    }

    @Override
    public void init(final List<BufferedImage> list) {
        this.current = list.get(0);
    }

    @Override
    public void runAnimation(final List<BufferedImage> list) {
        index++;
        if (index > speed) {
            index = 0;
            nextFrame(list);
        }
    }

    @Override
    public void nextFrame(final List<BufferedImage> list) {
        for (int i = 0; i < list.size(); i++) {
            if (count == i) {
                current = list.get(i);
            }
        }
        count++;
        if (count > list.size()) {
            count = 0;
        }
    }

    @Override
    public void drawAnimation(final Graphics g, final int x, final int y) {
        g.drawImage(current, x, y, null);
    }

}
