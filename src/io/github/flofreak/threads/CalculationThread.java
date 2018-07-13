/*
 * CalculationThread
 * v1.1
 * 02. Juli 2018
 * Copyright (C) 2018 Florian Warnke
 * All rights reserved.
 */
package io.github.flofreak.threads;

import io.github.flofreak.gui.GUI;

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
        GUI.rightPanel.jLabelLoading.setVisible(true);
        GUI.setImage(GUI.gui.getAlgorithm().calculate());
    }
}