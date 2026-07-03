package util;

/** Classe Node. */
public final class Node implements Comparable<Node> {

    private final Vector2<Integer> position;
    private final Node parent;
    private final double fCost, gCost, hCost;

    /**
     * Creazione di nodo a partire dalle Coordinate, dalle Coordinate della tile
     * padre, da un gcost e un hcost.
     * 
     * @param position
     *            Coordinate della tile.
     * @param parent
     *            Coordinate della tile padre.
     * @param gCost
     *            Costo dalla tile di partenza
     * @param hCost
     *            Costo per arrivare alla tile obiettivo.
     */
    public Node(final Vector2<Integer> position, final Node parent, final double gCost, final double hCost) {
        this.position = position;
        this.parent = parent;
        this.gCost = gCost;
        this.hCost = hCost;
        this.fCost = this.gCost + this.hCost;
    }

    /**
     * Ritorna la posizione della tile associata al nodo.
     * 
     * @return Posizione della tile.
     */

    public Vector2<Integer> getPosition() {
        return position;
    }

    /**
     * Ritorna il Nodo padre.
     * 
     * @return Nodo da cui proveniamo.
     */

    public Node getParent() {
        return parent;
    }

    /**
     * Ritorna il costo a partire dalla tile di partenza alla tile corrente.
     * 
     * @return Costo a partire dall'inizio del percorso.
     */

    public double getGCost() {
        return gCost;
    }

    /**
     * Ritorna il costo per arrivare alla tile obiettivo.
     * 
     * @return Costo per arrivare alla fine del percorso.
     */

    public double getHCost() {
        return hCost;
    }

    /**
     * Ritorna la somma di gCost e hCost.
     * 
     * @return Costo totale del percorso.
     */

    public double getFCost() {
        return fCost;
    }

    @Override
    public int compareTo(final Node o) {
        return fCost < o.fCost ? -1 : o.fCost < fCost ? 1 : 0;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(fCost);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(gCost);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(hCost);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((parent == null) ? 0 : parent.hashCode());
        result = prime * result + ((position == null) ? 0 : position.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof Node) {
            return position.equals(((Node) obj).position);
        } else {
            return false;
        }
    }

}
