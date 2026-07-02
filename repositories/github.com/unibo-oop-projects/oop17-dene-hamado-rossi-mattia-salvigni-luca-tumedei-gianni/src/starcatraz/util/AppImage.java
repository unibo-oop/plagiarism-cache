package starcatraz.util;

import javafx.scene.image.Image;

/**
 * Images used in the project.
 */
public enum AppImage {

    BACKGROUND_GAME("/images/background_game.jpg"),
    BACKGROUND_MENU("/images/background_menu.jpg"),
    CARD_BACK("/images/card_back.png"),
    CARD_CHIP_RED("/images/card_chip_red.png"),
    CARD_CHIP_BLUE("/images/card_chip_blue.png"),
    CARD_CHIP_YELLOW("/images/card_chip_yellow.png"),
    CARD_CHIP_GRAY("/images/card_chip_gray.png"),
    CARD_ROCKET_A_RED("/images/card_rocket_a_red.png"),
    CARD_ROCKET_A_BLUE("/images/card_rocket_a_blue.png"),
    CARD_ROCKET_A_YELLOW("/images/card_rocket_a_yellow.png"),
    CARD_ROCKET_A_GRAY("/images/card_rocket_a_gray.png"),
    CARD_ROCKET_B_RED("/images/card_rocket_b_red.png"),
    CARD_ROCKET_B_BLUE("/images/card_rocket_b_blue.png"),
    CARD_ROCKET_B_YELLOW("/images/card_rocket_b_yellow.png"),
    CARD_ROCKET_B_GRAY("/images/card_rocket_b_gray.png"),
    CARD_ROCKET_RED("/images/card_rocket_full_red.png"),
    CARD_ROCKET_BLUE("/images/card_rocket_full_blue.png"),
    CARD_ROCKET_YELLOW("/images/card_rocket_full_yellow.png"),
    CARD_ROCKET_GRAY("/images/card_rocket_full_gray.png"),
    CARD_ROBOT("/images/card_robot.png"),
    COMPONENT_BUTTON("/images/component_button.png"),
    COMPONENT_BUTTON_FLARE("/images/component_button_flare.png"),
    COMPONENT_BUTTON_SMALL("/images/component_button_small.png"),
    COMPONENT_COUNTER("/images/component_counter.png"),
    COMPONENT_DISCARD_PILE("/images/component_discard_pile.png"),
    COMPONENT_MENU_CORNER("/images/component_menu_corner.png"),
    COMPONENT_FIELD("/images/component_played_cards_field.png"),
    COMPONENT_ROCKET_BASE("/images/component_rocket_slot.png"),
    COMPONENT_ROCKET_DIVIDER("/images/component_rockets_divider.png"),
    COMPONENT_ROBOT_ATTACK("/images/component_robot_attack.png"),
    ICON_ACHIEVEMENTS("/images/icon_achievements.png"),
    ICON_APP("/images/icon_app.png"),
    ICON_BACK("/images/icon_back.png"),
    ICON_CANCEL("/images/icon_cancel.png"),
    ICON_CHIP("/images/icon_chip.png"),
    ICON_CREDITS("/images/icon_credits.png"),
    ICON_DISCARD_FROM_DECK("/images/icon_discard_from_deck.png"),
    ICON_DISCARD_HAND("/images/icon_discard_hand.png"),
    ICON_MANUAL("/images/icon_manual.png"),
    ICON_OK("/images/icon_ok.png"),
    ICON_PAUSE("/images/icon_pause.png"),
    ICON_PLAY("/images/icon_play.png"),
    ICON_RESTART("/images/icon_restart.png"),
    ICON_ROCKET("/images/icon_rocket.png"),
    ICON_SETTINGS("/images/icon_settings.png"),
    ICON_STATS("/images/icon_stats.png"),
    LOGO("/images/logo.png");

    private final String path;
    private final Image image;

    /**
     * Constructor for AppImage.
     * @param path
     */
    AppImage(final String path) {
        this.path = path;
        this.image = new Image((getClass().getResource(path).toString()));
    }

    /**
     * @return the image's relative path
     */
    public String getPath() {
        return this.path;
    }

    /**
     * @return the Image object for usage with JavaFX
     */
    public Image getImage() {
        return this.image;
    }

    @Override
    public String toString() {
        return this.path;
    }
}
