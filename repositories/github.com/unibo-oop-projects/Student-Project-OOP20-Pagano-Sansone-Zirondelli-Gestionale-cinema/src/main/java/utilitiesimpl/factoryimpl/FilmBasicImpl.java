package utilitiesimpl.factoryimpl;

import java.util.Optional;

import utilities.factory.Film;

public final class FilmBasicImpl implements Film {
    private final String name; /** Film name. More films can has the same name. */
    private final String genre; /** Film genre. */
    private final String description; /** Film description. */
    private final Optional<String> coverImagePath; /** If it's empty, user hasn't chosen any cover image path for the current film. */
    private final int duration; /** Film duration in minutes. This value will be used during film programmation to calculate end film time */
    private final int id; /** Film id. This value is unique. /*
    /**
     * @param name
     * @param genre
     * @param description
     * @param coverImagePath
     * @param duration
     * @param id
     */
    FilmBasicImpl(final String name, final String genre, final String description, final Optional<String> coverImagePath, final int duration, final int id) {
        this.name = name;
        this.genre = genre;
        this.description = description;
        this.coverImagePath = coverImagePath;
        this.duration = duration;
        this.id = id;
    }
    /** 
     * {@inheritDoc}
     * */
    @Override
    public String getName() {
        return this.name;
    }
    /** 
     * {@inheritDoc}
     * */
    @Override
    public String getGenre() {
        return this.genre;
    }
    /** 
     * {@inheritDoc}
     * */
    @Override
    public int getDuration() {
        return this.duration;
    }
    /** 
     * {@inheritDoc}
     * */
    @Override
    public Optional<String> getCoverPath() {
        return this.coverImagePath;
    }
    /** 
     * {@inheritDoc}
     * */
    @Override
    public String getDescription() {
        return this.description;
    }
    /** 
     * {@inheritDoc}
     * */
    public int getID() {
        return this.id;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }
    /**
     * {@inheritDoc}
     */

    @Override
    public String toString() {
        return "FilmBasicImpl [name=" + name + ", genre=" + genre + ", description=" + description + ", coverImagePath="
                + coverImagePath + ", duration=" + duration + ", id=" + id + "]";
    }
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        final FilmBasicImpl other = (FilmBasicImpl) obj;
        if (id != other.id) {
            return false;
        }
        return true;
    }


}
