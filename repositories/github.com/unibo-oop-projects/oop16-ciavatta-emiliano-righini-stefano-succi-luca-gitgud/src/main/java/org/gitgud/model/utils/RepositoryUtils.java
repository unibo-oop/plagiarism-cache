package org.gitgud.model.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.lib.Constants;
import org.gitgud.exceptions.NoRepositoryException;
import org.gitgud.model.RepositoryContainer;

/**
 * Static class with some utility methods used within git repository.
 */
public final class RepositoryUtils {

    private static final Pattern LOCAL_BRANCH_PATTERN = Pattern.compile("^refs\\/heads\\/(.*)$");
    private static final Pattern REMOTE_BRANCH_PATTERN = Pattern.compile("^refs\\/remotes\\/(.*?)\\/(.*)$");
    private static final Pattern TAG_PATTERN = Pattern.compile("^refs\\/tags\\/(.*)$");

    private RepositoryUtils() {
    }

    /**
     * Format a ref for a git local branch.
     *
     * @param ref
     *            the ref string of the local branch to parse
     * @param onlyName
     *            true to obtain only the branch name
     * @return the ref string formatted
     */
    public static String formatLocalBranchRef(final String ref, final boolean onlyName) {
        final Matcher m = LOCAL_BRANCH_PATTERN.matcher(ref);
        final String branchName;

        if (m.matches()) {
            branchName = m.group(1);
        } else {
            branchName = ref;
        }

        if (onlyName) {
            return branchName;
        } else {
            return "refs/heads/" + branchName;
        }
    }

    /**
     * Format a ref for a git remote branch.
     *
     * @param ref
     *            the ref string of the remote branch to parse
     * @param onlyName
     *            true to obtain only the branch name
     * @return the ref string formatted
     */
    public static String formatRemoteBranchRef(final String ref, final boolean onlyName) {
        final Matcher m = REMOTE_BRANCH_PATTERN.matcher(ref);
        final String remoteName;
        final String branchName;

        if (m.matches()) {
            remoteName = m.group(1);
            branchName = m.group(2);
        } else {
            remoteName = Constants.DEFAULT_REMOTE_NAME;
            branchName = ref;
        }

        if (onlyName) {
            return branchName;
        } else {
            return "refs/remotes/" + remoteName + "/" + branchName;
        }
    }

    /**
     * Format a ref for a git tag.
     *
     * @param ref
     *            the ref string of the tag to parse
     * @param onlyName
     *            true to obtain only the tag name
     * @return the string formatted
     */
    public static String formatTagRef(final String ref, final boolean onlyName) {
        final Matcher m = TAG_PATTERN.matcher(ref);
        final String tagName;

        if (m.matches()) {
            tagName = m.group(1);
        } else {
            tagName = ref;
        }

        if (onlyName) {
            return tagName;
        } else {
            return "refs/tags/" + tagName;
        }
    }

    /**
     * Get the remote name from a git remote ref string.
     *
     * @param ref
     *            the ref string of the remote branch to parse
     * @return the remote name
     */
    public static String getRemoteFromRef(final String ref) {
        final Matcher m = REMOTE_BRANCH_PATTERN.matcher(ref);

        if (m.matches()) {
            return m.group(1);
        }

        throw new IllegalArgumentException("Not a remote ref: " + ref);
    }

    /**
     * Obtain the git repository from a RepositoryContainer. If no repository is present, throw a NoRepositoryException.
     *
     * @param container
     *            the repository container
     * @return the git repository
     */
    public static Git getRepo(final RepositoryContainer container) {
        if (!container.getRepository().isPresent()) {
            throw new NoRepositoryException();
        }

        return container.getRepository().get();
    }

}
