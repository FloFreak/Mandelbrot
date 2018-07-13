/*
 * PictureClickListener
 * v1.0
 * 02. Juli 2018
 * Copyright (C) 2018 Florian Warnke
 * All rights reserved.
 */
package io.github.flofreak.listener;

import io.github.flofreak.algorithms.BaseAlgorithm;
import io.github.flofreak.gui.GUI;
import io.github.flofreak.threads.CalculationThread;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * The listener for clicking the picture
 */
public class PictureClickListener implements MouseListener {

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) {
            double column = e.getX();
            double row = e.getY();

            double zoom = GUI.gui.getZoom();
            double maxreal = GUI.gui.getMaxReal();
            double minreal = GUI.gui.getMinReal();
            double maximg = GUI.gui.getMaxImag();
            double minimg = GUI.gui.getMinImag();

            double realfactor = (maxreal - minreal) / BaseAlgorithm.WIDTH;
            double imgfactor = (maximg - minimg) / BaseAlgorithm.HEIGHT;

            double x = minreal + (column * realfactor);
            double y = maximg - (row * imgfactor);

            double difY = 0;
            double difX = 0;
            if (e.getButton() == 1) {
                difX = (maxreal - minreal) / (zoom * 2);
                difY = (maximg - minimg) / (zoom * 2);
            } else if (e.getButton() == 3) {
                difX = (maxreal - minreal) * (zoom / 2);
                difY = (maximg - minimg) * (zoom / 2);
            }

            maxreal = x + difX;
            minreal = x - difX;
            maximg = y + difY;
            minimg = y - difY;

            GUI.setMinImag(minimg);
            GUI.setMaxImag(maximg);
            GUI.setMinReal(minreal);
            GUI.setMaxReal(maxreal);

            Thread thread = new Thread(new CalculationThread());
            thread.start();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
