package enums;

/**
 * Enumeration for all the stages.
 */
public enum Stage {

    /**
     * The first level.
     */
    LEVEL_01(1),
    /**
     * The second level.
     */
    LEVEL_02(2),
    /**
     * The third level.
     */
    LEVEL_03(3),
    /**
     * The fourth level.
     */
    LEVEL_04(4),
    /**
     * The fifth level.
     */
    LEVEL_05(5),
    /**
     * The sixth level.
     */
    LEVEL_06(6),
    /**
     * The seventh level.
     */
    LEVEL_07(7),
    /**
     * The eighth level.
     */
    LEVEL_08(8),
    /**
     * The ninth level.
     */
    LEVEL_09(9),
    /**
     * The tenth level.
     */
    LEVEL_10(10),
    /**
     * The eleventh level.
     */
    LEVEL_11(11),
    /**
     * The twelfth level.
     */
    LEVEL_12(12),
    /**
     * The thirteenth level.
     */
    LEVEL_13(13),
    /**
     * The fourteenth level.
     */
    LEVEL_14(14),
    /**
     * The fifteenth level.
     */
    LEVEL_15(15),
    /**
     * The sixteenth level.
     */
    LEVEL_16(16),
    /**
     * The seventeenth level.
     */
    LEVEL_17(17),
    /**
     * The eighteenth level.
     */
    LEVEL_18(18),
    /**
     * The nineteenth level.
     */
    LEVEL_19(19),
    /**
     * The twentieth level.
     */
    LEVEL_20(20),
    /**
     * The twenty-first level.
     */
    LEVEL_21(21),
    /**
     * The twenty-second level.
     */
    LEVEL_22(22),
    /**
     * The twenty-third level.
     */
    LEVEL_23(23),
    /**
     * The twenty-fourth level.
     */
    LEVEL_24(24),
    /**
     * The twenty-fifth level.
     */
    LEVEL_25(25),
    /**
     * The twenty-sixth level.
     */
    LEVEL_26(26),
    /**
     * The twenty-seventh level.
     */
    LEVEL_27(27),
    /**
     * The twenty-eighth level.
     */
    LEVEL_28(28),
    /**
     * The twenty-ninth level.
     */
    LEVEL_29(29),
    /**
     * The thirtieth level.
     */
    LEVEL_30(30),
    /**
     * The thirty-first level.
     */
    LEVEL_31(31),
    /**
     * The thirty-second level.
     */
    LEVEL_32(32),
    /**
     * The thirty-third level.
     */
    LEVEL_33(33),
    /**
     * The thirty-fourth level.
     */
    LEVEL_34(34),
    /**
     * The thirty-fifth level.
     */
    LEVEL_35(35);

    // The number of the stage referred.
    private int stageNumber;

    /*
     * Constructor of the enumeration.
     * 
     * @param stageNumber the number of the stage referred.
     */
    Stage(final int stageNumber) {
        this.stageNumber = stageNumber;
    }

    /**
     * Method that returns the number of the stage.
     * 
     * @return the number of the stage as integer.
     */
    public int getStageNumber() {
        return this.stageNumber;
    }

}
