package j_monopoly.game.views;

import j_monopoly.assets.Resources;
import j_monopoly.game.board.Spaces;
import j_monopoly.models.Property;
import j_monopoly.models.Space;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public final class MainPage extends JFrame {
    public MainPage() {
        setTitle("JMonopoly");

        try {
            BufferedImage pic = ImageIO.read(Resources.getResource("Logo.png"));
            setIconImage(pic);
        } catch (IOException ignored) {
        }

        initialize();
        setContentPane(root);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
    }

    private JPanel root;
    private JPanel topPanel;

    private void initialize() {
        // Constructors
        root = new JPanel();
        JPanel bottomPanel = new JPanel();
        topPanel = new JPanel();


        // Root panel
        root.add(topPanel);
        root.add(bottomPanel);
        root.setBackground(new Color(205, 230, 208));

        // Top panel
        topPanel.setLayout(new GridLayout(9, 9, 0, 0));
        topPanel.setBackground(new Color(0, 0, 0, 0));

        // Bottom panel
        bottomPanel.setLayout(new GridLayout(2, 1, 0, 6));

        JButton nextBtn = new JButton("Next turn");
        JButton exitBtn = new JButton("Exit");
        exitBtn.addActionListener(e -> dispose());

        bottomPanel.add(nextBtn);
        bottomPanel.add(exitBtn);

        setupBoard();
    }

    private void setupBoard() {
        JPanel[][] panelHolder = new JPanel[9][9];

        int spaceCounter = 0;
        for (int i = 8; i >= 0; i--) {
            panelHolder[i][8] = createSpacePanel(spaceCounter);
            spaceCounter++;
        }

        for (int i = 7; i >= 0; i--) {
            panelHolder[0][i] = createSpacePanel(spaceCounter);
            spaceCounter++;
        }

        for (int i = 1; i <= 8; i++) {
            panelHolder[i][0] = createSpacePanel(spaceCounter);
            spaceCounter++;
        }

        for (int i = 1; i <= 7; i++) {
            panelHolder[8][i] = createSpacePanel(spaceCounter);
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

    private JPanel createSpacePanel(int space) {
        JPanel basePanel = new JPanel();
        basePanel.setSize(64, 64);

        JLabel imgLabel = new JLabel();
        imgLabel.setVerticalAlignment(SwingConstants.CENTER);
        imgLabel.setHorizontalAlignment(SwingConstants.CENTER);

        Space<Object> curr = Spaces.spaces.get(space);
        String resName = switch (curr.type) {
            case GO -> "Go.png";
            case PROPERTY -> ((Property) curr.data).info.group + ".png";
            case CHANCE -> "Chance.png";
            case COMMUNITY_CHEST -> "CChest.png";
            case FREE_PASS -> "Free.png";
            case JAIL -> "Jail.png";
            case GO_TO_JAIL -> "GoJail.png";
        };

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
