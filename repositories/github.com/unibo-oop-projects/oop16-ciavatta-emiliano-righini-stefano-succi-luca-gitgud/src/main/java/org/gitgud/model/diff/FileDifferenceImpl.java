package org.gitgud.model.diff;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.gitgud.model.utils.ChangeType;

class FileDifferenceImpl implements FileDifference {

    private String oldFile;
    private String newFile;
    private ChangeType changeType;
    private Optional<Integer> similarityScore = Optional.empty();
    private PatchType patchType;
    private String preImageShortHash;
    private String postImageShortHash;
    private final List<Hunk> hunks = new ArrayList<>();
    private Optional<DiffBinaryManager> oldFileDiffBinaryManager = Optional.empty();
    private Optional<DiffBinaryManager> newFileDiffBinaryManager = Optional.empty();

    @Override
    public ChangeType getChangeType() {
        return changeType;
    }

    @Override
    public List<Hunk> getHunks() {
        return Collections.unmodifiableList(hunks);
    }

    @Override
    public String getNewFile() {
        return newFile;
    }

    @Override
    public Optional<DiffBinaryManager> getNewFileDiffBinaryManager() {
        return newFileDiffBinaryManager;
    }

    @Override
    public String getOldFile() {
        return oldFile;
    }

    @Override
    public Optional<DiffBinaryManager> getOldFileDiffBinaryManager() {
        return oldFileDiffBinaryManager;
    }

    @Override
    public PatchType getPatchType() {
        return patchType;
    }

    @Override
    public String getPostImageShortHash() {
        return postImageShortHash;
    }

    @Override
    public String getPreImageShortHash() {
        return preImageShortHash;
    }

    @Override
    public Optional<Integer> getSimilarityScore() {
        return similarityScore;
    }

    @Override
    public String toString() {
        return "FileDifference [oldFile=" + oldFile + ", newFile=" + newFile + ", changeType=" + changeType
                + ", similarityScore=" + similarityScore + ", patchType=" + patchType
                + ", preImageShortHash=" + preImageShortHash + ", postImageShortHash=" + postImageShortHash + ", hunks="
                + hunks + "]";
    }

    /**
     * @param hunk
     *            the hunk to add
     */
    void addHunk(final Hunk hunk) { // NOPMD - Package private
        hunks.add(hunk);
    }

    /**
     * @param changeType
     *            the ChangeType to set
     */
    void setChangeType(final ChangeType changeType) { // NOPMD - Package private
        this.changeType = changeType;
    }

    /**
     * @param newFile
     *            the newFile to set
     */
    void setNewFile(final String newFile) { // NOPMD - Package private
        this.newFile = newFile;
    }

    /**
     * @param newFileDiffBinaryManager
     *            - the DiffBinaryManager to set
     */
    void setNewFileDiffBinaryManager(final DiffBinaryManager newFileDiffBinaryManager) { // NOPMD - Package private
        this.newFileDiffBinaryManager = Optional.ofNullable(newFileDiffBinaryManager);
    }

    /**
     * @param oldFile
     *            - the oldFile to set
     */
    void setOldFile(final String oldFile) { // NOPMD - Package private
        this.oldFile = oldFile;
    }

    /**
     * @param oldFileDiffBinaryManager
     *            - the DiffBinaryManager to set
     */
    void setOldFileDiffBinaryManager(final DiffBinaryManager oldFileDiffBinaryManager) { // NOPMD - Package private
        this.oldFileDiffBinaryManager = Optional.ofNullable(oldFileDiffBinaryManager);
    }

    /**
     * @param patchType
     *            - the PatchType to set
     */
    void setPatchType(final PatchType patchType) { // NOPMD - Package private
        this.patchType = patchType;
    }

    /**
     * @param postImageShortHash
     *            the postImageShortHash to set
     */
    void setPostImageShortHash(final String postImageShortHash) { // NOPMD - Package private
        this.postImageShortHash = postImageShortHash;
    }

    /**
     * @param preImageShortHash
     *            the preImageShortHash to set
     */
    void setPreImageShortHash(final String preImageShortHash) { // NOPMD - Package private
        this.preImageShortHash = preImageShortHash;
    }

    /**
     * @param similarityScore
     *            the similarityScore to set
     */
    void setSimilarityScore(final int similarityScore) { // NOPMD - Package private
        this.similarityScore = Optional.ofNullable(similarityScore);
    }

    class HunkImpl implements Hunk {

        private Optional<String> header;
        private int fromFileRangeStartLine;
        private int fromFileRangeLinesNumber;
        private int toFileRangeStartLine;
        private int toFileRangeLinesNumber;
        private final List<String> lines = new ArrayList<>();

        @Override
        public int getFromFileRangeLinesNumber() {
            return fromFileRangeLinesNumber;
        }

        @Override
        public int getFromFileRangeStartLine() {
            return fromFileRangeStartLine;
        }

        @Override
        public Optional<String> getHeader() {
            return header;
        }

        @Override
        public List<String> getLines() {
            return Collections.unmodifiableList(lines);
        }

        @Override
        public int getToFileRangeLinesNumber() {
            return toFileRangeLinesNumber;
        }

        @Override
        public int getToFileRangeStartLine() {
            return toFileRangeStartLine;
        }

        /**
         * @param header
         *            the header to set
         */
        public void setHeader(final String header) {
            this.header = Optional.ofNullable(header);
        }

        @Override
        public String toString() {
            return "Hunk [header=" + header + ", fromFileRangeStartLine=" + fromFileRangeStartLine
                    + ", fromFileRangeLinesNumber=" + fromFileRangeLinesNumber
                    + ", toFileRangeStartLine=" + toFileRangeStartLine + ", toFileRangeLinesNumber="
                    + toFileRangeLinesNumber + ", lines=" + lines + "]";
        }

        /**
         * @param line
         *            the line to add
         */
        void addLine(final String line) { // NOPMD - Package private
            lines.add(line);
        }

        /**
         * @param fromFileRangeLinesNumber
         *            the fromFileRangeLinesNumber to set
         */
        void setFromFileRangeLinesNumber(final int fromFileRangeLinesNumber) { // NOPMD - Package private
            this.fromFileRangeLinesNumber = fromFileRangeLinesNumber;
        }

        /**
         * @param fromFileRangeStartLine
         *            the fromFileRangeStartLine to set
         */
        void setFromFileRangeStartLine(final int fromFileRangeStartLine) { // NOPMD - Package private
            this.fromFileRangeStartLine = fromFileRangeStartLine;
        }

        /**
         * @param toFileRangeLinesNumber
         *            the toFileRangeLinesNumber to set
         */
        void setToFileRangeLinesNumber(final int toFileRangeLinesNumber) { // NOPMD - Package private
            this.toFileRangeLinesNumber = toFileRangeLinesNumber;
        }

        /**
         * @param toFileRangeStartLine
         *            the toFileRangeStartLine to set
         */
        void setToFileRangeStartLine(final int toFileRangeStartLine) { // NOPMD - Package private
            this.toFileRangeStartLine = toFileRangeStartLine;
        }

    }

}
