package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import abilities.Ability;
import abilities.endofturnconditionability.SpeedBoost;
import abilities.hpcondition.Blaze;
import moves.Move;
import moves.damagingmove.physical.Acrobatics;
import moves.damagingmove.physical.AerialAce;
import moves.damagingmove.physical.BlazeKick;
import moves.damagingmove.physical.BrickBreak;
import moves.damagingmove.physical.CrushClaw;
import moves.damagingmove.physical.Earthquake;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.FirePunch;
import moves.damagingmove.physical.Peck;
import moves.damagingmove.physical.PoisonJab;
import moves.damagingmove.physical.QuickAttack;
import moves.damagingmove.physical.RockSlide;
import moves.damagingmove.physical.RockTomb;
import moves.damagingmove.physical.Scratch;
import moves.damagingmove.physical.ShadowClaw;
import moves.damagingmove.physical.SkyUppercut;
import moves.damagingmove.physical.Slash;
import moves.damagingmove.physical.SmellingSalts;
import moves.damagingmove.physical.StoneEdge;
import moves.damagingmove.physical.counterattacking.Counter;
import moves.damagingmove.physical.hpdependent.Reversal;
import moves.damagingmove.physical.multistrike.two.DoubleKick;
import moves.damagingmove.physical.selfrecoil.BraveBird;
import moves.damagingmove.physical.weightdependent.LowKick;
import moves.damagingmove.special.Ember;
import moves.damagingmove.special.FireBlast;
import moves.damagingmove.special.Flamethrower;
import moves.damagingmove.special.FocusBlast;
import moves.damagingmove.special.Overheat;
import moves.status.Agility;
import moves.status.Attract;
import moves.status.BulkUp;
import moves.status.Curse;
import moves.status.DoubleTeam;
import moves.status.FeatherDance;
import moves.status.Growl;
import moves.status.MirrorMove;
import moves.status.Rest;
import moves.status.Roar;
import moves.status.SandAttack;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.SwordsDance;
import moves.status.Toxic;
import moves.status.WillOWisp;
import moves.status.protecting.Protect;
import types.Fight;
import types.Fire;
import types.Type;

public class Blaziken extends Pokemon {

    public Blaziken(int level) {
        super(level,
                80,		                                                              	 //hp
                120,		                                                              	 //atk
                70,		                                                              	 //def
                80,		                                                              	 //speed
                110,		                                                              	 //spa
                70,		                                                              	 //spd
                new Type[]{new Fire(), new Fight()},		                                 //tipo
                Ability.getRandomAbility(new Ability[]{new Blaze(),new SpeedBoost()}),           //ability
                52,	                                                                      	 //weight(kg)
                0.8,                                                                             //miniFrontSizeScale
                Gender.getRandomGender(),	                                              	 //gender
                new HashSet<Move>(                                                            	 //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new BlazeKick(),
                                        new FirePunch(),
                                        new DoubleKick(),
                                        new Scratch(),
                                        new Growl(),
                                        new Ember(),
                                        new SandAttack(),
                                        new Peck(),
                                        new QuickAttack(),
                                        new BulkUp(),
                                        new Slash(),
                                        new BraveBird(),
                                        new SkyUppercut(),
                                        new Flamethrower(),
                                        new Toxic(),
                                        new Roar(),
                                        new SunnyDay(),
                                        new Protect(),
                                        new Earthquake(),
                                        new DoubleTeam(),
                                        new BrickBreak(),
                                        new FireBlast(),
                                        new RockTomb(),
                                        new AerialAce(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new Overheat(),
                                        new FocusBlast(),
                                        new WillOWisp(),
                                        new Acrobatics(),
                                        new ShadowClaw(),
                                        new StoneEdge(),
                                        new SwordsDance(),
                                        new RockSlide(),
                                        new PoisonJab(),
                                        new Swagger(),
                                        new Agility(),
                                        new Counter(),
                                        new CrushClaw(),
                                        new Curse(new Type[]{new Fire(), new Fight()}),
                                        new FeatherDance(),
                                        new LowKick(),
                                        new Reversal(),
                                        new SmellingSalts(),
                                        new MirrorMove()
                                }
                                )
                        )
                );
    }

}
