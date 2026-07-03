package org.gitgud.model.repository;

import java.io.File;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.InitCommand;
import org.eclipse.jgit.lib.StoredConfig;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.gitgud.events.repository.RepositoryChangedListener;
import org.gitgud.events.repository.RepositoryUpdatedListener;
import org.gitgud.model.Model;
import org.gitgud.model.WritableRepositoryContainer;
import org.gitgud.model.services.AutoFetcher;
import org.gitgud.model.services.Fetcher;
import org.gitgud.model.services.RepositoryWatcher;
import org.gitgud.model.services.Watcher;
import org.gitgud.model.utils.CommandStatus;
import org.gitgud.model.utils.ProgressMonitorAdapter;
import org.gitgud.utils.Pair;
import org.gitgud.utils.Utils;

/**
 *
 */
public class RepositoryModelImpl implements RepositoryModel {

    private static final String GIT_DIR = ".git";
    private static final String SUBSECTION_EMAIL = "email";
    private static final String SUBSECTION_NAME = "name";
    private static final String SECTION_USER = "user";

    private final Model model;
    private final WritableRepositoryContainer container;
    private Optional<Git> repository = Optional.empty();
    private Optional<RepositoryError> error = Optional.empty();

    private final List<RepositoryChangedListener> changedListeners = new LinkedList<>();
    private final List<RepositoryUpdatedListener> updatedListeners = new LinkedList<>();

    private Optional<Watcher> repositoryWatcher = Optional.empty();
    private Optional<Fetcher> repositoryFetcher = Optional.empty();

    /**
     * @param model
     *            the application model
     * @param repo
     *            the WritableRepositoryContainer
     */
    public RepositoryModelImpl(final Model model, final WritableRepositoryContainer repo) {
        this.model = model;
        container = repo;
    }

    @Override
    public void addRepositoryChangedListener(final RepositoryChangedListener rceh) {
        Objects.requireNonNull(rceh);
        changedListeners.add(rceh);
    }

    @Override
    public void addRepositoryUpdatedListener(final RepositoryUpdatedListener rul) {
        Objects.requireNonNull(rul);
        updatedListeners.add(rul);

        if (repositoryWatcher.isPresent()) {
            repositoryWatcher.get().addRepositoryUpdatedListener(rul);
        }
    }

    @Override
    public CommandStatus cloneRepository(final CloneParam cloneParam) {
        Objects.requireNonNull(cloneParam);

        final CloneCommand cc = Git.cloneRepository();
        cc.setDirectory(cloneParam.getDirectory());
        cc.setURI(cloneParam.getRemote());

        Optional<CredentialsProvider> credentialsProvider = Optional.empty();
        if (cloneParam.getUsername().isPresent() && cloneParam.getPassword().isPresent()) {
            credentialsProvider = Optional.of(new UsernamePasswordCredentialsProvider(cloneParam.getUsername().get(),
                    cloneParam.getPassword().get()));
            cc.setCredentialsProvider(credentialsProvider.get());
        }

        if (cloneParam.getProgressListener().isPresent()) {
            cc.setProgressMonitor(
                    new ProgressMonitorAdapter(cloneParam.getProgressListener().get(), CloneTask.UNKNOWN_TASK));
        }

        try {
            final Git newRepository = cc.call();
            updateRepository(newRepository);
        } catch (final Exception e) {
            error = Optional.of(RepositoryError.getByJgitMessage(e.getMessage()));
            return CommandStatus.ERROR;
        }

        if (credentialsProvider.isPresent()) {
            container.setCredentialsProvider(credentialsProvider.get());
        }

        return CommandStatus.SUCCESS;
    }

    @Override
    public void closeRepository() {
        if (!repository.isPresent()) {
            throw new IllegalStateException("No repository to close");
        }

        if (repositoryWatcher.isPresent()) {
            repositoryWatcher.get().stopWatching();
        }

        if (repositoryFetcher.isPresent()) {
            repositoryFetcher.get().stopFetching();
        }

        repository.get().close();
        repository = null;
        error = Optional.empty();
    }

    @Override
    public int getAutoFetchInterval() {
        if (repositoryFetcher.isPresent()) {
            return repositoryFetcher.get().getInterval();
        }
        return 0;
    }

    @Override
    public RepositoryError getError() {
        if (!error.isPresent()) {
            throw new IllegalStateException("The last operation is successful");
        }

        return error.get();
    }

    @Override
    public Optional<Pair<String, String>> getGlobalIdentity() {
        final StoredConfig config = repository.get().getRepository().getConfig();
        final String name = config.getString(SECTION_USER, null, SUBSECTION_NAME);
        final String email = config.getString(SECTION_USER, null, SUBSECTION_EMAIL);

        if (name == null || email == null || name.isEmpty() || email.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(new Pair<>(name, email));
        }
    }

    @Override
    public String getName() {
        if (!repository.isPresent()) {
            throw new IllegalStateException("No repository opened");
        }

        return repository.get().getRepository().getDirectory().getParentFile().getName();
    }

    @Override
    public boolean hasRepositoryOpened() {
        return repository.isPresent();
    }

    @Override
    public CommandStatus initRepository(final InitParam initParam) {
        Objects.requireNonNull(initParam);

        if (new File(initParam.getDirectory().getAbsolutePath() + Utils.getSystemSeparator() + GIT_DIR).isDirectory()) {
            error = Optional.of(RepositoryError.PATH_ALREADY_EXISTS);
            return CommandStatus.ERROR;
        }

        final InitCommand ic = Git.init();
        ic.setDirectory(initParam.getDirectory());

        try {
            final Git newRepository = ic.call();
            updateRepository(newRepository);
        } catch (final Exception e) {
            error = Optional.of(RepositoryError.getByJgitMessage(e.getMessage()));
            return CommandStatus.ERROR;
        }

        return CommandStatus.SUCCESS;
    }

    @Override
    public CommandStatus openRepository(final OpenParam openParam) {
        Objects.requireNonNull(openParam);

        try {
            final Git newRepository = Git.open(openParam.getDirectory());
            if (!newRepository.getRepository().getDirectory().getAbsolutePath()
                    .equals(openParam.getDirectory().getAbsolutePath() + Utils.getSystemSeparator() + GIT_DIR)) {
                throw new Exception("repository not found");
            }
            updateRepository(newRepository);
        } catch (final Exception e) {
            error = Optional.of(RepositoryError.getByJgitMessage(e.getMessage()));
            return CommandStatus.ERROR;
        }

        return CommandStatus.SUCCESS;
    }

    @Override
    public void sendManualRepositoryUpdate() {
        updatedListeners.forEach(listener -> listener.onManualUpdated());
    }

    @Override
    public CommandStatus setAutoFetchInterval(final int interval) {
        if (repositoryFetcher.isPresent()) {
            repositoryFetcher.get().setInterval(interval);
            return CommandStatus.SUCCESS;
        }
        return CommandStatus.ERROR;
    }

    @Override
    public CommandStatus setCredentials(final String username, final String password) {
        container.setCredentialsProvider(new UsernamePasswordCredentialsProvider(username, password));
        return CommandStatus.SUCCESS;
    }

    @Override
    public CommandStatus setGlobalIdentiry(final String name, final String email) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(email);

        final StoredConfig config = repository.get().getRepository().getConfig();
        config.setString(SECTION_USER, null, SUBSECTION_NAME, name);
        config.setString(SECTION_USER, null, SUBSECTION_EMAIL, email);

        try {
            config.save();
            return CommandStatus.SUCCESS;
        } catch (final Exception e) {
            error = Optional.of(RepositoryError.getByJgitMessage(e.getMessage()));
            return CommandStatus.ERROR;
        }
    }

    private void updateRepository(final Git newRepository) {
        if (repository.isPresent()) {
            closeRepository();
        }

        repository = Optional.of(newRepository);
        container.setRepository(newRepository);

        if (repositoryWatcher.isPresent()) {
            repositoryWatcher.get().stopWatching();
        }

        if (repositoryFetcher.isPresent()) {
            repositoryFetcher.get().stopFetching();
        }

        final Path path = newRepository.getRepository().getDirectory().toPath().getParent();
        final Watcher watcher = new RepositoryWatcher(path);
        watcher.startWatching();
        updatedListeners.forEach(listener -> watcher.addRepositoryUpdatedListener(listener));
        repositoryWatcher = Optional.of(watcher);

        final Fetcher fetcher = new AutoFetcher(model);
        fetcher.startFetching();
        repositoryFetcher = Optional.of(fetcher);

        changedListeners.forEach(listener -> listener.onRepositoryChanged());
    }

    /*
     * private void removeFolder(final File folder) { try { FileUtils.delete(folder, FileUtils.RECURSIVE); } catch
     * (Exception e) { Core.getInstance().sendMessage(MessageType.ERROR, "Error while deleting directory."); } }
     */

}
