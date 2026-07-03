package model.Astronauta;

import java.io.Serializable;

import model.Oggetti.Equipaggiamento;

public class Sopravvivenza implements Serializable {
    private static final long serialVersionUID = -8979983480567730812L;
    private static final Integer MAXFAME = 100;
    private static final Integer MAXSETE = 100;
    private static final Integer MAXOSSIGENO = 1000;

    private static final Integer MINFAME = 0;
    private static final Integer MINSETE = 0;
    private static final Integer MINOSSIGENO = 0;
    
    private static final Integer DECR_FAME = 1;
    private static final Integer DECR_SETE = 2;
    private static final Integer DECR_OSSIGENO = 17;
    
    private Integer fame = MAXFAME;
    private Integer sete = MAXSETE;
    private Integer ossigeno = MAXOSSIGENO;

    public Sopravvivenza() {
    }
    
    public Sopravvivenza(final Integer fame, final Integer sete, final Integer ossigeno) {
        this.fame = fame;
        this.sete = sete;
        this.ossigeno = ossigeno;
    }
    /**
     * Change hunger
     * @param modifica amount of the change
     */
    public void modificaFame(Integer modifica) {
        this.fame += modifica;
        if (this.fame > MAXFAME) {
            this.fame = MAXFAME;
        } else if (this.fame < MINFAME) {
            this.fame = MINFAME;
        }
    }
    /**
     * Change thirsty
     * @param modifica amount of the change
     */
    public void modificaSete(Integer modifica) {
        this.sete += modifica;
        if (this.sete > MAXSETE) {
            this.sete = MAXSETE;
        } else if (this.sete < MINSETE) {
            this.sete = MINSETE;
        }
    }
    /**
     * Change oxygen
     * @param modifica amount of the change
     */
    public void modificaOssigeno(Integer modifica) {
        this.ossigeno += modifica;
        if (this.ossigeno > MAXOSSIGENO) {
            this.ossigeno = MAXOSSIGENO;
        } else if (this.ossigeno < MINOSSIGENO) {
            this.ossigeno = MINOSSIGENO;
        }
    }

    /**
     * Getter method of the hunger
     * @return the integer value of the hunger
     */
    public Integer getFame() {
        return this.fame;
    }
    /**
     * Getter method of the thirsty
     * @return the integer value of the thirsty
     */
    public Integer getSete() {
        return this.sete;
    }
    /**
     * Getter method of the oxygen
     * @return the integer value of the oxygen
     */
    public Integer getOssigeno() {
        return this.ossigeno;
    }

    /**
     * Increase hour to decrease all the parameters
     * @param ore amount of hours passed
     */
    public void incrementaOre(final Integer ore) {
        this.modificaFame( -DECR_FAME * ore );
        this.modificaSete( -DECR_SETE * ore );
        this.modificaOssigeno( -(DECR_OSSIGENO + Equipaggiamento.getLog().getOssigeno()) * ore );
    }

    
    public String toString() {
        String returnString = "Fame: " + this.fame + "/" +MAXFAME + " Sete: " + this.sete + "/" +MAXSETE + " Ossigeno: " + this.ossigeno + "/" +MAXOSSIGENO;
        return returnString;
    }
}
