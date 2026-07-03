package players;

import java.util.LinkedList;

/**
 *
 * @param <Player>
 * 
 * my extension of linked list.
 */
@SuppressWarnings("hiding")
public class CircList<Player> extends LinkedList<Player>  {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Integer ind;

    /**
     * circular list's constructor, defines the index.
     */
    public CircList() {
        super();
        this.ind = 0;
    }

    /**
     * 
     * @return the following player of the list.
     */
    public Player next() {
        if (this.ind.equals(this.size() - 1)) {
            this.ind = 0; 
        } else {
            this.ind += 1;
        }
        return this.get(this.ind);
    }
}
