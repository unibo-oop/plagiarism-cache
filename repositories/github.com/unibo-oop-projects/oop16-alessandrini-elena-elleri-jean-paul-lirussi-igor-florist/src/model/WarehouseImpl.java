package model;

/**
 * the class for the warehouse.
 */
public class WarehouseImpl implements Warehouse {

    private long water;
    private long fertilizer;

    private long totalnumFlowers;
    private long totalnumRose;
    private long totalnumTulip;
    private long totalnumSunflower;
    private long totalnumLily;
    private long totalnumGenericPT;

    /**
     * constructor.
     */
    public WarehouseImpl() {
        this.water = 0;
        this.fertilizer = 0;

        this.totalnumFlowers = 0;

        this.totalnumRose = 0;
        this.totalnumTulip = 0;
        this.totalnumSunflower = 0;
        this.totalnumLily = 0;
        this.totalnumGenericPT = 0;
    }

    //water
    @Override
    public void addWaterUsed(final int liters) {
       this.water += liters;
    }

    @Override
    public long getWaterUsed() {
        return this.water;
    }

    @Override
    public boolean resetWaterUsed() {
        this.water = 0;
        return true;
    }

    //fertilizer
    @Override
    public void addFertilizerUsed(final int kg) {
        this.fertilizer += kg;
    }

    @Override
    public long getFertilizerUsed() {
        return this.fertilizer;
    }

    @Override
    public boolean resetFertilizerUsed() {
        this.fertilizer = 0;
        return true;
    }


    //add
    @Override
    public void addRosePicked(final int num) {
        this.totalnumRose += num;
        this.updateTotNumFLowers();
    }

    @Override
    public void addTulipPicked(final int num) {
        this.totalnumTulip += num;
        this.updateTotNumFLowers();
    }

    @Override
    public void addSunflowerPicked(final int num) {
        this.totalnumSunflower += num;
        this.updateTotNumFLowers();
    }

    @Override
    public void addLilyPicked(final int num) {
        this.totalnumLily += num;
        this.updateTotNumFLowers();
    }

    @Override
    public void addGenericPicked(final int num) {
        this.totalnumGenericPT += num;
        this.updateTotNumFLowers();
    }

    //GETTER
    @Override
    public long getRosePicked() {
        return totalnumRose;
    }

    @Override
    public long getTulipPicked() {
        return totalnumTulip;
    }

    @Override
    public long getSunflowerPicked() {
        return totalnumSunflower;
    }

    @Override
    public long getLilyPicked() {
        return totalnumLily;
    }

    @Override
    public long getGenericPicked() {
        return totalnumGenericPT;
    }

    //get total
    @Override
    public long getTotalPlantsPicked() {
        this.updateTotNumFLowers();
        return totalnumFlowers;
    }

    //private
    private boolean updateTotNumFLowers() {
        this.totalnumFlowers = this.totalnumRose + this.totalnumSunflower + this.totalnumTulip + this.totalnumLily + this.totalnumGenericPT;
        return true;
    }

    //tostring
    @Override
    public String toString() {
        this.updateTotNumFLowers();
        return ("Il magazzino conta: " + this.totalnumFlowers + " fiori raccolti, " + this.water + " l di acqua, " + this.fertilizer + " kg di fertilizzante usati.");
    }
}
