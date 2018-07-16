/*
 * ImageUtilities
 * v2
 * 16. Juli 2018
 * Copyright (C) 2018 Florian Warnke
 * All rights reserved.
 */
package io.github.flofreak.utilities;

import io.github.flofreak.gui.GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Class with usefull image utilities
 *
 * @author florian.warnke
 * @version v2
 */
public class ImageUtilities {
    /**
     * Opens a File Chooser on button click
     * Saves the image to the selected path
     *
     * @param parent the button on which click the save is performed
     * @param image  the image which should be saved
     */
    public static void saveImageAsJPG(Component parent, BufferedImage image) {
        //Instantiate the file chooser
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setDialogTitle(GUI.getCfg().getProperty("title") + " - Export");
        jFileChooser.setFileFilter(new FileNameExtensionFilter("JPG Files", "jpg"));
        jFileChooser.setAcceptAllFileFilterUsed(false);
        jFileChooser.setSelectedFile(new File(GUI.getCfg().getProperty("title") + ".jpg"));

        //On submit from the file chooser
        if (jFileChooser.showSaveDialog(parent) == JFileChooser.APPROVE_OPTION) {
            try {
                //Writes the image as 'BaseAlgorithm.jpg' to the selected path
                File file = (jFileChooser.getSelectedFile().getName().endsWith(".jpg")
                        ? jFileChooser.getSelectedFile()
                        : new File(jFileChooser.getSelectedFile().getAbsolutePath() + ".jpg"));
                ImageIO.write(image, "jpg", file);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * Rotates image clockwise
     * @param image BufferedImage which should be rotated
     * @return BufferedImage rotated
     */
    public static BufferedImage turnClockwise(BufferedImage image) {
        return getRotatedBufferedImage(image, Math.PI / 2);
    }


    /**
     * Rotates image counterclockwise
     * @param image BufferedImage which should be rotated
     * @return BufferedImage rotated
     */
    public static BufferedImage turnCounterClockwise(BufferedImage image) {
        return getRotatedBufferedImage(image, -Math.PI / 2);
    }


    /**
     * Rotates image with theta
     * @param image BufferedImage which should be rotated
     * @param theta The degree
     * @return BufferedImage rotated
     */
    private static BufferedImage getRotatedBufferedImage(BufferedImage image, double theta) {
        //Size
        int w = image.getWidth();
        int h = image.getHeight();

        BufferedImage rot = new BufferedImage(h, w, BufferedImage.TYPE_INT_RGB);

        //Rotation
        AffineTransform xform = AffineTransform.getRotateInstance(theta, w / 2, h / 2);
        Graphics2D g = rot.createGraphics();
        g.drawImage(image, xform, null);
        g.dispose();

        return rot;
    }

}
