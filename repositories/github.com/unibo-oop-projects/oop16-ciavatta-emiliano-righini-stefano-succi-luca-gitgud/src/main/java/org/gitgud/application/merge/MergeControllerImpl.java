package org.gitgud.application.merge;

import org.gitgud.application.WorkingAreaController;
import org.gitgud.model.Model;
import org.gitgud.model.merge.MergeParamBuilder;
import org.gitgud.model.utils.CommandStatus;
import org.gitgud.utils.ResourceType;
import org.gitgud.utils.Utils;

/**
 * The merge controller implementation.
 */
public class MergeControllerImpl implements MergeController {

    private final Model model;
    private final WorkingAreaController parent;

    /**
     * @param parent
     *            - the parent controller
     * @param model
     *            - the application model
     */
    public MergeControllerImpl(final WorkingAreaController parent, final Model model) {
        this.parent = parent;
        this.model = model;
    }

    @Override
    public void merge(final String branchName) {
        final MergeParamBuilder param = MergeParamBuilder.createMergeParamBuilder();
        param.mergeAlways(true).message("test_commit_message").progressListener(parent.startTaskProgressMode("merge"))
                .addRef(branchName);

        final CommandStatus status = model.getMergeModel().merge(param.build());
        parent.stopTaskProgressMode();
        if (status != CommandStatus.ERROR) {
            parent.showInfoNotification(Utils.resolveString(ResourceType.MESSAGES, "remote.fetch.title"),
                    Utils.replacePattern(Utils.resolveString(ResourceType.MESSAGES, "remote.fetch.description"),
                            "remote", "master"));
        } else {
            parent.showErrorNotification(Utils.resolveString(ResourceType.MESSAGES, "remote.fetch.title"),
                    model.getRemoteModel().getActions().getError().getErrorKey());
        }
    }

}
