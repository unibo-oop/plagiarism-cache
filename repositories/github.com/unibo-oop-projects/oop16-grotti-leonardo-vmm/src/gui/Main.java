package gui;

import testdata.TestDataFiller;

public final class Main {

  private static final Boolean TESTFLAG = true;

  private Main() {
  }

  /**
   * Metodo main.
   * @param args non utilizzato.
   */
  public static void main(final String[] args) {
    if (TESTFLAG) {
      final TestDataFiller test = new TestDataFiller();
      test.run();
    }
    final LoginMenu log = new LoginMenu();
    log.run();
  }

}
