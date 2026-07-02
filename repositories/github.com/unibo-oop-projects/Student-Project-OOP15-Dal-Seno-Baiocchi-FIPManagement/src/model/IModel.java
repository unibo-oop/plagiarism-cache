package model;

import java.io.Serializable;
import java.util.List;

import exceptions.ChampionshipAlreadyExistException;
import exceptions.TeamAlreadyInThisChampionshipException;
/**
 * Define the model(following MVC pattern) of the FIP application
 * @author lucadalseno
 *
 */
public interface IModel extends Serializable {
    /**
     * 
     * @param champ: the championship to add
     * @throws ChampionshipAlreadyExistException if there's already that championship
     */
    void addChampionship(Championship champ) throws ChampionshipAlreadyExistException;
    /**
     * 
     * @param champ: the championship to delete
     */
    void deletChampionship(Championship champ);
    /**
     * 
     * @param champ: the reference championship 
     * @param team: the team to add
     * @throws TeamAlreadyInThisChampionshipException if there's already that team for that championship
     */
    void addTeam(Championship champ, Team team) throws TeamAlreadyInThisChampionshipException;
    /**
     * 
     * @param champ: the reference championship
     * @param team: the team to delete
     */
    void removeTeam(Championship champ,Team team);
    /**
     * 
     * @param champ the reference championship
     * @return a list of teams
     */
    List<Team> getTeam(Championship champ);
    /**
     * 
     * @return a list of championship
     */
    List<Championship> getChampionship(); 
}