package model.enemy;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Stream;

import model.command.Command;

/**
 * 
 * An implementation of AiManager that permit to orchestrate AIs in the game.
 */
public final class AiMAnagerImpl implements AiManager {
    private static final int GENERATION_THRESHOLD = 10;
    private final List<EnemyAi> artificials;

    /**
     * Default constructor that instantiate the artificial with a new empty list.
     */
    public AiMAnagerImpl() {
        this.artificials = new ArrayList<EnemyAi>();
    }

    @Override
    public void startAll() {
        artificials.stream().forEach(ai -> ai.start());

    }

    @Override
    public void stopAll() {
        artificials.stream().forEach(ai -> ai.stop());
    }

    @Override
    public void resetAll() {
        artificials.clear();
    }

    @Override
    public void generateAiCommands() {
        artificials.stream().filter(ai -> ai.isStarted()).forEach(ai -> ai.generate(GENERATION_THRESHOLD));
    }

    @Override
    public EnemyAi getNewEnemyAI(final AiCommandGenerator aiGenerator) {
        final EnemyAi tempAI = new EnemyAi() {
            private boolean started = true;
            private final AiCommandGenerator generator = aiGenerator;
            private final Queue<Command> commands = new LinkedList<>();

            @Override
            public void stop() {
                this.started = false;

            }

            @Override
            public void start() {
                this.started = true;

            }

            @Override
            public boolean isStarted() {
                return started;
            }

            @Override
            public Queue<Command> getCommandQueue() {
                return commands;
            }

            @Override
            public void generate(final int thereshold) {
                if (commands.isEmpty() && this.isStarted()) {
                    Stream.generate(() -> generator.generateNextCommand()).limit(thereshold)
                            .forEach(c -> commands.add(c));
                }
            }
        };

        this.artificials.add(tempAI);
        return tempAI;
    }

}
