package dungeon.character;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class Worker.
 */
public final class Worker extends Thread {

  /** The queue. */
  private final List<Runnable> queue;

  /** The run. */
  private volatile boolean run;

  /**
   * Instantiates a new worker.
   */
  public Worker() {
    this.queue = new ArrayList<>();
    this.run = true;
  }

  /**
   * Run thread.
   */
  @Override
  public void run() {
    final List<Runnable> queue = new ArrayList<>();

    while (run) {
      synchronized (this.queue) {
        while (this.queue.size() == 0) {
          try {
            this.queue.wait();
          } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
        }

        queue.addAll(this.queue);
        this.queue.clear();
      }

      queue.forEach(entry -> entry.run()); 
      queue.clear();
    }
  }

  /**
   * End thread.
   */
  public void end() {
    this.interrupt();
    this.run = false;
  }

  /**
   * Adds the.
   *
   * @param entry the entry
   */
  public void add(final Runnable entry) {
    synchronized (this.queue) {
      this.queue.add(entry);
      this.queue.notifyAll();
    }
  }
}
