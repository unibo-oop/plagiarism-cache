package pokemon;

import java.util.Arrays;
import java.util.HashSet;

import abilities.Ability;
import abilities.firstturn.Intimidate;
import abilities.movecondition.AngerPoint;
import abilities.movecondition.SapSipper;
import abilities.movecondition.SheerForce;
import abilities.movecondition.WaterAbsorb;
import abilities.otherconditions.Scrappy;
import moves.Move;
import moves.damagingmove.physical.Earthquake;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.HornAttack;
import moves.damagingmove.physical.Rage;
import moves.damagingmove.physical.RockSlide;
import moves.damagingmove.physical.RockTomb;
import moves.damagingmove.physical.Stomp;
import moves.damagingmove.physical.Tackle;
import moves.damagingmove.physical.ZenHeadbutt;
import moves.damagingmove.physical.selfrecoil.TakeDown;
import moves.damagingmove.physical.selfrecoil.WildCharge;
import moves.damagingmove.physical.variablepriority.Pursuit;
import moves.damagingmove.special.Blizzard;
import moves.damagingmove.special.FireBlast;
import moves.damagingmove.special.Flamethrower;
import moves.damagingmove.special.IceBeam;
import moves.damagingmove.special.Surf;
import moves.damagingmove.special.Thunder;
import moves.damagingmove.special.Thunderbolt;
import moves.status.Attract;
import moves.status.Curse;
import moves.status.DefenseCurl;
import moves.status.DoubleTeam;
import moves.status.Growl;
import moves.status.HealBell;
import moves.status.MilkDrink;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.Sandstorm;
import moves.status.ScaryFace;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.TailWhip;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Normal;
import types.Type;

public class Miltank extends Pokemon{

    public Miltank(int level) {
        super(  level,                                                                                          //level
                95,                                                                                             //baseHP 
                80,                                                                                            	//baseAtk 
                105,                                                                                            //baseDef 
                100,                                                                                            //baseSpe
                40,                                                                                             //baseSpA 
                70,                                                                                             //baseSpD 
                new Type[]{new Normal(), null},                                                            	//type
                Ability.getRandomAbility(new Ability[]{new SapSipper(), new Scrappy()}),                        //ability
                75.2,                                                                                           //weight (Kg)
                1,                                                                                              //miniFrontSizeScale
                Gender.FEMALE,                                                                       		//gender  
                new HashSet<Move>(                                                                              //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Tackle(),
                                        new Growl(),
                                        new HealBell(),
                                        /*new GyroBall(),*/
                                        new Stomp(),
                                        new MilkDrink(),
                                        new Curse(new Type[]{new Normal(), null}),
                                        new DefenseCurl(),
                                        new ScaryFace(),
                                        new Pursuit(),
                                        new Rest(),
                                        new ZenHeadbutt(),
                                        new TakeDown(),
                                        new Swagger(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new IceBeam(),
                                        new Blizzard(),
                                        new Protect(),
                                        new RainDance(),
                                        new Thunder(),
                                        new Thunderbolt(),
                                        new Earthquake(),
                                        new DoubleTeam(),
                                        new Flamethrower(),
                                        new Sandstorm(),
                                        new FireBlast(),
                                        new RockTomb(),
                                        new Facade(),
                                        new Attract(),
                                        /*new StoneEdge(),*/
                                        new RockSlide(),
                                        /*new Bulldoze(),*/
                                        new WildCharge(),
                                        new Surf(),

                                }
                                )
                        )
                );
    }

}
