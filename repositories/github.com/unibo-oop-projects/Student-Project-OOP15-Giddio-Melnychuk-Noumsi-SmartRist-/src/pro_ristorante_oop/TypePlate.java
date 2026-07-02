package pro_ristorante_oop;

public enum TypePlate {
	ANTIPASTO(1),PRIMO(2),SECONDO(3),DRINK(4),DOLCE(5);
	
	int ric;
	
    private TypePlate(int num){
    this.ric = num;
    }
    
    public String toString(){
    	
    	switch(this.ric){
    	case 1:
    		return "Antipasto";
    	case 2:
    		return "Primo";
    	case 3: 
    		return "Secondo";
    	case 4:
    		return "Drink";
    	case 5:
    		return "Dolce";
    	default:
    		return "";
    	}
    }
    public TypePlate getATaypeFromAString(String st){

        	
        	switch(st){
        	case "Antipasto":
        		return TypePlate.ANTIPASTO;
        	case "Primo":
        		return TypePlate.PRIMO;
        	case "Secondo": 
        		return TypePlate.SECONDO;
        	case "Drink":
        		return TypePlate.DRINK;
        	case "Dolce":
        		return TypePlate.DOLCE;
        	default:
        	   throw new IllegalArgumentException();
        	   }
    }
        	
    
}
