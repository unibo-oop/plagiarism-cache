package modelimpl.managefilms;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;

import model.managefilms.IdsGenerator;
import model.managefilms.ManagerIdsFilms;
/** 
 * Describes a manager for ids film.
 * */
public final class ManagerIdsFilmImpl implements ManagerIdsFilms {
    private  final Collection<Integer> containerFilmsIds;
    private  final IdsGenerator idsGenerator;

    public ManagerIdsFilmImpl(final IdsGenerator idsGenerator, final Collection<Integer> containerFilmsIds) { 
        this.containerFilmsIds = containerFilmsIds;
        this.idsGenerator = idsGenerator;
    }

    public ManagerIdsFilmImpl(final IdsGenerator idsGenerator) { 
        this.containerFilmsIds = new HashSet<>();
        this.idsGenerator = idsGenerator;
    }

    public Collection<Integer> getUsedIDs(final IdsGenerator idsGenerator) {
        return containerFilmsIds;
    }
    public int getNewFilmID() {
        final int id = idsGenerator.getNewId();
        containerFilmsIds.add(id);
        return id;
    }
    /** 
     * {@inheritDoc}
     * */

    @Override
    public Optional<Integer> getLastGeneratedId() {
        return idsGenerator.getLastGeneratedId();
    }
    /** 
     * {@inheritDoc}
     * */
    @Override
    public Collection<Integer> getUsedIDs() {
             return containerFilmsIds;
    }
    /** 
     * {@inheritDoc}
     * */
    @Override
    public String toString() {
        return containerFilmsIds.toString() + idsGenerator.toString();
    }
    /** 
     * {@inheritDoc}
     * */
    @Override
    public void removeFilmId(final int idToDelete) {
       containerFilmsIds.remove(idToDelete);
    }
}
