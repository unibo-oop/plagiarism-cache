package Model;

/**
 * This class implements a list of all the rooms used by the faculty
 * 
 * @author Francesco Ceroni
 * 
 */

public enum ListRoom {
    MAGNA("Aula Magna Vicolo Carbonari"),
    MAGNA_PSICO("Aula Magna Psicologia"),
    A("Aula A"),
    B("Aula B"),
    C("Aula C"),
    D("Aula D"),
    E("Aula E"),
    VELA("Laboratorio Vela"),
    LAB2("Laboratorio 2"),
    LAB3("Laboratorio 3"),
    GPT("Aula G.P.T. Via Genova");
    
     
     private final String y;
     
     private ListRoom(String l){
         this.y = l;
     }
     
     public String getValue(){
         return this.y;
     }
}
