package pokemon;

import java.util.Arrays;
import java.util.HashSet;

import abilities.Ability;
import abilities.movecondition.WaterAbsorb;
import abilities.otherconditions.MagicBounce;
import moves.Move;
import moves.damagingmove.physical.Acrobatics;
import moves.damagingmove.physical.AerialAce;
import moves.damagingmove.physical.BrickBreak;
import moves.damagingmove.physical.Bulldoze;
import moves.damagingmove.physical.DragonClaw;
import moves.damagingmove.physical.DragonTail;
import moves.damagingmove.physical.Earthquake;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.LeechLife;
import moves.damagingmove.physical.MegaKick;
import moves.damagingmove.physical.MegaPunch;
import moves.damagingmove.physical.PoisonJab;
import moves.damagingmove.physical.Pound;
import moves.damagingmove.physical.RockSlide;
import moves.damagingmove.physical.RockTomb;
import moves.damagingmove.physical.ShadowClaw;
import moves.damagingmove.physical.StoneEdge;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.physical.Waterfall;
import moves.damagingmove.physical.XScissor;
import moves.damagingmove.physical.selfko.Explosion;
import moves.damagingmove.physical.selfrecoil.WildCharge;
import moves.damagingmove.special.AncientPower;
import moves.damagingmove.special.AuraSphere;
import moves.damagingmove.special.Blizzard;
import moves.damagingmove.special.Confusion;
import moves.damagingmove.special.DarkPulse;
import moves.damagingmove.special.EnergyBall;
import moves.damagingmove.special.FireBlast;
import moves.damagingmove.special.Flamethrower;
import moves.damagingmove.special.FlashCannon;
import moves.damagingmove.special.FocusBlast;
import moves.damagingmove.special.IceBeam;
import moves.damagingmove.special.Overheat;
import moves.damagingmove.special.ShadowBall;
import moves.damagingmove.special.SludgeBomb;
import moves.damagingmove.special.Surf;
import moves.damagingmove.special.Thunder;
import moves.damagingmove.special.Thunderbolt;
import moves.damagingmove.special.sleeprequired.DreamEater;
import moves.status.Amnesia;
import moves.status.Attract;
import moves.status.BulkUp;
import moves.status.CalmMind;
import moves.status.DoubleTeam;
import moves.status.Hail;
import moves.status.NastyPlot;
import moves.status.PsychUp;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.Roar;
import moves.status.RockPolish;
import moves.status.Sandstorm;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.SwordsDance;
import moves.status.ThunderWave;
import moves.status.Toxic;
import moves.status.WillOWisp;
import moves.status.protecting.Protect;
import types.Psychic;
import types.Type;

public class Mew extends Pokemon{

    public Mew(int level) {
        super(  level,                                                                                                  //level
                100,                                                                                                    //baseHP 
                100,                                                                                                    //baseAtk 
                100,                                                                                                    //baseDef 
                100,                                                                                                    //baseSpe
                100,                                                                                                    //baseSpA 
                100,                                                                                                    //baseSpD 
                new Type[]{new Psychic(), null},                                                      			//type
                Ability.getRandomAbility(new Ability[]{new MagicBounce()}),                                   	        //ability
                4,                                                                                                      //weight (Kg)
                1,                                                                                                      //miniFrontSizeScale
                Gender.GENDERLESS,                                                                                      //gender  
                new HashSet<Move>(                                                                                      //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Pound(),
                                        new MegaPunch(),
                                        new MegaKick(),
                                        new moves.damagingmove.special.Psychic(),
                                        /*new Barrier(),*/
                                        new AncientPower(),
                                        new Amnesia(),
                                        new NastyPlot(),
                                        new AuraSphere(),
                                        /*new WorkUp(),*/
                                        new DragonClaw(),
                                        /*new PsyShock(),*/
                                        new CalmMind(),
                                        new Roar(),
                                        new Toxic(),
                                        new Hail(),
                                        new BulkUp(),
                                        /*new VenoShock(),*/
                                        new SunnyDay(),
                                        new IceBeam(),
                                        new Blizzard(),
                                        new Protect(),
                                        new RainDance(),
                                        new Thunderbolt(),
                                        new Thunder(),
                                        new Earthquake(),
                                        new LeechLife(),
                                        new SludgeBomb(),
                                        new ShadowBall(),
                                        new BrickBreak(),
                                        new DoubleTeam(),
                                        new Flamethrower(),
                                        new Sandstorm(),
                                        new FireBlast(),
                                        new RockTomb(),
                                        new AerialAce(),
                                        /*new FlameCharge(),*/
                                        new Thief(),
                                        new Overheat(),
                                        new FocusBlast(),
                                        new EnergyBall(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new WillOWisp(),
                                        new Acrobatics(),
                                        new Explosion(),
                                        new ShadowClaw(),
                                        new RockPolish(),
                                        new StoneEdge(),
                                        /*new GyroBall(),*/
                                        new SwordsDance(),
                                        new ThunderWave(),
                                        new PsychUp(),
                                        new Bulldoze(),
                                        new RockSlide(),
                                        new DreamEater(),
                                        /*new GrassKnot(),*/
                                        new Swagger(),
                                        new XScissor(),
                                        new DragonTail(),
                                        new PoisonJab(),
                                        new FlashCannon(),
                                        new WildCharge(),
                                        new Surf(),
                                        /*new Snarl(),*/
                                        new DarkPulse(),
                                        new Waterfall(),
                                       /* new DazzlingGleam(),*/
                                        new Confusion(),
                                }
                                )
                        )
                );
    }

}
