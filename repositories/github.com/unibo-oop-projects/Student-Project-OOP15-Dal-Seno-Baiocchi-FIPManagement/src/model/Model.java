package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import exceptions.ChampionshipAlreadyExistException;
import exceptions.TeamAlreadyInThisChampionshipException;
/**
 * Class that manage all the data of the FIP application
 * @author lucadalseno
 *
 */
public class Model implements IModel{
    /**
     * 
     */
	private static final long serialVersionUID = 1L;
	private Map<Championship,Set<Team>> dataMap;
    
    public Model(){
        this.dataMap = new HashMap<Championship, Set<Team>>();
    }
    
    @Override
    public void addChampionship(Championship champ) throws ChampionshipAlreadyExistException {
        if(!dataMap.containsKey(champ)){
            dataMap.put(champ, new HashSet<Team>());
        } else throw new ChampionshipAlreadyExistException();
    }

    @Override
    public void deletChampionship(Championship champ) {
        if(dataMap.containsKey(champ)){
            dataMap.remove(champ);
        } else throw new IllegalArgumentException();
    }
    
    @Override
    public List<Championship> getChampionship(){
    	List<Championship> order = new ArrayList<Championship>(dataMap.keySet());
    	order.sort((a,b)->{
    		return  a.toString().compareTo(b.toString());   
    	});
    	return order;
    }

    @Override
    public void addTeam(Championship champ, Team team) throws TeamAlreadyInThisChampionshipException {
        if(dataMap.containsKey(champ)){
            if(!dataMap.get(champ).contains(team)){
                dataMap.get(champ).add(team);
            } else throw new TeamAlreadyInThisChampionshipException();
        }  
    }
    
    @Override
    public List<Team> getTeam(Championship champ){
    	List<Team> order = (new ArrayList<Team>(dataMap.get(champ)));
    	order.sort((a,b)->{
			return a.toString().compareTo(b.toString());    		
    	});
    	return order; 
    }

    @Override
    public void removeTeam(Championship champ,Team team) {
        dataMap.get(champ).remove(team); 
    }
}