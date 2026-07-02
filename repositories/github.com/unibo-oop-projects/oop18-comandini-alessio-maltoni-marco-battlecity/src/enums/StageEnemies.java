package enums;

/**
 * Enumeration for each stage enemies.
 */
public enum StageEnemies {

    /**
     * Enemies for the level 1.
     */
    ENEMIES_LEVEL_01(1, "enemies_level_01.txt"),
    /**
     * Enemies for the level 2.
     */
    ENEMIES_LEVEL_02(2, "enemies_level_02.txt"),
    /**
     * Enemies for the level 3.
     */
    ENEMIES_LEVEL_03(3, "enemies_level_03.txt"),
    /**
     * Enemies for the level 4.
     */
    ENEMIES_LEVEL_04(4, "enemies_level_04.txt"),
    /**
     * Enemies for the level 5.
     */
    ENEMIES_LEVEL_05(5, "enemies_level_05.txt"),
    /**
     * Enemies for the level 6.
     */
    ENEMIES_LEVEL_06(6, "enemies_level_06.txt"),
    /**
     * Enemies for the level 7.
     */
    ENEMIES_LEVEL_07(7, "enemies_level_07.txt"),
    /**
     * Enemies for the level 8.
     */
    ENEMIES_LEVEL_08(8, "enemies_level_08.txt"),
    /**
     * Enemies for the level 9.
     */
    ENEMIES_LEVEL_09(9, "enemies_level_09.txt"),
    /**
     * Enemies for the level 10.
     */
    ENEMIES_LEVEL_10(10, "enemies_level_10.txt"),
    /**
     * Enemies for the level 11.
     */
    ENEMIES_LEVEL_11(11, "enemies_level_11.txt"),
    /**
     * Enemies for the level 12.
     */
    ENEMIES_LEVEL_12(12, "enemies_level_12.txt"),
    /**
     * Enemies for the level 13.
     */
    ENEMIES_LEVEL_13(13, "enemies_level_13.txt"),
    /**
     * Enemies for the level 14.
     */
    ENEMIES_LEVEL_14(14, "enemies_level_14.txt"),
    /**
     * Enemies for the level 15.
     */
    ENEMIES_LEVEL_15(15, "enemies_level_15.txt"),
    /**
     * Enemies for the level 16.
     */
    ENEMIES_LEVEL_16(16, "enemies_level_16.txt"),
    /**
     * Enemies for the level 17.
     */
    ENEMIES_LEVEL_17(17, "enemies_level_17.txt"),
    /**
     * Enemies for the level 18.
     */
    ENEMIES_LEVEL_18(18, "enemies_level_18.txt"),
    /**
     * Enemies for the level 19.
     */
    ENEMIES_LEVEL_19(19, "enemies_level_19.txt"),
    /**
     * Enemies for the level 20.
     */
    ENEMIES_LEVEL_20(20, "enemies_level_20.txt"),
    /**
     * Enemies for the level 21.
     */
    ENEMIES_LEVEL_21(21, "enemies_level_21.txt"),
    /**
     * Enemies for the level 22.
     */
    ENEMIES_LEVEL_22(22, "enemies_level_22.txt"),
    /**
     * Enemies for the level 23.
     */
    ENEMIES_LEVEL_23(23, "enemies_level_23.txt"),
    /**
     * Enemies for the level 24.
     */
    ENEMIES_LEVEL_24(24, "enemies_level_24.txt"),
    /**
     * Enemies for the level 25.
     */
    ENEMIES_LEVEL_25(25, "enemies_level_25.txt"),
    /**
     * Enemies for the level 26.
     */
    ENEMIES_LEVEL_26(26, "enemies_level_26.txt"),
    /**
     * Enemies for the level 27.
     */
    ENEMIES_LEVEL_27(27, "enemies_level_27.txt"),
    /**
     * Enemies for the level 28.
     */
    ENEMIES_LEVEL_28(28, "enemies_level_28.txt"),
    /**
     * Enemies for the level 29.
     */
    ENEMIES_LEVEL_29(29, "enemies_level_29.txt"),
    /**
     * Enemies for the level 30.
     */
    ENEMIES_LEVEL_30(30, "enemies_level_30.txt"),
    /**
     * Enemies for the level 31.
     */
    ENEMIES_LEVEL_31(31, "enemies_level_31.txt"),
    /**
     * Enemies for the level 32.
     */
    ENEMIES_LEVEL_32(32, "enemies_level_32.txt"),
    /**
     * Enemies for the level 33.
     */
    ENEMIES_LEVEL_33(33, "enemies_level_33.txt"),
    /**
     * Enemies for the level 34.
     */
    ENEMIES_LEVEL_34(34, "enemies_level_34.txt"),
    /**
     * Enemies for the level 35.
     */
    ENEMIES_LEVEL_35(35, "enemies_level_35.txt");

    // The path of the file on disk.
    private String path;
    // The directory which the file is stored.
    private static final String DIR = "enemies_lists";
    //
    private int stageNumber;

    /*
     * Constructor of the enumeration.
     * 
     * @param stageNumber the number of the stage of which the enemies are referred.
     * @param fileName    the name which the file is stored on the disk with
     *                    extension.
     */
    StageEnemies(final int stageNumber, final String fileName) {
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
     * Method that returns the number of the stage of which the enemies are
     * referred.
     * 
     * @return the number of the stage as integer.
     */
    public int getStageNumber() {
        return this.stageNumber;
    }
}
