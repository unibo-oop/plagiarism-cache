package model;

import java.util.ArrayList;
import java.util.List;


/**
 * implementation of greenhouse.
 */
public class GreenhouseImpl implements Greenhouse {

    private int numFlowers;
    private int numRose;
    private int numTulip;
    private int numSunflower;
    private int numLily;
    private int numGenericPT;

    private final List<PlantImpl> listapiante;

    private static final int ROWS = 20; //rows available in the greenhouse
    private static final int COLUMNS = 2; //columns available in the greenhouse

    private static final int SPACEAVAILABLE = COLUMNS * ROWS; //lots available in the greenhouse

    private static final String SPACEEXEPTION = "No such space in greenhouse"; //for exeption

    //costruttore
    GreenhouseImpl() {
        this.listapiante = new ArrayList<>();
        //piante iniziali
        this.numRose = 0;
        this.numTulip = 0;
        this.numSunflower = 0;
        this.numLily = 0;
        this.numGenericPT = 0;
        this.updateTotalFlowersNum();
    }

    //metodi
    @Override
    public int getNumFlowers() {
        this.updateTotalFlowersNum();
        return numFlowers;
    }

    @Override
    public int getNumFreeSpaces() {
        this.updateTotalFlowersNum();
        return SPACEAVAILABLE - numFlowers;
    }

    /**
     * utilizzabile per sapere se non ci son piante .
     * o per controllo che non vadano sotto zero
     */
    @Override
    public boolean isEmpty() {
        //necessaria per tenere la lista aggiornata e non fare controlli su numeri vecchi
        this.updateGreenHouseImpl();
        if (this.getNumFlowers() < 0) {
            throw new IllegalArgumentException();
        }
            return this.getNumFlowers() == 0; 
    }

    //gli update private

    private boolean updateGreenHouseImpl() {
        this.updateTotalFlowersNum();
        if (this.listapiante.size() != this.numFlowers) {
            this.listapiante.clear();
            for (int i = 0; i < numRose; i++) {
                listapiante.add(new Rose(this.minPosFree()));
            }
            for (int i = 0; i < numTulip; i++) {
                listapiante.add(new Tulip(this.minPosFree()));
            }
            for (int i = 0; i < numSunflower; i++) {
                listapiante.add(new Sunflower(this.minPosFree()));
            }
            for (int i = 0; i < numLily; i++) {
                listapiante.add(new Lily(this.minPosFree()));
            }
            for (int i = 0; i < numGenericPT; i++) {
                listapiante.add(new GenericPT(this.minPosFree()));
            }
        }
        if (this.listapiante.size() != this.numFlowers) {
            throw new IllegalArgumentException("number of flowers and number in list are not the same");
        }
        return true;
    }



    @Override
    public int minPosFree() {
        if (this.listapiante.isEmpty()) {
            return 0;
        }
        if (listapiante.size() < SPACEAVAILABLE) {
            for (int i = 0; i < listapiante.size(); i++) {
                if (listapiante.get(i) == null) {
                    return i;
                }
            }
            return listapiante.size();
        } else {
            return -1;
        }
    }


    private int updateTotalFlowersNum() {
        numFlowers = (numRose + numTulip + numSunflower + numLily + numGenericPT);
        return numFlowers;
    }

    @Override
    public List<PlantImpl> getGreenHouse() {
        // returns list of plant
        return this.listapiante;
    }

    @Override
    public void setAllFlowerNum(final int roseNum, final int tulipNum, final int sunflowerNum, final int lilyNum, final int genericPTNum) {
        if (roseNum < 0 || tulipNum < 0 || sunflowerNum < 0 || lilyNum < 0 ||  genericPTNum < 0) {
            throw new IllegalArgumentException("numbers can not be negative");
        }
        if (roseNum + tulipNum + sunflowerNum + lilyNum + genericPTNum > SPACEAVAILABLE) {
            throw new IllegalArgumentException(SPACEEXEPTION);
        }
        this.numRose = roseNum;
        this.numTulip = tulipNum;
        this.numSunflower = sunflowerNum;
        this.numLily = lilyNum;
        this.numGenericPT = genericPTNum;

        this.updateGreenHouseImpl();
    }


    //setter fiori
    @Override
    public void setRoseNum(final int roseNum) {
        if (roseNum < 0) {
            throw new IllegalArgumentException();
        }
        if (roseNum + this.numTulip + this.numSunflower + this.numLily + this.numGenericPT > SPACEAVAILABLE) {
            throw new IllegalArgumentException(SPACEEXEPTION);
        }
        this.numRose = roseNum;

        this.updateGreenHouseImpl();
    }

    @Override
    public void setTulipNum(final int tulipNum) {
        if (tulipNum < 0) {
            throw new IllegalArgumentException();
        }

        if (this.numRose + tulipNum + this.numSunflower + this.numLily + this.numGenericPT > SPACEAVAILABLE) {
            throw new IllegalArgumentException(SPACEEXEPTION);
        }
        this.numTulip = tulipNum;

        this.updateGreenHouseImpl();
    }

    @Override
    public void setSunflowerNum(final int sunflowerNum) {
        if (sunflowerNum < 0) {
        throw new IllegalArgumentException();
        }
        if (this.numRose + this.numTulip + sunflowerNum + this.numLily + this.numGenericPT > SPACEAVAILABLE) {
            throw new IllegalArgumentException(SPACEEXEPTION);
        }
        this.numSunflower = sunflowerNum;

        this.updateGreenHouseImpl();
    }

    @Override
    public void setLilyNum(final int lilyNum) {
        if (lilyNum < 0) {
            throw new IllegalArgumentException();
        }
        if (this.numRose + this.numTulip + this.numSunflower + lilyNum + this.numGenericPT > SPACEAVAILABLE) {
            throw new IllegalArgumentException(SPACEEXEPTION);
        }
        this.numLily = lilyNum;

        this.updateGreenHouseImpl();
    }

    @Override
    public void setGenericPTNum(final int genericPTNum) {
        if (genericPTNum < 0) {
            throw new IllegalArgumentException();
        }
        if (this.numRose + this.numTulip + this.numSunflower + this.numLily + genericPTNum > SPACEAVAILABLE) {
            throw new IllegalArgumentException(SPACEEXEPTION);
        }
        this.numGenericPT = genericPTNum;

        this.updateGreenHouseImpl();
    }

    //getter fiori
    @Override
    public int getRoseNum() {
        return this.numRose;
    }

    @Override
    public int getTulipNum() {
        return this.numTulip;
    }

    @Override
    public int getSunflowerNum() {
        return this.numSunflower;
    }

    @Override
    public int getLilyNum() {
        return this.numLily;
    }

    @Override
    public int getGenericPTNum() {
        return this.numGenericPT;
    }

    /**
     * ATTENZIONE, metodo preferibile: setAllFlowerNum.
     * il metodo non dice quali fiori piantare quindi non è utile se non per controlli rapidi sul numero totale
     * il numero totale verrà sovrascritto al prossimo cambiamento della serra
     */
    @Override
    public void setNumFlowers(final int numFlowers) {
        this.numFlowers = numFlowers;
    }

}
