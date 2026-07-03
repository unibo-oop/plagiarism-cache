package pokemon;

import java.util.Arrays;
import java.util.HashSet;

import abilities.Ability;
import abilities.movecondition.Sniper;
import abilities.statisticsalterationondemand.KeenEye;
import moves.Move;
import moves.damagingmove.physical.AerialAce;
import moves.damagingmove.physical.Astonish;
import moves.damagingmove.physical.DrillPeck;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.FalseSwipe;
import moves.damagingmove.physical.Peck;
import moves.damagingmove.physical.QuickAttack;
import moves.damagingmove.physical.SteelWing;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.physical.bypassprotect.FeintAttack;
import moves.damagingmove.physical.multistrike.twotofive.FuryAttack;
import moves.damagingmove.physical.variablepriority.Pursuit;
import moves.damagingmove.special.TriAttack;
import moves.status.Agility;
import moves.status.Attract;
import moves.status.DoubleTeam;
import moves.status.FeatherDance;
import moves.status.Growl;
import moves.status.Leer;
import moves.status.MirrorMove;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.ScaryFace;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.Toxic;
import moves.status.Whirlwind;
import moves.status.protecting.Protect;
import types.Flying;
import types.Normal;
import types.Type;

public class Spearow extends Pokemon {

    public Spearow(int level) {
        super(level,
                40,		                                                       				//hp
                60,		                                                       				//atk
                30,		                                                       				//def
                70,		                                                       				//speed
                31,		                                                       				//spa
                31,		                                                       				//spd
                new Type[]{new Normal(), new Flying()},		                       			        //tipo
                Ability.getRandomAbility(new Ability[]{new KeenEye(), new Sniper()}),  		                //ability
                2,	                                                               			        //weight(kg)
                1,                                                                                              //miniFrontSizeScale
                Gender.getRandomGender(),	                                       			        //gender
                new HashSet<Move>(                                                     		                //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Peck(),
                                        new Growl(),
                                        new Leer(),
                                        new Pursuit(),
                                        new FuryAttack(),
                                        new AerialAce(),
                                        new MirrorMove(),
                                        new Agility(),
                                        new DrillPeck(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new Protect(),
                                        new RainDance(),
                                        new DoubleTeam(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new Thief(),
                                        new FalseSwipe(),
                                        new Swagger(),
                                        new Astonish(),
                                        new FeatherDance(),
                                        new FeintAttack(),
                                        new QuickAttack(),
                                        new ScaryFace(),
                                        new SteelWing(),
                                        new TriAttack(),
                                        new Whirlwind()
                                }
                                )
                        )
                );
    }

}
