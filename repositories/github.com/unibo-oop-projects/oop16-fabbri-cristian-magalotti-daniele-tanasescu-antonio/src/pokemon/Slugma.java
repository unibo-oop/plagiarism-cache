package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import abilities.Ability;
import abilities.movecondition.FlameBody;
import abilities.movecondition.WeakArmor;
import abilities.statusalterationcondition.MagmaArmor;
import moves.Move;
import moves.damagingmove.physical.BodySlam;
import moves.damagingmove.physical.CrushClaw;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.FlameWheel;
import moves.damagingmove.physical.QuickAttack;
import moves.damagingmove.physical.RockSlide;
import moves.damagingmove.physical.hpdependent.Reversal;
import moves.damagingmove.physical.multistrike.two.DoubleKick;
import moves.damagingmove.physical.multistrike.twotofive.FurySwipes;
import moves.damagingmove.physical.selfrecoil.DoubleEdge;
import moves.damagingmove.physical.selfrecoil.WildCharge;
import moves.damagingmove.special.AncientPower;
import moves.damagingmove.special.EarthPower;
import moves.damagingmove.special.Ember;
import moves.damagingmove.special.Extrasensory;
import moves.damagingmove.special.FireBlast;
import moves.damagingmove.special.Flamethrower;
import moves.damagingmove.special.HeatWave;
import moves.damagingmove.special.Overheat;
import moves.status.Amnesia;
import moves.status.Attract;
import moves.status.Curse;
import moves.status.DoubleTeam;
import moves.status.Howl;
import moves.status.Memento;
import moves.status.NaturePower;
import moves.status.Recover;
import moves.status.Rest;
import moves.status.SmokeScreen;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.Toxic;
import moves.status.WillOWisp;
import moves.status.protecting.Protect;
import types.Fire;
import types.Type;

public class Slugma extends Pokemon {

	public Slugma(int level) {
		super(level,
                40,		                                                              				//hp
                40,		                                                              				//atk
                40,		                                                              				//def
                20,		                                                              				//speed
                70,		                                                              				//spa
                40,		                                                              				//spd
                new Type[]{new Fire(), null},		                                      				//tipo
                Ability.getRandomAbility(new Ability[]{new MagmaArmor(), new FlameBody(), new WeakArmor()}),            //ability
                35,	                                                                      				//weight(kg)
                1,                                                                                                      //miniFrontSizeScale
                Gender.getRandomGender(),	                                              				//gender
                new HashSet<Move>(                                                            				//learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new SmokeScreen(),
                                        new Ember(),
                                        /*new FlameCharge(),*/
                                        new FlameWheel(),
                                        new Flamethrower(),
                                        new RockSlide(),
                                        new AncientPower(),
                                        new Amnesia(),
                                        new BodySlam(),
                                        new Recover(),
                                        new EarthPower(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new Protect(),
                                        new DoubleTeam(),
                                        new FireBlast(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new Overheat(),
                                        new WillOWisp(),
                                        new Swagger(),
                                        new WildCharge(),
                                        new NaturePower(),
                                        new CrushClaw(),
                                        new DoubleEdge(),
                                        new DoubleKick(),
                                        new Extrasensory(),
                                        new FurySwipes(),
                                        new Howl(),
                                        new QuickAttack(),
                                        new Reversal(),
                                        new Curse(new Type[]{new Fire(), null}),
                                        new HeatWave(),
                                        new Memento(),
                                        
                                }
                        )
                )
        );
	}

}
