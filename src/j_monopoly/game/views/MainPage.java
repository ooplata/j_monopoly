package j_monopoly.game.views;

import j_monopoly.assets.Resources;
import j_monopoly.enums.SpaceType;
import j_monopoly.game.board.Properties;
import j_monopoly.game.board.Spaces;
import j_monopoly.models.cards.PropertyCard;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public final class MainPage {
    public static JFrame createFrame() {
        JFrame frame = new JFrame("JMonopoly");

        try {
            BufferedImage pic = ImageIO.read(Resources.getResource("Logo.png"));
            frame.setIconImage(pic);
        } catch (IOException ignored) {
        }

        frame.setContentPane(new MainPage().root);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();

        return frame;
    }

    private JPanel root;
    private JPanel topPanel;
    private JPanel bottomPanel;

    private void createUIComponents() {
        // Top panel
        topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(9, 9, 0, 0));
        topPanel.setBackground(new Color(0, 0, 0, 0));

        Dimension topDimen = new Dimension(576, 576);
        topPanel.setMinimumSize(topDimen);
        topPanel.setMaximumSize(topDimen);

        // Bottom panel
        bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(0, 0, 0, 16));

        Dimension bottomDimen = new Dimension(576, 128);
        bottomPanel.setMinimumSize(bottomDimen);
        bottomPanel.setMaximumSize(bottomDimen);

        setupBoard();
    }

    private void setupBoard() {
        JPanel[][] panelHolder = new JPanel[9][9];

        int spaceCounter = 0;
        int propCounter = 0;

        for (int i = 8; i >= 0; i--) {
            SpaceType space = Spaces.spaces.get(spaceCounter);
            panelHolder[i][8] = createSpacePanel(space, propCounter);
            if (space == SpaceType.PROPERTY) propCounter++;
            spaceCounter++;
        }

        for (int i = 7; i >= 0; i--) {
            SpaceType space = Spaces.spaces.get(spaceCounter);
            panelHolder[0][i] = createSpacePanel(space, propCounter);
            if (space == SpaceType.PROPERTY) propCounter++;
            spaceCounter++;
        }

        for (int i = 1; i <= 8; i++) {
            SpaceType space = Spaces.spaces.get(spaceCounter);
            panelHolder[i][0] = createSpacePanel(space, propCounter);
            if (space == SpaceType.PROPERTY) propCounter++;
            spaceCounter++;
        }

        for (int i = 1; i <= 7; i++) {
            SpaceType space = Spaces.spaces.get(spaceCounter);
            panelHolder[8][i] = createSpacePanel(space, propCounter);
            if (space == SpaceType.PROPERTY) propCounter++;
            spaceCounter++;
        }

        panelHolder[4][4] = createLogoPanel();
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                JPanel panel;
                if (panelHolder[y][x] != null) panel = panelHolder[y][x];
                else panel = new JPanel();

                panel.setBackground(new Color(0, 0, 0, 0));
                topPanel.add(panel);
            }
        }
    }

    private JPanel createSpacePanel(SpaceType space, int propCounter) {
        JPanel basePanel = new JPanel();
        basePanel.setSize(64, 64);

        JLabel imgLabel = new JLabel();
        imgLabel.setVerticalAlignment(SwingConstants.CENTER);
        imgLabel.setHorizontalAlignment(SwingConstants.CENTER);

        String resName;
        switch (space) {
            case GO -> resName = "Go.png";
            case PROPERTY -> {
                PropertyCard info = Properties.properties.get(propCounter).info;
                resName = info.group + ".png";
            }
            case CHANCE -> resName = "Chance.png";
            case COMMUNITY_CHEST -> resName = "CChest.png";
            case FREE_PASS -> resName = "Free.png";
            case JAIL -> resName = "Jail.png";
            case GO_TO_JAIL -> resName = "GoJail.png";
            default -> resName = "Empty.png";
        }

        try {
            BufferedImage pic = ImageIO.read(Resources.getResource(resName));
            imgLabel.setIcon(new ImageIcon(pic));
        } catch (IOException ignored) {
        }
        basePanel.add(imgLabel);
        return basePanel;
    }

    private JPanel createLogoPanel() {
        JPanel basePanel = new JPanel();
        basePanel.setSize(64, 64);

        JLabel imgLabel = new JLabel();
        imgLabel.setVerticalAlignment(SwingConstants.CENTER);
        imgLabel.setHorizontalAlignment(SwingConstants.CENTER);

        try {
            BufferedImage pic = ImageIO.read(Resources.getResource("Logo.png"));
            imgLabel.setIcon(new ImageIcon(pic));
        } catch (IOException ignored) {
        }

        basePanel.add(imgLabel);
        return basePanel;
    }
}
