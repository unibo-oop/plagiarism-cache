package powpaw.player.controller.api;

import powpaw.player.view.api.DamageMeterRender;

/**
 * DamageMeter controller that create and set DamageMeterRender.
 * 
 * @author Simone Collorà
 */
public interface DamageMeterController {

    /**
     * Get render.
     * 
     * @return render.
     */
    DamageMeterRender getRender();

}
