package org.gitgud.model.diff;

import java.util.List;
import java.util.Optional;

import org.gitgud.model.utils.ChangeType;

/**
 * The output of the calculation of differences of a file between two stages.
 */
public interface FileDifference {

    /**
     * @return the ChangeType of the file
     */
    ChangeType getChangeType();

    /**
     * @return a list of all hunks modified
     */
    List<Hunk> getHunks();

    /**
     * @return the new file name
     */
    String getNewFile();

    /**
     * @return the new file binary manager if the PatchType is a binary
     */
    Optional<DiffBinaryManager> getNewFileDiffBinaryManager();

    /**
     * @return the old file name
     */
    String getOldFile();

    /**
     * @return the old file binary manager if the PatchType is a binary
     */
    Optional<DiffBinaryManager> getOldFileDiffBinaryManager();

    /**
     * @return the file patch type
     */
    PatchType getPatchType();

    /**
     * @return the post image short hash
     */
    String getPostImageShortHash();

    /**
     * @return the pre image short hash
     */
    String getPreImageShortHash();

    /**
     * @return the similarity score when the ChangeType equals to is copied or renamed
     */
    Optional<Integer> getSimilarityScore();

    /**
     *
     */
    interface Hunk {

        int getFromFileRangeLinesNumber();

        int getFromFileRangeStartLine();

        Optional<String> getHeader();

        List<String> getLines();

        int getToFileRangeLinesNumber();

        int getToFileRangeStartLine();
    }

    /**
     * Represents all possibles patch types.
     */
    enum PatchType {
        BINARY, GIT_BINARY, TEXT
    }

}
