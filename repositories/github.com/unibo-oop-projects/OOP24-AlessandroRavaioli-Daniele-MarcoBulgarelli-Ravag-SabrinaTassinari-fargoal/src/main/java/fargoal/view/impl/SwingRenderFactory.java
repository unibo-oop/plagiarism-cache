package fargoal.view.impl;

import java.awt.Color;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import fargoal.commons.api.Position;
import fargoal.model.commons.FloorElement;
import fargoal.model.interactable.pickupable.inside_chest.impl.ChestImpl;
import fargoal.model.interactable.pickupable.on_ground.SackOfMoney;
import fargoal.model.interactable.pickupable.on_ground.SwordOfFargoal;
import fargoal.view.api.ImageHolder;
import fargoal.view.api.RenderFactory;
import fargoal.view.api.Renderer;
import fargoal.view.api.View;

/**
 * Factory to generate renderers for every type of {@link FloorElement}.
 */
public class SwingRenderFactory implements RenderFactory {

    private final SwingView view;

    /**
     * Constructor to assigns to the local field {@link #view}.
     * 
     * @param view - the general view then casted to {@link SwingView}
     */
    @SuppressFBWarnings(
        value = {"EI_EXPOSE_REP2"},
        justification = "The class needs to work on the same view as the one given"
            + "so if the one given changes the reference also needs to change"
    )
    public SwingRenderFactory(final View view) {
        this.view = (SwingView) view;
    }

    /** {@inheritDoc} */
    @Override
    public Renderer wallRenderer(final Position pos) {
        return new SwingRendererMiddle(g2d -> {
                    g2d.setColor(Color.BLACK);
                    g2d.drawImage(ImageHolder.wall(),
                    this.view.getMargin() + pos.x() * this.view.getTilePixelWidth(),
                    pos.y() * this.view.getTilePixelHeight(),
                    this.view.getTilePixelWidth(),
                    this.view.getTilePixelHeight(),
                    null);
                }, this.view);
    }

    /** {@inheritDoc} */
    @Override
    public Renderer tileRenderer(final Position pos) {
        return new SwingRendererMiddle(g2d -> {
                g2d.setColor(Color.WHITE);
                g2d.drawImage(ImageHolder.tile(),
                    this.view.getMargin() + pos.x() * this.view.getTilePixelWidth(),
                    pos.y() * this.view.getTilePixelHeight(),
                    this.view.getTilePixelWidth(),
                    this.view.getTilePixelHeight(),
                    null);
                }, this.view);
    }

    /** {@inheritDoc} */
    @Override
    public Renderer playerRenderer(final FloorElement obj) {
        return new SwingRendererMiddle(g2d -> {
            g2d.setColor(Color.BLUE);
            g2d.drawImage(ImageHolder.player(),
                    this.view.getMargin() + obj.getPosition().x() * this.view.getTilePixelWidth(),
                    obj.getPosition().y() * this.view.getTilePixelHeight(),
                    this.view.getTilePixelWidth(),
                    this.view.getTilePixelHeight(),
                    null);
        }, this.view);
    }

    /** {@inheritDoc} */
    @Override
    public Renderer upstairRenderer(final FloorElement obj) {
        return new SwingRendererMiddle(g2d -> {
            g2d.setColor(Color.LIGHT_GRAY);
            g2d.drawImage(ImageHolder.upStair(),
                    this.view.getMargin() + obj.getPosition().x() * this.view.getTilePixelWidth(),
                    obj.getPosition().y() * this.view.getTilePixelHeight(),
                    this.view.getTilePixelWidth(),
                    this.view.getTilePixelHeight(),
                    null);
        }, this.view);
    }

    /** {@inheritDoc} */
    @Override
    public Renderer downstairRenderer(final FloorElement obj) {
        return new SwingRendererMiddle(g2d -> {
            g2d.setColor(Color.DARK_GRAY);
            g2d.drawImage(ImageHolder.downstairs(), 
                this.view.getMargin() + obj.getPosition().x() * this.view.getTilePixelWidth(),
                obj.getPosition().y() * this.view.getTilePixelHeight(),
                this.view.getTilePixelWidth(),
                this.view.getTilePixelHeight(), 
                null);
        }, this.view);
    }

    /** {@inheritDoc} */
    @Override
    public Renderer templeRenderer(final FloorElement obj) {
        return new SwingRendererMiddle(g2d -> {
            g2d.setColor(Color.MAGENTA);
            g2d.drawImage(ImageHolder.temple(),
                    this.view.getMargin() + obj.getPosition().x() * this.view.getTilePixelWidth(),
                    obj.getPosition().y() * this.view.getTilePixelHeight(),
                    this.view.getTilePixelWidth(),
                    this.view.getTilePixelHeight(),
                    null);
        }, this.view);
    }

    /** {@inheritDoc} */
    @Override
    public Renderer chestRenderer(final ChestImpl obj) {
        return new SwingRendererMiddle(g2d -> {
            if (obj.exists()) {
                g2d.drawImage(ImageHolder.chest(), 
                    this.view.getMargin() + obj.getPosition().x() * this.view.getTilePixelWidth(),
                    obj.getPosition().y() * this.view.getTilePixelHeight(),
                    this.view.getTilePixelWidth(),
                    this.view.getTilePixelHeight(), 
                    null);
            }
        }, this.view);
    }

    /** {@inheritDoc} */
    @Override
    public Renderer goldRenderer(final SackOfMoney obj) {
        return new SwingRendererMiddle(g2d -> {
                if (obj.isHiddenInGround()) {
                    g2d.drawImage(ImageHolder.hiddenGold(), 
                    this.view.getMargin() + obj.getPosition().x() * this.view.getTilePixelWidth(),
                    obj.getPosition().y() * this.view.getTilePixelHeight(),
                    this.view.getTilePixelWidth(),
                    this.view.getTilePixelHeight(), 
                    null);
                } else {
                g2d.drawImage(ImageHolder.sackOfGold(), 
                    this.view.getMargin() + obj.getPosition().x() * this.view.getTilePixelWidth(),
                    obj.getPosition().y() * this.view.getTilePixelHeight(),
                    this.view.getTilePixelWidth(),
                    this.view.getTilePixelHeight(), 
                    null);
                }
        }, this.view);
    }

    /** {@inheritDoc} */
    @Override
    public Renderer beaconRenderer(final FloorElement obj) {
        return new SwingRendererMiddle(g2d -> {
            g2d.setColor(Color.CYAN);
            g2d.drawImage(ImageHolder.beaconPlaced(), 
                    this.view.getMargin() + obj.getPosition().x() * this.view.getTilePixelWidth(), 
                    obj.getPosition().y() * this.view.getTilePixelHeight(),
                    this.view.getTilePixelWidth(),
                    this.view.getTilePixelHeight(),
                    null);
        }, this.view);
    }

    /** {@inheritDoc} */
    @Override
    public Renderer assassinRenderer(final FloorElement obj) {
        return new SwingRendererMiddle(g2d -> {
            g2d.setColor(Color.DARK_GRAY);
            g2d.drawImage(ImageHolder.assassin(),
                    this.view.getMargin() + obj.getPosition().x() * this.view.getTilePixelWidth(), 
                    obj.getPosition().y() * this.view.getTilePixelHeight(), 
                    this.view.getTilePixelWidth(),
                    this.view.getTilePixelHeight(),
                    null);
        }, this.view);
    }

    /** {@inheritDoc} */
    @Override
    public Renderer barbarianRenderer(final FloorElement obj) {
        return new SwingRendererMiddle(g2d -> {
            g2d.setColor(Color.RED);
            g2d.drawImage(ImageHolder.barbarian(),
                    this.view.getMargin() + obj.getPosition().x() * this.view.getTilePixelWidth(), 
                    obj.getPosition().y() * this.view.getTilePixelHeight(), 
                    this.view.getTilePixelWidth(),
                    this.view.getTilePixelHeight(),
                    null);
        }, this.view);
    }

    /** {@inheritDoc} */
    @Override
    public Renderer mageRenderer(final FloorElement obj) {
        return new SwingRendererMiddle(g2d -> {
            g2d.setColor(Color.GREEN);
            g2d.drawImage(ImageHolder.mage(),
                    this.view.getMargin() + obj.getPosition().x() * this.view.getTilePixelWidth(), 
                    obj.getPosition().y() * this.view.getTilePixelHeight(), 
                    this.view.getTilePixelWidth(),
                    this.view.getTilePixelHeight(),
                    null);
        }, this.view);
    }

    /** {@inheritDoc} */
    @Override
    public Renderer monkRenderer(final FloorElement obj) {
        return new SwingRendererMiddle(g2d -> {
            g2d.setColor(Color.CYAN);
            g2d.drawImage(ImageHolder.monk(),
                    this.view.getMargin() + obj.getPosition().x() * this.view.getTilePixelWidth(), 
                    obj.getPosition().y() * this.view.getTilePixelHeight(), 
                    this.view.getTilePixelWidth(),
                    this.view.getTilePixelHeight(),
                    null);
        }, this.view);
    }

    /** {@inheritDoc} */
    @Override
    public Renderer rogueRenderer(final FloorElement obj) {
        return new SwingRendererMiddle(g2d -> {
            g2d.setColor(Color.ORANGE);
            g2d.drawImage(ImageHolder.rogue(),
                    this.view.getMargin() + obj.getPosition().x() * this.view.getTilePixelWidth(), 
                    obj.getPosition().y() * this.view.getTilePixelHeight(), 
                    this.view.getTilePixelWidth(),
                    this.view.getTilePixelHeight(),
                    null);
        }, this.view);
    }

    /** {@inheritDoc} */
    @Override
    public Renderer spiderRenderer(final FloorElement obj) {
        return new SwingRendererMiddle(g2d -> {
            g2d.setColor(Color.BLACK);
            g2d.drawImage(ImageHolder.spider(),
                    this.view.getMargin() + obj.getPosition().x() * this.view.getTilePixelWidth(), 
                    obj.getPosition().y() * this.view.getTilePixelHeight(), 
                    this.view.getTilePixelWidth(),
                    this.view.getTilePixelHeight(),
                    null);
        }, this.view);
    }

    /** {@inheritDoc} */
    @Override
    public Renderer warlordRenderer(final FloorElement obj) {
        return new SwingRendererMiddle(g2d -> {
            g2d.setColor(Color.PINK);
            g2d.drawImage(ImageHolder.warlord(),
                    this.view.getMargin() + obj.getPosition().x() * this.view.getTilePixelWidth(), 
                    obj.getPosition().y() * this.view.getTilePixelHeight(), 
                    this.view.getTilePixelWidth(),
                    this.view.getTilePixelHeight(),
                    null);
        }, this.view);
    }

    /** {@inheritDoc} */
    @Override
    public Renderer swordRenderer(final SwordOfFargoal obj) {
        return new SwingRendererMiddle(g2d -> {
            if (obj.exists()) {
                g2d.drawImage(ImageHolder.sword(), 
                    this.view.getMargin() + obj.getPosition().x() * this.view.getTilePixelWidth(),
                    obj.getPosition().y() * this.view.getTilePixelHeight(),
                    this.view.getTilePixelWidth(),
                    this.view.getTilePixelHeight(),
                    null);
            }
        }, this.view);
    }
}
