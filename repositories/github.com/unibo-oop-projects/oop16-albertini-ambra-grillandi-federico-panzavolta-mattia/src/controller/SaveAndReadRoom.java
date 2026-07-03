package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.Room;
import utilities.CreateCinema;

/**
 * It is used to save and read informations of films.
 *
 */
public class SaveAndReadRoom implements SaveAndRead<List<String>> {

    private static final String FILE = CreateCinema.FILE_R;
    private static final String ROOM_STR = "ROOM: ";
    private static final String SEATS_STR = "N .SEATS: ";
    private static final String FILM_STR = "FILM :";

    @Override
    public void save() {
        try (BufferedWriter w = new BufferedWriter(new FileWriter(FILE))) {
            // It will write how much rooms there are in this cinema, then it will write
            // the number of seats for each room and the film screened in it.
            w.write("N. ROOMS: " + CreateCinema.getCinema().getRoomList().size());
            w.newLine();
            CreateCinema.getCinema().getRoomList().forEach(r -> {
                try {
                    w.write(ROOM_STR + CreateCinema.getCinema().getRoomName(r));
                    w.newLine();
                    w.write(SEATS_STR + CreateCinema.getCinema().getSeats(r));
                    w.newLine();
                    w.write(FILM_STR);
                    if (CreateCinema.getCinema().getFilmScreened(r) != null) {
                        w.write(CreateCinema.getCinema().getName(CreateCinema.getCinema().getFilmScreened(r)));
                    }
                    w.newLine();
                } catch (final IOException e) {
                    System.err.println(e.getMessage());
                }
            });
        } catch (final IOException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public List<String> read() {
        final List<Room> roomList = new ArrayList<>();
        final List<String> nameList = new ArrayList<>();
        final List<Integer> seatsList = new ArrayList<>();
        final List<String> filmList = new ArrayList<>();
        try (BufferedReader r = new BufferedReader(new FileReader(FILE))) {
            r.lines().forEach(l -> {
                if (l.contains(ROOM_STR)) {
                    nameList.add(l.substring(ROOM_STR.length()));
                }
                if (l.contains(SEATS_STR)) {
                    seatsList.add(Integer.parseInt(l.substring(SEATS_STR.length())));
                }
                if (l.contains(FILM_STR)) {
                    if (l.length() > FILM_STR.length()) {
                        filmList.add(l.substring(FILM_STR.length()));
                    } else {
                        filmList.add(" ");
                    }
                }
            });
            for (int i = 0; i < nameList.size(); i++) {
                roomList.add(CreateCinema.getCinema().createRoom(nameList.get(i), seatsList.get(i)));
            }
            return filmList;
        } catch (final Exception e) {
            System.err.println(e.getMessage());
            return filmList;
        }
    }

    @Override
    public void reset() {
        // It removes all the informations of the rooms from the room list and deleted what is saved in the file.
        CreateCinema.getCinema().getRoomList().removeAll(CreateCinema.getCinema().getRoomList());
        try (BufferedWriter w = new BufferedWriter(new FileWriter(FILE))) {
            w.flush();
        } catch (final IOException e) {
            System.err.println(e.getMessage());
        }

    }

}
