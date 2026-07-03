package model.bonus;
/**
 *
 */
public interface Bonus {

    /**
     * This method return the level player's armor.
     * 
     * @return the armor of the player.
     */
    int getArmor();

    /**
     * This method return the level player's bonus cash.
     * 
     * @return the "bonus-cash" of the player.
     */
    int getBonusCash();

    /**
     * This method return the level player's bonus points.
     * 
     * @return the "bonus-points" of the player.
     */
    int getBonusPoints();

    /**
     * This method return the level player's bonus life.
     * 
     * @return the "bonus-life" of the player.
     */
    int getBonusLife();

    /**
     * This method return the level player's cash sale.
     * 
     * @return the "cash-sale" of the player.
     */
    int getCashSale();

    /**
     * This method return the level player's bonus damages.
     * 
     * @return the "bonus-damages" of the player.
     */
    int getBonusDamages();

    /**
     * This method set the level of player's armor.
     * @param armor is how many bonus armor increases.
     */
    void setArmor(int armor);

    /**
     * This method set the level of player's bonus cash.
     * @param bonusCash how much bonus moneys player receives when he earns moneys.
     */
    void setBonusCash(int bonusCash);

    /**
     * This method set the level of player's bonus points.
     * @param bonusPoints how much bonus points player receives when he earns points.
     */
    void setBonusPoints(int bonusPoints);

    /**
     * This method set the level of player's bonus life.
     * @param bonusLife is how much maximum life player can reach.
     */
    void setBonusLife(int bonusLife);

    /**
     * This method set the level of player's cash sale.
     * @param cashSale is the discount applied to card that player could buy.
     */
    void setCashSale(int cashSale);

    /**
     * This method set the level of player's bonus damages.
     * @param bonusDamages is how many damage player can inflict to other players.
     */
    void setBonusDamages(int bonusDamages);
}
