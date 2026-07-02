package model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import login.Custom;
import login.CustomImpl;
import login.Player;
import pair.Pair;
import piece.Piece;
import piece.PieceImpl;
import piece.Type;

/**
 * Class that implements {@link CustomPieceModel}.
 * 
 * @see CostumPieceModel.
 */
public class CustomPieceModelImpl implements CustomPieceModel {

    private static final Color DEFUALT_COLOR = Color.PINK;

    private Color currentColor;
    private final Set<Pair<Integer, Integer>> hitSquares;

    /**
     * 
     */
    public CustomPieceModelImpl() {
        this.currentColor = DEFUALT_COLOR;
        this.hitSquares = new LinkedHashSet<>();
    }

    @Override
    public final Color getCurrentColor() {
        return this.currentColor;
    }

    @Override
    public final Set<Pair<Integer, Integer>> getHitSquares() {
        return this.hitSquares;
    }

    @Override
    public final void refresh() {
        this.currentColor = DEFUALT_COLOR;
        this.hitSquares.clear();
    }

    @Override
    public final void add(final Pair<Integer, Integer> hit) {
        this.hitSquares.add(hit);
    }

    @Override
    public final void delete(final Pair<Integer, Integer> deleteSquare) {
        this.hitSquares.remove(deleteSquare);
    }

    @Override
    public final void setColor(final Color newColor) {
        this.currentColor = newColor;
    }

    @Override
    public final boolean isUnique(final Optional<List<Piece>> checkList) {

        if (checkList.isEmpty()) {
            return true;
        }
        final Piece temp = this.getBlockCostumPiece();

        return !checkList.get().stream().filter(b -> b.getCoordinates().size() == temp.getCoordinates().size())
                .anyMatch(b -> b.getCoordinates().stream().allMatch(c -> temp.getCoordinates().contains(c)));
    }

    private Pair<Integer, Integer> getCenter(final Set<Pair<Integer, Integer>> fixedCoords) {
        final int xCenter;
        final int yCenter;
        final List<Integer> listX;
        final List<Integer> listY;

        listX = fixedCoords.stream().map(pair -> pair.getX()).sorted().collect(Collectors.toList());
        xCenter = listX.get(0) + listX.get(listX.size() - 1) / 2;
        listY = fixedCoords.stream().map(pair -> pair.getY()).sorted().collect(Collectors.toList());
        yCenter = listY.get(0) + listY.get(listY.size() - 1) / 2;

        return new Pair<>(xCenter, yCenter);
    }

    private Piece getBlockCostumPiece() {
        Piece customBlock;
        Set<Pair<Integer, Integer>> fixedCoordinates = new HashSet<>();

        final int minX = this.hitSquares.stream().map(x -> x.getX()).min(Comparator.naturalOrder()).get();
        final int minY = this.hitSquares.stream().map(x -> x.getY()).min(Comparator.naturalOrder()).get();

        if (minX > 0 || minY > 0) {
            final List<Pair<Integer, Integer>> tempList = new ArrayList<>();
            this.hitSquares.forEach(e -> tempList.add(new Pair<Integer, Integer>(e.getX() - minX, e.getY() - minY)));
            fixedCoordinates = tempList.stream().collect(Collectors.toSet());

        } else {
            fixedCoordinates = this.hitSquares;
        }
        customBlock = new PieceImpl(Type.CUSTOM, Optional.of(fixedCoordinates), Optional.of(this.currentColor),
                Optional.of(this.getCenter(fixedCoordinates)));

        return customBlock;
    }

    @Override
    public final void saveRtPiece(final Optional<List<Piece>> tempList) {
        tempList.get().add(this.getBlockCostumPiece());
    }

    @Override
    public final void savePlayerPiece(final Player player) {

        final List<Custom> tempPieces = new ArrayList<>();

        if (player.getCustomPiece().isPresent()) {
            tempPieces.addAll(player.getCustomPiece().get());
        }
        tempPieces.add(this.getCustom());
        player.setCustomPiece(Optional.of(tempPieces));
        player.writeOnFile();

    }

    private Custom getCustom() {

        Set<Pair<Integer, Integer>> fixedCoordinates = new HashSet<>();

        final int minX = this.hitSquares.stream().map(x -> x.getX()).min(Comparator.naturalOrder()).get();
        final int minY = this.hitSquares.stream().map(x -> x.getY()).min(Comparator.naturalOrder()).get();

        if (minX > 0 || minY > 0) {
            final List<Pair<Integer, Integer>> tempList = new ArrayList<>();
            this.hitSquares.forEach(e -> tempList.add(new Pair<Integer, Integer>(e.getX() - minX, e.getY() - minY)));
            fixedCoordinates = tempList.stream().collect(Collectors.toSet());

        } else {
            fixedCoordinates = this.hitSquares;
        }
        return new CustomImpl(this.currentColor, fixedCoordinates.stream().collect(Collectors.toList()),
                this.getCenter(fixedCoordinates));

    }

    @Override
    public final String toString() {
        return "CustomPiece [color = " + this.currentColor.getRGB() + ", coordinates = " + this.hitSquares + "]";
    }

}
