package it.unibo.biscia.core;

import it.unibo.biscia.core.Entity.EntityManaged;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

final class InteractionManagerlImpl implements InteractionManager {
    private final Level.LevelManaged coreLevel;
    private final LevelAnalyzer analyzer;
    private final Map<Entity, Integer> eat = new HashMap<>();
    private final Map<Entity, Integer> trimmed = new HashMap<>();
    private final Set<Entity> removed = new HashSet<>();
    private final Set<Entity> updated = new HashSet<>();

    InteractionManagerlImpl(final Level.LevelManaged coreLevel) {
        this.coreLevel = coreLevel;
        this.analyzer = coreLevel.getAnalyzer();
    }

    private Set<Entity.EntityManaged> getIntersect(final Entity entity, final Set<Entity.EntityManaged> inter) {
        return inter.stream().filter(e -> !e.equals(entity))
                .filter(e -> e.getCells().stream().filter(c -> entity.getCells().contains(c)).findAny().isPresent())
                .collect(Collectors.toSet());
    }

    private Set<Entity.EntityManaged> getIntersect(final Entity entity) {
        return getIntersect(entity, this.getEntityIntrsecated());
    }

    private Set<Entity.EntityManaged> getEntityIntrsecated() {
        return this.coreLevel.getAnalyzer().getIntersecatedEntity().stream().collect(Collectors.toSet());
    }

    private Stream<Entity.EntityManaged> filterForInterfaces(final Stream<Entity.EntityManaged> stream,
            final Class<? extends Entity.EntityManaged> filters) {
        return stream.filter(e -> filters.isInstance(e));
    }

    private Stream<Entity.EntityManaged> filterForInterfaces(final Stream<Entity.EntityManaged> entities,
            final List<Class<? extends Entity.EntityManaged>> types) {
        var ret = entities;
        for (final var t : types) {
            ret = this.filterForInterfaces(ret, t);
        }
        return ret;
    }

    private Stream<Entity.EntityManaged> filterForType(final Stream<Entity.EntityManaged> entities,
            final EntityType type) {
        return entities.filter(e -> e.getType().equals(type));
    }

    private Stream<Entity.EntityManaged> filterForType(final Stream<Entity.EntityManaged> entities,
            final List<EntityType> types) {
        var ret = entities;
        for (final var t : types) {
            ret = filterForType(ret, t);
        }
        return ret;
    }

    private boolean detectEat(final Set<Entity.EntityManaged> inter) {
        final int removed = this.removed.size();
        // determino le intersezioni col cibo di:
        // prima gli eater directable (ai mangiatori pilotati do' la precedenza)
        // poi gli eater movable (poi i mangiatori che si muovono)
        // poi gli eater (tutti i mangiatori residui)
        // e alla fine tutti gli elementi, che non mangiano ma schiacciano (e quindi
        // distruggono( il cibo
        final List<Class<? extends Entity.EntityManaged>> filters = new LinkedList<>(
                Arrays.asList(Entity.EntityManaged.Eater.class, Entity.EntityManaged.Movable.class,
                        Entity.EntityManaged.Movable.Directable.class));
        boolean exit = false;
        while (!exit) {
            this.filterForInterfaces(inter.stream(), filters).forEach(eater -> {
                this.getIntersect(eater).stream().forEach(eatable -> {
                    if (eatable.getType().equals(EntityType.FOOD) || eatable instanceof Entity.EntityManaged.Eatable) {
                        this.removed.add(eatable);
                        this.coreLevel.removeEntity(eatable);
                        if (eater instanceof Entity.EntityManaged.Eater) {
                            final int energy = ((Entity.EntityManaged.Eatable) eatable).getEnergy();
                            ((Entity.EntityManaged.Eater) eater).eat(energy);
                            this.updated.add(eater);
                            this.eat.put(eater, energy + this.eat.getOrDefault(eater, 0));
                        }
                        this.updated.remove(eatable);
                    }
                });
            });
            if (filters.isEmpty()) {
                exit = true;
            } else {
                filters.remove(filters.size() - 1);
            }
        }

        return this.removed.size() > removed;
        /*
         * for (int i = 0; i < 4; i++) { final int j = i; inter.stream().filter(e -> (j
         * > 1) || e instanceof Entity.EntityManaged.Eater) .filter(e -> (j > 0) || e
         * instanceof Entity.EntityManaged.Movable.Directable).forEach(eater -> {
         * this.getIntersect(eater).stream() .filter(eatable -> eatable instanceof
         * Entity.EntityManaged.Eatable).forEach(eatable -> { this.removed.add(eatable);
         * this.coreLevel.removeEntity(eatable); if (eater instanceof
         * Entity.EntityManaged.Eater) { int energy = ((Entity.EntityManaged.Eatable)
         * eatable).getEnergy(); ((Entity.EntityManaged.Eater) eater).eat(energy);
         * this.updated.add(eater); this.eat.put(eater, energy +
         * this.eat.getOrDefault(eater, 0)); } this.updated.remove(eatable); }); }); }
         */
        // return (this.removed.size() < removed);

    }

    private boolean detectDead(final Set<Entity.EntityManaged> inter) {
        // in questo punto food ed eatable in genere son spariti, tutti gli scontri di
        // snake con la testa
        // portano alla morte singolarmente, quindi le eliminazioni le faccio alla fine
        this.filterForType(inter.stream(), EntityType.SNAKE).forEach(snake -> {
            // determino lo scontro col proprio corpo
            boolean crash = false;
            final Cell head = snake.getCells().get(0);
            for (int i = 1; i < snake.getCells().size() && !crash; i++) {
                if (snake.getCells().get(i).equals(head)) {
                    crash = true;
                }
            }
            if (!crash) {
                // determino lo scontro con altre entità
                crash = this.coreLevel.getAnalyzer().entitiesOnCell(snake.getCells().get(0)).size() > 1;
            }
            if (crash) {
                removed.add(snake);
                this.coreLevel.removeEntity(snake);
                this.updated.remove(snake);
            }
        });
        this.removed.addAll(removed);
        return !removed.isEmpty();

    }

    private void addTrim(final EntityManaged entity, final int trimmed) {
        if (entity.getCells().isEmpty()) {
            this.removed.add(entity);
            this.updated.remove(entity);
        } else {
            this.trimmed.put(entity, trimmed + this.trimmed.getOrDefault(entity, 0));
            this.updated.add(entity);
        }

    }

    private boolean detectTrim(final Set<Entity.EntityManaged> inter) {
        // tutti i wall che intersecano degli snake (a questo punto non nella testa),
        // tagliamno via la parte di snake verso la coda
        boolean trimmed = false;
        final var it = this.filterForType(inter.stream(), EntityType.SNAKE).collect(Collectors.toList());
        for (final var snake : it) {
            for (int i = 1; i < snake.getCells().size(); i++) {
                final Cell cell = snake.getCells().get(i);
                if (this.filterForType(inter.stream(), EntityType.WALL).flatMap(wall -> wall.getCells().stream())
                        .filter(c -> c.equals(cell)).findAny().isPresent()) {
                    addTrim(snake, snake.removeFromCell(i));
                    trimmed = true;
                }
            }
        }
        return trimmed;
    }

    private boolean detectDestroy(final Set<Entity.EntityManaged> inter) {
        // tutti i wall che intersecano altre cose (a questo punto altri wall) perdono
        // le celle che intersecano
        // verifico le celle soggette prima poi le elimino, così le elimino da antrambi
        // i wall
        final Set<Cell> cells = this.coreLevel.getAnalyzer().getIntersectionsCells().stream()
                .collect(Collectors.toSet());
        if (cells.isEmpty()) {
            return false;
        }
        cells.forEach(cell -> {
            inter.stream().filter(e -> e.getCells().contains(cell)).forEach(e -> {
                e.removeCell(e.getCells().indexOf(cell));
                if (e.getCells().isEmpty()) {
                    this.removed.add(e);
                    this.updated.remove(e);
                    this.eat.remove(e);
                } else {
                    this.trimmed.put(e, 1 + this.trimmed.getOrDefault(e, 0));
                    this.updated.add(e);
                }
            });
        });
        return true;
    }

    @Override
    public Map<Entity, Integer> getEat() {
        return this.eat;
    }

    @Override
    public Map<Entity, Integer> getTrimmed() {
        return this.trimmed;
    }

    @Override
    public Set<Entity> getRemoved() {
        return this.removed;
    }

    @Override
    public Set<Entity> getUpdated() {
        return this.updated;
    }

    private Set<Entity.EntityManaged.Movable> moveAllMoveable(
            final Map<Entity.EntityManaged.Movable.Directable, Direction> commands) {
        // set direction to directable elements
        commands.forEach((entity, direction) -> {
            if (entity instanceof Entity.EntityManaged.Movable.Directable) {
                ((Entity.EntityManaged.Movable.Directable) entity).setDirection(Optional.of(direction));
            }
        });
        return this.analyzer.getMovables().stream().filter(m -> m.getDirection().isPresent()).filter(m -> m.move())
                .collect(Collectors.toSet());
    }

    private Set<Entity.EntityManaged.Eater> growAllEater() {
        return this.analyzer.getEater().stream().filter(e -> e.grow()).collect(Collectors.toSet());
    }

    @Override
    public boolean performMovement(final Map<Entity.EntityManaged.Movable.Directable, Direction> commands) {
        this.eat.clear();
        this.removed.clear();
        this.updated.clear();
        // muovo i moveable
        this.moveAllMoveable(commands).forEach(e -> this.updated.add(e));
        // faccio crescere gli eater
        this.growAllEater().forEach(e -> this.updated.add(e));
        if (this.updated.isEmpty()) {
            return false;
        }
        Set<Entity.EntityManaged> inter = this.getEntityIntrsecated();
        // se non ci sono intersezioni esco con true
        if (inter.isEmpty()) {
            return true;
        }
        // gestisco le mangiate
        if (this.detectEat(inter)) {
            inter = this.getEntityIntrsecated();
            if (inter.isEmpty()) {
                return true;
            }
        }
        // gestisco la morte degli snake
        if (this.detectDead(inter)) {
            inter = this.getEntityIntrsecated();
            if (inter.isEmpty()) {
                return true;
            }
        }
        // gestisco il trim degli snake
        if (this.detectTrim(inter)) {
            inter = this.getEntityIntrsecated();
            if (inter.isEmpty()) {
                return true;
            }
        }

        if (this.detectDestroy(inter)) {
            inter = this.getEntityIntrsecated();
            if (inter.isEmpty()) {
                return true;
            }
        }
        return true;
    }

}
