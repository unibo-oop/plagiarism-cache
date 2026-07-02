package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javafx.util.Pair;
import model.Model;
import model.ModelImpl;
import model.entities.AttackType;
import model.entities.Entities;
import model.entities.Entity;
import model.entities.Hero;
import model.entities.MovementType;
import model.entities.Obstacle;
import model.utilities.ActionPerformed;
import model.utilities.TerrainType;
import model.utilities.exceptions.MismatchedPlayerTurnException;
import model.utilities.exceptions.TeamAlreadyFullException;
import uicontrollers.BattleController;
import uicontrollers.HeroPickerController;
import view.View;

public final class ControllerImpl implements Controller {
    private View v;
    private Model m;
    private static Controller controllerInstance;
    private static final int ARRAY_LENGTH = 5;

    /**
     * System separator for the system.
     */
    private static final String SEPARATOR = System.getProperty("file.separator");
    private static final String HOME_DIR = System.getProperty("user.home") + SEPARATOR + ".battle-tactics" + SEPARATOR;
    private static final String HEROES_DIR = HOME_DIR + "heroes" + SEPARATOR;
    private static final String MAPS_DIR = HOME_DIR + "maps" + SEPARATOR;
    private static final String IMAGES_DIR = HOME_DIR + "images" + SEPARATOR;

    private ControllerImpl() {
    }

    public static synchronized Controller getInstance() {
        if (ControllerImpl.controllerInstance == null) {
            ControllerImpl.controllerInstance = new ControllerImpl();
        }
        return ControllerImpl.controllerInstance;
    }

    @Override
    public void loadModel() {
        this.m = new ModelImpl();
    }

    @Override
    public void loadHeroImages() {
        final List<Hero> heroPool = new ArrayList<>();
        final File folder = new File(HEROES_DIR);
        final File[] listOfHeroes = folder.listFiles();
        if (listOfHeroes != null) {
            for (final File f : listOfHeroes) {
                try (BufferedReader reader = new BufferedReader(new FileReader(f))) {
                    final StringBuffer sb = new StringBuffer();
                    reader.lines().forEach(line -> {
                        sb.append(line);
                        sb.append(" ");
                    });
                    final String oneline = sb.toString();
                    final String[] arrayString = oneline.split("\\s+");
                    String[] stat = new String[ARRAY_LENGTH];
                    int j = 0;
                    for (final String s : arrayString) {
                        if (s.matches(".*\\d.*")) {
                            stat[j] = s;
                            j++;
                        }
                    }
                    heroPool.add(new Hero(arrayString[1], Integer.parseInt(stat[1]), Integer.parseInt(stat[0]),
                            Integer.parseInt(stat[2]),
                            MovementType.fromInteger(Integer.parseInt(stat[stat.length - 2])),
                            AttackType.fromInteger(Integer.parseInt(stat[stat.length - 1])), new Pair<>(0, 0)));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            this.m.setHeroPool(List.copyOf(heroPool));
            final List<String> heroImages = new ArrayList<>();
            for (final var h : this.m.getHeroPool().stream().map(p -> p.getKey()).collect(Collectors.toList())) {
                heroImages.add(resourceFromName(h.getInfo().get(0)));
            }
            this.v.showPickerScreen(List.copyOf(heroImages), this.m.getPickerPlayerTurn().toString());
        }
    }

    @Override
    public void setTeamSize(final int teamSize) {
        this.m.setTeamSize(teamSize);
    }

    @Override
    public void loadMapNames() {
        final List<String> mapNames = new ArrayList<>();
        final File folder = new File(MAPS_DIR);
        final File[] listOfMaps = folder.listFiles();
        if (listOfMaps != null) {
            for (final File f : listOfMaps) {
                if (f.isFile()) {
                    mapNames.add(f.getName());
                }
            }
            this.v.showPrePickerScreen(mapNames);
        }
    }

    @Override
    public void chooseArenaMap(final String name) {
        int width = 0;
        int height = 0;
        int cells = 0;
        String[] charArray = null;
        final List<TerrainType> arenaMap = new ArrayList<>();
        final File file = new File(MAPS_DIR + name);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            final String l = reader.readLine();
            if (l != null) {
                height = (int) reader.lines().count() + 1;
                width = l.split(" ").length;
                cells = height * width;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedReader reader2 = new BufferedReader(new FileReader(file))) {
            final StringBuffer arrayModificabile = new StringBuffer();
            String line;
            do {
                line = reader2.readLine();
                if (line != null) {
                    arrayModificabile.append(line);
                }
            } while (line != null);
            line = arrayModificabile.toString();
            charArray = line.split(" ");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        for (int i = 0; i < cells; i++) {
            if ("S".equals(charArray[i])) {
                arenaMap.add(TerrainType.EARTH);
            }
            if ("E".equals(charArray[i])) {
                arenaMap.add(TerrainType.EARTH);
            }
            if ("M".equals(charArray[i])) {
                arenaMap.add(TerrainType.MOUNTAIN);
            }
            if ("W".equals(charArray[i])) {
                arenaMap.add(TerrainType.WATER);
            }
            if ("O".equals(charArray[i])) {
                arenaMap.add(TerrainType.EARTH);
                final Obstacle obstacle = new Obstacle("Hwall", 3, 0, 0, new Pair<>(i / width, i % width));
                this.m.addObstacle(obstacle);
            }
            if ("T".equals(charArray[i])) {
                arenaMap.add(TerrainType.EARTH);
                final Obstacle obstacle = new Obstacle("Vwall", 3, 0, 0, new Pair<>(i / width, i % width));
                this.m.addObstacle(obstacle);
            }
        }
        this.m.generateArenaMap(arenaMap, height, width);
    }

    @Override
    public void startGameLoop() {
        final List<String> heroesPaths = Stream
                .of("Eadgar.txt", "Edhelthran.txt", "Frenrir.txt", "Henry.txt", "Iko.txt", "Kachi.txt", "Lenk.txt",
                        "Lycia.txt", "Merlin.txt", "Monron.txt", "Thrythwil.txt", "Winry.txt")
                .collect(Collectors.toList());
        final List<String> mapsPaths = Stream
                .of("CastleSiege.txt", "MappaConFiume.txt", "MappaTrincea.txt", "Prova.txt")
                .collect(Collectors.toList());
        final List<String> imgsPaths = Stream.of("Eadgar.png", "Edhelthran.png", "Frenrir.png", "Henry.png", "Iko.png",
                "Kachi.png", "Lenk.png", "Lycia.png", "Merlin.png", "Monron.png", "Thrythwil.png", "Winry.png",
                "earth.png", "water.png", "mountain.png", "Vwall.png", "Hwall.png").collect(Collectors.toList());
        if (!Files.exists(Paths.get(HOME_DIR))) {
            try {
                Files.createDirectories(Paths.get(HOME_DIR));
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                Files.createDirectories(Paths.get(HEROES_DIR));
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                Files.createDirectories(Paths.get(MAPS_DIR));
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                Files.createDirectories(Paths.get(IMAGES_DIR));
            } catch (IOException e) {
                e.printStackTrace();
            }
            for (final String s : heroesPaths) {
                try {
                    ClassLoader.getSystemResourceAsStream("heroes/" + s)
                            .transferTo(Files.newOutputStream(Paths.get(HEROES_DIR, s)));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }

            for (final String s : mapsPaths) {
                try {
                    ClassLoader.getSystemResourceAsStream("maps/" + s)
                            .transferTo(Files.newOutputStream(Paths.get(MAPS_DIR, s)));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            for (final String s : imgsPaths) {
                try {
                    ClassLoader.getSystemResourceAsStream("images/" + s)
                            .transferTo(Files.newOutputStream(Paths.get(IMAGES_DIR, s)));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        this.v.startView();
    }

    @Override
    public void setView(final View v) {
        this.v = v;
    }

    @Override
    public View getView() {
        return this.v;
    }

    @Override
    public void changePickerPosition(final int row, final int column, final int columnCount,
            final HeroPickerController hpc) {
        this.m.setPickerSelection(row * columnCount + column);
        final List<String> infos = this.m.getPickerSelectionInfo();
        this.v.showPickerSelectionInfo(infos.get(Entities.INFO_NAME_POS), infos.get(Entities.INFO_MAXHP_POS),
                infos.get(Entities.INFO_ATTACK_POS), infos.get(Entities.INFO_DEFENSE_POS),
                infos.get(Entities.INFO_MOVEMENTTYPE_POS), infos.get(Entities.INFO_ATTACKTYPE_POS), hpc);
    }

    /**
     * Private Method for getting path for Images.
     */
    private String resourceFromName(final String name) {
        return "/images/" + name + ".png";
    }

    @Override
    public void updatePool(final HeroPickerController hpc) {
        this.v.updatePickerHeroPool(this.m.getHeroPool().get(this.m.getPickerSelectionPosition()).getValue(),
                this.m.getPickerSelectionPosition(), hpc);
    }

    @Override
    public void pickerSelectionAction(final HeroPickerController hpc, final int identifier) {
        try {
            final ActionPerformed result = this.m.selectPickerSelection();
            switch (result) {
            case ADD_TO_TEAM_ONE:
                this.v.addTeamOne(resourceFromName(this.m.getPickerSelectionInfo().get(0)), identifier, hpc);
                this.m.changePickerTurn();
                this.v.updatePickerTurn(this.m.getPickerPlayerTurn().toString(), hpc);
                this.v.updatePickerHeroPool(this.m.getHeroPool().get(this.m.getPickerSelectionPosition()).getValue(),
                        this.m.getPickerSelectionPosition(), hpc);
                break;
            case ADD_TO_TEAM_TWO:
                this.v.addTeamTwo(resourceFromName(this.m.getPickerSelectionInfo().get(0)), identifier, hpc);
                this.m.changePickerTurn();
                this.v.updatePickerTurn(this.m.getPickerPlayerTurn().toString(), hpc);
                this.v.updatePickerHeroPool(this.m.getHeroPool().get(this.m.getPickerSelectionPosition()).getValue(),
                        this.m.getPickerSelectionPosition(), hpc);
                break;
            case REMOVE_FROM_TEAM_ONE:
                this.v.removeTeamOne(hpc, identifier);
                this.v.updatePickerHeroPool(this.m.getHeroPool().get(this.m.getPickerSelectionPosition()).getValue(),
                        this.m.getPickerSelectionPosition(), hpc);
                break;
            case REMOVE_FROM_TEAM_TWO:
                this.v.removeTeamTwo(hpc, identifier);
                this.v.updatePickerHeroPool(this.m.getHeroPool().get(this.m.getPickerSelectionPosition()).getValue(),
                        this.m.getPickerSelectionPosition(), hpc);
                break;
            default:
                break;
            }
        } catch (TeamAlreadyFullException e) {
            System.out.println("Team already full.");
            e.printStackTrace();
        } catch (MismatchedPlayerTurnException e) {
            System.out.println("Mismatched player turn.");
            e.printStackTrace();
        }
    }

    @Override
    public void pickerSelectionInfo(final HeroPickerController hpc) {
        final List<String> heroInfo = this.m.getPickerSelectionInfo();
        this.v.showPickerSelectionInfo(heroInfo.get(Entities.INFO_NAME_POS), heroInfo.get(Entities.INFO_MAXHP_POS),
                heroInfo.get(Entities.INFO_ATTACK_POS), heroInfo.get(Entities.INFO_DEFENSE_POS),
                heroInfo.get(Entities.INFO_MOVEMENTTYPE_POS), heroInfo.get(Entities.INFO_ATTACKTYPE_POS), hpc);
    }

    @Override
    public void loadBattleScene() {
        final Pair<Integer, Integer> p = this.m.getArenaMap().entrySet().stream().map(e -> e.getKey())
                .max((p1, p2) -> p1.getKey() + p1.getValue() - p2.getKey() - p2.getValue()).get();
        this.v.showGameScreen(p);
    }

    @Override
    public void loadBattleMap(final BattleController bc) {
        final Map<Pair<Integer, Integer>, String> arenaMap = this.m.getArenaMap().entrySet().stream().collect(Collectors
                .toMap(Map.Entry::getKey, e -> resourceFromName(e.getValue().toString().toLowerCase(Locale.ENGLISH))));
        this.m.startGame();
        this.v.drawArenaTerrain(arenaMap, bc);
        final Map<Pair<Integer, Integer>, String> aliveEntities = new HashMap<>();
        for (final Entity e : this.m.getAliveEntities()) {
            aliveEntities.put(e.getPosition(), this.resourceFromName(e.getInfo().get(Entities.INFO_NAME_POS)));
        }
        this.v.drawAliveEntities(aliveEntities, bc);
        this.v.updateGameTurn(bc, this.m.getGamePlayerTurn().toString());
    }

    @Override
    public void battleSelectionInfo(final BattleController bc) {
        final List<String> heroInfo = this.m.getGameSelectionInfo();
        this.v.showGameSelectionInfo(heroInfo.get(Entities.INFO_NAME_POS), heroInfo.get(Entities.INFO_MAXHP_POS),
                heroInfo.get(Entities.INFO_CURRENTHP_POS), heroInfo.get(Entities.INFO_ATTACK_POS),
                heroInfo.get(Entities.INFO_DEFENSE_POS), heroInfo.get(Entities.INFO_MOVEMENTTYPE_POS),
                heroInfo.get(Entities.INFO_ATTACKTYPE_POS), heroInfo.get(Entities.INFO_ATTACKSTATUS_POS),
                heroInfo.get(Entities.INFO_MOVEMENTSTATUS_POS), bc);
    }

    @Override
    public void battleCursorInfo(final BattleController bc) {
        final List<String> heroInfo = this.m.getGameCursorSelectionInfo();
        if (!Objects.isNull(heroInfo)) {
            if (!heroInfo.isEmpty()) {
                this.v.showGameCursorInfo(heroInfo.get(Entities.INFO_NAME_POS), heroInfo.get(Entities.INFO_MAXHP_POS),
                        heroInfo.get(Entities.INFO_CURRENTHP_POS), heroInfo.get(Entities.INFO_ATTACK_POS),
                        heroInfo.get(Entities.INFO_DEFENSE_POS), heroInfo.get(Entities.INFO_MOVEMENTTYPE_POS),
                        heroInfo.get(Entities.INFO_ATTACKTYPE_POS), heroInfo.get(Entities.INFO_ATTACKSTATUS_POS),
                        heroInfo.get(Entities.INFO_MOVEMENTSTATUS_POS), bc);
                this.v.showCursorSelectionMovementCandidates(this.m.getGameCursorSelectionMovementCandidates(), bc);
                this.v.showCursorSelectionAttackCandidates(this.m.getGameCursorSelectionAttackCandidates(), bc);
            }
        } else {
            this.v.showGameCursorInfo("", "", "", "", "", "", "", "", "", bc);
        }
    }

    @Override
    public void changeGameCursorPosition(final int row, final int column, final BattleController bc) {
        this.m.setGameCursorPosition(new Pair<>(row, column));
        this.battleCursorInfo(bc);
    }

    @Override
    public void gameSelectionAction(final BattleController bc, final String identifier) {
        final ActionPerformed result = this.m.selectGameCursorSelection();
        this.v.showSelectionMovementCandidates(this.m.getGameSelectionMovementCandidates(), bc);
        this.v.showSelectionAttackCandidates(this.m.getGameSelectionAttackCandidates(), bc);

        this.v.updateGameTurn(bc, this.m.getGamePlayerTurn().toString());
        final List<String> selectionHeroInfo = this.m.getGameSelectionInfo();
        if (!Objects.isNull(selectionHeroInfo)) {
            if (!selectionHeroInfo.isEmpty()) {
                this.v.showGameSelectionInfo(selectionHeroInfo.get(Entities.INFO_NAME_POS),
                        selectionHeroInfo.get(Entities.INFO_MAXHP_POS),
                        selectionHeroInfo.get(Entities.INFO_CURRENTHP_POS),
                        selectionHeroInfo.get(Entities.INFO_ATTACK_POS),
                        selectionHeroInfo.get(Entities.INFO_DEFENSE_POS),
                        selectionHeroInfo.get(Entities.INFO_MOVEMENTTYPE_POS),
                        selectionHeroInfo.get(Entities.INFO_ATTACKTYPE_POS),
                        selectionHeroInfo.get(Entities.INFO_ATTACKSTATUS_POS),
                        selectionHeroInfo.get(Entities.INFO_MOVEMENTSTATUS_POS), bc);
            }
        } else {
            this.v.showGameSelectionInfo("", "", "", "", "", "", "", "", "", bc);
        }
        switch (result) {
        case MOVE_ENTITY:
            this.v.updateEntityPosition(bc, this.m.getGameCursorSelectionPosition(), this.m.getOldGameSelection());
            break;
        case PLAYER1_WON:
            this.v.showVictoryMessage(bc, "Player 1 has won!");
            break;
        case PLAYER2_WON:
            this.v.showVictoryMessage(bc, "Player 2 has won!");
            break;
        default:
            break;
        }
        final Map<Pair<Integer, Integer>, String> aliveEntities = new HashMap<>();
        for (final Entity e : this.m.getAliveEntities()) {
            aliveEntities.put(e.getPosition(), this.resourceFromName(e.getInfo().get(Entities.INFO_NAME_POS)));
        }
        this.v.drawAliveEntities(aliveEntities, bc);
    }

    @Override
    public void changeGameTurn(final BattleController bc) {
        this.m.changeGameTurn();
        this.v.updateGameTurn(bc, this.m.getGamePlayerTurn().toString());
    }
}
