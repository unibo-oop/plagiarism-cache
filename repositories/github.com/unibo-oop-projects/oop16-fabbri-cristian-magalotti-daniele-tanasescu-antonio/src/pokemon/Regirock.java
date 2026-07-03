package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import abilities.Ability;
import abilities.firstturn.LightMetal;
import abilities.statisticsalterationondemand.ClearBody;
import moves.Move;
import moves.damagingmove.physical.AerialAce;
import moves.damagingmove.physical.BrickBreak;
import moves.damagingmove.physical.Bulldoze;
import moves.damagingmove.physical.Earthquake;
import moves.damagingmove.physical.HammerArm;
import moves.damagingmove.physical.MetalClaw;
import moves.damagingmove.physical.RockSlide;
import moves.damagingmove.physical.RockTomb;
import moves.damagingmove.physical.ShadowClaw;
import moves.damagingmove.physical.Stomp;
import moves.damagingmove.physical.StoneEdge;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.physical.multistrike.twotofive.RockBlast;
import moves.damagingmove.physical.selfko.Explosion;
import moves.damagingmove.physical.selfko.SelfDestruct;
import moves.damagingmove.physical.selfrecoil.DoubleEdge;
import moves.damagingmove.special.AncientPower;
import moves.damagingmove.special.FireBlast;
import moves.damagingmove.special.Flamethrower;
import moves.damagingmove.special.FlashCannon;
import moves.damagingmove.special.FocusBlast;
import moves.damagingmove.special.Thunder;
import moves.damagingmove.special.Thunderbolt;
import moves.damagingmove.special.ZapCannon;
import moves.status.Amnesia;
import moves.status.Attract;
import moves.status.Automize;
import moves.status.Curse;
import moves.status.DefenseCurl;
import moves.status.DoubleTeam;
import moves.status.IronDefense;
import moves.status.Rest;
import moves.status.Sandstorm;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Rock;
import types.Steel;
import types.Type;

public class Regirock extends Pokemon {

    public Regirock(int level) {
        super(level,
                80,		                                                                           		//hp
                100,		                                                                           		//atk
                200,		                                                                           		//def
                50,		                                                                           		//speed
                50,		                                                                           		//spa
                100,		                                                                           		//spd
                new Type[]{new Rock(), null},		                                                 		//tipo
                Ability.getRandomAbility(new Ability[]{new ClearBody()}),	 					//ability
                230,	                                                                                  	 	//weight(kg)
                0.9,                                                                                                    //miniFrontSizeScale
                Gender.GENDERLESS,	                                                           			//gender
                new HashSet<Move>(                                                                         		//learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Stomp(),
                                        new IronDefense(),
                                        new AncientPower(),
                                        new Amnesia(),
                                        new Thunder(),
                                        new Thunderbolt(),
                                        new AerialAce(),
                                        new ShadowClaw(),
                                        new DefenseCurl(),
                                        new Bulldoze(),
                                        new RockBlast(),
                                        new Explosion(),
                                        new SelfDestruct(),
                                        new DoubleEdge(),
                                        new StoneEdge(),
                                        new Earthquake(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new Protect(),
                                        new DoubleTeam(),
                                        new Sandstorm(),
                                        new RockTomb(),
                                        new BrickBreak(),
                                        new Flamethrower(),
                                        new FireBlast(),
                                        new FocusBlast(),
                                        new Rest(),
                                        new Attract(),
                                        new Thief(),
                                        new RockSlide(),
                                        new Swagger(),
                                        new Automize(),
                                        new Curse(new Type[]{new Rock(), null}),
                                        new HammerArm(),
                                }
                                )
                        )
                );
    }

}
