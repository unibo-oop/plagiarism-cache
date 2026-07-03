package utils.enumerations;

/**
 * Defines type of map in use.
 */
public enum MapType {

    /**
     * Classic Risk! map.
     */
    ClassicMap("/map/RiskClassicMap.txt", "/initialization/classicMap/initClassicStatesInfo.txt", "/initialization/classicMap/initClassicRegionsInfo.txt", 
            "/initialization/classicMap/classicAimManagerInfo.txt", 24, 18);
 
    private final String mapInitFile;
    private final String statesInitFile;
    private final String regionsInitFile;
    private final String aimManagerInitFile;
    private final int defTargetNum;
    private final int undefTargetNum;

    MapType(final String mapInitFile, final String statesInitFile, final String regionsInitFile, 
            final String aimManagerInitFile, final int defTargetNum, final int undefTargetNum) {
        this.mapInitFile = mapInitFile;
        this.statesInitFile = statesInitFile;
        this.regionsInitFile = regionsInitFile;
        this.aimManagerInitFile = aimManagerInitFile;
        this.defTargetNum = defTargetNum;
        this.undefTargetNum = undefTargetNum;
    }

    /**
     * @return the path for the initialization file of the map. 
     */
    public String getMapInitFile() {
        return this.mapInitFile;
    }

    /**
     * @return the path for the initialization file of the states.
     */
    public String getStatesInitFile() {
        return this.statesInitFile;
    }

    /**
     * @return the path for the initialization file of the regions.
     */
    public String getRegionsInitFile() {
        return this.regionsInitFile;
    }

    /**
     * @return the path for the initialization file of the {@link model.AimManager}.
     */
    public String getAimManagerInitFile() {
        return this.aimManagerInitFile;
    }

    /**
     * @return the number of states to conquer for defined aims of {@link model.AimManager}. 
     */
    public int getDefTargetNum() {
        return this.defTargetNum;
    }

    /**
     * @return the number of states to conquer for undefined aims of {@link model.AimManager}.
     */
    public int getUndefTargetNum() {
        return this.undefTargetNum;
    }

}
