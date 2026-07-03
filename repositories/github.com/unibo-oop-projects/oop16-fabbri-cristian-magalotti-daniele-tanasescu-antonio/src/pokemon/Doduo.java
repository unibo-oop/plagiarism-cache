package pokemon;

import java.util.Arrays;
import java.util.HashSet;

import abilities.Ability;
import abilities.movecondition.TangledFeet;
import abilities.statusalterationcondition.EarlyBird;
import abilities.switchcondition.RunAway;
import moves.Move;
import moves.damagingmove.physical.AerialAce;
import moves.damagingmove.physical.DrillPeck;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.Peck;
import moves.damagingmove.physical.QuickAttack;
import moves.damagingmove.physical.Rage;
import moves.damagingmove.physical.SteelWing;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.physical.bypassprotect.FeintAttack;
import moves.damagingmove.physical.multistrike.two.DoubleHit;
import moves.damagingmove.physical.multistrike.twotofive.FuryAttack;
import moves.damagingmove.physical.selfrecoil.BraveBird;
import moves.damagingmove.physical.selfrecoil.JumpKick;
import moves.damagingmove.physical.variablepriority.Pursuit;
import moves.status.Agility;
import moves.status.Attract;
import moves.status.DoubleTeam;
import moves.status.Growl;
import moves.status.Haze;
import moves.status.Rest;
import moves.status.ScaryFace;
import moves.status.SunnyDay;
import moves.status.Supersonic;
import moves.status.Swagger;
import moves.status.SwordsDance;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Flying;
import types.Normal;
import types.Type;

public class Doduo extends Pokemon {

    public Doduo(int level) {
        super(level,
                35,		                                                       				//hp
                85,		                                                       				//atk
                45,		                                                       				//def
                75,		                                                       				//speed
                35,		                                                       				//spa
                35,		                                                       				//spd
                new Type[]{new Normal(), new Flying()},		                       				//tipo
                Ability.getRandomAbility(new Ability[]{new EarlyBird(), new RunAway(), new TangledFeet()}),  	//ability
                40,	                                                               				//weight(kg)
                1,                                                                                              //miniFrontSizeScale
                Gender.getRandomGender(),	                                       				//gender
                new HashSet<Move>(                                                     				//learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Peck(),
                                        new Growl(),
                                        new Rage(),
                                        new Pursuit(),
                                        new FuryAttack(),
                                        new AerialAce(),
                                        new DoubleHit(),
                                        new Agility(),
                                        new SwordsDance(),
                                        new JumpKick(),
                                        new DrillPeck(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new Protect(),	
                                        new DoubleTeam(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new Thief(),
                                        new SteelWing(),
                                        new Swagger(),
                                        new BraveBird(),
                                        new FeintAttack(),
                                        new QuickAttack(),
                                        new ScaryFace(),
                                        new Haze(),
                                        new Supersonic()
                                }
                                )
                        )
                );
    }

}
