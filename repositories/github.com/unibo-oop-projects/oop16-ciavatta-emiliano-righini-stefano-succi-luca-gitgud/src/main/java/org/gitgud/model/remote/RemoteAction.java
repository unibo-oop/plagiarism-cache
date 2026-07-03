package org.gitgud.model.remote;

import java.util.List;

import org.gitgud.model.remote.fetch.FetchOutput;
import org.gitgud.model.remote.fetch.FetchParam;
import org.gitgud.model.remote.pull.PullParam;
import org.gitgud.model.remote.push.PushParam;
import org.gitgud.model.utils.CommandStatus;

/**
 * Perform the git remote actions.
 */
public interface RemoteAction {

    /**
     * Execute the git fetch operation.
     *
     * @param param
     *            a FetchParam with the arguments of git fetch
     * @return the CommandStatus of the operation
     */
    CommandStatus fetch(FetchParam param);

    /**
     * @return the remote error
     */
    RemoteError getError();

    /**
     * Return the results after a fetch operation.
     *
     * @return a list of fetch results
     */
    List<FetchOutput> getFetchOutput();

    /**
     * Execute the git pull operation.
     *
     * @param param
     *            a PullParam with the arguments of git pull
     * @return the CommandStatus of the operation
     */
    CommandStatus pull(PullParam param);

    /**
     * @param param
     *            a PushParam with the arguments of git push
     * @return the CommandStatus of the operation
     */
    CommandStatus push(PushParam param);

}
