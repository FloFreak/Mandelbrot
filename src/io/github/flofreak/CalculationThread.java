/*
 * CalculationThread
 * v1.1
 * 02. Juli 2018
 * Copyright (C) 2018 Florian Warnke
 * All rights reserved.
 */
package io.github.flofreak;

/**
 * The Thread which calculates and draw the image
 *
 * @author florian.warnke
 * @version v1.1
 */
class CalculationThread extends Thread {
    private final GUI gui;               //The GUI where everything will be displayed
    private final Mandelbrot mandelbrot; //The algorithm where the image is calculated

    /**
     * The thread which calculates the image and draws it
     *
     * @param gui        the GUI where it should be displayed
     * @param mandelbrot the algorithm
     */
    CalculationThread(GUI gui, Mandelbrot mandelbrot) {
        this.gui = gui;
        this.mandelbrot = mandelbrot;
    }

    /**
     * Calculates the image and let the GUI draw it
     */
    public void run() {
        gui.setImage(mandelbrot.calculate());
    }
}