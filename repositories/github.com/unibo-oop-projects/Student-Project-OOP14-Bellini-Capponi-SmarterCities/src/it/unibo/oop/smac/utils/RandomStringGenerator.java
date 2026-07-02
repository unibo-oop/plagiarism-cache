package it.unibo.oop.smac.utils;

/**
 * Generatore di stringhe formato da un determinato set di caratteri.
 * 
 * @author Francesco Capponi
 */
public final class RandomStringGenerator {

  private RandomStringGenerator() {

  }

  /**
   * Modalità con cui si vuole generare la stringa.
   */
  public static enum Mode {
    /**
     * Solo lettere dell'alfabeto.
     */
    ALPHA,

    /**
     * lettere dell'alfabeto e numeri.
     */
    ALPHANUMERIC,
    /**
     * Solo numeri.
     */
    NUMERIC
  }

  /**
   * Utility che data la lunghezza e la modalità di generazione, restituisce una stringa composta
   * dai caratteri richiesti.
   * 
   * @param length
   *          lunghezza della stringa da generare
   * @param mode
   *          tipi di caratteri da utilizzare nella generazione ({@link RandomStringGenerator.Mode}
   * @return stringa composta dai caratteri richiesti
   */
  public static String generateRandomString(final int length, final Mode mode) {

    final StringBuffer buffer = new StringBuffer();
    String characters = "";
    switch (mode) {
      case ALPHA:
        characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        break;
      case NUMERIC:
      default:
        characters = "1234567890";
        break;
    }

    final int charactersLength = characters.length();

    for (int i = 0; i < length; i++) {
      final double index = Math.random() * charactersLength;
      buffer.append(characters.charAt((int) index));
    }
    return buffer.toString();
  }
}