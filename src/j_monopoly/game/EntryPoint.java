package j_monopoly.game;

import j_monopoly.game.views.MainPage;

import javax.swing.*;
import java.awt.*;

public class EntryPoint {
    public static void main(String[] args) {
        JFrame frame = MainPage.createFrame();

        frame.setSize(new Dimension(576, 640));
        frame.setResizable(false);

        frame.setVisible(true);
    }
}
