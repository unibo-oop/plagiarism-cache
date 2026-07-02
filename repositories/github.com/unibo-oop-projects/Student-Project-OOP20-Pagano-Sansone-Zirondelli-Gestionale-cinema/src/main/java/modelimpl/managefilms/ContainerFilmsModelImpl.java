package modelimpl.managefilms;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import model.managefilms.ContainerFilmsModel;
import model.managefilms.ManagerIdsFilms;
import utilities.factory.Film;

/**
 *  Container where films are stored. It's used a set of films and a manager for ids films.
 *  */
public final class ContainerFilmsModelImpl implements ContainerFilmsModel {
    private final Set<Film> containerFilms;
    private final ManagerIdsFilms manager; 

    // must be invoked on the first use of application because no date must be load
    public ContainerFilmsModelImpl() { 
        containerFilms = new HashSet<>();
        manager = new ManagerIdsFilmImpl(new IdsGeneratorImpl(Optional.ofNullable(null)), new HashSet<>());
    }
    // always must be invoked apart on  the first use (in case of Container cant't be written on file)
    public ContainerFilmsModelImpl(final Set<Film> container, final ManagerIdsFilms manager) {
        this.containerFilms = container;
        this.manager = manager;
    }

    /** 
     * {@inheritDoc}
     * */
    @Override
    public void addFilm(final Film newFilm) {
        this.containerFilms.add(newFilm);
    }
    /** 
     * {@inheritDoc}
     * */
    @Override
    public void removeFilm(final Film oldFilm) {
       this.containerFilms.remove(oldFilm);
    }
    /** 
     * {@inheritDoc}
     * */
    @Override
    public Set<Film> getFilms() {
        return this.containerFilms;
    }
    /** 
     * {@inheritDoc}
     * */
    @Override
    public ManagerIdsFilms getManagerIdsFilms() {
        return this.manager;
    }
    /** 
     * {@inheritDoc}
     * */
    @Override
    public String toString() {
        return "" + containerFilms + "" + manager;
    }

}
