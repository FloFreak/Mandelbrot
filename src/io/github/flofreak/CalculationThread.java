package io.github.flofreak;

class CalculationThread extends Thread {
    private GUI gui;
    private Mandelbrot mandelbrot;

    CalculationThread(GUI gui, Mandelbrot mandelbrot) {
        this.gui = gui;
        this.mandelbrot = mandelbrot;
    }

    /**
     * Calculates the image and let the GUI draw it
     */
    public void run() {
        gui.setImage(mandelbrot.calculate());
    }
}