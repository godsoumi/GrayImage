package mi.sou;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class GrayscaleConverter {

    public static BufferedImage convert(BufferedImage original) {
        BufferedImage grayImage = new BufferedImage(
                original.getWidth(),
                original.getHeight(),
                BufferedImage.TYPE_INT_ARGB
        );

        for (int y = 0; y < original.getHeight(); y++) {
            for (int x = 0; x < original.getWidth(); x++) {
                Color color = new Color(original.getRGB(x, y));
                int grayLevel = (int) (0.299 * color.getRed() +
                        0.587 * color.getGreen() +
                        0.114 * color.getBlue());
                Color gray = new Color(grayLevel, grayLevel, grayLevel);
                grayImage.setRGB(x, y, gray.getRGB());
            }
        }

        return grayImage;
    }
}

