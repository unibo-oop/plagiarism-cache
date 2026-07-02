package utilities;

import java.io.File;

public final class Multiplatform {
	
	public enum OSType {
		
		/**
		 * Gets operating system file separator.
		 */
		SEPARATOR(File.separator),
		
		/**
		 * Gets user home directory; 
		 */
		USER_HOME(System.getProperty("user.home"));
		
		private final String property;
		
		OSType(final String property) {
			this.property = property;
		}
		
		public String getProperty() {
			return this.property;
		}
	}
}
