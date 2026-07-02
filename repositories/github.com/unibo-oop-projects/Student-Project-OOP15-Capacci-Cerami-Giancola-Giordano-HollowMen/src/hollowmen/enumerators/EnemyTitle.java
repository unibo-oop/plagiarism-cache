package hollowmen.enumerators;

public enum EnemyTitle {

		BOSS,
		COMMANDER,
		ORDINARY;
		
		@Override
		public String toString(){
			return this.name().toLowerCase();
		}

}
