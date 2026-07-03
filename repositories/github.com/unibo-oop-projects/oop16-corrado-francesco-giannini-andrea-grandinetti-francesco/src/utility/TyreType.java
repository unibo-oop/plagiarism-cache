package utility;

import model.car.tyre.TTByDegrade;

/**
 * 
 *
 */
public enum TyreType {
    /**
     * UltraSoft.
     */
    US("UltraSoft", 90, TTByDegrade.USB),
    /**
     * SuperSoft.
     */
    SS("SuperSoft", 110, TTByDegrade.SSB),
    /**
     * Soft.
     */
    S("Soft", 130, TTByDegrade.SB),
    /**
     * Medium.
     */
    M("Medium", 150, TTByDegrade.MB),
    /**
     * Hard.
     */
    H("Hard", 170, TTByDegrade.HB);




   private final String name;
   private final TTByDegrade firstTyre;
   private final int maxDegrade;
   TyreType(final String name, final int maxDegrade, final TTByDegrade firstTyre) {
        this.name = name;
        this.firstTyre = firstTyre;
        this.maxDegrade = maxDegrade;
   }
   /**
    * 
    * @return name
    */
   public String getName() {
       return name;
   }
   /**
    * 
    * @return first tyre to take
    */
   public TTByDegrade getFirstTyre() {
       return firstTyre;
   }
   /**
    * 
    * @return max degrade
    */
   public int getMaxDegrade() {
       return maxDegrade;
   }
}
