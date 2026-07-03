package moves.damagingmove.physical.multistrike;

import abilities.otherconditions.Scrappy;
import abilities.otherconditions.SkillLink;
import battle_arena.BattleArena;
import main.view.BattleMenuController;
import moves.damagingmove.physical.PhysicalDamagingMove;
import pokemon.Pokemon;
import types.Fight;
import types.Ghost;
import types.Normal;
import types.Type;

public abstract class MultiStrikePhysicalDamagingMove extends PhysicalDamagingMove{

    private final int maxHits;

    public int numHits;

    public MultiStrikePhysicalDamagingMove(String name, String description, int moveBasePower, Type moveType, double moveAccuracy,
            double actualCritRange, int minPP, int priority, int maxHits) {
        super(name, description, moveBasePower, moveType, moveAccuracy, actualCritRange, minPP, priority);
        this.maxHits = maxHits;
    }

    public int getMaxHits(){
        return this.maxHits;
    }

    public abstract void extractNumOfHits();

    public void multiStrike(Pokemon user, Pokemon target, BattleArena battleArena){
        if(user.getAbility().equals(new SkillLink())){
            this.numHits = this.maxHits;
        }
        else{
            this.extractNumOfHits();
        }
        int i = 0;
       if(!target.isFainted()){
           if(!target.isProtecting){
               boolean canHit = true;
               for(Type type : target.getType()){
                   if(type != null){
                       if(type.isImmuneTo(this.getMoveType())){
                           //if has scrappy ability with right conditions, it will attack just the same
                           if(! (type.equals(new Ghost()) && 
                                 (this.getMoveType().equals(new Normal()) || this.getMoveType().equals(new Fight())) &&
                                  user.getAbility().equals(new Scrappy()))
                                 ){
                               canHit = false;
                           }
                       }
                   }
               }
               if(canHit = true){
                   for(; i < this.numHits; i++){
                       this.getDamage(user, target, battleArena);
                       if(this.hasFailed || target.isFainted()){
                           break;
                       }
                   }
                   BattleMenuController.battleLogManager.setNumOfMultiDamages(i);
               }
               else{
                   BattleMenuController.battleLogManager.setEffectivenessMessage(0);
               }

           }
           else{
               BattleMenuController.battleLogManager.setProtectMessage(target);
           }
       }
       else{
           BattleMenuController.battleLogManager.setMoveFailedMassage();
       }
    }
}
