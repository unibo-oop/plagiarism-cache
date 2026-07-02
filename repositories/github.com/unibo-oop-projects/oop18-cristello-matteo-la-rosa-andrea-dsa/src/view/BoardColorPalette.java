package view;
/**

 */
public enum BoardColorPalette {

    /**
     * 
     */
    PALE_SPRING_BUD("0xF0F3BD"),
    /**
     * 
     */
    CARRIBEAN_GREEN("0x02C39A"),
    /**
     * 
     */
    PERSIAN_GREEN("0x00A896"),
    /**
     * 
     */
    METALLIC_SEAWEED("0x028090"),
    /**
     * 
     */
    SEA_BLUE("0x05668d");


    /**
     * 
     */
    private final String rgbHex;

    /**
     * @param hexCode
     */
    BoardColorPalette(final String hexCode) {
 
        this.rgbHex = hexCode;
    }

    /**
     * @return rgb in HexaDecimal.
     */
    public String getHexRGB() {
        return rgbHex;
    }


}
