package thatlevelagain.view.map;

/**
 * 
 * enum for Color class.
 *
 */
public enum ValoreColori {

    /**
     * RGB value.
     */
    MAXRGB(255), ZERO(0), CENTO(100), CENTOCINQUANTATRE(153), DUECENTOQUARANTA(240), SESSANTAQUATTRO(64), CENTOSEDICI(116), CENTOVENTOTTO(128), TRENTASETTE(37), CENTOVENTICINQUE(125),
    /**
     * 
     */
    DUECENTOTRENTASEI(236), DUECENTOVENTISETTE(227), DUECENTODICIANNOVE(219), DUECENTOQUATTORDICI(214), DUECENTODIECI(210), DUECENTOOTTO(208), DUECENTODUE(202), DUECENTOUNO(201), 
    /**
     * 
     */
    CENTONOVANTASETTE(197), CENTONOVANTATRE(193), CENTOOTTANTA(180), CENTOSETTANTADUE(172), CENTONOVANTACINQUE(195), CENTOQUARANTASEI(146), TRENTOTTO(38), CENTOCINQUANTAQUATTRO(154), SESSANTUNO(61);

    private final int numero;

   ValoreColori(final int numero) {
        this.numero = numero;
    }

   /**
    * @return
    *       value
    */
    public int getNumero() {
        return numero;
    }
}
