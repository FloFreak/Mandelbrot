/*
 * CalculationThread
 * v1.1
 * 02. Juli 2018
 * Copyright (C) 2018 Florian Warnke
 * All rights reserved.
 */
package io.github.flofreak;

import io.github.flofreak.algorithms.BaseAlgorithm;

/**
 * The Thread which calculates and draw the image
 *
 * @author florian.warnke
 * @version v1.1
 */
class CalculationThread extends Thread {
    private final GUI gui;               //The GUI where everything will be displayed
    private final BaseAlgorithm algorithm; //The algorithm where the image is calculated

    /**
     * The thread which calculates the image and draws it
     *
     * @param gui        the GUI where it should be displayed
     * @param algorithm the algorithm
     */
    CalculationThread(GUI gui, BaseAlgorithm algorithm) {
        this.gui = gui;
        this.algorithm = algorithm;
    }

    /**
     * Calculates the image and let the GUI draw it
     */
    public void run() {
        gui.setImage(algorithm.calculate());
    }
}