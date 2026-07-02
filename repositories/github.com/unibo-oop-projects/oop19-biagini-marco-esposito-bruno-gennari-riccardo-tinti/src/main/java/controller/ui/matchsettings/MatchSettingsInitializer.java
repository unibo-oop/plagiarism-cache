package controller.ui.matchsettings;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import application.Battleships;
import controller.matchsetup.PlayerChecker;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.ChoiceBox;
import model.gamemode.GameMode;
import view.util.ChoiceBoxInitializer;
import view.util.ChoiceBoxInitializerImpl;

//package-protected
final class MatchSettingsInitializer {

    private final MatchSettings ms;
    private final PlayerChecker playerChecker;
    private final Collection<String> usernames = Battleships.getController().getAccountManager().getAllUsername().orElse(Collections.emptyList());

    private enum ChoiceBoxType {
        PLAYER, GAMEMODE;
    }

    protected MatchSettingsInitializer(final MatchSettings ms, final PlayerChecker playerCheckerImpl) {
        this.ms = ms;
        this.playerChecker = playerCheckerImpl;
    }

    protected void initialize(final ChoiceBox<String> choiceboxPlayer1, final ChoiceBox<String> choiceboxPlayer2, final ChoiceBox<GameMode> choiceboxGameMode) {
        ChoiceBoxInitializer cbInit = new ChoiceBoxInitializerImpl();
        cbInit.initChoiceBox(choiceboxPlayer1, usernames, getChoiceBoxListener(choiceboxPlayer1, ChoiceBoxType.PLAYER));
        cbInit.initChoiceBox(choiceboxPlayer2, usernames, getChoiceBoxListener(choiceboxPlayer2, ChoiceBoxType.PLAYER));
        cbInit.initChoiceBox(choiceboxGameMode, Arrays.asList(GameMode.values()), getChoiceBoxListener(choiceboxGameMode, ChoiceBoxType.GAMEMODE));
        choiceboxGameMode.getSelectionModel().selectFirst();
    }
 
    private <T> ChangeListener<T> getChoiceBoxListener(final ChoiceBox<T> cb, final ChoiceBoxType type) {
        return type.equals(ChoiceBoxType.PLAYER)
                ? (x, y, z) -> {
                    if (z != null && !playerChecker.areCredentialsValid((String) z)) {
                        cb.getSelectionModel().clearSelection();
                    }
                }
                : (x, y, z) -> {
                    ms.setGameModeDescription(((GameMode) z).getDescription());
                };
    }

}
