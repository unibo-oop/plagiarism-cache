package pokemon;

import java.util.Arrays;
import java.util.HashSet;

import abilities.Ability;
import abilities.endofturnconditionability.SpeedBoost;
import abilities.firstturn.CompoundEyes;
import moves.Move;
import moves.damagingmove.physical.AerialAce;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.LeechLife;
import moves.damagingmove.physical.QuickAttack;
import moves.damagingmove.physical.SteelWing;
import moves.damagingmove.physical.Tackle;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.physical.WingAttack;
import moves.damagingmove.physical.hpdependent.Reversal;
import moves.damagingmove.physical.selfrecoil.DoubleEdge;
import moves.damagingmove.physical.variablepriority.Pursuit;
import moves.damagingmove.special.AirSlash;
import moves.damagingmove.special.AncientPower;
import moves.damagingmove.special.Psychic;
import moves.damagingmove.special.ShadowBall;
import moves.damagingmove.special.SignalBeam;
import moves.damagingmove.special.amount.SonicBoom;
import moves.damagingmove.special.sleeprequired.DreamEater;
import moves.status.Attract;
import moves.status.DoubleTeam;
import moves.status.Hypnosis;
import moves.status.Rest;
import moves.status.Roost;
import moves.status.Screech;
import moves.status.SunnyDay;
import moves.status.Supersonic;
import moves.status.Swagger;
import moves.status.Toxic;
import moves.status.protecting.Detect;
import moves.status.protecting.Protect;
import types.Bug;
import types.Flying;
import types.Type;

public class Yanma extends Pokemon{

    public Yanma(int level) {
        super(  level,                                                                                          //level
                65,                                                                                             //baseHP 
                65,                                                                                             //baseAtk 
                45,                                                                                             //baseDef 
                95,                                                                                             //baseSpe
                75,                                                                                             //baseSpA 
                45,                                                                                             //baseSpD 
                new Type[]{new Bug(), new Flying()},                                                            //type
                Ability.getRandomAbility(new Ability[]{new SpeedBoost(), new CompoundEyes()}),                  //ability
                38,                                                                                             //weight (Kg)
                1,                                                                                              //miniFrontSizeScale
                Gender.getRandomGender(),                                                                       //gender  
                new HashSet<Move>(                                                                              //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Tackle(),
                                        new QuickAttack(),
                                        new DoubleTeam(),
                                        new SonicBoom(),
                                        new Detect(),
                                        new Supersonic(),
                                        new Pursuit(),
                                        new AncientPower(),
                                        new Hypnosis(),
                                        new WingAttack(),
                                        new Screech(),
                                        new AirSlash(),
                                        /*new BugBuzz(),*/
                                        new Toxic(),
                                        new Protect(),
                                        new Roost(),
                                        new SunnyDay(),
                                        new Psychic(),
                                        new ShadowBall(),
                                        new AerialAce(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new Thief(),
                                        new SteelWing(),
                                        new Swagger(),
                                        new DreamEater(),
                                        new DoubleEdge(),
                                        new LeechLife(),
                                        new Reversal(),
                                        new SignalBeam(),
                                }
                                )
                        )
                );
    }

}
