package powpaw.player.controller.impl;

import powpaw.player.controller.api.DamageMeterController;
import powpaw.player.controller.api.PlayerController;
import powpaw.player.view.api.DamageMeterRender;
import powpaw.player.view.impl.DamageMeterRenderImpl;

/**
 * DamageMeterController implementation.
 * 
 * @author Simone Collorà
 */
public final class DamageMeterControllerImpl implements DamageMeterController {
    private final DamageMeterRender render;

    /**
     * DamageMeter costructor.
     * 
     * @param controller for every player damage meter.
     */
    public DamageMeterControllerImpl(final PlayerController controller) {
        render = new DamageMeterRenderImpl(controller.getPlayers());
    }

    @Override
    public DamageMeterRender getRender() {
        return this.render;
    }
}
