package moves.status;

import battle_arena.BattleArena;
import main.view.BattleMenuController;
import pokemon.Pokemon;
import types.Ghost;
import types.Type;

public class Curse extends StatusMove{

    public Curse(Type[] userTypes) {
        super(  "Curse",                                                                                                         //name
                "A move that works differently for the Ghost type than for all other types.",                                    //description
                new Ghost(),                                                                                                     //type
                999,                                                                                                             //accuracy
                10,                                                                                                              //PP                                                                                                                     
                0);                                                                                                              //priority  
        this.setHasRecoil(true);
        String[] userTypesNames = new String[]{userTypes[0].getTypeName(),(userTypes[1] != null? userTypes[1].getTypeName() : null)};
        if(!Type.containsType(userTypesNames, new Ghost())){
            this.setSelfEffect(true);
        }
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        String[] userTypes = new String[]{user.getType()[0].getTypeName(),
                                          (user.getType()[1] != null? user.getType()[1].getTypeName() : "")};
        if(Type.containsType(userTypes, new Ghost())){
            this.effectGhost(user, target);
        }
        else{
            user.setAlterationSpe(-1, true);
            user.setAlterationAtk(+1, true);
            user.setAlterationDef(+1, true);
        }
        
    }
    
    private void effectGhost(Pokemon user, Pokemon target){
        if(user.getHp() >= user.getMaxHp()/2){
            status_condition.volatile_status.Curse curse = new status_condition.volatile_status.Curse();
            if(!curse.isContained(target.volatileStatusConditions)){
                user.takeDamage(user.getMaxHp()/2, this.hasRecoil());
                curse.addVolatile(target, target.volatileStatusConditions);
            }
            else{
                BattleMenuController.battleLogManager.setMoveFailedMassage();
            }
        }
        else{
            BattleMenuController.battleLogManager.setMoveFailedMassage();
        }
    }

}
