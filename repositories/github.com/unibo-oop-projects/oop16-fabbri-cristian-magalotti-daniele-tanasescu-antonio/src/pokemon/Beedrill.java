package pokemon;

import java.util.Arrays;
import java.util.HashSet;

import abilities.Ability;
import abilities.hpcondition.Swarm;
import abilities.movecondition.Sniper;
import moves.Move;
import moves.damagingmove.physical.Acrobatics;
import moves.damagingmove.physical.AerialAce;
import moves.damagingmove.physical.BrickBreak;
import moves.damagingmove.physical.BugBite;
import moves.damagingmove.physical.Endeavor;
import moves.damagingmove.physical.FalseSwipe;
import moves.damagingmove.physical.PoisonJab;
import moves.damagingmove.physical.PoisonSting;
import moves.damagingmove.physical.Rage;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.physical.XScissor;
import moves.damagingmove.physical.multistrike.two.Twineedle;
import moves.damagingmove.physical.multistrike.twotofive.FuryAttack;
import moves.damagingmove.physical.multistrike.twotofive.PinMissile;
import moves.damagingmove.physical.variablepriority.Pursuit;
import moves.damagingmove.special.SludgeBomb;
import moves.status.Agility;
import moves.status.Attract;
import moves.status.DoubleTeam;
import moves.status.Harden;
import moves.status.Rest;
import moves.status.Roost;
import moves.status.StringShot;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.SwordsDance;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Bug;
import types.Poison;
import types.Type;

public class Beedrill extends Pokemon{

    public Beedrill(int level) {
        super(  level,                                                                                          //level
                65,                                                                                             //baseHP 
                80,                                                                                             //baseAtk 
                40,                                                                                             //baseDef 
                75,                                                                                             //baseSpe
                45,                                                                                             //baseSpA 
                80,                                                                                             //baseSpD 
                new Type[]{new Bug(), new Poison()},                                                            //type
                Ability.getRandomAbility(new Ability[]{new Swarm(), new Sniper()}),                                        
                30,                                                                                             //weight (Kg)
                0.8,                                                                                            //miniFrontSizeScale
                Gender.getRandomGender(),                                                                       //gender  
                new HashSet<Move>(                                                                              //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new PoisonSting(),
                                        new StringShot(),
                                        new BugBite(),
                                        new Harden(),
                                        new Twineedle(),
                                        new FuryAttack(),
                                        new Rage(),
                                        new Pursuit(),
                                        new PinMissile(),
                                        new PoisonJab(),
                                        new Agility(),
                                        new Endeavor(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new Protect(),
                                        new Roost(),
                                        new BrickBreak(),
                                        new DoubleTeam(),
                                        new SludgeBomb(),
                                        new AerialAce(),
                                        new Rest(),
                                        new Attract(),
                                        new Thief(),
                                        new FalseSwipe(),
                                        new Acrobatics(),
                                        new SwordsDance(),
                                        new XScissor(),
                                        new Swagger(),
                                }
                                )
                        )
                );
    }

}
