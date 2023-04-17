package Constants;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LoadSave {
    public static BufferedImage getSpireAtlas(String location, String fileName){
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("src/resources/textures/" + location + "/" + fileName));
        } catch (IOException e){
            e.printStackTrace();
        }
        return image;
    }

    public static BufferedImage GetIsland(){
        File file = new File("src/resources/island/island3.png");
        BufferedImage image = null;
        try {
            image = ImageIO.read(file);
        } catch (IOException e){
            e.printStackTrace();
        }
        return image;
    }
}
