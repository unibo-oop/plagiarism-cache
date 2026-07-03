package org.gitgud.model.remote;

import org.gitgud.model.Model;
import org.gitgud.model.RepositoryContainer;

/**
 *
 */
public class RemoteModelImpl implements RemoteModel {

    private final RemoteOperationImpl operations;
    private final RemoteActionImpl actions;

    /**
     * @param model
     *            the application model
     * @param repo
     *            the repository container instance
     */
    public RemoteModelImpl(final Model model, final RepositoryContainer repo) {
        operations = new RemoteOperationImpl(model, repo);
        actions = new RemoteActionImpl(model, repo);
    }

    @Override
    public RemoteAction getActions() {
        return actions;
    }

    @Override
    public RemoteOperation getOperations() {
        return operations;
    }

}
