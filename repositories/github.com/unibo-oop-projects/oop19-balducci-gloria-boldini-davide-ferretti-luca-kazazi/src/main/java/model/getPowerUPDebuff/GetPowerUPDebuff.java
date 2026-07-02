package model.getPowerUPDebuff;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import controllers.texture.GetTexture;
import model.ID;
import model.debuff.FireDebuff;
import model.debuff.FreezeDebuff;
import model.debuff.LoseTimeDebuff;
import model.debuff.SlowDownDebuff;
import model.gameObject.GameObject;
import model.powerup.GainTimePowerUP;
import model.powerup.InvisiblePowerUP;
import model.powerup.KnifePowerUP;
import model.powerup.LifeUpPowerUP;
import model.powerup.SpeedUPPowerUP;
import other.Pair;

public class GetPowerUPDebuff implements GetPowerUPDebuffInterface {

    private final List<ID> powerUPID;
    private final List<ID> debuffID;

    private final Random rand;
    private final GetTexture texture;

    /**
     * Constructor for GetPowerUPDebuff.
     * 
     * @param texture
     */
    public GetPowerUPDebuff(final GetTexture texture) {
        this.powerUPID = new LinkedList<>();
        this.debuffID = new LinkedList<>();
        this.rand = new Random();
        this.texture = Objects.requireNonNull(texture);
        this.fillDebuffList();
        this.fillPowerUPList();
    }

    private void fillPowerUPList() {

        this.powerUPID.add(ID.LIFEUPPU);
        this.powerUPID.add(ID.GAINTIMEPU);
        this.powerUPID.add(ID.SPEEDUPPU);
        this.powerUPID.add(ID.INVISIBLEPU);
        this.powerUPID.add(ID.KNIFEPU);
    }

    private void fillDebuffList() {

        this.debuffID.add(ID.FIREDB);
        this.debuffID.add(ID.FREEZEDB);
        this.debuffID.add(ID.SLOWDOWNDB);
        this.debuffID.add(ID.TIMEDOWNDB);
    }

    @Override
    public GameObject getRandomPowerUP(final Pair<Integer, Integer> pos) {

        final int randomInt = rand.nextInt(powerUPID.size());
        final ID temp = powerUPID.get(randomInt);
        GameObject pUP;
        if (temp.equals(ID.LIFEUPPU)) {
            pUP = new LifeUpPowerUP(temp, 0, 0, texture.getPowerUPImageByID(temp));
        } else if (temp.equals(ID.GAINTIMEPU)) {
            pUP = new GainTimePowerUP(temp, 0, 0, texture.getPowerUPImageByID(temp));
        } else if (temp.equals(ID.SPEEDUPPU)) {
            pUP = new SpeedUPPowerUP(temp, 0, 0, texture.getPowerUPImageByID(temp));
        } else if (temp.equals(ID.INVISIBLEPU)) {
            pUP = new InvisiblePowerUP(temp, 0, 0, texture.getPowerUPImageByID(temp));
        } else {
            pUP = new KnifePowerUP(temp, 0, 0, texture.getPowerUPImageByID(temp));
        }
        pUP.setCoord(Objects.requireNonNull(pos));
        return pUP;
    }

    @Override
    public GameObject getRandomDebuff(final Pair<Integer, Integer> pos) {
        final int randomInt = rand.nextInt(debuffID.size());
        final ID temp = debuffID.get(randomInt);
        GameObject pUP;
        if (temp.equals(ID.FIREDB)) {
            pUP = new FireDebuff(temp, 0, 0, texture.getDebuffImageByID(temp));
        } else if (temp.equals(ID.FREEZEDB)) {
            pUP = new FreezeDebuff(temp, 0, 0, texture.getDebuffImageByID(temp));
        } else if (temp.equals(ID.SLOWDOWNDB)) {
            pUP = new SlowDownDebuff(temp, 0, 0, texture.getDebuffImageByID(temp));
        } else {
            pUP = new LoseTimeDebuff(temp, 0, 0, texture.getDebuffImageByID(temp));
        }
        pUP.setCoord(Objects.requireNonNull(pos));
        return pUP;
    }

}