package pokemon;

import java.util.Arrays;
import java.util.HashSet;

import abilities.Ability;
import abilities.firstturn.Intimidate;
import abilities.movecondition.AngerPoint;
import abilities.movecondition.SheerForce;
import moves.Move;
import moves.damagingmove.physical.Bulldoze;
import moves.damagingmove.physical.Earthquake;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.HornAttack;
import moves.damagingmove.physical.Rage;
import moves.damagingmove.physical.RockSlide;
import moves.damagingmove.physical.RockTomb;
import moves.damagingmove.physical.StoneEdge;
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
import moves.status.DoubleTeam;
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

public class Tauros extends Pokemon{

    public Tauros(int level) {
        super(  level,                                                                                          //level
                75,                                                                                             //baseHP 
                100,                                                                                            //baseAtk 
                95,                                                                                             //baseDef 
                110,                                                                                            //baseSpe
                40,                                                                                             //baseSpA 
                70,                                                                                             //baseSpD 
                new Type[]{new Normal(), null},                                                            	//type
                Ability.getRandomAbility(new Ability[]{new Intimidate(), new AngerPoint(), new SheerForce()}),  //ability
                83.2,                                                                                           //weight (Kg)
                0.9,                                                                                            //miniFrontSizeScale
                Gender.MALE,                                                                       		//gender  
                new HashSet<Move>(                                                                              //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Tackle(),
                                        new TailWhip(),
                                        new Rage(),
                                        new HornAttack(),
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
                                        new StoneEdge(),
                                        new RockSlide(),
                                        new Bulldoze(),
                                        new WildCharge(),
                                        new Surf(),
                                        
                                }
                                )
                )
        );
    }

}
