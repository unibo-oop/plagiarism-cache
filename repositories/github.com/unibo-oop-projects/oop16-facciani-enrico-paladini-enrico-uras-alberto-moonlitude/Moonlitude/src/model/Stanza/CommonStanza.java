package model.Stanza;


public enum CommonStanza {
    GIARDINO("Giardino"),
    MAGAZZINO("Magazzino"),
    REFRIGERATORE("Refrigeratore"),
    GENERATORE("Generatore"),
    FILTRATORE("Filtratore"),
    LABORATORIO("Laboratorio"),
    CUCINA("Cucina"),
    RADAR("Radar");
    
    private final String nome;    
    
    private CommonStanza(final String nome) {
        this.nome = nome;
    }
    
    /**
     * Getter method for the name of the room
     * @return the string value of the name of the room
     */
    public String getNome() {
        return this.nome;
    }
    
    public String toString() {
        return "Stanza: " + this.nome;
    }

}
