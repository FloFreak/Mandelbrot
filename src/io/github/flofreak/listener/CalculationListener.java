/*
 * CalculationListener
 * v1
 * 16. Juli 2018
 * Copyright (C) 2018 Florian Warnke
 * All rights reserved.
 */
package io.github.flofreak.listener;

import io.github.flofreak.gui.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The ActionListener which starts the calculation
 *
 * @author florian.warnke
 * @version v1
 */
public class CalculationListener implements ActionListener {

    /**
     * Sets the calculated image
     * @param e ActionEvent which is performed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        GUI.setImage(GUI.getAlgorithm().calculate());

    }
}
