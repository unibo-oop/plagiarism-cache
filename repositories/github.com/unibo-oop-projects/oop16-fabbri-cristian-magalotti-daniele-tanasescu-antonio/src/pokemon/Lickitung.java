package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import abilities.Ability;
import abilities.firstturn.alwaysactive.CloudNine;
import abilities.movecondition.WaterAbsorb;
import abilities.statusalterationcondition.Oblivious;
import moves.Move;
import moves.damagingmove.physical.BodySlam;
import moves.damagingmove.physical.BrickBreak;
import moves.damagingmove.physical.DragonTail;
import moves.damagingmove.physical.Earthquake;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.KnockOff;
import moves.damagingmove.physical.Lick;
import moves.damagingmove.physical.RockSlide;
import moves.damagingmove.physical.RockTomb;
import moves.damagingmove.physical.Slam;
import moves.damagingmove.physical.SmellingSalts;
import moves.damagingmove.physical.Stomp;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.physical.ZenHeadbutt;
import moves.damagingmove.special.Blizzard;
import moves.damagingmove.special.FireBlast;
import moves.damagingmove.special.Flamethrower;
import moves.damagingmove.special.IceBeam;
import moves.damagingmove.special.MuddyWater;
import moves.damagingmove.special.ShadowBall;
import moves.damagingmove.special.Surf;
import moves.damagingmove.special.Thunder;
import moves.damagingmove.special.Thunderbolt;
import moves.damagingmove.special.sleeprequired.DreamEater;
import moves.status.Amnesia;
import moves.status.Attract;
import moves.status.BellyDrum;
import moves.status.Curse;
import moves.status.DoubleTeam;
import moves.status.PsychUp;
import moves.status.RainDance;
import moves.status.Refresh;
import moves.status.Rest;
import moves.status.Sandstorm;
import moves.status.Screech;
import moves.status.SunnyDay;
import moves.status.Supersonic;
import moves.status.Swagger;
import moves.status.SwordsDance;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Normal;
import types.Type;

public class Lickitung extends Pokemon {

    public Lickitung(int level) {
        super(level,
                90,		                                                              			//hp
                55,		                                                              			//atk
                75,		                                                              			//def
                30,		                                                              			//speed
                60,		                                                              			//spa
                75,		                                                              			//spd
                new Type[]{new Normal(), null},		                                      		        //tipo
                Ability.getRandomAbility(new Ability[]{new Oblivious(),new CloudNine()}),                       //ability
                65.5,	                                                                                  	//weight(kg)
                1,                                                                                              //miniFrontSizeScale
                Gender.getRandomGender(),	                                              		        //gender
                new HashSet<Move>(                                                            	                //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Lick(),
                                        new Supersonic(),
                                        new KnockOff(),
                                        new Stomp(),
                                        new Slam(),
                                        new Refresh(),
                                        new Screech(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new IceBeam(),
                                        new Blizzard(),
                                        new Protect(),
                                        new RainDance(),
                                        new Thunderbolt(),
                                        new Thunder(),
                                        new Earthquake(),
                                        new ShadowBall(),
                                        new BrickBreak(),
                                        new DoubleTeam(),
                                        new Flamethrower(),
                                        new Sandstorm(),
                                        new FireBlast(),
                                        new RockTomb(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new Thief(),
                                        new SwordsDance(),
                                        new PsychUp(),
                                        new RockSlide(),
                                        new DragonTail(),
                                        new DreamEater(),
                                        new Swagger(),
                                        new Surf(),
                                        new Amnesia(),
                                        new BellyDrum(),
                                        new BodySlam(),
                                        new Curse(new Type[]{new Normal(), null}),
                                        new MuddyWater(),
                                        new SmellingSalts(),
                                        new ZenHeadbutt()
                                }
                                )
                        )
                );
    }

}
