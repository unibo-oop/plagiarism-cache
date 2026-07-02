package it.unibo.oop.mge.libraries;

public class Pair<X, Y> {

    private final X fst;
    private final Y snd;

    public Pair(final X fst, final Y snd) {
        super();
        this.fst = fst;
        this.snd = snd;
    }

    public final X getFst() {
        return fst;
    }

    public final Y getSnd() {
        return snd;
    }

    @Override
    public final int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((fst == null) ? 0 : fst.hashCode());
        result = prime * result + ((snd == null) ? 0 : snd.hashCode());
        return result;
    }

    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Pair)) {
            return false;
        }
        final Pair other = (Pair) obj;
        if (fst == null) {
            if (other.fst != null) {
                return false;
            }
        } else if (!fst.equals(other.fst)) {
            return false;
        }
        if (snd == null) {
            if (other.snd != null) {
                return false;
            }
        } else if (!snd.equals(other.snd)) {
            return false;
        }
        return true;
    }

    @Override
    public final String toString() {
        return "[" + fst + ";" + snd + "]";
    }

}
