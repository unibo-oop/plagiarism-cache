package view.menu;

import common.MsgStrings;
import enumerators.PlayerCharacter;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import model.user.CurrentUser;
import model.user.UserDataInterface;
import view.GenericView;

public abstract class CharacterShopBorderPane extends BorderPane {

    private final Button buyBtn;
    private final Button selectBtn;
    private final PlayerCharacter character;
    private final GenericView view;

    public CharacterShopBorderPane(final PlayerCharacter character, final GenericView view) {
        super();
        this.character = character;
        this.view = view;

        final ImageView img = new ImageView(new Image("images/" + character.getImageName()));

        final HBox infoBox = new HBox();
        final Label coinLabel = new Label(String.valueOf(character.getCost()));

        buyBtn = createButton(MsgStrings.BUY_CHARACTER);

        selectBtn = createButton(MsgStrings.SET_CHARACTER);
        selectBtn.setDisable(true);

        infoBox.getChildren().addAll(coinLabel, buyBtn, selectBtn);
        this.setCenter(img);
        this.setBottom(infoBox);
        this.setId("characters");
    }

    /**
     * Disable set button of current or locked characters, enable other Disable buy
     * button if character is unlocked
     */
    public void setButtonActivation() {
        final UserDataInterface data = CurrentUser.getInstance().getUser().getUserData();
        if (data.getCharacters().contains(character)) {
            buyBtn.setDisable(true);
            if (character.equals(data.getCurrentCharacter())) {
                selectBtn.setDisable(true);
            } else {
                selectBtn.setDisable(false);
            }
        } else {
            buyBtn.setDisable(false);
            selectBtn.setDisable(true);
        }
        this.checkTooExpensive();
    }

    public void setBuyActivation(final Boolean active) {
        buyBtn.setDisable(active);
    }

    public void setSelectActivation(final Boolean active) {
        selectBtn.setDisable(active);
    }

    public void checkTooExpensive() {
        final int coin = CurrentUser.getInstance().getUser().getUserData().getCoin();
        if (coin < this.character.getCost()) {
            buyBtn.setDisable(true);
        }
    }

    /**
     * Abstract method to be implemented to get character before button event is
     * send to the EventBus
     */
    public abstract void selectCharacter();

    public PlayerCharacter getCharacter() {
        return character;

    }

    private Button createButton(final String msg) {
        final Button b = new AbstractEventButton(view, msg) {
            @Override
            public void setAction() {
                selectCharacter();
            }
        }.getButton();
        b.setText(msg);
        return b;
    }
}
