package controller;

import java.awt.Color;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import manager.ControllerManager;
import view.ViewCustomPiece;
import view.ViewCustomPieceImpl;
import model.CustomPieceModel;
import model.CustomPieceModelImpl;
import pair.Pair;
import piece.Piece;
import utility.UtilsPlayer;

/**
 * Class that implements the {@link ControllerCustomPiece}.
 * 
 * @see ControllerCustomPiece
 */
public class CustomPieceImpl implements ControllerCustomPiece {

    private final ViewCustomPiece customView;
    private final CustomPieceModel customModel;
    private final ControllerManager manager;

    /**
     * @param controllerManager : controller manager of the application.
     */
    public CustomPieceImpl(final ControllerManager controllerManager) {
        this.customModel = new CustomPieceModelImpl();
        this.customView = new ViewCustomPieceImpl(this);
        this.manager = controllerManager;
        this.manager.setView(customView);
    }

    @Override
    public final void setCurrentColor(final Color newColor) {
        if (!this.customModel.getCurrentColor().equals(newColor)) {
            this.customModel.setColor(newColor);
        }
    }

    @Override
    public final void hit(final Pair<Integer, Integer> hitSquare) {
        if (this.customModel.getHitSquares().contains(hitSquare)) {
            this.customModel.delete(hitSquare);
        } else {
            this.customModel.add(hitSquare);
        }
    }

//JSON VERSION
    @Override
    public final void saveAttempt() {
        if (this.isTypeCorrect()) {
            if (this.manager.getPlayer().isPresent()) {
                final Optional<List<Piece>> temp;
                if (this.manager.getPlayer().get().getCustomPiece().isPresent()) {
                    temp = Optional.of(UtilsPlayer.getBlockList(this.manager.getPlayer().get().getCustomPiece().get()));
                } else {
                    temp = Optional.empty();
                }
                if (this.customModel.isUnique(temp)) {
                    this.customModel.savePlayerPiece(this.manager.getPlayer().get());
                    this.customModel.refresh();
                    this.customView.start();
                    this.customView.savedPiece();
                } else {
                    this.customView.alreayOnCustomPieceList();
                }
            } else {
                if (this.manager.getRtCustom().isEmpty()) {
                    this.manager.initRtCustomList();
                }
                if (this.customModel.isUnique(this.manager.getRtCustom())) {
                    this.customModel.saveRtPiece(this.manager.getRtCustom());
                    this.customModel.refresh();
                    this.customView.start();
                    this.customView.savedPiece();
                } else {
                    this.customView.alreayOnCustomPieceList();
                }
            }
        } else {
            this.customView.incorrectTypePiece();
        }
    }

    private boolean isTypeCorrect() {

        Boolean correctType = true;

        if (this.getSquares().isEmpty() || this.getSquares().size() == 1) {
            correctType = false;
        } else {
            final Set<Pair<Integer, Integer>> wrongs = new HashSet<>();
            this.getSquares().stream().forEach(p -> {
                if (!(this.getSquares().contains(new Pair<>(p.getX() - 1, p.getY()))
                        || this.getSquares().contains(new Pair<>(p.getX() + 1, p.getY()))
                        || this.getSquares().contains(new Pair<>(p.getX(), p.getY() - 1))
                        || this.getSquares().contains(new Pair<>(p.getX(), p.getY() + 1)))) {
                    wrongs.add(p);
                }
            });

            if (!wrongs.isEmpty()) {
                correctType = false;
            }
        }

        return correctType;
    }

    @Override
    public final void clearData() {
        this.customModel.refresh();
    }

    @Override
    public final Set<Pair<Integer, Integer>> getSquares() {
        return this.customModel.getHitSquares();
    }

    @Override
    public final ControllerManager getManager() {
        return this.manager;
    }

}
