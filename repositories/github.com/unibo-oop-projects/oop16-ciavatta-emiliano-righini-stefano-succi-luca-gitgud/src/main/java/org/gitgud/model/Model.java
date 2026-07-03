package org.gitgud.model;

import org.gitgud.model.branch.BranchModel;
import org.gitgud.model.diff.DiffModel;
import org.gitgud.model.merge.MergeModel;
import org.gitgud.model.remote.RemoteModel;
import org.gitgud.model.repository.RepositoryModel;
import org.gitgud.model.stage.StageModel;
import org.gitgud.model.tag.TagModel;

/**
 * Represents the main model of the entire application.
 */
public interface Model {

    /**
     * Advise model that the application it's going to be closed. Should be called to clear all resources and stop all
     * active tasks.
     */
    void exitCommand();

    /**
     * Return the model that manages the logic of git branches.
     *
     * @return the branch model
     */
    BranchModel getBranchModel();

    /**
     * Return the model that manages the logic of the difference between commits.
     *
     * @return the diff model
     */
    DiffModel getDiffModel();

    /**
     * Return the model that manages the logic of the merge operations.
     *
     * @return the merge model
     */
    MergeModel getMergeModel();

    /**
     * Return the model that manages the logic of the remotes.
     *
     * @return the remote model
     */
    RemoteModel getRemoteModel();

    /**
     * Return the model that manages the logic of the repository.
     *
     * @return the repository model
     */
    RepositoryModel getRepositoryModel();

    /**
     * Return the model that manages the logic of the staging area.
     *
     * @return the stage model
     */
    StageModel getStageModel();

    /**
     * Return the model that manages the logic of the tags.
     *
     * @return the tag model
     */
    TagModel getTagModel();
}
