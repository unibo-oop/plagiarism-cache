package pro_ristorante_oop;



public class Cuoco extends Persona {

              
	/**
	 * 
	 */
	private static final long serialVersionUID = -7423359891263796785L;
	Ordine o;           // Oggetto  ordine per i piatti che il cuoco deve cucinare
	boolean kicthenOpen; // attributo booleano per verificare che la cucine del ristorante sia aperta 

	public Cuoco(String name , String surname , boolean sex){
		super(name,surname,sex);
		this.kicthenOpen = false;
		this.o= null;
		
	}

    

    public void  setOrdine (Ordine or){ // metodo per settare l' oggeto o
    	if(or== null)
    		throw new IllegalArgumentException();
    	
    	if(this.kicthenOpen)
    	    this.o.addOrd(or.getAOrd());
		else
			try {
				throw new Exception("la cucina e' chiusa");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    }
    
    public void kichenIsOpen(){ 
    	this.kicthenOpen=true;
    }
    
    public void kichenIsClose(){
    	this.kicthenOpen=false;
    }
    public void setOrdDone(OrdineD Od){//serve a settare Gli ordini fatti ed ho fatto in modo che 

                                       //possa contenere più  ordini
    	if(!this.kicthenOpen)
    		try {
				throw new Exception("la cucina e' chiusa");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	
    	if(o!=null){                     

      if(o!=null){                    //possa contenere piÃ¹  ordini

    	  if(!o.getFirstPtOfOrders().isEmpty()) 
    	    if(Od == null){                        
    		        Od = new OrdineD();
    		        Od.add(o.getFirstNumOfTableOfOrders().getX(),o.getFirstPtOfOrders().get(0));
    		        o.getFirstPtOfOrders().remove(0);
    	    }
    	     else{
    		         Od.add(o.getFirstNumOfTableOfOrders().getX(),o.getFirstPtOfOrders().get(0));
    		         o.getFirstPtOfOrders().remove(0);
    		} 
    	  else
    		 o.cancOrd(o.getFirstNumOfTableOfOrders());
      }
      else
    	  throw new IllegalArgumentException();
    	
    }
   
    /*public static void main(String[] args){
    	
    }*/
    }	
}
