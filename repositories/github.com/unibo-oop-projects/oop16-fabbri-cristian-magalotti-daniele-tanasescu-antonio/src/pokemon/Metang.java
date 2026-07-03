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
import moves.damagingmove.physical.BulletPunch;
import moves.damagingmove.physical.Earthquake;
import moves.damagingmove.physical.IronHead;
import moves.damagingmove.physical.MetalClaw;
import moves.damagingmove.physical.MeteorMash;
import moves.damagingmove.physical.RockSlide;
import moves.damagingmove.physical.RockTomb;
import moves.damagingmove.physical.ShadowClaw;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.physical.ZenHeadbutt;
import moves.damagingmove.physical.selfrecoil.TakeDown;
import moves.damagingmove.physical.variablepriority.Pursuit;
import moves.damagingmove.special.AncientPower;
import moves.damagingmove.special.Confusion;
import moves.damagingmove.special.FlashCannon;
import moves.damagingmove.special.FocusBlast;
import moves.damagingmove.special.ShadowBall;
import moves.damagingmove.special.SludgeBomb;
import moves.status.Agility;
import moves.status.Amnesia;
import moves.status.Attract;
import moves.status.Automize;
import moves.status.Curse;
import moves.status.DoubleTeam;
import moves.status.IronDefense;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.RockPolish;
import moves.status.Sandstorm;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Psychic;
import types.Steel;
import types.Type;

public class Metang extends Pokemon {

    public Metang(int level) {
        super(level,
                60,		                                                                           		//hp
                75,		                                                                           		//atk
                100,		                                                                           		//def
                50,		                                                                           		//speed
                55,		                                                                           		//spa
                80,		                                                                           		//spd
                new Type[]{new Steel(), new Psychic()},		                                                   	//tipo
                Ability.getRandomAbility(new Ability[]{new ClearBody(), new LightMetal()}),	 			//ability
                200,	                                                                                  	 	//weight(kg)
                1,                                                                                                      //miniFrontSizeScale
                Gender.GENDERLESS,	                                                           			//gender
                new HashSet<Move>(                                                                         		//learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new TakeDown(),
                                        new Confusion(),
                                        new Pursuit(),
                                        new BulletPunch(),
                                        new ZenHeadbutt(),
                                        new MeteorMash(),
                                        new Agility(),
                                        new moves.damagingmove.special.Psychic(),
                                        new MetalClaw(),
                                        new IronDefense(),
                                        new AncientPower(),
                                        new IronHead(),
                                        new Amnesia(),
                                        new FlashCannon(),
                                        new AerialAce(),
                                        new ShadowBall(),
                                        new ShadowClaw(),
                                        //new GyroBall(),
                                        new RockPolish(),
                                        new SludgeBomb(),
                                        new RainDance(),
                                        //new GrassKnot(),
                                        new Bulldoze(),
                                        new Earthquake(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new Protect(),
                                        new DoubleTeam(),
                                        new Sandstorm(),
                                        new RockTomb(),
                                        new BrickBreak(),
                                        new FocusBlast(),
                                        new Rest(),
                                        new Attract(),
                                        new Thief(),
                                        new RockSlide(),
                                        new Swagger(),
                                        new Automize(),
                                        new Curse(new Type[]{new Steel(), new Psychic()}),
                                }
                                )
                        )
                );
    }

}
