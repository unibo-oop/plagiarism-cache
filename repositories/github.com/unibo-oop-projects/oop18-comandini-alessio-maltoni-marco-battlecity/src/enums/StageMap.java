package enums;

/**
 * Enumeration for each stage map.
 */
public enum StageMap {

    /**
     * Blocks for the level 1.
     */
    BLOCKS_LEVEL_01(1, "blocks_level_01.txt"),
    /**
     * Blocks for the level 2.
     */
    BLOCKS_LEVEL_02(2, "blocks_level_02.txt"),
    /**
     * Blocks for the level 3.
     */
    BLOCKS_LEVEL_03(3, "blocks_level_03.txt"),
    /**
     * Blocks for the level 4.
     */
    BLOCKS_LEVEL_04(4, "blocks_level_04.txt"),
    /**
     * Blocks for the level 5.
     */
    BLOCKS_LEVEL_05(5, "blocks_level_05.txt"),
    /**
     * Blocks for the level 6.
     */
    BLOCKS_LEVEL_06(6, "blocks_level_06.txt"),
    /**
     * Blocks for the level 7.
     */
    BLOCKS_LEVEL_07(7, "blocks_level_07.txt"),
    /**
     * Blocks for the level 8.
     */
    BLOCKS_LEVEL_08(8, "blocks_level_08.txt"),
    /**
     * Blocks for the level 9.
     */
    BLOCKS_LEVEL_09(9, "blocks_level_09.txt"),
    /**
     * Blocks for the level 10.
     */
    BLOCKS_LEVEL_10(10, "blocks_level_10.txt"),
    /**
     * Blocks for the level 11.
     */
    BLOCKS_LEVEL_11(11, "blocks_level_11.txt"),
    /**
     * Blocks for the level 12.
     */
    BLOCKS_LEVEL_12(12, "blocks_level_12.txt"),
    /**
     * Blocks for the level 13.
     */
    BLOCKS_LEVEL_13(13, "blocks_level_13.txt"),
    /**
     * Blocks for the level 14.
     */
    BLOCKS_LEVEL_14(14, "blocks_level_14.txt"),
    /**
     * Blocks for the level 15.
     */
    BLOCKS_LEVEL_15(15, "blocks_level_15.txt"),
    /**
     * Blocks for the level 16.
     */
    BLOCKS_LEVEL_16(16, "blocks_level_16.txt"),
    /**
     * Blocks for the level 17.
     */
    BLOCKS_LEVEL_17(17, "blocks_level_17.txt"),
    /**
     * Blocks for the level 18.
     */
    BLOCKS_LEVEL_18(18, "blocks_level_18.txt"),
    /**
     * Blocks for the level 19.
     */
    BLOCKS_LEVEL_19(19, "blocks_level_19.txt"),
    /**
     * Blocks for the level 20.
     */
    BLOCKS_LEVEL_20(20, "blocks_level_20.txt"),
    /**
     * Blocks for the level 21.
     */
    BLOCKS_LEVEL_21(21, "blocks_level_21.txt"),
    /**
     * Blocks for the level 22.
     */
    BLOCKS_LEVEL_22(22, "blocks_level_22.txt"),
    /**
     * Blocks for the level 23.
     */
    BLOCKS_LEVEL_23(23, "blocks_level_23.txt"),
    /**
     * Blocks for the level 24.
     */
    BLOCKS_LEVEL_24(24, "blocks_level_24.txt"),
    /**
     * Blocks for the level 25.
     */
    BLOCKS_LEVEL_25(25, "blocks_level_25.txt"),
    /**
     * Blocks for the level 26.
     */
    BLOCKS_LEVEL_26(26, "blocks_level_26.txt"),
    /**
     * Blocks for the level 27.
     */
    BLOCKS_LEVEL_27(27, "blocks_level_27.txt"),
    /**
     * Blocks for the level 28.
     */
    BLOCKS_LEVEL_28(28, "blocks_level_28.txt"),
    /**
     * Blocks for the level 29.
     */
    BLOCKS_LEVEL_29(29, "blocks_level_29.txt"),
    /**
     * Blocks for the level 30.
     */
    BLOCKS_LEVEL_30(30, "blocks_level_30.txt"),
    /**
     * Blocks for the level 31.
     */
    BLOCKS_LEVEL_31(31, "blocks_level_31.txt"),
    /**
     * Blocks for the level 32.
     */
    BLOCKS_LEVEL_32(32, "blocks_level_32.txt"),
    /**
     * Blocks for the level 33.
     */
    BLOCKS_LEVEL_33(33, "blocks_level_33.txt"),
    /**
     * Blocks for the level 34.
     */
    BLOCKS_LEVEL_34(34, "blocks_level_34.txt"),
    /**
     * Blocks for the level 35.
     */
    BLOCKS_LEVEL_35(35, "blocks_level_35.txt");

    // The path of the file on disk.
    private String path;
    // The directory which the file is stored.
    private static final String DIR = "blocks_lists";
    private int stageNumber;

    /*
     * Constructor of the enumeration.
     * 
     * @param stageNumber the number of the stage of which the map is referred.
     * @param fileName    the name which the file is stored on the disk with
     *                    extension.
     */
    StageMap(final int stageNumber, final String fileName) {
        this.stageNumber = stageNumber;
        path = "/" + DIR + "/" + fileName;
    }

    /**
     * Getter method of the path of the File.
     * 
     * @return the path of the file on disk.
     */
    public String getPath() {
        return path;
    }

    /**
     * Method that returns the number of the stage of which the map is referred.
     * 
     * @return the number of the stage as integer.
     */
    public int getStageNumber() {
        return this.stageNumber;
    }
}
