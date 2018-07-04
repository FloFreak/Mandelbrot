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

    public Mandelbrot() {
        for (int i = 0; i < MAX; i++)
            colors[i] = Color.HSBtoRGB(i / 120f, 1, i / (i + 5f));
    }

    public BufferedImage calculate() {
        /*todo add the min and max selections*/
        /*todo inputs must eventually be not 0*/
        /*todo decide weather zoom > 1 is a zoom out*/
        /*todo zoom and axis must be depended?*/
        double zoom = gui.getZoom();
        double minImag = gui.getMinImag();
        double maxImag = gui.getMaxImag();
        double minReal = gui.getMinReal();
        double maxReal = gui.getMaxReal();
        System.out.println(minImag + " " + maxImag + " " + minReal + " " + maxReal);

        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

        for (int row = 0; row < HEIGHT; row++) {
            for (int col = 0; col < WIDTH; col++) {
                double complexImaginary = (row - HEIGHT / 2) * zoom / WIDTH;
                double complexReal = (col - WIDTH / 2) * zoom / WIDTH;
                int iterations = 0;

                double x = 0;
                double y = 0;

                while (x * x + y * y < zoom && iterations < MAX) {
                    double x_new = x * x - y * y + complexReal;
                    y = 2 * x * y + complexImaginary;
                    x = x_new;
                    iterations++;
                }

                if (iterations < MAX) {
                    image.setRGB(col, row, colors[iterations]);
                } else {
                    image.setRGB(col, row, 0x000000);
                }
            }
        }
        return image;
    }
}
