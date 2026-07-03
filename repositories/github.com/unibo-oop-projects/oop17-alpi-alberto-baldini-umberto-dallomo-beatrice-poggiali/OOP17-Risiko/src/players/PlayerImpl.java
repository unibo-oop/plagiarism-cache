package players;

/**
 * 
 * has player params.
 */
public class PlayerImpl implements Player {

    private final String nome;
    private final int colore;        //from 1 to 6, 0 means that the land does'nt have an owner

    /**
     * 
     * @param nome : name of a player.
     * @param colore : color of a player.
     */
    public PlayerImpl(final String nome, final int colore) {
        this.nome = nome;
        this.colore = colore;
    }

    /* (non-Javadoc)
     * @see players.Player#getNome()
     */
    @Override
    public String getNome() {
        return this.nome;
    }

    /* (non-Javadoc)
     * @see players.Player#getColore()
     */
    @Override
    public int getColore() {
        return this.colore;
    }
}
