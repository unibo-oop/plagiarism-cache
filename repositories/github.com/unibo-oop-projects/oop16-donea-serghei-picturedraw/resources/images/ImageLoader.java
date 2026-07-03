package images;


import java.awt.Image;
import java.awt.Toolkit;
public final class ImageLoader {
    
    private ImageLoader() {
        
    }
   
    public static Image setImage(String imageName) {
      return  Toolkit.getDefaultToolkit().getImage(ImageLoader.class.getResource(imageName));
              
    }
    
}
