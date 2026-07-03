package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import abilities.Ability;
import abilities.movecondition.Levitate;
import moves.Move;
import moves.damagingmove.physical.Astonish;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.FirePunch;
import moves.damagingmove.physical.IcePunch;
import moves.damagingmove.physical.Lick;
import moves.damagingmove.physical.ShadowClaw;
import moves.damagingmove.physical.ShadowPunch;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.physical.ThunderPunch;
import moves.damagingmove.physical.selfko.Explosion;
import moves.damagingmove.special.DarkPulse;
import moves.damagingmove.special.DazzlingGleam;
import moves.damagingmove.special.EnergyBall;
import moves.damagingmove.special.ShadowBall;
import moves.damagingmove.special.SludgeBomb;
import moves.damagingmove.special.Smog;
import moves.damagingmove.special.Thunderbolt;
import moves.damagingmove.special.sleeprequired.DreamEater;
import moves.status.Attract;
import moves.status.ConfuseRay;
import moves.status.Curse;
import moves.status.DoubleTeam;
import moves.status.Haze;
import moves.status.Hypnosis;
import moves.status.MeanLook;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.ScaryFace;
import moves.status.Spite;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.Toxic;
import moves.status.WillOWisp;
import moves.status.protecting.Protect;
import types.Ghost;
import types.Poison;
import types.Type;

public class Gengar extends Pokemon {

    public Gengar(int level) {
        super(level,
                60,		                                                                  		//hp
                65,		                                                                  		//atk
                60,		                                                                  		//def
                110,		                                                                  	        //speed
                130,		                                                                  	        //spa
                75,		                                                                  		//spd
                new Type[]{new Ghost(), new Poison()},		                                 	        //tipo,
                Ability.getRandomAbility(new Ability[]{new Levitate()}),  					//ability
                40,	                                                                       		        //weight(kg)
                1,                                                                                              //miniFrontSizeScale
                Gender.getRandomGender(),	                                                  	        //gender
                new HashSet<Move>(                                                                              //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Hypnosis(),
                                        new Lick(),
                                        new Spite(),
                                        new MeanLook(),
                                        new ShadowPunch(),
                                        new Curse(new Type[]{new Ghost(), new Poison()}),
                                        new ConfuseRay(),
                                        new ShadowBall(),
                                        new DarkPulse(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new Protect(),
                                        new RainDance(),
                                        new moves.damagingmove.special.Psychic(),
                                        new Thunderbolt(),
                                        new DoubleTeam(),
                                        new SludgeBomb(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new Thief(),
                                        new EnergyBall(),
                                        new WillOWisp(),
                                        new DazzlingGleam(),
                                        new DreamEater(),
                                        new Swagger(),
                                        new Astonish(),
                                        new FirePunch(),
                                        new Haze(),
                                        new IcePunch(),
                                        new ScaryFace(),
                                        new ShadowClaw(),
                                        new Explosion(),
                                        new Smog(),
                                        new ThunderPunch()
                                }
                                )
                        )
                );
    }

}
