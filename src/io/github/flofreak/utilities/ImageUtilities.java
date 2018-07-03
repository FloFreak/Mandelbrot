package io.github.flofreak.utilities;

import io.github.flofreak.GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageUtilities {
    /**
     * Opens a File Chooser on button click
     * Saves the image to the selected path
     *
     * @param parent the button on which click the save is performed
     * @param image  the image which should be saved
     */
    public static void saveImageAsJPG(JButton parent, BufferedImage image) {
        //Instantiate the file chooser
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setDialogTitle(GUI.cfg.getProperty("title") + " - Export");
        jFileChooser.setFileFilter(new FileNameExtensionFilter("JPG Files", "jpg"));
        jFileChooser.setAcceptAllFileFilterUsed(false);
        jFileChooser.setSelectedFile(new File(GUI.cfg.getProperty("title") + ".jpg"));

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

}
