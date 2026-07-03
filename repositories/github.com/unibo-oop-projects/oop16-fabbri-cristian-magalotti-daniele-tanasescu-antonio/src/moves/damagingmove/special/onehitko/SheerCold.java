package moves.damagingmove.special.onehitko;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Ground;
import types.Ice;
import types.Type;

public class SheerCold extends OneHitKOSpecialDamagingMove{
    
    private static final double STANDARDACCURACY = 0.3;
    private static final int MAXTYPES = 2;

    public SheerCold() {
        super(  "Sheer Cold",                                                                                                           //name
                "The target faints instantly. It's less likely to hit the target if it's used by Pokémon other than Ice types.",        //description
                new Ground(),                                                                                                           //type
                STANDARDACCURACY,                                                                                                       //accuracy
                5,                                                                                                                      //PP
                0);                                                                                                                     //Priority
    }
    
    @Override
    public void getDamage(Pokemon user, Pokemon target, BattleArena battleArena) {
        String[] userTypes = new String[MAXTYPES];
        String[] targetTypes = new String[MAXTYPES];
        for(int i = 0; i < MAXTYPES; i++){
            if(user.getType()[i] != null){
                userTypes[i] = user.getType()[i].getTypeName();
            }
            if(target.getType()[i] != null){
                targetTypes[i] = target.getType()[i].getTypeName();
            }
        }
        if(!Type.containsType(targetTypes, new Ice())){                                 //which is immune
            if(!Type.containsType(userTypes, new Ice())){                               //no ice -> less accuracy
                this.setMoveAccuracy(0.5);
            }
            super.getDamage(user, target, battleArena);
            this.setMoveAccuracy(2d);
        }
        else{
            //immunity
        }
    }
    
    

}
