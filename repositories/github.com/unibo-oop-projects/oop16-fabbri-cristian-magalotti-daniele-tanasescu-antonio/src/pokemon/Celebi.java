package pokemon;

import java.util.Arrays;
import java.util.HashSet;

import abilities.Ability;
import abilities.switchcondition.NaturalCure;
import moves.Move;
import moves.damagingmove.physical.AerialAce;
import moves.damagingmove.physical.BrickBreak;
import moves.damagingmove.physical.Bulldoze;
import moves.damagingmove.physical.DragonTail;
import moves.damagingmove.physical.Earthquake;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.LeechLife;
import moves.damagingmove.physical.PoisonJab;
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
import moves.damagingmove.special.Blizzard;
import moves.damagingmove.special.Confusion;
import moves.damagingmove.special.DarkPulse;
import moves.damagingmove.special.DazzlingGleam;
import moves.damagingmove.special.EnergyBall;
import moves.damagingmove.special.FireBlast;
import moves.damagingmove.special.Flamethrower;
import moves.damagingmove.special.FlashCannon;
import moves.damagingmove.special.FocusBlast;
import moves.damagingmove.special.IceBeam;
import moves.damagingmove.special.LeafStorm;
import moves.damagingmove.special.MagicalLeaf;
import moves.damagingmove.special.Overheat;
import moves.damagingmove.special.ShadowBall;
import moves.damagingmove.special.SludgeBomb;
import moves.damagingmove.special.Surf;
import moves.damagingmove.special.Thunder;
import moves.damagingmove.special.Thunderbolt;
import moves.damagingmove.special.sleeprequired.DreamEater;
import moves.status.Attract;
import moves.status.CalmMind;
import moves.status.DoubleTeam;
import moves.status.Hail;
import moves.status.HealBell;
import moves.status.PsychUp;
import moves.status.RainDance;
import moves.status.Recover;
import moves.status.Rest;
import moves.status.RockPolish;
import moves.status.Sandstorm;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.SwordsDance;
import moves.status.ThunderWave;
import moves.status.Toxic;
import moves.status.WillOWisp;
import moves.status.protecting.Protect;
import types.Grass;
import types.Psychic;
import types.Type;

public class Celebi extends Pokemon{

    public Celebi(int level) {
        super(  level,                                                                                          //level
                100,                                                                                            //baseHP 
                100,                                                                                            //baseAtk 
                100,                                                                                            //baseDef 
                100,                                                                                            //baseSpe
                100,                                                                                            //baseSpA 
                100,                                                                                            //baseSpD 
                new Type[]{new Psychic(), new Grass()},                                                         //type
                Ability.getRandomAbility(new Ability[]{new NaturalCure()}),                                     //ability                                        
                5,               	                                                                        //weight (Kg) 
                1.1,                                                                                            //miniFrontSizeScale
                Gender.GENDERLESS,                                                                              //gender  
                new HashSet<Move>(                                                                              //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Confusion(),
                                        new Recover(),
                                        new HealBell(),
                                        new MagicalLeaf(),
                                        new AncientPower(),
                                        new LeafStorm(),
                                        new CalmMind(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new Protect(),
                                        new RainDance(),
                                        new ShadowBall(),
                                        new DoubleTeam(),
                                        new Sandstorm(),
                                        new AerialAce(),
                                        new Facade(),
                                        new Rest(),
                                        new SwordsDance(),
                                        new ThunderWave(),
                                        new PsychUp(),
                                        new Bulldoze(),
                                        new RockSlide(),
                                        new DreamEater(),
                                        /*new GrassKnot(),*/
                                        new Swagger(),
                                        new EnergyBall(),
                                        new DazzlingGleam()

                                }

                                )
                        )
                );
    }

}
