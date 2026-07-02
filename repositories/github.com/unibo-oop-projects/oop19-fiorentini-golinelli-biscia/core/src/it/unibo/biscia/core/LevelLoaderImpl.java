package it.unibo.biscia.core;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

/**
 * for level generation.
 *
 */
class LevelLoaderImpl implements LevelLoader {

    private final String levelPath;

    LevelLoaderImpl(final String path) {
        levelPath = path;
    }

    private Level.LevelManaged getEmptyLevel(final int cardinal) {
        final var ret = new LevelImpl(Level.MIN_COLS, Level.MIN_ROWS);
        ret.setCardinal(cardinal);
        return ret;
    }

    @Override
    public Level.LevelManaged getFirstLevel() {
        return getLevel(1);
    }

    @Override
    public Level.LevelManaged getLevel(final int cardinal) {
        Level.LevelManaged ret;
        final String fileName = this.levelPath + "level" + cardinal + ".xml";
        final File file = new File(fileName);
        Optional<Level.LevelManaged> pop = Optional.empty();
        if (file.exists() && !file.isDirectory()) {
            pop = populateFromFile(file);
            if (pop.isPresent()) {
                ret = pop.get();
                ret.setCardinal(cardinal);
                return ret;
            }
        }
        ret = getEmptyLevel(cardinal);
        final var entityFactory = new EntityFactoryImpl(ret);
        populateCasual(entityFactory, ret);
        return ret;

    }

    @Override
    public Level.LevelManaged getNextLevel(final Level level) {
        return getLevel(level.getCardinal() + 1);
    }

    private void populateCasual(final EntityFactory entityFactory, final Level.LevelManaged level) {

        if (level.getCardinal() == 1) {
            return;
        }
        level.addEntity(entityFactory.makeEdge());
        final var analyzer = level.getAnalyzer();
        final Random random = new Random();
        int wallsCount = analyzer.getEntityOfType(EntityType.WALL).size();
        while (wallsCount < level.getCardinal() * 2) {
            int cols;
            int rows;
            cols = random.nextInt(3) + 1;
            rows = random.nextInt(3) + 1;
            switch (random.nextInt(3)) {
            case 0:
                cols = 1;
                break;
            case 1:
                rows = 1;
                break;
            default:
                break;
            }

            final List<Cell> freeCells = analyzer.getFreeCells();
            if (freeCells.isEmpty()) {
                break;
            }

            boolean ok = false;
            List<Cell> area = Arrays.asList(freeCells.get(random.nextInt(freeCells.size())));
            for (int i = 0; i < 10; i++) {
                area = level.getArea(freeCells.get(random.nextInt(freeCells.size())), cols, rows);
                if (analyzer.entitiesOnCells(area).isEmpty()) {
                    ok = true;
                    break;
                }
            }
            if (!ok) {
                area = Arrays.asList(freeCells.get(random.nextInt(freeCells.size())));
            }
            // List<Cell> areas = analyzer.getFreeAreas(cols, rows);
            // if (areas.isEmpty()) {
            // return;
            // }

            // level.addEntity(entityFactory.makeWall(level.getArea(areas.get(random.nextInt(areas.size())),
            // cols, rows)));
            level.addEntity(entityFactory.makeMovableWall(area,
                    Direction.values()[random.nextInt(Direction.values().length)],
                    MovementType.values()[random.nextInt(MovementType.values().length)], random.nextInt(3) + 1));
            wallsCount++;
        }
    }

    private Optional<Level.LevelManaged> populateFromFile(final File file) {
        Level.LevelManaged level;
        final DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        final DocumentBuilder documentBuilder;
        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document;
            document = documentBuilder.parse(file);
            if (!document.getDocumentElement().getNodeName().equals("Level")) {
                return Optional.empty();
            }
            final int cols = this.getAttrbuteIntegerValue(document.getDocumentElement(), "cols").orElse(Level.MIN_COLS);
            final int rows = this.getAttrbuteIntegerValue(document.getDocumentElement(), "rows").orElse(Level.MIN_ROWS);
            level = new LevelImpl(cols, rows);
            final EntityFactory factory = new EntityFactoryImpl(level);
            if (this.getAttrbuteIntegerValue(document.getDocumentElement(), "edge").orElse(0) == 1) {
                level.addEntity(factory.makeEdge());
            }
            List<Entity.EntityManaged> walls;
            walls = this.getWallNodes(factory, level, document);
            walls.forEach(w -> level.addEntity(w));

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
            return Optional.empty();
        }
        return Optional.of(level);
    }

    private List<Entity.EntityManaged> getWallNodes(final EntityFactory entityFactory, final Level.LevelManaged level,
            final Document document) {

        final List<Entity.EntityManaged> ret = new LinkedList<>();
        for (final LevelLoader.WallType wallType : LevelLoader.WallType.values()) {
            final List<Node> walls = this.getChild(document.getDocumentElement(),
                    wallType.name().substring(0, 1).toUpperCase(Locale.getDefault())
                            + wallType.name().substring(1).toLowerCase(Locale.getDefault()));

            for (final var wall : walls) {
                List<Cell> cells = this.getCellsNodes(level, wall);
                if (wallType.equals(LevelLoader.WallType.AREA)) {
                    if (cells.size() != 2) {
                        throw new IllegalArgumentException("area need only 2 cells");
                    }
                    cells = level.getArea(cells.get(0), cells.get(1));

                }
                if (cells.isEmpty()) {
                    throw new IllegalArgumentException("no cell on wall");
                }
                final Optional<String> movement = this.getAttrbuteStringValue(wall, "movement");
                Optional<MovementType> movType = Optional.empty();
                if (movement.isPresent()) {
                    for (final var m : MovementType.values()) {
                        if (m.name().equalsIgnoreCase(movement.get())) {
                            movType = Optional.of(m);
                            break;
                        }
                    }
                }
                if (movType.isEmpty()) {
                    ret.add(entityFactory.makeWall(cells));
                } else {
                    final Optional<String> dir = this.getAttrbuteStringValue(wall, "direction");
                    Direction direction = Direction.RIGHT;
                    final int interval = this.getAttrbuteIntegerValue(wall, "interval").orElse(1);
                    if (dir.isEmpty()) {
                        if (cells.size() > 1) {
                            for (final var d : Direction.values()) {
                                if (level.getSideCell(cells.get(1), d).equals(cells.get(0))) {
                                    direction = d;
                                    break;
                                }
                            }
                        }
                    } else {
                        for (final var d : Direction.values()) {
                            if (d.name().equalsIgnoreCase(dir.get())) {
                                direction = d;
                                break;
                            }
                        }

                    }
                    ret.add(entityFactory.makeMovableWall(cells, direction, movType.get(), interval));
                }
            }
        }
        return ret;
    }

    private List<Node> getChild(final Node parent, final String name) {
        final var nodes = parent.getChildNodes();
        if (nodes.getLength() == 0) {
            throw new IllegalArgumentException("no nodes on " + parent);
        }
        final List<Node> ret = new ArrayList<>(nodes.getLength());

        for (int i = 0; i < nodes.getLength(); i++) {
            final var node = nodes.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals(name)) {
                ret.add(node);
            }
        }
        return ret;
    }

    private List<Cell> getCellsNodes(final Level.LevelManaged level, final Node wall) {
        final List<Node> cells = this.getChild(wall, "Cell");
        final List<Cell> ret = new ArrayList<>(cells.size());
        for (final var cell : cells) {
            final int col = this.getAttrbuteIntegerValue(cell, "col").get();
            final int row = this.getAttrbuteIntegerValue(cell, "row").get();
            ret.add(level.getCell(col, row));
        }
        return ret;
    }

    private Optional<Integer> getAttrbuteIntegerValue(final Node node, final String attributeName) {
        final var a = node.getAttributes().getNamedItem(attributeName);
        if (Objects.isNull(a)) {
            return Optional.empty();
        }
        return Optional.of(Integer.valueOf(a.getTextContent()));
    }

    private Optional<String> getAttrbuteStringValue(final Node node, final String attributeName) {
        final var a = node.getAttributes().getNamedItem(attributeName);
        if (Objects.isNull(a)) {
            return Optional.empty();
        }
        return Optional.of(a.getTextContent());
    }
}
