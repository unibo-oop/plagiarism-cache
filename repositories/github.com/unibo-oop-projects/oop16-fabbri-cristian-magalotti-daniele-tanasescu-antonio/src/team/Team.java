package team;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import pokemon.Pokemon;

/**
 * 
 * @author daniele
 *
 */

public class Team implements Serializable{
    

        private static final long serialVersionUID = 1L;
        public static final int MAX_POKEMON = 6;
        private static final int STANDARDPPDECREMENT = 1;
    
        private final int numOfPokemon;
        private final Set<Pokemon> availablePokemons;           //il set di tutti i pokemon disponibili per quel team
        
        private int ppDecrement;                                //di norma 1, un'abilità può variarlo
        
	private Pokemon[] pokemon;
	
	//costruttore
	public Team(Pokemon[] availablePokemons, int numOfPokemon){
	        this.numOfPokemon = numOfPokemon;
	        //dall'array che viene passato genero il set di pokemon disponibili
	        this.availablePokemons = new HashSet<Pokemon>(Arrays.asList(availablePokemons));
	        //inizializzo l'array del team
	        this.pokemon = new Pokemon[this.numOfPokemon];
	        //genero randomicamente un team 
		this.pokemon = this.getRandomTeam();
		
		this.setPpDecrement(STANDARDPPDECREMENT);
	}
	
	//getter
	public Pokemon[] getPokemon(){
		return Arrays.copyOf(this.pokemon, this.pokemon.length);
	}
	
	public Pokemon getFirst(){
	    if(this.pokemon[0] != null){
	        return this.pokemon[0];
	    }
	    return null;
	}
	
	public boolean isAlmostOneAlive(){
	    return this.getAliveOnesCount() > 0;
	}
	
	public int getAliveOnesCount(){
	    int count = 0;
	    for(Pokemon teammer : this.pokemon){
	        if(!teammer.isFainted()){
	            count ++;
	        }
	    }
	    return count;
	}
	
	public Pokemon getRandomAlly(Pokemon actualActive){
	    if(this.getAliveOnesCount() > 1){          //oltre all'attivo deve essercene un altro!
	        Random random = new Random();
	        int position = random.nextInt(this.numOfPokemon);
	        if(!this.pokemon[position].isFainted() && !this.pokemon[position].equals(actualActive)){
	            return this.pokemon[position];
	        }
	        else{
	            Pokemon newActive = this.getRandomAlly(actualActive);  //riprovo ancora
	            return newActive;
	        }
	    }  
	    else{
	        if(this.isAlmostOneAlive() && actualActive.isFainted()){           //quando rimane solo un pokemon in vita, dietro
	            for(Pokemon teammer : this.pokemon){
	                if(!teammer.isFainted()){
	                    return teammer;
	                }
	            }
	        }
	    }
	    return null;                               //non ci sono alleati vivi
	}
	
	//sarà usata in modo sicuro, per questo non metto controlli sull'effettiva presenza di questo pokemonToSearch nella squadra
	public int getItsPosition(Pokemon pokemonToSearch){
	    int index = 0;
	    int answer = 0;
	    for(Pokemon member : this.pokemon){
	        if(member.equals(pokemonToSearch)){
	            answer = index;
	            index++;
	        }
	    }
	    return answer;
	}
	
	public Pokemon[] getAllies(Pokemon active){
	    Pokemon[] allies = new Pokemon[this.numOfPokemon-1];
	    int i = 0;
	    for(Pokemon pokemon : this.pokemon){
	        if(!pokemon.equals(active)){
	            allies[i] = pokemon;
	            i++;
	        }
	    }
	    return Arrays.copyOf(allies, allies.length);
	}
	
	private Pokemon[] getRandomTeam(){
	        Random random = new Random();
	        Pokemon[] pokemons = new Pokemon[this.numOfPokemon];
	        Pokemon pokemon = null;
	        //non c'è problema perchè il set avrà almeno numOfPokemons pokemon all'interno
	        for(int index = 0; index < this.numOfPokemon; index++){
	            int pokemonID = random.nextInt(this.availablePokemons.size());
	            Iterator<Pokemon> pokemonIterator = this.availablePokemons.iterator();
	            while(pokemonID >= 0 && pokemonIterator.hasNext()){
	                pokemonID--;
	                pokemon = pokemonIterator.next();
	            }
	            pokemons[index] = pokemon;
	            pokemonIterator.remove();                      
	        }
	        
	        return Arrays.copyOf(pokemons, pokemons.length);
	    }

    public int getPpDecrement() {
        return ppDecrement;
    }

    public void setPpDecrement(int ppDecrement) {
        this.ppDecrement = ppDecrement;
    }
    
    public int getNumOfPokemon(){
        return this.numOfPokemon;
    }
    
    public void pokemonCenterCare(){
        for(Pokemon teammer : this.pokemon){
            teammer.cure();
        }
    }
    
    public void trade(int index, Pokemon newPokemon){
        this.pokemon[index] = newPokemon;
    }
    
  }
