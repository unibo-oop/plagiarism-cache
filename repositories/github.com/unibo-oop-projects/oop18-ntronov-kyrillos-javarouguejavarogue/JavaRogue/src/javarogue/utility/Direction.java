package javarogue.utility;

public enum Direction {

	UP {
		@Override
		public Position newPos(Position old) {
			return new Position(old.getX() - 1, old.getY());
		}
	},DOWN {
		@Override
		public Position newPos(Position old) {
			return new Position(old.getX() + 1, old.getY());
		}
	},LEFT {
		@Override
		public Position newPos(Position old) {
			return new Position(old.getX(), old.getY() - 1);
		}
	},RIGHT {
		@Override
		public Position newPos(Position old) {
			return new Position(old.getX(), old.getY() + 1);
		}
	};
	
	public abstract Position newPos(Position old);
	
}
