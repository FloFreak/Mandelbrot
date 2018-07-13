package io.github.flofreak.gui;

import io.github.flofreak.algorithms.BaseAlgorithm;
import io.github.flofreak.listener.PictureClickListener;

import javax.swing.*;

class LeftPanel extends JPanel {

    private final JLabel jLabelPicture;

    LeftPanel() {
        jLabelPicture = new JLabel();

        this.setSize(BaseAlgorithm.WIDTH, BaseAlgorithm.HEIGHT);

        jLabelPicture.setVisible(true);
        jLabelPicture.addMouseListener(
                new PictureClickListener()
        );

        this.add(jLabelPicture);
    }

    JLabel getJLabelPicture() {
        return jLabelPicture;
    }
}
