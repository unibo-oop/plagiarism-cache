package moves.damagingmove.physical.selfrecoil;

import abilities.otherconditions.RockHead;
import battle_arena.BattleArena;
import moves.damagingmove.physical.PhysicalDamagingMove;
import pokemon.Pokemon;
import types.Type;

public abstract class SelfRecoilPhysicalDamagingMove extends PhysicalDamagingMove{

    private final double selfRecoilPerc;
    private final boolean recoilAfterFail;

    public SelfRecoilPhysicalDamagingMove(String name, String description, int moveBasePower, Type moveType, double moveAccuracy,
            double actualCritRange, int minPP, int priority, double selfRecoilPerc, boolean recoilAfterFail) {
        super(name, description, moveBasePower, moveType, moveAccuracy, actualCritRange, minPP, priority);
        this.selfRecoilPerc = selfRecoilPerc;
        this.recoilAfterFail = recoilAfterFail;
        this.setHasRecoil(true);
    }

    @Override
    public void getDamage(Pokemon user, Pokemon target, BattleArena battleArena){
        this.setHasRecoil(false);                               //it's not a recoil now...
        super.getDamage(user, target, battleArena);             //the original one
        this.setHasRecoil(true);                                //now it's a recoil, maybe!
        if(this.recoilAfterFail){
            if(this.hasFailed){
                //if RockHead -> no recoil , if RockHead but it's Struggle -> yes recoil!
                if(!user.getAbility().equals(new RockHead())|| this.equals(new Struggle())){
                    this.getRecoil(user);
                }
            }            
        }
        else{
            //if RockHead -> no recoil , if RockHead but it's Struggle -> yes recoil!
            if(!user.getAbility().equals(new RockHead())|| this.equals(new Struggle())){
                this.getRecoil(user);
            }
        }
    }

    public void getRecoil(Pokemon user){
        if(this.recoilAfterFail){
            user.takeDamage(this.selfRecoilPerc * user.getMaxHp(), this.hasRecoil());
        }
        else{
            if(!this.hasFailed){
                user.takeDamage(this.selfRecoilPerc * this.getLastDamageDone(), this.hasRecoil());
            }
        }
    }

    public double getSelfRecoilPerc(){
        return this.selfRecoilPerc;
    }

    public  boolean hasRecoilAfterFail(){
        return this.recoilAfterFail;
    }

}
