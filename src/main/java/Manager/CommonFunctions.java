package Manager;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;


public interface CommonFunctions {
    default ImageIcon setIcon(String source){
        URL imageUrl = getClass().getResource(source);

        ImageIcon icon=null;
        if (imageUrl != null) {
            try (InputStream inputStream = imageUrl.openStream()) {

                Image originalImage = ImageIO.read(inputStream);

                icon = new ImageIcon(originalImage);

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("Nie udało się znaleźć zasobu.");
        }
        return icon;
    }

    default  String extractTitle(String inputString) {

        String[] parts = inputString.split(", ");
        for (String part : parts) {
            if (part.startsWith("Title:")) {
                String[] titleParts = part.split(": ");
                return titleParts[1];
            }
        }
        return null;
    }
}
