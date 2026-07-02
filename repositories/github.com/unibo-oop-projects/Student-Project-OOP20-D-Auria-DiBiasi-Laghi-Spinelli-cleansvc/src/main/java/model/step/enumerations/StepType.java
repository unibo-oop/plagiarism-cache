package model.step.enumerations;

public enum StepType {
    /** 
     * Mandatory CLEANING step.
     */
    CLEANING("CLEANING", "Fase iniziale: pulizia"),
    /**
     * Mandatory CLEANSING step.
     */
    CLEANSING("CLEANSING", "Fase opzionale: detersione"),
    /**
     * Mandatory DISINFECTION step. 
     */
    DISINFECTION("DISINFECTION", "Fase chiave del processo: disinfezione"),
    /**
     * Optional DISINFESTATION step.
     */
    DISINFESTATION("DISINFESTATION", "Fase opzionale: disinfestazione"),
    /**
     * Mandatory CONCLUSION step.
     */
    CONCLUSION("CONCLUSION", "Fase finale: sistemazione");

    private String type;
    private String description;

    public String getType() {
        return this.type;
    }

    public String getDescription() {
        return this.description;
    }

    StepType(final String type, final String description) {
        this.description = description;
        this.type = type;
    }
}
