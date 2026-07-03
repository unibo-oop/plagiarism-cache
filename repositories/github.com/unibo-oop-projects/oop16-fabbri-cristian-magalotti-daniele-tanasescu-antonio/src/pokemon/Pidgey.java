package pokemon;

import java.util.Arrays;
import java.util.HashSet;

import abilities.Ability;
import abilities.movecondition.TangledFeet;
import abilities.statisticsalterationondemand.BigPecks;
import abilities.statisticsalterationondemand.KeenEye;
import moves.Move;
import moves.damagingmove.physical.AerialAce;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.QuickAttack;
import moves.damagingmove.physical.SteelWing;
import moves.damagingmove.physical.Tackle;
import moves.damagingmove.physical.WingAttack;
import moves.damagingmove.physical.bypassprotect.FeintAttack;
import moves.damagingmove.physical.selfrecoil.BraveBird;
import moves.damagingmove.physical.variablepriority.Pursuit;
import moves.damagingmove.special.AirSlash;
import moves.damagingmove.special.Gust;
import moves.damagingmove.special.Hurricane;
import moves.status.Agility;
import moves.status.Attract;
import moves.status.DoubleTeam;
import moves.status.FeatherDance;
import moves.status.MirrorMove;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.Roost;
import moves.status.Toxic;
import moves.status.SandAttack;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.Whirlwind;
import moves.status.protecting.Protect;
import types.Flying;
import types.Normal;
import types.Type;

public class Pidgey extends Pokemon{

    public Pidgey(int level) {
        super(  level,                                                                                          //level
                40,                                                                                             //baseHP 
                45,                                                                                             //baseAtk 
                40,                                                                                             //baseDef 
                56,                                                                                             //baseSpe
                35,                                                                                             //baseSpA 
                35,                                                                                             //baseSpD 
                new Type[]{new Normal(), new Flying()},                                                         //type
                Ability.getRandomAbility(new Ability[]{new KeenEye(),                                           //ability
                        new TangledFeet(),
                        new BigPecks()}),                                        
                1.8,                                                                                            //weight (Kg)
                1,                                                                                              //miniFrontSizeScale
                Gender.getRandomGender(),                                                                       //gender  
                new HashSet<Move>(                                                                              //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Tackle(),
                                        new SandAttack(),
                                        new Gust(),
                                        new QuickAttack(),
                                        new Whirlwind(),
                                        new FeatherDance(),
                                        new Agility(),
                                        new WingAttack(),
                                        new Roost(),
                                        new MirrorMove(),
                                        new AirSlash(),
                                        new Hurricane(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new Protect(),
                                        new RainDance(),
                                        new DoubleTeam(),
                                        new AerialAce(),
                                        new Rest(),
                                        new Attract(),
                                        new SteelWing(),
                                        new Swagger(),
                                        new FeintAttack(),
                                        new BraveBird(),
                                        new Pursuit(),
                                        new Facade()
                                }
                                )
                        )
                );
    }

}
