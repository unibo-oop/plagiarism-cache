package model.mod;

/**
 * Enumeration of Mod types. Differences between Mod types will determine 
 * the code executed by the ModEngine when a Mod is activated.
 * Adding mods in here is fairly easy. This enumeration should be intended
 * as a declaration of all possible mods in the game. ModEngine's code should be seen
 * as their direct implementations.
 */
public enum ModType {

   /**
    * FASTERSPEED is a malus that modifies lane objects' speed.
    */
   FASTERSPEED(g -> g.getWorld().getLane().forEach(l -> l.setSpeed(l.getSpeed() * 2)),
               g -> g.getWorld().getLane().forEach(l -> l.setSpeed(l.getSpeed() / 2)),
               600, 0.80),

   /**
    * INVERTEDCONTROLS is a malus that modifies how the frog reacts to player's commands.
    * UP will move the frog DOWN
    * DOWN will move the frog UP
    * LEFT will move the frog RIGHT
    * RIGHT will move the frog LEFT
    */
   INVERTEDCONTROLS(g -> { }, g -> { }, 600, 0.45),

   /**
    * LESSTIME malus simply subtracts some time from the timer.
    */
   LESSTIME(g -> { }, g -> { }, 1, 0.80),

   /**
    * RESTART puts the frog back at the starting point on the first SafeLane.
    */
   RESTART(g -> { }, g -> { }, 1, 0.30),

   /**
    * DOUBLEJUMP malus (for certain people who are trying to beat the game faster might be a 
    * bonus) doubles each of the frog's movement by two.
    */
   DOUBLEJUMP(g -> { }, g -> { }, 600, 0.30),

   /**
    * ADDSCORE is the most basic bonus of them all. It just adds some score.
    */
   ADDSCORE(g -> { }, g -> { }, 1, 0.85),

   /**
    * ADDLIFE adds a life to the frog.
    */
   ADDLIFE(g -> { }, g -> { }, 1, 0.50),

   /**
    * SUBTRACTLIFE subtracts a life to the frog. If the life subtracted is the last one,
    * the game ends.
    */
   SUBTRACTLIFE(g -> { }, g -> { }, 1, 0.50);

/********************************************************************************/

    /* All mods visually look the same, so LENGTH and TEXTURE are constants.    */
    private static final double LENGTH = 1;
    private static final String TEXTURE = "res/img/mod.png";
    private final Mod mod;
    private final Mod fixer;
    private final int timeActive;
    private final double chance;

    ModType(final Mod m, final Mod fixer, final int timeActive, final double chance) {
        this.mod = m;
        this.fixer = fixer;
        this.timeActive = timeActive;
        this.chance = chance;
    }

    /**
     * This method returns the length of the Mod object.
     * A Mod object is a square sized length*length.
     * @return length of the Mod object
     */
    public double getLength() {
        return LENGTH;
    }

    /**
     * This method returns the String indicating 
     * the location of the texture for the mod type.
     * In the original game all mods look the same, 
     * but with changes in the future any mod might 
     * have a different texture.
     * @return texture The location of the mod.
     */
    public String getTextureLocation() {
        return TEXTURE;
    }

    /**
     * This method is a getter for the mod.
     * @return the mod to be executed.
     */
    public Mod getMod() {
        return this.mod;
    }

    /**
     * This method is a getter for the fixer, that is what the mod does once it is
     * deactivated.
     * @return the fixer to be executed.
     */
    public Mod getFixer() {
        return this.fixer;
    }

    /**
     * This method is a getter for the time (as in, GameLoop ticks) this mod stays 
     * active for.
     * @return the time this mod stays active for.
     */
    public int getTimeActive() {
        return this.timeActive;
    }

    /**
     * This method is a getter for the spawn chance, a double between 0 and 1 which indicates how rare
     * the spawning of a mod is.
     * @return chance The spawn chance, a double between 0 and 1.
     */
    public double getChance() {
        return this.chance;
    }
}
