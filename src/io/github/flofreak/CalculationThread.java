package io.github.flofreak;

class CalculationThread extends Thread {
    private GUI gui;
    private Mandelbrot mandelbrot;

    CalculationThread(GUI gui, Mandelbrot mandelbrot) {
        this.gui = gui;
        this.mandelbrot = mandelbrot;
    }

    public void run() {
        gui.setImage(mandelbrot.calculate());
    }
}