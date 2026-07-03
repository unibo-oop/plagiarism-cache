package enemies;

import characters.Position;
import dodge.DodgeImpl;

public class Nitro extends Enemy{
	
	public Nitro(final Position position) {
		super(position);
	}

	public static class NitroBuilder {
		
		//HAVE TO USE BUILDER (slide 76-77-78-69_Pattern)
		public Nitro build() {
			return null;
		}

	}

	@Override
	public void damageMod(final DodgeImpl dodge) {
		dodge.setLife(0);
	}

}
