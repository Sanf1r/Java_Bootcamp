package edu.school21.printer.logic;

import java.io.*;
import java.awt.image.BufferedImage;
import java.awt.Color;
import javax.imageio.ImageIO;

public class Logic {
    public void read(String[] args) {
        String white = args[0];
        String black = args[1];
        try {
            BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/resources/image.bmp"));
            for (int i = 0; i < image.getHeight(); ++i) {
                for (int j = 0; j < image.getWidth(); ++j) {
                    int color = image.getRGB(j, i);
                    if (color == Color.white.getRGB()) {
                        System.out.print(white);
                    } else if (color == Color.black.getRGB()) {
                        System.out.print(black);
                    }
                }
                System.out.println();
            }
        } catch (IOException e) {
            System.err.println("Wrong file path!");
        }
    }
}
