package ala.models;

import ala.models.LevelGeneratorPatternModel.OBJECTSTYPE;

/**
 * BackgroundPatternModel class.
 * 
 */
public class BackgroundPatternModel extends GameObjectModel {
    //Attributes:
    private static final int BACKGROUND_PATTERN_MODEL_X = 0; //This is for spawn the background always in the origin of the screen, to change if we want to choose this.
    private static final int BACKGROUND_PATTERN_MODEL_Y = 0; //This is for spawn the background always in the origin of the screen, to change if we want to choose this.
    private static final int BACKGROUND_PATTERN_MODEL_R = 0; //This could be interesting if we want to simply revert a level.
    private static final OBJECTSTYPE BACKGROUND_PATTERN_MODEL_TYPE = OBJECTSTYPE.PLATFORM;
    private static final int BACKGROUND_PATTERN_MODEL_WIDTH = 0; //This is to avoid that the screen has an hitbox.
    private static final int BACKGROUND_PATTERN_MODEL_HEIGHT = 0; //This is to avoid that the screen has an hitbox.
    private static final int BACKGROUND_PATTERN_MODEL_DAMAGE_ON_CONTACT = 0; //this could be interesting if in the future we want to implement a way to get damage at every objects in the screen.

    //Attributes:
    public BackgroundPatternModel() {
        super(BACKGROUND_PATTERN_MODEL_X, BACKGROUND_PATTERN_MODEL_Y, BACKGROUND_PATTERN_MODEL_R, BACKGROUND_PATTERN_MODEL_TYPE, BACKGROUND_PATTERN_MODEL_WIDTH, BACKGROUND_PATTERN_MODEL_HEIGHT, BACKGROUND_PATTERN_MODEL_DAMAGE_ON_CONTACT);
    }
}
