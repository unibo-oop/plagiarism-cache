package thatlevelagain.view.various_jbutton;


import thatlevelagain.view.state.various_state.LevelStateGeneral;

/**
 * 
 * view Zone button in menu.
 *
 */
public class ViewZone extends ButtonGeneral {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private static final String NAME = "VIEW DOG ZONE";
    /**
     * @param level
     *         level 
     */
    public ViewZone(final LevelStateGeneral level) {
        super(NAME);
        this.addActionListener(e -> {
            level.getMap().setViewDogZone(true);
        });
    }
}
