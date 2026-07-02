package talisman.controller.board;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import talisman.model.board.TalismanBoard;
import talisman.model.board.TalismanBoardFactory;
import talisman.model.board.TalismanBoardPawn;
import talisman.model.character.CharacterModel;
import talisman.model.character.CharacterModelImpl;
import talisman.model.character.defaultCharacters.CharacterType;
import talisman.util.PathUtils;

import talisman.view.board.TalismanBoardViewBuilder;

public final class TalismanBoardControllerFactory {
    private TalismanBoardControllerFactory() {
    }

    public static TalismanBoardController createController(final List<CharacterModel> playerCharacters) {
        final List<TalismanBoardPawn> pawns = new ArrayList<>();
        for (int i = 0; i < playerCharacters.size(); i++) {
            final int iconIndex = Arrays.binarySearch(CharacterType.values(),
                    ((CharacterModelImpl) playerCharacters.get(i)).getType());
            pawns.add(TalismanBoardPawn.createPawn(PathUtils.getPathToCharacterIcon(iconIndex), i));
        }
        final TalismanBoard board = TalismanBoardFactory.createDefaultBoardModel(pawns);
        final TalismanBoardViewBuilder viewBuilder = new TalismanBoardViewBuilder();
        return TalismanBoardController.create(board, viewBuilder.buildFromModel(board));
    }
}
