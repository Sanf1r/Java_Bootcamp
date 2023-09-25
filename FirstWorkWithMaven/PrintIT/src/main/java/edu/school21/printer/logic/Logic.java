package edu.school21.printer.logic;

import java.io.*;
import java.awt.image.BufferedImage;
import java.awt.Color;
import javax.imageio.ImageIO;
import com.diogonunes.jcolor.AnsiFormat;
import com.diogonunes.jcolor.Attribute;

import static com.diogonunes.jcolor.Ansi.colorize;
import static com.diogonunes.jcolor.Attribute.*;

public class Logic {
    public void read(String white, String black) {
        try {
            BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/image.bmp"));
            for (int i = 0; i < image.getHeight(); ++i) {
                for (int j = 0; j < image.getWidth(); ++j) {
                    int color = image.getRGB(j, i);
                    if (color == Color.white.getRGB()) {
                        colorPrinter(white);
                    } else if (color == Color.black.getRGB()) {
                        colorPrinter(black);
                    }
                }
                System.out.println();
            }
        } catch (IOException e) {
            System.err.println("Wrong file path!");
        }
    }

    public void colorPrinter(String mode) {
        switch (mode) {
            case "RED":
                System.out.print(colorize("  ", RED_BACK()));
                break;
            case "GREEN":
                System.out.print(colorize("  ", GREEN_BACK()));
                break;
            case "YELLOW":
                System.out.print(colorize("  ", YELLOW_BACK()));
                break;
            case "BLUE":
                System.out.print(colorize("  ", BLUE_BACK()));
                break;
            case "MAGENTA":
                System.out.print(colorize("  ", MAGENTA_BACK()));
                break;
            case "CYAN":
                System.out.print(colorize("  ", CYAN_BACK()));
                break;
        }

    }
}
