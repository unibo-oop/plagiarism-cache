package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import abilities.Ability;
import abilities.firstturn.HeavyMetal;
import abilities.otherconditions.RockHead;
import moves.Move;
import moves.damagingmove.physical.AerialAce;
import moves.damagingmove.physical.BodySlam;
import moves.damagingmove.physical.BrickBreak;
import moves.damagingmove.physical.DragonClaw;
import moves.damagingmove.physical.DragonRush;
import moves.damagingmove.physical.DragonTail;
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
import moves.damagingmove.physical.StoneEdge;
import moves.damagingmove.physical.Superpower;
import moves.damagingmove.physical.Tackle;
import moves.damagingmove.physical.hpdependent.Reversal;
import moves.damagingmove.physical.selfrecoil.DoubleEdge;
import moves.damagingmove.physical.selfrecoil.TakeDown;
import moves.damagingmove.special.Blizzard;
import moves.damagingmove.special.DarkPulse;
import moves.damagingmove.special.FireBlast;
import moves.damagingmove.special.Flamethrower;
import moves.damagingmove.special.FlashCannon;
import moves.damagingmove.special.FocusBlast;
import moves.damagingmove.special.IceBeam;
import moves.damagingmove.special.MudSlap;
import moves.damagingmove.special.Surf;
import moves.damagingmove.special.Thunder;
import moves.damagingmove.special.Thunderbolt;
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
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.ThunderWave;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Rock;
import types.Steel;
import types.Type;

public class Aggron extends Pokemon {

    public Aggron(int level) {
        super(level,
                70,		                                                              	//hp
                110,		                                                              	//atk
                180,		                                                              	//def
                50,		                                                                //speed
                60,		                                                              	//spa
                60,		                                                              	//spd
                new Type[]{new Steel(), new Rock()},		                                //tipo
                Ability.getRandomAbility(new Ability[]{new RockHead(), new HeavyMetal()}),     	//ability
                360,	                                                                      	//weight(kg)
                0.9,                                                                            //miniFrontSizeScale
                Gender.getRandomGender(),	                                              	//gender
                new HashSet<Move>(                                                              //learnable moves
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
                                        new DragonClaw(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new IceBeam(),
                                        new Blizzard(),
                                        new RainDance(),
                                        new Thunderbolt(),
                                        new Thunder(),
                                        new Earthquake(),
                                        new BrickBreak(),
                                        new DoubleTeam(),
                                        new Flamethrower(),
                                        new Sandstorm(),
                                        new AerialAce(),
                                        new FireBlast(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new FocusBlast(),
                                        new ShadowClaw(),
                                        new RockPolish(),
                                        new StoneEdge(),
                                        new ThunderWave(),
                                        new DragonTail(),
                                        new Swagger(),
                                        new FlashCannon(),
                                        new Surf(),
                                        new DarkPulse(),
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
