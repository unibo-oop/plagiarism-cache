package org.gitgud.model;

import java.util.Optional;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.gitgud.model.branch.BranchModel;
import org.gitgud.model.branch.BranchModelImpl;
import org.gitgud.model.diff.DiffModel;
import org.gitgud.model.diff.DiffModelImpl;
import org.gitgud.model.merge.MergeModel;
import org.gitgud.model.merge.MergeModelImpl;
import org.gitgud.model.remote.RemoteModel;
import org.gitgud.model.remote.RemoteModelImpl;
import org.gitgud.model.repository.RepositoryModel;
import org.gitgud.model.repository.RepositoryModelImpl;
import org.gitgud.model.stage.StageModel;
import org.gitgud.model.stage.StageModelImpl;
import org.gitgud.model.tag.TagModel;
import org.gitgud.model.tag.TagModelImpl;
import org.gitgud.utils.Log;
import org.gitgud.utils.Utils;

/**
 * The main model of the entire application.
 */
public final class ModelImpl implements Model {

    private final BranchModel branchModel;
    private final DiffModel diffModel;
    private final RemoteModel remoteModel;
    private final RepositoryModel repositoryModel;
    private final StageModel stageModel;
    private final TagModel tagModel;
    private final MergeModel mergeModel;

    /**
     *
     */
    public ModelImpl() {
        Log.getLogger().info("Initialiting model..");

        final WritableRepositoryContainer repository = createRepositoryContainer();
        repositoryModel = new RepositoryModelImpl(this, repository);
        remoteModel = new RemoteModelImpl(this, repository);
        branchModel = new BranchModelImpl(this, repository);
        stageModel = new StageModelImpl(repository);
        diffModel = new DiffModelImpl(repository);
        tagModel = new TagModelImpl(repository);
        mergeModel = new MergeModelImpl(repository);
    }

    @Override
    public void exitCommand() {
        if (repositoryModel.hasRepositoryOpened()) {
            repositoryModel.closeRepository();
        }

        Utils.closeResources();
    }

    @Override
    public BranchModel getBranchModel() {
        return branchModel;
    }

    @Override
    public DiffModel getDiffModel() {
        return diffModel;
    }

    @Override
    public MergeModel getMergeModel() {
        return mergeModel;
    }

    @Override
    public RemoteModel getRemoteModel() {
        return remoteModel;
    }

    @Override
    public RepositoryModel getRepositoryModel() {
        return repositoryModel;
    }

    @Override
    public StageModel getStageModel() {
        return stageModel;
    }

    @Override
    public TagModel getTagModel() {
        return tagModel;
    }

    private WritableRepositoryContainer createRepositoryContainer() {
        return new WritableRepositoryContainer() {

            private Optional<CredentialsProvider> credentialsProvider;
            private Git repo;

            @Override
            public Optional<CredentialsProvider> getCredentialsProvider() {
                return credentialsProvider;
            }

            @Override
            public Optional<Git> getRepository() {
                return Optional.ofNullable(repo);
            }

            @Override
            public void setCredentialsProvider(final CredentialsProvider credentialsProvider) {
                this.credentialsProvider = Optional.ofNullable(credentialsProvider);
            }

            @Override
            public void setRepository(final Git repository) {
                repo = repository;
                credentialsProvider = Optional.empty();
            }
        };
    }

}
