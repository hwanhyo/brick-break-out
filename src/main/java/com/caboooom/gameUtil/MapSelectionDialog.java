package com.caboooom.gameUtil;

import com.caboooom.Main;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MapSelectionDialog extends JDialog {

    private static Logger logger = LogManager.getLogger(Main.class);
    private int selectedMapIndex = -1;

    public MapSelectionDialog(Frame owner, String[] mapNames, ImageIcon[] mapImages) {
        super(owner, "MAP 선택", true);

        setLayout(new BorderLayout());
        JPanel mapPanel = new JPanel();
        mapPanel.setLayout(new GridLayout(1, mapNames.length, 10, 10));

        for (int i = 0; i < mapNames.length; i++) {
            JButton mapButton = new JButton(mapNames[i], mapImages[i]);
            mapButton.setHorizontalTextPosition(SwingConstants.CENTER);
            mapButton.setVerticalTextPosition(SwingConstants.BOTTOM);
            final int index = i;
            mapButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    selectedMapIndex = index;
                    dispose();
                }
            });
            mapPanel.add(mapButton);
        }

        add(mapPanel, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(owner);
    }

    public int getSelectedMapIndex() {
        return selectedMapIndex;
    }

    /**
     * image를 일정한 크기의 icon으로 변경합니다.
     * @param path 이미지 경로
     * @param width 넓이
     * @param height 높이
     * @return 이미지 아이콘
     */
    public static ImageIcon loadImageIcon(String path, int width, int height) {
        java.net.URL imgURL = Main.class.getResource(path);
        if (imgURL != null) {
            ImageIcon icon = new ImageIcon(imgURL);
            Image img = icon.getImage();
            Image scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(scaledImg);
        } else {
            logger.log(Level.ERROR, "File not found: " + path);
            return null;
        }
    }

    public static int showMapSelectionDialog(Frame owner) {
        String[] mapNames = {"MAP1", "MAP2", "MAP3", "MAP4", "MAP5"};

        int imgWidth = 100;
        int imgHeight = 100;
        ImageIcon[] mapImages = {
                loadImageIcon("/images/map1.png", imgWidth, imgHeight),
                loadImageIcon("/images/map2.png", imgWidth, imgHeight),
                loadImageIcon("/images/map3.png", imgWidth, imgHeight),
                loadImageIcon("/images/map4.png", imgWidth, imgHeight),
                loadImageIcon("/images/map5.png", imgWidth, imgHeight)
        };

        MapSelectionDialog dialog = new MapSelectionDialog(owner, mapNames, mapImages);
        dialog.setVisible(true);
        return dialog.getSelectedMapIndex();
    }
}
