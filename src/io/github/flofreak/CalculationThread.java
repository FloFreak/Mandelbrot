package io.github.flofreak;

class CalculationThread extends Thread {
    private GUI gui;

    CalculationThread(GUI gui) {
        this.gui = gui;
    }

    public void run() {
        gui.setImage(Mandelbrot.calculate());
    }
}