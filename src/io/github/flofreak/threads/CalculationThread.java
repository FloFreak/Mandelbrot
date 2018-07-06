/*
 * CalculationThread
 * v1.1
 * 02. Juli 2018
 * Copyright (C) 2018 Florian Warnke
 * All rights reserved.
 */
package io.github.flofreak.threads;

import io.github.flofreak.GUI;

/**
 * The Thread which calculates and draw the image
 *
 * @author florian.warnke
 * @version v1.1
 */
public class CalculationThread implements Runnable {

    /**
     * Calculates the image and let the GUI draw it
     */
    @Override
    public void run() {
        GUI.gui.jLabelLoading.setVisible(true);
        GUI.gui.setImage(GUI.gui.getAlgorithm().calculate());
    }
}