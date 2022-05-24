package j_monopoly.game;

import j_monopoly.game.board.Properties;
import j_monopoly.game.board.Spaces;
import j_monopoly.game.views.MainPage;

import javax.swing.*;
import java.awt.*;

public class EntryPoint {
    public static void main(String[] args) {
        Properties.populateList();
        Spaces.populateList();

        JFrame frame = MainPage.createFrame();

        frame.setSize(new Dimension(592, 704));
        frame.setResizable(false);

        frame.setVisible(true);
    }
}
