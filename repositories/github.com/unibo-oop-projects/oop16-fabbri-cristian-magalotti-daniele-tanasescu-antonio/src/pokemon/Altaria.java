package pokemon;

import java.util.Arrays;
import java.util.HashSet;

import abilities.Ability;
import abilities.firstturn.alwaysactive.CloudNine;
import abilities.switchcondition.NaturalCure;
import moves.Move;
import moves.damagingmove.physical.AerialAce;
import moves.damagingmove.physical.Astonish;
import moves.damagingmove.physical.DragonClaw;
import moves.damagingmove.physical.DragonRush;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.Peck;
import moves.damagingmove.physical.Rage;
import moves.damagingmove.physical.SteelWing;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.physical.multistrike.twotofive.FuryAttack;
import moves.damagingmove.physical.selfrecoil.TakeDown;
import moves.damagingmove.physical.variablepriority.Pursuit;
import moves.damagingmove.special.DazzlingGleam;
import moves.damagingmove.special.DisarmingVoice;
import moves.damagingmove.special.DragonBreath;
import moves.damagingmove.special.DragonPulse;
import moves.damagingmove.special.HyperVoice;
import moves.damagingmove.special.IceBeam;
import moves.damagingmove.special.MistBall;
import moves.damagingmove.special.Moonblast;
import moves.damagingmove.special.sleeprequired.DreamEater;
import moves.status.Agility;
import moves.status.Attract;
import moves.status.CottonSpore;
import moves.status.DoubleTeam;
import moves.status.FeatherDance;
import moves.status.Growl;
import moves.status.Haze;
import moves.status.MirrorMove;
import moves.status.RainDance;
import moves.status.Refresh;
import moves.status.Rest;
import moves.status.Roost;
import moves.status.Sing;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Dragon;
import types.Flying;
import types.Type;

public class Altaria extends Pokemon {

    public Altaria(int level) {
        super(level,
                75,		                                                       			//hp
                70,		                                                       			//atk
                90,		                                                       			//def
                80,		                                                       			//speed
                70,		                                                       			//spa
                105,		                                                       			//spd
                new Type[]{new Dragon(), new Flying()},		                       			//tipo
                Ability.getRandomAbility(new Ability[]{new NaturalCure(), new CloudNine()}),            //ability
                20,	                                                               			//weight(kg)
                0.9,                                                                                    //miniFrontSizeScale
                Gender.getRandomGender(),	                                       			//gender
                new HashSet<Move>(                                                     		        //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Peck(),
                                        new DragonBreath(),
                                        new DragonClaw(),
                                        new Growl(),
                                        new Astonish(),
                                        new Sing(),
                                        new FuryAttack(),
                                        new AerialAce(),
                                        new DisarmingVoice(),
                                        new MistBall(),
                                        new Refresh(),
                                        new TakeDown(),
                                        new CottonSpore(),
                                        new DragonPulse(),
                                        new Moonblast(),
                                        new MirrorMove(),
                                        new Agility(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new Protect(),
                                        new RainDance(),
                                        new IceBeam(),
                                        new Roost(),
                                        new AerialAce(),
                                        new DazzlingGleam(),
                                        new DreamEater(),
                                        new DoubleTeam(),
                                        new Facade(),
                                        new Rest(),
                                        new DragonRush(),
                                        new Haze(),
                                        new HyperVoice(),
                                        new Pursuit(),
                                        new Rage(),
                                        new Attract(),
                                        new Thief(),
                                        new Swagger(),
                                        new FeatherDance(),
                                        new SteelWing(),
                                }
                                )
                        )
                );
    }

}
