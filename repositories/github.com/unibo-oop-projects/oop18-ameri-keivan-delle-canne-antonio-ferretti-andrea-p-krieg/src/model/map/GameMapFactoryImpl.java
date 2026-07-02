package model.map;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfoList;
import io.github.classgraph.ScanResult;
import javafx.util.Pair;
import model.managers.SkillTreeManager;
import model.objects.structures.Capital;
import model.objects.structures.CityImpl;
import model.objects.structures.Harbor;
import model.objects.structures.Level1Capital;
import model.objects.structures.Structure;
import model.objects.terrains.BasicTerrain;
import model.objects.terrains.Terrain;
import model.objects.terrains.WaterTerrain;
import model.player.Player;
import util.Coordinates;
import util.rectangle.Rectangle;
import util.rectangle.RectangleFactory;
import util.rectangle.RectangleFactoryImpl;
import util.rectangle.RectangleImpl;

import static java.lang.Math.min;
import static java.lang.Math.max;
import static util.Coordinates.sum;
import static util.Coordinates.subtract;;

/**
 * {@link GameMapFactory}.
 */
public class GameMapFactoryImpl implements GameMapFactory {

    /** {@inheritDoc} **/
    @Override
    public ModifiableGameMap getEmptyMapFromBackground(final Map<Coordinates, Terrain> background,
            final Pair<Integer, Integer> size) {
        return new AbstractGameMap(size) {

            /** {@inheritDoc} **/
            @Override
            protected Map<Coordinates, Case> generateGameMap() {
                return new RectangleImpl<Case>(size.getValue(), size.getKey(), (p) -> new CaseImpl(background.get(p)))
                        .toMap();
            }
        };
    }

    /** {@inheritDoc} **/
    @Override
    public ModifiableGameMap gameMapWithIslands(final Pair<Integer, Integer> size, final Set<Player> players,
            final SkillTreeManager skillTreeManager) {
        // manager
        List<Class<? extends Terrain>> terrainClasses = getClassesFromPackage(Terrain.class, "model.objects.terrains")
                .stream()
                .filter(c -> Arrays.asList(c.getConstructors()).stream().anyMatch(ic -> ic.getParameterCount() == 0))
                .collect(Collectors.toList());
        List<Class<? extends Structure>> structureClasses = getClassesFromPackage(Structure.class,
                "model.objects.structures").stream().filter(
                        c -> Arrays.asList(c.getConstructors()).stream().anyMatch(ic -> ic.getParameterCount() == 0))
                        .collect(Collectors.toList());
        List<Supplier<Structure>> structures = structureClasses.stream().map(c -> (Supplier<Structure>) (() -> {
            try {
                return (Structure) c.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
            return new CityImpl(Optional.empty());
        })).collect(Collectors.toList());
        List<Supplier<Terrain>> terrains = terrainClasses.stream().map(c -> (Supplier<Terrain>) (() -> {
            try {
                return (Terrain) c.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
            return new BasicTerrain();
        })).collect(Collectors.toList());
        return gameMapWithIslandsFromElements(size, players, terrains, structures, skillTreeManager);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ModifiableGameMap gameMapWithIslandsFromElements(final Pair<Integer, Integer> size,
            final Set<Player> players, final List<Supplier<Terrain>> terrains,
            final List<Supplier<Structure>> structures, final SkillTreeManager skillTreeManager) {
        terrains.removeIf(s -> s.get() instanceof BasicTerrain);
        terrains.removeIf(s -> s.get() instanceof WaterTerrain);
        structures.removeIf(s -> s.get() instanceof Harbor);

        return new AbstractGameMap(size) {

            /** {@inheritDoc} **/
            @Override
            protected Map<Coordinates, Case> generateGameMap() {

                final Pair<Rectangle<Case>, List<Pair<Coordinates, Coordinates>>> randomMap = getRandomMapWithIslands(
                        size.getKey(), size.getValue());
                final Rectangle<Case> background = randomMap.getKey();
                final List<Pair<Coordinates, Coordinates>> islandsEdges = randomMap.getValue();

                // creating a capital for each player
                final List<Capital> capitals = players.stream().map(p -> new Level1Capital(Optional.of(p)))
                        .collect(Collectors.toList());
                // edges:key()down left, value()up right
                islandsEdges.forEach(i -> {
                    Coordinates size = sum(subtract(i.getValue(), i.getKey()), new Coordinates(1, 1));
                    final Rectangle<Case> island = background.getSubrectangle(i.getKey(), size.getY(), size.getX());
                    final int minStructures = 6;
                    final int maxStructures = 7;
                    final int minTerrains = maxStructures;
                    final int maxTerrains = minTerrains + 2;
                    final int capitalsOnIsland = (players.size() / islandsEdges.size())
                            + players.size() % islandsEdges.size();
//                    final int maxCapitalsOnIsland = minCapitalsOnIsland + 1;

                    int terrainsToGenerate = randomBetween(minTerrains, maxTerrains);
                    final int structuresToGenerate = randomBetween(minStructures, maxStructures);

                    // dock generation
                    List<Coordinates> dockPositions = new ArrayList<>();
                    final Coordinates shiftedDownLeft = subtract(i.getKey(), new Coordinates(1, 1));
                    final Coordinates shiftedUpRight = sum(i.getValue(), new Coordinates(1, 1));
                    dockPositions.add(shiftedDownLeft);
                    dockPositions.add(shiftedUpRight);
                    dockPositions.addAll(IntStream.range(1, size.getX() + 2)
                            .mapToObj(s -> Arrays.asList(sum(shiftedDownLeft, new Coordinates(s, 0)),
                                    subtract(shiftedUpRight, new Coordinates(s, 0))))
                            .flatMap(l -> l.stream()).collect(Collectors.toSet()));
                    dockPositions.addAll(IntStream.range(1, size.getY() + 2)
                            .mapToObj(s -> Arrays.asList(sum(shiftedDownLeft, new Coordinates(0, s)),
                                    subtract(shiftedUpRight, new Coordinates(0, s))))
                            .flatMap(l -> l.stream()).collect(Collectors.toSet()));
                    final int dockToGenerate = randomBetween(0, dockPositions.size());

                    background.get(dockPositions.get(dockToGenerate)).setStructure(new Harbor(skillTreeManager));

                    // put capitals on the island
                    for (int k = 0; k < capitalsOnIsland && !capitals.isEmpty(); k++) {
                        island.get(new Coordinates((island.width() * ((1 + (k * 2))) / (capitalsOnIsland * 2)),
                                island.height() / 2)).setStructure(capitals.remove(0));
                    }

                    // differentiate the terrains on the island, eliminate basicTerrain and
                    // waterTerrain
                    terrains.forEach(t -> generateTerrainsOnSquare(island, t, terrainsToGenerate));

                    // generate the structures
                    // for each terrain build the compatible structures
                    structures.forEach(s -> generateStructuresOnSquare(island, s, structuresToGenerate));

                    background.setSubrectangle(i.getKey(), island);
                });
                return background.toMap();
            }
        };
    }

    private Pair<Rectangle<Case>, List<Pair<Coordinates, Coordinates>>> getRandomMapWithIslands(final int h,
            final int w) {
        final CaseFactory caseFactory = new CaseFactoryImpl();
        final RectangleFactory<Case> zoneFactory = new RectangleFactoryImpl<>();
        final List<Pair<Coordinates, Coordinates>> islandsEdges = new ArrayList<>();

        final int maxIslandHeight = 8;
        final int minIslandHeight = 6;
        final int maxIslandWidth = 17;
        final int minIslandWidth = 15;
        final int offset = 3;
        // generate the islands with random dimension between max area and a min area,
        // considering a max total area wich has to be > than the sum of all the max
        // area
        // islands have to be placed in random points calculated considering distance
        // from the previously placed, divide the map in rectangles, calculate random
        // point in a random rectangle, calculate biggest square, recalculate max area,
        // calculate random point in one of the new square, generate island to fit in
        final Rectangle<Case> background = zoneFactory.getFromElement(h, w,
                (p) -> caseFactory.getEmptyCase(new WaterTerrain()));
        for (int i = 2; i < background.width() - (minIslandWidth + 1); i += offset) {
            int topWidth = 0;
            for (int j = 2; j < background.height() - (minIslandHeight + 1); j += offset) {
                // set the island position
                int remainingWidth = background.width() - i - 2;
                int remainingHeight = background.height() - j - 2;
                int height = min(randomBetween(minIslandHeight, maxIslandHeight), remainingHeight);
                int width = min(randomBetween(minIslandWidth, maxIslandWidth), remainingWidth);
                final Coordinates edge = new Coordinates(i, j);
                islandsEdges.add(
                        new Pair<Coordinates, Coordinates>(edge, sum(edge, new Coordinates(width - 1, height - 1))));
                // generate the island
                Rectangle<Case> island = zoneFactory.getFromElement(height, width,
                        (p) -> caseFactory.getEmptyCase(new BasicTerrain()));

                // adding the new island to the map
                background.setSubrectangle(edge, island);

                topWidth = max(topWidth, width);
                j += island.height();

            }
            i += topWidth;
        }
        return new Pair<Rectangle<Case>, List<Pair<Coordinates, Coordinates>>>(background, islandsEdges);
    }

    @SuppressWarnings("unchecked")
    private <X> List<Class<? extends X>> getClassesFromPackage(final Class<X> c, final String packageName) {
        try (ScanResult scan = new ClassGraph().enableAllInfo().whitelistPackages(packageName).scan()) {
            ClassInfoList classesOnly = scan.getClassesImplementing(c.getName()).getStandardClasses();
            return classesOnly.loadClasses().stream().map(cl -> (Class<? extends X>) cl).collect(Collectors.toList());
        }
    }

    private void generateStructuresOnSquare(final Rectangle<Case> island, final Supplier<Structure> structureSupplier,
            final int structuresToGenerate) {
        if (island.toMap().values().stream().map(v -> v.getTerrain())
                .anyMatch(t -> structureSupplier.get().canBeBuilt(t))) {
            Stream.generate(
                    () -> new Coordinates(randomBetween(0, island.width() - 1), randomBetween(0, island.height() - 1)))
                    .distinct().filter(c -> !island.get(c).getStructure().isPresent())
                    .filter(c -> structureSupplier.get().canBeBuilt(island.get(c).getTerrain()))
                    .limit(structuresToGenerate).forEach(c -> {
                        island.get(c).setStructure(structureSupplier.get());
                    });
        }
    }

    private <X> void generateTerrainsOnSquare(final Rectangle<Case> island, final Supplier<Terrain> terrainSupplier,
            final int terrainsToGenerate) {
        CaseFactory caseFactory = new CaseFactoryImpl();
        Stream.generate(
                () -> new Coordinates(randomBetween(0, island.width() - 1), randomBetween(0, island.height() - 1)))
                .distinct().filter(c -> !island.get(c).getStructure().isPresent()).limit(terrainsToGenerate)
                .filter(c -> island.get(c).getTerrain() instanceof BasicTerrain).forEach(c -> {
                    island.set(c, caseFactory.getEmptyCase(terrainSupplier.get()));
                });
    }

    // min inclusive, max exclusive
    private int randomBetween(final int min, final int max) {
        Random rand = new Random();
        return rand.nextInt(max - min) + min;
    }
}
