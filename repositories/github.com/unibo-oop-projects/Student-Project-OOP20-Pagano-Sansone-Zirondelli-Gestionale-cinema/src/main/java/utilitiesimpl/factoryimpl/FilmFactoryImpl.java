package utilitiesimpl.factoryimpl;

import java.util.Optional;

import model.managefilms.ManagerIdsFilms;
import utilities.factory.Film;
import utilities.factory.FilmFactory;

/** 
    Factory to create new Film.
*/
public final class FilmFactoryImpl implements FilmFactory {

    private final ManagerIdsFilms managerIds; /** Manager for film ids. It's used to assign a new id for a new film. */

    /** Creates a FilmFactory  with specified managerIds.
     * @param managerIds ManagerIdsFilms
    */
    public FilmFactoryImpl(final ManagerIdsFilms managerIds) {
        this.managerIds = managerIds;
    }

    /** Creates basic film with film id generated from  managerIds.
     * @param name Film name
     * @param genre Film genre
     * @param description Film description
     * @param coverPath   Film cover path
     * @param duration Film duration
    */
    @Override
    public Film createBasicFilm(final String name, final String genre, final  String description, final Optional<String> coverPath, final  int duration) {
        return new FilmBasicImpl(name, genre, description, coverPath, duration, managerIds.getNewFilmID());
    }

    /** Creates basic film with film with a specified film id.
     * @param name Film name
     * @param genre Film genre
     * @param description Film description
     * @param coverPath   Film cover path
     * @param duration Film duration
     * @param id Film id
    */
    @Override
    public Film createBasicFilmById(final String name, final String genre, final String description, final Optional<String> coverPath,
            final int duration, final int id) {
        return new FilmBasicImpl(name, genre, description, coverPath, duration, id);
    }


 
}
