package org.gitgud.model.merge;

import org.gitgud.model.utils.CommandStatus;

/**
 *
 */
public interface MergeModel {

    /**
     * @return the merge error
     */
    MergeError getError();

    /**
     * @param param
     *            the merge param
     * @return the command status of the operation
     */
    CommandStatus merge(MergeParam param);

}
