package util;

public class ImageLoader
{
	private static String projectRoot = System.getProperty("ROOT_DIRECTORY", "./");
	
	public static String getImage(String image)
	{
		return ImageLoader.projectRoot+"img/"+image; 
	}
}
