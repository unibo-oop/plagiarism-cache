

package model;

import java.io.Serializable;

public class Pair<firstThing, secondThing> implements Serializable{
	   /**
	 * 
	 */
	private static final long serialVersionUID = 5927576208043847407L;
	private firstThing first;//first member of pair
	   private secondThing second;//second member of pair

	   public Pair(firstThing first, secondThing second){
	     this.first = first;
	     this.second = second;
	   }

	   public void setFirst(firstThing first){
	    this.first = first;
	   }

	   public void setSecond(secondThing second) {
	     this.second = second;
	   }

	   public firstThing getKey() {
	     return this.first;
	   }

	public secondThing getValue() {
	     return this.second;
	   }
	}

