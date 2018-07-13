package io.github.flofreak.listener;

import io.github.flofreak.threads.CalculationThread;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculationListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        //Stats an anonymous thread, where the image is calculated and drawn
        Thread thread = new Thread(new CalculationThread());
        thread.start();
    }
}
