package model.bonus;

/**
 *
 */
public class BonusImpl implements Bonus {

    private int armor; // prendi danni in meno
    private int bonusCash; // prendi soldi ogni volta che prendi soldi
    private int bonusPoints; // uguale per i punti
    private int bonusLife; // aumento della vita massima
    private int cashSale; // carte costano meno
    private int bonusDamages; // aggiungi danni ai danni

    @Override
    public int getArmor() {
        return this.armor;
    }

    @Override
    public int getBonusCash() {
        return this.bonusCash;
    }

    @Override
    public int getBonusPoints() {
        return this.bonusPoints;
    }

    @Override
    public int getBonusLife() {
        return this.bonusLife;
    }

    @Override
    public int getCashSale() {
        return this.cashSale;
    }

    @Override
    public int getBonusDamages() {
        return this.bonusDamages;
    }

    @Override
    public void setArmor(final int armor) {
        this.armor = armor;
    }

    @Override
    public void setBonusCash(final int bonusCash) {
        this.bonusCash = bonusCash;
    }

    @Override
    public void setBonusPoints(final int bonusPoints) {
        this.bonusPoints = bonusPoints;
    }

    @Override
    public void setBonusLife(final int bonusLife) {
        this.bonusLife = bonusLife;
    }

    @Override
    public void setCashSale(final int cashSale) {
        this.cashSale = cashSale;
    }

    @Override
    public void setBonusDamages(final int bonusDamages) {
        this.bonusDamages = bonusDamages;
    }
}
