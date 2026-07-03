package org.gitgud.model.utils;

import java.util.Arrays;

import org.eclipse.jgit.diff.DiffEntry;

/**
 *
 */
public enum ChangeType {

    /**
     * Add a new file to the project.
     */
    ADDED(DiffEntry.ChangeType.ADD, "changetype.added"),
    /**
     * Copy an existing file to a new location, keeping the original.
     */
    COPIED(DiffEntry.ChangeType.COPY, "changetype.copied"),
    /**
     * Delete an existing file from the project.
     */
    DELETED(DiffEntry.ChangeType.DELETE, "changetype.deleted"),
    /**
     * Modify an existing file in the project (content and/or mode).
     */
    MODIFIED(DiffEntry.ChangeType.MODIFY, "changetype.modified"),
    /**
     * Rename an existing file to a new location.
     */
    RENAMED(DiffEntry.ChangeType.RENAME, "changetype.renamed");

    private DiffEntry.ChangeType jgitChangeType;
    private String labelKey;

    ChangeType(final DiffEntry.ChangeType jgitChangeType, final String labelKey) {
        this.jgitChangeType = jgitChangeType;
        this.labelKey = labelKey;
    }

    /**
     * @param jgitChangeType
     *            the jgit library change type
     * @return the change type remapped
     */
    public static ChangeType getByJgitChangeType(final DiffEntry.ChangeType jgitChangeType) {
        return Arrays.stream(ChangeType.values())
                .filter(ct -> jgitChangeType == ct.jgitChangeType)
                .findAny()
                .get();
    }

    /**
     * @return the resource bundle string key associated with the change type
     */
    public String getLabelKey() {
        return labelKey;
    }

}
