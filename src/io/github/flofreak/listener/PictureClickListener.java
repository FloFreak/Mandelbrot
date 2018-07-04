/*
 * PictureClickListener
 * v1.0
 * 02. Juli 2018
 * Copyright (C) 2018 Florian Warnke
 * All rights reserved.
 */
package io.github.flofreak.listener;

import io.github.flofreak.GUI;
import io.github.flofreak.algorithms.BaseAlgorithm;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * The listener for clicking the picture
 * todo Comments
 */
public class PictureClickListener implements MouseListener {

    private final GUI gui;

    public PictureClickListener(GUI gui) {
        this.gui = gui;
    }

    @Override
    /*todo add the ability to click on image to recenter*/
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        System.out.println(x + " " + y);
        Thread t = new Thread(() -> gui.setImage(gui.getAlgorithm().calculate()));
        t.start();
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