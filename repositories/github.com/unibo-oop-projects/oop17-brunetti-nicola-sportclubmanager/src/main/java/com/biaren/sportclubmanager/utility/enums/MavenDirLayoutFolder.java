package com.biaren.sportclubmanager.utility.enums;

/**
 * Constants of directory names.
 * @author nbrunetti
 *
 */
public enum MavenDirLayoutFolder {
    SRC_DIR("src"),
    MAIN_DIR("main"),
    RESOURCES_DIR("resources"),
    JAVA_DIR("java"),
    JSON_DIR("json"),
    PDF_DIR("pdf"),
    LOGO_DIR("logo"),
    CSV_DIR("csv");
    
    private String actualName;
    
    private MavenDirLayoutFolder (String name) {
        this.actualName = name;
    }

    /**
     * Folder name
     */
    public String toString() {
        return this.actualName;
    }
}
