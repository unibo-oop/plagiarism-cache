package it.dpg.minigames.molegame.model;

public class MoleImpl implements Mole {

    private boolean isOut = false;

    public MoleImpl(){}

    /**
     * @return true if Mole is out
     */
    @Override
    public boolean isOut() {
        return isOut;
    }


    /**
     * set the mole out of the hole
     */
    @Override
    public void setMoleOut() {
        isOut=true;
    }

    /**
     * set the mole inside of the hole
     */
    @Override
    public void setMoleIn() {
        isOut=false;
    }
}
