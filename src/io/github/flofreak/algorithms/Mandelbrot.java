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

    private final double offsetx = -WIDTH / 2;
    private final double offsety = -HEIGHT / 2;

    public Mandelbrot() {
        for (int i = 0; i < MAX; i++)
            colors[i] = Color.HSBtoRGB(i / 120f, 1, i / (i + 5f));
    }

    public BufferedImage calculate() {
        /*todo add the min and max selections*/
        /*todo inputs must eventually be not 0*/
        /*todo must zoom must change axes*/
        //abfangen von eingabefehlern//

        double zoom = gui.getZoom();
        double maxreal = gui.getMaxReal();
        double minreal = gui.getMinReal();
        double maximg = gui.getMaxImag();
        double minimg = gui.getMinImag();

        double realfactor = (maxreal - minreal) / WIDTH ;
        double imgfactor = (maximg - minimg) / HEIGHT ;


        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

        for (int row = 0; row < HEIGHT; row ++) {
            for (int column = 0; column < WIDTH; column ++) {

                double c_real = minreal + (column * realfactor);
                double c_img = maximg - (row * imgfactor);


                // Iteration variables
                double z_real = 0;
                double z_img = 0;

                // Iterate
                int iterations = 0;
                while (iterations < MAX && (z_real * z_real + z_img * z_img <= 4)) {

                    double new_z_real = z_real * z_real - z_img * z_img + c_real;
                    double new_z_img = 2 * z_real * z_img + c_img;

                    // Next iteration
                    z_real = new_z_real;
                    z_img = new_z_img;
                    iterations ++;

                }

                // Get palette color based on the number of iterations
                if (iterations == MAX) image.setRGB(column, row, 0x000000); // Black
                else image.setRGB(column, row, colors[iterations]);
            }
        }
        return image;
    }
}
