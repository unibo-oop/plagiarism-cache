package managers;

import abilities.endofturnconditionability.Moody;
import abilities.endofturnconditionability.SpeedBoost;
import abilities.firstturn.Download;
import abilities.firstturn.Drizzle;
import abilities.firstturn.Drought;
import abilities.firstturn.ElectricSurge;
import abilities.firstturn.GrassySurge;
import abilities.firstturn.Intimidate;
import abilities.firstturn.LightMetal;
import abilities.firstturn.MistySurge;
import abilities.firstturn.Pressure;
import abilities.firstturn.PsychicSurge;
import abilities.firstturn.SandStream;
import abilities.firstturn.alwaysactive.AirLock;
import abilities.hpcondition.Blaze;
import abilities.hpcondition.Multiscale;
import abilities.hpcondition.Overgrow;
import abilities.hpcondition.Torrent;
import abilities.movecondition.Levitate;
import abilities.movecondition.LightningRod;
import abilities.movecondition.SheerForce;
import abilities.movecondition.Static;
import abilities.movecondition.WaterAbsorb;
import abilities.otherconditions.MagicBounce;
import abilities.otherconditions.MagicGuard;
import abilities.otherconditions.RockHead;
import abilities.statisticsalterationondemand.ClearBody;
import abilities.statisticsalterationondemand.Contrary;
import abilities.statisticsalterationondemand.KeenEye;
import abilities.statusalterationcondition.EarlyBird;
import abilities.statusalterationcondition.Guts;
import abilities.switchcondition.NaturalCure;
import abilities.switchcondition.Regenerator;
import abilities.weathercondition.Chlorophyll;
import abilities.weathercondition.RainDish;
import main.MainApp;
import moves.damagingmove.physical.Acrobatics;
import moves.damagingmove.physical.AerialAce;
import moves.damagingmove.physical.BugBite;
import moves.damagingmove.physical.BulletPunch;
import moves.damagingmove.physical.CrossChop;
import moves.damagingmove.physical.Crunch;
import moves.damagingmove.physical.DragonClaw;
import moves.damagingmove.physical.DragonTail;
import moves.damagingmove.physical.Earthquake;
import moves.damagingmove.physical.ExtremeSpeed;
import moves.damagingmove.physical.FirePunch;
import moves.damagingmove.physical.IcePunch;
import moves.damagingmove.physical.IronHead;
import moves.damagingmove.physical.IronTail;
import moves.damagingmove.physical.KnockOff;
import moves.damagingmove.physical.LeechLife;
import moves.damagingmove.physical.SacredFire;
import moves.damagingmove.physical.StoneEdge;
import moves.damagingmove.physical.Superpower;
import moves.damagingmove.physical.ThunderPunch;
import moves.damagingmove.physical.Waterfall;
import moves.damagingmove.physical.XScissor;
import moves.damagingmove.physical.onlyfirstturn.FakeOut;
import moves.damagingmove.physical.selfko.Explosion;
import moves.damagingmove.physical.selfrecoil.BraveBird;
import moves.damagingmove.physical.selfrecoil.DoubleEdge;
import moves.damagingmove.physical.selfrecoil.FlareBlitz;
import moves.damagingmove.physical.selfrecoil.WildCharge;
import moves.damagingmove.physical.weightdependent.LowKick;
import moves.damagingmove.special.AuraSphere;
import moves.damagingmove.special.DarkPulse;
import moves.damagingmove.special.DazzlingGleam;
import moves.damagingmove.special.DracoMeteor;
import moves.damagingmove.special.EarthPower;
import moves.damagingmove.special.FireBlast;
import moves.damagingmove.special.FlashCannon;
import moves.damagingmove.special.FocusBlast;
import moves.damagingmove.special.GigaDrain;
import moves.damagingmove.special.HeatWave;
import moves.damagingmove.special.HiddenPower;
import moves.damagingmove.special.Hurricane;
import moves.damagingmove.special.HyperVoice;
import moves.damagingmove.special.IceBeam;
import moves.damagingmove.special.IcyWind;
import moves.damagingmove.special.LeafStorm;
import moves.damagingmove.special.Moonblast;
import moves.damagingmove.special.MuddyWater;
import moves.damagingmove.special.Overheat;
import moves.damagingmove.special.Psychic;
import moves.damagingmove.special.PsychoBoost;
import moves.damagingmove.special.Scald;
import moves.damagingmove.special.ShadowBall;
import moves.damagingmove.special.SludgeBomb;
import moves.damagingmove.special.Surf;
import moves.damagingmove.special.Thunder;
import moves.damagingmove.special.Thunderbolt;
import moves.damagingmove.special.TriAttack;
import moves.damagingmove.special.WaterPulse;
import moves.damagingmove.special.hpdependent.Eruption;
import moves.damagingmove.special.hpdependent.WaterSprout;
import moves.status.Agility;
import moves.status.Aromatherapy;
import moves.status.CalmMind;
import moves.status.Curse;
import moves.status.DragonDance;
import moves.status.MorningSun;
import moves.status.NastyPlot;
import moves.status.NaturePower;
import moves.status.PainSplit;
import moves.status.Recover;
import moves.status.Rest;
import moves.status.Roar;
import moves.status.RockPolish;
import moves.status.Roost;
import moves.status.SleepPowder;
import moves.status.Spore;
import moves.status.SwordsDance;
import moves.status.Synthesis;
import moves.status.ThunderWave;
import moves.status.Toxic;
import moves.status.Whirlwind;
import moves.status.WillOWisp;
import moves.status.protecting.Protect;
import natures.Adamant;
import natures.Bold;
import natures.Calm;
import natures.Careful;
import natures.Impish;
import natures.Jolly;
import natures.Mild;
import natures.Modest;
import natures.Naive;
import natures.Quiet;
import natures.Rash;
import natures.Relaxed;
import natures.Timid;
import pokemon.*;
import types.Fight;
import types.Fire;
import types.Grass;
import types.Ground;
import types.Ice;
import types.Rock;
import types.Steel;
import types.Type;

public class PokemonListManager {

            public static final Pokemon[] LEVEL1LIST = new Pokemon[]{new Snorlax(50),new Snubbull(50), new Oddish(50), new Gloom(50),
                                                                     new Vileplume(50), new Sunflora(50), new Remoraid(50), new Zubat(50),
                                                                     new Crobat(50), new Pidgey(50), new Lickitung(50), new Feebas(50),
                                                                     new Magikarp(50)};  

            public static final Pokemon[] LEVEL2LIST = new Pokemon[]{new Meowth(50), new Persian(50), new Growlithe(50), new Arcanine(50),
                                                                     new Jirachi(50), new Pidgeot(50), new Pidgeotto(50), new Taillow(50),
                                                                     new Swellow(50), new Ponyta(50), new Rapidash(50), new Zangoose(50)};  
            
            public static final Pokemon[] LEVEL3LIST = new Pokemon[]{getPerfectAlakazam(), new Slowking(50),new Hypno(50), new Misdreavus(50), 
                                                                     getPerfectLele()};  

            public static final Pokemon[] LEVEL4LIST = new Pokemon[]{new Cleffa(50), new Clefairy(50),new Clefable(50), new Magby(50), 
                                                                     new Elekid(50), new Pichu(50), new Pikachu(50), new Granbull(50),
                                                                     new Tyrogue(50), new Bulbasaur(50), new Charmander(50), new Squirtle(50), 
                                                                     new Chikorita(50), new Cyndaquil(50), new Totodile(50), new Treecko(50),
                                                                     new Torchic(50), new Mudkip(50), new Slakoth(50), new Chimecho(50),
                                                                     new Vulpix(50), new Ninetales(50), new Jigglypuff(50), new Igglybuff(50),
                                                                     new Wigglytuff(50), new Chansey(50), new Blissey(50), new Togepi(50),
                                                                     new Togetic(50), new Wynaut(50), new Marill(50), new Azumarill(50), 
                                                                     new Azurill(50), new Smoochum(50), new Skitty(50), new Delcatty(50)};  
            
            public static final Pokemon[] LEVEL5LIST = new Pokemon[]{new Hitmonchan(50), new Hitmonlee(50), new Hitmontop(50), new Rhydon(50), 
                                                                     new Rhyhorn(50), new Skarmory(50), new Voltorb(50), new Electrode(50),
                                                                     new Lapras(50), new Aipom(50), new Ledyba(50), new Ledian(50),
                                                                     new Magmar(50), new Croconaw(50), new Bayleef(50), new Sandshrew(50),
                                                                     new Sandslash(50), new Ludicolo(50), new Wingull(50), new Pelipper(50),
                                                                     new Wailmer(50), new Wailord(50), new Milotic(50)};  
            
            public static final Pokemon[] LEVEL6LIST = new Pokemon[]{new Crobat(50), new Muk(50), getPerfectBulu(), new Umbreon(50),
                                                                     new Ariados(50), new Kabutops(50)};  
            
            public static final Pokemon[] LEVEL7LIST = new Pokemon[]{new Blastoise(50), new Feraligatr(50), new Swampert(50), new Goldeen(50), 
                                                                     new Seaking(50), new Psyduck(50), new Golduck(50), new Slowbro(50),
                                                                     new Seel(50), new Dewgong(50), new Wooper(50), new Quagsire(50),
                                                                     new Surskit(50), new Kyogre(50), new Carvanha(50), new Sharpedo(50),
                                                                     new Krabby(50), new Kingler(50), new Mantine(50), new Corphish(50),
                                                                     new Crawdaunt(50), new Horsea(50), new Seadra(50), new Kingdra(50),
                                                                     new Staryu(50), new Starmie(50), new Lanturn(50), new Politoed(50),
                                                                     new Walrein(50), new Huntail(50), new Gorebyss(50)};  
            
            public static final Pokemon[] LEVEL8LIST = new Pokemon[]{new Geodude(50), new Graveler(50), new Golem(50), new Nidoking(50), 
                                                                     new Groudon(50), new Regirock(50), new Kabuto(50), new Omanyte(50),
                                                                     new Omastar(50), new Aerodactyl(50), new Lileep(50), new Armaldo(50),
                                                                     new Anorith(50), new Cradily(50), new Plusle(50), new Minun(50),
                                                                     new Altaria(50), new Seviper(50), new Lunatone(50), new Solrock(50),
                                                                     new Kecleon(50)};  
            
            public static final Pokemon[] LEVEL9LIST = new Pokemon[]{getPerfectDragonite(), getPerfectRayquaza(), getPerfectLatias(), 
                                                                     getPerfectLatios(), getPerfectSalamence(), getPerfectKoko()};  
            
            public static final Pokemon[] LEVEL10LIST = new Pokemon[]{new Electabuzz(50), new Raichu(50), new Zapdos(50),new Diglett(50),
                                                                     new Dugtrio(50), new Aron(50), new Lairon(50), new Aggron(50),
                                                                     new Larvitar(50), new Pupitar(50), new Shelgon(50), new Tyrannitar(50),
                                                                     new Registeel(50), new Porygon(50), new Porygon2(50), new Metang(50)};  
            
            public static final Pokemon[] LEVEL11LIST = new Pokemon[]{new Tangela(50), new Machamp(50), new Arbok(50),new Tauros(50), 
                                                                      new Scyther(50), new Scizor(50), new Mewtwo(50), new Mew(50),
                                                                      new Poliwrath(50), new Steelix(50), new Sudowoodo(50), new Glalie(50),
                                                                      new Cacturne(50), new Banette(50), new Dusclops(50), new Absol(50)};  
            
            public static final Pokemon[] LEVEL12LIST = new Pokemon[]{getPerfectRegice(), getPerfectFini(), getPerfectArticuno(),
                                                                      getPerfectLugia(), getPerfectHoOh(), getPerfectDusclops()};  
            
            public static final Pokemon[] LEVEL13LIST = new Pokemon[]{new Ariados(50), new Venonat(50), new Venomoth(50), new Muk(50), 
                                                                      new Crobat(50), new Umbreon(50), new Espeon(50), new Hypno(50),
                                                                      new Gastly(50), new Haunter(50), new Gastly(50), new Sableye(50),
                                                                      new Slaking(50), new Shedinja(50), new Ninjask(50), new Beedrill(50),
                                                                      new Butterfree(50), new Beautifly(50), new Dustox(50), new Unown(50),
                                                                      new Wobbuffet(50), new Murkrow(50), new Shiftry(50), new Swalot(50),
                                                                      new Grumpig(50)};  
            
            public static final Pokemon[] LEVEL14LIST = new Pokemon[]{new Growlithe(50), new Arcanine(50),new Aipom(50), new Houndour(50), 
                                                                      new Houndoom(50), new Ponyta(50), new Rapidash(50), new Bulbasaur(50),
                                                                      new Ivysaur(50), new Celebi(50), new Entei(50), new Suicune(50), 
                                                                      new Raikou(50), new Deoxys(50), new Raichu(50), new Ampharos(50),
                                                                      new Mightyena(50)};  
            
            public static final Pokemon[] LEVEL15LIST = new Pokemon[]{getPerfectCharizard(), getPerfectVenusaur(), getPerfectBlastoise(),
                                                                      getPerfectMewtwo(), getPerfectDragonite(), getPerfectSmeargle()};  
            
            public static final Pokemon[] PLAYERSTARTINGLIST = PlayerPokemonAviliable.PLAYERAVAILABLE;

            public static final Pokemon[] POKEMONSHOPLIST1000 = getPokemonShopList1000();

            public static final Pokemon[] POKEMONSHOPLIST3000 = getPokemonShopList3000();
            
            public static final Pokemon[] POKEMONSHOPLIST5000 = getPokemonShopList5000();


            //don't need an instantiation! all here is static
            private PokemonListManager() {
                // TODO Auto-generated constructor stub
            }

            private static Pokemon[] getPokemonShopList1000(){
                Venusaur venusaur = getPerfectVenusaur();

                Charizard charizard = getPerfectCharizard();

                Blastoise blastoise = getPerfectBlastoise();

                Raichu raichu = new Raichu(50);
                raichu.setEV(0, 0, 0, 252, 252, 4);
                raichu.setIV(31, 0, 31, 31, 31, 31);
                raichu.setNature(new Timid());
                raichu.setAbility(new LightningRod());
                raichu.setMove(0, new NastyPlot());
                raichu.setMove(1, new Thunderbolt());
                raichu.setMove(2, new FocusBlast());
                raichu.setMove(3, new HiddenPower(new Ice()));

                Alakazam alakazam = getPerfectAlakazam();

                Arcanine arcanine = new Arcanine(50);
                arcanine.setEV(252, 252, 0, 4, 0, 0);
                arcanine.setIV(31, 31, 31, 31, 0, 31);
                arcanine.setNature(new Adamant());
                arcanine.setAbility(new Intimidate());
                arcanine.setMove(0, new ExtremeSpeed());
                arcanine.setMove(1, new FlareBlitz());
                arcanine.setMove(2, new MorningSun());
                arcanine.setMove(3, new WildCharge());

                Machamp machamp = new Machamp(50);
                machamp.setEV(172, 252, 0, 84, 0, 0);
                machamp.setIV(31, 31, 31, 31, 0, 31);
                machamp.setNature(new Adamant());
                machamp.setAbility(new Guts());
                machamp.setMove(0, new CrossChop());
                machamp.setMove(1, new KnockOff());
                machamp.setMove(2, new StoneEdge());
                machamp.setMove(3, new BulletPunch());

                Gengar gengar = new Gengar(50);
                gengar.setEV(248, 0, 0, 252, 8, 0);
                gengar.setIV(31, 0, 31, 31, 31, 31);
                gengar.setNature(new Timid());
                gengar.setAbility(new Levitate());
                gengar.setMove(0, new WillOWisp());
                gengar.setMove(1, new ShadowBall());
                gengar.setMove(2, new FocusBlast());
                gengar.setMove(3, new PainSplit());

                Dragonite dragonite = getPerfectDragonite();

                Meganium meganium = new Meganium(50);
                meganium.setEV(252, 0, 4, 0, 0, 252);
                meganium.setIV(31, 0, 31, 31, 31, 31);
                meganium.setNature(new Calm());
                meganium.setAbility(new Overgrow());
                meganium.setMove(0, new Aromatherapy());
                meganium.setMove(1, new Synthesis());
                meganium.setMove(2, new GigaDrain());
                meganium.setMove(3, new Toxic());

                Typhlosion typhlosion = new Typhlosion(50);
                typhlosion.setEV(0, 0, 4, 252, 252, 0);
                typhlosion.setIV(31, 0, 31, 31, 31, 31);
                typhlosion.setAbility(new Blaze());
                typhlosion.setNature(new Timid());
                typhlosion.setAbility(new Blaze());
                typhlosion.setMove(0, new Eruption());
                typhlosion.setMove(1, new FireBlast());
                typhlosion.setMove(2, new HiddenPower(new Grass()));
                typhlosion.setMove(3, new FocusBlast());

                Feraligatr feraligatr = new Feraligatr(50);
                feraligatr.setEV(0, 252, 0, 252, 0, 0);
                feraligatr.setIV(31, 31, 31, 31, 0, 31);
                feraligatr.setNature(new Adamant());
                feraligatr.setAbility(new SheerForce());
                feraligatr.setMove(0, new DragonDance());
                feraligatr.setMove(1, new Waterfall());
                feraligatr.setMove(2, new Crunch());
                feraligatr.setMove(3, new IcePunch());

                Quagsire quagsire = new Quagsire(50);
                quagsire.setEV(252, 0, 252, 0, 0, 4);
                quagsire.setIV(31, 0, 31, 31, 31, 31);
                quagsire.setNature(new Relaxed());
                quagsire.setAbility(new WaterAbsorb());
                quagsire.setMove(0, new Scald());
                quagsire.setMove(1, new Recover());
                quagsire.setMove(2, new Earthquake());
                quagsire.setMove(3, new Toxic());

                Porygon2 porygon2 = new Porygon2(50);
                porygon2.setEV(252, 0, 4, 0, 252, 0);
                porygon2.setIV(31, 0, 31, 31, 31, 31);
                porygon2.setNature(new Calm());
                porygon2.setAbility(new Download());
                porygon2.setMove(0, new TriAttack());
                porygon2.setMove(1, new IceBeam());
                porygon2.setMove(2, new Thunderbolt());
                porygon2.setMove(3, new Recover());

                Smeargle smeargle = getPerfectSmeargle();

                Scizor scizor = new Scizor(50);
                scizor.setEV(248, 16, 60, 60, 0, 124);
                scizor.setIV(31, 31, 31, 31, 0, 31);
                scizor.setNature(new Adamant());
                scizor.setAbility(new LightMetal());
                scizor.setMove(0, new SwordsDance());
                scizor.setMove(1, new Roost());
                scizor.setMove(2, new BulletPunch());
                scizor.setMove(3, new XScissor());

                Skarmory skarmory = new Skarmory(50);
                skarmory.setEV(252, 0, 4, 0, 0, 252);
                skarmory.setIV(31, 31, 31, 31, 0, 31);
                skarmory.setNature(new Careful());
                skarmory.setAbility(new KeenEye());
                skarmory.setMove(0, new BraveBird());
                skarmory.setMove(1, new Roost());
                skarmory.setMove(2, new IronHead());
                skarmory.setMove(3, new Whirlwind());


                Tyrannitar tyrannitar = new Tyrannitar(50);
                tyrannitar.setEV(180, 252, 0, 76, 0, 0);
                tyrannitar.setIV(31, 31, 31, 31, 0, 31);
                tyrannitar.setNature(new Adamant());
                tyrannitar.setAbility(new SandStream());
                tyrannitar.setMove(0, new StoneEdge());
                tyrannitar.setMove(1, new Crunch());
                tyrannitar.setMove(2, new LowKick());
                tyrannitar.setMove(3, new Superpower());

                Sceptile sceptile = new Sceptile(50);
                sceptile.setEV(4, 0, 0, 252, 252, 0);
                sceptile.setIV(31, 0, 31, 31, 31, 31);
                sceptile.setNature(new Timid());
                sceptile.setAbility(new Contrary());
                sceptile.setMove(0, new LeafStorm());
                sceptile.setMove(1, new FocusBlast());
                sceptile.setMove(2, new HiddenPower(new Rock()));
                sceptile.setMove(3, new GigaDrain());

                Blaziken blaziken = new Blaziken(50);
                blaziken.setEV(4, 252, 0, 252, 0, 0);
                blaziken.setIV(31, 31, 31, 31, 0, 31);
                blaziken.setNature(new Adamant());
                blaziken.setAbility(new SpeedBoost());
                blaziken.setMove(0, new SwordsDance());
                blaziken.setMove(1, new LowKick());
                blaziken.setMove(2, new Acrobatics());
                blaziken.setMove(3, new FlareBlitz());

                Swampert swampert = new Swampert(50);
                swampert.setEV(240, 252, 16, 0, 0, 0);
                swampert.setIV(31, 31, 31, 31, 0, 31);
                swampert.setNature(new Adamant());
                swampert.setAbility(new Torrent());
                swampert.setMove(0, new Earthquake());
                swampert.setMove(1, new Waterfall());
                swampert.setMove(2, new IcePunch());
                swampert.setMove(3, new Toxic());

                Ninjask ninjask = new Ninjask(50);
                ninjask.setEV(0, 252, 4, 252, 0, 0);
                ninjask.setIV(31, 31, 31, 31, 0, 31);
                ninjask.setNature(new Jolly());
                ninjask.setAbility(new SpeedBoost());
                ninjask.setMove(0, new SwordsDance());
                ninjask.setMove(1, new AerialAce());
                ninjask.setMove(2, new LeechLife());
                ninjask.setMove(3, new Protect());

                Shiftry shiftry = new Shiftry(50);
                shiftry.setEV(0, 88, 0, 176, 244, 0);
                shiftry.setIV(31, 31, 31, 31, 31, 31);
                shiftry.setNature(new Rash());
                shiftry.setAbility(new EarlyBird());
                shiftry.setMove(0, new LeafStorm());
                shiftry.setMove(1, new DarkPulse());
                shiftry.setMove(2, new NaturePower());
                shiftry.setMove(3, new FakeOut());

                Gardevoir gardevoir = new Gardevoir(50);
                gardevoir.setEV(16, 0, 8, 252, 232, 0);
                gardevoir.setIV(31, 0, 31, 31, 31, 31);
                gardevoir.setNature(new Timid());
                gardevoir.setAbility(new MagicBounce());
                gardevoir.setMove(0, new HyperVoice());
                gardevoir.setMove(1, new Psychic());
                gardevoir.setMove(2, new Moonblast());
                gardevoir.setMove(3, new CalmMind());

                Torkoal torkoal = new Torkoal(50);
                torkoal.setEV(252, 0, 4, 0, 252, 0);
                torkoal.setIV(31, 0, 31, 31, 31, 31);
                torkoal.setNature(new Quiet());
                torkoal.setAbility(new Drought());
                torkoal.setMove(0, new Eruption());
                torkoal.setMove(1, new HeatWave());
                torkoal.setMove(2, new HiddenPower(new Ice()));
                torkoal.setMove(3, new EarthPower());

                Aggron aggron = new Aggron(50);
                aggron.setEV(0, 252, 4, 252, 0, 0);
                aggron.setIV(31, 31, 31, 31, 0, 31);
                aggron.setNature(new Adamant());
                aggron.setAbility(new RockHead());
                aggron.setMove(0, new RockPolish());
                aggron.setMove(1, new IronTail());
                aggron.setMove(2, new Earthquake());
                aggron.setMove(3, new DoubleEdge());

                Dusclops dusclops = getPerfectDusclops();

                Salamence salamence = getPerfectSalamence();

                Pokemon[] list = new Pokemon[]{venusaur, charizard, blastoise, raichu, arcanine, machamp, gengar, dragonite, alakazam,
                                               meganium, typhlosion, feraligatr, quagsire, porygon2, smeargle, scizor, skarmory, tyrannitar,
                                               sceptile, blaziken, swampert, ninjask, shiftry, gardevoir, torkoal, aggron, dusclops, salamence};
                return list;
            }

            private static Pokemon[] getPokemonShopList3000(){
                Articuno articuno = getPerfectArticuno();

                Zapdos zapdos = new Zapdos(50);
                zapdos.setEV(252, 0, 60, 16, 0, 180);
                zapdos.setIV(31, 0, 31, 31, 31, 31);
                zapdos.setNature(new Calm());
                zapdos.setAbility(new Static());
                zapdos.setMove(0, new Thunderbolt());
                zapdos.setMove(1, new Roost());
                zapdos.setMove(2, new HiddenPower(new Ice()));
                zapdos.setMove(3, new HeatWave());

                Moltres moltres = new Moltres(50);
                moltres.setEV(0, 0, 4, 252, 252, 0);
                moltres.setIV(31, 0, 31, 31, 31, 31);
                moltres.setNature(new Timid());
                moltres.setAbility(new Pressure());
                moltres.setMove(0, new FireBlast());
                moltres.setMove(1, new Hurricane());
                moltres.setMove(2, new HiddenPower(new Grass()));
                moltres.setMove(3, new Roost());

                Suicune suicune= new Suicune(50);
                suicune.setEV(252, 0, 252, 0, 0, 4);
                suicune.setIV(31, 0, 31, 31, 31, 31);
                suicune.setNature(new Bold());
                suicune.setAbility(new Pressure());
                suicune.setMove(0, new Roar());
                suicune.setMove(1, new Scald());
                suicune.setMove(2, new Rest());
                suicune.setMove(3, new CalmMind());

                Raikou raikou = new Raikou(50);
                raikou.setEV(0, 0, 4, 252, 252, 0);
                raikou.setIV(31, 0, 31, 31, 31, 31);
                raikou.setNature(new Timid());
                raikou.setAbility(new Pressure());
                raikou.setMove(0, new CalmMind());
                raikou.setMove(1, new Thunderbolt());
                raikou.setMove(2, new HiddenPower(new Ice()));
                raikou.setMove(3, new ShadowBall());

                Entei entei = new Entei(50);
                entei.setEV(0, 252, 4, 252, 0, 0);
                entei.setIV(31, 31, 31, 31, 0, 31);
                entei.setNature(new Adamant());
                entei.setAbility(new Pressure());
                entei.setMove(0, new SwordsDance());
                entei.setMove(1, new SacredFire());
                entei.setMove(2, new MorningSun());
                entei.setMove(3, new ExtremeSpeed());

                Regice regice = getPerfectRegice();

                Regirock regirock = new Regirock(50);
                regirock.setEV(252, 184, 16, 0, 0, 56);
                regirock.setIV(31, 31, 31, 31, 0, 31);
                regirock.setNature(new Impish());
                regirock.setAbility(new ClearBody());
                regirock.setMove(0, new Curse(new Type[]{new Rock(), null}));
                regirock.setMove(1, new StoneEdge());
                regirock.setMove(2, new ThunderWave());
                regirock.setMove(3, new Earthquake());

                Registeel registeel = new Registeel(50);
                registeel.setEV(252, 0, 4, 0, 0, 252);
                registeel.setIV(31, 31, 31, 31, 0, 31);
                registeel.setNature(new Careful());
                registeel.setAbility(new ClearBody());
                registeel.setMove(0, new Curse(new Type[]{new Steel(), null}));
                registeel.setMove(1, new IronHead());
                registeel.setMove(2, new Rest());
                registeel.setMove(3, new Toxic());

                Latias latias = getPerfectLatias();

                Latios latios = getPerfectLatios();

                Pokemon[] list = new Pokemon[]{articuno, zapdos, moltres, 
                                               suicune, raikou, entei,
                                               regice, regirock, registeel, latias, latios};
                return list;

            }

            private static Pokemon[] getPokemonShopList5000(){                
                Mewtwo mewtwo = getPerfectMewtwo();
                
                Mew mew = new Mew(50);
                mew.setEV(0, 0, 0, 252, 252, 4);
                mew.setIV(31, 0, 31, 31, 31, 31);
                mew.setNature(new Timid());
                mew.setAbility(new MagicBounce());
                mew.setMove(0, new AuraSphere());
                mew.setMove(1, new Psychic());
                mew.setMove(2, new NastyPlot());
                mew.setMove(3, new GigaDrain());
                
                Lugia lugia = getPerfectLugia();
                
                HoOh hoOh = getPerfectHoOh();
                
                Celebi celebi = new Celebi(50);
                celebi.setEV(0, 0, 0, 252, 252, 4);
                celebi.setIV(31, 0, 31, 31, 31, 31);
                celebi.setNature(new Timid());
                celebi.setAbility(new NaturalCure());
                celebi.setMove(0, new NastyPlot());
                celebi.setMove(1, new GigaDrain());
                celebi.setMove(2, new Psychic());
                celebi.setMove(3, new DazzlingGleam());
                
                Kyogre kyogre = new Kyogre(50);
                kyogre.setEV(4, 0, 0, 252, 252, 0);
                kyogre.setIV(31, 0, 31, 31, 31, 31);
                kyogre.setNature(new Modest());
                kyogre.setAbility(new Drizzle());
                kyogre.setMove(0, new WaterSprout());
                kyogre.setMove(1, new Surf());
                kyogre.setMove(2, new Thunder());
                kyogre.setMove(3, new IceBeam());
                
                Groudon groudon = new Groudon(50);
                groudon.setEV(200, 200, 108, 0, 0, 0);
                groudon.setIV(31, 31, 31, 31, 0, 31);
                groudon.setNature(new Adamant());
                groudon.setAbility(new Drought());
                groudon.setMove(0, new SwordsDance());
                groudon.setMove(1, new ThunderWave());
                groudon.setMove(2, new Earthquake());
                groudon.setMove(3, new StoneEdge());
                
                Rayquaza rayquaza = getPerfectRayquaza();
                Deoxys deoxys = new Deoxys(50);
                deoxys.setEV(0, 4, 0, 252, 252, 0);
                deoxys.setIV(31, 31, 31, 31, 31, 31);
                deoxys.setNature(new Naive());
                deoxys.setAbility(new Pressure());
                deoxys.setMove(0, new DarkPulse());
                deoxys.setMove(1, new DragonClaw());
                deoxys.setMove(2, new HiddenPower(new Fire()));
                deoxys.setMove(3, new ExtremeSpeed());
                
                Jirachi jirachi = new Jirachi(50);
                jirachi.setEV(0, 132, 0, 216, 160, 0);
                jirachi.setIV(31, 31, 31, 31, 31, 31);
                jirachi.setNature(new Mild());
                jirachi.setAbility(new MagicGuard());
                jirachi.setMove(0, new IronHead());
                jirachi.setMove(1, new IcyWind());
                jirachi.setMove(2, new HiddenPower(new Ground()));
                jirachi.setMove(3, new Thunderbolt());
                
                TapuKoko koko = getPerfectKoko();
                
                TapuBulu bulu = getPerfectBulu();
                
                TapuFini fini = getPerfectFini();
                
                TapuLele lele = getPerfectLele();
                
                Pokemon[] list = new Pokemon[]{mewtwo, mew,
                                               lugia, hoOh, celebi,
                                               kyogre, groudon, rayquaza, deoxys, jirachi,
                                               koko, bulu, fini, lele};
                return list;
            }
            
            private static Mewtwo getPerfectMewtwo() {
                Mewtwo mewtwo = new Mewtwo(50);
                mewtwo.setEV(0, 0, 4, 252, 252, 0);
                mewtwo.setIV(31, 0, 31, 31, 31, 31);
                mewtwo.setNature(new Timid());
                mewtwo.setAbility(new Pressure());
                mewtwo.setMove(0, new Psychic());
                mewtwo.setMove(1, new IceBeam());
                mewtwo.setMove(2, new AuraSphere());
                mewtwo.setMove(3, new CalmMind());
                return mewtwo;
            }

            private static HoOh getPerfectHoOh() {
                HoOh hoOh = new HoOh(50);
                hoOh.setEV(248, 176, 0, 0, 0, 84);
                hoOh.setIV(31, 31, 31, 31, 0, 31);
                hoOh.setNature(new Adamant());
                hoOh.setAbility(new Regenerator());
                hoOh.setMove(0, new SacredFire());
                hoOh.setMove(1, new BraveBird());
                hoOh.setMove(2, new Earthquake());
                hoOh.setMove(3, new Roost());
                return hoOh;
            }

            private static Lugia getPerfectLugia() {
                Lugia lugia = new Lugia(50);
                lugia.setEV(252, 0, 176, 0, 0, 80);
                lugia.setIV(31, 0, 31, 31, 31, 31);
                lugia.setNature(new Bold());
                lugia.setAbility(new Pressure());
                lugia.setMove(0, new Roost());
                lugia.setMove(1, new Whirlwind());
                lugia.setMove(2, new IceBeam());
                lugia.setMove(3, new Toxic());
                return lugia;
            }

            private static TapuFini getPerfectFini() {
                TapuFini fini = new TapuFini(50);
                fini.setEV(252, 0, 0, 232, 24, 0);
                fini.setIV(31, 0, 31, 31, 31, 31);
                fini.setNature(new Timid());
                fini.setAbility(new MistySurge());
                fini.setMove(0, new CalmMind());
                fini.setMove(1, new Scald());
                fini.setMove(2, new Moonblast());
                fini.setMove(3, new MuddyWater());
                return fini;
            }

            private static Rayquaza getPerfectRayquaza() {
                Rayquaza rayquaza = new Rayquaza(50);
                rayquaza.setEV(0, 252, 0, 252, 4, 0);
                rayquaza.setIV(31, 31, 31, 31, 31, 31);
                rayquaza.setNature(new Jolly());
                rayquaza.setAbility(new AirLock());
                rayquaza.setMove(0, new DragonDance());
                rayquaza.setMove(1, new DragonClaw());
                rayquaza.setMove(2, new Earthquake());
                rayquaza.setMove(3, new Overheat());
                return rayquaza;
            }

            private static TapuKoko getPerfectKoko() {
                TapuKoko koko = new TapuKoko(50);
                koko.setEV(4, 0, 0, 252, 252, 0);
                koko.setIV(31, 0, 31, 31, 31, 31);
                koko.setNature(new Timid());
                koko.setAbility(new ElectricSurge());
                koko.setMove(0, new CalmMind());
                koko.setMove(1, new Thunderbolt());
                koko.setMove(2, new DazzlingGleam());
                koko.setMove(3, new Roost());               
                return koko;
            }

            private static TapuBulu getPerfectBulu() {
                TapuBulu bulu = new TapuBulu(50);
                bulu.setEV(0, 252, 4, 252, 0, 0);
                bulu.setIV(31, 31, 31, 31, 0, 31);
                bulu.setNature(new Adamant());
                bulu.setAbility(new GrassySurge());
                bulu.setMove(0, new CalmMind());
                bulu.setMove(1, new Thunderbolt());
                bulu.setMove(2, new DazzlingGleam());
                bulu.setMove(3, new Roost());
                return bulu;
            }

            private static TapuLele getPerfectLele() {
                TapuLele lele = new TapuLele(50);
                lele.setEV(0, 0, 0, 252, 252, 4);
                lele.setIV(31, 0, 31, 31, 31, 31);
                lele.setNature(new Modest());
                lele.setAbility(new PsychicSurge());
                lele.setMove(0, new CalmMind());
                lele.setMove(1, new Psychic());
                lele.setMove(2, new Moonblast());
                lele.setMove(3, new HiddenPower(new Fire()));
                return lele;
            }

            private static Alakazam getPerfectAlakazam() {
                Alakazam alakazam = new Alakazam(50);
                alakazam.setEV(0, 0, 0, 252, 252, 4);
                alakazam.setIV(31, 0, 31, 31, 31, 31);
                alakazam.setNature(new Timid());
                alakazam.setAbility(new MagicGuard());
                alakazam.setMove(0, new Psychic());
                alakazam.setMove(1, new FocusBlast());
                alakazam.setMove(2, new ShadowBall());
                alakazam.setMove(3, new ThunderWave());
                return alakazam;
            }
            
            private static Salamence getPerfectSalamence() {
                Salamence salamence = new Salamence(50);
                salamence.setEV(0, 252, 0, 252, 4, 0);
                salamence.setIV(31, 31, 31, 31, 31, 31);
                salamence.setNature(new Naive());
                salamence.setAbility(new Intimidate());
                salamence.setMove(0, new DragonDance());
                salamence.setMove(1, new DragonClaw());
                salamence.setMove(2, new Earthquake());
                salamence.setMove(3, new FireBlast());
                return salamence;
            }

            private static Dragonite getPerfectDragonite() {
                Dragonite dragonite = new Dragonite(50);
                dragonite.setEV(0, 252, 0, 252, 0, 4);
                dragonite.setIV(31, 31, 31, 31, 0, 31);
                dragonite.setNature(new Adamant());
                dragonite.setAbility(new Multiscale());
                dragonite.setMove(0, new DragonDance());
                dragonite.setMove(1, new DragonClaw());
                dragonite.setMove(2, new FirePunch());
                dragonite.setMove(3, new ExtremeSpeed());
                return dragonite;
            }
            
            private static Latios getPerfectLatios() {
                Latios latios = new Latios(50);
                latios.setEV(0, 0, 0, 252, 252, 4);
                latios.setIV(31, 0, 31, 31, 31, 31);
                latios.setNature(new Timid());
                latios.setAbility(new Levitate());
                latios.setMove(0, new DracoMeteor());
                latios.setMove(1, new Psychic());
                latios.setMove(2, new Surf());
                latios.setMove(3, new HiddenPower(new Fire()));
                return latios;
            }

            private static Latias getPerfectLatias() {
                Latias latias = new Latias(50);
                latias.setEV(4, 0, 0, 252, 252, 0);
                latias.setIV(31, 0, 31, 31, 31, 31);
                latias.setNature(new Timid());
                latias.setAbility(new Levitate());
                latias.setMove(0, new DracoMeteor());
                latias.setMove(1, new Psychic());
                latias.setMove(2, new Surf());
                latias.setMove(3, new Roost());
                return latias;
            }
            

            private static Regice getPerfectRegice() {
                Regice regice = new Regice(50);
                regice.setEV(0, 0, 4, 252, 252, 0);
                regice.setIV(31, 0, 31, 31, 31, 31);
                regice.setNature(new Modest());
                regice.setAbility(new ClearBody());
                regice.setMove(0, new RockPolish());
                regice.setMove(1, new IceBeam());
                regice.setMove(2, new Thunderbolt());
                regice.setMove(3, new FocusBlast());
                return regice;
            }
            
            private static Articuno getPerfectArticuno() {
                Articuno articuno = new Articuno(50);
                articuno.setEV(28, 0, 0, 228, 252, 0);
                articuno.setIV(31, 0, 31, 31, 31, 31);
                articuno.setNature(new Modest());
                articuno.setAbility(new Pressure());
                articuno.setMove(0, new Agility());
                articuno.setMove(1, new IceBeam());
                articuno.setMove(2, new Hurricane());
                articuno.setMove(3, new HiddenPower(new Fight()));
                return articuno;
            }
            
            private static Dusclops getPerfectDusclops() {
                Dusclops dusclops = new Dusclops(50);
                dusclops.setEV(252, 0, 124, 0, 132, 0);
                dusclops.setIV(31, 0, 31, 31, 31, 31);
                dusclops.setNature(new Bold());
                dusclops.setAbility(new Pressure());
                dusclops.setMove(0, new CalmMind());
                dusclops.setMove(1, new Rest());
                dusclops.setMove(2, new ShadowBall());
                dusclops.setMove(3, new DarkPulse());
                return dusclops;
            }
            
            private static Blastoise getPerfectBlastoise() {
                Blastoise blastoise = new Blastoise(50);
                blastoise.setEV(252, 0, 4, 0, 252, 0);
                blastoise.setIV(31, 0, 31, 31, 31, 31);
                blastoise.setNature(new Modest());
                blastoise.setAbility(new RainDish());
                blastoise.setMove(0, new WaterPulse());
                blastoise.setMove(1, new AuraSphere());
                blastoise.setMove(2, new IceBeam());
                blastoise.setMove(3, new DarkPulse());
                return blastoise;
            }

            private static Charizard getPerfectCharizard() {
                Charizard charizard = new Charizard(50);
                charizard.setEV(0, 252, 4, 252, 0, 0);
                charizard.setIV(31, 31, 31, 31, 0, 31);
                charizard.setNature(new Jolly());
                charizard.setAbility(new Blaze());
                charizard.setMove(0, new DragonDance());
                charizard.setMove(1, new FlareBlitz());
                charizard.setMove(2, new ThunderPunch());
                charizard.setMove(3, new DragonClaw());
                return charizard;
            }

            private static Venusaur getPerfectVenusaur() {
                Venusaur venusaur = new Venusaur(50);
                venusaur.setEV(252, 0, 0, 4, 252, 0);
                venusaur.setNature(new Modest());
                venusaur.setAbility(new Chlorophyll());
                venusaur.setMove(0, new SludgeBomb());
                venusaur.setMove(1, new Synthesis());
                venusaur.setMove(2, new GigaDrain());
                venusaur.setMove(3, new SleepPowder());
                return venusaur;
            }
            

            private static Smeargle getPerfectSmeargle() {
                Smeargle smeargle = new Smeargle(50);
                smeargle.setEV(0, 4, 0, 252, 252, 0);
                smeargle.setIV(31, 31, 31, 31, 0, 31);
                smeargle.setNature(new Adamant());
                smeargle.setAbility(new Moody());
                smeargle.setMove(0, new FakeOut());
                smeargle.setMove(1, new Spore());
                smeargle.setMove(2, new FlashCannon());
                smeargle.setMove(3, new Recover());

                return smeargle;
            }

}
