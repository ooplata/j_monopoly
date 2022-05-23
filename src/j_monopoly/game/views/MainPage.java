package j_monopoly.game.views;

import javax.swing.*;
import java.awt.*;

public final class MainPage {
    public static JFrame createFrame() {
        JFrame frame = new JFrame("Monopoly");
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
        topPanel.setBackground(new Color(255, 255, 255));
        topPanel.setLayout(new GridLayout(9, 9));

        Dimension topDimen = new Dimension(576, 576);
        topPanel.setMinimumSize(topDimen);
        topPanel.setMaximumSize(topDimen);

        // Bottom panel
        bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(242, 242, 247));

        Dimension bottomDimen = new Dimension(576, 128);
        bottomPanel.setMinimumSize(bottomDimen);
        bottomPanel.setMaximumSize(bottomDimen);
    }
}
