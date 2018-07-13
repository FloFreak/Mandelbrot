/*
 * PictureClickListener
 * v1.0
 * 02. Juli 2018
 * Copyright (C) 2018 Florian Warnke
 * All rights reserved.
 */
package io.github.flofreak.listener;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import io.github.flofreak.gui.GUI;
import io.github.flofreak.algorithms.BaseAlgorithm;
import io.github.flofreak.threads.CalculationThread;

/**
 * The listener for clicking the picture
 * todo Comments
 */
public class PictureClickListener implements MouseListener {

    @Override
    /*todo add the ability to click on image to recenter*/
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) {
            Point point = e.getPoint();
            double column = point.x;
            double row = point.y;

            double zoom = GUI.gui.getZoom();
            double maxreal = GUI.gui.getMaxReal();
            double minreal = GUI.gui.getMinReal();
            double maximg = GUI.gui.getMaxImag();
            double minimg = GUI.gui.getMinImag();

            double realfactor = (maxreal - minreal) / BaseAlgorithm.WIDTH;
            double imgfactor = (maximg - minimg) / BaseAlgorithm.HEIGHT;

            double x = minreal + (column * realfactor);
            double y = maximg - (row * imgfactor);

            double difX = (maxreal - minreal)/(zoom*2);
            double difY = (maximg - minimg)/(zoom*2);

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
