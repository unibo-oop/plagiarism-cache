package gymman.training.model;

import static gymman.training.model.Category.Type.*;

import java.util.ArrayList;
import java.util.List;

public enum Category {

	CARDIO(ENDURANCE, "cardio"),
	CORE(ENDURANCE, "core"),
	FULL_BODY(STRENGTH, "full_body"),
	FUNCTIONAL_TRAINING(STRENGTH, "functional_training"),
	OWN_BODY_WEIGHT(STRENGTH, "oun_body_weight"),
	STRETCHING(BALANCE, "stretching"),
	TONING(BALANCE, "toning");


	private final Type type;
	private final String actualName;

	private Category(final Type type, final String actualName){
		this.type = type;
		this.actualName = actualName;
	}

	public Type getType(){
		return this.type;
	}

	@Override
	public String toString(){
		return this.actualName;
	}


	enum Type {
		ENDURANCE, STRENGTH, BALANCE, FLEXIBILITY;

		public List<Category> getActivities(){
			final ArrayList<Category> list=new ArrayList<>();
			for (final Category a: Category.values()){
				if (a.getType()==this){
					list.add(a);
				}
			}
			return list;
		}
	}
 }

