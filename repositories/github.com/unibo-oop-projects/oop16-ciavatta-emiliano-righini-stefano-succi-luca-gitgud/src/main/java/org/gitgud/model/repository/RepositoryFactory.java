package org.gitgud.model.repository;

import java.io.File;
import java.util.Optional;

import org.gitgud.events.ProgressListener;
import org.gitgud.model.utils.Builder;

/**
 *
 */
public final class RepositoryFactory {

    private RepositoryFactory() {
    }

    /**
     * @return a new clone builder
     */
    public static CloneBuilder createCloneBuilder() {
        return new CloneBuilder() {

            private String reqDirectory;
            private String reqUrl;
            private Optional<String> optUsername = Optional.empty();
            private Optional<String> optPassword = Optional.empty();
            private Optional<ProgressListener> optProgressListener = Optional.empty();

            @Override
            public CloneParam build() {
                if (reqDirectory == null) {
                    throw new IllegalStateException("directory must be set");
                } else if (reqUrl == null) {
                    throw new IllegalStateException("remote must be set");
                }

                return new CloneParam() {

                    private File directory;

                    @Override
                    public File getDirectory() {
                        if (directory == null) {
                            directory = new File(reqDirectory);
                        }
                        return directory;
                    }

                    @Override
                    public Optional<String> getPassword() {
                        return optPassword;
                    }

                    @Override
                    public Optional<ProgressListener> getProgressListener() {
                        return optProgressListener;
                    }

                    @Override
                    public String getRemote() {
                        return reqUrl;
                    }

                    @Override
                    public Optional<String> getUsername() {
                        return optUsername;
                    }

                };
            }

            @Override
            public CloneBuilder directory(final String directory) {
                reqDirectory = directory;
                return this;
            }

            @Override
            public CloneBuilder password(final String password) {
                optPassword = Optional.ofNullable(password);
                return this;
            }

            @Override
            public CloneBuilder progressListener(final ProgressListener progressListener) {
                optProgressListener = Optional.ofNullable(progressListener);
                return this;
            }

            @Override
            public CloneBuilder remote(final String url) {
                reqUrl = url;
                return this;
            }

            @Override
            public CloneBuilder username(final String username) {
                optUsername = Optional.ofNullable(username);
                return this;
            }

        };
    }

    /**
     * @return a new init builder
     */
    public static InitBuilder createInitBuilder() {
        return new InitBuilder() {

            private String reqDirectory;
            private Optional<GitLicense> optGitLicense = Optional.empty();
            private Optional<Gitignore> optGitIgnore = Optional.empty();

            @Override
            public InitParam build() {
                if (reqDirectory == null) {
                    throw new IllegalStateException("directory must be set");
                }

                return new InitParam() {

                    private File directory;

                    @Override
                    public File getDirectory() {
                        if (directory == null) {
                            directory = new File(reqDirectory);
                        }
                        return directory;
                    }

                    @Override
                    public Optional<Gitignore> getGitIgnore() {
                        return optGitIgnore;
                    }

                    @Override
                    public Optional<GitLicense> getLicense() {
                        return optGitLicense;
                    }
                };
            }

            @Override
            public InitBuilder directory(final String directory) {
                reqDirectory = directory;
                return this;
            }

            @Override
            public InitBuilder gitIgnore(final Gitignore gitIgnore) {
                optGitIgnore = Optional.ofNullable(gitIgnore);
                return this;
            }

            @Override
            public InitBuilder gitLicense(final GitLicense gitLicense) {
                optGitLicense = Optional.ofNullable(gitLicense);
                return this;
            }
        };
    }

    /**
     * @return a new open builder
     */
    public static OpenBuilder createOpenBuilder() {
        return new OpenBuilder() {

            private String reqDirectory;

            @Override
            public OpenParam build() {
                if (reqDirectory == null) {
                    throw new IllegalStateException("directory must be set");
                }

                return new OpenParam() {

                    private File directory;

                    @Override
                    public File getDirectory() {
                        if (directory == null) {
                            directory = new File(reqDirectory);
                        }
                        return directory;
                    }
                };
            }

            @Override
            public OpenBuilder directory(final String directory) {
                reqDirectory = directory;
                return this;
            }

        };
    }

    /**
     *
     */
    public interface CloneBuilder extends Builder<CloneParam> {

        /**
         * @param directory
         *            the directory
         * @return this
         */
        CloneBuilder directory(String directory);

        /**
         * @param password
         *            the password
         * @return this
         */
        CloneBuilder password(String password);

        /**
         * @param progressListener
         *            the progress listener
         * @return this
         */
        CloneBuilder progressListener(ProgressListener progressListener);

        /**
         * @param url
         *            the remote url
         * @return this
         */
        CloneBuilder remote(String url);

        /**
         * @param username
         *            the username
         * @return this
         */
        CloneBuilder username(String username);

    }

    /**
     *
     */
    public interface InitBuilder extends Builder<InitParam> {

        /**
         * @param directory
         *            the directory
         * @return this
         */
        InitBuilder directory(String directory);

        /**
         * @param gitIgnore
         *            the gitignore
         * @return this
         */
        InitBuilder gitIgnore(Gitignore gitIgnore);

        /**
         * @param gitLicense
         *            the gitlicense
         * @return this
         */
        InitBuilder gitLicense(GitLicense gitLicense);

    }

    /**
     *
     */
    public interface OpenBuilder extends Builder<OpenParam> {

        /**
         * @param directory
         *            the directory
         * @return this
         */
        OpenBuilder directory(String directory);

    }

}
