package org.gitgud.model.commons;

import java.util.Optional;

import org.gitgud.exceptions.GitGudUnckeckedException;
import org.gitgud.model.utils.Builder;

/**
 * A factory to get the builder of an Author.
 */
public final class AuthorFactory {

    /**
     * The builder of an Author instance.
     */
    public interface AuthorProfiler extends Builder<Author> {

        /**
         * @param mail
         *            the e-mail of the author.
         * @return The AuthorProfiler.
         */
        AuthorProfiler setMail(String mail);

        /**
         * @param name
         *            the complete name of the author.
         * @return The AuthorProfiler.
         */
        AuthorProfiler setName(String name);

    }

    /**
     * @return An AuthorProfiler instance.
     */
    public static AuthorProfiler getAuthorProfiler() {
        return new AuthorProfiler() {

            private Optional<String> name;
            private Optional<String> mail;

            @Override
            public Author build() {
                validate();
                return new Author() {

                    @Override
                    public String getMail() {
                        return mail.get();
                    }

                    @Override
                    public String getName() {
                        return name.get();
                    }

                };
            }

            @Override
            public AuthorProfiler setMail(final String mail) {
                this.mail = Optional.ofNullable(mail);
                return this;
            }

            @Override
            public AuthorProfiler setName(final String name) {
                this.name = Optional.ofNullable(name);
                return this;
            }

            private void validate() {
                if (!name.isPresent() || !mail.isPresent()) {
                    throw new GitGudUnckeckedException("AuthorProfiler was not ready to build an Author!");
                }
            }

        };

    }

    private AuthorFactory() {
    }
}
