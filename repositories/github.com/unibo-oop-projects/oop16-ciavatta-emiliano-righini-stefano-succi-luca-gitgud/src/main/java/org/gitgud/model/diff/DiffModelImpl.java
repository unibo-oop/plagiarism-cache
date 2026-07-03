package org.gitgud.model.diff;

import java.util.Objects;

import org.gitgud.model.RepositoryContainer;

/**
 *
 */
public class DiffModelImpl implements DiffModel {

    private final RepositoryContainer repo;

    /**
     * @param repo
     *            the repository container
     */
    public DiffModelImpl(final RepositoryContainer repo) {
        this.repo = repo;
    }

    @Override
    public DiffManager diff(final DiffParam diffParam) {
        Objects.requireNonNull(diffParam);

        final DiffManagerImpl manager = new DiffManagerImpl(repo);
        if (diffParam.isDiffCommits()) {
            manager.setDiffCommits(diffParam.getOldCommitHash().get(), diffParam.getNewCommitHash().get());
        } else if (diffParam.isDiffStaged()) {
            manager.setDiffStaged();
        } else if (diffParam.isDiffUnstaged()) {
            manager.setDiffUnstaged();
        }
        manager.computeDifferences();

        return manager;
    }

}
