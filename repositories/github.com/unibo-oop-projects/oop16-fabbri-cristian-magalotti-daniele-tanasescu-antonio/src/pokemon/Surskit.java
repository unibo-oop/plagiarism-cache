package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import abilities.Ability;
import abilities.movecondition.ShieldDust;
import abilities.switchcondition.RunAway;
import abilities.weathercondition.RainDish;
import abilities.weathercondition.SwiftSwim;
import moves.Move;
import moves.damagingmove.physical.AquaJet;
import moves.damagingmove.physical.BugBite;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.QuickAttack;
import moves.damagingmove.physical.Tackle;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.physical.multistrike.twotofive.FuryAttack;
import moves.damagingmove.special.Blizzard;
import moves.damagingmove.special.Bubble;
import moves.damagingmove.special.BubbleBeam;
import moves.damagingmove.special.HydroPump;
import moves.damagingmove.special.IceBeam;
import moves.damagingmove.special.MudShot;
import moves.damagingmove.special.Psybeam;
import moves.damagingmove.special.Scald;
import moves.damagingmove.special.ShadowBall;
import moves.damagingmove.special.SignalBeam;
import moves.damagingmove.special.hpdependent.WaterSprout;
import moves.status.Agility;
import moves.status.Attract;
import moves.status.DoubleTeam;
import moves.status.Haze;
import moves.status.PsychUp;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.StringShot;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.SweetScent;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Bug;
import types.Type;
import types.Water;

public class Surskit extends Pokemon {

	public Surskit(int level) {
		super(level,
                40,		                                                              			//hp
                30,		                                                              			//atk
                32,		                                                              			//def
                65,		                                                              			//speed
                50,		                                                              			//spa
                52,		                                                              			//spd
                new Type[]{new Bug(), new Water()},		                                      	//tipo
                Ability.getRandomAbility(new Ability[]{new SwiftSwim(), new RainDish()}),     	//ability
                1.7,	                                                                      	//weight(kg)
                0.5,                                                                            //miniFrontSizeScale
                Gender.getRandomGender(),	                                              		//gender
                new HashSet<Move>(                                                              //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Bubble(),
                                        new QuickAttack(),
                                        new SweetScent(),
                                        new WaterSprout(),
                                        new BubbleBeam(),
                                        new Agility(),
                                        new Haze(),
                                        new AquaJet(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new IceBeam(),
                                        new Blizzard(),
                                        new Protect(),
                                        new RainDance(),
                                        new ShadowBall(),
                                        new DoubleTeam(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new Thief(),
                                        new Scald(),
                                        new PsychUp(),
                                        new Swagger(),
                                        new BugBite(),
                                        new HydroPump(),
                                        new MudShot(),
                                        new Psybeam(),
                                        new SignalBeam()
                                }
                        )
                )
        );
	}

}
