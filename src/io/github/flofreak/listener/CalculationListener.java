package io.github.flofreak.listener;

import io.github.flofreak.GUI;
import io.github.flofreak.algorithms.BaseAlgorithm;
import io.github.flofreak.threads.CalculationThread;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculationListener implements ActionListener {

    private final GUI gui;

    public CalculationListener(GUI gui) {
        this.gui = gui;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Shows calculating label
        gui.jLabelLoading.setVisible(true);
        //Stats an anonymous thread, where the image is calculated and drawn
        Thread thread = new Thread(new CalculationThread(gui));
        thread.start();
    }
}
