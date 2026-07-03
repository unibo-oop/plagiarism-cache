package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import abilities.Ability;
import abilities.firstturn.Intimidate;
import abilities.weathercondition.RainDish;
import abilities.weathercondition.SwiftSwim;
import moves.Move;
import moves.damagingmove.physical.AerialAce;
import moves.damagingmove.physical.AquaJet;
import moves.damagingmove.physical.BugBite;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.QuickAttack;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.special.AirCutter;
import moves.damagingmove.special.AirSlash;
import moves.damagingmove.special.Blizzard;
import moves.damagingmove.special.Bubble;
import moves.damagingmove.special.BubbleBeam;
import moves.damagingmove.special.EnergyBall;
import moves.damagingmove.special.Gust;
import moves.damagingmove.special.HydroPump;
import moves.damagingmove.special.IceBeam;
import moves.damagingmove.special.MudShot;
import moves.damagingmove.special.Psybeam;
import moves.damagingmove.special.Scald;
import moves.damagingmove.special.ShadowBall;
import moves.damagingmove.special.SignalBeam;
import moves.damagingmove.special.SilverWind;
import moves.damagingmove.special.hpdependent.WaterSprout;
import moves.status.Agility;
import moves.status.Attract;
import moves.status.DoubleTeam;
import moves.status.Haze;
import moves.status.PsychUp;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.Roost;
import moves.status.ScaryFace;
import moves.status.StunSpore;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.SweetScent;
import moves.status.Toxic;
import moves.status.Whirlwind;
import moves.status.protecting.Protect;
import types.Bug;
import types.Flying;
import types.Type;
import types.Water;

public class Masquerain extends Pokemon {

	public Masquerain(int level) {
		super(level,
                70,		                                                              	//hp
                60,		                                                              	//atk
                62,		                                                              	//def
                60,		                                                              	//speed
                80,		                                                              	//spa
                82,		                                                              	//spd
                new Type[]{new Bug(), new Flying()},		                                //tipo
                Ability.getRandomAbility(new Ability[]{new Intimidate()}),     	                //ability
                3.6,	                                                                      	//weight(kg)
                0.8,                                                                            //miniFrontSizeScale
                Gender.getRandomGender(),	                                              	//gender
                new HashSet<Move>(                                                              //learnable moves
                        Arrays.asList(
                                new Move[]{
                                	new Whirlwind(),
                                        new Bubble(),
                                        new QuickAttack(),
                                        new SweetScent(),
                                        new WaterSprout(),
                                        new Gust(),
                                        new ScaryFace(),
                                        new AirCutter(),
                                        new StunSpore(),
                                        new SilverWind(),
                                        new AirSlash(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new IceBeam(),
                                        new Blizzard(),
                                        new Protect(),
                                        new RainDance(),
                                        new Roost(),
                                        new ShadowBall(),
                                        new DoubleTeam(),
                                        new AerialAce(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new Thief(),
                                        new EnergyBall(),
                                        new Scald(),
                                        new PsychUp(),
                                        new Swagger(),
                                        new AquaJet(),
                                        new BugBite(),
                                        new HydroPump(),
                                        new MudShot(),
                                        new Psybeam(),
                                        new SignalBeam(),
                                        new BubbleBeam(),
                                        new Agility(),
                                        new Haze()
                                }
                        )
                )
        );
	}

}
