/*
 * Mandelbrot
 * v1.2
 * 02. Juli 2018
 * Copyright (C) 2018 Florian Warnke
 * All rights reserved.
 */
package io.github.flofreak.algorithms;

import java.awt.*;
import java.awt.image.BufferedImage;

/*Todo add all comments and other conventions*/
public class Mandelbrot implements BaseAlgorithm {
    private static final int[] colors = new int[MAX];

    private double offsetx = -WIDTH / 2;
    private double offsety = -HEIGHT / 2;

    public Mandelbrot() {
        for (int i = 0; i < MAX; i++)
            colors[i] = Color.HSBtoRGB(i / 120f, 1, i / (i + 5f));
    }

    public BufferedImage calculate() {
        /*todo add the min and max selections*/
        /*todo inputs must eventually be not 0*/
        /*todo must zoom must change axes*/
        double zoom = (gui.getZoom() == 0) ? 0.000001 : gui.getZoom();

        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                // Convert the screen coordinate to a fractal coordinate
                double panx = -100;
                double x0 = (x + offsetx + panx) / zoom;
                double pany = 0;
                double y0 = (y + offsety + pany) / zoom;

                // Iteration variables
                double a = 0;
                double b = 0;
                double rx = 0;
                double ry = 0;

                // Iterate
                int iterations = 0;
                while (iterations < MAX && (rx * rx + ry * ry <= 4)) {
                    rx = a * a - b * b + x0;
                    ry = 2 * a * b + y0;

                    // Next iteration
                    a = rx;
                    b = ry;
                    iterations++;
                }

                // Get palette color based on the number of iterations
                if (iterations == MAX) image.setRGB(x, y, 0x000000); // Black
                else image.setRGB(x, y, colors[iterations]);
            }
        }
        return image;
    }
}
