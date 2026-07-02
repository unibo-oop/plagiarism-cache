package utilities;

import utilities.Multiplatform.OSType;

public class RecordsFolder {
	
	private static final String USER_HOME_DIR = OSType.USER_HOME.getProperty();
	private static final String SEPARATOR = OSType.SEPARATOR.getProperty();
	private static final String RANKING_FOLDER_NAME = ".aloneInTheSpace";
	private static final String RANKING_DIRECTORY_NAME = "user_rankings";
	private static final String RANKING_FILE_NAME = "Ranking.txt";
	
	public enum RankingsPath {
		
		/**
		 * Path to rankings directory.
		 */
		RANKING_DIR_PATH(USER_HOME_DIR + SEPARATOR + RANKING_FOLDER_NAME),
		
		/**
		 * Path to user_rankings directory.
		 */
		USER_RANKING_DIR_PATH(RANKING_DIR_PATH.getPath() + SEPARATOR + RANKING_DIRECTORY_NAME),
		
		/**
		 * Path to text file in user_rankings directory.
		 */
		USER_RANKING_FILE_PATH(USER_RANKING_DIR_PATH.getPath() + SEPARATOR + RANKING_FILE_NAME);
		
		private final String path;
		
		RankingsPath(final String path) {
			this.path = path;
		}
		
		public String getPath() {
			return this.path;
		}
	}
}
