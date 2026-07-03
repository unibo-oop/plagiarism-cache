package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import abilities.Ability;
import abilities.firstturn.HugePower;
import moves.Move;
import moves.damagingmove.physical.AerialAce;
import moves.damagingmove.physical.Bite;
import moves.damagingmove.physical.BrickBreak;
import moves.damagingmove.physical.Bulldoze;
import moves.damagingmove.physical.CrossChop;
import moves.damagingmove.physical.Crunch;
import moves.damagingmove.physical.Earthquake;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.Lick;
import moves.damagingmove.physical.MetalClaw;
import moves.damagingmove.physical.PlayRough;
import moves.damagingmove.physical.RockSlide;
import moves.damagingmove.physical.RockTomb;
import moves.damagingmove.physical.Scratch;
import moves.damagingmove.physical.ShadowClaw;
import moves.damagingmove.physical.Slash;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.physical.bypassprotect.FeintAttack;
import moves.damagingmove.physical.counterattacking.Counter;
import moves.damagingmove.physical.multistrike.twotofive.FurySwipes;
import moves.damagingmove.physical.selfrecoil.DoubleEdge;
import moves.damagingmove.physical.selfrecoil.TakeDown;
import moves.status.Attract;
import moves.status.BellyDrum;
import moves.status.BulkUp;
import moves.status.Charm;
import moves.status.DoubleTeam;
import moves.status.FakeTears;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.Screech;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.SwordsDance;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Normal;
import types.Type;

public class Teddiursa extends Pokemon {

    public Teddiursa(int level) {
        super(level,
                60,		                                                                   	//hp
                80,		                                                                   	//atk
                50,		                                                                   	//def
                40,		                                                                   	//speed
                50,		                                                                   	//spa
                50,		                                                                   	//spd
                new Type[]{new Normal(), null},		                                           	//tipo
                Ability.getRandomAbility(new Ability[]{new HugePower()}),		                //ability
                9,	                                                                        	//weight(kg)
                1,                                                                                      //sizeScale
                Gender.getRandomGender(),	                                                  	//gender
                new HashSet<Move>(                                                                      //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Scratch(),
                                        new Lick(),
                                        new Bite(),
                                        new FakeTears(),
                                        new FurySwipes(),
                                        new Screech(),
                                        new FeintAttack(),
                                        new Slash(),
                                        new Charm(),
                                        /*new WorkUp(),*/
                                        new BulkUp(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new Protect(),
                                        new RainDance(),
                                        new Earthquake(),
                                        new BrickBreak(),
                                        new RockTomb(),
                                        new ShadowClaw(),
                                        new Bulldoze(),
                                        new RockSlide(),
                                        new SwordsDance(),
                                        new DoubleTeam(),
                                        new AerialAce(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new Thief(),
                                        new Swagger(),
                                        new BellyDrum(),
                                        /*new CloseCombat(),*/
                                        new Counter(),
                                        new CrossChop(),
                                        new Crunch(),
                                        new DoubleEdge(),
                                        new MetalClaw(),
                                        /*new NightSlash(),*/
                                        new PlayRough(),
                                        new TakeDown(),
                                }
                                )
                        )
                );
    }

}
