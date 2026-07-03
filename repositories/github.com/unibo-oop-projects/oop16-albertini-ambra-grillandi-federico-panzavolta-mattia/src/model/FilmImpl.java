package model;

import utilities.Genre;

/**
 * 
 * This is an implementation of {@link model.Film}.
 * 
 */
public class FilmImpl implements Film {

    private final String name;
    private final int length;
    private final Genre genre;
    private final boolean over14;
    private final boolean threeDimensional;

    /**
     * Builds a new {@link FilmImpl}.
     * 
     * @param name
     *            the film name
     * @param length
     *            the film length
     * @param genre
     *            the film genre
     * @param over14
     *            the film is rated 14
     * @param threeDimensional
     *            the film is 3D
     *
     */
    public FilmImpl(final String name, final int length, final Genre genre, final boolean over14, final boolean threeDimensional) {
        this.name = name;
        this.length = length;
        this.genre = genre;
        this.over14 = over14;
        this.threeDimensional = threeDimensional;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getLength() {
        return this.length;
    }

    @Override
    public Genre getGenre() {
        return this.genre;
    }

    @Override
    public Boolean isOver14() {
        return this.over14;
    }

    @Override
    public Boolean is3D() {
        return this.threeDimensional;
    }

}
