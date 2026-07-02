package model;

import java.awt.image.BufferedImage;
import java.util.Objects;
import model.gameObject.GameObject;
import model.handler.Handler;
import model.player.Player;

public class Portal extends GameObject implements PortalInterface{

    private String color;
    private final Handler handler;

    /**
     * Constructor for Portal.
     * 
     * @param id
     * @param color
     * @param posX
     * @param posY
     * @param velX
     * @param velY
     * @param image
     * @param handler
     */
    public Portal(final ID id, final String color, final int posX, final int posY, final double velX, final double velY,
            final BufferedImage image, final Handler handler) {
        super(id, posX, posY, velX, velY, image);
        this.color = Objects.requireNonNull(color);
        this.handler = Objects.requireNonNull(handler);
    }

    @Override
    public void tick() {
    }

    @Override
    public String getColor() {
        return this.color;
    }
    
    @Override
    public void effect(final Player player) {
        Portal temp = null;
        this.color = this.color.substring(1);
        final int portalNumber = Integer.parseInt(this.color) + 1;
        temp=this.getNextPortal(portalNumber);
        this.setVisible(false);
        if (temp != null) {
            temp.setVisible(false);
            player.setCoord(temp.getCoord());
        }
    }

    private Portal getNextPortal(int portalNumber) {
        for (final Portal p : handler.getPortalList()) {
            if (Integer.parseInt(p.getColor().substring(1)) == portalNumber) {
                return p;
            }
        }
        return null;
    }
}
