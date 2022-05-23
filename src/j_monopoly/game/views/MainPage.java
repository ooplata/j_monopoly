package j_monopoly.game.views;

import javax.swing.*;

public final class MainPage {
    public static JFrame createFrame() {
        JFrame frame = new JFrame("Monopoly");
        frame.setContentPane(new MainPage().root);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();

        return frame;
    }

    private JPanel root;
}
