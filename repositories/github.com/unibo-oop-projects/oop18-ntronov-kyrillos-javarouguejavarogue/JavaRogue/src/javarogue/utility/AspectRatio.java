package javarogue.utility;

public enum AspectRatio {

	SIXTEEN_NINE {
		@Override
		public int getHorizontal() {
			return 16;
		}

		@Override
		public int getVertical() {
			return 9;
		}
	},
	
	FOUR_THREE {
		@Override
		public int getHorizontal() {
			return 4;
		}

		@Override
		public int getVertical() {
			return 3;
		}
	};
	
	public abstract int getHorizontal();
	
	public abstract int getVertical();
	
}
