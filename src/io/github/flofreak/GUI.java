package io.github.flofreak;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;

public class GUI extends JFrame {
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 800;
    private GUI gui;
    private BufferedImage image;

    private JSplitPane jSplitPane;
    private JPanel jPanelLeft, jPanelRight;
    private JButton jButtonDraw, jButtonExport;
    private JFormattedTextField jTextFieldMinReell, jTextFieldMaxReell, jTextFieldMinImag, jTextFieldMaxImag, jTextFieldZoom;
    private JLabel jLabelPicture;

    private GUI() {
        this.gui = this;

        this.setTitle("Mandelbrot");
        this.setSize(WIDTH, HEIGHT);
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        initComponents();

        new CalculationThread(this).start();

        this.setVisible(true);
    }

    public static void main(String[] args) {
        new GUI();
    }

    private void saveImageAsJPG(JButton parent) {
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setDialogTitle("Mandelbrot Save");
        jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        jFileChooser.setAcceptAllFileFilterUsed(false);
        if (jFileChooser.showOpenDialog(parent) == JFileChooser.APPROVE_OPTION) {
            try {
                ImageIO.write(image, "jpg", new File(jFileChooser.getSelectedFile() + "/Mandelbrot.jpg"));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    private void initComponents() {
        jSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        jPanelLeft = new JPanel();
        jPanelRight = new JPanel();
        jLabelPicture = new JLabel();
        jTextFieldMaxImag = new JFormattedTextField(NumberFormat.getNumberInstance());
        jTextFieldMinImag = new JFormattedTextField(NumberFormat.getNumberInstance());
        jTextFieldMaxReell = new JFormattedTextField(NumberFormat.getNumberInstance());
        jTextFieldMinReell = new JFormattedTextField(NumberFormat.getNumberInstance());
        jTextFieldZoom = new JFormattedTextField(NumberFormat.getNumberInstance());

        jPanelLeft.setSize(Mandelbrot.WIDTH, Mandelbrot.HEIGHT);

        jButtonDraw = new JButton("Draw");
        jButtonDraw.addActionListener(e -> new CalculationThread(gui).start());

        jButtonExport = new JButton("Export");
        jButtonExport.addActionListener(e -> saveImageAsJPG(((JButton) e.getSource())));

        jLabelPicture.setVisible(true);
        jLabelPicture.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                Thread t = new Thread(() -> gui.setImage(Mandelbrot.calculate()));
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
        });

        createDoubleJTextField(-2.0, jTextFieldMinReell, jTextFieldMinImag);
        createDoubleJTextField(2.0, jTextFieldMaxReell, jTextFieldMaxImag);
        createDoubleJTextField(4.0, jTextFieldZoom);

        GroupLayout groupLayout = new GroupLayout(jPanelRight);
        GroupLayout.ParallelGroup horizontalGroup = groupLayout.createParallelGroup();

        createHorizontalGroup(groupLayout, horizontalGroup, new JLabel("Min Imag"), jTextFieldMinImag);
        createHorizontalGroup(groupLayout, horizontalGroup, new JLabel("Max Imag"), jTextFieldMaxImag);
        createHorizontalGroup(groupLayout, horizontalGroup, new JLabel("Min Real"), jTextFieldMinReell);
        createHorizontalGroup(groupLayout, horizontalGroup, new JLabel("Max Real"), jTextFieldMaxReell);
        createHorizontalGroup(groupLayout, horizontalGroup, new JLabel("Zoom"), jTextFieldZoom);

        jPanelRight.add(jButtonExport);
        jPanelRight.add(jButtonDraw);

        jPanelLeft.add(jLabelPicture);

        jSplitPane.setDividerLocation(Mandelbrot.WIDTH);
        jSplitPane.setLeftComponent(jPanelLeft);
        jSplitPane.setRightComponent(jPanelRight);

        this.add(jSplitPane);
    }

    void setImage(BufferedImage image) {
        this.image = image;
        jLabelPicture.setIcon(new ImageIcon(this.image));
    }

    private void createDoubleJTextField(double value, JFormattedTextField... fields) {
        for (JFormattedTextField field : fields) {
            field.setValue(value);
            field.setColumns(10);
            field.addPropertyChangeListener("value", evt -> ((JFormattedTextField) evt.getSource()).setValue(((Number) evt.getNewValue()).doubleValue()));
        }
    }

    private void createHorizontalGroup(GroupLayout groupLayout, GroupLayout.ParallelGroup horizontalGroup, Component... components) {
        GroupLayout.SequentialGroup sequentialGroup = groupLayout.createSequentialGroup();
        for (Component component : components)
            sequentialGroup.addComponent(component);

        horizontalGroup.addGroup(sequentialGroup);
    }
}
