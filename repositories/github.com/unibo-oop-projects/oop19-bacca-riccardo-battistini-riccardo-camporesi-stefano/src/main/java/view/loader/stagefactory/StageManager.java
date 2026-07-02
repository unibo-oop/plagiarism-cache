package view.loader.stagefactory;

public class StageManager {

    private final StageFactory scenefactory = new StageFactory();

    /**
     * 
     * @return new StageFactory.
     */
    public final StageFactory getScenefactory() {
        return this.scenefactory;
    }

}
