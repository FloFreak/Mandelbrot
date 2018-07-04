/*
 * CalculationThread
 * v1.1
 * 02. Juli 2018
 * Copyright (C) 2018 Florian Warnke
 * All rights reserved.
 */
package io.github.flofreak.threads;

import io.github.flofreak.GUI;
import io.github.flofreak.algorithms.BaseAlgorithm;

/**
 * The Thread which calculates and draw the image
 *
 * @author florian.warnke
 * @version v1.1
 */
public class CalculationThread implements Runnable {
    private final GUI gui;               //The GUI where everything will be displayed

    /**
     * The thread which calculates the image and draws it
     *
     * @param gui        the GUI where it should be displayed
     */
    public CalculationThread(GUI gui) {
        this.gui = gui;
    }

    /**
     * Calculates the image and let the GUI draw it
     */
    @Override
    public void run() {
        gui.jLabelLoading.setVisible(true);
        gui.setImage(gui.getAlgorithm().calculate());
    }
}