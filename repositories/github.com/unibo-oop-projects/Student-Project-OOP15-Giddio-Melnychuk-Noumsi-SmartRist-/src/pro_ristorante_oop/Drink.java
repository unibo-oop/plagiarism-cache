package pro_ristorante_oop;

public class Drink implements IPiatti {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2733533344033498085L;

	private String name;
	private Integer ID;
	private Double coust;
	private String desc;
	final private static Double IVA = 0.22;
	
	Drink(String name,Integer ID,Double cost,String des){
		this.name=name;
		this.ID=ID;
		this.coust=cost;
		this.desc=des;
	}
	
	Drink(String name,Integer ID,Double cost){
		this.name=name;
		this.ID=ID;
		this.coust=cost;
		this.desc="";
	}
	
	@Override
	public Integer getID() {
		// TODO Auto-generated method stub
		return this.ID;
	}

	@Override
	public String getname() {
		// TODO Auto-generated method stub
		return this.name;
	}

	@Override
	public Double getcost() {
		// TODO Auto-generated method stub
		return this.coust;
	}

	@Override
	public void setdesc(String msg){
		   this.desc=msg;
	}
   
	public String getdesc(){
		return this.desc;
	}
	@Override
	public double getcost_iva() {
		// TODO Auto-generated method stub
		return this.coust*IVA;
	}

}
