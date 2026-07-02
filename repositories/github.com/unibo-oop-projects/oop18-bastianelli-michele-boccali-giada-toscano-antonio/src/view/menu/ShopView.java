package view.menu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import common.MsgStrings;
import controller.menu.Controller;
import enumerators.PlayerCharacter;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import view.GenericView;

/**
 * Create a concrete shop view that extends a GenericView.
 */
public class ShopView extends GenericView {

    private PlayerCharacter characterSelected;
    private final List<CharacterShopBorderPane> charactersList;

    /**
     * Shop view constructor.
     * 
     * @param c : View controller
     */
    public ShopView(final Controller c) {
        super(c);
        final BorderPane box = new BorderPane();
        super.init(box, 20.0, 40.0, 20.0, 20.0);

        // top
        final Label title = new Label(MsgStrings.SHOP);
        title.setId("title");
        final HBox titleBox = new HBox();
        titleBox.getChildren().add(title);

        // center
        final TilePane charactersBox = new TilePane();
        charactersList = new ArrayList<>();
        Arrays.asList(PlayerCharacter.values()).forEach(player -> {
            charactersList.add(new CharacterShopBorderPane(player, this) {
                @Override
                public void selectCharacter() {
                    setCharacterSelected(player);
                }
            });
            charactersList.get(charactersList.size() - 1).setButtonActivation();
            charactersList.get(charactersList.size() - 1).checkTooExpensive();
        });
        charactersBox.getChildren().addAll(charactersList);

        // buttom
        final Button menuBtn = new MsgEventButton(this, MsgStrings.MENU).getButton();
        final HBox buttonBox = new HBox();
        buttonBox.getChildren().add(menuBtn);

        box.setTop(titleBox);
        box.setCenter(charactersBox);
        box.setBottom(buttonBox);
    }

    /**
     * Set buttons enable od disable foreach player character.
     */
    public void updateActivation() {
        for (final CharacterShopBorderPane characterShop : charactersList) {
            characterShop.setButtonActivation();
            characterShop.checkTooExpensive();
        }
        this.characterSelected = null;
    }

    /**
     * Set character to buy.
     * 
     * @param characterSelected from view
     */
    private void setCharacterSelected(final PlayerCharacter characterSelected) {
        this.characterSelected = characterSelected;
    }

    /**
     * Get character to buy from the shop.
     * 
     * @return selected player
     */
    public PlayerCharacter getSelectedPlayer() {
        return this.characterSelected;
    }
}
