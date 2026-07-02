package model.enemy;

import java.util.Queue;

import model.command.Command;

/**
 * Interface that rappresent an AI that generate a queue of command to attach to
 * an enemy.
 */
public interface EnemyAi {

    /**
     * populate of the command queue if this is empty.
     * 
     * @param thereshold is the number of command to generate;
     */
    void generate(int thereshold);

    /**
     * 
     * @return the command queue initialized from the AI.
     */
    Queue<Command> getCommandQueue();

    /**
     * Start the AI to generate commands.
     */
    void start();

    /**
     * Stop the AI to generate commands.
     */
    void stop();

    /**
     * 
     * @return true if the AI is started, so it can generate commands.
     */
    boolean isStarted();

}
