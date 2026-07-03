package org.gitgud.model.diff;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.jgit.patch.FileHeader;
import org.gitgud.model.diff.FileDifference.PatchType;
import org.gitgud.model.diff.FileDifferenceImpl.HunkImpl;
import org.gitgud.model.utils.ChangeType;

class DiffStreamOutput extends ByteArrayOutputStream {

    private static final Pattern HUNK_PATTERN = Pattern
            .compile("^@@ -(\\d+)?,?(\\d+)? \\+(\\d+)?,?(\\d+)? @@\\s?(.*)$");
    private static final int HUNK_PATTERN_GROUPS_COUNT = 5;

    private boolean headerParsed;
    private HunkImpl currentHunk;

    public FileDifferenceImpl buildFileDifference(final FileHeader fileHeader) {
        final FileDifferenceImpl fileDifference = new FileDifferenceImpl();

        switch (fileHeader.getChangeType()) {
            case ADD:
                fileDifference.setChangeType(ChangeType.ADDED);
                break;
            case COPY:
                fileDifference.setChangeType(ChangeType.COPIED);
                fileDifference.setSimilarityScore(fileHeader.getScore());
                break;
            case DELETE:
                fileDifference.setChangeType(ChangeType.DELETED);
                break;
            case MODIFY:
                fileDifference.setChangeType(ChangeType.MODIFIED);
                break;
            case RENAME:
                fileDifference.setChangeType(ChangeType.RENAMED);
                fileDifference.setSimilarityScore(fileHeader.getScore());
                break;
            default:
                break;
        }

        switch (fileHeader.getPatchType()) {
            case BINARY:
                fileDifference.setPatchType(PatchType.BINARY);
                break;
            case GIT_BINARY:
                fileDifference.setPatchType(PatchType.GIT_BINARY);
                break;
            case UNIFIED:
                fileDifference.setPatchType(PatchType.TEXT);
                break;
            default:
                break;
        }

        fileDifference.setOldFile(fileHeader.getOldPath());
        fileDifference.setNewFile(fileHeader.getNewPath());
        fileDifference.setPreImageShortHash(fileHeader.getOldId().name());
        fileDifference.setPostImageShortHash(fileHeader.getNewId().name());

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new ByteArrayInputStream(super.toByteArray())))) {

            reader.lines().forEach(line -> {
                if (headerParsed) {
                    parseLine(fileDifference, line);
                }

                if (!headerParsed && line.startsWith("+++")) {
                    headerParsed = true;
                }
            });
        } catch (final Exception e) {
            throw new RuntimeException("Can't read from diff output stream");
        }

        super.reset();

        headerParsed = false;
        currentHunk = null;

        return fileDifference;
    }

    private void parseLine(final FileDifferenceImpl fileDifference, final String line) {
        if (line == null) {
            return;
        } else if (line.startsWith("+") || line.startsWith("-") || line.startsWith(" ")) {
            currentHunk.addLine(line);
        } else if (HUNK_PATTERN.matcher(line).matches()) {
            final Matcher m = HUNK_PATTERN.matcher(line);
            m.find();
            currentHunk = fileDifference.new HunkImpl();
            if (m.group(1) != null && !m.group(1).isEmpty()) {
                currentHunk.setFromFileRangeStartLine(Integer.parseInt(m.group(1)));
            } else {
                currentHunk.setFromFileRangeStartLine(0);
            }
            if (m.group(2) != null && !m.group(1).isEmpty()) {
                currentHunk.setFromFileRangeLinesNumber(Integer.parseInt(m.group(2)));
            } else {
                currentHunk.setFromFileRangeLinesNumber(0);
            }
            if (m.group(3) != null && !m.group(1).isEmpty()) {
                currentHunk.setToFileRangeStartLine(Integer.parseInt(m.group(3)));
            } else {
                currentHunk.setToFileRangeStartLine(0);
            }
            if (m.group(4) != null && !m.group(1).isEmpty()) {
                currentHunk.setToFileRangeLinesNumber(Integer.parseInt(m.group(4)));
            } else {
                currentHunk.setToFileRangeLinesNumber(0);
            }

            if (m.groupCount() == HUNK_PATTERN_GROUPS_COUNT) {
                currentHunk.setHeader(m.group(HUNK_PATTERN_GROUPS_COUNT));
            }
            fileDifference.addHunk(currentHunk);
        }
    }

}
