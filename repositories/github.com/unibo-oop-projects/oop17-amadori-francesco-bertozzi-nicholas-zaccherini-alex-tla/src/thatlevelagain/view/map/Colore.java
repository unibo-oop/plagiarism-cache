package thatlevelagain.view.map;

/**
 * Enum that create various part of the world.
 * @author root
 *
 */
public enum Colore {

    /**
     * 
     */
    ROSSO(ValoreColori.MAXRGB.getNumero(), ValoreColori.ZERO.getNumero(), ValoreColori.ZERO.getNumero()),
    /**
     * 
     */
    BIANCO(ValoreColori.MAXRGB.getNumero(), ValoreColori.MAXRGB.getNumero(), ValoreColori.MAXRGB.getNumero()), 
    /**
     * 
     */
    GRIGIO(ValoreColori.CENTO.getNumero(), ValoreColori.CENTO.getNumero(), ValoreColori.CENTO.getNumero()), 
    /**
     * 
     */
    NERO(ValoreColori.ZERO.getNumero(), ValoreColori.ZERO.getNumero(), ValoreColori.ZERO.getNumero()),
    /**
     * 
     */
    FUXIA(ValoreColori.MAXRGB.getNumero(), ValoreColori.ZERO.getNumero(), ValoreColori.MAXRGB.getNumero()),
    /**
     * 
     */
    ARANCIONE(ValoreColori.MAXRGB.getNumero(), ValoreColori.CENTOCINQUANTATRE.getNumero(), ValoreColori.ZERO.getNumero()),
    /**
     * 
     */
    BLU(ValoreColori.ZERO.getNumero(), ValoreColori.ZERO.getNumero(), ValoreColori.MAXRGB.getNumero()),
    /**
     * 
     */
    GIALLO(ValoreColori.DUECENTOQUARANTA.getNumero(), ValoreColori.MAXRGB.getNumero(), ValoreColori.ZERO.getNumero()),
    /**
     * 
     */
    VERDE(ValoreColori.ZERO.getNumero(), ValoreColori.MAXRGB.getNumero(), ValoreColori.ZERO.getNumero()),
    /**
     * 
     */
    CIANO(ValoreColori.ZERO.getNumero(), ValoreColori.MAXRGB.getNumero(), ValoreColori.MAXRGB.getNumero()),
    /**
     * 
     */
    BLUGRIGIO(ValoreColori.SESSANTAQUATTRO.getNumero(), ValoreColori.SESSANTAQUATTRO.getNumero(), ValoreColori.CENTOSEDICI.getNumero()),
    /**
     * 
     */
    VERDEGRIGIO(ValoreColori.SESSANTAQUATTRO.getNumero(), ValoreColori.CENTOSEDICI.getNumero(), ValoreColori.SESSANTAQUATTRO.getNumero()),
    /**
     * 
     */
    ROSSOCHIARO(ValoreColori.CENTOSEDICI.getNumero(), ValoreColori.SESSANTAQUATTRO.getNumero(), ValoreColori.SESSANTAQUATTRO.getNumero()),
    /**
     * 
     */
    GRIGIO1(ValoreColori.DUECENTOTRENTASEI.getNumero(), ValoreColori.DUECENTOTRENTASEI.getNumero(), ValoreColori.DUECENTOTRENTASEI.getNumero()),
    /**
     * 
     */
    GRIGIO2(ValoreColori.DUECENTOVENTISETTE.getNumero(), ValoreColori.DUECENTOVENTISETTE.getNumero(), ValoreColori.DUECENTOVENTISETTE.getNumero()),
    /**
     * 
     */
    GRIGIO3(ValoreColori.DUECENTODICIANNOVE.getNumero(), ValoreColori.DUECENTODICIANNOVE.getNumero(), ValoreColori.DUECENTODICIANNOVE.getNumero()),
    /**
     * 
     */
    GRIGIO4(ValoreColori.DUECENTOQUATTORDICI.getNumero(), ValoreColori.DUECENTOQUATTORDICI.getNumero(), ValoreColori.DUECENTOQUATTORDICI.getNumero()), 
    /**
     * 
     */
    GRIGIO5(ValoreColori.DUECENTODIECI.getNumero(), ValoreColori.DUECENTODIECI.getNumero(), ValoreColori.DUECENTODIECI.getNumero()),
    /**
     * 
     */
    GRIGIO6(ValoreColori.DUECENTOOTTO.getNumero(), ValoreColori.DUECENTOOTTO.getNumero(), ValoreColori.DUECENTOOTTO.getNumero()),
    /**
     * 
     */
    GRIGIO7(ValoreColori.DUECENTODUE.getNumero(), ValoreColori.DUECENTODUE.getNumero(), ValoreColori.DUECENTODUE.getNumero()),
    /**
     * 
     */
    GRIGIO8(ValoreColori.DUECENTOUNO.getNumero(), ValoreColori.DUECENTOUNO.getNumero(), ValoreColori.DUECENTOUNO.getNumero()),
    /**
     * 
     */
    GRIGIO9(ValoreColori.CENTONOVANTASETTE.getNumero(), ValoreColori.CENTONOVANTASETTE.getNumero(), ValoreColori.CENTONOVANTASETTE.getNumero()),
    /**
     * 
     */
    GRIGIO10(ValoreColori.CENTONOVANTATRE.getNumero(), ValoreColori.CENTONOVANTATRE.getNumero(), ValoreColori.CENTONOVANTATRE.getNumero()),
    /**
     * 
     */
    GRIGIO11(ValoreColori.CENTOOTTANTA.getNumero(), ValoreColori.CENTOOTTANTA.getNumero(), ValoreColori.CENTOOTTANTA.getNumero()),
    /**
     * 
     */
    GRIGIO12(ValoreColori.CENTOSETTANTADUE.getNumero(), ValoreColori.CENTOSETTANTADUE.getNumero(), ValoreColori.CENTOSETTANTADUE.getNumero()),
    /**
     * 
     */
    VIOLA(ValoreColori.CENTOVENTOTTO.getNumero(), ValoreColori.TRENTASETTE.getNumero(), ValoreColori.CENTOVENTICINQUE.getNumero()),
    /**
     * 
     */
    GIALLINO(ValoreColori.CENTONOVANTACINQUE.getNumero(), ValoreColori.MAXRGB.getNumero(), ValoreColori.ZERO.getNumero()),
    /**
     * 
     */
    MARRONE(ValoreColori.CENTOCINQUANTAQUATTRO.getNumero(), ValoreColori.SESSANTUNO.getNumero(), ValoreColori.ZERO.getNumero()),
    /**
     * 
     */
    MAGENTA(ValoreColori.CENTO.getNumero(), ValoreColori.SESSANTAQUATTRO.getNumero(), ValoreColori.SESSANTAQUATTRO.getNumero()), 
    /**
     * 
     */
    VERDINO(ValoreColori.CENTOQUARANTASEI.getNumero(), ValoreColori.MAXRGB.getNumero(), ValoreColori.TRENTOTTO.getNumero());
    private final int rosso, verde, blu;
    /**
     * 
     * @param rosso
     *        red value
     * @param verde
     *        green value
     * @param blu
     *        blue value
     */
    Colore(final int rosso, final int verde, final int blu) {
        this.rosso = rosso;
        this.verde = verde;
        this.blu = blu;
    }

    /**
     * 
     * @return
     *       red value
     */

    public int getRosso() {
        return rosso;
    }
    /**
     * 
     * @return
     *       green value
     */
    public int getVerde() {
            return verde;
    }
    /**
     * 
     * @return
     *      blue value
     */
    public int getBlu() {
            return blu;
    }
}
