package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import model.Room;
import utilities.CreateCinema;

/**
 * It implements ScreeningController.
 *
 */
public class ScreeningControllerImpl implements ScreeningController {

    private final List<List<Map<Character, List<Boolean>>>> seatsPosition;

    /**
     * This is the constructor of the class ScreeningImpl.
     */
    public ScreeningControllerImpl() {
        seatsPosition = new ArrayList<>();
        setRooms();
    }

    @Override
    public List<Integer> getScreeningList() {
        final List<Integer> screeningList = new ArrayList<>();
        CreateCinema.getCinema().getRoomList().forEach(room -> {
            screeningList.add(room.getScreening());
        });
        return screeningList;
    }

    @Override
    public void setSeatsPosition(final Map<Character, List<Boolean>> map, final int screening, final int room) {
        seatsPosition.get(room).set(screening, map);
        CreateCinema.getCinema().changeListMapScreening(CreateCinema.getCinema().getRoomList().get(room),
                seatsPosition.get(room));
    }

    @Override
    public List<Map<Character, List<Boolean>>> getScreeningListForRoom(final int pos) {
        return seatsPosition.get(pos);
    }

    @Override
    public int emptySeats(final Map<Character, List<Boolean>> screening) {
        int num = 0;
        for (final Map.Entry<Character, List<Boolean>> entry : screening.entrySet()) {
            num += entry.getValue().stream().filter(seat -> seat).count();
        }
        return num;
    }

    private void setRooms() {
        final List<Room> roomList = CreateCinema.getCinema().getRoomList();
        roomList.forEach(room -> {
            seatsPosition.add(CreateCinema.getCinema().getListMapScreening(room));
        });
    }

}
