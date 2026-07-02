package model;


/**
 * Interfaccia Food
 */
public interface Food {
	
    /**
     * Metodi getter della quantità di carboidrati di un cibo
     * 
     * @return
     *      carboidrati di un cibo
     *      
     */
	public double getCarbs();
	
	/**
     * Metodi getter della quantità di grassi di un cibo
     * 
     * @return
     *      grassi di un cibo
     *      
     */
	public double getFats();
	
	/**
     * Metodi getter della quantità di proteine di un cibo
     * 
     * @return
     *      proteine di un cibo
     *      
     */
	public double getProts();
	
	/**
     * Metodi getter della quantità di fibre di un cibo
     * 
     * @return
     *      fibre di un cibo
     *      
     */
	public double getFibers();
	
	/**
     * Metodo getter delle kcal derivanti da carboidrati
     * 
     * @return
     *      kcal derivanti da carboidrati
     *      
     */
	public double getCarbsKCals();
	
	/**
     * Metodo getter delle kcal derivanti da grassi
     * 
     * @return
     *      kcal derivanti da grassi
     *      
     */
	public double getFatsKCals();
	
	/**
     * Metodo getter delle kcal derivanti da proteine
     * 
     * @return
     *      kcal derivanti da proteine
     *      
     */
	public double getProtsKCals();
	
	/**
     * Metodo getter delle kcal totali di un cibo
     * 
     * @return
     *      le kcal totali di un alimento
     */
	public double getTotKcals();
	
	/**
     * Metodo che restituisce il totale di kcal di un alimento sommando le kcal derivanti
     * dai tre macronutrienti
     * 
     * @return
     *      il totale di kcal di un alimento
     */
	public double processTotKcals();
	
}
