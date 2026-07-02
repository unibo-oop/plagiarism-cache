package util;

import java.awt.Color;
import java.awt.Dimension;

/**
 * An Interface to store all the constants in the project.
 */
public class Constants {
	
	public static class ObjectDimension{
		public final static Dimensions screenInfo = new Dimensions();
		public final static Dimension preferDimension = new Dimension(screenInfo.getPreferScreenWidth(), screenInfo.getPreferScreenHeight());
		public final static Dimension maxLabelDimension = new Dimension(screenInfo.getMaxLabelWidth(), screenInfo.getMaxLabelHeight());
		public final static Dimension maxButtonDimension = new Dimension(screenInfo.getMaxLabelWidth(), screenInfo.getMaxButtonHeight());
		public final static Dimension maxTextFieldDimension = new Dimension(screenInfo.getMaxTextFieldWidth(), screenInfo.getMaxTextFieldHeight());
	}
	
	public class ObjectSize{
		public final static int imageDimension = 100;
		public final static int titleSize = 60;
		public final static int subtitleSize = 25;
	}
	
	public class LeaderboardConstants{
		public final static int minPodium = 1;
		public final static int maxPodium = 3;
		public final static int maxLeaderboardCharacters = 10; 
	}
	
	public static class Colors{
		public final static Color colorTitle = Color.cyan;
		public final static Color colorSubtitle = Color.white;
	}
	
	public class AudioConstants{
		public final static boolean IN_LOOP = true;
		public final static boolean NOT_IN_LOOP = false;
		public final static float VOLUME_LEVEL_START = 1.0f;
		public final static int SPACING = 1;
		public final static int minSliderValue = 0;
		public final static int maxSliderValue = 10;
	}
	
	public class LayoutDimension{
		public final static int specificRow = 2;
		public final static int specificColumns = 2;
	}
}
