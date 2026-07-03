package org.gitgud.model.branch;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.gitgud.model.utils.CommandStatus;

/**
 *
 *
 */
public interface BranchModel {

    /**
     * @param branch
     *            the branch you want to checkout, performs the checkout action towards a existing local branch.
     * @return The CommandStatus message.
     */
    CommandStatus checkoutToLocal(String branch);

    /**
     * @param branch
     *            the branch you want to checkout, performs the checkout action towards a existing remote branch.
     * @return The CommandStatus message.
     */
    CommandStatus checkoutToRemote(String branch);

    /**
     * @param branch
     *            the name of the new branch.
     * @param startPoint
     *            the id of the start point of the ne branch.
     * @return The CommandStatus message.
     */
    CommandStatus createBranch(String branch, String startPoint);

    /**
     * @param branchnames
     *            the list of all branches you want to delete.
     * @return The CommandStatus message.
     */
    CommandStatus deleteBranch(List<String> branchnames);

    /**
     * @return The name of the current checked out branch.
     */
    String getCheckedOutBranch();

    /**
     * @return The last BranchError registered.
     */
    BranchError getError();

    /**
     * @return A simple Set with the names of the existing local branches.
     */
    Set<String> getLocalBranches();

    /**
     * @return A LogManager used to walk through the Git commit tree.
     */
    LogManager getLogManager();

    /**
     * @return A Map, keys are the remote repositories, values are collections of full branch names.
     */
    Map<String, Collection<String>> getRemoteBranches();

    /**
     * @param branch
     *            the branch you want to rename.
     * @param newName
     *            the new name (must be the full name version).
     * @return The CommandStatus message.
     */
    CommandStatus renameBranch(String branch, String newName);
}
