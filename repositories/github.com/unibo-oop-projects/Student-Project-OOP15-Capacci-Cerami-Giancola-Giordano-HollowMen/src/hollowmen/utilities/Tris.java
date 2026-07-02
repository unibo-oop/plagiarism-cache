package hollowmen.utilities;

public class Tris<X,Y,Z> extends Pair<X,Y>{

	private final Z z;
	
	public Tris(X x, Y y, Z z) {
		super(x, y);
		this.z = z;
	}

	public Z getZ() {
		return this.z;
	}
	
	  @Override
	    public int hashCode() {
	        return super.hashCode() * z.hashCode();
	    }

	    @Override
	    public boolean equals(final Object obj) {
	    	return super.equals(obj) && obj instanceof Tris ? z.equals(((Tris<?, ?, ?>) obj).z) : false;
	    }

	    @Override
	    public String toString() {
	        return "<" + super.getX() + ", " + super.getY() + ", " + z + ">";
	    }
	
}
