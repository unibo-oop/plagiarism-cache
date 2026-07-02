package pro_ristorante_oop;

import java.io.Serializable;

public interface IPiatti extends Serializable {
	
	public Integer getID();
    String getname();
    Double getcost();
    void setdesc( String msg);
    String getdesc();
    double getcost_iva();
    public String toString();
}
