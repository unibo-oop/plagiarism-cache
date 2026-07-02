package spacesurvival.view.game.utilities;

import spacesurvival.model.World;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.util.Optional;

/**
 * Implements a panel to graph bullet objects, through a support structure.
 */
public class PanelBulletGame extends JPanel {
    private static final long serialVersionUID = -7694414073000255484L;
    private Optional<World> world;

    /**
     * Initialize and create all graphics components.
     */
    public PanelBulletGame() {
        super();
        super.setOpaque(false);
        this.world = Optional.empty();
    }

    /**
     * Set world for panel.
     * @param world
     */
    public void setWorld(final World world) {
        this.world = Optional.of(world);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void paintComponent(final Graphics g) {
//        super.paintComponent(g);
//        final Graphics2D g2d = (Graphics2D) g;
//
//        this.world.get().getAllBullets().forEach(bullet -> {
//            g2d.setTransform(bullet.getTransform());
//            System.out.println(bullet.getImgBody());
//            g2d.drawImage(bullet.getImgBody(), 0, 0, null);
//        });
    }

}
