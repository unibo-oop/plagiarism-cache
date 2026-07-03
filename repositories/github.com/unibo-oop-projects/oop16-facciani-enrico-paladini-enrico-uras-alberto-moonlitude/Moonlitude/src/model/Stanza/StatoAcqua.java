package model.Stanza;

import java.io.Serializable;

public enum StatoAcqua implements Serializable{
    POTABILE("Potabile"),
    ACIDA("Acida"),
    INSABBIATA("Insabbiata");
    
    private static final long serialVersionUID = -8979983480567110810L;
    private final String nome;
    
    private StatoAcqua(final String nome) {
        this.nome = nome;
    }
    /**
     * Getter method for the name of the water status
     * @return the string value of the name of the water status
     */
    public String getNome() {
        return this.nome;
    }
    
    public String toString() {
        return "L'acqua e': " + this.nome;
    }

}
