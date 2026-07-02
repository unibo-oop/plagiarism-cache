package iuniversity.model;

import java.util.Optional;

import iuniversity.model.didactics.DidacticsManager;
import iuniversity.model.didactics.DidacticsManagerImpl;
import iuniversity.model.exams.ExamsManager;
import iuniversity.model.exams.ExamsManagerImpl;
import iuniversity.model.user.Archive;
import iuniversity.model.user.ArchiveImpl;
import iuniversity.model.user.User;

/**
 * This is the model class. It contains method to access the representation of model data.
 *
 */
public class ModelImpl implements Model {

    private final ExamsManager examManager = new ExamsManagerImpl();
    private final DidacticsManager didacticsManager = new DidacticsManagerImpl();
    private final Archive archive = new ArchiveImpl();
    private Optional<User> currentUser = Optional.empty();

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<User> getLoggedUser() {
        return this.currentUser;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCurrentUser(final User user) {
        this.currentUser = Optional.of(user);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ExamsManager getExamManager() {
        return this.examManager;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DidacticsManager getDidacticsManager() {
        return this.didacticsManager;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Archive getArchive() {
        // TODO Auto-generated method stub
        return this.archive;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void unsetCurrentUser() {
        this.currentUser = Optional.empty(); 
    }

}
