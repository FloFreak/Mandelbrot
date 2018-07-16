/*
 * LeftPanel
 * v1
 * 16. Juli 2018
 * Copyright (C) 2018 Florian Warnke
 * All rights reserved.
 */
package io.github.flofreak.gui;

import io.github.flofreak.algorithms.BaseAlgorithm;
import io.github.flofreak.listener.CoordinateListener;
import io.github.flofreak.listener.PictureClickListener;

import javax.swing.*;

/**
 * Class for the left Panel
 * Display for the image
 *
 * @author florian.warnke
 * @version v1
 */
class LeftPanel extends JPanel {

    private final JLabel jLabelPicture; //The label which displays the image

    /**
     * Constructor for the LeftPanel
     * Configuration from the Label to fit the image
     */
    LeftPanel() {
        jLabelPicture = new JLabel();
        jLabelPicture.setToolTipText(null);

        //Sizes the panel to fit the Image as in properties defined
        setSize(BaseAlgorithm.WIDTH, BaseAlgorithm.HEIGHT);

        jLabelPicture.setVisible(true);

        //Adds the Listener for the zoom
        jLabelPicture.addMouseListener(
                new PictureClickListener()
        );
        //Adds the Listener for the coordinate display
        jLabelPicture.addMouseMotionListener(
                new CoordinateListener()
        );

        add(jLabelPicture);
    }

    /**
     * Getter for the Pictur Label
     *
     * @return jLabel for the picture
     */
    JLabel getJLabelPicture() {
        return jLabelPicture;
    }
}
