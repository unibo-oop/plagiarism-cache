package it.unibo.game;

import it.unibo.game.app.api.AppController;
import it.unibo.game.app.controller.ControllerImpl;

/**
 * class that starts the application.
 */
public final class LaunchApp {
  private LaunchApp() {
  }

  /**
   * Runs the application.
   *
   * @param args ignored
   */
  public static void main(final String... args) {
    final AppController app = new ControllerImpl();
    app.setModel();
    app.setView();
    app.setGameEngine();
  }
}
