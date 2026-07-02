package model.attribute;

/**
 * Collects all the supported features.
 */
public interface Feature {

    /***/
    enum HairStyle {
        STRAIGHT, WAVY, CURLY, BRAIDED;
    }
    /***/
    enum Length {
        SHAVED, SHORT, LONG;
    }
    /***/
    enum Color {
        BLACK, WHITE, BROWN, BLONDE, GREEN, GINGER, GREY;
    }
    /***/
    enum Gender {
        MALE, FEMALE;
    }
    /***/
    enum EyeColor {
        BROWN, BLUE;
    }
    /***/
    enum Dimension {
        BIG, SMALL;
    }
    /***/
    enum Complexion {
        LIGHT, DARK;
    }

}
