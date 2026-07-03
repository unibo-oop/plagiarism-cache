package org.gitgud.model.commons;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.gitgud.exceptions.GitGudUnckeckedException;
import org.gitgud.model.utils.Builder;

/**
 * A factory used to get a CommitConstructor.
 */
public final class CommitFactory {

    /**
     * The Builder used to assemble a Commit representation.
     */
    public interface CommitConstructor extends Builder<Commit> {

        /**
         * @param parents
         *            the parents of the commit.
         * @return This CommitConstructor.
         */
        CommitConstructor addParents(List<String> parents);

        /**
         * @param author
         *            the author of the commit.
         * @return This CommitConstructor.
         */
        CommitConstructor setAuthor(Author author);

        /**
         * @param date
         *            the date of the commit.
         * @return This CommitConstructor.
         */
        CommitConstructor setDate(Date date);

        /**
         * @param message
         *            the long message of the commit.
         * @return This CommitConstructor.
         */
        CommitConstructor setFullMessage(String message);

        /**
         * @param sha1
         *            the sha ID of the commit.
         * @return This CommitConstructor.
         */
        CommitConstructor setID(String sha1);

        /**
         * @param message
         *            the short message of the commit.
         * @return This CommitConstructor.
         */
        CommitConstructor setShortMessage(String message);
    }

    /**
     * @return A CommitConstructor instance.
     */
    public static CommitConstructor createCommitBuilder() {
        return new CommitConstructor() {

            private final List<String> parents = new LinkedList<>();
            private Optional<String> sha;
            private Optional<String> shtMsg;
            private Optional<String> fullMsg;
            private Optional<Date> date;
            private Optional<Author> author;

            @Override
            public CommitConstructor addParents(final List<String> parents) {
                this.parents.addAll(parents);
                return this;
            }

            @Override
            public Commit build() {
                validate();
                return new Commit() {

                    @Override
                    public Author getAuthor() {
                        return author.get();
                    }

                    @Override
                    public String getFullMessage() {
                        return fullMsg.get();
                    }

                    @Override
                    public String getID() {
                        return sha.get();
                    }

                    @Override
                    public List<String> getParentsId() {
                        return parents;
                    }

                    @Override
                    public String getShortMessage() {
                        return shtMsg.get();
                    }

                    @Override
                    public Date getWhen() {
                        return date.get();
                    }

                    @Override
                    public String toString() {
                        return "Commit : " + sha.get() + " " + "Message: " + shtMsg.get() + " " + "Author: "
                                + author.get().getName();

                    }

                };
            }

            @Override
            public CommitConstructor setAuthor(final Author author) {
                this.author = Optional.ofNullable(author);
                return this;
            }

            @Override
            public CommitConstructor setDate(final Date date) {
                this.date = Optional.ofNullable(date);
                return this;
            }

            @Override
            public CommitConstructor setFullMessage(final String message) {
                fullMsg = Optional.ofNullable(message);
                return this;
            }

            @Override
            public CommitConstructor setID(final String sha1) {
                sha = Optional.ofNullable(sha1);
                return this;
            }

            @Override
            public CommitConstructor setShortMessage(final String message) {
                shtMsg = Optional.ofNullable(message);
                return this;
            }

            private void validate() {
                if (!shtMsg.isPresent() || !sha.isPresent() || !author.isPresent()) {
                    throw new GitGudUnckeckedException("Builder was not ready to create a Commit");
                }
            }

        };
    }

    private CommitFactory() {
    }
}
