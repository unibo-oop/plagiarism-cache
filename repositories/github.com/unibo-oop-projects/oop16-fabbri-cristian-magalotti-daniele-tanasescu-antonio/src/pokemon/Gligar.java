package pokemon;

import java.util.Arrays;
import java.util.HashSet;

import abilities.Ability;
import abilities.otherconditions.Immunity;
import abilities.statisticsalterationondemand.HyperCutter;
import abilities.weathercondition.SandVeil;
import moves.Move;
import moves.damagingmove.physical.Acrobatics;
import moves.damagingmove.physical.AerialAce;
import moves.damagingmove.physical.BrickBreak;
import moves.damagingmove.physical.Bulldoze;
import moves.damagingmove.physical.Earthquake;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.KnockOff;
import moves.damagingmove.physical.MetalClaw;
import moves.damagingmove.physical.PoisonJab;
import moves.damagingmove.physical.PoisonSting;
import moves.damagingmove.physical.PoisonTail;
import moves.damagingmove.physical.QuickAttack;
import moves.damagingmove.physical.RockSlide;
import moves.damagingmove.physical.RockTomb;
import moves.damagingmove.physical.Slash;
import moves.damagingmove.physical.SteelWing;
import moves.damagingmove.physical.StoneEdge;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.physical.WingAttack;
import moves.damagingmove.physical.XScissor;
import moves.damagingmove.physical.bypassprotect.FeintAttack;
import moves.damagingmove.physical.counterattacking.Counter;
import moves.damagingmove.physical.onehitko.Guillotine;
import moves.damagingmove.physical.selfrecoil.DoubleEdge;
import moves.damagingmove.special.DarkPulse;
import moves.damagingmove.special.EarthPower;
import moves.damagingmove.special.SludgeBomb;
import moves.status.Agility;
import moves.status.DoubleTeam;
import moves.status.Harden;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.RockPolish;
import moves.status.Roost;
import moves.status.SandAttack;
import moves.status.Sandstorm;
import moves.status.SunnyDay;
import moves.status.SwordsDance;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Bug;
import types.Poison;
import types.Type;

public class Gligar extends Pokemon{

    public Gligar(int level) {
        super(  level,                                                                                          //level
                65,                                                                                             //baseHP 
                75,                                                                                             //baseAtk 
                105,                                                                                            //baseDef 
                85,                                                                                             //baseSpe
                35,                                                                                             //baseSpA 
                65,                                                                                             //baseSpD 
                new Type[]{new Bug(), new Poison()},                                                            //type
                Ability.getRandomAbility(new Ability[]{new HyperCutter(), new Immunity(), new SandVeil()}),     //ability
                65,		                                                                                //weight (Kg)
                1,                                                                                              //miniFrontSizeScale
                Gender.getRandomGender(),                                                                       //gender  
                new HashSet<Move>(                                                                              //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new PoisonSting(),
                                        new SandAttack(),
                                        new Harden(),
                                        new QuickAttack(),
                                        new FeintAttack(),
                                        new Acrobatics(),
                                        new KnockOff(),
                                        new Slash(),
                                        new XScissor(),
                                        new SwordsDance(),
                                        new Guillotine(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new RainDance(),
                                        new Protect(),
                                        new Roost(),
                                        new Earthquake(),
                                        new EarthPower(),
                                        new Bulldoze(),
                                        new BrickBreak(),
                                        new DoubleEdge(),
                                        new DoubleTeam(),
                                        new SludgeBomb(),
                                        new Sandstorm(),
                                        new RockTomb(),
                                        new StoneEdge(),
                                        new RockSlide(),
                                        new AerialAce(),
                                        new Facade(),
                                        new Rest(),
                                        new Thief(),
                                        new SteelWing(),
                                        new RockPolish(),
                                        new PoisonJab(),
                                        new DarkPulse(),
                                        new Agility(),
                                        new Counter(),
                                        new MetalClaw(),
                                        /*new NightSlash(),*/
                                        new PoisonTail(),
                                        new WingAttack(),
                                }
                                )
                )
        );
    }

}
