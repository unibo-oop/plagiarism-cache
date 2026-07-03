package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.Film;
import utilities.CreateCinema;
import utilities.Genre;

/**
 * It is used to save and read informations of films.
 *
 */
public class SaveAndReadFilm implements SaveAndRead<List<Film>> {

    private static final String FILE = CreateCinema.FILE_F;
    private static final String FILM_STR = "FILM";
    private static final String TITLE_STR = "TITLE: ";
    private static final String LENGTH_STR = "LENGTH: ";
    private static final String GENRE_STR = "GENRE: ";
    private static final String OVER_14_STR = "OVER 14: ";
    private static final String THREE_DIM_STR = "3D: ";

    private Film filmToSave;

    @Override
    public void save() {
        // It will write on the file the position of the file in the film list and a line
        // for every field of the film (one for the title, one for the length, etc.).
        final String filmStr = new String(FILM_STR + CreateCinema.getCinema().getFilmList().indexOf(filmToSave));
        try (BufferedWriter w = new BufferedWriter(new FileWriter(FILE, true))) {
            w.append(filmStr + ":");
            w.newLine();
            w.write(TITLE_STR + CreateCinema.getCinema().getName(filmToSave));
            w.newLine();
            w.write(LENGTH_STR + CreateCinema.getCinema().getLength(filmToSave));
            w.newLine();
            w.write(GENRE_STR + CreateCinema.getCinema().getGenre(filmToSave).toString());
            w.newLine();
            w.write(OVER_14_STR + CreateCinema.getCinema().isOver14(filmToSave));
            w.newLine();
            w.write(THREE_DIM_STR + CreateCinema.getCinema().is3D(filmToSave));
            w.newLine();
        } catch (final IOException e) {
            System.err.println("ERROR: " + e.getMessage());
        }
    }

    @Override
    public List<Film> read() {
        final List<Film> filmList = new ArrayList<>();
        final List<String> titleList = new ArrayList<>();
        final List<Integer> lengthList = new ArrayList<>();
        final List<Genre> genreList = new ArrayList<>();
        final List<Boolean> over14List = new ArrayList<>();
        final List<Boolean> threeDimList = new ArrayList<>();
        // Creation of lists with titles, length, genre and boolean fields
        // of the films that has to be added in the film list.
        try (BufferedReader r = new BufferedReader(new FileReader(FILE))) {
            r.lines().forEach(l -> {
                if (l.contains(TITLE_STR)) {
                    titleList.add(l.substring(TITLE_STR.length()));
                }
                if (l.contains(LENGTH_STR)) {
                    lengthList.add(Integer.parseInt(l.substring(LENGTH_STR.length())));
                }
                if (l.contains(GENRE_STR)) {
                    genreList.add(Genre.valueOf(l.substring(GENRE_STR.length())));
                }
                if (l.contains(OVER_14_STR)) {
                    over14List.add(Boolean.valueOf(l.substring(OVER_14_STR.length())));
                }
                if (l.contains(THREE_DIM_STR)) {
                    threeDimList.add(Boolean.valueOf(l.substring(THREE_DIM_STR.length())));
                }
            });
            // Creation of the films using values saved in the lists of titles, genres, etc.
            for (int i = 0; i < titleList.size(); i++) {
                filmList.add(CreateCinema.getCinema().createFilm(titleList.get(i), lengthList.get(i), genreList.get(i),
                        over14List.get(i), threeDimList.get(i)));
            }
            return filmList;
        } catch (final IOException e) {
            return filmList;
        }
    }

    @Override
    public void reset() {
        // It removes all the films from the film list and deleted what is saved in the file.
        CreateCinema.getCinema().getFilmList().removeAll(CreateCinema.getCinema().getFilmList());
        try (BufferedWriter w = new BufferedWriter(new FileWriter(FILE))) {
            w.flush();
        } catch (final IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * It sets the film to save in the file.
     *
     * @param film
     *            the film to save.
     */
    protected void setFilm(final Film film) {
        filmToSave = film;
    }
}
