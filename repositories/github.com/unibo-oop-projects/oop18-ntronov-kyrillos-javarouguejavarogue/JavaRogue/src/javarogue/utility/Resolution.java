package javarogue.utility;

/**
 * 
 * Represents various resolutions of a window. Has a method to provide window
 * width and height. Overrides toString to represent resolution in a
 * conventional way.
 *
 */
public enum Resolution {

	HD {

		@Override
		public int getWidth() {
			return 1366;
		}

		@Override
		public int getHeight() {
			return 768;
		}

		@Override
		public String toString() {
			return "1366 x 768";
		}

	},
	FHD {

		@Override
		public int getWidth() {
			return 1920;
		}

		@Override
		public int getHeight() {
			return 1080;
		}

		@Override
		public String toString() {
			return "1920 x 1080";
		}

	},
	WXGA {
		@Override
		public int getWidth() {
			return 1280;
		}

		@Override
		public int getHeight() {
			return 720;
		}

		@Override
		public String toString() {
			return "1280 x 720";
		}
		
	};

	/**
	 * 
	 * @return Resolution's width
	 */
	public abstract int getWidth();

	/**
	 * 
	 * @return Resolutuons's Height
	 */
	public abstract int getHeight();
}
