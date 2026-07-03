package abilities;

import java.io.Serializable;
import java.util.Random;

import battle_arena.BattleArena;
import pokemon.Pokemon;

/**
 * 
 * @author Antonio
 *
 */


public abstract class Ability implements Serializable{


    private static final long serialVersionUID = 1L;

    private final String name;
    private final String description;

    private boolean isActivable;

    public Ability(String name, String description){
        this.name = name;
        this. description = description;
    }

    public abstract void activateAbility(Pokemon user, Pokemon target, BattleArena battleArena);
    public abstract void setNextCondition(Pokemon user, Pokemon target, BattleArena battleArena);
    public abstract void abilityStartCondition();
    public abstract void checkForActivation(Pokemon user, Pokemon target, BattleArena battleArena);
    public abstract String getAbilityEffect(Pokemon user, Pokemon target, BattleArena battleArena);

    public String getName(){
        return this.name;
    }

    public String getDescription(){
        return this.description;
    }

    public boolean getIsActivable(){
        return this.isActivable;
    }

    public void setIsActivable(boolean isActivable){
        this.isActivable = isActivable;
    }

    public static Ability getRandomAbility(Ability[] possibleAbilities){
        Random random = new Random();
        int index = random.nextInt(possibleAbilities.length);
        return possibleAbilities[index];
    }

    public void exitingAbility(Pokemon user, Pokemon target, BattleArena battleArena){
        user.setAbility(null);
    }

    public void resetAbilityInSwitch(Pokemon user, Pokemon target, BattleArena battleArena){
        user.getAbility().abilityStartCondition();
        user.changeAbility(user, target, battleArena, this);            //reset alterations
    }

    @Override
    public boolean equals(Object ability){
        if(ability instanceof Ability){
            if(this.name.equals(((Ability)ability).name)){
                return true;
            }
        }
        //if not an Ability or if non that type of Ability
        return false;
    }
}
