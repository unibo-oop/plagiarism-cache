package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import abilities.Ability;
import abilities.firstturn.CompoundEyes;
import abilities.movecondition.ShieldDust;
import moves.Move;
import moves.damagingmove.physical.Acrobatics;
import moves.damagingmove.physical.AerialAce;
import moves.damagingmove.physical.BugBite;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.LeechLife;
import moves.damagingmove.physical.PoisonFang;
import moves.damagingmove.physical.Tackle;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.physical.ZenHeadbutt;
import moves.damagingmove.special.Confusion;
import moves.damagingmove.special.EnergyBall;
import moves.damagingmove.special.GigaDrain;
import moves.damagingmove.special.Gust;
import moves.damagingmove.special.Psybeam;
import moves.damagingmove.special.Psychic;
import moves.damagingmove.special.SignalBeam;
import moves.damagingmove.special.SilverWind;
import moves.damagingmove.special.SludgeBomb;
import moves.status.Agility;
import moves.status.Attract;
import moves.status.DoubleTeam;
import moves.status.MorningSun;
import moves.status.PoisonPowder;
import moves.status.Rest;
import moves.status.Roost;
import moves.status.Screech;
import moves.status.SkillSwap;
import moves.status.SleepPowder;
import moves.status.StunSpore;
import moves.status.SunnyDay;
import moves.status.Supersonic;
import moves.status.Swagger;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Bug;
import types.Poison;
import types.Type;

public class Venomoth extends Pokemon {

    public Venomoth(int level) {
        super(level,
                70,		                                                              			//hp
                65,		                                                              			//atk
                60,		                                                              			//def
                90,		                                                              			//speed
                90,		                                                              			//spa
                75,		                                                              			//spd
                new Type[]{new Bug(), new Poison()},		                                                //tipo
                Ability.getRandomAbility(new Ability[]{new ShieldDust(), new CompoundEyes()}),     		//ability
                12.5,	                                                                      	                //weight(kg)
                1,                                                                                              //miniFrontSizeScale
                Gender.getRandomGender(),	                                              		        //gender
                new HashSet<Move>(                                                            	                //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Gust(),
                                        new SilverWind(),
                                        new Tackle(),
                                        new Supersonic(),
                                        new Confusion(),
                                        new PoisonPowder(),
                                        new Psybeam(),
                                        new StunSpore(),
                                        new SignalBeam(),
                                        new SleepPowder(),
                                        new LeechLife(),
                                        new ZenHeadbutt(),
                                        new PoisonFang(),
                                        new Psychic(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new Protect(),
                                        new Roost(),
                                        new LeechLife(),
                                        new DoubleTeam(),
                                        new SludgeBomb(),
                                        new AerialAce(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new Thief(),
                                        new EnergyBall(),
                                        new Acrobatics(),
                                        new Swagger(),
                                        new Agility(),
                                        new BugBite(),
                                        new GigaDrain(),
                                        new MorningSun(),
                                        new Screech(),
                                        new SignalBeam(),
                                        new SkillSwap()
                                }
                                )
                        )
                );
    }

}
