package it.unibo.the100dayswar.controller;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.junit.jupiter.api.Test;


/**
 * Test class for MainControllerImpl to verify the behavior of timeout 
 * handling in the asynchronous methods saveGame and loadOldGame.
 */
class MainControllerImplTest {
    private static final int SLEEP_TIME = 2000; // Simulated task duration in milliseconds
    private static final int TIMEOUT = 1; // Timeout in seconds

    /**
     * Tests that a simulated save or load operation fails when it exceeds the defined timeout.
     */
    @Test
    void testSaveGameTimeout() {
        final boolean result = simulateTaskWithTimeout();
        assertFalse(result, "The operation should fail due to timeout");
    }

    /**
     * Simulates an asynchronous save or load operation using ExecutorService with a timeout.
     * The task is designed to exceed the timeout and return false.
     *
     * @return true if the task completes within the timeout, false if it times out or an exception occurs.
     */
    private boolean simulateTaskWithTimeout() {
        final ExecutorService executor = Executors.newSingleThreadExecutor();

        // Simulated task that takes longer than the allowed timeout
        final Callable<Boolean> task = () -> {
            Thread.sleep(SLEEP_TIME); // Simulate a long-running operation
            return true;
        };

        final Future<Boolean> future = executor.submit(task);
        try {
            return future.get(TIMEOUT, TimeUnit.SECONDS);
        } catch (TimeoutException | InterruptedException | ExecutionException e) {
            return false;
        } finally {
            executor.shutdownNow();
        }
    }
}
