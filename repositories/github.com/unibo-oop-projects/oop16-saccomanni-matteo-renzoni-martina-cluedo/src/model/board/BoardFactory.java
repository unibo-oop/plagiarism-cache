package model.board;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.common.base.Optional;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Multimap;

import model.GameInit;
import utilities.enumerations.BoardType;
import utilities.enumerations.CellType;
import utilities.enumerations.RoomCard;

/**
 * This class realizes a simple factory of Board instances.
 */
public final class BoardFactory {

    private final BoardType boardType;
    private int width;
    private final Multimap<RoomCard, Cell> rooms;
    private final Set<Cell> hallway;

    /**
     * Creates a BoardFactory instance.
     * 
     * @param boardType
     *            the board that will be created
     */
    public BoardFactory(final BoardType boardType) {
        this.boardType = boardType;
        this.rooms = HashMultimap.create();
        this.hallway = new HashSet<>();
    }

    /**
     * Creates a new board instance.
     *
     * @return a new board instance
     * @throws IOException
     *             if can't load the board initialization file
     */
    public Board createBoard() throws IOException {
        final int height = this.boardType.getHeight();
        this.width = this.boardType.getWidth();
        this.rooms.clear();
        this.hallway.clear();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(GameInit.class.getResourceAsStream(this.boardType.getBoardInitFile())))) {
            createRooms(reader);
            createHallway(reader);
            addPassage(reader, CellType.DOOR);
            addPassage(reader, CellType.TRAP_DOOR);
        }
        return new BoardImpl(height, this.width, this.rooms, this.hallway);
    }

    private void createRooms(final BufferedReader reader) throws IOException {
        String line = reader.readLine();
        while (line != null && !line.equals("###")) {
            final String[] roomLine = line.split(":");
            final RoomCard room = RoomCard.valueOf(roomLine[0]);
            final Set<Cell> roomCells = Arrays.asList(roomLine[1].split(",")).stream()
                    .map(num -> new SimpleCell(convertToPosition(Integer.parseInt(num)), Optional.of(room)))
                    .collect(Collectors.toSet());
            rooms.putAll(room, roomCells);
            line = reader.readLine();
        }
    }

    private void createHallway(final BufferedReader reader) throws IOException {
        final String line = reader.readLine();
        if (line != null) {
            this.hallway.addAll(Arrays.asList(line.split(",")).stream()
                    .map(num -> new SimpleCell(convertToPosition(Integer.parseInt(num)), Optional.absent()))
                    .collect(Collectors.toSet()));
        }
        reader.readLine();
    }

    private void addPassage(final BufferedReader reader, final CellType cellType) throws IOException {
        String line = reader.readLine();
        while (line != null && !line.equals("###")) {
            final String[] pos = line.split(",");
            final Cell roomCell = getCell(convertToPosition(Integer.parseInt(pos[0])));
            final Cell destinationCell = getCell(convertToPosition(Integer.parseInt(pos[1])));
            this.rooms.get(roomCell.getRoom().get()).remove(roomCell);
            if (cellType.equals(CellType.DOOR)) {
                this.rooms.put(roomCell.getRoom().get(), new DoorCell(destinationCell, roomCell));
            } else if (cellType.equals(CellType.TRAP_DOOR)) {
                this.rooms.put(roomCell.getRoom().get(), new TrapDoorCell(destinationCell.getRoom().get(), roomCell));
            }
            line = reader.readLine();
        }
    }

    private Cell getCell(final Position position) {
        final List<Cell> hallwayCells = this.hallway.stream().filter(cell -> cell.getPosition().equals(position))
                .collect(Collectors.toList());
        if (!hallwayCells.isEmpty()) {
            return hallwayCells.get(0);
        } else {
            return Iterables.filter(this.rooms.values(), cell -> cell.getPosition().equals(position)).iterator().next();
        }
    }

    /**
     * Converts the position of the cell expressed as an absolute number into a
     * pair of X-Y coordinates.
     * 
     * @param cellNumber
     *            the position of the cell as absolute number
     * @return the position of the cell as X-Y coordinates
     */
    private Position convertToPosition(final Integer cellNumber) {
        final int height = cellNumber / this.width;
        final int width = cellNumber % this.width;
        return new Position(width, height);
    }
}