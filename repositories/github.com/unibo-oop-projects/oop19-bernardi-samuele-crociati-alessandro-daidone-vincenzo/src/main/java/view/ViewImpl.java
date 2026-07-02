package view;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.util.Pair;
import uicontrollers.BattleController;
import uicontrollers.HeroPickerController;

/**
 * Implementation of an generic View.
 */
public final class ViewImpl implements View {
    @Override
    public void startView() {
        Application.launch(MainWindow.class);
    }

    @Override
    public void showPrePickerScreen(final List<String> arenaNames) {
        try {
            final FXMLLoader loader = new FXMLLoader(getClass().getResource("/layouts/prepicker.fxml"));
            final Parent root = loader.load();
            final Scene prePickerScene = new PrePickerScene(root, arenaNames);
            MainWindow.getStage().setScene(prePickerScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showPickerScreen(final List<String> heroPool, final String initialPlayerTurn) {
        try {
            final FXMLLoader loader = new FXMLLoader(getClass().getResource("/layouts/heropicker.fxml"));
            final Parent root = loader.load();
            final Scene heroPickerScene = new HeroPickerScene(root, heroPool, loader.getController(),
                    initialPlayerTurn);
            MainWindow.getStage().setScene(heroPickerScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showGameScreen(final Pair<Integer, Integer> p) {
        try {
            final FXMLLoader loader = new FXMLLoader(getClass().getResource("/layouts/battlewindow.fxml"));
            final Parent root = loader.load();
            final Scene battleScene = new BattleScene(root, loader.getController(), p);
            MainWindow.getStage().setScene(battleScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showEditorScreen() {
        try {
            final FXMLLoader loader = new FXMLLoader(getClass().getResource("/layouts/mapeditor.fxml"));
            final Parent root = loader.load();
            final Scene mapEditorScene = new MapEditorScene(root, loader.getController());
            MainWindow.getStage().setScene(mapEditorScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showCharacterEditorScreen() {
        try {
            final FXMLLoader loader = new FXMLLoader(getClass().getResource("/layouts/pgeditor.fxml"));
            final Parent root = loader.load();
            final Scene pgEditorScene = new CharacterEditorScene(root, loader.getController());
            MainWindow.getStage().setScene(pgEditorScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addTeamOne(final String heroImageName, final int identifier, final HeroPickerController hpc) {
        hpc.addTeamOne(heroImageName, identifier);
    }

    @Override
    public void addTeamTwo(final String heroImageName, final int identifier, final HeroPickerController hpc) {
        hpc.addTeamTwo(heroImageName, identifier);
    }

    @Override
    public void removeTeamOne(final HeroPickerController hpc, final int identifier) {
        hpc.removeTeamOne(identifier);
    }

    @Override
    public void removeTeamTwo(final HeroPickerController hpc, final int identifier) {
        hpc.removeTeamTwo(identifier);
    }

    @Override
    public void updatePickerHeroPool(final boolean isPickable, final int index, final HeroPickerController hpc) {
        hpc.updatePool(isPickable, index);
    }

    @Override
    public void updatePickerTurn(final String playerTurn, final HeroPickerController hpc) {
        hpc.updateTurn(playerTurn);
    }

    @Override
    public void drawArenaTerrain(final Map<Pair<Integer, Integer>, String> arenaMap, final BattleController bc) {
        bc.drawTerrain(arenaMap);
    }

    @Override
    public void drawAliveEntities(final Map<Pair<Integer, Integer>, String> aliveEntities, final BattleController bc) {
        bc.drawAlive(aliveEntities);
    }

    @Override
    public void showSelectionAttackCandidates(final List<Pair<Integer, Integer>> positions, final BattleController bc) {
        bc.selectionAttackCandidates(positions);
    }

    @Override
    public void showSelectionMovementCandidates(final List<Pair<Integer, Integer>> positions,
            final BattleController bc) {
        bc.selectionMovementCandidates(positions);
    }

    @Override
    public void showCursorSelectionAttackCandidates(final List<Pair<Integer, Integer>> positions,
            final BattleController bc) {
        bc.cursorSelectionAttackCandidates(positions);
    }

    @Override
    public void showCursorSelectionMovementCandidates(final List<Pair<Integer, Integer>> positions,
            final BattleController bc) {
        bc.cursorSelectionMovementCandidates(positions);
    }

    @Override
    public void showPickerSelectionInfo(final String name, final String maxHp, final String attack,
            final String defence, final String movementRange, final String attackRange,
            final HeroPickerController hpc) {
        hpc.showPickerInfo(name, maxHp, attack, defence, movementRange, attackRange);
    }

    @Override
    public void showGameSelectionInfo(final String name, final String maxHP, final String currentHP,
            final String attack, final String defence, final String movementType, final String attackType,
            final String attackStatus, final String movementStatus, final BattleController bc) {
        bc.showSelectionInfo(name, maxHP, currentHP, attack, defence, movementType, attackType, attackStatus,
                movementStatus);

    }

    @Override
    public void showGameCursorInfo(final String name, final String maxHP, final String currentHP, final String attack,
            final String defence, final String movementType, final String attackType, final String attackStatus,
            final String movementStatus, final BattleController bc) {
        bc.showCursorInfo(name, maxHP, currentHP, attack, defence, movementType, attackType, attackStatus,
                movementStatus);
    }

    @Override
    public void updateEntityPosition(final BattleController bc, final Pair<Integer, Integer> newPos,
            final Pair<Integer, Integer> oldPos) {
        bc.updatePosition(newPos, oldPos);
    }

    @Override
    public void updateGameTurn(final BattleController bc, final String turn) {
        bc.updateTurn(turn);
    }

    @Override
    public void showVictoryMessage(final BattleController bc, final String victoryMessage) {
        bc.victoryMessage(victoryMessage);
    }

}
