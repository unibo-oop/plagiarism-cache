package org.gitgud.model.tag;

import java.util.List;
import java.util.stream.Collectors;

import org.gitgud.exceptions.GitGudUnckeckedException;
import org.gitgud.model.RepositoryContainer;
import org.gitgud.model.utils.CommandStatus;
import org.gitgud.model.utils.RepositoryUtils;

/**
 *
 */
public class TagModelImpl implements TagModel {

    private final RepositoryContainer repo;

    /**
     * @param repo
     *            - the repository container instance
     */
    public TagModelImpl(final RepositoryContainer repo) {
        this.repo = repo;
    }

    @Override
    public CommandStatus addTag(final String message, final String start) {
        throw new UnsupportedOperationException();
    }

    @Override
    public CommandStatus deleteTag() {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String> getTags() {
        try {
            return RepositoryUtils.getRepo(repo).tagList().call().stream()
                    .map(ref -> ref.getName())
                    .collect(Collectors.toList());
        } catch (final Exception e) {
            throw new GitGudUnckeckedException("Can't retrieve tags: " + e.getMessage());
        }
    }

}
