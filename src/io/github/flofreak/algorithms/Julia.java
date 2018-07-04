/*
    https://rosettacode.org/wiki/Julia_set#Java
 */
package io.github.flofreak.algorithms;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Julia implements BaseAlgorithm {

    public Julia() {

    }

    @Override
    public BufferedImage calculate() {
        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

        double cX = -0.7;
        double cY = 0.27015;
        double moveX = 0;
        double moveY = 0;
        double zx;
        double zy;

        double zoom = gui.getZoom();

        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                zx = 1.5 * (x - WIDTH / 2) / (0.5 * zoom * WIDTH) + moveX;
                zy = (y - HEIGHT / 2) / (0.5 * zoom * HEIGHT) + moveY;
                float i = MAX;
                while (zx * zx + zy * zy < 4 && i > 0) {
                    double tmp = zx * zx - zy * zy + cX;
                    zy = 2.0 * zx * zy + cY;
                    zx = tmp;
                    i--;
                }
                image.setRGB(x, y, Color.HSBtoRGB((MAX / i) % 1, 1, i > 0 ? 1 : 0));
            }
        }
        return image;
    }
}
