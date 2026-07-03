package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import abilities.Ability;
import abilities.firstturn.HeavyMetal;
import abilities.movecondition.ShieldDust;
import abilities.otherconditions.RockHead;
import abilities.switchcondition.RunAway;
import moves.Move;
import moves.damagingmove.physical.AerialAce;
import moves.damagingmove.physical.BodySlam;
import moves.damagingmove.physical.BugBite;
import moves.damagingmove.physical.DragonRush;
import moves.damagingmove.physical.Earthquake;
import moves.damagingmove.physical.Endeavor;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.Headbutt;
import moves.damagingmove.physical.IronTail;
import moves.damagingmove.physical.MetalClaw;
import moves.damagingmove.physical.RockSlide;
import moves.damagingmove.physical.RockTomb;
import moves.damagingmove.physical.ShadowClaw;
import moves.damagingmove.physical.SmellingSalts;
import moves.damagingmove.physical.Stomp;
import moves.damagingmove.physical.Superpower;
import moves.damagingmove.physical.Tackle;
import moves.damagingmove.physical.hpdependent.Reversal;
import moves.damagingmove.physical.multistrike.twotofive.FuryAttack;
import moves.damagingmove.physical.selfrecoil.DoubleEdge;
import moves.damagingmove.physical.selfrecoil.TakeDown;
import moves.damagingmove.special.MudSlap;
import moves.status.Attract;
import moves.status.DoubleTeam;
import moves.status.Harden;
import moves.status.IronDefense;
import moves.status.MetalSound;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.Roar;
import moves.status.RockPolish;
import moves.status.Sandstorm;
import moves.status.Screech;
import moves.status.StringShot;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import status_condition.volatile_status.Curse;
import types.Rock;
import types.Steel;
import types.Type;

public class Aron extends Pokemon {

	public Aron(int level) {
		super(level,
                50,		                                                              		//hp
                70,		                                                              		//atk
                100,		                                                              		//def
                30,		                                                              		//speed
                40,		                                                              		//spa
                40,		                                                              		//spd
                new Type[]{new Steel(), new Rock()},		                                        //tipo
                Ability.getRandomAbility(new Ability[]{new RockHead(), new HeavyMetal()}),     		//ability
                60,	                                                                      		//weight(kg)
                1,                                                                                      //miniFrontSizeScale
                Gender.getRandomGender(),	                                              		//gender
                new HashSet<Move>(                                                                      //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Tackle(),
                                        new Harden(),
                                        new MudSlap(),
                                        new Headbutt(),
                                        new MetalClaw(),
                                        new RockTomb(),
                                        new Protect(),
                                        new Roar(),
                                        new RockSlide(),
                                        new TakeDown(),
                                        new MetalSound(),
                                        new IronTail(),
                                        new IronDefense(),
                                        new DoubleEdge(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new RainDance(),
                                        new Earthquake(),
                                        new DoubleTeam(),
                                        new Sandstorm(),
                                        new AerialAce(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new ShadowClaw(),
                                        new RockPolish(),
                                        new Swagger(),
                                        new BodySlam(),
                                        new DragonRush(),
                                        new Endeavor(),
                                        new Reversal(),
                                        new Screech(),
                                        new SmellingSalts(),
                                        new Stomp(),
                                        new Superpower()
                                }
                        )
                )
        );
	}

}
