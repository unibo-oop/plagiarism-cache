package controller;

import controller.jsoninput.LocationJSON;
import controller.jsoninput.ResolutionsJSON;
import controller.jsoninput.TankShapeJSON;
import view.View;

/**
 *
 * @author Oleg, Nicola Tamburini
 *
 */
public interface Controller {

    /**
     *
     * @param status
     *            game status
     */
    void update(GameStatus status);

    /**
     *
     * @return location JSON
     */
    LocationJSON getLocationsJSON();

    /**
     *
     * @return resolution JSON
     */
    ResolutionsJSON getResolutinsJSON();

    /**
     *
     * @return tank shape JSON
     */
    TankShapeJSON getTankShapeJSON();

    /**
     *
     * @param view
     *            view
     */
    void setView(View view);
}
